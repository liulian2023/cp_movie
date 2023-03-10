package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveRewardMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.OpenRedActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.ZSQAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveGiftSVGAEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ZSQEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.liveActivityEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.AssetsSvgaManage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.GiftSvgaManage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.JoinSvgaMage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop.AutoFollowPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop.CountdownPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.EditPanel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop.FollowPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.LiveChatFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.GiftSendModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift.GiftView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.giftRain.BoxInfo;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.giftRain.BoxPrizeBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.giftRain.GiftRainBeen;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.giftRain.RedPacketViewHelper;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop.RedRainCountDownPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.CloseLiveMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.ForbiddenMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveExitAndJoinMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveFollowMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveGiftMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveNormalRedMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveRedMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveSystemBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveTextMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.RoomManageMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.SwichMoneyMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveSystemMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RewardAnchorPop;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow.CommisionPlanPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineMessageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MyEquipmentActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateGiftAmountModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChatNoticeMessageEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ImageDomainEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveInviteCodeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveNoticeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveTJRedEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PreviewCacheModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RedRainEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RefreshUrlEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ResetVisibleEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RongConnectModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SkipModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SystemBetMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SystemMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ZjMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChatUserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.AutoTollPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ForbiddenPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.LiveQrCodePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.LiveRankDialogFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RedRainCountTimePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.TurntablePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RulePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DrawerLeftEdgeSize;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideCacheUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.HeightProvider;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.net_speed.NetSpeed;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.net_speed.NetSpeedTimer;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.AutoLineFeedLayoutManager;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusActivityRankPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusBetJoinOldPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusBetRulePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusGiftPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusLotteryListPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusNotePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusPackMorePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusPackPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusSetMaDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusSetMaPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.MyRecycleView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dalong.marqueeview.MarqueeView;
import com.my.xunboplayerlib.CustomJzvd.JZMediaIjk;
import com.my.xunboplayerlib.CustomJzvd.MyJzvdStd;
import com.my.xunboplayerlib.Jzvd;
import com.my.xunboplayerlib.PlayEvent;
import com.opensource.svgaplayer.SVGAImageView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;
import okhttp3.Headers;
import pl.droidsonroids.gif.GifImageView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PackType.TJ;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveViewModel.STATE.CLOSE;
import static io.rong.imlib.RongIMClient.ErrorCode.FORBIDDEN_IN_CHATROOM;


public class LiveFragment extends MvpBaseFragment /*implements Handler.Callback */{

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    /*    @BindView(R.id.tv_id)
        TextView tv_id;*/
    @BindView(R.id.ll_live)
    FrameLayout ll_live;
    /*    @BindView(R.id.can_not_play_iv)
        public ImageView can_not_play_iv;*/
    @BindView(R.id.myjzvd)
    public MyJzvdStd myjzvd;
    @BindView(R.id.drawerlayout)
//    XDrawerLayout drawerlayout;
    public DrawerLayout drawerlayout;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.recy_lottery)
    RecyclerView mRecyLotteryNo;
    @BindView(R.id.tv_lottery_name)
    TextView tv_lottery_name;
    @BindView(R.id.tv_lottery_qishu)
    TextView tv_lottery_qishu;
    @BindView(R.id.recy_renshu)
    MyRecycleView mRecyRenshu;
    @BindView(R.id.tv_countdown)
    TextView tv_countdown;
    @BindView(R.id.right_recycler_group)
    Group right_recycler_group;
    //@BindView(R.id.iv_pack)
//    ImageView iv_pack;
//    @BindView(R.id.tv_packtype)
//    TextView tv_packtype;
//    @BindView(R.id.rabtn_zx)
//    RadioButton rabtn_zx;
//    @BindView(R.id.rabtn_qy)
//    RadioButton rabtn_qy;
    @BindView(R.id.iv_lottery)
    ImageView iv_lottery;
    @BindView(R.id.iv_bottomgift)
    ImageView iv_bottomgift;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.live_edit_panel)
    EditPanel editPanel;
    @BindView(R.id.drawwe_linear)
    ConstraintLayout drawerLinear;
    @BindView(R.id.svga_imageview)
    public SVGAImageView svgaImageView;
    @BindView(R.id.giftView)
    public GiftView giftView;
    /*    @BindView(R.id.tv_liveroom_tag)
        TextView tv_liveroom_tag;*/
    @BindView(R.id.notice_textview)
    MarqueeView marqueeTv;
    @BindView(R.id.live_cover_iv)
    ImageView live_cover_iv;

    @BindView(R.id.recy_pack)
    MyRecycleView recy_pack;
    @BindView(R.id.recy_pack3)
    MyRecycleView recy_pack3;
    @BindView(R.id.recy_pack4)
    MyRecycleView recy_pack4;
    @BindView(R.id.zsq_recycler_2)
    MyRecycleView zsq_recycler_2;
    @BindView(R.id.zsq_recycler_3)
    MyRecycleView zsq_recycler_3;
    @BindView(R.id.zsq_recycler_4)
    MyRecycleView zsq_recycler_4;
    @BindView(R.id.xian_iv)
    ImageView xianIv;
    @BindView(R.id.linearLayout7)
    public LinearLayout linearLayout7;
    @BindView(R.id.lottrry_open_result_constraintLayout)
    ConstraintLayout cos_lottrry_open_result;
    @BindView(R.id.money_tv)
    public TextView money_tv;
    @BindView(R.id.download_url_tv)
    TextView download_url_tv;
    @BindView(R.id.iv_collect)
    ImageView iv_collect;
    @BindView(R.id.internet_speed_tv)
    TextView internet_speed_tv;
    @BindView(R.id.invite_code_tv)
    TextView invite_code_tv;
    /*    @BindView(R.id.recy_pack_zsq)
        RecyclerView recy_pack_zsq;*/
    @BindView(R.id.dismiss_open_result_iv)
    ImageView dismiss_open_result_iv;

    /*    @BindView(R.id.recyc_pack_zsq_linear)
        LinearLayout recyc_pack_zsq_linear;*/
/*    @BindView(R.id.tv_zsq_left)
    TextView tv_zsq_left;
    @BindView(R.id.tv_zsq_right)
    TextView tv_zsq_right;*/
    @BindView(R.id.linear_liveroom_tag)
    LinearLayout linear_liveroom_tag;
    @BindView(R.id.clear_screen_iv)
    ImageView clear_screen_iv;
    @BindView(R.id.xiabo_iv)
    ImageView xiabo_iv;
    @BindView(R.id.share_iv)
    ImageView share_iv;
    @BindView(R.id.toll_constraintLayout)
    ConstraintLayout toll_constraintLayout;
    @BindView(R.id.toll_type_tv)
    public TextView toll_type_tv;
    @BindView(R.id.toll_countdown_tv)
    TextView toll_countdown_tv;
    @BindView(R.id.toll_iv)
    ImageView toll_iv;
    @BindView(R.id.join_svga_imageview)
    SVGAImageView join_svga_imageview;
    @BindView(R.id.test_svga_imageview)
    SVGAImageView test_svga_imageview;

    @BindView(R.id.count_down_fall_frameLayout)
    FrameLayout count_down_fall_frameLayout;
    @BindView(R.id.fail_tip_tv)
    TextView fail_tip_tv;
    @BindView(R.id.fail_loading_iv)
    GifImageView fail_loading_iv;
    @BindView(R.id.reward_linear)
    LinearLayout reward_linear;
    @BindView(R.id.reward_countdown_tv)
    TextView reward_countdown_tv;
    @BindView(R.id.reward_content_tv)
    TextView reward_content_tv;
    /*    @BindView(R.id.red_rain_countDown_tv)
        TextView red_rain_countDown_tv;*/
/*    @BindView(R.id.red_rain_countDown_iv)
    ImageView red_rain_countDown_iv;
    @BindView(R.id.online_service_iv)
    ImageView online_service_iv;*/
/*    @BindView(R.id.red_rain_linear)
    LinearLayout red_rain_linear;*/
    @BindView(R.id.turntable_iv)
    ImageView turntable_iv;

    String currentTypeName="";
    int updateCount=0;
    private Timer openResultTimer;
    private static String TAG = "LiveFragment";
    public String roomId;
    //    private Handler handler = new Handler(this);
    private MyHandler handler = new MyHandler(new SoftReference(this));
    //??????livefragment???viewmodel
    public LiveFragmentViewModel mViewModel;
    //liveactivity???viewmodel
    private LiveViewModel mLiveViewModel;
    private List<OpenNoMulBean> mOpenNoMulList = new ArrayList<>();//??????????????????
    private List<ChatUserEntity.MemberHeadPortraitListBean> mChatUserList = new ArrayList<>();
    private List<ChatUserEntity.MemberHeadPortraitListBean> userPortraitList = new ArrayList<>();//????????????url
    private List<Object> mIconDataList2 = new ArrayList<>();//??????icon??????
    private List<Object> mIconDataList3 = new ArrayList<>();//??????icon??????
    private List<Object> mIconDataList4 = new ArrayList<>();//??????icon??????
    private OpenNoMulAdapter mOpenNoMulAdapter;
    private ChatUserAdapter mChatUserAdapter;
    private ActivityIconAdapter mIconAdapter2;
    private ActivityIconAdapter mIconAdapter3;
    private ActivityIconAdapter mIconAdapter4;
    private ZSQAdapter zsqAdapter2;
    private ZSQAdapter zsqAdapter3;
    private ZSQAdapter zsqAdapter4;
    private ArrayList<ZSQEntity>zsqEntityArrayList2 = new ArrayList<>();
    private ArrayList<ZSQEntity>zsqEntityArrayList3= new ArrayList<>();
    private ArrayList<ZSQEntity>zsqEntityArrayList4= new ArrayList<>();
    public static final String POSITION = "position";
    private int position;
    public LiveEntity.AnchorMemberRoomListBean mLiveData;//?????????????????????
    private Disposable disposable;
    private CusPackPop mCusPackPop;
    private CusActivityRankPop mCusRankPop;
    private CusPackMorePop cusPackMorePop;
    private CusGiftPop cusGiftPop;
    private CusLotteryListPop cusLotteryListPop;
    private CusBetRulePop cusBetRulePop;
    //   private CusBetJoinPop cusBetJoinPop;
    private CusBetJoinOldPop cusBetJoinOldPop;
    private CusSetMaPop cusSetMaPop;
    private CusNotePop cusNotePop;
    private CustomPopupWindow customPopupWindow;
    public LiveChatFragment liveChatFragment;
    RedPacketViewHelper mRedPacketViewHelper;
    List<RedRainEntity.DataBean> dataBeanList;
    RedRainEntity.DataBean willOnGoingOrRainingEntity;
    public boolean isSkip = false;
    public int visibleCount=0;
    private CountdownPop countdownPop;//?????????????????????????????????
    private RedRainCountDownPop redRainCountDownPop;//????????????????????????????????????
    private String nowQishu;
    private NetSpeedTimer mNetSpeedTimer;
    private int quYueHBSwitch;//?????????????????? 1 ?????? 0??????
    private int zxHBSwitch;//??????????????????
    Timer showFollowTimer;
    private TimerTask showFollowPopTask;
    public ManagerTypeEntity managerTypeEntity;
    private LiveQrCodePop liveQrCodePop;
    //?????????
    private AutoTollPop autoTollPop;
    private String autoAnchorSubscribe;
    private String anchorSubscribeEndDate;
    private String anchorSubscribe;
    public long tollCountTime;
    public long redRainCountTime;
    private long tollEndDate;
    public boolean isNeedToll=false;//??????????????????
    public PublishSubject tollAmountSubject =PublishSubject.create();
    public TurntablePop turntablePop;
    public String turntableStatus = "0";
    private boolean position2HasCountDown;
    private boolean position3HasCountDown;
    private boolean position4HasCountDown;

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public GiftSvgaManage giftSvgaMage;
    public JoinSvgaMage joinGiftSvgaMage;
    public AssetsSvgaManage assetsSvgaManage;
    private RedRainCountTimePop redRainCountTimePop;
