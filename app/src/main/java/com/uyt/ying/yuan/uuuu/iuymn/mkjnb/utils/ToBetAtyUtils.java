package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.content.Intent;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.Happy10.Happy10BetActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.K3Activity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.MarksixActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.RaceActivity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.XuanwuActivity;



import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.LuckFram.LuckFarmBetActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.happy8.Happy8Activity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.pcDanDan.PcDanDanBetActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.ssc.SscBetActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ShoppingWebViewActivity;

public class ToBetAtyUtils {
    /**
     * @param context      上下文
     * @param type_id      彩票type_id
     * @param isopenOffice 是否有官方彩
     * @param isStart      当前彩票是否有销售(为0: 倒计时显示Utils.getString(R.string.暂停销售))
     * @param typename     彩票typeName
     * @param game         彩票大类
     */
    public static void toBetActivity(Context context, int game, String typename, int type_id, String isopenOffice, String isStart) {
        /*
1》快3
2》时时彩
3》赛车
4》六合彩
5》蛋蛋
6》快8
7》农场
8》快乐10分
9》11选5
         */
        if (game == 1) {//快三
            Intent intent = new Intent(context, K3Activity.class);
            intent.putExtra("game", String.valueOf(game));
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", String.valueOf(type_id));
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);

        }
        else if(game==2){//s时时彩
            Intent intent = new Intent(context, SscBetActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);
        }else if (game == 3) {
            Intent intent = new Intent(context, RaceActivity.class);
            intent.putExtra("game", String.valueOf(game));
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", String.valueOf(type_id));
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);
        }
        else if(game==4){//六合彩
            Intent intent = new Intent(context, MarksixActivity.class);
            intent.putExtra("game", String.valueOf(game));
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", String.valueOf(type_id));
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);
        }
       else if (game == 5) {//pc蛋蛋

            Intent intent = new Intent(context, PcDanDanBetActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);

        }else if(game==6){//北京快乐8
            Intent intent = new Intent(context, Happy8Activity.class);
            intent.putExtra("typename", typename);
            intent.putExtra("game", game);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);

        } else if(game==7){//重庆幸运农场
            Intent intent = new Intent(context, LuckFarmBetActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);
        }
        else if(game==8){//广东快乐10分
            Intent intent = new Intent(context, Happy10BetActivity.class);
            intent.putExtra("game", game);
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);

        }else if (game == 9) {
            Intent intent = new Intent(context, XuanwuActivity.class);
            intent.putExtra("game", String.valueOf(game));
            intent.putExtra("typename", typename);
            intent.putExtra("type_id", String.valueOf(type_id));
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);

        }
        else {

            Intent intent = new Intent(context, ShoppingWebViewActivity.class);
            intent.putExtra("type_id", type_id);
            intent.putExtra("isopenOffice", isopenOffice);
            intent.putExtra("isStart", isStart);
            intent.putExtra("game", game);
            intent.putExtra("typename", typename);
//            if (!fromBetInfo) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
            context.startActivity(intent);
        }



    }

}
