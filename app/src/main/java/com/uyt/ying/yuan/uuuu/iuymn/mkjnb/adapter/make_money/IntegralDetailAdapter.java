package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IntegralModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;

import java.util.ArrayList;

public class IntegralDetailAdapter extends CommonAdapter<IntegralDetailAdapter.MyHolder, IntegralModel.DataBean> {

    public IntegralDetailAdapter(ArrayList<IntegralModel.DataBean> list ) {
        super(list);

    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        IntegralModel.DataBean itemModel = getItemModel(position);
        commonHolder.time_tv.setText(DateUtil.stampToDate(itemModel.getCreatedDate()+""));
        int integral = itemModel.getIntegral();
        commonHolder.integral_tv.setText(integral>0?("+"+integral):integral+"");
        int integralType = itemModel.getIntegralType();
        if(integralType==1){//有返回用户名, 用户名高亮处理
            String inviteName = itemModel.getInviteName();
            SpannableString spannableString = new SpannableString(Utils.getString(R.string.邀请好友) + inviteName + Utils.getString(R.string.成功注册));
            ForegroundColorSpan getRedSpan = new ForegroundColorSpan(Color.parseColor("#F68607"));
            spannableString.setSpan(getRedSpan,4,4+inviteName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            commonHolder.remark_tv.setText(spannableString);
        }else {
              commonHolder.remark_tv.setText(itemModel.getRemark());
        }
    }

    @Override
    public int getLayOutRes() {
        return R.layout.integral_details_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView remark_tv;
        TextView time_tv;
        TextView integral_tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            remark_tv=itemView.findViewById(R.id.remark_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            integral_tv=itemView.findViewById(R.id.integral_tv);
        }
    }
}
