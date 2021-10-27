//package com.cambodia.zhanbang.xunbo.widget;
//
//import android.content.Context;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.cambodia.zhanbang.xunbo.R;
//import com.cambodia.zhanbang.xunbo.activity.live.LiveRoomMenuAdapter;
//import com.cambodia.zhanbang.xunbo.net.entity.LiveRoomEntity;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//
//import java.util.List;
//
//public class LiveRoomMenuPop {
//
//    public PopupWindow liveroommenuPop;
//
//    RecyclerView mRecy;
//
//    LiveRoomMenuAdapter adapter;
//
//    public  interface MyItemClickListener {
//        void MyItemClick(View view,int position);
//    }
//    private MyItemClickListener mMyItemClickListener = null;
//    public void setMyItemClickListener(MyItemClickListener listener){
//        this.mMyItemClickListener = listener;
//    }
//
//    public  void showMenuPop(final Context context, final LinearLayout targetView, final List<LiveRoomEntity.DataBean.ClassificationsBean> list){
//        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_liveroommenu, null);
//        liveroommenuPop = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
//        liveroommenuPop.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
//
//        mRecy = contentView.findViewById(R.id.recy_liveroommenu);
//        mRecy.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecy.setLayoutManager(layoutManager);
//        adapter = new LiveRoomMenuAdapter(R.layout.item_recyliveroommenu,list);
//        mRecy.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                mMyItemClickListener.MyItemClick(view,position);
//                liveroommenuPop.dismiss();
//            }
//        });
//
//        //右侧菜单pop的disMIss监听
//        liveroommenuPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                //        darkenBackground((Activity) context,1f);//恢复背景亮度
//            }
//        });
//
//
//        if (liveroommenuPop != null) {
//            liveroommenuPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
//        //    liveroommenuPop.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
//            //      darkenBackground((Activity) context,0.3f);
//        }
//    }
//}
