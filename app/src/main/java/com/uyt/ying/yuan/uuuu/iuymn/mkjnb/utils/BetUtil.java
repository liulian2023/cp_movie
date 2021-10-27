
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/*
  下注接口选择
   */
public class BetUtil {
    private  static  String TAG="BetUtil";

    public static  void requestBet(final Context context, long user_id, final int game, int type_id, String rule_id, String amount, String lotteryqishu,BetListener betListener){
        String url="";
        if(game==1){
            url= RequestUtil.REQUEST_7r;//快三
        }else if(game==2){
            url=RequestUtil.REQUEST_02ssc;//时时彩
        }else if(game==3){
            url=RequestUtil.REQUEST_02sc;//赛车
        }else if(game==4){
            url=RequestUtil.REQUEST_03lhc;//六合彩
        }else if(game==5){
            url=RequestUtil.REQUEST_03dd;//pc蛋蛋
        }else if(game==6){
            url=RequestUtil.REQUEST_02ha;//北京快乐8
        }else if(game==7){
            url=RequestUtil.REQUEST_02farm;//农场
        }else if(game==8){
            url=RequestUtil.REQUEST_02happyten;//广东快乐10分
        }else if(game==9){
            url=RequestUtil.REQUEST_02xuanwu;//11选5
        }
        HashMap<String, Object> data = new HashMap<>();
        String token = SharePreferencesUtil.getString(context, "token", "");
        String domain = SharePreferencesUtil.getString(context, "domain", "");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);
        data.put("user_id",user_id);
        data.put("type_id",type_id);
        data.put("rule_id",rule_id);
        data.put("amount",amount);
        data.put("lotteryqishu",lotteryqishu);
        data.put("source",2);
        data.put("curtime",format);//当前时间
        data.put("token",token);//用户token
//        data.put("lianma",lianma);//用户token
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                ToastUtil.showToast(jsonObject.getString("message"));
                if(betListener!=null){
                    betListener.OnBetSuccuseListener();
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                ToastUtil.showToast(Utils.getString(R.string.下注失败));
                if(betListener!=null){
                    betListener.OnBetFailListener(messageHead);
                }
            }
        });

    }

    public static  void requestBet(final Context context, long user_id, final int game, int type_id, String rule_id, String amount, String lotteryqishu,int size,String anchorAccount,String remoteLiveManagementId,BetListener betListener){
        String url="";
        if(game==1){
            url= RequestUtil.REQUEST_7r;//快三
        }else if(game==2){
            url=RequestUtil.REQUEST_02ssc;//时时彩
        }else if(game==3){
            url=RequestUtil.REQUEST_02sc;//赛车
        }else if(game==4){
            url=RequestUtil.REQUEST_03lhc;//六合彩
        }else if(game==5){
            url=RequestUtil.REQUEST_03dd;//pc蛋蛋
        }else if(game==6){
            url=RequestUtil.REQUEST_02ha;//北京快乐8
        }else if(game==7){
            url=RequestUtil.REQUEST_02farm;//农场
        }else if(game==8){
            url=RequestUtil.REQUEST_02happyten;//广东快乐10分
        }else if(game==9){
            url=RequestUtil.REQUEST_02xuanwu;//11选5
        }
        HashMap<String, Object> data = new HashMap<>();
        String amountStr="";
        for (int i = 0; i < size; i++) {
            amountStr+=amount+",";
        }
        amountStr = amountStr.substring(0, amountStr.length()-1);
        String token = SharePreferencesUtil.getString(context, "token", "");
        String domain = SharePreferencesUtil.getString(context, "domain", "");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);
        data.put("user_id",user_id);
        data.put("type_id",type_id);
        data.put("rule_id",rule_id);
        data.put("amount",amountStr);
        data.put("lotteryqishu",lotteryqishu);
        data.put("source",2);
        data.put("curtime",format);//当前时间
        data.put("token",token);//用户token
        data.put("anchorAccount",anchorAccount);//用户token
        data.put("remoteLiveManagementId",remoteLiveManagementId);//用户token
//        data.put("lianma",lianma);//用户token
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                ToastUtil.showToast(jsonObject.getString("message"));
                if(betListener!=null){
                    betListener.OnBetSuccuseListener();
                }

            }

            @Override
            public void failed(MessageHead messageHead) {
                if(betListener!=null){
                    betListener.OnBetFailListener(messageHead);
                }
            }
        });

    }
    public  static interface  BetListener{
        void OnBetSuccuseListener();
        void OnBetFailListener(MessageHead messageHead);
    }
    private BetListener betListener =null;

    public void setBetSuccuseListener(BetListener betListener) {
        this.betListener = betListener;
    }
}
