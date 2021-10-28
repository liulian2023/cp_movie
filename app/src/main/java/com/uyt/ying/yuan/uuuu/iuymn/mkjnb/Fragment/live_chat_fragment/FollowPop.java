package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import okhttp3.Headers;

public class FollowPop extends PopupWindow {
    private Button collectBtn;
    private ImageView collectIv;
    private TextView collectTitleTv;
    private ImageView collectDeleteIv;

    Fragment fragment;
    int isCollect;//当前的收藏状态
    String liveName;//标题
    int liveId;//主播id
    String imageUrl; //封面图
    static  String CANEL_FOLLOW=Utils.getString(R.string.加号取消关注);
    static  String FOLLOW=Utils.getString(R.string.加号关注);


    public FollowPop(Context context, Fragment fragment, int isCollect, String liveName,String imageUrl,int liveId) {
        super(context);
        this.fragment = fragment;
        this.isCollect = isCollect;
        this.liveName = liveName;
        this.imageUrl = imageUrl;
        this.liveId = liveId;
        initView();
    }

    private void initView() {
        View v = LayoutInflater.from(fragment.getContext()).inflate(R.layout.collect_movie_pop, null);
        collectBtn = v.findViewById(R.id.collect_btn);
        collectIv = v.findViewById(R.id.collect_big_iv);
        collectTitleTv = v.findViewById(R.id.collect_title_tv);
        collectDeleteIv = v.findViewById(R.id.collect_delete_iv);
        collectTitleTv.setText(liveName);
        if (isCollect == 1) {
            collectBtn.setText(CANEL_FOLLOW);
        } else {
            collectBtn.setText(FOLLOW);
        }
        GlideLoadViewUtil.FLoadNormalView(fragment,Utils.checkImageUrl(imageUrl),collectIv);
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collectBtn.getText().toString().equals(CANEL_FOLLOW)){//取消关注
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("anchorMemberId",liveId);
                    HttpApiUtils.CPnormalRequest(fragment.getActivity(),fragment, RequestUtil.CANCEL_FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            //{"message":"success","status":"success"}
                            collectBtn.setText(FOLLOW);
                            //发送evenBus 通知更新列表状态
                            EventBus.getDefault().postSticky(new FollowEvenModel(liveId+"",false));
                            FollowPop.this.dismiss();
                        }

                        @Override
                        public void onFailed(String msg) {
                        }
                    });
                }else {//关注
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("anchorMemberId",liveId);
                    HttpApiUtils.CPnormalRequest(fragment.getActivity(),fragment, RequestUtil.FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            //{"message":"success","status":"success"}
                            collectBtn.setText(Utils.getString(R.string.取消关注));
                            // 发送evenBus 通知更新列表状态
                            EventBus.getDefault().postSticky(new FollowEvenModel(liveId+"",true));
                            FollowPop.this.dismiss();
                        }
                        @Override
                        public void onFailed(String msg) {

                        }
                    });
                }
            }
        });
        collectDeleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FollowPop.this.dismiss();
            }
        });
        initPop(v);
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
                ProgressDialogUtil.darkenBackground(fragment.getActivity(),1f);
            }
        });
        this.showAtLocation(Utils.getContentView(fragment.getActivity()), Gravity.CENTER,0,0);
    }
}
