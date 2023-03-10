package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.ssc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.TouzhuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.GfSureBetPopAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOffcialChooseTwoAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOfficialAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOfficialChooseAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SureBetPopAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GfSureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseTwoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BetNumUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.NoAlphaProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import pl.droidsonroids.gif.GifImageView;

public class SscBetActivity extends BaseActivity implements View.OnClickListener,CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener, TextWatcher, SscOfficialAdapter.onItemFourClickListener, SscOfficialAdapter.onItemOneClickListener, SscOfficialAdapter.OnQuickClickLintener, SscAdapter.OnRecyclerViewItemClickListener {
    private TextView hourText;//??????????????????
    private TextView minutesText;//??????????????????
    private TextView secondsText;//???????????????
    private long countTime;//???????????????
    private TextView nowQishuText;//????????????
    private TextView lastQiShuText;//???????????????(??????????????????????????????????????????,????????????????????????????????????(lastQiShuText??????10?????????????????????????????????????????????lotteryqishu?????????,??????????????????,??????????????????))
    private String nowQishu;//????????????
    private ImageView rotateImg;//??????????????????
    private boolean isWaitOpen = true;//?????????????????????(??????????????????true,????????????????????????isWaitOpen?????????runnable?????????????????????,??????????????????????????????????????????????????????)
    private int num = 1;//???????????????????????????????????????????????????
    private LinearLayout showClassfyLinear;//????????????????????????pop
    private TextView rightMenu;//????????????????????????pop
    private LinearLayout todayOpenResultLinear;//??????????????????????????????
    private LinearLayout choosePlayType;//????????????????????????pop
    private LinearLayout popTargetLinear;//????????????pop????????????
    /*
   ????????????pop
    */
    private PopupWindow choosePlayTypePop;
    private RadioButton liangmianBtn;//??????button
    private RadioButton oneToFiveBtn;//1-5???button
    private RadioButton qianzhonghouBtn;//?????????button
    private LinearLayout openResultLinear;
    private MyCornerTextView sscNumTv1;
    private MyCornerTextView sscNumTv2;
    private MyCornerTextView sscNumTv3;
    private MyCornerTextView sscNumTv4;
    private MyCornerTextView sscNumTv5;

    private TextView betNum;//??????????????????
    private EditText amountEdit;//?????????????????????
    private TextView betButton;//????????????
    private TextView randomButton;//????????????

    private ArrayList<SscModel> zongheList = new ArrayList<>();
    private ArrayList<SscModel> zongheOneList = new ArrayList<>();
    private ArrayList<SscModel> zongheTwoList = new ArrayList<>();
    private ArrayList<SscModel> zongheThreeList = new ArrayList<>();
    private ArrayList<SscModel> zongheFourList = new ArrayList<>();
    private ArrayList<SscModel> zongheFiveList = new ArrayList<>();
    private ArrayList<SscModel> diyiqiuList = new ArrayList<>();
    private ArrayList<SscModel> dierqiuList = new ArrayList<>();
    private ArrayList<SscModel> disanqiuList = new ArrayList<>();
    private ArrayList<SscModel> disiqiuList = new ArrayList<>();
    private ArrayList<SscModel> diwuqiuList = new ArrayList<>();
    private ArrayList<SscModel> qiansanList = new ArrayList<>();
    private ArrayList<SscModel> zhongsanList = new ArrayList<>();
    private ArrayList<SscModel> housanList = new ArrayList<>();
    private ArrayList<ArrayList<SscModel>> allMoDelList = new ArrayList<>();

    private ArrayList<SscModel> diyiqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> dierqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> disanqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> disiqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> diwuqiuSelectList = new ArrayList<>();
    /*
   recycleView????????????
    */
    private ArrayList<SscModel> selecterList = new ArrayList<>();//????????????item??????
    private RecyclerView mRecy;
    private SscAdapter sscAdapter;
    private ArrayList<SscModel> sscModelList = new ArrayList<>();

    /*
    ????????????pop
     */
    private PopupWindow sureBetPop;
    private TextView lotteryNameTv;//?????????
    private TextView qishuTv;//??????
    private TextView betAmoumtTv;//????????????(????????????)
    private TextView allBetNumTv;//????????????
    private TextView allBetAmountTv;//???????????????
    private TextView sureButton;
    private TextView cancelButton;
    private RecyclerView sureBetRecy;
    private SureBetPopAdapter sureBetPopAdapter;
    private ArrayList<SureBetPopMeldol> sureBetPopMeldolArrayList = new ArrayList<>();

    private TextView memberMoneyText;//????????????
    private TextView pcddTypeText;//?????????????????????
    private ImageView chooseImg;//????????????pop?????????,?????????????????????
    private TextView pcddTypeTextGf;//?????????????????????
    private ImageView chooseImgGf;//????????????pop?????????,?????????????????????
    private ImageView xingYongIma;

    private TextView name;//????????????????????????name

    private LinearLayout xingyongLinear;//????????????????????????
    private TextView xingyongTv;
    private PopupWindow CountDownEndPop;//?????????????????????pop
    private TextView lastQiShuTv;  //???????????????
    private TextView newQiShuTv; //????????????
    private TextView countDownEndSure;//???????????????pop???????????????

    private TextView actionBarBack;//?????????

    private long millionSeconds;//?????????????????????
    private long time;//???????????????
    private long shijiancha;//?????????????????????????????????
    private String tqtime;//????????????

    private Long user_id;

    private int type_id;
    private int game;
    private String isopenOffice;
    private String isStart = "1";

    private String typename;

    private ArrayList<MyCornerTextView> myCornerTextViewArrayList = new ArrayList<>();
    private List<String> openResultList;
    private long jgTime;
//    private PopupWindow fengpanPop;

    private String todayZJ;//???????????? 1 ??????  0??????

    private int sscaiCount;//1-5???????????????

    private JSONArray playRuleListFour;


    //    ---------------------------------------------------------------????????????---------------------------------------------------------------------------
       /*
    ????????????pop(??????)
     */
    private PopupWindow choosePlayTypePopGf;//????????????pop
    private LinearLayout choosePlayTypeGf;//????????????????????????pop(??????)
    private ArrayList<SscOfficialChooseMedol> sscListOne = new ArrayList<>();//??????????????????
    private ArrayList<SscOfficialChooseTwoMedol> sscListTwo = new ArrayList<>();//??????????????????????????????(????????????????????????)
    private ArrayList<SscOfficialChooseMedol> sscListTwoAll = new ArrayList<>();//???????????????????????????
    //    private ArrayList<SscOfficialChooseMedol> sscListThree=new ArrayList<>();
    private ArrayList<SscOfficialChooseMedol> sscListThreeAll = new ArrayList<>();//???????????????????????????
    private SscOfficialChooseAdapter sscOfficialChooseAdapter;//?????????????????????
    private SscOffcialChooseTwoAdapter sscOffcialChooseTwoAdapter;//?????????????????????(????????????????????????????????????????????????????????????)
    //    private SscOffcialChooseThreeAdapter sscOffcialChooseThreeAdapter;
    private RecyclerView recyTwo;//??????recycle
    private RecyclerView recyOne;//??????recycle
    private String typeOneName;//??????????????????????????????name
    private String typeThreeName;//??????????????????????????????name(???typeOneName?????????,?????????actionbar??????)
    private LinearLayout gfRecycleLinear;
    private LinearLayout gfBottomLinear;
    private LinearLayout gfAmountLinear;
    private LinearLayout RecycleLinear;
    private LinearLayout BottomLinear;
    private TextView betShuoMingTv;//??????????????????????????????


    /*
    ?????????????????????recycleView(????????????)
     */
    private RecyclerView officialbetRecy;
    private SscOfficialAdapter sscOfficialAdapter;
    private ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedolArrayList = new ArrayList<>();
    private ArrayList<SscOfiicialBetMedol> sscofficialFourModelList = new ArrayList<>();
    private ArrayList<SscOfiicialBetMedol> officialSelectorList = new ArrayList<>();//??????medol??????

    private String threeId;//???????????????id,???????????????????????????????????????????????????

    private TextView jianTv;
    private EditText officialAmountEt;
    private TextView jiaTv;
    private TextView maxAmountTv;
    private RadioButton yuanBtn;
    private RadioButton jiaoBtn;

    private TextView jixuanGfTv;
    private TextView betNumGfTv;
    private TextView betAmountGfTv;
    private TextView sureBetGf;

    /*
    ????????????????????????pop
     */
    private PopupWindow gfSureBetPop;
    private TextView gfLotteryNameTv;//?????????
    private TextView gfQishuTv;//??????
    private TextView gfBetAmoumtTv;//????????????(????????????)
    private TextView gfAllBetNumTv;//????????????
    private TextView gfAllBetAmountTv;//???????????????
    private TextView gfSureButton;
    private TextView gfCancelButton;
    private RecyclerView gfSureBetRecy;
    private GfSureBetPopAdapter gfSureBetPopAdapter;
    private ArrayList<GfSureBetPopMeldol> gfSureBetPopMeldolArrayList = new ArrayList<>();

    private long officialAmonnt = 1;//????????????,???????????????
    private String odds; //??????(????????????)
    private String remark;//?????????????????????????????????
    private int allBetNum = 0;//?????????(??????????????????????????????,allBetnum???0:???????????????)
    private String allBetAmountStr;//???????????????(????????????  ??????????????????)

    private boolean isCrate = true;
    private boolean isZero = false;//??????????????????????????????,??????????????????????????????0,???0?????????????????????????????????,??????runnableTime????????????,?????????????????????????????????
    public static final int ReqTouzhu = 101;
    private TextView stopTv;
    private LinearLayout timeLinear;
    private LinearLayout timeLoadLinear;
    private ConstraintLayout loadingLinear;

   private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
   private TextView is_stop_tv;

    private GifImageView count_down_fail_loading_iv;
    private int failCount=0;
    private PublishSubject failCountObservable =PublishSubject.create();
    private TextView fail_refresh_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssc_bet);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game,type_id);
        bindView();//????????????
        initChoosePlayTypePop();
        initAllPop();//???????????? ???????????? ???????????????,???????????????
