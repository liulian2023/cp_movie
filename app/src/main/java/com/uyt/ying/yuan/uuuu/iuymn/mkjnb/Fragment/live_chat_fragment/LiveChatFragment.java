package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop.FollowBetPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.ForbiddenMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RedType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.ZhuangXiangRedActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.JoinMessageAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveChatAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ForbiddenSetManagerPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ForbiddenTimePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import io.rong.imlib.model.Message;
import okhttp3.Headers;

public class LiveChatFragment extends BaseFragment implements BasePopupWindow2.OnPopClickListener {
    public RecyclerView liveChatRecycle;
    public LiveChatAdapter liveChatAdapter;
    public ArrayList<LiveMessageModel> liveMessageModelArrayList = new ArrayList<>();
    private LiveFragment liveFragment;
    public TextView un_read_tv;
    private int unReadCount=0;
    private LiveMessageModel currentLiveMessageModel;
    private ForbiddenSetManagerPop forbiddenSetManagerPop;
    private ForbiddenTimePop forbiddenTimePop;

    public RecyclerView join_chat_room_recycler;
    public JoinMessageAdapter joinMessageAdapter;
    public  ArrayList<LiveMessageModel>joinMessageList = new ArrayList<>();

    public LiveFragment getLiveFragment() {
        return liveFragment;
    }