//    private int caculatePeopleNum=5;

    RulePop rulePop;
    boolean isOnCreate=true;
    int rewardCountdownTime = 4 ;
    public String zjPrice;

    public static int TO_DIAMOND_CODE=111;
    public static Fragment newInstant(int position) {
        LiveFragment liveFragment = new LiveFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        liveFragment.setArguments(bundle);
        return liveFragment;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//??????eventBus
        }

        //??????NetSpeedTimer??????
        mNetSpeedTimer = new NetSpeedTimer(new SoftReference<>(getContext()), new NetSpeed(), new SoftReference<>(handler)).setDelayTime(1000).setPeriodTime(2000);
        //??????????????????
        mNetSpeedTimer.startSpeedTimer();
        mRedPacketViewHelper= new RedPacketViewHelper(new SoftReference<>(getActivity()));
        position = getArguments().getInt(POSITION);
        /**
         * ????????? drawlayout
         */
        drawerlayout.setScrimColor(0x00000000);
        //  DrawerLeftEdgeSize.setRightEdgeSize(drawerlayout, 1f);
        DrawerLeftEdgeSize.setRightEdge(getActivity(),drawerlayout, 1f);
        drawerlayout.openDrawer(GravityCompat.END);
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {


            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {


            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mViewModel = ViewModelProviders.of(this).get(LiveFragmentViewModel.class);
        mLiveViewModel = ViewModelProviders.of(_mActivity).get(LiveViewModel.class);
        UpdateUi();
        /**
         * ????????????????????? ??????LiveActivity???viewmodel ??? ?????????position ?????? ????????????????????????
         */
        if (mLiveViewModel.getLiveData() != null&&mLiveViewModel.getLiveData().getValue()!=null) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = mLiveViewModel.getLiveData().getValue().get(position);
            if(anchorMemberRoomListBean!=null){
                mLiveData = anchorMemberRoomListBean;
                int isForbidden = mLiveData.getIsForbidden();
                int isLevel = mLiveData.getIsLevel();
                TextView inputTv = editPanel.inputTv;
                if (isForbidden==1) {
                    inputTv.setText(Utils.getString(R.string. ??????????????? ));
                    inputTv.setClickable(false);
                }/*else if(SharePreferencesUtil.getInt(getContext(),CommonStr.GRADE,1) < isLevel){
                inputTv.setText(" vip" + isLevel + Utils.getString(R.string.????????????????????? ));
                inputTv.setClickable(false);
            }*/else {
                    inputTv.setText(Utils.getString(R.string.???????????????));
                    inputTv.setClickable(true);
                }
                mViewModel.initCpId(mLiveData.getCpId(),mLiveData.getLiveRoomId());
//                tv_id.setText("ID:" + mLiveData.getAnchorAccount());
                tv_name.setText(mLiveData.getAnchorNickName());
//                money_tv.setText(mLiveData.getGiftAmount()+"");
//                money_tv_gone.setText(mLiveData.getGiftAmount()+"");
                if(mLiveData.getIsPrivate()==0){
                    GlideLoadViewUtil.LoadCircleView(getContext(), Utils.CPImageUrlCheck(getContext(), mLiveData.getHeadImg()), iv_avatar);
                }else {
                    GlideLoadViewUtil.LoadCircleView(getContext(), Utils.checkLiveImageUrl( mLiveData.getHeadImg()), iv_avatar);
                }
                myjzvd.setUp(StringMyUtil.isEmptyString(mLiveData.getLiveUrl())?"":mLiveData.getLiveUrl(), mLiveData.getAnchorAccount(), "init", myjzvd.SCREEN_NORMAL, JZMediaIjk.class);
                myjzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
                if(mLiveData.getIsPrivate()==0){
                    GlideLoadViewUtil.LoadMaoBoliNormalView( Utils.CPImageUrlCheck(getContext(), mLiveData.getCover()), myjzvd.thumbImageView);
//                    GlideLoadViewUtil.LoadMaoBoliNormalView( Utils.CPImageUrlCheck(getContext(), mLiveData.getCover()),can_not_play_iv);
                }else {
                    GlideLoadViewUtil.LoadMaoBoliNormalView( Utils.checkLiveImageUrl(mLiveData.getCover()), myjzvd.thumbImageView);//??????????????????????????????
//                    GlideLoadViewUtil.LoadMaoBoliNormalView( Utils.checkLiveImageUrl(mLiveData.getCover()), can_not_play_iv);//????????????????????????????????????????????????imageView
                }

                HbGameClassModel instance = HbGameClassModel.getInstance();
                String gameIdListStr = instance.getGameIdListStr();
                if(StringMyUtil.isNotEmpty(gameIdListStr)){
                    selectorId(instance);
                }
            }
        }
        /**
         * ??????????????? ?????????????????????????????????
         */
        mViewModel.getRequestFailCount().observe(this,(Integer failCount)  -> {
//            ????????? ??????????????????????????????6??????, ??????????????????
            if(failCount>=6){
                if(count_down_fall_frameLayout.getVisibility()!=VISIBLE){
                    count_down_fall_frameLayout.setVisibility(View.VISIBLE);
                }
            }else {
                //??????????????????
                if(count_down_fall_frameLayout.getVisibility()!=GONE){
                    count_down_fall_frameLayout.setVisibility(GONE);
                }
            }
        });

        /**
         * ?????????????????????recyclerview
         */
        mOpenNoMulAdapter = new OpenNoMulAdapter(mOpenNoMulList);
        AutoLineFeedLayoutManager layoutManager = new AutoLineFeedLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        mRecyLotteryNo.setLayoutManager(layoutManager);
        mRecyLotteryNo.setItemAnimator(new DefaultItemAnimator());
        mRecyLotteryNo.setHasFixedSize(true);
        mRecyLotteryNo.setAdapter(mOpenNoMulAdapter);
        /**
         * ?????????????????????recyclerView
         */
        mChatUserAdapter = new ChatUserAdapter(R.layout.item_chatuser, mChatUserList);
        LinearLayoutManager chatUserManager = new LinearLayoutManager(_mActivity);
        chatUserManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyRenshu.setLayoutManager(chatUserManager);
        mRecyRenshu.setHasFixedSize(true);
        mRecyRenshu.setAdapter(mChatUserAdapter);

        /**
         * ???????????????????????? recy
         */
        initActivityRecyclerPosition2();
        initActivityRecyclerPosition3();
        initActivityRecyclerPosition4();


        /**
         * ??????fragment??????
         */
        initChatFragment();
        if (customPopupWindow == null) {
            customPopupWindow = new CustomPopupWindow();
        }
        /**
         ?????????????????????
         */
        initGiftView();
        /**
         ?????????????????????
         */
        initSvgaImageView();

        getPaoMa();
        /*
        ????????????
         */
        iniImageDomain();
        /*
        ?????????
         */
//        requestInviteRedList();

        /*
        ????????????
         */
        if(mLiveData!=null&&mLiveData.getIsFollow()==1){
            iv_collect.setImageResource(R.drawable.ygz_iocn);
            iv_collect.setVisibility(GONE);
        }else {
            iv_collect.setImageResource(R.drawable.live_follow);
            iv_collect.setVisibility(View.VISIBLE);
        }
        /*
        ??????????????????
        */
        handler.postDelayed(redRainCountDownRunnable,1000);



    }
    private void initActivityRecyclerPosition4() {

        mIconAdapter4 = new ActivityIconAdapter(R.layout.item_activityicon, mIconDataList4);
        LinearLayoutManager iconManager = new LinearLayoutManager(_mActivity);
        iconManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_pack4.setLayoutManager(iconManager);
        recy_pack4.setAdapter(mIconAdapter4);
        ((DefaultItemAnimator)recy_pack4.getItemAnimator()).setSupportsChangeAnimations(false);//??????????????????
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recy_pack4);

        zsqAdapter4 = new ZSQAdapter(R.layout.red_pack_zsq_item_layout,zsqEntityArrayList4);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        zsq_recycler_4.setLayoutManager(layout);
        zsq_recycler_4.setAdapter(zsqAdapter4);
        position4HasCountDown = false;
        liveActivityEntity liveActivityArray = Utils.getLiveActivityArray();
        if(liveActivityArray!=null){
            Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
            List<liveActivityEntity.LiveShowPosition4Bean> liveShowPosition4 = liveActivityArray.getLiveShowPosition4();
            for (int i = 0; i < liveShowPosition4.size(); i++) {
                liveActivityEntity.LiveShowPosition4Bean liveShowPosition4Bean = liveShowPosition4.get(i);
                long liveShowEndDate = liveShowPosition4Bean.getLiveShowEndDate();
                if(liveShowEndDate + shijiancha - System.currentTimeMillis()>0){
                    /**
                     * ????????????list?????????????????????icon . ???????????????????????????runnable????????????????????????, ???????????????
                     */
                    if(!position4HasCountDown &&liveShowPosition4Bean.getLiveShowPage()==4){
                        position4HasCountDown = true;
                    }
                    mIconAdapter4.setHaveRedCountdown(position4HasCountDown);
                    //????????????????????????add
                    mIconDataList4.add(liveShowPosition4Bean);
                }
            }

            //???????????????????????????????????????
            if(mIconDataList4.size()>1){
                for (int i = 0; i < mIconDataList4.size(); i++) {
                    ZSQEntity zsqEntity = new ZSQEntity();
                    if(i==0){
                        zsqEntity.setStatus(1);
                    }
                    zsqEntityArrayList4.add(zsqEntity);
                }
            }
            mIconAdapter4.notifyDataSetChanged();
            zsqAdapter4.notifyDataSetChanged();
        }

        mIconAdapter4.addChildClickViewIds(R.id.iv_item_activityicon);
        mIconAdapter4.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = mIconDataList4.get(position);
                if(o instanceof liveActivityEntity.LiveShowPosition4Bean){
                    liveActivityEntity.LiveShowPosition4Bean liveShowPosition4Bean = (liveActivityEntity.LiveShowPosition4Bean) o;
                    int liveShowPage = liveShowPosition4Bean.getLiveShowPage();//????????????//0??????1???????????????2???????????????3???????????????4?????????
                    switch (liveShowPage){
                        case 0:

                            break;
                        case 1:
                            //??????
                            Intent intent = new Intent(getContext(), LiveWebViewActivity.class);
                            intent.putExtra("loadUrl",liveShowPosition4Bean.getLink());
                            startActivity(intent);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 2:
                            InviteAndMakeMoneyActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 3:
                            ZhuangXiangRedActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 4:
                            if(redRainCountTimePop==null){
                                redRainCountTimePop = new RedRainCountTimePop(getContext(),false);
                            }
                            redRainCountTimePop.showAtLocation(iv_close, Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                            break;
                    }
                }
            }
        });

        /*
        ????????????icon,???????????????
         */
        recy_pack4.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==SCROLL_STATE_IDLE  ){
                    RecyclerView.LayoutManager layoutManager1 = recyclerView.getLayoutManager();
                    if(layoutManager1 instanceof  LinearLayoutManager){
                        int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager1).findLastVisibleItemPosition();
                        if(zsqEntityArrayList4.size()-1>=lastVisibleItemPosition){
                            for (int i = 0; i < zsqEntityArrayList4.size(); i++) {
                                zsqEntityArrayList4.get(i).setStatus(0);
                            }
                            zsqEntityArrayList4.get(lastVisibleItemPosition).setStatus(1);
                            zsq_recycler_4.scrollToPosition(lastVisibleItemPosition);
                            zsqAdapter4.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
    private void initActivityRecyclerPosition3() {
        mIconAdapter3 = new ActivityIconAdapter(R.layout.item_activityicon, mIconDataList3);
        LinearLayoutManager iconManager = new LinearLayoutManager(_mActivity);
        iconManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_pack3.setLayoutManager(iconManager);
        recy_pack3.setAdapter(mIconAdapter3);
        ((DefaultItemAnimator)recy_pack3.getItemAnimator()).setSupportsChangeAnimations(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recy_pack3);

        zsqAdapter3 = new ZSQAdapter(R.layout.red_pack_zsq_item_layout,zsqEntityArrayList3);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        zsq_recycler_3.setLayoutManager(layout);
        zsq_recycler_3.setAdapter(zsqAdapter3);
        position3HasCountDown = false;
        liveActivityEntity liveActivityArray = Utils.getLiveActivityArray();
        if(liveActivityArray!=null){
            Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
            List<liveActivityEntity.LiveShowPosition3Bean> liveShowPosition3 = liveActivityArray.getLiveShowPosition3();
            for (int i = 0; i < liveShowPosition3.size(); i++) {
                liveActivityEntity.LiveShowPosition3Bean liveShowPosition3Bean = liveShowPosition3.get(i);
                long liveShowEndDate = liveShowPosition3Bean.getLiveShowEndDate();
                if(liveShowEndDate + shijiancha - System.currentTimeMillis()>0){
                    /**
                     * ????????????list?????????????????????icon . ???????????????????????????runnable????????????????????????, ???????????????
                     */
                    if(!position3HasCountDown &&liveShowPosition3Bean.getLiveShowPage()==4){
                        position3HasCountDown = true;
                    }
                    mIconAdapter3.setHaveRedCountdown(position3HasCountDown);
                    //????????????????????????add
                    mIconDataList3.add(liveShowPosition3Bean);
                }
            }
            //???????????????????????????????????????
            if(mIconDataList3.size()>1){
                for (int i = 0; i < mIconDataList3.size(); i++) {
                    ZSQEntity zsqEntity = new ZSQEntity();
                    if(i==0){
                        zsqEntity.setStatus(1);
                    }
                    zsqEntityArrayList3.add(zsqEntity);
                }

            }

            mIconAdapter3.notifyDataSetChanged();
            zsqAdapter3.notifyDataSetChanged();
        }

        mIconAdapter3.addChildClickViewIds(R.id.iv_item_activityicon);
        mIconAdapter3.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = mIconDataList3.get(position);
                if(o instanceof liveActivityEntity.LiveShowPosition3Bean){
                    liveActivityEntity.LiveShowPosition3Bean liveShowPosition3Bean = (liveActivityEntity.LiveShowPosition3Bean) o;
                    int liveShowPage = liveShowPosition3Bean.getLiveShowPage();//????????????//0??????1???????????????2???????????????3???????????????4?????????
                    switch (liveShowPage){
                        case 0:
                            break;
                        case 1:
                            //??????
                            Intent intent = new Intent(getContext(), LiveWebViewActivity.class);
                            intent.putExtra("loadUrl",liveShowPosition3Bean.getLink());
                            startActivity(intent);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 2:
                            InviteAndMakeMoneyActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 3:
                            ZhuangXiangRedActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 4:
                            if(redRainCountTimePop==null){
                                redRainCountTimePop = new RedRainCountTimePop(getContext(),false);
                            }
                            redRainCountTimePop.showAtLocation(iv_close, Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                            break;
                    }
                }
            }
        });

        /*
        ????????????icon,???????????????
         */
        recy_pack3.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==SCROLL_STATE_IDLE  ){
                    RecyclerView.LayoutManager layoutManager1 = recyclerView.getLayoutManager();
                    if(layoutManager1 instanceof  LinearLayoutManager){
                        int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager1).findLastVisibleItemPosition();
                        if(zsqEntityArrayList3.size()-1>=lastVisibleItemPosition){
                            for (int i = 0; i < zsqEntityArrayList3.size(); i++) {
                                zsqEntityArrayList3.get(i).setStatus(0);
                            }
                            zsqEntityArrayList3.get(lastVisibleItemPosition).setStatus(1);
                            zsq_recycler_3.scrollToPosition(lastVisibleItemPosition);
                            zsqAdapter3.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    private void initActivityRecyclerPosition2() {
        mIconAdapter2 = new ActivityIconAdapter(R.layout.item_activityicon, mIconDataList2);
        LinearLayoutManager iconManager = new LinearLayoutManager(_mActivity);
        iconManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_pack.setLayoutManager(iconManager);
        ((DefaultItemAnimator)recy_pack.getItemAnimator()).setSupportsChangeAnimations(false);
        recy_pack.setAdapter(mIconAdapter2);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recy_pack);

        zsqAdapter2 = new ZSQAdapter(R.layout.red_pack_zsq_item_layout,zsqEntityArrayList2);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        zsq_recycler_2.setLayoutManager(layout);
        zsq_recycler_2.setAdapter(zsqAdapter2);
        position2HasCountDown = false;
        liveActivityEntity liveActivityArray = Utils.getLiveActivityArray();
        if(liveActivityArray!=null){
            Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
            List<liveActivityEntity.LiveShowPosition2Bean> liveShowPosition2 = liveActivityArray.getLiveShowPosition2();
            for (int i = 0; i < liveShowPosition2.size(); i++) {
                liveActivityEntity.LiveShowPosition2Bean liveShowPosition2Bean = liveShowPosition2.get(i);
                long liveShowEndDate = liveShowPosition2Bean.getLiveShowEndDate();
                if(liveShowEndDate + shijiancha - System.currentTimeMillis()>0){
                    /**
                     * ????????????list?????????????????????icon . ???????????????????????????runnable????????????????????????, ???????????????
                     */
                    if(!position2HasCountDown &&liveShowPosition2Bean.getLiveShowPage()==4){
                        position2HasCountDown = true;
                    }
                    mIconAdapter2.setHaveRedCountdown(position2HasCountDown);
                    //??????????????????add
                    mIconDataList2.add(liveShowPosition2Bean);
                }

            }
            //???????????????????????????????????????
            if(mIconDataList2.size()>1){
                for (int i = 0; i < mIconDataList2.size(); i++) {
                    ZSQEntity zsqEntity = new ZSQEntity();
                    if(i==0){
                        zsqEntity.setStatus(1);
                    }
                    zsqEntityArrayList2.add(zsqEntity);
                }

            }
            mIconAdapter2.notifyDataSetChanged();
            zsqAdapter2.notifyDataSetChanged();
        }

        mIconAdapter2.addChildClickViewIds(R.id.iv_item_activityicon);
        mIconAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = mIconDataList2.get(position);
                if(o instanceof liveActivityEntity.LiveShowPosition2Bean){
                    liveActivityEntity.LiveShowPosition2Bean liveShowPosition2Bean = (liveActivityEntity.LiveShowPosition2Bean) o;
                    int liveShowPage = liveShowPosition2Bean.getLiveShowPage();//????????????//0??????1???????????????2???????????????3???????????????4?????????
                    switch (liveShowPage){
                        case 0:
                            break;
                        case 1:
                            Intent intent = new Intent(getContext(), LiveWebViewActivity.class);
                            intent.putExtra("loadUrl",liveShowPosition2Bean.getLink());
                            startActivity(intent);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            //??????
                            break;
                        case 2:
                            InviteAndMakeMoneyActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 3:
                            ZhuangXiangRedActivity.startAty(getContext(),roomId);
                            EventBus.getDefault().postSticky(new SkipModel(true));
                            break;
                        case 4:
                            if(redRainCountTimePop==null){
                                redRainCountTimePop = new RedRainCountTimePop(getContext(),false);
                            }
                            redRainCountTimePop.showAtLocation(iv_close, Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                            break;
                    }
                }
            }
        });

        /*
        ????????????icon,???????????????
         */
        recy_pack.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==SCROLL_STATE_IDLE  ){
                    RecyclerView.LayoutManager layoutManager1 = recyclerView.getLayoutManager();
                    if(layoutManager1 instanceof  LinearLayoutManager){
                        int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager1).findLastVisibleItemPosition();
                        if(zsqEntityArrayList2.size()-1>=lastVisibleItemPosition){
                            for (int i = 0; i < zsqEntityArrayList2.size(); i++) {
                                zsqEntityArrayList2.get(i).setStatus(0);
                            }
                            zsqEntityArrayList2.get(lastVisibleItemPosition).setStatus(1);
                            zsq_recycler_2.scrollToPosition(lastVisibleItemPosition);
                            zsqAdapter2.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    /**
     * ????????????
     */
    private void requestManageType(boolean joinChatRoom) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorAccount",mLiveData.getAnchorAccount());
        data.put("remoteLiveManagementId",mLiveData.getRemoteLiveManagementId());
        data.put("liveRoomId",mLiveData.getLiveRoomId());
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.MANAGE_TYPE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                managerTypeEntity = JSONObject.parseObject(result, ManagerTypeEntity.class);
                if(mLiveData!=null){
                    int robotCount = managerTypeEntity.getRobotCount();
                    if(robotCount!=0){
                        mLiveData.setRealChangeNum(0);
                        mLiveData.setBasePeopleNum(0);
                        mLiveData.setRobotCount(robotCount);
                    }
                }
                mViewModel.getAnchorManageData().postValue(managerTypeEntity);
                if(joinChatRoom){
                    joinChatRoom(roomId);
                }
                anchorSubscribe = managerTypeEntity.getAnchorSubscribe();//????????????????????? >0 ??? <=0 ??????
                anchorSubscribeEndDate = managerTypeEntity.getAnchorSubscribeEndDate();//??????????????????
                autoAnchorSubscribe = managerTypeEntity.getAutoAnchorSubscribe();//?????????????????? 0??? 1???
                double subscribeAmount = Double.parseDouble(StringMyUtil.isEmptyString(anchorSubscribe)?"0":anchorSubscribe);
                if(StringMyUtil.isEmptyString(anchorSubscribeEndDate)){
                    //?????????????????? ?????????????????????0, ????????????pop
                    if(subscribeAmount>0){
                        int previewTimes = getPreviewTimes();
                        if(previewTimes<2){
                            //??????????????????2, ????????????
                            initAutoTollPop(AutoTollPop.PopStatus.PREVIEW);
                        }else {
                            initAutoTollPop(AutoTollPop.PopStatus.NORMAL);
                        }
                    }else {
                        toll_constraintLayout.setVisibility(GONE);
                    }
                }else {
                    try {
                        long endDate = Long.parseLong(DateUtil.dateToStamp(anchorSubscribeEndDate));
                        if(endDate >System.currentTimeMillis()&&subscribeAmount>0){
                            //??????????????????????????????,???????????????
                            initTollCountDown();
                        }else {
                            if(subscribeAmount>0){
                                if(getPreviewTimes()<2){
                                    //??????????????????2,??????pop????????????
                                    initAutoTollPop(AutoTollPop.PopStatus.PREVIEW);
                                }else {
                                    //??????????????????2,??????pop(????????????, ????????????)
                                    initAutoTollPop(AutoTollPop.PopStatus.NORMAL);
                                }
                            }else {
                                toll_constraintLayout.setVisibility(GONE);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                String giftAmount = managerTypeEntity.getGiftAmount();
                if(money_tv!=null&&StringMyUtil.isNotEmpty(giftAmount)){
                    money_tv.setText(giftAmount);
                }
            }

            @Override
            public void onFailed(String msg) {
                /**
                 * ???????????????????????????????????????,????????????
                 */
                if(!joinChatRoom){
                    ToastUtil.showToast(Utils.getString(R.string.??????????????????????????????????????????????????????));
                    if(getActivity()!=null&&!getActivity().isFinishing()){
                        getActivity().finish();
                    }
                }
            }
        });
    }
    /**
     * ?????????????????????????????????????????????(??????2???????????????)
     * @return
     */
    private int getPreviewTimes() {
        PreviewCacheModel previewCacheModel;
        int previewTimes=0;
        String cache = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.PREVIEW_CACHE, "");
        if(StringMyUtil.isNotEmpty(cache)){
            previewCacheModel = JSONObject.parseObject(cache, PreviewCacheModel.class);
        }else {
            previewCacheModel = new PreviewCacheModel();
        }
        List<PreviewCacheModel.DataBean> beanList = previewCacheModel.getDataBeanList();
        if(beanList!=null){
            for (int i = 0; i < beanList.size(); i++) {
                PreviewCacheModel.DataBean dataBean = beanList.get(i);
                long date = dataBean.getDate();
                String url = dataBean.getUrl();
                if(url.equals(mLiveData.getLiveUrl())){
                    if(System.currentTimeMillis()-date<(3600*24*1000)){
                        previewTimes++;
                    }
                }
            }
        }
        return previewTimes;
    }

    private void initAutoTollPop(AutoTollPop.PopStatus popStatus) {
        if(autoTollPop!=null&&autoTollPop.isShowing()){
            autoTollPop.dismiss();
        }
        autoTollPop = new AutoTollPop(getContext(), LiveFragment.this,popStatus);
        if(autoTollPop!=null)autoTollPop.dismiss();
        autoTollPop.showAtLocation(ll_live, Gravity.BOTTOM,0,0);
    }
    public void initTollCountDown() throws ParseException {
        Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
        if((StringMyUtil.isEmptyString(autoAnchorSubscribe)?"0":autoAnchorSubscribe).equals("0")){
            toll_type_tv.setText(Utils.getString(R.string.??????));
        }else {
            toll_type_tv.setText(Utils.getString(R.string.??????));
        }
        toll_constraintLayout.setVisibility(View.VISIBLE);
        //  tollCountTime = millionSeconds + shijiancha - nowTime -(Long.parseLong(tqtime)*1000);
        if(StringMyUtil.isNotEmpty(anchorSubscribeEndDate)){
            tollEndDate = Long.parseLong(DateUtil.dateToStamp(anchorSubscribeEndDate));
            tollCountTime =  tollEndDate +shijiancha-System.currentTimeMillis();
            handler.postDelayed(tollCountDownRunnable,1000);
        }
    }



    /**
     * ??????????????????recyclerView????????????????????????item???, ??????????????????????????????
     * @param timeStr
     */
    private void initActivityRecyclerRedCountdown(String timeStr) {
        if(position2HasCountDown){
            for (int i = 0; i < mIconDataList2.size(); i++) {
                Object o = mIconDataList2.get(i);
                if(o instanceof liveActivityEntity.LiveShowPosition2Bean ){
                    liveActivityEntity.LiveShowPosition2Bean liveShowPosition2Bean = (liveActivityEntity.LiveShowPosition2Bean) o;
                    if(liveShowPosition2Bean.getLiveShowPage()==4){
                        liveShowPosition2Bean.setTimeStr(timeStr);
                        mIconAdapter2.notifyItemChanged(i);
                    }
                }
            }
        }
        if(position3HasCountDown){
            for (int i = 0; i < mIconDataList3.size(); i++) {
                Object o = mIconDataList3.get(i);
                if(o instanceof liveActivityEntity.LiveShowPosition3Bean ){
                    liveActivityEntity.LiveShowPosition3Bean liveShowPosition3Bean = (liveActivityEntity.LiveShowPosition3Bean) o;
                    if(liveShowPosition3Bean.getLiveShowPage()==4){
                        liveShowPosition3Bean.setTimeStr(timeStr);
                        mIconAdapter3.notifyItemChanged(i);
                    }
                }
            }
        }
        if(position4HasCountDown){
            for (int i = 0; i < mIconDataList4.size(); i++) {
                Object o = mIconDataList4.get(i);
                if(o instanceof liveActivityEntity.LiveShowPosition4Bean ){
                    liveActivityEntity.LiveShowPosition4Bean liveShowPosition4Bean = (liveActivityEntity.LiveShowPosition4Bean) o;
                    if(liveShowPosition4Bean.getLiveShowPage()==4){
                        liveShowPosition4Bean.setTimeStr("00:00:00");
                        mIconAdapter4.notifyItemChanged(i);
                    }
                }
            }
        }
    }

    /**
     ??????????????????
     */
    public void iniImageDomain() {
        ImageDomainEntity imageDomainEntity = ImageDomainEntity.getInstance();
        String jsonStr = imageDomainEntity.getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("type", "2");
            HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.REQUEST_10005, map, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    imageDomainEntity.setJsonStr(result);
                    handImageDomainJson(result);
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            handImageDomainJson(jsonStr);
        }

    }

    private void handImageDomainJson(String result) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        String imageUrl = jsonObject.getString("imageUrl");
        if(download_url_tv!=null){
            download_url_tv.setText(Utils.getString(R.string.??????)+imageUrl);
        }

    }

    /**
     * ???????????????
     */
    private void requestInviteRedList( ) {
        LiveInviteCodeEntity liveInviteCodeEntity = LiveInviteCodeEntity.getInstance();
        String jsonStr = liveInviteCodeEntity.getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){

            HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.MAKE_MONEY_RULE, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    liveInviteCodeEntity.setJsonStr(result);
                    handInviteJson(result);
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            handInviteJson(jsonStr);
        }
    }

    private void handInviteJson(String result) {
        JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
        if(null==data){
            return;
        }
        invite_code_tv.setText(Utils.getString(R.string.?????????)+data.getString("inviteCode"));
    }
    /**
     ?????????????????????
     */

    private void getPaoMa() {
        LiveNoticeEntity liveNoticeEntity = LiveNoticeEntity.getInstance();
        String jsonStr = liveNoticeEntity.getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("pageNo",1);
            data.put("pageSize",15);
            data.put("type",2);//0.logo  1?????????  2?????? 3????????????  4????????????
            HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_33r, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    liveNoticeEntity.setJsonStr(result);
                    handNoticeJson(result);
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            handNoticeJson(jsonStr);
        }
    }

    /**
     * ?????????????????????json
     * @param result
     */
    private void handNoticeJson(String result) {
        String text="";
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        JSONArray extensionNoticeInfoList = jsonObject1.getJSONArray("extensionNoticeInfoList");
        for (int j = 0; j < extensionNoticeInfoList.size(); j++) {
            for (int i = 0; i < extensionNoticeInfoList.size(); i++) {
                JSONObject jsonObject = extensionNoticeInfoList.getJSONObject(i);
                String contentTxt = jsonObject.getString("contentTxt");
                text += "              " +(i+1)+":  "+ contentTxt;
            }
            if(null!=marqueeTv){
                marqueeTv.setText("          " + text + "               ");
//                        marqueeTv.setSelected(true);
                marqueeTv.startScroll();
            }
        }
    }

    /**
     ?????????????????????(?????????????????????)
     */
    private void getNotice(ArrayList<LiveMessageModel> liveMessageModelArrayList) {
        ChatNoticeMessageEntity chatNoticeMessageEntity = ChatNoticeMessageEntity.getInstance();
        String jsonStr = chatNoticeMessageEntity.getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("pageNo", 1);
            data.put("pageSize", 15);
            data.put("type", 7);//0.logo  1?????????  2?????? 3????????????  4????????????  7:??????????????????
            HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_33r, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    chatNoticeMessageEntity.setJsonStr(result);
                    handChatNoticeMessageJson(result, liveMessageModelArrayList);
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            handChatNoticeMessageJson(jsonStr, liveMessageModelArrayList);
        }

    }

    private void handChatNoticeMessageJson(String result, ArrayList<LiveMessageModel> liveMessageModelArrayList) {
        if(liveChatFragment!=null){
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            JSONArray extensionNoticeInfoList = jsonObject1.getJSONArray("extensionNoticeInfoList");
            for (int i = 0; i < extensionNoticeInfoList.size(); i++) {
                JSONObject jsonObject = extensionNoticeInfoList.getJSONObject(i);
                String contentTxt = jsonObject.getString("contentTxt");
                LiveMessageModel liveMessageModel = new LiveMessageModel();
                liveMessageModel.setObjectName(CommonStr.NOTICE_MESSAGE);
                liveMessageModel.setTextContent(contentTxt);
                boolean haveNotice =false;
                for (int j = 0; j < liveMessageModelArrayList.size(); j++) {
                    if(liveMessageModelArrayList.get(j).getObjectName().equals(CommonStr.NOTICE_MESSAGE)){
                        haveNotice=true;
                        break;
                    }
                }
                if(!haveNotice){
                    liveMessageModelArrayList.add(liveMessageModel);
                    liveChatFragment.liveChatAdapter.notifyDataSetChanged();
                }

            }
        }
    }

    private void initSvgaImageView() {
        giftSvgaMage = new GiftSvgaManage(getContext(),svgaImageView);
    }

    public void startCountDown(String redId){
        countdownPop = new CountdownPop(new SoftReference<>(getContext()));
        if(marqueeTv!=null){
            countdownPop.showAtLocation(marqueeTv,Gravity.CENTER,0,0);
        }
        countdownPop.setMyOnDismissListener(new CountdownPop.MyOnDismissListener() {
            @Override
            public void onDisMissListener() {
                startRedRain(redId);
            }
        });
    }

    private void startRedRain(String redId) {
        GiftRainBeen giftRainBeen = new GiftRainBeen();
        mRedPacketViewHelper.endGiftRain();
        if(getActivity()==null){
            return;
        }
        getActivity(). getWindow().getDecorView().postDelayed(() -> {
            List<BoxInfo> boxInfos = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                BoxInfo boxInfo = new BoxInfo();
                boxInfo.setAwardId(i);
                boxInfo.setVoucher("ice " + i);
                boxInfos.add(boxInfo);
            }
            mRedPacketViewHelper.launchGiftRainRocket(0, boxInfos, new RedPacketViewHelper.GiftRainListener(){
                @Override
                public void startLaunch() {

                }

                @Override
                public void startRain() {
                    LiveActivity liveActivity = (LiveActivity)getActivity();
                    liveActivity.mViewPager.setCanScroll(false);
                    String giftRainId = SharePreferencesUtil.getString(MyApplication.getInstance(), "giftRainId", "");
                    long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
                    giftRainId=redId+"_"+user_id+","+giftRainId+"_"+user_id;
                    String needString = "";
                    needString = giftRainId.substring(0, giftRainId.length()-1);
                    SharePreferencesUtil.putString(MyApplication.getInstance(),"giftRainId",needString);
                    //??????model??????,????????????
                    willOnGoingOrRainingEntity=null;
                    findWillOnGoingOrRainingEntity();
                    /*
                    ??????????????????(?????????)
                     */
/*                    redRainCountDownPop = new RedRainCountDownPop(getContext(),LiveFragment.this);
                    redRainCountDownPop.showAtLocation(marqueeTv,Gravity.TOP|Gravity.LEFT,15,-50);
                    ProgressDialogUtil.darkenBackground(getActivity(),0.9f);
                    redRainCountDownPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            mRedPacketViewHelper.endGiftRain();
                            ProgressDialogUtil.darkenBackground(getActivity(),1f);
                        }
                    });*/

                }

                @Override
                public void openBoom() {
                    int clickCount = giftRainBeen.getClickCount();
                    clickCount++;
                    giftRainBeen.setClickCount(clickCount);
                    //??????????????????????????????
                    if(clickCount==1){
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("user_id",SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l));
                        data.put("redId",redId);
                        data.put("ip",SharePreferencesUtil.getString(MyApplication.getInstance(),"netIp",""));
                        HttpApiUtils.CPnormalRequest(_mActivity, LiveFragment.this,RequestUtil.HB_OPEN_TJ_GHB, data, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                Boolean isOpened = jsonObject.getBoolean("isOpened");
                                BigDecimal price = jsonObject.getBigDecimal("price");
                                price= price==null?BigDecimal.ZERO:price;
                                if (isOpened == null ? false : isOpened) {
                                    String message = jsonObject.getString("message");
                                    giftRainBeen.setFailStr(message);
                                    if (!giftRainBeen.isHaveShowResult()) {
                                        giftRainBeen.setHaveRequestData(true);
                                        if (giftRainBeen.isEnd()) {
                                            giftRainBeen.setHaveShowResult(true);
                                            OpenPackPop(redId + "", RedStatus.FAIL, "0", RedType.TJ, roomId, message);
                                        }
                                    }
                                } else if (price.compareTo(BigDecimal.ZERO) <= 0) {
                                    String message = jsonObject.getString("message");
                                    giftRainBeen.setFailStr(message);
                                    if (!giftRainBeen.isHaveShowResult()) {
                                        giftRainBeen.setHaveRequestData(true);
                                        if (giftRainBeen.isEnd()) {
                                            giftRainBeen.setHaveShowResult(true);
                                            OpenPackPop(redId + "", RedStatus.FAIL, "0", RedType.TJ, roomId, message);
                                        }
                                    }
                                }
                                else {
                                    giftRainBeen.setSuccese(true);
                                    giftRainBeen.setPrice(price + "");
                                    if (!giftRainBeen.isHaveShowResult()) {
                                        giftRainBeen.setHaveRequestData(true);
                                        if (giftRainBeen.isEnd()) {
                                            giftRainBeen.setHaveShowResult(true);
                                            OpenPackPop(redId + "", RedStatus.SUCCESS, price + "", RedType.TJ, roomId, "");
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onFailed(String msg) {
                                giftRainBeen.setFailStr(msg);
                                if(!giftRainBeen.isHaveShowResult()){
                                    giftRainBeen.setHaveRequestData(true);
                                    if(giftRainBeen.isEnd()){
                                        giftRainBeen.setHaveShowResult(true);
                                        OpenPackPop(redId+"",RedStatus.FAIL,"0",RedType.TJ,roomId,msg);
                                    }
                                }

                            }
                        });
                    }
                }

                @Override
                public void openGift(BoxPrizeBean boxPrizeBean) {

                }


                @Override
                public void endRain() {
                    LiveActivity liveActivity = (LiveActivity)getActivity();
                    if(liveActivity!=null&&!liveActivity.isFinishing()&&!liveActivity.isDestroyed()){
                        liveActivity.mViewPager.setCanScroll(true);
                    }

                    if(!giftRainBeen.isHaveShowResult()){
                        if(giftRainBeen.isHaveRequestData()){
                            giftRainBeen.setHaveShowResult(true);
                            giftRainBeen.setEnd(true);
                            //String redId, RedStatus redStatus, RedType redType, String roomId
                            if(giftRainBeen.isSuccese()){
//                              String redId, RedStatus redStatus, String qbPrice, RedType redType, String roomId, String failInfo
                                OpenPackPop(redId+"",RedStatus.SUCCESS,giftRainBeen.getPrice(),RedType.TJ,roomId,"");
                            }else {
                                OpenPackPop(redId+"",RedStatus.FAIL,"0",RedType.TJ,roomId,giftRainBeen.getFailStr());
                            }
                        }
                    }
                }
            });
        }, 200);
    }

    private void initGiftView() {
        giftView.setViewCount(3);
        giftView.init();
    }

    private void initChatFragment() {
        //LiveChatFragment{d0fae22 (9efa9a56-9fd3-4133-ba27-2e71f742d459) id=0x7f0903ec}
        FragmentManager fragmentManager = getChildFragmentManager();
        liveChatFragment = (LiveChatFragment) fragmentManager.findFragmentById(R.id.live_chat_fragment);
        liveChatFragment.setLiveFragment(this);
        /**
         * ???????????? ???????????????
         */
        drawerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPanel.editLinear.setVisibility(GONE);
                editPanel.inputClickLinear.setVisibility(View.VISIBLE);
                linearLayout7.setVisibility(View.VISIBLE);
                Utils.hideSoftKeyBoard(getActivity(),editPanel.editText);
            }
        });
    }


    private void initRongYun() {
        setRoomId(mLiveData.getLiveRoomId());
        editPanel = getRootView().findViewById(R.id.live_edit_panel);
        editPanel.setRoomId(roomId);
        editPanel.setLiveFragment(this);
        RongLibUtils.addEventHandler(handler);
//        joinChatroom(roomId);
        joinGiftSvgaMage = new JoinSvgaMage(getContext(),join_svga_imageview);
        assetsSvgaManage = new AssetsSvgaManage(getContext(),test_svga_imageview);
        editPanel.setInputLinstener();
        editPanel.setOnHeightChangeListener(new EditPanel.OnHeightChangeListener() {
            @Override
            public void onHeightChange(int height) {
                if (height == 0) {
                    //??????????????????????????????recyclerVIew
                    if(right_recycler_group!=null){
                        right_recycler_group.setVisibility(View.VISIBLE);
                    }
                } else {
                    //??????????????????????????????recyclerView
                    if(right_recycler_group!=null) {
                        right_recycler_group.setVisibility(GONE);
                    }
                }
            }
        });
    }
    /**
     * ???????????????
     *
     * @param roomId
     */
    private void joinChatRoom(String roomId) {
        String joinChatroomId = SharePreferencesUtil.getString(MyApplication.getInstance(), "joinChatroomId", "");
        LiveExitAndJoinMessage liveExitAndJoinMessage = new LiveExitAndJoinMessage(SharePreferencesUtil.getString(getContext(), "userNickName", ""), "1");
        liveExitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext(),managerTypeEntity));
        if (StringMyUtil.isNotEmpty(joinChatroomId)&&!joinChatroomId.equals(mLiveData.getLiveRoomId())) {
//            RongLibUtils.sendMessage(joinChatroomId, liveExitAndJoinMessage);//???????????????????????????

            RongLibUtils.quitChatRoom(joinChatroomId, new RongIMClient.OperationCallback() {
                @Override
                public void onSuccess() {
                    Utils.logE(TAG, "rongLog onSuccess:  ????????????????????? roomId="  + joinChatroomId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Utils.logE(TAG, "rongLog  onError:  ????????????????????? roomId="  + joinChatroomId);
                }
            });
        }
        ArrayList<LiveMessageModel> liveMessageModelArrayList = liveChatFragment.liveMessageModelArrayList;
        liveMessageModelArrayList.clear();
        liveChatFragment.liveChatAdapter.notifyDataSetChanged();
                 /*
                ?????????????????????
                 */
        getNotice(liveMessageModelArrayList);

        RongLibUtils.joinChatRoom(-1, roomId, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                SharePreferencesUtil.putString(MyApplication.getInstance(),"joinChatroomId", roomId);
                Utils.logE(TAG, "rongLog: ????????????????????? id" + roomId);
                /*
                ??????????????????????????????message
                 */
                if(SharePreferencesUtil.getInt(MyApplication.getInstance(),CommonStr.GRADE,1)>=2){
                    String userNickName = SharePreferencesUtil.getString(getContext(), "userNickName", "");
                    LiveExitAndJoinMessage exitAndJoinMessage = new LiveExitAndJoinMessage(userNickName, "0");
                    exitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext(),managerTypeEntity));
                    RongLibUtils.sendMessage(roomId, exitAndJoinMessage);
                }
                /*
                ??????????????????
                 */
                requestTJRed();
                //?????????????????????
                handler.postDelayed(onlinePeopleRunnable,3000);
                //VIP1??????????????????????????????
                handler.postDelayed(vip1JoinRunnable,1000);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Utils.logE(TAG, "rongLog: ????????????????????? errorCode=" + errorCode.getValue());
            }

        });
    }

    private void requestTJRed() {
        String jsonStr = LiveTJRedEntity.getInstance().getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){
            HttpApiUtils.CPnormalRequest(_mActivity, LiveFragment.this, RequestUtil.TJ_YUGAO, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    LiveTJRedEntity.getInstance().setJsonStr(result);
                    handlerTJRedJson(result);
                }

                @Override

                public void onFailed(String msg) {
                }
            });
        }else {
            handlerTJRedJson(jsonStr);
        }

    }

    private void handlerTJRedJson(String result) {
        RedRainEntity redRainEntity = JSONObject.parseObject(result, RedRainEntity.class);
        dataBeanList = redRainEntity.getData();
        selectorGiftRain(dataBeanList);
        findWillOnGoingOrRainingEntity();
        handler.postDelayed(giftRainRunnable,60*1000);
    }

    private RedRainEntity.DataBean  findWillOnGoingOrRainingEntity() {
        Long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
        if(willOnGoingOrRainingEntity==null){
            if(dataBeanList!=null&&dataBeanList.size()>0){
                long curServiceTimeInterval = new Date().getTime()-SharePreferencesUtil.getLong(MyApplication.getInstance(),"shijiancha",0l);
                String giftRainId = SharePreferencesUtil.getString(MyApplication.getInstance(), "giftRainId", "");
                Collections.sort(dataBeanList, new Comparator<RedRainEntity.DataBean>() {
                    @Override
                    public int compare(RedRainEntity.DataBean o1, RedRainEntity.DataBean o2) {
                        return (int) (o1.getEndDate()-o2.getEndDate());
                    }
                });
                for (int i = 0; i < dataBeanList.size(); i++) {
                    RedRainEntity.DataBean dataBean = dataBeanList.get(i);
                    long endDate = dataBean.getEndDate();
                    boolean haveRedId = false;
                    String[] split = giftRainId.split(",");
                    List<String> stringList = Arrays.asList(split);
                    if(stringList.contains(dataBean.getRedId()+"_"+user_id)){
                        haveRedId=true;
//                        break;
                    }
                    if(curServiceTimeInterval<endDate&&!haveRedId){
                        willOnGoingOrRainingEntity = dataBean;
                        break;
                    }
                }
            }
        }
        return willOnGoingOrRainingEntity;
    }


    private void selectorGiftRain( List<RedRainEntity.DataBean> dataBeans) {
        if(null==dataBeans){
            return;
        }
        Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
        for (int i = 0; i < dataBeans.size(); i++) {
            RedRainEntity.DataBean dataBean = dataBeans.get(i);
            long giftRainEndTime = dataBean.getEndDate();
            long sendDate = dataBean.getSendDate();
            String redId = dataBean.getRedId();
            String giftRainId = SharePreferencesUtil.getString(MyApplication.getInstance(), "giftRainId", "");
            long user_id = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
            if(StringMyUtil.isEmptyString(giftRainId)){
                if(giftRainEndTime>new Date().getTime()-shijiancha&&new Date().getTime()-shijiancha>sendDate){
                    startCountDown(redId);
                }
            }else {
                boolean haveRedId = false;
                String[] split = giftRainId.split(",");
                List<String> stringList = Arrays.asList(split);
                if(stringList.contains(redId+"_"+user_id)){
                    haveRedId=true;
                }
                if(!haveRedId){
                    if(giftRainEndTime>new Date().getTime()-shijiancha&&new Date().getTime()-shijiancha>sendDate){
                        startCountDown(redId);
                        break;
                    }
                }
            }
        }
        //TODO ceshi
//        startCountDown(1);
    }
    /**
     * ???????????????
     */
    Runnable tollCountDownRunnable = new Runnable() {
        @Override
        public void run() {
            Long shijiancha = SharePreferencesUtil.getLong(getContext(), "shijiancha", 0l);
            LiveActivity liveActivity = (LiveActivity) getActivity();
            boolean continueCountDown=true;
            //???????????????????????????,?????????????????????????????????
            if (tollCountTime <=0){
                if(autoAnchorSubscribe.equals("1")){
                    //?????????????????????,??????????????????
                    if(autoTollPop==null)autoTollPop = new AutoTollPop(getContext(),LiveFragment.this, AutoTollPop.PopStatus.SHOW_COUNTDOWN);
                    if (position == liveActivity.curPosition) {
                        autoTollPop. requestToll();
                    }
                }else {
                    //?????????????????????, ????????????,??????????????????
                    if(position==liveActivity.curPosition){
                        initAutoTollPop(AutoTollPop.PopStatus.NORMAL);
                    }
//                    toll_constraintLayout.setVisibility(GONE);
                    isNeedToll =true;
                    myjzvd.goOnPlayOnPause();
                }
                continueCountDown=false;
            }else {
                //????????????????????????????????????????????????, ????????????????????????,???????????????30????????????????????????pop.
                tollCountTime = tollEndDate + shijiancha - System.currentTimeMillis();
                int mHour = (int) ((tollCountTime / 1000) / (60 * 60));  //  ???3600 ??????  ????????????
                int mMin = (int) (((tollCountTime / 1000) % (60 * 60)) / 60);//  ???3600 ????????????60 ??????????????????
                int mSecond = (int) ((tollCountTime / 1000) % 60); //  ???60 ??????  ??????????????????
                String str_time;
                str_time =DateUtil.timeMode(mHour)+":"+ DateUtil.timeMode(mMin) + ":" + DateUtil.timeMode(mSecond);
                toll_countdown_tv.setText(str_time);
                if(tollCountTime <=1){
                    tollCountTime =0;
                }
                if(!autoAnchorSubscribe.equals("1")){
                    if((int)tollCountTime/1000==30){
                        if(autoTollPop!=null) autoTollPop.dismiss();
                        autoTollPop = new AutoTollPop(getContext(),LiveFragment.this, AutoTollPop.PopStatus.SHOW_COUNTDOWN);
                        autoTollPop.showAtLocation(ll_live,Gravity.BOTTOM,0,0);
                    }
                }

            }
            tollAmountSubject.onNext(tollCountTime);
            if(continueCountDown){
                handler.postDelayed(tollCountDownRunnable,1000);
            }
        }
    };
    Runnable redRainCountDownRunnable = new Runnable() {
        @Override
        public void run() {
            long shijiancha  = SharePreferencesUtil.getLong(getContext(),"shijiancha",0l);
            if(willOnGoingOrRainingEntity!=null){
                redRainCountTime = willOnGoingOrRainingEntity.getSendDate()+shijiancha-new Date().getTime();//??????????????????
                if(redRainCountTime<=0){
                    initActivityRecyclerRedCountdown("00:00:00");
//
                    if(redRainCountTimePop!=null&&redRainCountTimePop.isShowing()){
                        redRainCountTimePop.hour1_tv.setText("0");
                        redRainCountTimePop.hour2_tv.setText("0");
                        redRainCountTimePop.minute1_tv.setText("0");
                        redRainCountTimePop.minute2_tv.setText("0");
                        redRainCountTimePop.second1_tv.setText("0");
                        redRainCountTimePop.second2_tv.setText("0");
                    }

                }else {
                    int mHour = (int) ((redRainCountTime / 1000) / (60 * 60));  //  ???3600 ??????  ????????????
                    int mMin = (int) (((redRainCountTime / 1000) % (60 * 60)) / 60);//  ???3600 ????????????60 ??????????????????
                    int mSecond = (int) ((redRainCountTime / 1000) % 60); //  ???60 ??????  ??????????????????
                    String str_time;
                    str_time =DateUtil.timeMode(mHour)+":"+ DateUtil.timeMode(mMin) + ":" + DateUtil.timeMode(mSecond);
//                    red_rain_countDown_tv.setText(str_time);
                    initActivityRecyclerRedCountdown(str_time);
                    if(redRainCountTime <=1){
                        redRainCountTime =0;
                    }
                    if(redRainCountTimePop!=null&&redRainCountTimePop.isShowing()){
                        redRainCountTimePop.hour1_tv.setText(str_time.substring(0,1));
                        redRainCountTimePop.hour2_tv.setText(str_time.substring(1,2));
                        redRainCountTimePop.minute1_tv.setText(str_time.substring(3,4));
                        redRainCountTimePop.minute2_tv.setText(str_time.substring(4,5));
                        redRainCountTimePop.second1_tv.setText(str_time.substring(6,7));
                        redRainCountTimePop.second2_tv.setText(str_time.substring(7));
                    }
                }
            }
            handler.postDelayed(this,1000);
        }
    };

    Runnable giftRainRunnable = new Runnable() {
        @Override
        public void run() {
            selectorGiftRain(dataBeanList);
            handler.postDelayed(giftRainRunnable,10*1000);
        }
    };
    Runnable onlinePeopleRunnable = new Runnable() {
        @Override
        public void run() {
            getChatRoomNum();
            handler.postDelayed(this,3000);
        }
    };
    /**
     * ??????????????????vip1????????????????????????
     */
    Runnable vip1JoinRunnable = new Runnable() {
        @Override
        public void run() {
            LiveMessageModel liveMessageModel = new LiveMessageModel();
            liveMessageModel.setManagerType("0");//????????????
            String levelUrl = Utils.getLevelUrl(1);//??????1
            liveMessageModel.setLevelIcon(levelUrl);
            liveMessageModel.setUserName(generateAccount(8));//?????????
            liveMessageModel.setStatus("0");//???????????????
            liveChatFragment.addJoinItem(liveMessageModel);
            handler.postDelayed(this,1000);
        }
    };

    /**
     * ???????????????
     */
    Runnable rewardRunnable = new Runnable() {
        @Override
        public void run() {
            reward_countdown_tv.setText(Utils.getString(R.string.????????????)+(rewardCountdownTime)+"s");
            rewardCountdownTime--;
            if(rewardCountdownTime == -2){
                reward_linear.setVisibility(GONE);
                rewardCountdownTime=4;
                handler.removeCallbacks(this);
            }else {
                handler.postDelayed(this,1000);
            }
        }
    };
    /** ??????????????????userInfoJson ??????
     * @param messageModel
     * @param userInfoJson
     */
    private boolean initUserInfo(LiveMessageModel messageModel, String userInfoJson,String sendUserId) {
        if (StringMyUtil.isNotEmpty(userInfoJson)&&Utils.isJson(userInfoJson)) {
            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
            String userId = jsonObject.getString("userId");
            String name = jsonObject.getString("name");
            String level = jsonObject.getString("level");
            if(Utils.isNotInt(level)){
                return true;
            }
            String portrait = jsonObject.getString("portrait");
            String platUserId = jsonObject.getString("platUserId");//??????userName
            String managerType = jsonObject.getString("managerType");//1 ?????? 2?????? 3??????
            String medalIcon = jsonObject.getString("medalIcon");// ??????icon URL
            String levelIcon = jsonObject.getString("levelIcon");// ????????????URL
            String titleIcon = jsonObject.getString("titleIcon");// ??????URL ????????????????????????????????????
            String levelSVGA = jsonObject.getString("levelSVGA");// ????????????URL
            String mountSVGA = jsonObject.getString("mountSVGA");// ????????????URL
            if(StringMyUtil.isNotEmpty(medalIcon)&&!medalIcon.contains(Utils.getFirstImgurl(MyApplication.getInstance()))){
                medalIcon = "";
            }
            if(StringMyUtil.isNotEmpty(levelIcon)&&!levelIcon.contains(Utils.getFirstImgurl(MyApplication.getInstance()))){
                levelIcon = "";
            }
            if(StringMyUtil.isNotEmpty(titleIcon)&&!titleIcon.contains(Utils.getFirstImgurl(MyApplication.getInstance()))){
                titleIcon = "";
            }
            if(StringMyUtil.isNotEmpty(levelSVGA)&&!levelSVGA.contains(Utils.getFirstImgurl(MyApplication.getInstance()))){
                levelSVGA = "";
            }
            if(StringMyUtil.isNotEmpty(mountSVGA)&&!mountSVGA.contains(Utils.getFirstImgurl(MyApplication.getInstance()))){
                mountSVGA = "";
            }
            String mountName = jsonObject.getString("mountName");// ?????????
            messageModel.setUserName(StringMyUtil.initEmptyStr(name));
            messageModel.setLevel(StringMyUtil.initEmptyStr(level,"1"));
            messageModel.setPortrait(StringMyUtil.initEmptyStr(portrait));
            messageModel.setManagerType(StringMyUtil.initEmptyStr(managerType));
            messageModel.setUserInfoJson(StringMyUtil.initEmptyStr(userInfoJson));
            messageModel.setUserInfoJsonUserId(StringMyUtil.initEmptyStr(userId,"1"));
            messageModel.setPlatUserId(StringMyUtil.initEmptyStr(platUserId));
            messageModel.setMedalIcon(StringMyUtil.initEmptyStr(medalIcon));
            messageModel.setLevelIcon(StringMyUtil.initEmptyStr(levelIcon));
            messageModel.setTitleIcon(StringMyUtil.initEmptyStr(titleIcon));
            messageModel.setLevelSVGA(StringMyUtil.initEmptyStr(levelSVGA));
            messageModel.setMountSVGA(StringMyUtil.initEmptyStr(mountSVGA));
            messageModel.setMountName(StringMyUtil.initEmptyStr(mountName));
//          messageModel.setUserInfoJson(jsonObject.getString("zkCode"));
            if(messageModel.getManagerType().equals("1") && Utils.isNotLong(sendUserId)){
                return true;
            }
            return  false;
        }
        return true;
    }

    @Override
    protected void initEventAndData() {

        /**
         * ?????????????????? ????????? ???????????? ?????????????????? ????????????????????????livedata???
         */

        mViewModel.reqChatUsereInfo();
//        mViewModel.reqGiftData();
        /**
         * ???????????? ????????????  ???????????? ????????????
         */
//        mViewModel.reqActivityRankData();

        /**
         * ??????????????????item????????????
         */
        mChatUserAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LogUtils.e(""+mChatUserList.get(position));
            }
        });
    }
    @Override
    public void onSupportVisible() {
        if(null!=marqueeTv){
            marqueeTv.startScroll();
        }
        visibleCount++;
        if (visibleCount!=1) {
            return;
        }


        boolean showClearScreen = SharePreferencesUtil.getBoolean(MyApplication.getInstance(), CommonStr.SHOW_CLEAR_SCREEN, true);
        if(showClearScreen){
            clear_screen_iv.setVisibility(View.VISIBLE);
        }else {
            clear_screen_iv.setVisibility(GONE);
        }

        initVideoPlayer();

        refreshPeoples();

        requestManageType(true);

        /**
         *
         * ?????????????????????
         */
        initRongYun();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }
    /**
     ???????????????????????????
     */
    private void getChatRoomNum() {
        RongIMClient.getInstance().getChatRoomInfo(roomId, 0, ChatRoomInfo.ChatRoomMemberOrder.RC_CHAT_ROOM_MEMBER_ASC, new RongIMClient.ResultCallback<ChatRoomInfo>() {
            @Override
            public void onSuccess(ChatRoomInfo chatRoomInfo) {
                //??????????????????
                mLiveData.setRealChangeNum(chatRoomInfo.getTotalMemberCount());
                refreshPeoples();
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    //?????????????????????????????????????????????,
    public  String generateAccount(int length) {
        String val = "";
        Random random = new Random();
        //??????length??????????????????????????????
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //????????????????????????
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //???????????????????????????????????????
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return Utils.getString(R.string.??????)+val;
    }
    private void requestTurnOnOff() {
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.TURNTABLE_COUNT, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                turntableStatus = jsonObject.getString("yqzpStatus");
                if(cusNotePop!=null){
                    cusNotePop.setTurntableStatus();
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
    /**
     * ?????????videoPlayer
     */
    private void initVideoPlayer() {
        disposable = Observable.interval(300, TimeUnit.MILLISECONDS).take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    if (aLong == 1) {
                        if(!isNeedToll){
                            myjzvd.startVideo();
                            LogUtils.e(Utils.getString(R.string.????????????) + mLiveData.getLiveUrl());
                        }
                        //????????????????????????
                        initFollowPopTimer();
                    }
                }).doOnComplete(() -> {
                    if (disposable != null) {
                        disposable.dispose();
                    }
                })
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe();
    }

    /**
     *????????????
     */
    public void initTurntablePop(){
        if(rulePop ==null){
            rulePop = new RulePop(getContext(),true,Utils.checkImageUrl("/rules/zp_rule.html"),Utils.getString(R.string.????????????));
//            rulePop = new RulePop(getContext(),true,"file:///android_asset/zp_rule.html",Utils.getString(R.string.????????????));
        }
        rulePop.showAtLocation(iv_close, Gravity.CENTER,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }
    /**
     * ?????????5??????????????????pop
     */
    private void initFollowPopTimer() {
        if(showFollowTimer ==null){
            showFollowTimer = new Timer();
        }
        if(showFollowTimer != null && showFollowPopTask != null){
            showFollowPopTask.cancel();
        }
        showFollowPopTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LiveActivity liveActivity = (LiveActivity) getActivity();
                        if(liveActivity!=null&&!liveActivity.isDestroyed()&&!liveActivity.isFinishing()){
                            if (position == liveActivity.curPosition) {
                                if(mLiveData.getIsFollow()==0){
                                    new AutoFollowPop(getContext(), LiveFragment.this, mLiveData,tv_lottery_qishu);
                                }
                            }
                        }

                    }
                });
            }
        };
        showFollowTimer.schedule(showFollowPopTask,5*60*1000);
    }

    private void UpdateUi() {
        /**
         * ??????????????????
         */
        updateCount++;
        mViewModel.getIsShowLiveData().observe(this,(Boolean b)->{

        });
        mViewModel.getOpenLottery().observe(this, (LiveLotteryEntity liveLotteryEntity) -> {
            String typeName = liveLotteryEntity.getTypeName();
            if (typeName != null) {
                tv_lottery_name.setText(typeName);
            }
            String openQishu = liveLotteryEntity.getOpenQishu();
            String currentQishu = liveLotteryEntity.getCurrentQishu();

            if (openQishu != null&&currentQishu!=null) {
                String subOpenQishu = openQishu.substring(0, openQishu.length() - 4);
                String subCurrentQiShu = currentQishu.substring(0, currentQishu.length() - 1);
                tv_lottery_qishu.setText(openQishu);
//                long l = Long.parseLong(subOpenQishu);
//                if(StringMyUtil.isEmptyString(nowQishu)||nowQishu.equals((l -1)+"")){
                if(StringMyUtil.isNotEmpty(subCurrentQiShu)){
                    if(Long.parseLong(subCurrentQiShu)-1==Long.parseLong(subOpenQishu)){
                        nowQishu=subOpenQishu;
                        YoYo.with(Techniques.SlideInRight)
                                .duration(700)
                                .playOn(cos_lottrry_open_result);
                        cos_lottrry_open_result.setVisibility(View.VISIBLE);
                        if(openResultTimer!=null){
                            openResultTimer.cancel();
                        }
                        openResultTimer   =new Timer();
                        openResultTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if(getActivity() != null &&!getActivity().isFinishing() && !getActivity().isDestroyed()){
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(cos_lottrry_open_result!=null){
                                                YoYo.with(Techniques.SlideOutRight)
                                                        .duration(700)
                                                        .playOn(cos_lottrry_open_result);
                                                cos_lottrry_open_result.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    });
                                }

                            }
                        },1000*10);
                    }
                }
            }
           /* if (liveLotteryEntity.getCurrCountTime() != null) {
                tv_countdown.setText(liveLotteryEntity.getCurrCountTime());
            }*/
