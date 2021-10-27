package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;

public class ForbiddenSetManagerPop extends BasePopupWindow2 {
    TextView forbidden_tv;
    TextView forbidden_cancel_tv;
    TextView block_tv;
    public LiveMessageModel liveMessageModel;
    boolean isSuperManager;

    /**
     *
     * @param context
     * @param liveMessageModel
     * @param
     */
    public ForbiddenSetManagerPop(Context context, LiveMessageModel liveMessageModel,boolean isSuperManager ) {
        super(context,false);
        this.liveMessageModel = liveMessageModel;
        this.isSuperManager = isSuperManager;
        setAnimationStyle(R.style.pop_bottom_to_top_150);
        if(isSuperManager){
            block_tv.setVisibility(View.VISIBLE);
        }else {
            block_tv.setVisibility(View.GONE);
        }
    }




    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.forbidden_set_manager_pop, null);
        forbidden_tv =rootView.findViewById(R.id.forbidden_tv);
        block_tv =rootView.findViewById(R.id.block_tv);
        forbidden_cancel_tv =rootView.findViewById(R.id.forbidden_cancel_tv);
        forbidden_tv.setOnClickListener(this);
        block_tv.setOnClickListener(this);
        forbidden_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForbiddenSetManagerPop.this.dismiss();
            }
        });
    }

}
