package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PaihangAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.PaihangEntity;

import java.util.List;

public class LiveRoomPaihangPop {

    public PopupWindow liveroomPaihangPop;

    RecyclerView mRecy;

    PaihangAdapter adapter;


    public  void showPaihangPop(final Context context, final LinearLayout targetView, final List<PaihangEntity.BonusRecordListBean> list){
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_liveroompaihang, null);
        liveroomPaihangPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        liveroomPaihangPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画

        mRecy = contentView.findViewById(R.id.recy_paihangpop);
      //  mRecy.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(layoutManager);
        adapter = new PaihangAdapter(R.layout.item_paihang,list);
        mRecy.setAdapter(adapter);


        //右侧菜单pop的disMIss监听
        liveroomPaihangPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //        darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });


        if (liveroomPaihangPop != null) {
       //     liveroomPaihangPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            liveroomPaihangPop.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
            //      darkenBackground((Activity) context,0.3f);
        }
    }
}
