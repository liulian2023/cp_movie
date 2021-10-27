package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
    /*
    消息和公告详情 activity
     */

public class NoticeMessageDetailsActivity extends BaseActivity {
    private WebView webView;
    private Long notice_id;
    private Long letter_id;
    private boolean fromMessage;
    private String title;
    private String content;
    private String time;
    private   String sendTime;
    private TextView timeTv;
    private TextView titleTv;
    private Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        Intent intent = getIntent();
        id=intent.getLongExtra("id",0l);
        fromMessage= intent.getBooleanExtra("fromMessage",false);
        title= intent.getStringExtra("title");
        content= intent.getStringExtra("content");
        time= intent.getStringExtra("time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(time));
        sendTime = format.format(date);
        bindView();
        requestIsRead();
        webView=findViewById(R.id.notice_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
//        webView.loadData(content,"text/html;charset=utf-8", "utf-8");
        webView.loadDataWithBaseURL(null,content,"text/html", "utf-8",null);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            //页面开始加载时显示进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                PopupWindowUtil.showShareWindow(NoticeMessageDetailsActivity.this);
                ProgressDialogUtil.show(NoticeMessageDetailsActivity.this);
                super.onPageStarted(view, url, favicon);
            }
            //页面结束加载时隐藏进度条
            @Override
            public void onPageFinished(WebView view, String url) {
//                PopupWindowUtil.disMiss(NoticeMessageDetailsActivity.this);
                ProgressDialogUtil.stop(NoticeMessageDetailsActivity.this);
                super.onPageFinished(view, url);
            }
        });
    }

    private void requestIsRead() {
        HashMap<String, Object> data = new HashMap<>();
        String url;
        if(fromMessage){
            url=RequestUtil.REQUEST_03me;//私信
            data.put("letter_id",id);
        }else {
            url= RequestUtil.REQUEST_04me;//公告
            data.put("notice_id",id);
        }
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
            }

            @Override
            public void failed(MessageHead messageHead) {
                showToast(messageHead.getInfo());
            }
        });
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        timeTv=findViewById(R.id.send_time);
        titleTv=findViewById(R.id.title);
        timeTv.setText(sendTime);
        titleTv.setText(title);
        if(fromMessage){
            CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.私信详情));
        }else {
            CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.公告详情));
        }
        StatusBarUtil.setColor(this,Color.WHITE);
        StatusBarUtil.setDarkMode(this);
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
