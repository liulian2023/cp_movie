package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.HelpGuideRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HelpGuideMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
/*
帮助中心 activity
 */
public class HelpGuideActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private HelpGuideRecycleAdapter helpGuideRecycleAdapter;
    private ArrayList<HelpGuideMedol> helpGuideMedolArrayList=new ArrayList<>();
    private PopupWindow popupWindow;
    private WebView webView;
    private TextView webTitleTv;
    private ConstraintLayout loadingLinear;
    private TextView toolbar_title_tv;
    private ImageView toolbar_back_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_guide);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.帮助指南));
        StatusBarUtil.setColor(this,Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
        bindView();
        initRecycle();
        getHelpList();
        intiPopwindow();
    }

    @Override
    protected void init() {

    }

    /*
    帮助列表
     */
    private void getHelpList() {
//        ProgressDialogUtil.show(HelpGuideActivity.this);
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("contentType","0");
        Utils.docking(data, RequestUtil.REQUEST_hp01, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
//                ProgressDialogUtil.stop(HelpGuideActivity.this);
                loadingLinear.setVisibility(View.GONE);
                helpGuideMedolArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray promptHelp = jsonObject1.getJSONArray("PromptHelp");
                for (int i=0;i<promptHelp.size();i++) {
                    JSONObject jsonObject = promptHelp.getJSONObject(i);
                    String theme = jsonObject.getString("theme");
                    String id = jsonObject.getString("id");
                    String content1 = jsonObject.getString("content");
                    helpGuideMedolArrayList.add(new HelpGuideMedol(theme,id,content1));
                }
                helpGuideRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(HelpGuideActivity.this);
                loadingLinear.setVisibility(View.GONE);
                showToast(messageHead.getInfo());
            }
        });
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        helpGuideRecycleAdapter = new HelpGuideRecycleAdapter(helpGuideMedolArrayList);
        recyclerView.setAdapter(helpGuideRecycleAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        helpGuideRecycleAdapter.setOnItemClickListener(new HelpGuideRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            /*
            recycleView点击事件(点击弹出帮助详情popupwindow)
             */
            public void onItemClick(View view, int position) {
                HelpGuideMedol helpGuideMedol = helpGuideMedolArrayList.get(position);
                webTitleTv.setText(helpGuideMedol.getTheme());
                toolbar_title_tv.setText(helpGuideMedol.getTheme());//popwindow的actionbar标题
                webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+helpGuideMedol.getContent(),"text/html", "utf-8",null);
                popupWindow.showAtLocation(view , Gravity.CENTER, 0, 0);
            }
        });

    }

    private void bindView() {
        loadingLinear=findViewById(R.id.loading_linear);
        recyclerView=findViewById(R.id.help_recycle);
    }
    private void intiPopwindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(HelpGuideActivity.this);
        View inflate = layoutInflater.inflate(R.layout.help_guide_popupwindow, null);
        webTitleTv=inflate.findViewById(R.id.title);
        toolbar_back_iv=inflate.findViewById(R.id.toolbar_back_iv);
        toolbar_title_tv=inflate.findViewById(R.id.toolbar_title_tv);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.style.popupAnimationNormol2);//设置进出动画
        popupWindow.setTouchable(true);//响应内部点击
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        webView=inflate.findViewById(R.id.help_webvView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            //页面开始加载时显示进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                ProgressDialogUtil.show(HelpGuideActivity.this);
                super.onPageStarted(view, url, favicon);
            }
            //页面结束加载时隐藏进度条
            @Override
            public void onPageFinished(WebView view, String url) {
                ProgressDialogUtil.stop(HelpGuideActivity.this);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
