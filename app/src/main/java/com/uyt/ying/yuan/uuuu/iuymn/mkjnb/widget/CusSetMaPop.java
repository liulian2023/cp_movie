package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.SetMaAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class CusSetMaPop extends PopupWindow {

    private Activity mActivity;
    private Fragment mContext;
    private final View view;

    private LiveFragmentViewModel mLiveFragmentViewModel;

    private ImageView iv_setma_close;
    private RecyclerView recy_sema;
    private Button btn_sema_confirm;

    private SetMaAdapter setMaAdapter;
    private List<BetJoinAllModel.BetJoinMaModel> maList = new ArrayList<>();

    public enum CLICKTYPE{
        CLOSE,
        CONFIRM,
        SETCUSMA
    }

    public interface OnItemClickListener {
        void onItemClick(CLICKTYPE clickType);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public CusSetMaPop( Activity mActivity, Fragment mContext) {
        super(mActivity);
        this.mActivity = mActivity;
        this.mContext = mContext;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.setma_pop_layout, null);
        mLiveFragmentViewModel = ViewModelProviders.of(mContext).get(LiveFragmentViewModel.class);
        initView();
        initPopWindow();
    }

    private void initView(){
        iv_setma_close = view.findViewById(R.id.iv_setma_close);
        recy_sema = view.findViewById(R.id.recy_sema);
        btn_sema_confirm = view.findViewById(R.id.btn_sema_confirm);

        /**
         * 初始化recy
         */
        setMaAdapter = new SetMaAdapter(R.layout.item_setma_layout,maList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,5);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy_sema.setLayoutManager(gridLayoutManager);
        recy_sema.setHasFixedSize(true);
       // recy_sema.addItemDecoration(new RecyclerItemDecoration(dp2px(0), dp2px(), 5));
        recy_sema.setAdapter(setMaAdapter);
        setMaAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                /**
                 * 最后一个可自定义筹码  选中再选中 弹出自定义dialog
                 */
                if (position==maList.size()-1&&maList.get(maList.size()-1).isSelect()){
                    mOnItemClickListener.onItemClick(CLICKTYPE.SETCUSMA);
                }else {
                    mLiveFragmentViewModel.selectMaByPosition(position);
                }
            }
        });

        /**
         * 观察筹码数据
         */
        mLiveFragmentViewModel.getBetJoinAllLiveData().observe(mContext,(BetJoinAllModel betJoinAllModel)->{
            if (betJoinAllModel.getBetJoinMaModelList()!=null){
                maList.clear();
                maList.addAll(betJoinAllModel.getBetJoinMaModelList());
                setMaAdapter.notifyDataSetChanged();
            }

        });
        /**
         * 点击确认  修改   isCurrent 数据
         */
        btn_sema_confirm.setOnClickListener(v -> {
            mLiveFragmentViewModel.selectCurrentMa();
            mOnItemClickListener.onItemClick(CLICKTYPE.CONFIRM);
        });
        /**
         * 点击close
         */
        iv_setma_close.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(CLICKTYPE.CLOSE);
        });



    }

    private void initPopWindow(){
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        ColorDrawable dw = new ColorDrawable(0xE6000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0
        this.setOnDismissListener(() ->
                backgroundAlpha(mActivity, 1f)
        );
    }

    /**
     * 设置添加屏幕的背景透明度(值越大,透明度越高)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1f) {
            //恢复屏幕亮度时需要移除flag,否则在切换activity时会有短暂黑屏
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }
}