/*            if (StringMyUtil.isNotEmpty(typeName)) {
                if(!typeName.equals()){
                    currentTypeNamcurrentTypeNamee=typeName;
                    GlideLoadViewUtil.LoadCircleView(_mActivity, Utils.CPImageUrlCheck(_mActivity, .getImliveLotteryEntityage_url()), iv_lottery);
                }
            }*/
//            if(updateCount==1){
            GlideLoadViewUtil.LoadNormalView(_mActivity, Utils.CPImageUrlCheck(_mActivity, liveLotteryEntity.getImage_url()), iv_lottery);
//            }
            boolean xian = liveLotteryEntity.isXian();
            if(xian){
                xianIv.setVisibility(View.VISIBLE);
            }else {
                xianIv.setVisibility(GONE);
            }
            if (liveLotteryEntity.getOpenMulNoList() != null) {
                mOpenNoMulList.clear();
                mOpenNoMulList.addAll(liveLotteryEntity.getOpenMulNoList());
                mOpenNoMulAdapter.notifyDataSetChanged();

            }
        });
        mViewModel.getCountDownLiveData().observe(this,s ->  tv_countdown.setText(s));
        /**
         * ????????????????????????
         */
        mViewModel.getChatUserLiveData().observe(this, (List<ChatUserEntity.MemberHeadPortraitListBean> list) -> {
            if (list != null) {
            /*    mChatUserList.clear();
                mChatUserList.addAll(list);*/
                userPortraitList.clear();
                userPortraitList.addAll(list);
//                mChatUserAdapter.notifyDataSetChanged();
            }

        });
        /**
         * ??????refreshRoomUrl??????
         */
        mViewModel.getRefreshRoomLiveData().observe(this, (RefreshUrlEntity refreshUrlEntity) -> {
            if (refreshUrlEntity.getIsLiving() == 1) {
                /**
                 * ???????????????url   ????????????
                 */

                myjzvd.setUp(refreshUrlEntity.getLiveUrl(), mLiveData.getAnchorAccount(), "refresh", myjzvd.SCREEN_NORMAL, JZMediaIjk.class);
                if(!isNeedToll){
                    myjzvd.startVideo();
                }
            } else {
                /**
                 * ???????????????
                 */
                linear_liveroom_tag.setVisibility(View.VISIBLE);
            }
        });
