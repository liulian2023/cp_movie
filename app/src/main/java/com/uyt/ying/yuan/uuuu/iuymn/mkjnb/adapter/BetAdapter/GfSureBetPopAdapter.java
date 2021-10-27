

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import androidx.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GfSureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class
GfSureBetPopAdapter extends CommonAdapter<GfSureBetPopAdapter.MyHolder, GfSureBetPopMeldol> {
    public GfSureBetPopAdapter() {

    }

    public GfSureBetPopAdapter(ArrayList<GfSureBetPopMeldol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        GfSureBetPopMeldol itemModel = getItemModel(position);
//        commonHolder.groupNameTv.setText(itemModel.getGroupName());
        commonHolder.groupNameTv.setText(Html.fromHtml(itemModel.getName()));
    }

    @Override
    public int getLayOutRes() {
        return R.layout.sure_bet_pop_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView groupNameTv;
//        private TextView betTypeTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTv=itemView.findViewById(R.id.group_name);
//            betTypeTv=itemView.findViewById(R.id.bet_type);
        }
    }
}