    public void setLiveFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_chat, container, false);
        bindView(view);
        initChatRecycle();
        initJoinRecycle();
        return view;
    }

    private void initJoinRecycle() {
        joinMessageAdapter = new JoinMessageAdapter(R.layout.live_exit_join_message_item,joinMessageList,this);
        join_chat_room_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        joinMessageAdapter.setAnimationEnable(true);
        joinMessageAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        joinMessageAdapter.setAnimationFirstOnly(false);
        join_chat_room_recycler.setAdapter(joinMessageAdapter);
    }

    private void initChatRecycle() {
        liveChatAdapter=new LiveChatAdapter(this,liveMessageModelArrayList);
        liveChatRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        liveChatRecycle.setAdapter(liveChatAdapter);

        liveChatRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isSlideToBottom(liveChatRecycle)){
                    unReadCount=0;
                    un_read_tv.setVisibility(View.GONE);
                }
            }
        });
        liveChatAdapter.setOnClickSpanLintener(new LiveChatAdapter.OnClickSpanLintener() {
            @Override
            public void onClickSpanLintener(View view, int position) {
                LiveMessageModel liveMessageModel= liveMessageModelArrayList.get(position);
                long redId = liveMessageModel.getRedId();
                String objectName = liveMessageModel.getObjectName();
                if(objectName.equals(CommonStr.RED_MESSAGE)){
                    int redType = liveMessageModel.getRedType();
                    RedType redTypeEnum = null;
                    switch (redType){
                        case 1:
                            redTypeEnum=RedType.QY;
                            break;
                        case 2:
                            redTypeEnum=RedType.ZX;
                            break;
                        case 3:
                            redTypeEnum=RedType.PT;
                            break;
                        default:
                            break;
                    }
                    //点击趣约红包时,需要先判断是否有领取次数,没有次数时直接提示次数已用完
                    if(redTypeEnum==RedType.QY){
/*                        boolean aBoolean = SharePreferencesUtil.getBoolean(MyApplication.getInstance(), CommonStr.HAVE_GET_RED_NUM, true);
                        if(aBoolean){
                            liveFragment.OpenPackPop(redId+"",liveMessageModel.getRedStatus(),liveMessageModel.getQbPrice(),redTypeEnum,liveFragment.roomId,liveMessageModel.getFailInfo());
                        }else {
                            ToastUtils.showToast(Utils.getString(R.string.趣约红包领取次数已用完));
                        }*/
                        InviteAndMakeMoneyActivity.startAty(getContext(),liveFragment.roomId);
                    }else if(redTypeEnum==RedType.ZX) {
                        //专享
                        ZhuangXiangRedActivity.startAty(getContext(),liveFragment.roomId);
                    }else {
                        //普通红包
                        liveFragment.OpenPackPop(redId+"", liveMessageModel.getRedStatus(),liveMessageModel.getQbPrice(),redTypeEnum,liveFragment.roomId,liveMessageModel.getFailInfo());
                    }
                    /*
                    点击跟投
                     */
                }else if(objectName.equals(CommonStr.SHARE_MESSAGE)){
                    new FollowBetPop(getContext(),liveFragment,liveMessageModel);

                }
            }
        });
        liveChatAdapter.setMyOnItemClickLintener(new LiveChatAdapter.MyOnItemClickLintener() {
            @Override
            public void onItemClickLintener(View view, int position) {
                if(liveFragment == null || liveFragment.managerTypeEntity==null){
                    return;
                }
                currentLiveMessageModel = liveMessageModelArrayList.get(position);
                //只有点击普通用户或者房管,且不是自己发送的文本消息  自身为房管 才弹出禁言pop
                boolean isRoomManager = liveFragment.managerTypeEntity.getIsRoomManager().equals("1");
                boolean isSuperManager = liveFragment.managerTypeEntity.getIsSuperRoomManager().equals("1");
                boolean isManager = isRoomManager || isSuperManager;//房管或超管
                boolean isOrdinaryUser = currentLiveMessageModel.getManagerType().equals("0");//当前点击的消息是普通用户发出的
                boolean isClickSuperManager = currentLiveMessageModel.getManagerType().equals("2");//当前消息是超管发出的
                boolean isClickRoomManager = currentLiveMessageModel.getManagerType().equals("3");//当前消息是房管发出的
                boolean isReceive = currentLiveMessageModel.getMessageDirection() == Message.MessageDirection.RECEIVE;//当前消息类型为接收(排除自己发出的消息)
                String objectName = currentLiveMessageModel.getObjectName();
                boolean isTextMessage = objectName.equals(CommonStr.TEXT_MESSAGE);//点击的消息是文本消息
                if(isReceive && isManager&&isTextMessage){//消息类型为接收的普通文本消息 且自身是管理员(拉黑操作移到点击主播头像)
                        if(isSuperManager){//如果自身是超管,则可以禁言普通用户和房管
                            if(isOrdinaryUser||isClickRoomManager){
                                initForbiddenPop(true);
                            }
                        }else {
                            //如果自身是房管, 则只能禁言普通用户
                            if(isOrdinaryUser){
                                initForbiddenPop(false);
                            }
                        }
                }
            }
        });
    }
    /**
     * 禁言pop
     * @param
     */
    private void initForbiddenPop(boolean isSuperManager) {
        forbiddenSetManagerPop = new ForbiddenSetManagerPop(getContext(),currentLiveMessageModel,isSuperManager);
        forbiddenSetManagerPop.setOnPopClickListener(this);
        forbiddenSetManagerPop.showAtLocation(liveChatRecycle, Gravity.BOTTOM,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }
    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            case R.id.forbidden_tv:
                //禁言
                if(forbiddenSetManagerPop!=null){
                    forbiddenSetManagerPop.dismiss();
                }
                initForbiddenTimePop();
                break;
            case R.id.block_tv:
                // 拉黑
                requestBlockUser();
                break;
            case R.id.one_hour_tv:
                requestForbidden(1);
                break;
            case R.id.six_hour_tv:
                requestForbidden(2);
                break;
            case R.id.twelve_tv:
                requestForbidden(3);
                break;
            case R.id.one_day_tv:
                requestForbidden(4);
                break;
            case R.id.one_week_tv:
                requestForbidden(5);
                break;
            case R.id.one_month_tv:
                requestForbidden(6);
                break;
                default:
                    break;
        }
    }

    private void requestBlockUser() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("killedUserId",currentLiveMessageModel.getUserInfoJsonUserId());
        data.put("maintainStatus","1");
        if(liveFragment!=null){
            data.put("liveRoomId",liveFragment.roomId);
        }
        HttpApiUtils.CpRequest(getActivity(), LiveChatFragment.this, RequestUtil.BLOCK_USER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
               showToast(Utils.getString(R.string.封号成功));
                if(forbiddenSetManagerPop!=null){
                    forbiddenSetManagerPop.dismiss();
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void requestForbidden(int minute) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorAccount",liveFragment.getmLiveData().getAnchorAccount());
        data.put("bannedUserId",currentLiveMessageModel.getUserInfoJsonUserId());
        data.put("minute",minute);
        data.put("gagType",minute);
        if(currentLiveMessageModel!=null){
            data.put("userSpeakContent",currentLiveMessageModel.getTextContent());
            data.put("remark",currentLiveMessageModel.getTextContent());
        }

        HttpApiUtils/**/.CpRequest(getActivity(),LiveChatFragment.this, RequestUtil.FORBIDDEN_USER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                forbiddenTimePop.dismiss();
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message = jsonObject.getString("message");
                showToast(message);
                ForbiddenMessage forbiddenMessage = new ForbiddenMessage("1", currentLiveMessageModel.getUserName(),    SharePreferencesUtil.getString(getContext(), "zkCode", "") );
                forbiddenMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext(),liveFragment.managerTypeEntity));
                RongLibUtils.sendMessage(liveFragment.roomId,forbiddenMessage);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
    /**
     * 禁言时间选择pop
     */
    private void initForbiddenTimePop() {
        forbiddenTimePop = new ForbiddenTimePop(getContext());
        forbiddenTimePop.setOnPopClickListener(LiveChatFragment.this);
        forbiddenTimePop.showAtLocation(liveChatRecycle, Gravity.BOTTOM,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }

    public void addItem(LiveMessageModel liveMessageModel){
        //自己发的消息,直接滚动到底部
        String userName = liveMessageModel.getUserName();
        if(StringMyUtil.isNotEmpty(userName)&&userName.equals(SharePreferencesUtil.getString(MyApplication.getInstance(),"userNickName",""))){
            liveMessageModelArrayList.add(liveMessageModel);
            liveChatAdapter.notifyItemInserted(liveMessageModelArrayList.size()-1);
            liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);
        }else {
            //收到其他用户发的消息  判断列表当前时候在底部
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) liveChatRecycle.getLayoutManager();
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            int itemCount = linearLayoutManager.getItemCount();
            //当前在底部时, 添加消息后再滚动到底部(并清空未读消息数)
//            if(lastVisibleItemPosition==liveMessageModelArrayList.size()-1){
            if(itemCount-1-lastVisibleItemPosition<=3){
                //滚动到底部
    /*            unReadCount=0;
                un_read_tv.setVisibility(View.GONE);*/
                liveMessageModelArrayList.add(liveMessageModel);
                liveChatAdapter.notifyItemInserted(liveMessageModelArrayList.size()-1);
                liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);

            }else {
                //当前列表不在底部时, 显示未读消息
                unReadCount++;
                liveMessageModelArrayList.add(liveMessageModel);
                un_read_tv.setVisibility(View.VISIBLE);
                un_read_tv.setText(Utils.getString(R.string.未读消息:)+unReadCount);
                liveChatAdapter.notifyDataSetChanged();
            }
        }

//        liveChatAdapter.notifyDataSetChanged();

    }

    public void addJoinItem(LiveMessageModel liveMessageModel){
        if(joinMessageList.size()==0){
            LiveMessageModel liveMessageModelTest = new LiveMessageModel();
            liveMessageModelTest.setLevel("2");
            liveMessageModelTest.setUserName("test");
            liveMessageModelTest.setPortrait("0");
            liveFragment.assetsSvgaManage.startAnimator(liveMessageModelTest);
        }
        joinMessageList.add(liveMessageModel);
        joinMessageAdapter.notifyItemInserted(joinMessageList.size()-1);
        join_chat_room_recycler.scrollToPosition(joinMessageList.size()-1);
//        joinMessageAdapter.notifyDataSetChanged();
    }
    private void bindView(View view) {
        join_chat_room_recycler=view.findViewById(R.id.join_chat_room_recycler);
        liveChatRecycle=view.findViewById(R.id.live_chat_recycle);
        un_read_tv=view.findViewById(R.id.un_read_tv);
        un_read_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击未读消息时滚动到底部 并清空未读消息数
                liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);
        /*        unReadCount=0;
                un_read_tv.setVisibility(View.GONE);*/
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }


}
