package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinContentModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class BetJoinContentOldAdapter extends BaseQuickAdapter<BetJoinContentModel, BaseViewHolder> {



    public BetJoinContentOldAdapter(int layoutResId, @Nullable List<BetJoinContentModel> data) {
        super(layoutResId, data);
    }

    private EtChangedListener mEtChangedListener;

    public void setEtChangedListener(EtChangedListener mEtChangedListener) {
        this.mEtChangedListener = mEtChangedListener;
    }

    //7、定义et监听回调接口
    public interface EtChangedListener {
        void onItemChanged(View view, int position, String s);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetJoinContentModel item) {
        helper.setIsRecyclable(false);
        helper.setText(R.id.tv_item_betjoinold_groud,item.getType())
                .setText(R.id.tv_item_betjoinold_name,item.getName());

        EditText editText = helper.getView(R.id.ed_item_betjoinold_money);
        TextView tv_money = helper.getView(R.id.tv_item_betjoinold_money);
        TextView multiple_tv = helper.getView(R.id.multiple_tv);

        tv_money.setText(item.getDanjia()*Integer.parseInt(item.getMultiple())+"");
        editText.setText(item.getDanjia()+"");
        editText.setSelection(editText.getText().toString().length());
        multiple_tv.setText("x"+ item.getMultiple());

        if (editText.getTag() instanceof TextWatcher) {
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String changeStr = StringMyUtil.isEmptyString(s.toString()) ? "0" : s.toString();
                item.setDanjia(Integer.parseInt(changeStr));
                tv_money.setText(Integer.parseInt(changeStr)*Integer.parseInt(item.getMultiple())+"");
                mEtChangedListener.onItemChanged(editText, helper.getLayoutPosition(), s.toString());
            }
        };

        editText.addTextChangedListener(textWatcher);
        editText.setTag(textWatcher);

    }
}
