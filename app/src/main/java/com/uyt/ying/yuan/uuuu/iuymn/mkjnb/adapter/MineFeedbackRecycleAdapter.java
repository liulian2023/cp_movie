package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineFeedbackModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MineFeedbackRecycleAdapter extends CommonAdapter<MineFeedbackRecycleAdapter.MyHolder, MineFeedbackModel> {
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        MineFeedbackModel itemModel = getItemModel(position);
        String time = itemModel.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(Long.parseLong(time)));
        commonHolder.timeTv.setText(Utils.getString(R.string.反馈时间:)+ format);
        commonHolder.typeTv.setText(Utils.getString(R.string.反馈类型:)+itemModel.getType());
        commonHolder.contentTv.setText(Utils.getString(R.string.反馈内容:)+itemModel.getContent());
        String reply = itemModel.getReply();
        if(StringMyUtil.isEmptyString(reply)){
            commonHolder.replyTv.setText(Utils.getString(R.string.官方回复:暂无回复));
        }else {
            commonHolder.replyTv.setText(Utils.getString(R.string.官方回复:)+reply);

        }
    }

    public MineFeedbackRecycleAdapter(ArrayList<MineFeedbackModel> list) {
        super(list);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.mine_feedback_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView timeTv;
        private TextView typeTv;
        private TextView contentTv;
        private TextView replyTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            timeTv=itemView.findViewById(R.id.feedback_time_tv);
            typeTv=itemView.findViewById(R.id.feedback_type);
            contentTv=itemView.findViewById(R.id.feedback_content);
            replyTv=itemView.findViewById(R.id.gf_Reply_tv);
        }
    }
}
