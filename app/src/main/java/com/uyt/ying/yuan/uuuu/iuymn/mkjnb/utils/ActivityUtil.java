package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.uyt.ying.rxhttp.net.utils.LogUtils;

import java.util.Stack;

public class ActivityUtil {
    private Stack<Activity> activityStack;
    private static ActivityUtil instance;

    // 单例模式中获取唯一的ExitApplication实例  单利模式双重检查
    public static  ActivityUtil getInstance() {
        if (null == instance) {
            synchronized (ActivityUtil.class) {
                if (instance == null) {
                    instance = new ActivityUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 栈中activity总数
     * @return
     */
    public int getActivitySize(){
        return activityStack.size();
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
        LogUtils.e("activity入栈size:"+activityStack.size());
        for (int i =0;i<activityStack.size();i++){
            LogUtils.e("activity_name:"+activityStack.get(i).getLocalClassName()+
                    "\n __taskId:"+activityStack.get(i).getTaskId()+" \n __Id:"+activityStack.get(i).toString());
        }
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activity=null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
            LogUtils.e("activity出栈size:"+activityStack.size());
            for (int i =0;i<activityStack.size();i++){
                LogUtils.e("activity_name:"+activityStack.get(i).getLocalClassName()+
                        "\n __taskId:"+activityStack.get(i).getTaskId()+" \n __Id:"+activityStack.get(i).toString());
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    public void finishActivityWithoutLoginActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            Activity activity = activityStack.get(i);
            if (null != activity){
                String name = activity.getClass().getName();
                if(!name.contains("LoginActivity")){
                    if(!activity.isFinishing()){
                        activity.finish();
                    }
                }
            }
        }
    }
    /**
     * 从管理器退出应用程序  service等也会kill
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // 移除Activity
    public void removeActivity(Activity activity) {
        if (activityStack != null)
            activityStack.remove(activity);
    }

    // TODO 遍历所有Activity并finish  但service等会存在
    public void exitSystem() {
        try {
            finishAllActivity();
            LogUtils.e(""+activityStack.size());
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}