package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BetUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.Arrays;

public class FollowBetPop extends PopupWindow implements View.OnClickListener {
    LiveFragment liveFragment;
    private TextView typenameTv;
    private TextView qishuTv;
    private TextView touzhuneirongTv;
    private EditText betEdit;
    private TextView betAmountTv;
    private TextView betSure;
    private TextView betCancel;
    private LiveMessageModel liveMessageModel;
    private TextView betNumTv;

    public FollowBetPop(Context context, LiveFragment liveFragment, LiveMessageModel liveMessageModel) {
        super(context);
        this.liveFragment = liveFragment;
        this.liveMessageModel = liveMessageModel;
        initView();

    }

    private void initView() {
        View v = LayoutInflater.from(liveFragment.getContext()).inflate(R.layout.chatroom_bet_pop, null);
        typenameTv = v.findViewById(R.id.type_name);
        qishuTv = v.findViewById(R.id.qishu);
        touzhuneirongTv = v.findViewById(R.id.touzhuneirong);
        betEdit = v.findViewById(R.id.bet_amount_edit);
        betNumTv=v.findViewById(R.id.bet_num);
        betEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String toString = betEdit.getText().toString();
                if (StringMyUtil.isEmptyString(toString)) {
                    betAmountTv.setText("0");
                }
                int size = getBetSize();
                long amount = Long.parseLong(StringMyUtil.isEmptyString(toString)?"0":toString);
                betAmountTv.setText((amount*size)+"");

            }
        });
        betAmountTv = v.findViewById(R.id.bet_amount);
        betSure = v.findViewById(R.id.bet_sure);
        betCancel = v.findViewById(R.id.bet_cancel);
        betCancel.setOnClickListener(this);
        betSure.setOnClickListener(this);
        typenameTv.setText(liveMessageModel.getTypeName()+":");
        qishuTv.setText(liveMessageModel.getBetQiShu()+Utils.getString(R.string.期));
        String betGroupName = liveMessageModel.getBetGroupName();
        int size = getBetSize();
        touzhuneirongTv.setText(betGroupName+":"+ liveMessageModel.getBetName());
        betNumTv.setText(size +"");
        initPop(v);
    }

    private int getBetSize( ) {
        String  betName= liveMessageModel.getBetName();
        return Arrays.asList(betName.split(",")).size();
    }

    private void initPop(View v) {
        this.setContentView(v);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(liveFragment.getActivity(),1f);
            }
        });
        this.showAtLocation(Utils.getContentView(liveFragment.getActivity()), Gravity.CENTER,0,-200);
        ProgressDialogUtil.darkenBackground(liveFragment.getActivity(),0.7f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bet_cancel:
                FollowBetPop.this.dismiss();
                break;
            case R.id.bet_sure:
                String amount = betEdit.getText().toString();
                if (StringMyUtil.isEmptyString(amount)) {
                    ToastUtil.showToast(Utils.getString(R.string.请输入下注金额));

                } else {
                    FollowBetPop.this.dismiss();
                    betSure.setClickable(false);
                    Long user_id = SharePreferencesUtil.getLong(liveFragment.getContext(), "user_id", 0l);
                    BetUtil.requestBet(liveFragment.getContext(), user_id, Integer.parseInt(liveMessageModel.getGame()), Integer.parseInt(liveMessageModel.getType_id()), liveMessageModel.getReluId(), amount, liveMessageModel.getBetQiShu(),getBetSize(),liveFragment.getmLiveData().getAnchorAccount(),liveFragment.getmLiveData().getRemoteLiveManagementId(), new BetUtil.BetListener() {
                        @Override
                        public void OnBetSuccuseListener() {
                            betSure.setClickable(true);
                            FollowBetPop.this.dismiss();
                        }

                        @Override
                        public void OnBetFailListener(MessageHead messageHead) {
                            betSure.setClickable(true);

                        }
                    });
                    break;
                }
        }
    }}
