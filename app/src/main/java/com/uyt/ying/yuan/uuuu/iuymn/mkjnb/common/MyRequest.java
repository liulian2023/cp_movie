/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.NetworkUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

public class MyRequest {

    public interface MyRequestListener {
        void success(String content);

        void failed(String content);
    }

    public interface MyRequestListener1 {
        void success1(String content);

        void failed1( String  failMessage );
    }

    



    public static void   ReqMemberMoney(Context mContext,int isCache,final MyRequestListener myRequestListener){
        //   final HashMap<String,String> resultMap=new HashMap<>();
        String countdown_url = "/member/memberMoney.json";
        Map<String, Object> dataMap = new HashMap<>();//倒计时请求参数

        dataMap.put("user_id",SharePreferencesUtil.getLong(mContext, "user_id", 0L));
        Utils.docking(dataMap, countdown_url, isCache, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String result) {

                //     resultMap.put("result",result);
                myRequestListener.success(result);
            }
            @Override
            public void failed(MessageHead messageHead) {
                myRequestListener.failed(messageHead.getInfo());
                //  resultMap.put("result",messageHead.getInfo());
            }
        });
        //   return resultMap.get("result");
    }

    public static void   ReqMemberMoney1(Context mContext,int isCache,final MyRequestListener myRequestListener){
        //   final HashMap<String,String> resultMap=new HashMap<>();
        String countdown_url = "/member/memberMoney.json";
        Map<String, Object> dataMap = new HashMap<>();//倒计时请求参数

        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap.put("user_id",SharePreferencesUtil.getLong(mContext, "user_id", 0L));
        dataMap.put("restAmount",1);
        Utils.docking(dataMap, countdown_url, isCache, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String result) {

                //     resultMap.put("result",result);
                myRequestListener.success(result);
            }
            @Override
            public void failed(MessageHead messageHead) {
                myRequestListener.failed(messageHead.getInfo());
                //  resultMap.put("result",messageHead.getInfo());
            }
        });
        //   return resultMap.get("result");
    }

    //倒计时
    public static void   ReqCount(Context mContext,String game, String type_id,int isCache,final MyRequestListener myRequestListener){
     //   final HashMap<String,String> resultMap=new HashMap<>();
        String countdown_url = "";
        Map<String, Object> dataMap = new HashMap<>();//倒计时请求参数

        Resources res = mContext.getResources();
        String[] countdowns = res.getStringArray(R.array.countdown);
        if (Integer.parseInt(game) <= countdowns.length) {
            countdown_url = countdowns[Integer.parseInt(game) - 1];
        }
        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap.put("type_id", type_id);//传入的参数与数据
        if (NetworkUtil.isNetworkConnected(mContext)){
            Utils.docking(dataMap, countdown_url, isCache, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String result) {

                    //     resultMap.put("result",result);
                    myRequestListener.success(result);
                }
                @Override
                public void failed(MessageHead messageHead) {
                    myRequestListener.failed(messageHead.getInfo());
                    //  resultMap.put("result",messageHead.getInfo());
                }
            });
        }

     //   return resultMap.get("result");
    }

    //上期开奖结果
    public static void  ReqlastLottery(Context mContext,String game, String type_id,int pageNo,int pageSize,int isCache,final MyRequestListener myRequestListener){
      //  final Map<String,String> resultMap=new HashMap<>();
        String lastLottery_url = "";
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数

        Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.lastLottery);
        if (Integer.parseInt(game) <= lastLotterys.length) {
            lastLottery_url = lastLotterys[Integer.parseInt(game) - 1];
        }
        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap.put("type_id", type_id);//传入的参数与数据
        dataMap.put("flag", isCache==1?"1":"0");//TODO popWindow传1
        dataMap.put("pageNo", pageNo);
        dataMap.put("pageSize", pageSize);

        if (NetworkUtil.isNetworkConnected(mContext)){
            Utils.docking(dataMap, lastLottery_url, isCache, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String result) {
                    myRequestListener.success(result);
                    //   resultMap.put("result",result);

                }
                @Override
                public void failed(MessageHead messageHead) {
                    //       resultMap.put("result",messageHead.getInfo());
                    myRequestListener.failed(messageHead.getInfo());
                }
            });
        }

   //     return resultMap.get("result");
    }
    //游戏规则
    public static void  ReqgetGroup(Context mContext,String game, String type_id,int isCache,final MyRequestListener myRequestListener){
        String getGroup_url = "";
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数

        Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.getgroup);
        if (Integer.parseInt(game) <= lastLotterys.length) {
            getGroup_url = lastLotterys[Integer.parseInt(game) - 1];
        }
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + game+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            dataMap.put("game", game);
            dataMap.put("user_id",SharePreferencesUtil.getLong(mContext, "user_id", 0L));
            dataMap.put("type_id", type_id);//传入的参数与数据

            if (NetworkUtil.isNetworkConnected(mContext)){
                String finalGetGroup_url = getGroup_url;
                Utils.docking(dataMap, getGroup_url, isCache, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String result) {
                        SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE +  game+type_id,result);
                        myRequestListener.success(result);
                        //      resultMap.put("result",result);
                    }
                    @Override
                    public void failed(MessageHead messageHead) {
                        //      resultMap.put("result",messageHead.getInfo());
                        myRequestListener.failed(messageHead.getInfo());
                    }
                });
            }
        }else {
            myRequestListener.success(jsonStr);
        }



