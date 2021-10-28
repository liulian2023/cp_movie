package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import android.util.Log;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class CommandUtil {
    public static final String TAG = CommandUtil.class.getSimpleName();
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_EXIT = "exit\n";
    private static final boolean ISDEBUG = true;

    /**
     * 执行单条命令
     *
     * @param command
     * @return
     */
    public static long execute(String command) {
        ArrayList<String> results = new ArrayList<>();
        results.add(command);
        return execute(results);
    }

    /**
     * 可执行多行命令（bat）
     *
     * @param results
     * @return
     */
    public static long execute(ArrayList<String>results) {
        int status = -1;
        if (results == null || results.size() == 0) {
            return 0;
        }
        debug("execute command start : " + results);
        Process process = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        StringBuilder errorMsg = null;

        DataOutputStream dos = null;
        try {
            // TODO
            process = Runtime.getRuntime().exec(COMMAND_SH);

            dos = new DataOutputStream(process.getOutputStream());
            for (String command : results) {
                if (command == null) {
                    continue;
                }
                dos.write(command.getBytes());
                dos.writeBytes(COMMAND_LINE_END);
                dos.flush();
            }
            dos.writeBytes(COMMAND_EXIT);
            dos.flush();

            status = process.waitFor();

            errorMsg = new StringBuilder();
            successReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            errorReader = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
            String lineStr;
            String delay = new String();
            while ((lineStr = successReader.readLine()) != null) {
                if (lineStr.contains("avg")) {
                    int i = lineStr.indexOf("/", 20);
                    int j = lineStr.indexOf(".", i);
                    Utils.logE(TAG, "ping success 延迟::"+ lineStr.substring(i + 1, j) );
                    delay = lineStr.substring(i + 1, j);
                    return Long.parseLong(StringMyUtil.isEmptyString(delay)?"10000":delay);
                }
//                results.add(lineStr);
                debug(" command line item : " + lineStr);
            }
            while ((lineStr = errorReader.readLine()) != null) {
                errorMsg.append(lineStr);
                Utils.logE(TAG, "ping error host:: ");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (successReader != null) {
                    successReader.close();
                }
                if (errorReader != null) {
                    errorReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        debug(String.format(Locale.CHINA,
                "execute command end,errorMsg:%s,and status %d: ", errorMsg,
                status));
        return 10000;
    }

    /**
     * DEBUG LOG
     *
     * @param message
     */
    private static void debug(String message) {
        if (ISDEBUG) {
            Log.d(TAG, message);
        }
    }

}