/*        mViewModel.getIsShowLiveData().observe(this,(Boolean b )->{

        });*/
    }

    /**
     ???????????????????????????
     */

    private void  refreshPeoples(){
        long totalPeople = getTotalPeople();
        initOnlinePeople(totalPeople);
    }
    private void initOnlinePeople(long totalPeople){


        String unit ="";
        float total = totalPeople *1.0f;
        String title = "";
        if(totalPeople > 10000){
            unit = Utils.getString(R.string.???);
            total = total/10000.f;
            title = String.format(Utils.getString(R.string.f?????????),total,unit);
        }else {
            title = String.format(Utils.getString(R.string.d?????????),totalPeople);
        }

        if(tv_num!=null){
            tv_num.setText(title);
        }
        if(userPortraitList == null||userPortraitList.size()==0){
            return;
        }
        /*
         ???????????? ??????
         */
        while (mChatUserList.size()!=totalPeople && mChatUserList.size()<50){
            if(mChatUserList.size()< totalPeople ){
                int index = new Random().nextInt(userPortraitList.size());
                mChatUserList.add(0,userPortraitList.get(index));
            }else {
                if(mChatUserList.size()!=0){
                    mChatUserList.remove(mChatUserList.size()-1);
                }
            }
        }
        mChatUserAdapter.notifyDataSetChanged();


    }
    private long getTotalPeople(){

        int baseNum = mLiveData.getBasePeopleNum();
        //??????????????????
        int totalChangeNum = 0;
        String limitChangeNumStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.ANCHOR_CHANGE_NUMBER, "1");

        int muiltyBy = 1;
        if (limitChangeNumStr .contains("-")) {
            String[] arr = limitChangeNumStr.split("-");

            if (arr.length > 0) {
                String from = arr[0];
                String to = arr[1];
                muiltyBy= new Random().nextInt(Integer.parseInt(to))+ Integer.parseInt(from);
//                muiltyBy = Integer.parseInt(from);

            }
        }else{
            muiltyBy = Integer.parseInt(limitChangeNumStr);
        }
        if (muiltyBy < 1) {
            muiltyBy = 1;
        }
        totalChangeNum += mLiveData.getRealChangeNum() * muiltyBy;
        return (totalChangeNum + baseNum);
    }

    private long dealWithRcUserId(String anchorMemberId) {
        if(anchorMemberId.contains("_")){
            String[] split = anchorMemberId.split("_");
            List<String> stringList = Arrays.asList(split);
            String s = stringList.get(stringList.size() - 1);
            anchorMemberId=s;
        }
        return Long.parseLong(anchorMemberId);
    }

    private  Long calculatePeopleWithPeopleId(long peopleId,String limitString){
        long baseNum = 0;
        if(limitString.equals("0-0")){
            return baseNum;
        }
        if (limitString.contains("-")) {
            String[] arr = limitString.split("-");
            if (arr.length == 2) {
                String from = arr[0];
                String to = arr[1];
                if (Long.parseLong(from) > 0 && Long.parseLong(to) > 0 && Long.parseLong(from) <= Long.parseLong(to)) {
                    baseNum = peopleId % Long.parseLong(to);
                    if (baseNum < Long.parseLong(from)) {
                        baseNum = Long.parseLong(from);
                    }
                }
            }
        }
        return baseNum;
    }
    /**
     * ????????????????????????  ???????????? account  ?????? url
     *
     * @param playEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RefreshUrlEvent(PlayEvent playEvent) {
        if (playEvent.getStatus() == PlayEvent.PLAYTYPE.ERROR.getValue() && playEvent.getAnchorAccount().equals(mLiveData.getAnchorAccount())) {
            //   if (playEvent.getStatus()== PlayEvent.PLAYTYPE.ERROR.getValue()&&playEvent.getAnchorAccount().equals("9_2115088_1581901140")){
            mViewModel.RefreshRoomUrl(mLiveData.getAnchorAccount(),mLiveData.getRemoteLiveManagementId(),mLiveData.getLiveRoomId());
        }
        /**
         * ????????????url   ???????????????  isLiving  ==1   ??????url??????????????????   ????????????
         */
        if (playEvent.getStatus() == PlayEvent.PLAYTYPE.XIABO.getValue() && playEvent.getAnchorAccount().equals(mLiveData.getAnchorAccount())) {
            linear_liveroom_tag.setVisibility(View.VISIBLE);
        }
    }

    /**
     *????????????????????????
     * @param followEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateIsFollow(FollowEvenModel followEvenModel){
        if(mLiveData!=null){
            if((mLiveData.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    mLiveData.setIsFollow(1);
                    iv_collect.setImageResource(R.drawable.ygz_iocn);
                    iv_collect.setVisibility(View.GONE);
                }else {
                    mLiveData.setIsFollow(0);
                    iv_collect.setVisibility(View.VISIBLE);
                    iv_collect.setImageResource(R.drawable.live_follow);
                }
            }
        }

    }

    /**
     * ???????????????visibleCount??????(viewPager????????????????????????)
     * @param resetVisibleEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetVisible(ResetVisibleEvenModel resetVisibleEvenModel){
        isSkip =false;
        visibleCount=0;

    }

    /**
     * ??????????????????
     * @param updateGiftAmountModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateGiftAmount(UpdateGiftAmountModel updateGiftAmountModel){
        if(mLiveViewModel==null||mLiveData==null){
            return;
        }
        if((mLiveData.getAnchorMemberId()+"").equals(updateGiftAmountModel.getId())){
            mLiveData.setGiftAmount(updateGiftAmountModel.getGiftAmount()+"");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSkip(SkipModel skipModel){
        isSkip=skipModel.isKip();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void rongConnect(RongConnectModel connectModel){
        LiveActivity liveActivity = (LiveActivity) getActivity();
        if(position==liveActivity.curPosition){
            LiveMessageModel liveMessageModel = new LiveMessageModel();
            liveMessageModel.setObjectName(CommonStr.RONG_CONNECT_STATUS);
            liveMessageModel.setRongConnect(connectModel.isConnect());
            /**
             *    ????????????item????????????
             */

