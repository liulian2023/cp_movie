package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class BetRulePopChildAdapter extends BaseQuickAdapter<BetPopAllModel.BetPopChildModel, BaseViewHolder> {


    public BetRulePopChildAdapter(int layoutResId, @Nullable List<BetPopAllModel.BetPopChildModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetPopAllModel.BetPopChildModel item) {
        helper.setText(R.id.fram_bet_content_tv,item.getName())
                .setText(R.id.fram_bet_odd_tv,""+item.getOdds());

        TextView tv = helper.getView(R.id.fram_bet_content_tv);
        if (item.isSelect()){
            tv.setSelected(true);
        }else {
            tv.setSelected(false);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int span = 15;  //默认设置4个
                /**
                 * 取出所有item 个数  3*4*5
                 */
                int itemCount = gridLayoutManager.getItemCount();
                if(itemCount==2){
                    span=30;//龙虎居中
                }else if(itemCount==12){
                    span =10;//生肖需要两行显示完
                } else if (itemCount%3==0){
                    span = 20;
                }else if (itemCount%4==0){
                    span = 15;
                }else if (itemCount%5==0){
                    span = 12;
                }
                return span;
            }
        });
    }
}