//        initFenpangPop(); //??????pop
        getCountTime(null);//???????????????
        initRecycle();//recycleView??????
        getMoney();//??????????????????
        initSureBetPop();//????????????pop
        initGfSureBetPop();
        initCountDownEndPop();//??????????????????pop
        observableFailCount();//???????????????????????????
    }

        private void getIntentData() {
            user_id = SharePreferencesUtil.getLong(SscBetActivity.this, "user_id", 0l);
            sscaiCount = SharePreferencesUtil.getInt(SscBetActivity.this, "sscaiCount", 8);
         /*
        ??????????????????,???????????????????????????????????????
         */
            Intent intent = getIntent();
            type_id = intent.getIntExtra("type_id", 0);
            game = intent.getIntExtra("game", 0);
            typename = intent.getStringExtra("typename");
            isopenOffice = intent.getStringExtra("isopenOffice");//0 ???????????????
            if(StringMyUtil.isEmptyString(isopenOffice)){
                isopenOffice="";
            }
            isStart = intent.getStringExtra("isStart");
            isStart = StringMyUtil.isEmptyString(isStart)?"1":isStart;
        }

        @Override
    protected void init() {

    }
    private void observableFailCount() {
        failCountObservable
                .observeOn(AndroidSchedulers.mainThread())//??????????????????
                .subscribeOn(Schedulers.io())//?????????io??????
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        if(integer>6){
                            if(fail_refresh_tv.getVisibility()!=View.VISIBLE){
                                fail_refresh_tv.setVisibility(View.VISIBLE);
                            }
                        }else {
                            if(fail_refresh_tv.getVisibility()!=View.GONE){
                                fail_refresh_tv.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*
    ?????????????????????????????????
     */
    private void initRecycle() {
        mRecy.setAdapter(sscAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //??????position?????????item?????????size
            @Override
            public int getSpanSize(int position) {
                if (liangmianBtn.isChecked()) {
                    if (position == 0 || position == 8 || position == 13 || position == 18 || position == 23 || position == 28) {
                        return 60;
                    } else if (position == 5 || position == 6 || position == 7) {
                        return 20;
                    } else {
                        return 15;
                    }
                } else if (oneToFiveBtn.isChecked()) {
                    if (position == 0 || position == 11 || position == 22 || position == 33 || position == 44) {
                        return 60;
                    } else {
                        return 12;
                    }
                } else {
                    if (position == 0 || position == 6 || position == 12) {
                        return 60;
                    } else {
                        return 12;
                    }
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//????????????
        sscAdapter.setOnItemClickListener(this);
    }

    /*
    ??????????????????pop
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//??????????????????
        choosePlayTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = SscBetActivity.this.getWindow().getAttributes();
                if (!CountDownEndPop.isShowing() && !sureBetPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(SscBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImg.startAnimation(loadAnimation);
            }

        });

    }

    /*
    ??????????????????????????????
     */
    private void requestRule() {
        loadingLinear.setVisibility(View.VISIBLE);
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame()+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("game", getGame());
            Utils.docking(data, RequestUtil.REQUEST_wt9, 1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE + getGame()+type_id,content);
                    handRuleJson(content);
                }

                @Override
                public void failed(MessageHead messageHead) {
                    loadingLinear.setVisibility(View.GONE);
                }
            });
        }else {
            handRuleJson(jsonStr);
        }

    }

        private void handRuleJson(String content) {
            loadingLinear.setVisibility(View.GONE);
            sscModelList.clear();
//                selecterList.clear();
            zongheList.clear();
            for (int i = 0; i < allMoDelList.size(); i++) {
                allMoDelList.get(i).clear();
            }
            JSONObject jsonObject1 = JSONObject.parseObject(content);
            JSONArray rulelistAll = jsonObject1.getJSONArray("RulelistAll");
            for (int i = 0; i < rulelistAll.size(); i++) {
                JSONObject jsonObject = rulelistAll.getJSONObject(i);
                JSONArray rulelist = jsonObject.getJSONArray("Rulelist");
                Integer isboth = jsonObject.getInteger("isboth");
                Integer isball = jsonObject.getInteger("isball");
                for (int j = 0; j < rulelist.size(); j++) {
                    JSONObject jsonObject2 = rulelist.getJSONObject(j);
                    Integer group_id = jsonObject2.getInteger("group_id");
                    String name = jsonObject2.getString("name");
                    BigDecimal odds = jsonObject2.getBigDecimal("odds");
                    String id = jsonObject2.getString("id");
                    String groupname = jsonObject2.getString("groupname");
                    Integer balled = jsonObject2.getInteger("balled");
                    if (isboth == 0 && isball == 0) {//??????????????????
                        if (group_id == 3) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.?????????)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            qiansanList.add(sscMedol);
                        } else if (group_id == 4) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.????????????)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            zhongsanList.add(sscMedol);
                        } else if (group_id == 5) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.????????????)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            housanList.add(sscMedol);
                        }

                    } else if (isball == 1 && isboth == 0) {//1-5????????????
                        if (group_id == 2) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.????????????)+"-" + groupname);
                            if (balled == 1) {
                                diyiqiuList.add(sscMedol);
                            } else if (balled == 2) {
                                dierqiuList.add(sscMedol);
                            } else if (balled == 3) {
                                disanqiuList.add(sscMedol);
                            } else if (balled == 4) {
                                disiqiuList.add(sscMedol);
                            } else {
                                diwuqiuList.add(sscMedol);
                            }
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                        }
                    } else if (isball == 1 && isboth == 1) {
                        SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.??????)+"-" + groupname);
                        if (balled == 0) {
                            zongheList.add(sscMedol);
                        } else if (balled == 1) {
                            zongheOneList.add(sscMedol);
                        } else if (balled == 2) {
                            zongheTwoList.add(sscMedol);
                        } else if (balled == 3) {
                            zongheThreeList.add(sscMedol);
                        } else if (balled == 4) {
                            zongheFourList.add(sscMedol);
                        } else {
                            zongheFiveList.add(sscMedol);
                        }
                        ArrayList<String> idList = new ArrayList<>();
                        for (int a = 0; a < selecterList.size(); a++) {
                            String rule_id = selecterList.get(a).getRule_id();
                            idList.add(rule_id);
                        }
                        if (idList.contains(id)) {
                            sscMedol.setStatus(1);
                        }
                    }
                }
            }

            if (liangmianBtn.isChecked()) {
                SscModel sscMedol = new SscModel(Utils.getString(R.string.???????????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.???????????????));
                sscModelList.add(sscMedol);
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                for (int i = 0; i < zongheList.size(); i++) {
                    SscModel sscMedol1 = zongheList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < zongheOneList.size(); i++) {
                    SscModel sscMedol1 = zongheOneList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);

                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < zongheTwoList.size(); i++) {
                    SscModel sscMedol1 = zongheTwoList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);

                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < zongheThreeList.size(); i++) {
                    SscModel sscMedol1 = zongheThreeList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < zongheFourList.size(); i++) {
                    SscModel sscMedol1 = zongheFourList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < zongheFiveList.size(); i++) {
                    SscModel sscMedol1 = zongheFiveList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
            } else if (oneToFiveBtn.isChecked()) {
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < diyiqiuList.size(); i++) {
                    SscModel sscMedol = diyiqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < dierqiuList.size(); i++) {
                    SscModel sscMedol = dierqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < disanqiuList.size(); i++) {
                    SscModel sscMedol = disanqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < disiqiuList.size(); i++) {
                    SscModel sscMedol = disiqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.?????????), "", "", Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????)));
                for (int i = 0; i < diwuqiuList.size(); i++) {
                    SscModel sscMedol = diwuqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
            } else {
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.??????), "", "", Utils.getString(R.string.???????????????)));
                for (int i = 0; i < qiansanList.size(); i++) {
                    SscModel sscMedol = qiansanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.??????), "", "", Utils.getString(R.string.???????????????)));
                for (int i = 0; i < zhongsanList.size(); i++) {
                    SscModel sscMedol = zhongsanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.??????), "", "",Utils.getString(R.string.???????????????)));
                for (int i = 0; i < housanList.size(); i++) {
                    SscModel sscMedol = housanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
            }
            sscAdapter.notifyDataSetChanged();
        }

        /*
        ????????????????????????
         */
    private void getMoney() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                memberMoneyText.setText(amount + "");
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    /*
    ???????????????pop???????????????
     */
    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop, null);
        lastQiShuTv = view.findViewById(R.id.last_qishu);
        newQiShuTv = view.findViewById(R.id.new_qihao);
        countDownEndSure = view.findViewById(R.id.countdown_pop_sure);
        countDownEndSure.setOnClickListener(this);
        CountDownEndPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//??????????????????
        CountDownEndPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!gfSureBetPop.isShowing() && !sureBetPop.isShowing() && !choosePlayTypePop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing() && !customPopupWindow.sscTodayOpenResultPop.isShowing() && !choosePlayTypePopGf.isShowing()&&!customPopupWindow.gameRulePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://??????????????????????????????,???????????????????????????(??????????????????????????????????????????????????????????????????,?????????????????????)
                    initOpenResult();//????????????????????????
                    break;
                case 2:
                    getMoney();
                    break;
            }
        }
    };

    private void bindView() {
        is_stop_tv=findViewById(R.id.is_stop_tv);
        if(isStart.equals("0")){
            is_stop_tv.setVisibility(View.VISIBLE);
        }else {
            is_stop_tv.setVisibility(View.GONE);
        }
        count_down_fail_loading_iv=findViewById(R.id.count_down_fail_loading_iv);
        fail_refresh_tv=findViewById(R.id.fail_refresh_tv);
        fail_refresh_tv.setOnClickListener(this);

        loadingLinear=findViewById(R.id.loading_linear);
        timeLinear=findViewById(R.id.time_linear);
        timeLoadLinear=findViewById(R.id.time_load_linear);
        stopTv=findViewById(R.id.stop_textview);
        stopTv.setOnClickListener(this);
        actionBarBack = findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);
        xingYongIma = findViewById(R.id.xingyong_image);
        hourText = findViewById(R.id.hour);
        minutesText = findViewById(R.id.minutes);
        secondsText = findViewById(R.id.seconds);

        openResultLinear = findViewById(R.id.ssc_open_resule_linear);
        sscNumTv1 = findViewById(R.id.num_one);
        sscNumTv2 = findViewById(R.id.num_two);
        sscNumTv3 = findViewById(R.id.num_three);
        sscNumTv4 = findViewById(R.id.num_four);
        sscNumTv5 = findViewById(R.id.num_five);
        myCornerTextViewArrayList.add(sscNumTv1);
        myCornerTextViewArrayList.add(sscNumTv2);
        myCornerTextViewArrayList.add(sscNumTv3);
        myCornerTextViewArrayList.add(sscNumTv4);
        myCornerTextViewArrayList.add(sscNumTv5);
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            myCornerTextViewArrayList.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayList.get(i).setCornerSize(50);
        }
        rotateImg = findViewById(R.id.rotate_image);
        rotateImg.setOnClickListener(this);
        nowQishuText = findViewById(R.id.now_qishu);
        lastQiShuText = findViewById(R.id.last_qishu);
        showClassfyLinear = findViewById(R.id.show_classfy_linear);
        showClassfyLinear.setOnClickListener(this);
        rightMenu = findViewById(R.id.right_menu);
        rightMenu.setOnClickListener(this);
        todayOpenResultLinear = findViewById(R.id.today_open_result);
        todayOpenResultLinear.setOnClickListener(this);


        choosePlayType = findViewById(R.id.choose_play_type);
        choosePlayType.setOnClickListener(this);
        popTargetLinear = findViewById(R.id.choose_pop_target);

        choosePlayTypeGf = findViewById(R.id.choose_play_type_gf);
        choosePlayTypeGf.setOnClickListener(this);
        //????????????????????????
        jianTv = findViewById(R.id.jian);
        officialAmountEt = findViewById(R.id.ssc_official_amount_edit);
        officialAmountEt.setText(officialAmonnt + "");
        officialAmountEt.setSelection(officialAmountEt.getText().length());//????????????????????????
        jiaTv = findViewById(R.id.jia);
        jianTv.setOnClickListener(this);
        jiaTv.setOnClickListener(this);
        officialAmountEt.addTextChangedListener(this);
        maxAmountTv = findViewById(R.id.max_amount);
        yuanBtn = findViewById(R.id.yuan);
        jiaoBtn = findViewById(R.id.jiao);
        yuanBtn.setOnClickListener(this);
        jiaoBtn.setOnClickListener(this);
        yuanBtn.performClick();
        jixuanGfTv = findViewById(R.id.official_jixuan_tv);
        betNumGfTv = findViewById(R.id.bet_num_gf);
        betAmountGfTv = findViewById(R.id.amount_num_gf);
        sureBetGf = findViewById(R.id.sure_bet_gf);
        sureBetGf.setOnClickListener(this);
        jixuanGfTv.setOnClickListener(this);


        gfRecycleLinear = findViewById(R.id.ssc_gf_recycle_linear);
        gfBottomLinear = findViewById(R.id.ssc_gf_bottom_linear);

        betShuoMingTv = findViewById(R.id.bet_shuoming);

        RecycleLinear = findViewById(R.id.ssc_normal_recycle_linear);
        BottomLinear = findViewById(R.id.ssc_normal_bottom_linear);
        gfAmountLinear = findViewById(R.id.gf_ssc_amount_linear);

        mRecy = findViewById(R.id.happy_bet_recycle);
        sscAdapter = new SscAdapter(sscModelList, selecterList);

        name = findViewById(R.id.name);
        name.setText(typename);

        View view = LayoutInflater.from(this).inflate(R.layout.pcdandan_play_type_pop, null);
        choosePlayTypePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        xingyongLinear = findViewById(R.id.xingyong_play);
        if(isopenOffice.equals("0")){
            xingyongLinear.setVisibility(View.INVISIBLE);
            xingyongLinear.setClickable(false);
        }else {
            xingyongLinear.setVisibility(View.VISIBLE);
            xingyongLinear.setClickable(true);
        }
        xingyongLinear.setOnClickListener(this);
        xingyongTv = findViewById(R.id.ll_caizhong_head2_right_tv);
        pcddTypeText = findViewById(R.id.pcdd_type);
        chooseImg = findViewById(R.id.choose_img);
        pcddTypeTextGf = findViewById(R.id.pcdd_type_gf);
        chooseImgGf = findViewById(R.id.choose_img_gf);
