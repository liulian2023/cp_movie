package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BaseUrlEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.List;

public class BaseUrlAdapter extends BaseQuickAdapter<BaseUrlEntity.AppRequestDomainsBean, BaseViewHolder> {
    public BaseUrlAdapter(int layoutResId, @Nullable List<BaseUrlEntity.AppRequestDomainsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseUrlEntity.AppRequestDomainsBean item) {
        helper.setText(R.id.line_num_tv,Utils.getString(R.string.线路)+(helper.getAdapterPosition()+1));
        helper.setText(R.id.url_tv,item.getDomain());

        ImageView selector_iv= helper.getView(R.id.selector_iv);
        if(item.isCheck()){
            selector_iv.setVisibility(View.VISIBLE);
        }else {
            selector_iv.setVisibility(View.GONE);
        }

        TextView speed_tv =helper.getView(R.id.speed_tv);
        boolean success = item.isSuccess();
        if(success){
            long endTime = item.getEndTime();
            if(endTime == 0){
                return;
            }
            long startTime = item.getStartTime();
            long differenceTime = endTime - startTime;
            if(differenceTime>=500){
                speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
            }else {
                speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
            }
            speed_tv.setText(Utils.getString(R.string.延迟)+differenceTime+"ms");
        }else {
            speed_tv.setText(Utils.getString(R.string.网络状况不佳));
            speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
        }



    }

}
