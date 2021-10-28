package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveTextMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LevelModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonSurePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.HeightProvider;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import androidx.annotation.Nullable;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class EditPanel extends LinearLayout {
    public TextView inputTv;
    public TextView sendTv;
    public EditText editText;
    public LinearLayout editLinear;
    public LinearLayout inputClickLinear;
    public Context context;
    String roomId;
    public LiveFragment liveFragment;
    public LinearLayout edit_root_linear;
    public static long  lastSendTime =0l;
    public LiveFragment getLiveFragment() {
        return liveFragment;
    }
    HeightProvider heightProvider;
    public void setLiveFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public EditPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }
    private void initView(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chat_edit_layout, this);
        bindView(view);
//        disableCopyAndPaste(editText);
        inputTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int mineGrade = SharePreferencesUtil.getInt(getContext(), CommonStr.GRADE, 1);
                ManagerTypeEntity managerTypeEntity = liveFragment.managerTypeEntity;
                if(managerTypeEntity==null){
                    ToastUtil.showToast(Utils.getString(R.string.请等待数据加载完毕));
                    return;
                }
                int isLevel = managerTypeEntity.getIsLevel();//发言等级限制
//                LiveEntity.AnchorMemberRoomListBean mLiveData = liveFragment.getmLiveData();
                String vipSpeak = SharePreferencesUtil.getString(getContext(), CommonStr.VIP_SPEAK, "0");//转盘抽奖或者签到获得的vip发言权限
                if( managerTypeEntity !=null){
                    boolean level = mineGrade >= isLevel;
                    boolean vip = vipSpeak.equals("1");
                    if(level || vip){
                        showHideInputView(editLinear);
                    }else {
                        ToastUtil.showToast(" vip" + isLevel + Utils.getString(R.string.及以上即可发言));
                    }
                }


            }
        });
        sendTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // send Message
                if (StringMyUtil.isEmptyString(editText.getText().toString())) {
                    return;
                }
                int mineGrade = SharePreferencesUtil.getInt(getContext(), CommonStr.GRADE, 1);
                //发言间隔
                if (initSpeedTime(mineGrade)) return;
                String chatRoomSensitiveWord = SharePreferencesUtil.getString(MyApplication.getInstance(), "chatRoomSensitiveWord", "");
                List<String> sensitiveList = Arrays.asList(chatRoomSensitiveWord.split(","));
                boolean forbiddenSelf=false;
                for (int i = 0; i < sensitiveList.size(); i++) {
                    String sensitive = sensitiveList.get(i);
                    if(editText.getText().toString().contains(sensitive)&&StringMyUtil.isNotEmpty(sensitive)){
                        forbiddenSelf=true;
                        break;
                    }
                }
                if(forbiddenSelf&&liveFragment!=null){
                    ProgressDialogUtil.show(liveFragment.getContext(),Utils.getString(R.string. 敏感词检测中));
                    //输入框中包含敏感词 封禁自己并返回首页
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("anchorAccount",liveFragment.getmLiveData().getAnchorAccount());
                    data.put("userSpeakContent",editText.getText().toString());//消息内容
                    HttpApiUtils.CPnormalRequest(liveFragment.getActivity(), liveFragment, RequestUtil.FORBIDDEN_SELF, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            ProgressDialogUtil.stop(liveFragment.getContext());
                            CommonSurePop commonSurePop = new CommonSurePop(liveFragment.getContext(),false,Utils.getString(R.string.系统提示),Utils.getString(R.string.系统检测到敏感词您的账号已被封禁));
                            commonSurePop.showAtLocation(liveFragment.drawerlayout, Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(liveFragment.getActivity(),0.5f);
                            commonSurePop.setOnPopClickListener(new BasePopupWindow2.OnPopClickListener() {
                                @Override
                                public void onPopClick(View view) {
                                    liveFragment.getActivity().finish();
                                }
                            });
                        }

                        @Override
                        public void onFailed(String msg) {
                            ProgressDialogUtil.stop(liveFragment.getContext());
                        }
                    });
                }else {
                    //正常发送消息
                    RongLibUtils.sendMessage(roomId,new LiveTextMessage(RongLibUtils.setUserInfo(context, liveFragment.managerTypeEntity), Utils.filtrationChineseEnglishNumberEmoji(editText.getText().toString())));
                }
                //隐藏显示输入框
                showHideInputView(editLinear);
                //清空输入框
                editText.setText("");
                //收回软键盘
                Utils.hideSoftKeyBoard((Activity) context,editText);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String editStr = editText.getText().toString();
                if (StringMyUtil.isNotEmpty(editStr)) {
                    sendTv.setBackground(getResources().getDrawable(R.drawable.login_button_can_click_shape));
                    sendTv.setClickable(true);
                } else {
                    sendTv.setBackground(getResources().getDrawable(R.drawable.login_button_can_not_click_shape));
                    sendTv.setClickable(false);
                }
            }

        });
        /**
         * 软键盘右下角action 按钮事件
         */
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //点击右键盘下角发送按钮, 默认点击sendTv
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    sendTv.performClick();
                }
                return false;
            }
        });
    }

    /**
     * 发言间隔
     * @param mineGrade
     * @return
     */
    private boolean initSpeedTime(int mineGrade) {

        String jsonStr = SharePreferencesUtil.getString(getContext(), CommonStr.LEVEL_LIST, "");
        if(StringMyUtil.isNotEmpty(jsonStr)){
            LevelModel levelModel = JSONObject.parseObject(jsonStr, LevelModel.class);
            List<LevelModel.SysGradeListBean> sysGradeList = levelModel.getSysGradeList();
            String speechIntervalSeconds="";
            for (int i = 0; i < sysGradeList.size(); i++) {
                LevelModel.SysGradeListBean sysGradeListBean = sysGradeList.get(i);
                int pointGrade = sysGradeListBean.getPointGrade();
                if(mineGrade-1==pointGrade){
                    speechIntervalSeconds=  sysGradeListBean.getSpeechIntervalSeconds()+"";
                    break;
                }
            }
            if(lastSendTime == 0l){
                lastSendTime = new Date().getTime();
            }
            if(StringMyUtil.isNotEmpty(speechIntervalSeconds)){
                long l = new Date().getTime() - lastSendTime;
                if( l <Long.parseLong(speechIntervalSeconds)*1000&&l!=0){
                    ToastUtil.showToast(String.format(Utils.getString(R.string.发言过于频繁VIP的发言间隔为秒),mineGrade,speechIntervalSeconds));
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 监听键盘高度, 将输入框顶到键盘上方
     */
    public  void setInputLinstener() {
        heightProvider = new HeightProvider(new SoftReference<>(liveFragment.getActivity())).init().setHeightListener(new HeightProvider.HeightListener() {
            @Override
            public void onHeightChanged(int height) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(edit_root_linear, "translationY", -height);
                if (height == 0) {
                    //收回键盘时,不需要动画时长
                    animator.setDuration(0);
                    editLinear.setVisibility(GONE);
                    inputClickLinear.setVisibility(View.VISIBLE);
                    if (liveFragment != null && !liveFragment.isUnBind) {
                        if (liveFragment.linearLayout7 != null) {
                            liveFragment.linearLayout7.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    animator.setDuration(200);
                }
                animator.start();
                if(onHeightChangeListener!=null){
                    onHeightChangeListener.onHeightChange(height);
                }
            }
        });
    }

    private void bindView(View view) {
        inputClickLinear = view.findViewById(R.id.input_click_linear);
        inputTv = view.findViewById(R.id.live_chat_tv);
        sendTv = view.findViewById(R.id.send_text_message_tv);
        editText = view.findViewById(R.id.live_chat_edit);
        editLinear = view.findViewById(R.id.edit_linear);
        edit_root_linear = view.findViewById(R.id.edit_root_linear);
    }

    /**
     *输入框和上方Utils.getString(R.string.来聊聊天)按钮之间的显示隐藏
     * @param editLinear
     */
    public void showHideInputView(LinearLayout editLinear) {
        if (editLinear.getVisibility() == VISIBLE) {
            editLinear.setVisibility(GONE);
            inputClickLinear.setVisibility(VISIBLE);
 /*           if(null!=liveFragment){
                liveFragment.bottomLinear.setVisibility(VISIBLE);
            }*/
            if(null!=liveFragment){
                liveFragment.linearLayout7.setVisibility(VISIBLE);
            }
            Utils.hideSoftKeyBoard((Activity) context);
        } else {
            editLinear.setVisibility(VISIBLE);
            inputClickLinear.setVisibility(GONE);

            if(null!=liveFragment){
                liveFragment.linearLayout7.setVisibility(INVISIBLE);
            }
            Utils.showSoftInputFromWindow((Activity) context, editText);
        }
    }

    public HeightProvider getHeightProvider() {
        return heightProvider;
    }

    /**
     * 禁止输入框复制粘贴菜单
     */
    public void disableCopyAndPaste(final EditText editText) {
        try {
            if (editText == null) {
                return ;
            }

            editText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            editText.setLongClickable(false);
            editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // setInsertionDisabled when user touches the view
                        setInsertionDisabled(editText);
                    }

                    return false;
                }
            });
            editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInsertionDisabled(EditText editText) {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(editText);

            // if this view supports insertion handles
            Class editorClass = Class.forName("android.widget.Editor");
            Field mInsertionControllerEnabledField = editorClass.getDeclaredField("mInsertionControllerEnabled");
            mInsertionControllerEnabledField.setAccessible(true);
            mInsertionControllerEnabledField.set(editorObject, false);

            // if this view supports selection handles
            Field mSelectionControllerEnabledField = editorClass.getDeclaredField("mSelectionControllerEnabled");
            mSelectionControllerEnabledField.setAccessible(true);
            mSelectionControllerEnabledField.set(editorObject, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface  OnHeightChangeListener{
        void onHeightChange(int height);
    }
    OnHeightChangeListener onHeightChangeListener;

    public void setOnHeightChangeListener(OnHeightChangeListener onHeightChangeListener) {
        this.onHeightChangeListener = onHeightChangeListener;
    }
}
