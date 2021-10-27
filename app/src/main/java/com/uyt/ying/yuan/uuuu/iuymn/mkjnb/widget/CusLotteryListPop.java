    package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

    import android.app.Activity;
    import android.content.Context;
    import android.graphics.drawable.ColorDrawable;
    import android.text.TextUtils;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.LinearLayout;
    import android.widget.PopupWindow;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProviders;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.uyt.ying.yuan.R;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.CusLotteryListRecyAdapter;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryListItem;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
    import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
    import com.chad.library.adapter.base.BaseQuickAdapter;
    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    /**
     * 自定义彩种列表筛选Pop
     */
    public class CusLotteryListPop extends PopupWindow {

        private Activity mActivity;
        private Fragment mContext;
        private final View view;
        private RecyclerView lotteryRecy;
        private CusLotteryListRecyAdapter mCusLotteryListRecyAdapter;
        private List<LotteryListItem> mDataList = new ArrayList<>();
        private LiveFragmentViewModel mLiveFragmentViewModel;

        public interface OnItemClickListener {
            void onItemClick(long cpId);
        }

        private OnItemClickListener mOnItemClickListener;

        public void setmOnItemClickListener(OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public CusLotteryListPop(Activity mActivity, Fragment mContext) {
            super(mActivity);
            this.mActivity = mActivity;
            this.mContext = mContext;

            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lottery_list_pop_layout, null);
            mLiveFragmentViewModel = ViewModelProviders.of(mContext).get(LiveFragmentViewModel.class);
            initView();
            initPopWindow();
        }
        private void initView() {
            lotteryRecy = view.findViewById(R.id.play_lottery_list_recycle);
            /**
             * 初始化recyclerView
             */
            mCusLotteryListRecyAdapter = new CusLotteryListRecyAdapter(R.layout.pop_lotterylist_recy_item, mDataList);
            lotteryRecy.setLayoutManager(new GridLayoutManager(mActivity, 4));
            lotteryRecy.setHasFixedSize(true);
            lotteryRecy.setAdapter(mCusLotteryListRecyAdapter);
            /**
             * 获取数据刷新recyclerview
             */
    /*        mLiveFragmentViewModel.getLotteryPopData().observe(mContext, (List<LotteryListItem> list) -> {
                mDataList.clear();
                mDataList.addAll(list);
                mCusLotteryListRecyAdapter.notifyDataSetChanged();
            });*/

            selectorHotLottery();
            /**
             * 点击Item监听 回调
             */
            mCusLotteryListRecyAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                    mOnItemClickListener.onItemClick(mDataList.get(position).getCpId());
                    if (position != adapter.getItemCount() - 1) {
    //                        mLiveFragmentViewModel.setLotteryPopDataByPosition(position);
                    }
                }
            });
        }

        private void selectorHotLottery() {
            Context mContext = MyApplication.getInstance();
            List<LotteryListItem> playLotteryList = new ArrayList<>();
            String navigateList = SharePreferencesUtil.getString(mContext, "navigateList", "");
            Gson gson = new GsonBuilder().serializeNulls().create();
            NavigateEntity navigateEntity = gson.fromJson(navigateList, NavigateEntity.class);
            boolean haveLiveCp = false;
            for (int i = 0; i < navigateEntity.getGameClassList().size(); i++) {
                NavigateEntity.GameClassListBean bean = navigateEntity.getGameClassList().get(i);
    //                if (bean.getIsHot() == 1) {
                String finalImgUrl = Utils.CPImageUrlCheck(mContext, bean.getImage());
                String count = bean.getRemark();//每天的期数
                int lotteryGame = bean.getGame();//彩票大类
                String lotteryTypeName = bean.getTypename();//彩票名称
                int lotteryTypeId = bean.getType_id();//彩票typeid
                String isStart = "" + bean.getIsStart();//判断该彩种是否销售 0为停止销售 1为销售
                String isopenOffice = "" + bean.getIsopenOffice();//判断是否有官方玩法 0为没有
                long id = bean.getId();//直播间筛选时需要用到(用于显示 直播中 的状态)
                Integer iscustom = bean.getIscustom();
                LotteryListItem lotteryListItem = new LotteryListItem();

                lotteryListItem.setCpId(id);
                lotteryListItem.setGame(lotteryGame);
                lotteryListItem.setType_id(lotteryTypeId);
                lotteryListItem.setName(lotteryTypeName);
                lotteryListItem.setImgUrl(finalImgUrl);
                lotteryListItem.setLive(false);
     /*                   if (playLotteryList.size() < 7) {
                            if (CPID.getValue() == id) {
                                lotteryListItem.setLive(true);
                                playLotteryList.add(0, lotteryListItem);
                            } else {
                                playLotteryList.add(lotteryListItem);
                            }
                        }*/
                if(mLiveFragmentViewModel.CPID.getValue() == id){
                    //如果筛选时,当前直播的cp在筛选了7次之后才出现,移出最后一个添加的cp,继续筛选,直到筛选出当前直播的cp
                    if(playLotteryList.size()>=7){
                        playLotteryList.remove(playLotteryList.size()-1);
                    }
                    if(playLotteryList.size()!=0){

                    }
                    lotteryListItem.setLive(true);
                    playLotteryList.add(0, lotteryListItem);
                    haveLiveCp=true;
                }else {
                    //只添加热门的cp
                    if(bean.getIsHot()==1){
                        if(playLotteryList.size()<7){
                            playLotteryList.add(lotteryListItem);
                        }
                    }
                }
                //保证当前直播的cp放在position为0的位置,并且只取7个彩种
                if(playLotteryList.size()==7&&haveLiveCp==true){
                    break;
                }
    //                }
            }
            LotteryListItem lotteryListItem = new LotteryListItem();
            lotteryListItem.setCpId(GameTypeEnum.LIVESHOP.getValue());
            lotteryListItem.setGame(GameTypeEnum.LIVESHOP.getValue());
            lotteryListItem.setName(Utils.getString(R.string.全部彩票));
            lotteryListItem.setLive(false);
            playLotteryList.add(lotteryListItem);
            mDataList.clear();
            mDataList.addAll(playLotteryList);
            /**
             * 限定玩法查询修改
             */
            String idsStr = HbGameClassModel.getInstance().getGameIdListStr();
            if (!TextUtils.isEmpty(idsStr)) {
                    List<String> idList = Arrays.asList(idsStr.split(","));
                    for (LotteryListItem item : playLotteryList) {
                        for (String s : idList) {
                            if (s.equals(item.getCpId() + "")) {
                                item.setXian(true);
                                break;
                            }else {
                                item.setXian(false);
                            }
                        }
                    }
                }
            mCusLotteryListRecyAdapter.notifyDataSetChanged();
        }


        private void initPopWindow() {
            this.setContentView(view);
            this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            this.setAnimationStyle(R.style.popupAnimationNormol150);
            ColorDrawable dw = new ColorDrawable(0xE6000000);
            //设置弹出窗体的背景
            this.setBackgroundDrawable(dw);
            //    backgroundAlpha(mActivity, 0.5f);//0.0-1.0

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
