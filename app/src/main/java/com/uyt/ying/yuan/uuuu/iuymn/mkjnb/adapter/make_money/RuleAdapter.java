package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RuleModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import java.util.ArrayList;

public class RuleAdapter extends CommonAdapter<RuleAdapter.MyHolder, RuleModel> {
    public RuleAdapter(ArrayList<RuleModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        RuleModel itemModel = getItemModel(position);
        commonHolder.contentTv.setText(itemModel.getContent());
    }

    @Override
    public int getLayOutRes() {
        return R.layout.rule_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView contentTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.rule_content_tv);
        }
    }
}