//        playTypeText=findViewById(R.id.play_type_text);
        memberMoneyText = findViewById(R.id.member_money);

        betNum = findViewById(R.id.bet_num);
        amountEdit = findViewById(R.id.amount_edit);
        betButton = findViewById(R.id.bet_button);
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);
        liangmianBtn = view.findViewById(R.id.happy8_liangmian);
        liangmianBtn.setText(Utils.getString(R.string.??????));
        liangmianBtn.setOnClickListener(this);
        oneToFiveBtn = view.findViewById(R.id.happy8_wuxing);
        oneToFiveBtn.setText(Utils.getString(R.string.????????????));
        oneToFiveBtn.setOnClickListener(this);
        qianzhonghouBtn = view.findViewById(R.id.happy8_zhengma);
        qianzhonghouBtn.setText(getString(R.string.?????????));
        qianzhonghouBtn.setOnClickListener(this);
        liangmianBtn.performClick();
        /*
        ????????????????????????
         */
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            myCornerTextViewArrayList.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayList.get(i).setCornerSize(50);
        }
        allMoDelList.add(zongheList);
        allMoDelList.add(zongheOneList);
        allMoDelList.add(zongheTwoList);
        allMoDelList.add(zongheThreeList);
        allMoDelList.add(zongheFourList);
        allMoDelList.add(zongheFiveList);
        allMoDelList.add(diyiqiuList);
        allMoDelList.add(dierqiuList);
        allMoDelList.add(disanqiuList);
        allMoDelList.add(disiqiuList);
        allMoDelList.add(diwuqiuList);
        allMoDelList.add(qiansanList);
        allMoDelList.add(zhongsanList);
        allMoDelList.add(housanList);
    }

    private void initAllPop() {
        //????????????pop????????????
        customPopupWindow.initClassfyPop(this, this);
        //????????????pop???????????? typename????????????????????????button
        customPopupWindow.initClassfyData(this, typename);
//        ????????????pop
        customPopupWindow.initMenuPop(this, this);
        //?????? ???????????? typename:????????? game:??????game  tyoe_id: ??????type_id
        customPopupWindow.toBetNote(this, typename, game, type_id);
        //?????? ????????????
        customPopupWindow.toInvestCenter(this);
        //??????????????????
        customPopupWindow.tovVipCenter(this);
        //?????? ???????????? type_id: ???????????????typ_id  lotteryClassId: ?????????????????????id
        customPopupWindow.toOpenResult(this, type_id, game);
        //game ????????????(??????????????????????????????????????????????????????) typename: ?????????: ???????????????????????????????????????
        customPopupWindow.initGameRule(this, game, typename, 0,false);
        //??????????????????pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        //????????????pop???????????????
        customPopupWindow.initSscTodayResultPop(this, game, type_id,false);
        //??????????????????
        customPopupWindow.toTwoChangLongAty(this, game, type_id);
        //??????????????????
        customPopupWindow.toTodayWinLose(this, game, type_id);
        //??????????????????
        customPopupWindow.toOnlineKf(this);
//        jgTime = customPopupWindow.getJgTIme(game, type_id);
        //????????????????????????
        jgTime = customPopupWindow.getJgTIme(game, type_id);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //???????????????????????????  (?????????????????????)
        if (isCrate) {
            initOfficialRecycle();
            initGfChooseTypePop();
        }
        if(runnableTime!=null){
        handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 300);
        isCrate = false;
        if (isWaitOpen) {
            if(runnableRandom!=null){
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
        }
        if (isWaitOpen) {
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
        }
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if (alpha == 1f) {
            CountDownEndPop.dismiss();
        }
        if(runnableZj!=null){
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj, 2000);
    }
    /*
    ????????????recycleVIew,???????????????
     */
    private void initOfficialRecycle() {
        officialbetRecy = findViewById(R.id.happy_bet_gf_recycle);
        sscOfficialAdapter = new SscOfficialAdapter(this, sscOfiicialBetMedolArrayList, officialSelectorList, sscofficialFourModelList);
        officialbetRecy.setLayoutManager(new LinearLayoutManager(this));
        officialbetRecy.setAdapter(sscOfficialAdapter);
        //????????????itemTwo????????????
        sscOfficialAdapter.setOnItemFourClickListener(this);
        //????????????itemOne????????????
        sscOfficialAdapter.setOnItemOneClickListener(this);
        sscOfficialAdapter.setOnQuickClickLintener(this);
    }

    /*
    ????????????????????????pop
     */
    private void initGfChooseTypePop() {
        // -------------------------------------------------------????????????-----------------------------------------------------------------
        View view = LayoutInflater.from(this).inflate(R.layout.ssc__official_choose_popwindow, null);
        recyOne = view.findViewById(R.id.ssc_official_chooose_recycle);
        sscOfficialChooseAdapter = new SscOfficialChooseAdapter(sscListOne);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyOne.setLayoutManager(gridLayoutManager);
        recyOne.setAdapter(sscOfficialChooseAdapter);
        choosePlayTypePopGf = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        choosePlayTypePopGf.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePopGf.setTouchable(true);//??????????????????
        choosePlayTypePopGf.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() && !sureBetPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(SscBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImgGf.startAnimation(loadAnimation);

            }
        });
        choosePlayTypeGf.setOnClickListener(this);


        //??????????????????pop recycleView????????????(????????????)
        sscOfficialChooseAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemClick(position);

            }
        });

        // -------------------------------------------------------????????????-----------------------------------------------------------------

        recyTwo = view.findViewById(R.id.ssc_official_two_recycle);
        sscOffcialChooseTwoAdapter = new SscOffcialChooseTwoAdapter(SscBetActivity.this, sscListTwo,/*sscListThree,*/sscListThreeAll);
        recyTwo.setLayoutManager(new LinearLayoutManager(SscBetActivity.this));
        recyTwo.setAdapter(sscOffcialChooseTwoAdapter);
        getGfPlayType();
        sscOffcialChooseTwoAdapter.setOnThreeItemClick(new SscOffcialChooseTwoAdapter.OnThreeItemClick() {
            @Override
            public void threeClick(View view, ArrayList<SscOfficialChooseMedol> threeList, int position) {
                SscOfficialChooseMedol sscOfficialChooseMedol = threeList.get(position);
                threeItemClick(sscOfficialChooseMedol);
            }
        });


    }

    /*
    ????????????????????????
     */
    public void threeItemClick(SscOfficialChooseMedol sscOfficialChooseMedol) {
        typeThreeName = sscOfficialChooseMedol.getName();
        pcddTypeTextGf.setText(typeOneName + typeThreeName);
        String remake = sscOfficialChooseMedol.getRemake();
        remark = remake.substring(0, remake.indexOf(";"));
        if (yuanBtn.isChecked()) {
            odds = sscOfficialChooseMedol.getOdds();
        } else {
            odds = new BigDecimal(sscOfficialChooseMedol.getOdds()).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP) + "";
        }
        officialAmonnt = 1;
        officialAmountEt.setText("1");
        String remarkStr = remark + Utils.getString(R.string.??????????????????) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.???);
        betShuoMingTv.setText(Html.fromHtml(remarkStr));
        maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.???)));
        threeId = sscOfficialChooseMedol.getId();
        initAllStatus();
        getPlayRuleListFour();
        if (choosePlayTypePopGf != null && choosePlayTypePopGf.isShowing()) {
            choosePlayTypePopGf.dismiss();
        }
    }

    /*
  ??????????????????pop????????????(????????????)
 */
    public void itemClick(int position) {
        if(sscListOne.size()!=0){
            if (officialSelectorList != null && officialSelectorList.size() != 0) {
                officialSelectorList.clear();
            }
            SscOfficialChooseMedol sscOfficialChooseMedol1 = sscListOne.get(position);
            String id = sscOfficialChooseMedol1.getId();
            sscListTwo.clear();
            typeOneName = sscOfficialChooseMedol1.getName();
            pcddTypeTextGf.setText(typeOneName + getString(R.string.??????));
//        sscListThree.clear();
            for (int i = 0; i < sscListTwoAll.size(); i++) {
                String parentId = sscListTwoAll.get(i).getParentId();
                if (parentId.equals(id)) {
                    SscOfficialChooseMedol sscOfficialChooseMedol = sscListTwoAll.get(i);
                    sscListTwo.add(new SscOfficialChooseTwoMedol(sscOfficialChooseMedol.getId(), sscOfficialChooseMedol.getName(), sscOfficialChooseMedol.getOdds(), sscOfficialChooseMedol.getParentId()));
                }
            }
            sscOffcialChooseTwoAdapter.notifyDataSetChanged();

            SscOfficialChooseTwoMedol sscOfficialChooseTwoMedol = sscListTwo.get(0);
            String idTwo = sscOfficialChooseTwoMedol.getId();
            ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList = sscOfficialChooseTwoMedol.getSscOfficialChooseMedolArrayList();
            for (int j = 0; j < sscListThreeAll.size(); j++) {
                SscOfficialChooseMedol sscOfficialChooseMedol = sscListThreeAll.get(j);
                if (sscOfficialChooseMedol.getParentId().equals(idTwo)) {
                    //??????parentid??????model,????????????????????????medol?????????sscOfficialChooseMedolArrayList???
                    if (!sscOfficialChooseMedolArrayList.contains(sscOfficialChooseMedol)) {
                        sscOfficialChooseMedolArrayList.add(sscOfficialChooseMedol);
                    }
                    sscOfficialChooseTwoMedol.setSscOfficialChooseMedolArrayList(sscOfficialChooseMedolArrayList);
                }
            }

            if (sscOfficialChooseMedolArrayList.size() != 0) {
                threeId = sscOfficialChooseMedolArrayList.get(0).getId();
                typeThreeName = sscOfficialChooseMedolArrayList.get(0).getName();
                pcddTypeTextGf.setText(typeOneName + typeThreeName);
                officialAmonnt = 1;
                officialAmountEt.setText("1");
                remark = sscOfficialChooseMedolArrayList.get(0).getRemake();
                remark = remark.substring(0, remark.indexOf(";"));
                if (yuanBtn.isChecked()) {
                    odds = sscOfficialChooseMedolArrayList.get(0).getOdds();
                } else {
                    odds = new BigDecimal(sscOfficialChooseMedolArrayList.get(0).getOdds()).divide(new BigDecimal("10")).setScale(2, RoundingMode.HALF_UP) + "";
                }

                String remarkStr = remark + Utils.getString(R.string.??????????????????) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.???);
                betShuoMingTv.setText(Html.fromHtml(remarkStr));
                maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.???)));

                allBetAmountStr = "0";
                allBetNum = 0;
                betAmountGfTv.setText("0");
                betNumGfTv.setText("0");
                if (yuanBtn != null) {
                    yuanBtn.performClick();
                }
            }
            getPlayRuleListFour();
        }


    }

    /*
    ?????????????????? choosePop??????
     */
    private void getGfPlayType() {
        //REQUEST_wt11
        HashMap<String, Object> data = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String token = SharePreferencesUtil.getString(this, "token", "");
        String domain = SharePreferencesUtil.getString(this, "domain", "");
        data.put("user_id", user_id);
        data.put("game", game);
        data.put("typeId", type_id);
        data.put("source", 2);
        data.put("token", token);
        Utils.docking(data, RequestUtil.REQUEST_wt11, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                //????????????
                JSONArray playRuleListOne = jsonObject1.getJSONArray("playRuleListOne");
                for (int i = 0; i < playRuleListOne.size(); i++) {
                    JSONObject jsonObject = playRuleListOne.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String id = jsonObject.getString("id");
                    BigDecimal odds = jsonObject.getBigDecimal("odds");
                    String parentId = jsonObject.getString("parentId");
                    SscOfficialChooseMedol sscOfficialChooseMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscListOne.add(sscOfficialChooseMedol);
                    if (i == 0) {
                        sscOfficialChooseMedol.setStatus(1);
//                        pcddTypeTextGf.setText(name);
                        typeOneName = name;
                    }

                }
                //????????????
                JSONArray playRuleListTwo = jsonObject1.getJSONArray("playRuleListTwo");
                for (int i = 0; i < playRuleListTwo.size(); i++) {
                    JSONObject jsonObject = playRuleListTwo.getJSONObject(i);
                    String parentId = jsonObject.getString("parentId");//??????id
                    String id = jsonObject.getString("id");//??????id
                    String name = jsonObject.getString("name");
                    BigDecimal odds = jsonObject.getBigDecimal("odds");
                    SscOfficialChooseMedol sscOfficialChooseMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscListTwoAll.add(sscOfficialChooseMedol);
                }

                //????????????
                JSONArray playRuleListThree = jsonObject1.getJSONArray("playRuleListThree");
                for (int i = 0; i < playRuleListThree.size(); i++) {
                    JSONObject jsonObject = playRuleListThree.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String remark = jsonObject.getString("remark");
                    String id = jsonObject.getString("id");
                    String parentId = jsonObject.getString("parentId");
                    BigDecimal odds = jsonObject.getBigDecimal("odds").setScale(2, RoundingMode.HALF_UP);
                    SscOfficialChooseMedol sscOfficialChooseChildMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscOfficialChooseChildMedol.setRemake(remark);
                    sscListThreeAll.add(sscOfficialChooseChildMedol);
                    if (i == 0) {
                        typeThreeName = name;
                    }
                }
                itemClick(0);//?????????????????????item???????????????
                sscOfficialChooseAdapter.notifyDataSetChanged();
                sscOffcialChooseTwoAdapter.notifyDataSetChanged();
//                sscOffcialChooseThreeAdapter.notifyDataSetChanged();
                pcddTypeTextGf.setText(typeOneName + typeThreeName);
                playRuleListFour = jsonObject1.getJSONArray("playRuleListFour");
                getPlayRuleListFour();
            }

            @Override
            public void failed(MessageHead messageHead) {
                String info = messageHead.getInfo();
            }
        });
    }

    /*
    ???????????????????????????
     */
    public void getPlayRuleListFour() {
        sscOfiicialBetMedolArrayList.clear();
        sscofficialFourModelList.clear();
        if (playRuleListFour != null && playRuleListFour.size() != 0) {
            for (int i = 0; i < playRuleListFour.size(); i++) {
                JSONObject jsonObject = playRuleListFour.getJSONObject(i);
                String codes = jsonObject.getString("codes");
                String id = jsonObject.getString("id");
                String isQuick = jsonObject.getString("isQuick");
                String name = jsonObject.getString("name");
                String playRuleId = jsonObject.getString("playRuleId");
                if (playRuleId.equals(threeId)) {
                    SscOfiicialBetMedol sscOfiicialBetMedol = new SscOfiicialBetMedol(codes, id, isQuick, name, playRuleId);
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {//???????????????5???item??????????????????,?????????????????????
                        sscOfiicialBetMedol.setTypeOneName(Utils.getString(R.string.????????????));
                    }
                    sscOfiicialBetMedolArrayList.add(sscOfiicialBetMedol);
                }
                sscOfficialAdapter.notifyDataSetChanged();
            }
        }

        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            String id = sscOfiicialBetMedol.getId();
            String isQuick = sscOfiicialBetMedol.getIsQuick();
            String playRuleId = sscOfiicialBetMedol.getPlayRuleId();
            String name = sscOfiicialBetMedol.getName();
            String codes = sscOfiicialBetMedol.getCodes();
            String[] split = codes.split(",");
            List<String> strings = Arrays.asList(split);
            SscOfiicialBetMedol ofiicialBetMedol = null;
            for (int j = 0; j < strings.size(); j++) {
                String code = strings.get(j);
                ofiicialBetMedol = new SscOfiicialBetMedol(code, id, isQuick, name, playRuleId);
//                if(seletedMedolMap.size()!=0&&seletedMedolMap.get(id+code)!=null){
//                    if((ofiicialBetMedol.getId()+ofiicialBetMedol.getCodes()).equals((seletedMedolMap.get(id+code).getId()+seletedMedolMap.get(id+code).getCodes()))){
//                        ofiicialBetMedol.setStatus(1);
//                    }
//                }
                sscofficialFourModelList.add(ofiicialBetMedol);
            }

        }
    }

    /*
    ???????????????
     */
    private void getCountTime(GifImageView count_down_fail_loading_iv) {
        if(count_down_fail_loading_iv!=null){
            count_down_fail_loading_iv.setVisibility(View.VISIBLE);
        }
        if(runnableTime!=null){
            handler.removeCallbacks(runnableTime);
        }
        showLoadingLinear();
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        data.put("type_id", type_id);
        data.put("source", 2);
        Utils.docking(data, RequestUtil.REQUEST_18r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content){
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                if(runnableTime!=null){
                    handler.post(runnableTime);
                }
                failCount = 0;
                failCountObservable.onNext(failCount);
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//?????????????????????
                nowQishu = jsonObject.getString("qishu");//????????????
                if (StringMyUtil.isEmptyString(nowQishu)) {//????????????,????????????
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER, 0, 0);
                    stopTv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.7f);
                    nowQishuText.setText(Utils.getString(R.string.???));
                    lastQiShuText.setText("-- -- -- "+Utils.getString(R.string.???????????????));
                    hourText.setText("--");
                    minutesText.setText("--");
                    secondsText.setText("--");
                    handler.removeCallbacks(runnableRandom);
                    handler.removeCallbacks(runnableRequestOpen);
                    handler.removeCallbacks(runnableTime);
                    openResultLinear.setVisibility(View.INVISIBLE);
                } else {
                    stopTv.setVisibility(View.GONE);
                    if (openResultLinear.getVisibility() != View.VISIBLE) {
                        openResultLinear.setVisibility(View.VISIBLE);
                    }
                    if(runnableRandom==null){
                        handler.postDelayed(runnableRandom,150);
                    }
                    if(runnableRequestOpen==null){
                        handler.postDelayed(runnableRequestOpen,jgTime);
                    }
                    if(runnableTime==null){
                        handler.postDelayed(runnableTime,300);
                    }
                    tqtime = jsonObject.getString("tqtime");//????????????(?????????????????????????????????)
                    nowQishuText.setText(nowQishu + Utils.getString(R.string. ???));
                    lastQiShuText.setText((Long.parseLong(nowQishu) - 1) + " "+Utils.getString(R.string.???????????????));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//?????????????????????
//                        time = Long.parseLong(Utils.getFileData("time"));//???????????????
                        long nowTime = System.currentTimeMillis();//????????????
//                        shijiancha = time- nowTime;//?????????????????????????????????
                        shijiancha = SharePreferencesUtil.getLong(SscBetActivity.this, "shijiancha", 0l);//?????????????????????????????????

                        countTime = millionSeconds + shijiancha - nowTime - (Long.parseLong(tqtime) * 1000);
//                        if(num==1){
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showTimeLInear();

                                }
                            },600);
