package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import android.content.Context;
import android.content.Intent;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseWebActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class USTDWebViewActivity extends BaseWebActivity {
    @Override
    public String getToolBarTitle() {
        return Utils.getString(R.string.转账教程);
    }

    public static void startAty(Context context, String assetPath){
        Intent intent = new Intent(context, USTDWebViewActivity.class);
        intent.putExtra("assetPath",assetPath);
        context.startActivity(intent);
    }

}
