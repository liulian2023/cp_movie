package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveExitAndJoinMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ResetVisibleEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.TurntablePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.VerticalViewPager;
import com.my.xunboplayerlib.Jzvd;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.rong.imlib.RongIMClient;
import okhttp3.Headers;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const.ANCHOR_ROOM_BEAN;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveViewModel.STATE.NORMAL;

public class LiveActivity extends MvpBaseActivity {

    @BindView(R.id.verticalviewpager)
    public  VerticalViewPager mViewPager;
    @BindView(R.id.live_root_relativeLayout)
    public RelativeLayout live_root_relativeLayout;
    private MyPagerAdapter pagerAdapter;
    private FragmentManager supportFragmentManager;
//  public ArrayList<Fragment> fragments = new ArrayList<>();
    //viewPager所有加载的数据(包括重复的)
    public ArrayList<LiveEntity.AnchorMemberRoomListBean> viewpagerDataList = new ArrayList<>();
    //viewPager去重后的数据(因为add的时候不能判断是否包含, 否则达不到循环滑动的效果, 付费房间点击dismiss时,通过此list的size判断是否退出)
    public ArrayList<LiveEntity.AnchorMemberRoomListBean> viewpagerDataList2 = new ArrayList<>();

    /**
     * LiveHomeFragment 传递过来的 当前直播间数据
     */
    private LiveEntity.AnchorMemberRoomListBean mLiveRoomData;
    private String categoryId;
    private String area;

    private LiveViewModel mViewModel;
    public int pageNo = 1;
    int pageSize = 16;
    /**
     * 当前直播间位置
     */
    public int curPosition = 0;
    /**
     * 直播间 index 标志位   通过index++来标志从哪里+ fragments
     */
    private int mIndex = 0;

    private long enterTime;

    private int watchTimes=1;
    Handler handler = new Handler();

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_live;
    }

    @Override
    public void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        enterTime = System.currentTimeMillis();
        initPerson();
        Utils.RequestUsingEquipment(this,null);
//        StatusBarUtil.setTransparentForWindow(this);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //设置状态栏字体颜色
        StatusBarUtil.setLightMode(this,true);

        SharePreferencesUtil.remove(MyApplication.getInstance(),"joinChatroomId");
        mViewModel = ViewModelProviders.of(this).get(LiveViewModel.class);
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        area = intent.getStringExtra("area");
        pageNo = intent.getIntExtra("pageNo",1);
        if(categoryId.equals("-5")){
            pageSize = 10;
        }else {
            pageSize = 16;
        }
        Bundle bundle = intent.getExtras();
        mLiveRoomData = (LiveEntity.AnchorMemberRoomListBean) bundle.getSerializable(ANCHOR_ROOM_BEAN);

        /**
         * 添加上级页面传递过来的第一条数据
         */
        mViewModel.addAnchorRoomData(mLiveRoomData);
        /**
         * 初始化verticalviewpager
         */
        //    mViewPager.setPageTransformer(false, new DefaultTransformer());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        supportFragmentManager  = getSupportFragmentManager();
        pagerAdapter = new MyPagerAdapter( supportFragmentManager, viewpagerDataList);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                /**
                 * 判断当前是否滑动到最后一页 是 pageNo++ 请求下一页数据
                 */
                curPosition = position;
                watchTimes= watchTimes < curPosition+1?curPosition+1:watchTimes;
                Utils.logE(TAG, "onPageSelected: watchTimes ="+watchTimes );
                if (position== viewpagerDataList.size()-1){
                    pageNo++;
                    mViewModel.reqLiveData(pageNo,pageSize,categoryId,area);
                }