//                        }
                    /*else {
                            timeLoadLinear.setVisibility(View.GONE);
                            timeLinear.setVisibility(View.VISIBLE);
                        }*/
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (num == 1) {//?????????????????????????????????,???????????????????????????????????????(?????????????????????,??????????????????,??????????????????????????????)
                        Message message = Message.obtain();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                    num++;
                }

            }

            @Override
            public void failed(MessageHead messageHead) {
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                failCount ++;
                failCountObservable.onNext(failCount);
                showToast(messageHead.getInfo());
                showLoadingLinear();
                if(runnableTime!=null){
                    handler.post(runnableTime);
                }

            }
        });
    }

        private void showLoadingLinear() {
            if (isStart.equals("0")) {
                timeLoadLinear.setVisibility(View.GONE);
                timeLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.VISIBLE);
            } else {
                timeLoadLinear.setVisibility(View.VISIBLE);
                timeLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.GONE);
            }
        }

        private void showTimeLInear() {
            if(isStart.equals("0")){
                is_stop_tv.setVisibility(View.VISIBLE);
                timeLoadLinear.setVisibility(View.GONE);
                timeLinear.setVisibility(View.GONE);
            }else {
                timeLinear.setVisibility(View.VISIBLE);
                timeLoadLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.GONE);
            }
        }

        /*
        ??????????????????
         */
    private void initOpenResult() {
        if(runnableRequestOpen!=null){
            handler.removeCallbacks(runnableRequestOpen);
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id", type_id);
        data.put("pageNo", 1);
        data.put("pageSize", 20);
        data.put("flag", 1);
        Utils.docking(data, RequestUtil.REQUEST_19r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                failCount=0;
                failCountObservable.onNext(failCount);
                if(runnableRequestOpen!=null){
                    handler.post(runnableRequestOpen);
                }
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("sscaiLotterylist");
                if(happytenLotterylist.size()==0){
                    showToast(getString(R.string.????????????????????????));
                    return;
                }
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(0);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");
                    if (!StringMyUtil.isEmptyString(nowQishu)) {
                        if (Long.parseLong(lotteryqishu) == (Long.parseLong(nowQishu) - 1)) {
                            isWaitOpen = false;
                            String lotteryNo = jsonObject.getString("lotteryNo");
                            String[] split = lotteryNo.split(",");
                            openResultList = Arrays.asList(split);
                            for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                                myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                            }
                        } else {
                            isWaitOpen = true;
                        }
                    }


            }

            @Override
            public void failed(MessageHead messageHead) {
                failCount++;
                failCountObservable.onNext(failCount);
                if(runnableRequestOpen!=null){
                    handler.post(runnableRequestOpen);
                }
            }
        });
    }

    /*
    ????????????
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_textview:
                showToast(Utils.getString(R.string.??????????????????));
                break;
            case R.id.official_jixuan_tv:
                if (jixuanGfTv.getText().equals(getString(R.string.??????))) {
                    initAllStatus();
                    jixuanGfTv.setText(getString(R.string.??????));
                    officialbetRecy.getLayoutManager().scrollToPosition(0);
                } else {
                    int max = 0;//??????item?????????position(?????????????????????sscOfiicialBetMedolArrayList???position)
                    officialSelectorList.clear();
                    for (int i = 0; i < sscofficialFourModelList.size(); i++) {
                        sscofficialFourModelList.get(i).setStatus(0);
                    }
                    sscOfficialAdapter.notifyDataSetChanged();
                    if (typeOneName.equals(getString(R.string.????????????))) {
                        max = randomOneItem(1);
                        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                    } else if (typeOneName.equals(getString(R.string.??????))) {
                        max = randomOneItem(1);
                        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                    } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
                        if (typeThreeName.equals(getString(R.string.????????????))) {
//                            max = randomMoreItemOneNum();
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 2);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 2);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size() * 9;
                        }
                    } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
                        if (typeThreeName.equals(getString(R.string.??????))) {
                            randomMoreItemOneNum();
//                            max =  randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 3);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 3);
                        } else if (typeThreeName.equals(getString(R.string.??????))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                            allBetNum = allBetNum * 2;
                        } else if (typeThreeName.equals(getString(R.string.??????))) {
                            max = randomOneItem(3);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
                        } else if (typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size() * 54;
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size();
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        }
                    } else if (typeOneName.equals(getString(R.string.??????))) {
                        if (typeThreeName.equals(getString(R.string.??????))) {
//                            max =   randomMoreItemOneNum();//??????????????????item????????????,?????????????????????
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.??????24))) {
                            max = randomOneItem(4);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 4);
                        } else if (typeThreeName.equals(getString(R.string.??????12))) {
                            randomMoreItemMoreNum(2);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.??????6))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.??????4))) {
//                            max =   randomMoreItemOneNum();
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 1, 2);
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        }
                    } else if (typeOneName.equals(getString(R.string.??????))) {
                        if (typeThreeName.equals(getString(R.string.??????))) {
                            randomMoreItemOneNum();
//                            max =    randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.??????120))) {
                            max = randomOneItem(5);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 5);
                        } else if (typeThreeName.equals(getString(R.string.??????60))) {
                            max = randomMoreItemMoreNum(3);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 3, 2);
                        } else if (typeThreeName.equals(getString(R.string.??????30))) {
                            max = randomMoreItemMoreNumWuXin30(2);
                            allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.??????20))) {
                            max = randomMoreItemMoreNum(2);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.??????10)) || typeThreeName.equals(getString(R.string.??????5))) {
                            randomMoreItemOneNum();
//                            max =   randomMoreItemOneNum();
                            allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSelectorList, typeThreeName, 1, 2);
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                            max = randomOneItem(3);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
                        } else if (typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        }
                    }
                    betNumGfTv.setText(allBetNum + "");
                    jixuanGfTv.setText(getString(R.string.??????));
                    BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
                    if (jiaoBtn.isChecked()) {
                        bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
                    }
                    allBetAmountStr = bigDecimal + "";
                    betAmountGfTv.setText(allBetAmountStr);
                    sscOfficialAdapter.notifyDataSetChanged();
                    officialbetRecy.getLayoutManager().scrollToPosition(max);
//                    officialbetRecy.smoothScrollToPosition(max);

                }
                //???????????? ?????????,???????????????????????????????????????
                if (onRandomListener != null) {
                    onRandomListener.onRandomClilck(officialSelectorList);
                }
                break;
            case R.id.yuan:
                yuanBtn.setClickable(false);
                jiaoBtn.setClickable(true);
                String toString = officialAmountEt.getText().toString();
                BigDecimal bigDecimal = BigDecimal.ZERO;//???????????????bigDecimal???
                if (StringMyUtil.isEmptyString(toString)) {
                    bigDecimal = BigDecimal.ZERO;
                } else {
                    bigDecimal = new BigDecimal(toString)/*.setScale(2, RoundingMode.HALF_UP)*/;
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal.multiply(new BigDecimal(odds + "")/*.setScale(2,RoundingMode.HALF_UP)*/);
                    String str = Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.???);
                    maxAmountTv.setText(Html.fromHtml(str));
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    String remarkStr = remark + Utils.getString(R.string.??????????????????) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.???);
                    betShuoMingTv.setText(Html.fromHtml(remarkStr));
                }
                //?????????????????????
                String num = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                allBetAmountStr = num;
                if (betAmoumtTv != null) {
                    betAmountGfTv.setText(num);
                }
                break;
            case R.id.jiao:
                yuanBtn.setClickable(true);
                jiaoBtn.setClickable(false);
                String toString1 = officialAmountEt.getText().toString();
                BigDecimal bigDecimal1 = BigDecimal.ZERO;//???????????????bigDecimal???
                if (StringMyUtil.isEmptyString(toString1)) {
                    bigDecimal1 = BigDecimal.ZERO;
                } else {
                    bigDecimal1 = new BigDecimal(toString1)/*.setScale(2, RoundingMode.HALF_UP)*/;
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal1.multiply(new BigDecimal(odds + "").divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP));
                    String str = Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.???);
                    maxAmountTv.setText(Html.fromHtml(str));
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal1.multiply(new BigDecimal(odds + "").divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP));
                    String remarkStr = remark + Utils.getString(R.string.??????????????????) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.???);
                    betShuoMingTv.setText(Html.fromHtml(remarkStr));
                }
                //?????????????????????
                String num1 = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
