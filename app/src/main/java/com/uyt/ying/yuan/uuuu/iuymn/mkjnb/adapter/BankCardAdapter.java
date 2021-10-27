package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class BankCardAdapter extends BaseQuickAdapter<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean, BaseViewHolder> {
    public BankCardAdapter(int layoutResId, @Nullable List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean) {

        baseViewHolder.setText(R.id.bank_name_tv, memberBankInfoVoListBean.getBankName());
        baseViewHolder.setText(R.id.bank_num_tv, memberBankInfoVoListBean.getBankCard());
        ImageView selector_iv = baseViewHolder.getView(R.id.selector_iv);
        ImageView bankcard_big_iv = baseViewHolder.getView(R.id.bankcard_big_iv);
        if (memberBankInfoVoListBean.getIsDefault() == 1) {
            selector_iv.setVisibility(View.VISIBLE);
        } else {
            selector_iv.setVisibility(View.GONE);
        }
        int itemPosition = getItemPosition(memberBankInfoVoListBean);
        if (itemPosition % 6 == 0) {
            bankcard_big_iv.setBackgroundResource(R.drawable.hongka);
        } else if (itemPosition % 6 == 1) {
            bankcard_big_iv.setBackgroundResource(R.drawable.lanka);
        } else if (itemPosition % 6 == 2) {
            bankcard_big_iv.setBackgroundResource(R.drawable.blka);
        } else if (itemPosition % 6 == 3) {
            bankcard_big_iv.setBackgroundResource(R.drawable.lka);
        } else if (itemPosition % 6 == 4) {
            bankcard_big_iv.setBackgroundResource(R.drawable.zka);
        } else {
            bankcard_big_iv.setBackgroundResource(R.drawable.hongka);

        }

    }
}