//        return resultMap.get("result");
    }

    //官方游戏规则
    public static void  ReqgetNewplay(Context mContext,String game, String type_id,int isCache,final MyRequestListener myRequestListener){
        //     final Map<String,String> resultMap=new HashMap<>();
        String getUrl = "/newPlay/getRule";
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数

       /* Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.getgroup);
        if (Integer.parseInt(game) <= lastLotterys.length) {
            getGroup_url = lastLotterys[Integer.parseInt(game) - 1];
        }*/

        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap.put("game", game);
        dataMap.put("user_id",SharePreferencesUtil.getLong(mContext, "user_id", 0L));
        dataMap.put("typeId", type_id);//传入的参数与数据

        if (NetworkUtil.isNetworkConnected(mContext)){
            Utils.docking(dataMap, getUrl, isCache, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String result) {
                    myRequestListener.success(result);
                    //      resultMap.put("result",result);
                }
                @Override
                public void failed(MessageHead messageHead) {
                    //      resultMap.put("result",messageHead.getInfo());
                    myRequestListener.failed(messageHead.getInfo());
                }
            });
        }

    }

    //官方游戏规则
    public static void  ReqNewplayBet(Context mContext, String game, String type_id,String lotteryqishu, List<HashMap> betList, int isCache, final MyRequestListener1 myRequestListener1){
        //     final Map<String,String> resultMap=new HashMap<>();
        String getUrl = "/newPlay/bet";
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数

        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap.put("game", game);
        dataMap.put("userId",SharePreferencesUtil.getLong(mContext, "user_id", 0L));
        dataMap.put("typeId", type_id);//传入的参数与数据
        dataMap.put("source",2);
        dataMap.put("curtime",TimerUtils.getTimeStyleOne());//当前时间
        dataMap.put("token",SharePreferencesUtil.getString(mContext,"token",""));
        dataMap.put("lotteryqishu",lotteryqishu);
        dataMap.put("betList", betList);


        if (NetworkUtil.isNetworkConnected(mContext)){
            Utils.docking(dataMap, getUrl, isCache, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String result) {
                    myRequestListener1.success1(result);
                    //      resultMap.put("result",result);
                }
                @Override
                public void failed(MessageHead messageHead) {
                    //      resultMap.put("result",messageHead.getInfo());
                    myRequestListener1.failed1(messageHead.getInfo());
                }
            });
        }

    }


    //投注  dataXiadan = new HashMap<>();
    //        dataXiadan.put("type_id",list.get(0).getType_id());//投注下单传入的参数与数据
    //        dataXiadan.put("user_id",SharePreferencesUtil.getLong(TouzhuActivity.this,"user_id",0L));
    //        dataXiadan.put("curtime",curtime);
    //        dataXiadan.put("token",SharePreferencesUtil.getString(TouzhuActivity.this,"token",""));
    //        dataXiadan.put("lotteryqishu",qishu);
    //      //  dataXiadan.put("domin","https://xunbo678.com");
    //     //   dataXiadan.put("source","2");
    //        dataXiadan.put("amount",amount);
    //        dataXiadan.put("rule_id",rule_id);

    /**
     *
     * @param mContext
     * @param type_id
     * @param curtime
     * @param lotteryqishu
     * @param amount
     * @param rule_id
     * @param isCache
     * @param myRequestListener1
     */
    public static void  ReqTouzhu(Context mContext,String game,String type_id, String curtime,String  lotteryqishu,
            String amount,String rule_id,String index,String ma,int isCache,final MyRequestListener1 myRequestListener1){

        String touzhu_url = "";
        HashMap<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数

        Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.cpmovie_touzhu);
        if (Integer.parseInt(game) <= lastLotterys.length) {
            touzhu_url = lastLotterys[Integer.parseInt(game) - 1];
        }
        String domain = SharePreferencesUtil.getString(mContext,"domain","");
        dataMap = new HashMap<>();
        dataMap.put("type_id",type_id);//投注下单传入的参数与数据
        dataMap.put("user_id",SharePreferencesUtil.getLong(mContext,"user_id",0L));
        dataMap.put("curtime",curtime);
        dataMap.put("token",SharePreferencesUtil.getString(mContext,"token",""));
        dataMap.put("lotteryqishu",lotteryqishu);
        dataMap.put("amount",amount);

        dataMap.put("source",2);

        if (game.equals("1")&&!index.equals("0")){
            dataMap.put("ma",ma);
        }
        if (Integer.valueOf(game)==4&&(Integer.valueOf(index)==8||Integer.valueOf(index)==9||Integer.valueOf(index)==10)){
            dataMap.put("ma",ma);
        }
        if ((Integer.valueOf(game)==7&&!StringMyUtil.isEmptyString(ma))||(Integer.valueOf(game)==8&&!StringMyUtil.isEmptyString(ma))){
            dataMap.put("lianma",ma);
            List<String> ruleidList = Arrays.asList(rule_id.split(","));
            rule_id = ruleidList.get(0);
        }

        dataMap.put("rule_id",rule_id);
     //   if (NetworkUtil.isNetworkConnected(mContext)){
/*            Utils.docking(dataMap, touzhu_url, isCache, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String result) {
                    myRequestListener1.success1(result);
                    //      resultMap.put("result",result);
                }
                @Override
                public void failed(MessageHead messageHead) {
                    //      resultMap.put("result",messageHead.getInfo());
                    if (!StringMyUtil.isEmptyString(messageHead)){

                        myRequestListener1.failed1(messageHead);
                    }
                }
            });*/
        HttpApiUtils.CpRequest((Activity) mContext, null, touzhu_url, dataMap, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                myRequestListener1.success1(result);
            }

            @Override
            public void onFailed(String msg) {
                myRequestListener1.failed1(msg);
            }
        });
       /* }else {

        }*/

//        return resultMap.get("result");
    }



}