//                String  num1=   new BigDecimal(allBetAmountStr).divide(new BigDecimal(10+""))+"";
                allBetAmountStr = num1;
                betAmountGfTv.setText(num1);
                break;
            case R.id.jian:
                if (officialAmonnt == 1) {
                    return;
                } else {
                    officialAmonnt--;
                }
                officialAmountEt.setText(officialAmonnt + "");
                //?????????????????????
                if (yuanBtn.isChecked()) {
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.???)));
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                } else {
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")).divide(new BigDecimal(10 + "")) + "</font>" + Utils.getString(R.string.???)));
                }
                officialAmountEt.setSelection(officialAmountEt.getText().length());//????????????????????????
                officialAmountEt.requestFocus();//????????????
                betAmountGfTv.setText(allBetAmountStr);

                break;
            case R.id.jia:
                officialAmonnt++;
                //?????????
                officialAmountEt.setText(officialAmonnt + "");
                //?????????????????????   //??????????????????
                if (yuanBtn.isChecked()) {
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.???)));
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                } else {
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")).divide(new BigDecimal(10 + "")) + "</font>" + Utils.getString(R.string.???)));
                }

                officialAmountEt.setSelection(officialAmountEt.getText().length());//????????????????????????
                officialAmountEt.requestFocus();//????????????
                betAmountGfTv.setText(allBetAmountStr);
                break;
            case R.id.sure_bet_gf:
                int sizeGf = officialSelectorList.size();
                String editTextGf = officialAmountEt.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.?????????????????????));
                }
                else if (sizeGf == 0) {
                    showToast(getString(R.string.???????????????));
                } else if (StringMyUtil.isEmptyString(editTextGf)) {
                    showToast(Utils.getString(R.string.?????????????????????));
                } else {
                    sureBetGf.setClickable(false);
                    if (allBetAmountStr.equals("0")) {
                        if (yuanBtn.isChecked()) {
                            allBetAmountStr = allBetNum * 2 + "";
                        } else {
                            BigDecimal bigDecimal2 = new BigDecimal(allBetNum * 2).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP);
                            allBetAmountStr = bigDecimal2 + "";
                        }
                    }
                    if (yuanBtn.isChecked()) {
                        gfBetAmoumtTv.setText("2");
                    } else {
                        gfBetAmoumtTv.setText("0.2");
//                        allBetAmountStr  =   new BigDecimal(allBetAmountStr)/*.divide(new BigDecimal(10+""))*/+"";
                    }
                    ArrayList<SscOfiicialBetMedol> list = new ArrayList<>();
                    initGfSureBetPopData(list); //????????????pop???????????????
                    gfLotteryNameTv.setText(name.getText().toString() + "-" + typeOneName + typeThreeName);//?????????
                    gfQishuTv.setText(nowQishu);//????????????
                    gfAllBetNumTv.setText(allBetNum + "");
                    gfAllBetAmountTv.setText(allBetAmountStr);
                    gfSureBetPop.showAtLocation(sureBetGf, Gravity.CENTER, 0, 0);
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.5f);
                    hintKbTwo();//???????????????

                }
                break;
            case R.id.gf_sure_bet_cancel:
                if(gfSureBetPop!=null){
                    gfSureBetPop.dismiss();
                }
                break;
            case R.id.gf_sure_bet_sure:
                gfSureButton.setClickable(false);
                gfSureBetPop.dismiss();
                requestBetGf(officialAmountEt.getText().toString());
                break;
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://????????????????????????pop
                customPopupWindow.showClassfyPop(showClassfyLinear, this);
                break;
            case R.id.xingyong_play:
                if(isopenOffice.equals("0")){
                    showToast(getString(R.string.???????????????));
                }else {
                    if (xingyongTv.getText().equals(getString(R.string.????????????))) {
                        choosePlayTypeGf.setVisibility(View.VISIBLE);
                        gfRecycleLinear.setVisibility(View.VISIBLE);
                        gfBottomLinear.setVisibility(View.VISIBLE);
                        gfAmountLinear.setVisibility(View.VISIBLE);
                        choosePlayType.setVisibility(View.GONE);
                        RecycleLinear.setVisibility(View.GONE);
                        BottomLinear.setVisibility(View.GONE);
                        xingyongTv.setText(getString(R.string.????????????));
                    } else {
                        choosePlayTypeGf.setVisibility(View.GONE);
                        gfRecycleLinear.setVisibility(View.GONE);
                        gfBottomLinear.setVisibility(View.GONE);
                        gfAmountLinear.setVisibility(View.GONE);
                        choosePlayType.setVisibility(View.VISIBLE);
                        RecycleLinear.setVisibility(View.VISIBLE);
                        BottomLinear.setVisibility(View.VISIBLE);
                        xingyongTv.setText(getString(R.string.????????????));
                    }
                    Animation loadAnimationXy = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                    ;
                    loadAnimationXy.setFillAfter(true);//???????????????,????????????????????????
                    xingYongIma.startAnimation(loadAnimationXy);
                }

//                if(clickedNum==1){
//                    initOfficialRecycle();
//                    initGfChooseTypePop();
//                }
//                clickedNum++;
                break;
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu, this);
                break;
            case R.id.today_open_result:
                customPopupWindow.initSscTodayResultData(this, game, type_id, todayOpenResultLinear);