/*                for (int i = 0; i < fragments.size(); i++) {
                    LiveFragment liveFragment= (LiveFragment) fragments.get(i);
                    liveFragment.isSkip=false;
                    liveFragment.visibleCount=0;
                }*/
                EventBus.getDefault().post(new ResetVisibleEvenModel());
                LiveFragment liveFragment = LiveActivity.this.findFragment(LiveFragment.class);
                if(liveFragment!=null){
                    liveFragment.isSkip=false;
                    liveFragment.visibleCount=0;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /**
         * 初始化页面状态
         */
        mViewModel.getPageState().setValue(NORMAL);

        /**
         * 进入直播间后每10分钟提交一次观看时长
         */
        handler.postDelayed(watchTimeRunnable,1000*60*10);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
/*            if(fragments.size()!=0){
                Fragment fragment = fragments.get(curPosition);
                if(fragment!=null&&!fragment.isDetached()&&fragment instanceof LiveFragment){
                    LiveFragment liveFragment = (LiveFragment) fragment;
                    TurntablePop turntablePop = liveFragment.turntablePop;
                    if(turntablePop!=null&&turntablePop.isShowing()){
                        return true;
                    }
                }
            }*/
            LiveFragment liveFragment = LiveActivity.this.findFragment(LiveFragment.class);
                if(liveFragment!=null){
                    TurntablePop turntablePop = liveFragment.turntablePop;
                    if(turntablePop!=null&&turntablePop.isShowing()){
                        return true;
                    }
                }


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initEventAndData() {
        /**
         * 懒加载 请求直播间数据
         */
        mViewModel.reqLiveData(pageNo,pageSize,categoryId,area,mLiveRoomData);

        /**
         * 观察页面状态
         */
        mViewModel.getPageState().observe(this,(LiveViewModel.STATE state)->{
            switch (state){
                case NORMAL:
                    break;
                case REFRESH:
                    break;
                case CLOSE:
                    Jzvd.releaseAllVideos();
                    // TODO
                    onBackPressedSupport();
                    break;
                default:
                    break;
            }
        });
        /**
         * 观察liveData 列表数据
         */
        mViewModel.getLiveData().observe(this,(List<LiveEntity.AnchorMemberRoomListBean> list)->{
            /**
             * 从当前房间位置开始给fragments add数据
             */
            for (int i =mIndex;i<list.size();i++){
//                fragments.add(LiveFragment.newInstant(i));
                LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = list.get(i);
                    viewpagerDataList.add(anchorMemberRoomListBean);
                    mIndex++;
                //添加去重的数据,付费房间点击dismiss判断使用
                if(!viewpagerDataList2.contains(anchorMemberRoomListBean)){
                    viewpagerDataList2.add(anchorMemberRoomListBean);
                }
            }
            pagerAdapter.notifyDataSetChanged();
        });

        mViewModel.getPageNum().observe(this,pageNo -> {
            this.pageNo = pageNo;
        });

    }
    private void requestWatchCount() {
        long minutes = (System.currentTimeMillis() - enterTime) / 1000 / 60;
        if(minutes>1){
            HashMap<String, Object> data = new HashMap<>();
            data.put("installApps",Utils. getInstallApps(LiveActivity.this));
            data.put("page",5);
            data.put("times",watchTimes);
            data.put("minutes",minutes);
            data.put("installApps", Utils.getInstallApps(LiveActivity.this));
            Utils.logE(TAG, "requestWatchCount: watchTimes="+watchTimes );
            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, "onSuccess: 观看直播次数+时长统计成功 次数"+watchTimes);
//                requestWatchMinutes();
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "onFailed: 观看直播次数+时长统计失败"+msg );
                }
            });
        }


    }

    private void requestWatchMinutes() {
        long minutes = (System.currentTimeMillis() - enterTime) / 1000 / 60;
        if(minutes>=1){
            HashMap<String, Object> data = new HashMap<>();
            data.put("page",5);
            data.put("minutes", minutes);
            data.put("times",0);
            data.put("installApps", Utils.getInstallApps(LiveActivity.this));
            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, "观看时长统计成功 分钟数:"+ data.get("minutes"));
                    enterTime= System.currentTimeMillis();
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "观看时长统计失败"+msg);
                }
            });
        }
    }
    private void initPerson() {
        HashMap<String, Object> aboutPerson = new HashMap<>();
        long userId =SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0L);
        aboutPerson.put("user_id",userId);
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.REQUEST_13r, aboutPerson, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                SharePreferencesUtil.putInt(MyApplication.getInstance(), CommonStr.GRADE,memberInfo.getInteger(CommonStr.GRADE)+1);
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }
    Runnable watchTimeRunnable = new Runnable() {
        @Override
        public void run() {
            requestWatchMinutes();
            handler.postDelayed(this,1000*60*10);
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("LiveActivity onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        enterTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("LiveActivity onStop");
/*        requestWatchMinutes();
        requestWatchCount();*/
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        requestWatchCount();
        LogUtils.e("LiveActivity onDestroy");
//        Jzvd.releaseAllVideos();
        String joinChatroomId = SharePreferencesUtil.getString(MyApplication.getInstance(), "joinChatroomId", "");
        LiveExitAndJoinMessage liveExitAndJoinMessage = new LiveExitAndJoinMessage(SharePreferencesUtil.getString(this, "userNickName", ""), "1");
        liveExitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(this,null));
        if (StringMyUtil.isNotEmpty(joinChatroomId)) {
//            RongLibUtils.sendMessage(joinChatroomId, liveExitAndJoinMessage);//退出直播间不发消息

            RongLibUtils.quitChatRoom(joinChatroomId, new RongIMClient.OperationCallback() {
                @Override
                public void onSuccess() {
                    Utils.logE(TAG, "rongLog onSuccess:   " + joinChatroomId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Utils.logE(TAG, "rongLog  onError:  退出聊天室失败 + roomId: " + joinChatroomId);
                }
            });
        }
/*        Fragment fragment = fragments.get(curPosition);
        if(fragment!=null&& fragment instanceof LiveFragment){
            LiveFragment liveFragment = (LiveFragment) fragment;
            EditPanel editPanel = liveFragment.editPanel;
            if(editPanel!=null){
                HeightProvider heightProvider = editPanel.getHeightProvider();
                if(heightProvider!=null&&heightProvider.isShowing()){
                    heightProvider.dismiss();
                    heightProvider = null;
                }
            }
        }*/
        handler.removeCallbacksAndMessages(null);
        handler = null;


    }



    static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<LiveEntity.AnchorMemberRoomListBean> viewpagerDataList;

        public MyPagerAdapter(FragmentManager fm, ArrayList<LiveEntity.AnchorMemberRoomListBean> viewpagerDataList ){
            super(fm);
            this.viewpagerDataList = viewpagerDataList;
        }
/*        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments ){
            super(fm);
            this.fragments = fragments;
        }*/
        @Override
        public Fragment getItem(int i) {
            return   LiveFragment.newInstant(i);
//            return   fragments.get(i);
        }

        @Override
        public int getCount() {
            return viewpagerDataList!=null ? viewpagerDataList.size() : 0;
        }
    }

}
