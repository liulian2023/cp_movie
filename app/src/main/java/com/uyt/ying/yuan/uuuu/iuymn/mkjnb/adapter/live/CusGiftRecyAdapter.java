package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.GiftEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class CusGiftRecyAdapter extends BaseQuickAdapter<GiftEntity.GiftListsBean, BaseViewHolder> {
    int isUesToy;
    List<GiftEntity.GiftListsBean> mDataList;
    int totalPage = 0;

    /**
     * 回调给上层的接口
     */
    public interface OnItemClickListener {
        void onItemClick( int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public CusGiftRecyAdapter(int layoutResId, @Nullable List<GiftEntity.GiftListsBean> data,int isUesToy) {
        super(layoutResId, data);
        mDataList = data;
        this.isUesToy = isUesToy;
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftEntity.GiftListsBean item) {
        RecyclerView childRecy = helper.getView(R.id.recy_cusgift);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        childRecy.setLayoutManager(layoutManager);
        CusGiftChildRecyAdapter childRecyAdapter = new CusGiftChildRecyAdapter(R.layout.item_cusgiftchildrecy, getChildList(helper.getLayoutPosition()),isUesToy);
        childRecy.setAdapter(childRecyAdapter);
        childRecyAdapter.setOnItemClickListener((adapter, view, position)->{
            int Xposition = helper.getLayoutPosition()*8+position;
            /**
             * 回调给上层 数据刷新
             */
            //最后一个item是占位置的,不用回调
            if(Xposition!=mDataList.size()-1){
                mOnItemClickListener.onItemClick(Xposition);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (mDataList.size()%8==0){
            totalPage = mDataList.size()/8;
        }else {
            totalPage = mDataList.size()/8+1;
        }
        return totalPage;
    }



    private List<GiftEntity.GiftListsBean> getChildList(int curPosition){
        List<GiftEntity.GiftListsBean> mChildList;
        if (totalPage>curPosition+1){
            mChildList = mDataList.subList(curPosition*8,(curPosition+1)*8);
        }else if(totalPage==curPosition+1){
            mChildList = mDataList.subList(curPosition*8,mDataList.size());
        }else {
            mChildList = null;
        }
        return mChildList;

    }
}