////                customPopupWindow.showHappy8TodayResultPop(todayOpenResultLinear,this);
                break;
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                loadAnimation.setFillAfter(true);//???????????????,????????????????????????
                chooseImg.startAnimation(loadAnimation);
                break;
            case R.id.choose_play_type_gf:
                choosePlayTypePopGf.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation anima = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                anima.setFillAfter(true);//???????????????,????????????????????????
                chooseImgGf.startAnimation(anima);
                break;
            case R.id.happy8_liangmian:
                requestRule();
                if (mRecy != null && mRecy.getLayoutManager() != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                choosePlayTypePop.dismiss();

                betNum.setText(selecterList.size() + "");
//                playTypeText.setText(hunheRb.getText().toString());
                pcddTypeText.setText(liangmianBtn.getText().toString());

                break;
            case R.id.happy8_wuxing:
                requestRule();
                choosePlayTypePop.dismiss();
                if (mRecy != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                betNum.setText(selecterList.size() + "");
//                playTypeText.setText(boseRb.getText().toString());
                pcddTypeText.setText(oneToFiveBtn.getText().toString());

                break;
            case R.id.happy8_zhengma:
                requestRule();
                choosePlayTypePop.dismiss();
                if (mRecy != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                betNum.setText(selecterList.size() + "");
                pcddTypeText.setText(qianzhonghouBtn.getText().toString());

                break;
            case R.id.random_button:
                int max = 0;
                if (randomButton.getText().toString().equals(getString(R.string.??????))) {
                    xingyongPlayRandom();
                } else {
                    for (int i = 0; i < sscModelList.size(); i++) {
                        sscModelList.get(i).setStatus(0);
                    }
                    diyiqiuSelectList.clear();
                    dierqiuSelectList.clear();
                    disanqiuSelectList.clear();
                    disiqiuSelectList.clear();
                    diwuqiuSelectList.clear();
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size + "");
                    randomButton.setText(getString(R.string.??????));
                }
                for (int i = 0; i < selecterList.size(); i++) {
                    String rule_id = selecterList.get(i).getRule_id();
                    for (int j = 0; j < sscModelList.size(); j++) {
                        SscModel sscModel = sscModelList.get(j);
                        if (sscModel.getRule_id().equals(rule_id)) {
                            if (j > max) {
                                max = j;
                            }
                        }
                    }
                }
                sscAdapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            case R.id.bet_button:
                if(Utils.isFastClick()){
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.?????????????????????));
                }
                else if (size == 0) {
                    showToast(getString(R.string.???????????????));
                } else if (StringMyUtil.isEmptyString(editText)) {
                    showToast(Utils.getString(R.string.?????????????????????));
                } else {
                    Collections.sort(selecterList);
                    List<TouzhuModel> touzhuList = new ArrayList<>();
                    for (int i = 0; i < selecterList.size(); i++) {
                        TouzhuModel touzhuModel = new TouzhuModel();
                        List<String> groupname = Arrays.asList(selecterList.get(i).getGroupname().split("-"));
                        if (groupname.size()==2){
                            touzhuModel.setGroupname(groupname.get(1));
                        } else if (groupname.size()==3){
                            touzhuModel.setGroupname(groupname.get(1)+"-"+groupname.get(2));
                        }else {
                            touzhuModel.setGroupname(groupname.get(0));
                        }
                        touzhuModel.setName(selecterList.get(i).getBetType());
                        touzhuModel.setId(String.valueOf(selecterList.get(i).getRule_id()));
                        touzhuModel.setMoney(amountEdit.getText().toString());
                        touzhuList.add(touzhuModel);
                    }
                    Intent intent = new Intent();
                    intent.setClass(SscBetActivity.this, TouzhuActivity.class);
                    intent.putExtra("touzhuList", (Serializable) touzhuList);
                    intent.putExtra("game", String.valueOf(game));
                    intent.putExtra("type_id", String.valueOf(type_id));
                    intent.putExtra("money", amountEdit.getText().toString());
                    intent.putExtra("qishu", nowQishu);
                    intent.putExtra("index", "");
                    intent.putExtra("ma", "");
                    startActivityForResult(intent, ReqTouzhu);

                }
                break;
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                requestBet(selecterList.size(), amountEdit.getText().toString());
                break;
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            case R.id.bet_actionbar_back:
                finish();
                break;
            case R.id.fail_refresh_tv:
                getCountTime(count_down_fail_loading_iv);
                initOpenResult();
                break;
            default:
                break;
        }
    }

    //???????????????????????????????????? ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // ??????????????????????????????????????????
            for (int i = 0; i < sscModelList.size(); i++) {
                sscModelList.get(i).setStatus(0);
            }
            selecterList.clear();
            diyiqiuSelectList.clear();
            dierqiuSelectList.clear();
            disanqiuSelectList.clear();
            disiqiuSelectList.clear();
            diwuqiuSelectList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size + "");
            sscAdapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.??????));
            getMoney();//?????????,??????????????????
        }

    }

    /*
    ??????????????????????????????
     */
    private void xingyongPlayRandom() {
        selecterList.clear();//?????????????????????????????????(?????????????????????)
        if(sscModelList.size()==0){
            return;
        }
        int random = new Random().nextInt(sscModelList.size());
        sscModelList.get(random).setStatus(1);
        selecterList.add(sscModelList.get(random));
        int size = selecterList.size();
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.??????));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            //???????????????title?????????sscMedolList???,?????????????????????title,???????????????,?????????????????????title,????????????
            if (betType.equals(Utils.getString(R.string.???????????????)) || betType.equals(Utils.getString(R.string.?????????)) || betType.equals(Utils.getString(R.string.?????????)) || betType.equals(Utils.getString(R.string.?????????)) || betType.equals(Utils.getString(R.string.?????????)) || betType.equals(Utils.getString(R.string.?????????)) || betType.equals(Utils.getString(R.string.??????)) || betType.equals(Utils.getString(R.string.??????)) || betType.equals(Utils.getString(R.string.??????))) {
                xingyongPlayRandom();
            } else {//???????????????title,????????????
                break;
            }
        }

    }

    /**
     * ??????item??????,????????????????????????
     *
     * @param ballCount ?????????????????????(?????????????????????)
     */
    public int randomOneItem(int ballCount) {
        ArrayList<SscOfiicialBetMedol> allMedolList = new ArrayList<>();
        for (int j = 0; j < sscofficialFourModelList.size(); j++) {
            allMedolList.add(sscofficialFourModelList.get(j));
        }

        for (int k = 0; k < ballCount; k++) {
            int random = new Random().nextInt(allMedolList.size());
            SscOfiicialBetMedol sscOfiicialBetMedol = allMedolList.get(random);
            for (int j = 0; j < sscofficialFourModelList.size(); j++) {
                SscOfiicialBetMedol ofiicialBetMedol = sscofficialFourModelList.get(j);
                if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                    officialSelectorList.add(ofiicialBetMedol);
                    ofiicialBetMedol.setStatus(1);
                }
            }
            allMedolList.remove(sscOfiicialBetMedol);
        }
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//????????????id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /*
  ?????? ?????? ??????item?????? ????????????(????????????????????????1???)
   */
//    public int randomMoreItemOneNum() {
    public void randomMoreItemOneNum() {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//???????????????item????????????medol(??????????????????code???medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//???????????????????????????name??????map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }
            SscOfiicialBetMedol medolCopy = null;
            for (int j = 0; j < list.size(); j++) {
                SscOfiicialBetMedol medol = list.get(j);
                if (i == 1) {//????????????????????????????????????????????????code
                    if (seletedRandomMedolList.get(0).getCodes().equals(medol.getCodes())) {
                        copyList.remove(medol);//?????????????????????item????????????code?????????medol
                        medolCopy = medol;
                    }
                }

            }

            list.remove(medolCopy);//?????????????????????item????????????code?????????medol
            int random = new Random().nextInt(list.size());
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
            seletedRandomMedolList.add(sscOfiicialBetMedol);
            for (int k = 0; k < sscofficialFourModelList.size(); k++) {
                SscOfiicialBetMedol ofiicialBetMedol = sscofficialFourModelList.get(k);
                if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                    officialSelectorList.add(ofiicialBetMedol);
                    ofiicialBetMedol.setStatus(1);
                }
            }

        }
//        int max=0;
//        for (int i = 0; i < officialSelectorList.size(); i++) {
//            SscOfiicialBetMedol medol = officialSelectorList.get(i);
//            String id1 = medol.getId();//????????????id
//            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
//                String id = sscOfiicialBetMedolArrayList.get(j).getId();
//                if(id.equals(id1)){
//                    if(j>max){
//                        max=j;
//                    }
//                }
//            }
//        }
//        return max;

    }

    /**
     * ?????? ?????? ??????item??????????????????(???????????????????????????????????????,???????????????????????????)
     *
     * @param ballCount ??????????????????????????????
     */
    public int randomMoreItemMoreNum(int ballCount) {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//???????????????item????????????medol(??????????????????code???medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//???????????????????????????name??????map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }

//                if(threeName.equals(Utils.getString(R.string.??????12))){
            if (name.equals(Utils.getString(R.string.?????????))) {
                SscOfiicialBetMedol medolCopy = null;//???????????????medol?????????medolCopy.????????????
                for (int j = 0; j < list.size(); j++) {
                    SscOfiicialBetMedol medol = list.get(j);
                    if (seletedRandomMedolList.get(0).getCodes().equals(medol.getCodes())) {
                        copyList.remove(medol);//?????????????????????item????????????code?????????medol
                        medolCopy = medol;
                    }
                }
                list.remove(medolCopy);//?????????????????????item????????????code?????????medol
                for (int num = 0; num < ballCount; num++) {
                    int random = new Random().nextInt(copyList.size());
                    SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                    for (int k = 0; k < list.size(); k++) {
                        SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                        if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                            officialSelectorList.add(ofiicialBetMedol);
                            ofiicialBetMedol.setStatus(1);
                        }
                    }
                    copyList.remove(sscOfiicialBetMedol);
                    list.remove(sscOfiicialBetMedol);

                }
            } else {
                int random = new Random().nextInt(list.size());
                SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                seletedRandomMedolList.add(sscOfiicialBetMedol);
                for (int k = 0; k < list.size(); k++) {
                    SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                    if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                        officialSelectorList.add(ofiicialBetMedol);
                        ofiicialBetMedol.setStatus(1);
                    }
                }
                copyList.remove(sscOfiicialBetMedol.getCodes());
            }