//            liveChatFragment.addItem(liveMessageModel);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian (HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }

    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        if(null!=xianIv){
            if(null==mLiveData){
                return;
            }
            if(idList.contains(mLiveData.getCpId()+"")){
                xianIv.setVisibility(View.VISIBLE);
            } else {
                xianIv.setVisibility(GONE);
            }
        }
    }
    /**
     * ????????????
     * @param view
     */
    @OnClick({/*R.id.rabtn_zx, R.id.rabtn_qy,*/ R.id.iv_bottomgift, R.id.iv_game, R.id.iv_centre, R.id.iv_lottery,
            R.id.iv_competition, R.id.iv_close, R.id.ll_gift, R.id.iv_avatar, R.id.iv_collect, /*R.id.iv_pack*/R.id.notice_textview,
            R.id.dismiss_open_result_iv,R.id.clear_screen_iv,R.id.share_iv,R.id.toll_constraintLayout,
            R.id.turntable_iv,R.id.count_down_fall_frameLayout,R.id.reward_countdown_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reward_countdown_tv:
                /**
                 * ??????pop
                 */
                initRewardAnchorPop();
                break;
            case R.id.count_down_fall_frameLayout:
                /**
                 *    ????????????????????????????????????5??????????????????, ??????????????????????????????
                 */

                if(Utils.isNotFastClick()){
                    mViewModel.reqCountDownData(fail_loading_iv);
                    mViewModel.reqLastLottery();
                }
                break;
            /**
             * ???????????????
             */
            case R.id.share_iv:
                initQrCodePop();
                liveQrCodePop.showAtLocation(iv_close, Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                break;
            /**
             * ???????????????
             */
            case R.id.clear_screen_iv:
                clear_screen_iv.setVisibility(GONE);
                SharePreferencesUtil.putBoolean(MyApplication.getInstance(),CommonStr.SHOW_CLEAR_SCREEN,false);
                break;
            /**
             * dismiss????????????
             */
            case R.id.dismiss_open_result_iv:
                cos_lottrry_open_result.setVisibility(View.INVISIBLE);
                break;
            /**
             * ????????????pop
             */
            case R.id.iv_bottomgift:
                OpenGiftPop();
                break;
            /**
             * ????????????pop
             */
            case R.id.iv_game:
                OpenLotteryListPop();
                break;
            case R.id.iv_lottery:
                /**
                 * ??????pop
                 */
                OpenBetRulePop(mLiveData.getCpId());
                break;
            /**
             * ????????????pop
             */
            case R.id.iv_centre:
                /**
                 * ??????pop
                 */
                OpenNotePop();
                /**
                 * ??????????????????
                 */
                requestTurnOnOff();

                break;
/*            case R.id.iv_competition:
                OpenActivityRankPop();
                break;*/
            /**
             * ???????????????
             */
            case R.id.iv_close:
                mLiveViewModel.getPageState().setValue(CLOSE);
                break;
            /**
             * ???????????????
             */
            case R.id.ll_gift:
//                RouteUtils.start2PaihangActivity(_mActivity, RankTypeEnum.GIFT);
                initLiveRankDialogFragment();
                break;
            /**
             * ????????????/??????????????????
             */
            case R.id.iv_avatar:
                if(managerTypeEntity!=null){
                    boolean isSuperManager = managerTypeEntity.getIsSuperRoomManager().equals("1");
                    new FollowPop(getContext(), LiveFragment.this,LiveFragment.this,isSuperManager,mLiveData.getIsUseToy());
                }
                break;
            case R.id.iv_collect:
                /**
                 * ??????
                 */
                requestFollow();
                break;
            /**
             * ?????????
             */
            case R.id.notice_textview:
                startActivity(new Intent(getContext(),MineMessageActivity.class));
                break;
            /**
             * ?????????????????????linear
             */
            case R.id.toll_constraintLayout:
//                if(tollCountTime<=30*1000){
                if(tollCountTime>0){
                    initAutoTollPop(AutoTollPop.PopStatus.SHOW_COUNTDOWN);
                }else {
                    initAutoTollPop(AutoTollPop.PopStatus.NORMAL);
                }
//                }
                break;

            case R.id.turntable_iv:
                /**
                 * ????????????
                 */
                showTurnblePop();
                break;
            default:
                break;
        }
    }

    private void initRewardAnchorPop() {
        RewardAnchorPop rewardAnchorPop = new RewardAnchorPop(getContext(),true,this);
        rewardAnchorPop.showAtLocation(iv_close, Gravity.BOTTOM,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }

    private void requestFollow() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorMemberId",mLiveData.getAnchorMemberId()+"");
        HttpApiUtils.CpRequest(getActivity(), LiveFragment.this, RequestUtil.FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                //{"message":"success","status":"success"}
                ToastUtil.showToast(Utils.getString(R.string.????????????));
                String zkCode = SharePreferencesUtil.getString(MyApplication.getInstance(), "zkCode", "");
                //????????????????????????
                LiveFollowMessage liveFollowMessage = new LiveFollowMessage(zkCode, RongLibUtils.setUserInfo(MyApplication.getInstance(),managerTypeEntity));
                RongLibUtils.sendMessage(roomId,liveFollowMessage);
                EventBus.getDefault().postSticky(new FollowEvenModel(mLiveData.getAnchorMemberId()+"",true));
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }

    /**
     * ????????????pop
     */
    private void showTurnblePop() {
        turntablePop = new TurntablePop(getContext(),true, LiveFragment.this);
        turntablePop.showAtLocation(turntable_iv, Gravity.BOTTOM,0,0);
    }


    /**
     *??????pop
     */
    private void initQrCodePop() {
        if(liveQrCodePop==null){
            liveQrCodePop = new LiveQrCodePop(getContext(),false, LiveFragment.this,mLiveData);
        }
    }

    private void initLiveRankDialogFragment() {
//        LiveRankDialogFragment liveRankDialogFragment = new LiveRankDialogFragment(LiveFragment.this);
        LiveRankDialogFragment liveRankDialogFragment = new LiveRankDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("anchorAccount",mLiveData.getAnchorAccount());
        liveRankDialogFragment.setArguments(bundle);
        liveRankDialogFragment.show(getChildFragmentManager(),"liveRankDialogFragment");
    }

    /**
     * ???????????????????????????
     */
    private void OpenActivityRankPop() {
        if (mCusRankPop == null) {
            mCusRankPop = new CusActivityRankPop(_mActivity, this);
        }
        mCusRankPop.initData();
        mCusRankPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        mCusRankPop.backgroundAlpha(_mActivity, 0.5f);
        mCusRankPop.setOnMoreClickListener((PackType type) -> {
            mCusRankPop.dismiss();
            switch (type) {
                case QY:
//                    startActivity(new Intent(getContext(), InviteAndMakeMoneyActivity.class));
                    InviteAndMakeMoneyActivity.startAty(getContext(),roomId);
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                case ZX:
//                    showPackPopType(ZX);
                    ZhuangXiangRedActivity.startAty(getContext(),roomId);
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                case TJ:
                    showPackPopType(TJ);
                    break;
                default:
                    break;
            }
        });
    }

    public void showPackPopType(PackType packType) {
//        if (cusPackMorePop==null){
        cusPackMorePop = new CusPackMorePop(_mActivity, this);
//        }
        cusPackMorePop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        cusPackMorePop.showType(packType);
//        mViewModel.reqHbStatus(packType);
    }
    /**
     * ?????????pop ?????????
     */
    public void OpenPackPop(String redId, RedStatus redStatus, String qbPrice, RedType redType, String roomId, String failInfo) {
        mCusPackPop = new CusPackPop(redId, _mActivity, this, redStatus, qbPrice, redType, roomId, failInfo);
        if(ll_live!=null){
            mCusPackPop.showAtLocation(ll_live, Gravity.CENTER, 0, 0);
            if(redStatus == RedStatus.SUCCESS && redType == RedType.TJ){
                String userNickName = SharePreferencesUtil.getString(MyApplication.getInstance(), "userNickName", "");
                LiveRedMessage liveRedMessage = new LiveRedMessage(RongLibUtils.setUserInfo(MyApplication.getInstance(), managerTypeEntity), qbPrice, redId, userNickName, 4);
                RongLibUtils.sendMessage(roomId,liveRedMessage);
            }
        }
        mCusPackPop.setmOnItemClickListener((View v, CusPackPop.CLICKTYPE clicktype) -> {
            switch (clicktype) {
                case OPEN:
//                    ToastUtils.showToast(Utils.getString(R.string.???????????????));
                    break;
                case UNLOCK:
//                    ToastUtils.showToast(Utils.getString(R.string.????????????));
                    break;
                case UNLOCK_GAMERULES:
//                    ToastUtils.showToast(Utils.getString(R.string.??????????????????));
                    break;
                case UNLOCK_RANK:
//                    ToastUtils.showToast(Utils.getString(R.string.???????????????1));
                    break;
                case PACK_RANK:
//                    ToastUtils.showToast(Utils.getString(R.string.???????????????2));
                    break;
            }
        });
    }


    /**
     * ?????????pop ?????????(????????????)
     */
    public void OpenPackPop(String redId, RedStatus redStatus, RedType redType, String roomId) {
        mCusPackPop = new CusPackPop(redId, _mActivity, this, redStatus, redType);
        mCusPackPop.setRoomId(roomId);
        mCusPackPop.showAtLocation(ll_live, Gravity.CENTER, 0, 0);
        mCusPackPop.setmOnItemClickListener((View v, CusPackPop.CLICKTYPE clicktype) -> {
            switch (clicktype) {
                case OPEN:
                    break;
                case UNLOCK:
                    break;
                case UNLOCK_GAMERULES:
                    break;
                case UNLOCK_RANK:
                    break;
                case PACK_RANK:
                    break;
            }
        });
    }

    /**
     * ????????????pop
     */
    private void OpenGiftPop() {
//        if (cusGiftPop == null) {
        cusGiftPop = new CusGiftPop(_mActivity, this, 1, mLiveData, giftSvgaMage,mLiveData.getIsUseToy());
//        }
        cusGiftPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        mViewModel.setShowingAmount(true);
    }


    /**
     * Open ????????????Pop
     */
    private void OpenLotteryListPop() {
//        if (null == cusLotteryListPop) {
        cusLotteryListPop = new CusLotteryListPop(_mActivity, this);
//        }
        cusLotteryListPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        /**
         * ????????????????????? ????????????????????? ??????initCpId
         */
        cusLotteryListPop.setmOnItemClickListener((long cpId) -> {
            cusLotteryListPop.dismiss();
            if (GameTypeEnum.LIVESHOP == GameTypeEnum.valueOf(cpId)) {
                startActivity(new Intent(getContext(), LiveShoppingActivity.class));
                EventBus.getDefault().postSticky(new SkipModel(true));
            } else {
                nowQishu="";
                mViewModel.initCpId(cpId,mLiveData.getLiveRoomId());
                OpenBetRulePop(mLiveData.getCpId());
            }

        });
    }

    /**
     * Open ??????pop
     */
    private void OpenBetRulePop(long cpId) {
//        if (cusBetRulePop == null) {
        cusBetRulePop = new CusBetRulePop(_mActivity, this);
//        }
        cusBetRulePop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        mViewModel.setShowingAmount(true);
        int game =mViewModel.getCpLiveData().getValue().getGame();
        String typeName = mViewModel.getCpLiveData().getValue().getTypeName();
        customPopupWindow.initGameRule(getContext(),game,typeName,0,true);
        /**
         * ??????????????????
         */
        cusBetRulePop.setmOnItemClickListener((CusBetRulePop.CLICKTYPE clickType) -> {
            switch (clickType) {
                case BET:
                    /**
                     * ???????????????????????? ????????????????????????pop
                     */

                    BetPopAllModel betPopAllModel = mViewModel.getBetPopAllData().getValue();
                    if(betPopAllModel==null){
                        ToastUtil.showToast(Utils.getString(R.string.?????????????????????????????????????????????????????????));
                        return;
                    }
                    List<BetPopAllModel.BetPopChildModel> betPopChildList = betPopAllModel.getBetPopChildModelList();
                    int cout_select = 0;
                    for (int i = 0; i < betPopChildList.size(); i++) {
                        if (betPopChildList.get(i).isSelect()) {
                            cout_select++;
                        }
                    }
                    if (cout_select==0){
                        ToastUtil.showToast(Utils.getString(R.string.?????????????????????));
                        return;
                    }
                    int ma = 0;
                    List<BetJoinAllModel.BetJoinMaModel> maList = mViewModel.getBetJoinAllLiveData().getValue().getBetJoinMaModelList();
                    for (int i =0;i<maList.size();i++){
                        if (maList.get(i).isCurrent()){
                            ma = maList.get(i).getDanjia();
                        }
                    }
                    if (ma==0){
                        ToastUtil.showToast(Utils.getString(R.string.?????????????????????));
                        return;
                    }
                    cusBetRulePop.dismiss();
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    OpenBetJoinPop();
                    break;
                case RECHARGE:
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest.equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(getContext(),getActivity());
                    }else {
                        startActivity(new Intent(getContext(), RechargeActivity.class));
                    }
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                case SELECTMA:
                    cusBetRulePop.dismiss();
                    OpenSetMaPop();
                    break;
                case CHANGE:
                    OpenLotteryListPop();
                    cusBetRulePop.dismiss();
                    break;
                case BET_NOTE:
                    LiveBetNoteActivity.startAty(getContext(), mViewModel.getCpLiveData().getValue().getGame(),  mViewModel.getCpLiveData().getValue().getTypeId());
                    isSkip=true;
                    break;
                case OPEN_RESULT:
                    OpenBetResultPop();
                    break;
                case GAME_RULE:
                    customPopupWindow.showGameRulePop(getActivity(),true);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Open SetMaPop
     */
    private void OpenSetMaPop() {
        if (cusSetMaPop == null) {
            cusSetMaPop = new CusSetMaPop(_mActivity, this);
        }
        cusSetMaPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        cusSetMaPop.setmOnItemClickListener((CusSetMaPop.CLICKTYPE clickType) -> {
            switch (clickType) {
                case CLOSE:
                    cusSetMaPop.dismiss();
                    break;
                case SETCUSMA:
                    OpenSetCusMaDialog();
                    break;
                case CONFIRM:
                    cusSetMaPop.dismiss();
                    OpenBetRulePop(mLiveData.getCpId());
                    break;
            }
        });
    }

    /**
     * ???????????????dialog
     */
    private void OpenSetCusMaDialog() {
        CusSetMaDialog cusSetMaDialog = new CusSetMaDialog.Builder(_mActivity)
                .setTitle(Utils.getString(R.string.?????????????????????))
                .setNo(Utils.getString(R.string.??????), v -> {
                    Utils.hideSoftKeyBoard(getActivity());
                })
                .setYes(Utils.getString(R.string.??????), (View v, int value) -> {
                    mViewModel.setCusMa(value);
                    Utils.hideSoftKeyBoard(getActivity());

                })
                .create(1);
        cusSetMaDialog.show();
    }

    /**
     * Open BetJoinPop
     */
    private void OpenBetJoinPop() {
//        if (cusBetJoinPop == null) {
//            cusBetJoinPop = new CusBetJoinPop(_mActivity, this);
//        }
//        cusBetJoinPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
//        if (cusBetJoinOldPop == null) {
        cusBetJoinOldPop = new CusBetJoinOldPop(_mActivity, this,mLiveData);
//        }
        cusBetJoinOldPop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
    }

    /**
     * Open NotePop
     */
    private void OpenNotePop() {
//        if (cusNotePop == null) {
        cusNotePop = new CusNotePop(_mActivity, this,managerTypeEntity);
//        }
        cusNotePop.showAtLocation(ll_live, Gravity.BOTTOM, 0, 0);
        cusNotePop.setmOnItemClickListener((CusNotePop.CLICKTYPE clickType) -> {
            cusNotePop.dismiss();
            switch (clickType) {
                case OPENRESULT:
                    OpenBetResultPop();
                    break;
                case BETRESULT:
                    LiveBetNoteActivity.startAty(getContext(), mViewModel.getCpLiveData().getValue().getGame(),  mViewModel.getCpLiveData().getValue().getTypeId());
                    isSkip = true;
                    break;
                case RECHARGECENTER:
                    //??????
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest .equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(getContext(),getActivity());
                    }else {
                        startActivity(new Intent(getContext(), RechargeActivity.class));
                    }
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                case PRILETTER:
                    //??????
                    startActivity(new Intent(getContext(), MineMessageActivity.class));
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                case TASKCENTER:
                    //?????????
                    OpenActivityRankPop();
                    break;
                case FORBIDDEN:
                    //????????????
                    openForbiddenPop();
                    break;
                case TURNTABLE:
                    showTurnblePop();
                    //??????
                    break;
                case EQUIPMENT:
                    Intent intent = new Intent(getContext(), MyEquipmentActivity.class);
                    startActivity(intent);
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    //??????
                    break;
                case DIAMOND:
                    //??????
                    startActivityForResult(new Intent(getContext(),DiamondActivity.class),TO_DIAMOND_CODE);
                    EventBus.getDefault().postSticky(new SkipModel(true));
                    break;
                default:
                    break;
            }
        });
    }
    private void openForbiddenPop() {
        ForbiddenPop forbiddenPop = new ForbiddenPop(getContext(), false,mLiveData,this);
        forbiddenPop.showAtLocation(ll_live,Gravity.BOTTOM,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }

    /**
     *????????????Pop
     */
    private void OpenBetResultPop() {
//        int game = 2;//mViewModel.getCpLiveData().getValue().getGame();
//        int typeId = 1;//mViewModel.getCpLiveData().getValue().getTypeId();
        int game =mViewModel.getCpLiveData().getValue().getGame();
        int typeId= mViewModel.getCpLiveData().getValue().getTypeId();
        switch (GameTypeEnum.valueOf(game)) {
            case KUAISAN:
                customPopupWindow.initKuaiSanTodayResult(new WeakReference<>(getContext()), typeId, true);
                customPopupWindow.initKuaiSanTodayResultData(_mActivity, ll_live, typeId);
                //    customPopupWindow.showKuaiSanTodayResultPop(ll_live,_mActivity);
                break;
            case SSC:
            case XUANWU:
                customPopupWindow.initSscTodayResultPop(_mActivity, game, typeId, true);
                customPopupWindow.initSscTodayResultData(_mActivity, game, typeId, ll_live);
                break;
            case RACE:
                customPopupWindow.initRaceTodayResultPop(_mActivity, game, typeId, true);
                customPopupWindow.initRaceTodayResultData(_mActivity, game, typeId, ll_live);
                break;
            case MARKSIX:
                customPopupWindow.initMarksixTodayResultPop(_mActivity, game, typeId, true);
                customPopupWindow.initMarksixTodayResultData(_mActivity, game, typeId, ll_live);
                break;
            case DANDAN:
                customPopupWindow.initPcddTodayResult(_mActivity, typeId, true);
                customPopupWindow.initPcddTodayResultData(_mActivity, ll_live, typeId);
                break;
            case HAPPY8:
                customPopupWindow.initHappy8TodayResult(_mActivity, typeId, true);
                customPopupWindow.initPcddTodayResultData(_mActivity, ll_live, typeId);
                break;
            case LUCKFARM:
                customPopupWindow.initHappy10TodayResult(_mActivity, game, typeId, true);
                customPopupWindow.initfarmTodayResultData(_mActivity, ll_live, typeId);
                break;
            case HAPPY10:
                customPopupWindow.initHappy10TodayResult(_mActivity, game, typeId, true);
                customPopupWindow.initHappy10TodayResultData(_mActivity, ll_live, typeId);
                break;
            default:
                break;
        }

    }


    @Override
    public void onStop() {
        super.onStop();

        myjzvd.goOnPlayOnPause();//??????
    }



    @Override
    public void onResume() {
        super.onResume();
        if(!isNeedToll){
            myjzvd.goOnPlayOnResume();//????????????
        }
    }

    @Override
    public void onDestroyView() {
        HeightProvider heightProvider = editPanel.getHeightProvider();
        if(heightProvider !=null&& heightProvider.isShowing()){
            heightProvider.dismiss();
            heightProvider=null;
        }
        super.onDestroyView();
        if (disposable != null) {
            disposable.dispose();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        GlideCacheUtil.getInstance().clearImageMemoryCache(getContext());
        mNetSpeedTimer.stopSpeedTimer();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== CommisionPlanPop.TO_ADD_INVETICODE&&resultCode==RESULT_OK){
            //?????????????????????
            if (liveQrCodePop!=null) {
                if(liveQrCodePop.commisionPlanPop!=null){
                    liveQrCodePop. commisionPlanPop.requestCodeList(false,false);
                }
            }
        }
        if(requestCode == TO_DIAMOND_CODE && resultCode == RESULT_OK){
            //????????????pop
            OpenGiftPop();
        }
        if(requestCode == TO_DIAMOND_CODE && resultCode == DiamondFragment.OPEN_BET_RULE_POP){
            //????????????pop
            OpenBetRulePop(mLiveData.getCpId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(giftRainRunnable);
        handler.removeCallbacks(tollCountDownRunnable);
        handler.removeCallbacks(onlinePeopleRunnable);
        handler.removeCallbacks(vip1JoinRunnable);
        handler.removeCallbacksAndMessages(null);

        RongLibUtils.removeEventHandler(handler);
        if(openResultTimer!=null){
            openResultTimer.cancel();
        }
        myjzvd.releaseAllVideos();//??????
        if(showFollowTimer!=null){
            showFollowTimer.cancel();
            showFollowTimer =null;
        }
        if (disposable != null) {
            disposable.dispose();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        GlideCacheUtil.getInstance().clearImageMemoryCache(getContext());
    }

    public LiveEntity.AnchorMemberRoomListBean getmLiveData() {
        return mLiveData;
    }

    public String getAutoAnchorSubscribe() {
        return autoAnchorSubscribe;
    }

    public void setAutoAnchorSubscribe(String autoAnchorSubscribe) {
        this.autoAnchorSubscribe = autoAnchorSubscribe;
    }

    public String getAnchorSubscribeEndDate() {
        return anchorSubscribeEndDate;
    }

    public void setAnchorSubscribeEndDate(String anchorSubscribeEndDate) {
        this.anchorSubscribeEndDate = anchorSubscribeEndDate;
    }

    public String getAnchorSubscribe() {
        return anchorSubscribe;
    }

    public void setAnchorSubscribe(String anchorSubscribe) {
        this.anchorSubscribe = anchorSubscribe;
    }
    /**
     * ??????????????????
     * @param
     * @return
     */
    private static class MyHandler extends Handler{
        private SoftReference<LiveFragment> fragmentSoftRefference;

        public MyHandler(SoftReference fragmentSoftRefference) {
            this.fragmentSoftRefference = fragmentSoftRefference;
        }

        @Override
        public void                                                                                                                                                                                                                                                                                                                                                                                                                 handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            LiveFragment liveFragment = fragmentSoftRefference.get();
            if(liveFragment!=null&&!liveFragment.isDetached()){
                if(message.obj instanceof io.rong.imlib.model.Message ){
                    //????????????
                    io.rong.imlib.model.Message obj = (io.rong.imlib.model.Message) message.obj;
                    String targetId = obj.getTargetId();//?????????????????????????????????
                    //??????????????????????????????
                    io.rong.imlib.model.Message.MessageDirection messageDirection = obj.getMessageDirection();
                    //?????????????????????????????????
                    if(targetId.equals(liveFragment.roomId)){
                        MessageContent content = obj.getContent();
                        switch (message.what) {
                            /**
                             ?????????????????????
                             */
                            case RongLibUtils.MESSAGE_ARRIVED:
                            case RongLibUtils.MESSAGE_SENT:
                                try {
                                    //?????????????????????userId,?????????????????????????????????????????????id
                                    LiveMessageModel messageModel = new LiveMessageModel();
                                    String senderUserId = obj.getSenderUserId();
                                    messageModel.setRcUsId(senderUserId);
                                    String sharePreferenZkCode = SharePreferencesUtil.getString(liveFragment.getContext(), "zkCode", "");
                                    if (content instanceof LiveTextMessage) {
                                        /**
                                         ??????????????????
                                         */
                                        LiveTextMessage liveTextMessage = (LiveTextMessage) content;
                                        if(liveFragment.initUserInfo(messageModel, liveTextMessage.getUserInfoJson(),senderUserId)){
                                            return;
                                        }


                                        if(liveTextMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.TEXT_MESSAGE);
                                            messageModel.setZkCode(liveTextMessage.getZkCode());
                                            messageModel.setTextContent( Utils.filtrationChineseEnglishNumberEmoji(liveTextMessage.getContent()));
                                            messageModel.setMessageDirection(messageDirection);
                                            if(liveFragment.mLiveData.getIsForbidden()!=1){
                                                liveFragment.liveChatFragment.addItem(messageModel);
                                            }
                                        }
                                        /**
                                         ????????????
                                         */
                                    } else if (content instanceof LiveShareBetMessage) {
                                        LiveShareBetMessage liveShareBetMessage = (LiveShareBetMessage) content;
                                        String type_id = liveShareBetMessage.getType_id();
                                        String rule_id = liveShareBetMessage.getRule_id();
                                        String lotmoney = liveShareBetMessage.getLotmoney();
                                        String[] typeidSplit = type_id.split(",");
                                        String[] ruleidSplit = rule_id.split(",");
                                        String[] lotmoneySplit = lotmoney.split(",");
                                        boolean isPass = true;
                                        for (int i = 0; i < typeidSplit.length; i++) {
                                            if(Utils.isNotLong(typeidSplit[i])){
                                                isPass = false;
                                                break;
                                            }
                                        }
                                        for (int i = 0; i < ruleidSplit.length; i++) {
                                            if(Utils.isNotLong(ruleidSplit[i])){
                                                isPass = false;
                                                break;
                                            }
                                        }
                                        for (int i = 0; i < lotmoneySplit.length; i++) {
                                            if(Utils.isNotInt(lotmoneySplit[i])){
                                                isPass = false;
                                                break;
                                            }
                                        }

                                        String game = liveShareBetMessage.getGame();
                                        if(Utils.isNotInt(liveShareBetMessage.getPointGrade())||
                                                Utils.isNotLong(liveShareBetMessage.getLotteryqishu()) ||
                                                Utils.isNotInt(game) ||
                                                Utils.isNotInt(lotmoney) ||
                                                !isPass ||
                                                liveFragment.initUserInfo(messageModel, liveShareBetMessage.getUserInfoJson(),senderUserId)){
                                            return;
                                        }

                                        if(StringMyUtil.isEmptyString(game) || game.equals("0") || game.equals("6") || game.equals("7") || game.equals("8")){
                                            //??????8?????????????????????????????? ???????????????????????????????????????
                                            return;
                                        }

                                        if(!Utils.filterBetMessage(liveShareBetMessage)){
                                            return;
                                        }
                                        if(liveShareBetMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.SHARE_MESSAGE);
                                            messageModel.setZkCode(liveShareBetMessage.getZkCode());
                                            messageModel.setLevel(liveShareBetMessage.getPointGrade());
                                            messageModel.setUserName(liveShareBetMessage.getNickname());
                                            String betMoney = liveShareBetMessage.getLotmoney();
                                            List<String> amountAmountList = Arrays.asList(betMoney.split(","));
                                            int betAmount=0;
                                            for (int i = 0; i < amountAmountList.size(); i++) {
                                                betAmount+=Integer.parseInt(amountAmountList.get(i));
                                            }
                                            messageModel.setBetAmount(betAmount+"");
                                            messageModel.setTypeName(liveShareBetMessage.getTypename());
                                            messageModel.setBetQiShu(liveShareBetMessage.getLotteryqishu());
                                            messageModel.setBetName(liveShareBetMessage.getName());
                                            messageModel.setBetGroupName(liveShareBetMessage.getGroupname());
                                            messageModel.setType_id(liveShareBetMessage.getType_id());
                                            messageModel.setGame(game);
                                            messageModel.setReluId(liveShareBetMessage.getRule_id());
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                        /**
                                         ????????????
                                         */
                                    } else if (content instanceof LiveRedMessage) {
                                        LiveRedMessage liveRedMessage = (LiveRedMessage) content;
                                        int redType = liveRedMessage.getRedType();
                                        if(Utils.isNotDouble(liveRedMessage.getRedPrice()) ||
                                                Utils.isNotLong(liveRedMessage.getRedId()) ||
                                                Utils.isNotInt(liveRedMessage.getRedType()+"")){
                                            return;
                                        }
                                        //3????????????????????? userInfoJson???  1 2 ??????????????????qpUserName???
                                        if (redType == 3) {
                                            if(liveFragment.initUserInfo(messageModel, liveRedMessage.getUserInfoJson(),senderUserId)){
                                                return;
                                            }
                                        } else {
                                            messageModel.setUserName(liveRedMessage.getQbUserName());
                                        }
                                        if(liveRedMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.RED_MESSAGE);
                                            messageModel.setRedType(redType);
                                            messageModel.setZkCode(liveRedMessage.getZkCode());
                                            messageModel.setRedPrice(liveRedMessage.getRedPrice());
                                            messageModel.setRedId(Long.parseLong(liveRedMessage.getRedId()));
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }

                                        /**
                                         ????????????
                                         */
                                    } else if (content instanceof LiveGiftMessage) {
                                        /**?????????????????????  ??????   ---------------------*/
                                        LiveGiftMessage liveGiftMessage = (LiveGiftMessage) content;
                                        String userInfoJson = liveGiftMessage.getUserInfoJson();
                                        if(liveFragment.initUserInfo(messageModel, userInfoJson,senderUserId) ||
                                                Utils.isNotLong(liveGiftMessage.getGiftId()) ||
                                                Utils.isNotInt(liveGiftMessage.getGiftNum()) ||
                                                Utils.isNotDouble(liveGiftMessage.getGiftPrice()) ||
                                                Utils.isNotInt(liveGiftMessage.getGear()) ||
                                                Utils.isNotInt(liveGiftMessage.getGearTime())){
                                            return;
                                        }
                                        if(liveGiftMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.GIFT_MESSAGE);
                                            messageModel.setZkCode(liveGiftMessage.getZkCode());
                                            messageModel.setGiftId(liveGiftMessage.getGiftId());
                                            String giftNum = liveGiftMessage.getGiftNum();
                                            messageModel.setGiftNum(giftNum);
                                            messageModel.setGiftName(liveGiftMessage.getGiftName());
                                            String giftPrice = liveGiftMessage.getGiftPrice();
                                            BigDecimal singlePrice = new BigDecimal(giftPrice);
                                            BigDecimal oldPrice = singlePrice.multiply(new BigDecimal(giftNum));
                                            liveFragment.   liveChatFragment.addItem(messageModel);
                                            String toString =liveFragment. money_tv.getText().toString();
                                            if(!toString.contains("-")){
                                                JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
                                                String userId = jsonObject.getString("userId");
                                                BigDecimal oldNum = new BigDecimal(toString);
                                                //??????????????????????????????
                                                if(!(StringMyUtil.isEmptyString(userId)?"0":userId).equals((SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)+""))){
                                                    liveFragment.  money_tv.setText((oldNum.add(oldPrice))+"");
                                                }

                                                EventBus.getDefault().postSticky(new UpdateGiftAmountModel(oldNum.add(oldPrice),liveFragment.mLiveData.getAnchorMemberId()+""));
                                            }
                                            /**?????????????????????  ??????   ---------------------*/

                                            /**??????????????????   ??????  -----------------------*/
                                            String giftUrl = liveGiftMessage.getGiftUrl();//??????????????????
                                            String giftIcon = liveGiftMessage.getGiftIcon();
                                            if(giftUrl.contains("http") || giftIcon.contains("http")){
                                                return;
                                            }
                                            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);//????????????
                                            int giftCount = 0;
                                            try {
                                                giftCount = Integer.parseInt(giftNum);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            GiftSendModel giftSendModel = new GiftSendModel(giftCount);
                                            giftSendModel.setGiftRes(giftIcon);
                                            giftSendModel.setNickname(jsonObject.getString("name"));
                                            giftSendModel.setSig(Utils.getString(R.string.??????) + liveGiftMessage.getGiftName());
                                            String portrait = jsonObject.getString("portrait");
                                            giftSendModel.setUserAvatarRes(portrait);
                                            liveFragment. giftView.addGift(giftSendModel);

                                            if(StringMyUtil.isNotEmpty(giftUrl)) {//?????????????????????????????????, ??????svga??????(????????????????????????????????????,?????????????????????????????????????????????????????????)
                                                if(!jsonObject.getString("userId").equals(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)+"")){
                                                    LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.checkLiveImageUrl(giftUrl), null);
                                                    liveFragment.giftSvgaMage.startAnimator(liveGiftSVGAEntity);
                                                }
                                            }
                                        }

                                        /** ??????????????????   ??????  -----------------------*/

                                        /**
                                         * ??????/?????????????????????
                                         */

                                    } else if (content instanceof LiveExitAndJoinMessage) {
                                        LiveExitAndJoinMessage liveExitAndJoinMessage = (LiveExitAndJoinMessage) content;
                                        if(liveFragment.initUserInfo(messageModel, liveExitAndJoinMessage.getUserInfoJson(),senderUserId) ||
                                                Utils.isNotInt(liveExitAndJoinMessage.getStatus())){
                                            return;
                                        }
                                        if(liveExitAndJoinMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.EXIT_JOIN_MESSAGE);
                                            String userName = liveExitAndJoinMessage.getUserName();
                                            messageModel.setUserName(userName);
                                            messageModel.setZkCode(liveExitAndJoinMessage.getZkCode());
                                            String status = liveExitAndJoinMessage.getStatus();
                                            status = StringMyUtil.isEmptyString(status)?"1":status;
                                            messageModel.setStatus(status);
                                            //????????????2, ??????????????????
                                            int level = Integer.parseInt(StringMyUtil.isEmptyString(messageModel.getLevel()) ? "1" : messageModel.getLevel());
                                            String mountSVGA = messageModel.getMountSVGA();
                                            String levelSVGA = messageModel.getLevelSVGA();

                                            // -------------------------- ??????????????????????????? start
                                            if(status.equals("0")){
                                                if(StringMyUtil.isNotEmpty(mountSVGA)){
                                                    //?????????????????????, ??????????????????View??????????????????
                                                    LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.checkImageUrl(mountSVGA), messageModel);
                                                    liveFragment.giftSvgaMage.startAnimator(liveGiftSVGAEntity);
                                                }else {
                                                    //??????????????????, ??????????????????
                                                    if(level>=2){
                                                        if(StringMyUtil.isNotEmpty(levelSVGA)){
                                                            liveFragment.joinGiftSvgaMage.startAnimator(messageModel,Utils.checkImageUrl(levelSVGA));
                                                        }
                                                    }
                                                }

//                                              liveFragment.liveChatFragment.addItem(messageModel);
                                                liveFragment.liveChatFragment.addJoinItem(messageModel);
                                                // -------------------------- ??????????????????????????? end
                                            }else {
                                       //????????????????????????
//                                    liveChatFragment.addItem(messageModel);
                                            }
                                        }
                                    }else if(content instanceof TextMessage){
                                        /**
                                         ?????????????????????(?????????????????????,???????????????????????????????????????)
                                         */
                                        TextMessage textMessage= (TextMessage) content;
                                        String textContent = textMessage.getContent();

                                        if(!Utils.isJson(textContent)||(!senderUserId.contains(CommonStr.RONG_ID_STR) && !senderUserId.equals("1"))){
                                            return;
                                        }
                                        ZjMessageModel zjMessageModel = JSONObject.parseObject(textMessage.getContent(), ZjMessageModel.class);
                                        if(zjMessageModel.getPlatformCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            String isBack = zjMessageModel.getIsBack();
                                            messageModel.setObjectName(isBack);
                                            messageModel.setUserNickName(zjMessageModel.getUserNickName());
                                            messageModel.setTypeName(zjMessageModel.getTypename());
                                            messageModel.setUserName(zjMessageModel.getUserNickName());
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                             /**
                                             * ?????????????????? ????????????linear
                                             */
                                        if(zjMessageModel.getUser_id() == SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)){
                                            MediaPlayer mPlayer = MediaPlayer.create(liveFragment.getContext(), R.raw.sound_gold);
                                            mPlayer.start();
                                            if(liveFragment.reward_linear.getVisibility()!=VISIBLE){
                                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                                                ForegroundColorSpan defaultColorSpan = new ForegroundColorSpan(Color.WHITE);//???????????????span
                                                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FDFF25"));//??????????????????pasn
                                                spannableStringBuilder.append(Utils.getString(R.string.????????????)+zjMessageModel.getTypename()+Utils.getString(R.string.??????),defaultColorSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                liveFragment. zjPrice = zjMessageModel.getPrice();
                                                spannableStringBuilder.append(liveFragment.zjPrice +"  ",foregroundColorSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                spannableStringBuilder.append(Utils.getString(R.string.???),defaultColorSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                                liveFragment.reward_content_tv.setText(spannableStringBuilder);
                                                YoYo.with(Techniques.SlideInRight)
                                                        .duration(700)
                                                        .playOn(liveFragment.reward_linear);
                                                liveFragment.reward_linear.setVisibility(VISIBLE);
                                                liveFragment.handler.post(liveFragment.rewardRunnable);
                                            }

                                        }
                                    }else if(content instanceof LiveSystemMessage){
                                        /**
                                         * ????????????/????????????
                                         */
                                        if(!senderUserId.contains(CommonStr.RONG_ID_STR) && !senderUserId.equals("1")){
                                            return;
                                        }
                                        LiveSystemMessage liveSystemMessage = (LiveSystemMessage) content;
                                        SystemMessageModel systemMessageModel = JSONObject.parseObject(liveSystemMessage.getContent(), SystemMessageModel.class);
                                        if(systemMessageModel.getPlatformCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setZkCode(systemMessageModel.getPlatformCode());
                                            messageModel.setObjectName(CommonStr.LIVE_SYSTEM_MESSAGE);
                                            messageModel.setUserNickName(systemMessageModel.getUserNickName());
                                            messageModel.setSystemStatus(systemMessageModel.getStatus());
                                            messageModel.setField(systemMessageModel.getField());
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                    }

                                    else if(content instanceof LiveFollowMessage){
                                        /**
                                         *????????????
                                         */
                                        LiveFollowMessage liveFollowMessage = (LiveFollowMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,liveFollowMessage.getUserInfoJson(),senderUserId)){
                                            return;
                                        }
                                        liveFragment.  initUserInfo(messageModel,liveFollowMessage.getUserInfoJson(),senderUserId);
                                        if(liveFollowMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setZkCode(liveFollowMessage.getZkCode());
                                            messageModel.setObjectName(CommonStr.FOLLOW_MESSAGE);
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }

                                    }else if(content instanceof ForbiddenMessage){
                                        /**
                                         * ????????????
                                         */
                                        ForbiddenMessage forbiddenMessage = (ForbiddenMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,forbiddenMessage.getUserInfoJson(),senderUserId) ||
                                           Utils.isNotInt(forbiddenMessage.getIsForbidden())){
                                            return;
                                        }
                                        if(forbiddenMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.FORBIDDEN_MESSAGE);
                                            messageModel.setZkCode(forbiddenMessage.getZkCode());
                                            messageModel.setTargetNickName(forbiddenMessage.getTargetNickName());
                                            messageModel.setIsForbidden(forbiddenMessage.getIsForbidden());
                                            String managerType = messageModel.getManagerType();
                                            if(!managerType.equals("2")){
                                                liveFragment.liveChatFragment.addItem(messageModel);
                                            }
                                        }
                                    }else if (content instanceof RoomManageMessage){

                                        /**
                                         * ??????????????????
                                         */
                                        RoomManageMessage roomManageMessage = (RoomManageMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,roomManageMessage.getUserInfoJson(),senderUserId) ||
                                                Utils.isNotInt(roomManageMessage.getIsRoomManager())){
                                            return;
                                        }
                                        if(roomManageMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.ROOM_MANAGER_MESSAGE);
                                            messageModel.setZkCode(roomManageMessage.getZkCode());
                                            messageModel.setTargetNickName(roomManageMessage.getTargetNickName());
                                            messageModel.setIsRoomManager(roomManageMessage.getIsRoomManager());
//                            mViewModel.getAnchorManageData().getValue().setIsRoomManager(roomManageMessage.getIsRoomManager());
                                            //????????????????????????????????????????????????,???????????????IsRoomManager??????,
                                            if(roomManageMessage.getRcUserId().equals(RongIMClient.getInstance().getCurrentUserId())){
                                                liveFragment.  managerTypeEntity.setIsRoomManager(roomManageMessage.getIsRoomManager());
                                            }
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }

                                    }else if(content instanceof SwichMoneyMessage){
                                        /**
                                         * ?????????????????? ??????
                                         */
                                        SwichMoneyMessage swichMoneyMessage = (SwichMoneyMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,swichMoneyMessage.getUserInfoJson(),senderUserId) ||
                                                Utils.isNotInt(swichMoneyMessage.gettype()) ||
                                                Utils.isNotLong(senderUserId)){
                                            return;
                                        }
                                        if(swichMoneyMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.CHANGE_ROOM_TYPE_MESSAGE);
                                            messageModel.setZkCode(swichMoneyMessage.getZkCode());
                                            messageModel.setType(swichMoneyMessage.gettype());
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                            if(swichMoneyMessage.gettype().equals("2")){
                                                /**
                                                 * ??????????????????.?????????????????????(????????????????????????????????????)
                                                 */
                                                liveFragment. requestManageType(false);
                                            }else {
                                                /**
                                                 * ??????????????????
                                                 */
                                                liveFragment. toll_constraintLayout.setVisibility(GONE);//?????????????????????????????????
                                                if(!liveFragment.drawerlayout.isDrawerOpen(GravityCompat.END)){
                                                    liveFragment. drawerlayout.openDrawer(GravityCompat.END);//????????????
                                                }
                                                liveFragment.  handler.removeCallbacks(liveFragment.tollCountDownRunnable);//?????????????????????
                                                if(liveFragment.autoTollPop!=null){
                                                    liveFragment.autoTollPop.dismiss();//??????????????????pop
                                                }
                                                if(liveFragment.myjzvd!=null){
                                                    liveFragment. myjzvd.startVideo();//?????????????????????
                                                }
                                            }
                                        }

                                    }else if(content instanceof CloseLiveMessage){
                                        /**
                                         *????????????
                                         */
                                        CloseLiveMessage closeLiveMessage = (CloseLiveMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,closeLiveMessage.getUserInfoJson(),senderUserId) || Utils.isNotLong(senderUserId)){
                                            return;
                                        }

                                        if(closeLiveMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.CLOSE_LIVE_MESSAGE);
                                            messageModel.setZkCode(closeLiveMessage.getZkCode());
                                            //?????????????????????
                                            liveFragment. linear_liveroom_tag.setVisibility(View.VISIBLE);
                                            //?????????????????????
                                            if(liveFragment.tollCountDownRunnable!=null){
                                                liveFragment.handler.removeCallbacks(liveFragment.tollCountDownRunnable);
                                            }
                                            //?????????????????????
                                            liveFragment. toll_constraintLayout.setVisibility(GONE);
                                        }

                                    }else if(content instanceof LiveNormalRedMessage){
                                        /**
                                         * ?????????????????????
                                         */
                                        LiveNormalRedMessage liveNormalRedMessage = (LiveNormalRedMessage) content;
                                        if(liveFragment.initUserInfo(messageModel,liveNormalRedMessage.getUserInfoJson(),senderUserId) ||
                                                Utils.isNotDouble(liveNormalRedMessage.getRedPrice()) ||
                                                Utils.isNotLong(liveNormalRedMessage.getRedId())){
                                            return;
                                        }

                                        if(liveNormalRedMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.NORMAL_RED_MESSAGE);
                                            messageModel.setUserInfoJson(liveNormalRedMessage.getUserInfoJson());
                                            messageModel.setUserNickName(liveNormalRedMessage.getQbUserName());
                                            messageModel.setRedPrice(liveNormalRedMessage.getRedPrice());
                                            messageModel.setZkCode(liveNormalRedMessage.getZkCode());
                                            messageModel.setRedId(Long.parseLong(liveNormalRedMessage.getRedId()));
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                    }else if(content instanceof LiveSystemBetMessage){
                                        /**
                                         * ?????????????????? (????????????jsonString, ???????????????,?????????????????????????????????, ???????????????)
                                         */
                                        LiveSystemBetMessage liveSystemBetMessage = (LiveSystemBetMessage) content;
                                        if(!Utils.isJson(liveSystemBetMessage.getContent())||(!senderUserId.contains(CommonStr.RONG_ID_STR) && !senderUserId.equals("1"))){
                                            return;
                                        }
                                        SystemBetMessageModel systemBetMessageModel = JSONObject.parseObject(liveSystemBetMessage.getContent(), SystemBetMessageModel.class);
                                        if(systemBetMessageModel.getPlatformCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            String isBack = systemBetMessageModel.getIsBack();
                                            String pointGrade = systemBetMessageModel.getPointGrade();
                                            messageModel.setObjectName(CommonStr.SHARE_MESSAGE);
                                            messageModel.setZkCode(systemBetMessageModel.getPlatformCode());
                                            messageModel.setLevel(pointGrade);
                                            messageModel.setUserNickName(systemBetMessageModel.getNickname());
                                            messageModel.setUserName(systemBetMessageModel.getNickname());
                                            String betMoney = systemBetMessageModel.getAmount();
//                                        List<String> amountAmountList = Arrays.asList(betMoney.split(","));
                                            messageModel.setBetAmount(betMoney);
                                            messageModel.setTypeName(systemBetMessageModel.getTypename());
                                            messageModel.setBetQiShu(systemBetMessageModel.getLotteryqishu());
                                            messageModel.setBetName(systemBetMessageModel.getName());
                                            messageModel.setBetGroupName(systemBetMessageModel.getGroupname());
                                            messageModel.setType_id(systemBetMessageModel.getType_id());
                                            messageModel.setGame(systemBetMessageModel.getGame());
                                            messageModel.setReluId(systemBetMessageModel.getRule_id());
                                            //??????????????????,???????????????adapter??????????????????
                                            messageModel.setManagerType("0");//??????????????????
                                            messageModel.setLevelIcon(Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(pointGrade)+1)+""));
                                            messageModel.setMedalIcon("");//??????????????????
                                            messageModel.setTitleIcon("");//?????????????????????
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                    }else if(content instanceof LiveRewardMessage){
                                        /**
                                         * ????????????
                                         */
                                        LiveRewardMessage liveRewardMessage = (LiveRewardMessage) content;
                                        String rewardMoney = liveRewardMessage.getRewardMoney();
                                        if(liveFragment.initUserInfo(messageModel,liveRewardMessage.getUserInfoJson(),senderUserId) || Utils.isNotDouble(rewardMoney)){
                                                return;
                                        }
                                        BigDecimal singlePrice = new BigDecimal(rewardMoney);
                                        String toString = liveFragment.money_tv.getText().toString();
                                        if(!toString.contains("-")){
                                            BigDecimal oldNum = new BigDecimal(toString);
                                            liveFragment. money_tv.setText((oldNum.add(singlePrice))+"");
                                        }
                                        if(liveRewardMessage.getZkCode().equals(sharePreferenZkCode)||messageModel.getManagerType().equals("1")){
                                            messageModel.setObjectName(CommonStr.REWARD_ANCHOR);
                                            messageModel.setUserInfoJson(liveRewardMessage.getUserInfoJson());
                                            messageModel.setRewardPrice(rewardMoney);
                                            liveFragment.liveChatFragment.addItem(messageModel);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            //??????????????????
                            case RongLibUtils.MESSAGE_SEND_ERROR:
                                int arg1 = message.arg1;
                                if(arg1==FORBIDDEN_IN_CHATROOM.getValue()){
                                    if(!(content instanceof LiveExitAndJoinMessage)){
                                        /**
                                         ?????????
                                         */
                                        ToastUtil.showToast(Utils.getString(R.string.?????????????????????????????????????????????));
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }else {
                    if(message.what==NetSpeedTimer.NET_SPEED_TIMER_DEFAULT){
                        /**
                         *   ????????????
                         */
                        String speed = (String) message.obj;
                        String substring = speed.substring(0,speed.indexOf(" kb/s"));
                        long speedLong = Long.parseLong(substring);
                        if(null!=liveFragment.internet_speed_tv){
                            if(speedLong<100) {
                                liveFragment. internet_speed_tv.setTextColor(ContextCompat.getColor(liveFragment.getContext(),R.color.red));
                            }else {
                                liveFragment. internet_speed_tv.setTextColor(ContextCompat.getColor(liveFragment.getContext(),R.color.green));
                            }
                            liveFragment.  internet_speed_tv.setText(speed);
                        }
                    }
                }
            }
        }
    }
}
