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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.AboutUsRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AboutUsMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/*
关于我们 activity
 */
public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private AboutUsRecycleAdapter aboutUsRecycleAdapter;
    private ArrayList<AboutUsMedol> aboutUsModelList=new ArrayList<>();
    private TextView webTitleTv;
    private PopupWindow popupWindow;
    private WebView webView;
    private ConstraintLayout loadingLinear;
    private TextView toolbar_title_tv;
    private ImageView toolbar_back_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.关于我们));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
        bindView();
        initRecycle();
        getAboutList();
        initPopwindow();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        loadingLinear=findViewById(R.id.loading_linear);
        recyclerView=findViewById(R.id.about_us_recycle);
    }
    /*
     请求列表数据
    */
    private void getAboutList() {
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("contentType","1");//contentType  0 帮助指南列表   1 关于我们列表
        Utils.docking(data, RequestUtil.REQUEST_hp01, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
//                ProgressDialogUtil.stop(AboutUsActivity.this);
                loadingLinear.setVisibility(View.GONE);
                aboutUsModelList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray promptHelp = jsonObject1.getJSONArray("PromptHelp");
                for (int i=0;i<promptHelp.size();i++) {
                    JSONObject jsonObject = promptHelp.getJSONObject(i);
                    String theme = jsonObject.getString("theme");
                    String id = jsonObject.getString("id");
                    String content1 = jsonObject.getString("content");
                    aboutUsModelList.add(new AboutUsMedol(theme,id,content1));
                }
                aboutUsRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//                ProgressDialogUtil.stop(AboutUsActivity.this);
            }
        });
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        aboutUsRecycleAdapter = new AboutUsRecycleAdapter(aboutUsModelList);
        recyclerView.setAdapter(aboutUsRecycleAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        aboutUsRecycleAdapter.setOnItemClickListener(new AboutUsRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            /*
            recycleView点击事件(点击弹出帮助详情popupwindow)
             */
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        toolbar_title_tv.setText(Utils.getString(R.string.隐私声明));
                        webTitleTv.setText(Utils.getString(R.string.隐私声明));
                        break;
                    case 1:
                        toolbar_title_tv.setText(Utils.getString(R.string.法律说明));
                        webTitleTv.setText(Utils.getString(R.string.法律说明));
                        break;
                    case 2:
                        toolbar_title_tv.setText(Utils.getString(R.string.商务合作));
                        webTitleTv.setText(Utils.getString(R.string.商务合作));
                        break;
                    case 3:
                        toolbar_title_tv.setText(Utils.getString(R.string.联系我们));
                        webTitleTv.setText(Utils.getString(R.string.联系我们));
                        break;
                    case 4:
                        toolbar_title_tv.setText(Utils.getString(R.string.关于我们));
                        webTitleTv.setText(Utils.getString(R.string.关于我们));
                        break;
                }
                AboutUsMedol aboutUsMedol = aboutUsModelList.get(position);
                webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+aboutUsMedol.getContent(),"text/html", "utf-8",null);
                popupWindow.showAtLocation(view , Gravity.CENTER, 0, 0);
                String s = webTitleTv.getText().toString();
            }
        });

    }
    private void initPopwindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(AboutUsActivity.this);
        View inflate = layoutInflater.inflate(R.layout.about_us_popupwindow, null);
        toolbar_back_iv=inflate.findViewById(R.id.toolbar_back_iv);
        toolbar_title_tv=inflate.findViewById(R.id.toolbar_title_tv);
        webTitleTv=inflate.findViewById(R.id.title);
                    /*
                popwindows相关配置
                 */
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindow.setTouchable(true);//响应内部点击
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
                /*
                webView相关配置
                 */
        webView =inflate.findViewById(R.id.about_us_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
//                webView.loadUrl(frontUrl+"home/getReward?user_id="+user_id+"&deviceCode="+deviceCode+"&token="+token+"&type="+"13"+"&theme="+aboutUsModelList.get(position).getId());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            //页面开始加载时显示进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        PopupWindowUtil.showShareWindow(AboutUsActivity.this);
                ProgressDialogUtil.show(AboutUsActivity.this);
                super.onPageStarted(view, url, favicon);
            }
            //页面结束加载时隐藏进度条
            @Override
            public void onPageFinished(WebView view, String url) {
//                        PopupWindowUtil.disMiss(AboutUsActivity.this);
                ProgressDialogUtil.stop(AboutUsActivity.this);
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