//                }
        }
        //??????????????????,??????id?????????sscOfiicialBetMedolArrayList?????????,??????id??????,??????sscOfiicialBetMedolArrayList???position??????max,???position??????,????????????????????????????????????position
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//?????????model???id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /**
     * ?????? ?????? ????????????30????????????(???????????????????????????????????????,???????????????????????????)
     *
     * @param ballCount ??????????????????????????????
     */


    public int randomMoreItemMoreNumWuXin30(int ballCount) {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//???????????????item????????????medol(??????????????????code???medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//???????????????????????????name??????map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }

            if (name.equals(Utils.getString(R.string.?????????))) {
                ArrayList<SscOfiicialBetMedol> medolCopyList = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    SscOfiicialBetMedol medol = list.get(j);
                    for (int k = 0; k < seletedRandomMedolList.size(); k++) {
                        if (seletedRandomMedolList.get(k).getCodes().equals(medol.getCodes())) {
                            medolCopyList.add(medol);
                        }
                    }
                }
                for (int j = 0; j < medolCopyList.size(); j++) {
                    if (list.contains(medolCopyList.get(j))) {
                        list.remove(medolCopyList.get(j));
                    }
                }
                int random = new Random().nextInt(list.size());
                SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                for (int k = 0; k < list.size(); k++) {
                    SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                    if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                        officialSelectorList.add(ofiicialBetMedol);
                        ofiicialBetMedol.setStatus(1);
                    }
                }
            } else {
                for (int num = 0; num < ballCount; num++) {
                    int random = new Random().nextInt(list.size());
                    SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                    seletedRandomMedolList.add(sscOfiicialBetMedol);
                    for (int k = 0; k < list.size(); k++) {
                        SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                        if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                            officialSelectorList.add(ofiicialBetMedol);
                            ofiicialBetMedol.setStatus(1);
                        }
                    }
                    list.remove(sscOfiicialBetMedol);
                }

            }
        }
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//????????????id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /**
     * ??????????????????
     *
     * @param amount ??????????????????
     */

    public void requestBetGf(String amount) {
        NoAlphaProgressDialogUtil.show(SscBetActivity.this,Utils.getString(R.string.????????????),false);
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        String token = SharePreferencesUtil.getString(SscBetActivity.this, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String amountType = "";
        if (yuanBtn.isChecked()) {
            amountType = "0";
        } else {
            amountType = "1";
        }
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);

        List<Map<String, Object>> nameList = new ArrayList<Map<String, Object>>();//????????????map2?????????(map2 ????????????betList)
        Map<String, Object> map2 = new HashMap<String, Object>();//??????ruleId amountType nameList amount???map, ????????????betList?????????
        List<Map<String, Object>> betList = new ArrayList<Map<String, Object>>();//??????map2???map, ????????????"betList"????????????????????????data map
        ArrayList<String> ruleIdList = new ArrayList<>();

        for (int i = 0; i < officialSelectorList.size(); i++) {
            String id = officialSelectorList.get(i).getId();
            if (!ruleIdList.contains(id)) {
                ruleIdList.add(id);
            }
        }

        //??????code (???????????????ruleIdList???size)
        for (int i = 0; i < ruleIdList.size(); i++) {
            Map<String, Object> map1 = new HashMap<String, Object>();//??????ruleTwoId ??? name???map(??????????????????nameList)
            String needCode = "";
            for (int j = 0; j < officialSelectorList.size(); j++) {
                SscOfiicialBetMedol sscOfiicialBetMedol = officialSelectorList.get(j);
                if (sscOfiicialBetMedol.getId().equals(ruleIdList.get(i))) {
                    needCode += sscOfiicialBetMedol.getCodes() + ",";
                }
            }
            needCode = needCode.substring(0, needCode.length() - 1);
            map1.put("name", needCode);
            map1.put("ruleTwoId", ruleIdList.get(i));
            nameList.add(map1);
        }
        map2.put("ruleId", threeId);
        map2.put("amountType", amountType);
        map2.put("nameList", nameList);
        map2.put("amount", amount);
        betList.add(map2);

        HashMap<String, Object> data = new HashMap<>();
        data.put("game", 2);
        data.put("userId", user_id);
        data.put("typeId", type_id);
        data.put("lotteryqishu", nowQishu);
        data.put("betList", JSONObject.toJSON(betList));
        data.put("source", 2);
        data.put("amountType", amountType);  //????????????  0 ???  1???
        data.put("curtime", format);//????????????
        data.put("token", token);//??????token
        Utils.docking(data, RequestUtil.REQUEST_02new, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                NoAlphaProgressDialogUtil.stop(SscBetActivity.this);
                sureBetGf.setClickable(true);
                gfSureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
                    sscOfiicialBetMedolArrayList.get(i).setStatus(0);
                }
                initAllStatus();
                getMoney();//?????????,??????????????????
            }

            @Override
            public void failed(MessageHead messageHead) {
                NoAlphaProgressDialogUtil.stop(SscBetActivity.this);
                showToast(messageHead.getInfo());
            }

        });
    }
    /*
    ???????????????????????????????????????(?????? ???????????? ????????????item?????????)
     */
    public void initAllStatus() {
        officialSelectorList.clear();
        int size = officialSelectorList.size();
        for (int i = 0; i < sscofficialFourModelList.size(); i++) {
            sscofficialFourModelList.get(i).setStatus(0);
        }
        officialAmountEt.setText(1 + "");
        betNumGfTv.setText(size + "");
        sscOfficialAdapter.notifyDataSetChanged();
        gfSureBetPop.dismiss();
        jixuanGfTv.setText(Utils.getString(R.string.??????));
        officialAmonnt = 1;
        allBetNum = 0;
        betAmountGfTv.setText(0 + "");
        yuanBtn.performClick();
        if (onBetSucceseListener != null) {
            onBetSucceseListener.onBetSuccese(officialSelectorList);
        }
    }


    /*
    ????????????pop ?????????????????????(??????)
    */
    private void initGfSureBetPopData(ArrayList<SscOfiicialBetMedol> list) {
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = officialSelectorList.get(i);
            String name = sscOfiicialBetMedol.getName();
            if (!list.contains(sscOfiicialBetMedol)) {
                sscOfiicialBetMedol = new SscOfiicialBetMedol();
                sscOfiicialBetMedol.setName(name);
                list.add(sscOfiicialBetMedol);//??????????????????model
            }
        }
        for (int i = 0; i < list.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(i);
            for (int j = 0; j < officialSelectorList.size(); j++) {
                SscOfiicialBetMedol model = officialSelectorList.get(j);
                if (sscOfiicialBetMedol.getName().equals(model.getName())) {//??????????????????()groupNama??????,?????????betType
                    String id = model.getId();
                    String modelCodes = model.getCodes();
                    sscOfiicialBetMedol.setId(sscOfiicialBetMedol.getId() + "," + id);
                    String modelCode = sscOfiicialBetMedol.getCodes();
                    if (StringMyUtil.isEmptyString(modelCode)) {//?????????,??????"",??????????????? null
                        modelCode = "";
                    }
                    sscOfiicialBetMedol.setCodes(modelCode + "," + modelCodes);
                }
            }
        }
        gfSureBetPopMeldolArrayList.clear();

        for (int i = 0; i < list.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(i);
            String name = sscOfiicialBetMedol.getName();
            String codes = sscOfiicialBetMedol.getCodes();
            String substring = codes.substring(1, codes.length());
            String finalStr1 = name + ":" + "<font color=\"#FF0000\">" + substring + "</font>";
            gfSureBetPopMeldolArrayList.add(new GfSureBetPopMeldol(finalStr1));
        }
        /*
        ????????????,?????????(????????????sureBetPopMeldolArrayList,??????????????????????????????)
         */
        for (int i = 0; i < gfSureBetPopMeldolArrayList.size() - 1; i++) {
            for (int j = i + 1; j < gfSureBetPopMeldolArrayList.size(); j++) {
                if (gfSureBetPopMeldolArrayList.get(i).equals(gfSureBetPopMeldolArrayList.get(j))) {
                    gfSureBetPopMeldolArrayList.remove(j);
                    j--;
                }
            }
        }
        gfSureBetPopAdapter.notifyDataSetChanged();
    }

    /**
     * ??????????????????
     *
     * @param size     ???????????????size(????????????)
     * @param editText ???????????? (???????????????)
     */
    private void requestBet(int size, String editText) {
        HashMap<String, Object> data = new HashMap<>();
        String needString = "";
        for (int i = 0; i < size; i++) {
            SscModel sscMedol = selecterList.get(i);
            String rule_id = sscMedol.getRule_id();
            needString += rule_id + ",";
        }
        needString = needString.substring(0, needString.length() - 1);
        String amountStr = "";
        for (int i = 0; i < size; i++) {
            amountStr += editText + ",";
        }
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        String token = SharePreferencesUtil.getString(SscBetActivity.this, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);


        String format = df.format(date);
        amountStr = amountStr.substring(0, amountStr.length() - 1);
        data.put("user_id", user_id);
        data.put("type_id", type_id);
        data.put("rule_id", needString);
        data.put("amount", amountStr);
        data.put("lotteryqishu", nowQishu);
        data.put("source", 2);
        data.put("curtime", format);//????????????
        data.put("token", token);//??????token
        Utils.docking(data, RequestUtil.REQUEST_02ssc, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                sureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < sscModelList.size(); i++) {
                    sscModelList.get(i).setStatus(0);
                }
                selecterList.clear();
                diyiqiuSelectList.clear();
                dierqiuSelectList.clear();
                disanqiuSelectList.clear();
                disiqiuSelectList.clear();
                diwuqiuSelectList.clear();
                int size = selecterList.size();
                amountEdit.setText("");
                betNum.setText(size + "");
                sscAdapter.notifyDataSetChanged();
                sureBetPop.dismiss();
                randomButton.setText(Utils.getString(R.string.??????));
                getMoney();//?????????,??????????????????
            }

            @Override
            public void failed(MessageHead messageHead) {
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                showToast(Utils.getString(R.string.????????????)+"\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    /*
     ?????????????????????
      */
    Runnable runnableTime = new Runnable() {
        @Override
        public void run() {
            if(failCount>6){
                return;
            }
//            if(countTime!=0/*||isCrate*/){
            if (countTime <= 0) {
                getCountTime(null);
            } else {
                countTime = millionSeconds + shijiancha - System.currentTimeMillis() - (Long.parseLong(tqtime) * 1000);
//                countTime = countTime - 1000;
                long hours = (countTime/* - days * (1000 * 60 * 60 * 24)*/) / (1000 * 60 * 60);
                long minutes = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
                hourText.setText(hours + "");
                minutesText.setText(minutes + "");
                secondsText.setText(seconds + "");
                if (hours == 0 || hours < 10) {
                    hourText.setText("0" + hours);
                } else {
                    hourText.setText("" + hours);
                }
                if (minutes == 0 || minutes < 10) {
                    minutesText.setText("0" + minutes);
                } else {

                    minutesText.setText("" + minutes);

                }
                if (seconds == 0 || seconds < 10) {

                    secondsText.setText("0" + seconds);
                } else {

                    secondsText.setText("" + seconds);
                }
                if (countTime <= 1) {
                    countTime = 0;
                    if (CountDownEndPop != null) {
                        lastQiShuTv.setText(nowQishu);
                        newQiShuTv.setText((Long.parseLong(nowQishu) + 1) + "");
                        if (!isFinishing() && CountDownEndPop != null && !CountDownEndPop.isShowing() && secondsText.getText().toString().equals("00")) {
                            CountDownEndPop.showAtLocation(minutesText, Gravity.CENTER, 0, 0);
                            ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.5f);
                        }
                    }
                    isWaitOpen = true;
                }
//            }
            }
            handler.postDelayed(runnableTime, 300);
        }

    };

    /*
    ?????????????????????????????????
     */
    Runnable runnableRandom = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen) {
                int i = new Random().nextInt(9) + 1;
                for (int j = 0; j < myCornerTextViewArrayList.size(); j++) {
                    myCornerTextViewArrayList.get(j).setText(i + "");
                }
            } else {
                for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                    myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRandom, 150);
        }
    };

    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen&&failCount<=6) {
                initOpenResult();
            } else {
                for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                    myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
        }
    };
    /*
    ????????????(??????????????????????????????)
     */
    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(SscBetActivity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    todayZJ = jsonObject.getString("todayZJ");
                    if (todayZJ.equals("1")) {
                        Message message = Message.obtain();
                        message.what = 2;
                        handler.sendMessage(message);
                    }

                }

                @Override
                public void failed(MessageHead messageHead) {

                }
            });
            handler.postDelayed(runnableZj, 2000);
        }
    };

    /**
     * ????????????recycleView ??????????????????
     *
     * @param view
     * @param position
     * @param lotteryClassModel
     */
    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();
        ToBetAtyUtils.toBetActivity(SscBetActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }

    /**
     * ????????????pop?????????????????????
     *
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }

    /**
     * ????????????recycleView??????????????????
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        SscModel sscModel = sscModelList.get(position);
        if (oneToFiveBtn.isChecked()) {
            if (sscModel.getGroupname().equals(Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????))) {
                if (sscModel.getStatus() == 1) {
                    diyiqiuSelectList.add(sscModel);
                    if (diyiqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.?????????????????????????????????) + sscaiCount + Utils.getString(R.string.???));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        diyiqiuSelectList.remove(sscModel);
                    }
                } else {
                    diyiqiuSelectList.remove(sscModel);
                }

            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????))) {

                if (sscModel.getStatus() == 1) {
                    dierqiuSelectList.add(sscModel);
                    if (dierqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.???????????????????????????) + sscaiCount + Utils.getString(R.string.???));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        dierqiuSelectList.remove(sscModel);
                    }
                } else {
                    dierqiuSelectList.remove(sscModel);
                }
            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????))) {
                if (sscModel.getStatus() == 1) {
                    disanqiuSelectList.add(sscModel);
                    if (disanqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.???????????????????????????) + sscaiCount + Utils.getString(R.string.???));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        disanqiuSelectList.remove(sscModel);
                    }
                } else {
                    disanqiuSelectList.remove(sscModel);
                }
            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.????????????)+"-"+Utils.getString(R.string.?????????))) {
                if (sscModel.getStatus() == 1) {
                    disiqiuSelectList.add(sscModel);
                    if (disiqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.???????????????????????????) + sscaiCount + Utils.getString(R.string.???));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        disiqiuSelectList.remove(sscModel);
                    }
                } else {
                    disiqiuSelectList.remove(sscModel);
                }
            } else {
                if (sscModel.getStatus() == 1) {
                    diwuqiuSelectList.add(sscModel);
                    if (diwuqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.???????????????????????????) + sscaiCount + Utils.getString(R.string.???));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        diwuqiuSelectList.remove(sscModel);
                    }
                } else {
                    diwuqiuSelectList.remove(sscModel);
                }
            }


        }
        //||dierqiuSelectList.size()>8||disanqiuSelectList.size()>8||disiqiuSelectList.size()>8||diwuqiuSelectList.size()>8
        int size = selecterList.size();
        betNum.setText(size + "");//????????????????????????,???????????????seleterList???size(selecterList?????????????????????item????????????????????????position?????????model??????,???????????????????????????,????????????medol??????)

        if (size != 0) {
            randomButton.setText(Utils.getString(R.string.??????));
        } else {
            randomButton.setText(Utils.getString(R.string.??????));
        }
    }

    /*
    ????????????pop
     */
    private void initSureBetPop() {
        sureBetPopAdapter = new SureBetPopAdapter(sureBetPopMeldolArrayList);
        View view = LayoutInflater.from(this).inflate(R.layout.sure_bet_pop, null);
        View footView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_foot, null);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_head, null);
        sureBetPopAdapter.addHeaderView(headView);
        sureBetPopAdapter.addFooterView(footView);
        lotteryNameTv = headView.findViewById(R.id.lottery_name);
        qishuTv = headView.findViewById(R.id.lottery_qishu);
        betAmoumtTv = headView.findViewById(R.id.bet_amount);
        allBetNumTv = footView.findViewById(R.id.all_bet_num);
        allBetAmountTv = footView.findViewById(R.id.all_bet_amout);
        cancelButton = view.findViewById(R.id.sure_bet_cancel);
        sureButton = view.findViewById(R.id.sure_bet_sure);
        sureBetRecy = view.findViewById(R.id.sure_bet_pop_recycly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        sureBetRecy.setLayoutManager(linearLayoutManager);
        sureBetRecy.setAdapter(sureBetPopAdapter);
        cancelButton.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        sureBetPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        sureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        sureBetPop.setTouchable(true);//??????????????????
        sureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() || !choosePlayTypePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                betButton.setClickable(true);
            }
        });

    }

    /*
    ????????????pop(??????)
    */
    private void initGfSureBetPop() {
        gfSureBetPopAdapter = new GfSureBetPopAdapter(gfSureBetPopMeldolArrayList);
        View view = LayoutInflater.from(this).inflate(R.layout.gf_sure_bet_pop, null);
        View footView = LayoutInflater.from(view.getContext()).inflate(R.layout.gf_sure_bet_pop_recycle_foot, null);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.gf_sure_bet_pop_recycle_head, null);
        gfSureBetPopAdapter.addHeaderView(headView);
        gfSureBetPopAdapter.addFooterView(footView);
        gfLotteryNameTv = headView.findViewById(R.id.gf_lottery_name);
        gfQishuTv = headView.findViewById(R.id.gf_lottery_qishu);
        gfBetAmoumtTv = headView.findViewById(R.id.gf_bet_amount);
        gfAllBetNumTv = footView.findViewById(R.id.gf_all_bet_num);
        gfAllBetAmountTv = footView.findViewById(R.id.gf_all_bet_amout);
        gfCancelButton = view.findViewById(R.id.gf_sure_bet_cancel);
        gfSureButton = view.findViewById(R.id.gf_sure_bet_sure);
        gfSureBetRecy = view.findViewById(R.id.gf_sure_bet_pop_recycly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        gfSureBetRecy.setLayoutManager(linearLayoutManager);
        gfSureBetRecy.setAdapter(gfSureBetPopAdapter);
        gfCancelButton.setOnClickListener(this);
        gfSureButton.setOnClickListener(this);
        gfSureBetPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        gfSureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        gfSureBetPop.setTouchable(true);//??????????????????
        gfSureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() || !choosePlayTypePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                gfSureButton.setClickable(true);
                sureBetGf.setClickable(true);
                allBetAmountStr = "0";
            }
        });

    }


        /*
        ???????????????
         */

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(runnableBoolen);
/*        handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);*/
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }


    private int getGame() {
        /*1??????3???
        2???????????????
        3????????????
        4???????????????
        5????????????
        6??????8???
        7????????????
        8?????????10??????
        9???11???5*/
        return 2;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initAllPop();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
    }

