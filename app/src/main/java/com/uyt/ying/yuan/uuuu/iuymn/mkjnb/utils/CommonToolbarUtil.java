package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;

public class CommonToolbarUtil {
    public static void initToolbar(Activity activity, String title){
        TextView titleTv = Utils.getContentView(activity).findViewById(R.id.toolbar_title_tv);
        ImageView backIv = Utils.getContentView(activity).findViewById(R.id.toolbar_back_iv);
        if(titleTv!=null){
            titleTv.setText(title);
        }

        if(backIv!=null){
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity!=null){
                        activity.finish();
                    }
                }
            });
        }
    }
    public static void initToolbar(Activity activity, String title, WebView webView){
        TextView titleTv = Utils.getContentView(activity).findViewById(R.id.toolbar_title_tv);
        ImageView backIv = Utils.getContentView(activity).findViewById(R.id.toolbar_back_iv);
        if(titleTv!=null){
            titleTv.setText(title);
        }

        if(backIv!=null){
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(webView.canGoBack()){
                        webView.goBack();
                    }else {
                        if(activity!=null){
                            activity.finish();
                        }
                    }
                }
            });
        }
    }
}
