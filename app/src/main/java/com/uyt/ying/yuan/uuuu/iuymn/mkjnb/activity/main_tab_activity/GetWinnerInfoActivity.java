package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

/*
更多中奖信息界面
 */
public class GetWinnerInfoActivity extends BaseActivity {
    private WebView webView;
    private String lotteryId;
    private String yestodayId;
    private TextView actionBarBack;
    private TextView actionBarCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_winner_info);
        Intent intent = getIntent();
        lotteryId = intent.getStringExtra("lottery_id");//中奖信息界面传递的值
        yestodayId = intent.getStringExtra("yestodayId");//昨日盈利界面传递的值
        bindView();
        initWebview();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        webView = findViewById(R.id.getwinner_webview);
        actionBarBack = findViewById(R.id.action_bar_return);
        actionBarCenter = findViewById(R.id.action_bar_text);
        actionBarCenter.setText(Utils.getString(R.string.玩家信息));
        actionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String frontUrl = SharePreferencesUtil.getString(this, CommonStr.FRONT_URL, "");
        String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
        String token = SharePreferencesUtil.getString(this, "token", "");
        if (!StringMyUtil.isEmptyString(lotteryId)) {
            webView.loadUrl(frontUrl + "home/getReward?user_id=" + user_id + "&deviceCode=" + deviceCode + "&token=" + token + "&type=" + "6" + "&lottery_id=" + lotteryId + "&type=" + 0);
        } else if (!StringMyUtil.isEmptyString(yestodayId)) {
            webView.loadUrl(frontUrl + "home/getReward?user_id=" + user_id + "&deviceCode=" + deviceCode + "&token=" + token + "&type=" + "6" + "&lottery_id=" + yestodayId + "&type=" + 1);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ProgressDialogUtil.show(GetWinnerInfoActivity.this);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressDialogUtil.stop(GetWinnerInfoActivity.this);
            }
        });
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