/*    @Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);
        if (!netWorkState) {
            showtoast(Utils.getString(R.string.?????????????????????,???????????????));
            findViewById(R.id.no_network).setVisibility(View.VISIBLE);
            ProgressDialogUtil.show(this);
            handler.removeCallbacks(runnableRandom);
            handler.removeCallbacks(runnableRequestOpen);
            handler.removeCallbacks(runnableZj);
            handler.removeCallbacks(runnableTime);
        } else {
            findViewById(R.id.no_network).setVisibility(View.GONE);
            ProgressDialogUtil.stop(this);
            if(runnableRandom==null){

                handler.postDelayed(runnableRandom,150);
            }
            if(runnableRequestOpen==null){
                handler.postDelayed(runnableRequestOpen,jgTime);
            }
            if(runnableZj==null){

                handler.postDelayed(runnableZj,3000);
            }if(runnableTime==null){
                handler.postDelayed(runnableTime,300);
            }
        }
    }*/

    /*
    ?????????????????????????????????
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String toString = s.toString();
        String newOdds = "";
        BigDecimal bigDecimal = BigDecimal.ZERO;//???????????????bigDecimal???
        if (StringMyUtil.isEmptyString(toString)) {
            bigDecimal = BigDecimal.ZERO;
        } else {
            //???????????????????????????10000
            if(Long.parseLong(toString)>=10000){
                //??????????????????setText??????????????????????????????,?????????????????????
                toString=10000+"";
                //???????????????
                officialAmountEt.removeTextChangedListener(this);
                //????????????????????????
                officialAmountEt.setText("10000");
                officialAmountEt.setSelection(officialAmountEt.getText().length());//????????????????????????
                //?????????????????????
                officialAmountEt.addTextChangedListener(this);
            }
            bigDecimal = new BigDecimal(toString)/*.setScale(2, RoundingMode.HALF_UP)*/;
            officialAmonnt=Long.parseLong(toString);
        }
        if (!StringMyUtil.isEmptyString(odds)) {
            if (yuanBtn.isChecked()) {
                newOdds = odds;
                allBetAmountStr = bigDecimal.multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
            } else if (jiaoBtn.isChecked()) {
                newOdds = new BigDecimal(odds).divide(new BigDecimal(10 + "")) + "";
                allBetAmountStr = bigDecimal.multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
            }
        }
//        if(StringMyUtil.isEmptyString(allBetAmountStr)){
//            allBetAmountStr =0+"";
//        }
        betAmountGfTv.setText(allBetAmountStr);
        if (!StringMyUtil.isEmptyString(newOdds)) {
            BigDecimal highAmount = bigDecimal.multiply(new BigDecimal(newOdds + "").setScale(2, RoundingMode.HALF_UP));
            String str = Utils.getString(R.string.????????????) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.???);
            maxAmountTv.setText(Html.fromHtml(str));
        }
    }

    /*
    ????????????itemTwo????????????(???????????????????????????????????????)
     */
    @Override
    public void onItemFourClick(View view, SscOfiicialBetMedol sscOfiicialBetMedol, int position) {
//        int num=0;
        if (typeOneName.equals(getString(R.string.??????))) {
            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
        } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 2);
//                allBetNum+=num;
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 2);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                if (officialSelectorList.size() >= 1) {
                    for (int i = 0; i < officialSelectorList.size(); i++) {
                        officialSelectorList.get(i).setStatus(0);
                    }
                    officialSelectorList.clear();
                    sscOfiicialBetMedol.setStatus(1);
                    officialSelectorList.add(sscOfiicialBetMedol);
                    allBetNum = officialSelectorList.size() * 9;
                } else {
                    allBetNum = officialSelectorList.size() * 9;
                }
            }
        } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 3);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 3);
            } else if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                allBetNum = allBetNum * 2;
            } else if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                if (officialSelectorList.size() >= 1) {
                    for (int i = 0; i < officialSelectorList.size(); i++) {
                        officialSelectorList.get(i).setStatus(0);
                    }
                    officialSelectorList.clear();
                    sscOfiicialBetMedol.setStatus(1);
                    officialSelectorList.add(sscOfiicialBetMedol);
                    allBetNum = officialSelectorList.size() * 54;
                } else {
                    allBetNum = officialSelectorList.size() * 54;
                }
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = officialSelectorList.size();
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            }
        } else if (typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.??????24))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 4);
            } else if (typeThreeName.equals(getString(R.string.??????12))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.??????6))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.??????4))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 1, 2);
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            }
        } else if (typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.??????120))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 5);
            } else if (typeThreeName.equals(getString(R.string.??????60))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 3, 2);
            } else if (typeThreeName.equals(getString(R.string.??????30))) {
                allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.??????20))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.??????10)) || typeThreeName.equals(getString(R.string.??????5))) {
                allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSelectorList, typeThreeName, 1, 2);
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????)) || typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            }
        }
        betNumGfTv.setText(allBetNum + "");
        BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(getString(R.string.??????));
        } else {
            jixuanGfTv.setText(getString(R.string.??????));
        }
    }

    /*
    ??????????????????????????????o
    */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onQuickClick(View view, ArrayList<SscOfiicialBetMedol> officialSeletorList) {
        if (typeOneName.equals(getString(R.string.??????))) {
            allBetNum = BetNumUtil.oneToOne(officialSeletorList);
        } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSeletorList, true, 2);
//                allBetNum+=num;
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSeletorList, 2);
                }
            }
        } else if (typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????)) || typeOneName.equals(getString(R.string.??????))) {
            if (typeThreeName.equals(getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.????????????))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSeletorList, true, 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.????????????))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSeletorList, 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
                allBetNum = allBetNum * 2;
            } else if (typeThreeName.equals(Utils.getString(R.string.??????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = officialSeletorList.size();
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            }
        } else if (typeOneName.equals(Utils.getString(R.string.??????))) {
            if (typeThreeName.equals(Utils.getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????24))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 4);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????12))) {
                allBetNum = (int) BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????6))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????4))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 1, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSeletorList);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            }
        } else if (typeOneName.equals(Utils.getString(R.string.??????))) {
            if (typeThreeName.equals(Utils.getString(R.string.??????))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????120))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 5);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????60))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 3, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????30))) {
                allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????20))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.??????10)) || typeThreeName.equals(Utils.getString(R.string.??????5))) {
                allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSeletorList, typeThreeName, 1, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSeletorList);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.???????????????))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.????????????)) || typeThreeName.equals(Utils.getString(R.string.????????????)) || typeThreeName.equals(Utils.getString(R.string.????????????)) || typeThreeName.equals(Utils.getString(R.string.????????????))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            }

        }
        betNumGfTv.setText(allBetNum + "");
        BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(Utils.getString(R.string.??????));
        } else {
            jixuanGfTv.setText(Utils.getString(R.string.??????));
        }
    }

    /*
    ????????????itemOne(??????????????????)????????????(????????????)
     */

    @Override
    public void onItemOneClick(View view, ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedols, int position) {
        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
        betNumGfTv.setText(allBetNum + "");
        String toString = officialAmountEt.getText().toString();
        if (StringMyUtil.isEmptyString(toString)) {
            toString = "1";
        }
        BigDecimal bigDecimal = new BigDecimal(toString).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(getString(R.string.??????));
        } else {
            jixuanGfTv.setText(getString(R.string.??????));
        }
    }

        @Override
        public void onNetChange(boolean netWorkState) {

        }
        @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
        public void updateXian(HbGameClassModel hbGameClassModel){
            customPopupWindow.selectorId(hbGameClassModel);
        }
        //????????????,???????????????????????????????????????(??????????????????????????????)
    public static interface OnRandomListener {
        void onRandomClilck(ArrayList<SscOfiicialBetMedol> selectedList);
    }

    SscBetActivity.OnRandomListener onRandomListener = null;

    public void setOnRandomListener(OnRandomListener onRandomListener) {
        this.onRandomListener = onRandomListener;
    }


    //????????????,???????????????????????????????????????(??????????????????????????????)
    public static interface onBetSucceseListener {
        void onBetSuccese(ArrayList<SscOfiicialBetMedol> selectedList);
    }

    SscBetActivity.onBetSucceseListener onBetSucceseListener = null;

    public void setOnBetSucceseListener(SscBetActivity.onBetSucceseListener onBetSucceseListener) {
        this.onBetSucceseListener = onBetSucceseListener;
    }
}


