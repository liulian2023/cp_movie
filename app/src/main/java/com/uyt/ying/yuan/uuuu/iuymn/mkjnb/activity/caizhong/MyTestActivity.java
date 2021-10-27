/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LastLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MyTestActivity extends BaseActivity {


    private Map<String, Object> dataCountDown;//倒计时请求参数
    private Map<String, Object> dataLastLottery;//上期开奖结果请求参数
    KJCountDown kjCountDown;
    LastLottery lastLottery;

    @Override
    protected void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_k3);

        Reqtimer.schedule(ReqTimerTask, 0, 1000);//延时0s，每隔10秒执行一次run方法
    }


    Timer Reqtimer = new Timer();
    TimerTask ReqTimerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            //  message.obj = count;   //从这里把你想传递的数据放进去就行了
            ReqHandler.sendMessage(message);

        }
    };
    Handler ReqHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

                //请求上期开奖结果 flag = "1"; pageNo = 1; pageSize = 1; type_id = 1
                /*dataCountDown = new HashMap<>();
                dataCountDown.put("type_id", "1");//传入的参数与数据*/
                dataLastLottery = new HashMap<>();
                dataLastLottery.put("flag", "1");//传入的参数与数据
                dataLastLottery.put("pageNo", 1);
                dataLastLottery.put("pageSize", 1);
                dataLastLottery.put("type_id", "1");
                Utils.docking(dataLastLottery, "/game/lastLottery", 1, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        Gson gson = new Gson();
                        //在重新对lastLottery赋值的时候 取出上期开奖号码
                    //    String localLastqishu = lastLottery.getGameLotterylist().get(0).getLotteryqishu();
                  //      kjCountDown = gson.fromJson(content, KJCountDown.class);
                        lastLottery = gson.fromJson(content, LastLottery.class);

                    }

                    @Override
                    public void failed(MessageHead messageHead) {
                    }
                });


            }
            super.handleMessage(msg);
        }
    };


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
