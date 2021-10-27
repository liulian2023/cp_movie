package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeAtyEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeAtyRecyclerAdapter extends BaseQuickAdapter<HomeAtyEntity.ExtensionNoticeInfoListBean, BaseViewHolder> {
    public HomeAtyRecyclerAdapter(int layoutResId, @Nullable List<HomeAtyEntity.ExtensionNoticeInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder,HomeAtyEntity.ExtensionNoticeInfoListBean extensionNoticeInfoListBean) {
      WebView webView =  baseViewHolder.getView(R.id.home_aty_webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //禁用横向滚动条
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDefaultTextEncodingName("UTF -8");
        webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+ extensionNoticeInfoListBean.getContent(),"text/html", "utf-8",null);

    }
}
