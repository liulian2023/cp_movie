package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.user_info_fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/*import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.airsaid.pickerviewlibrary.TimePickerView;*/
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.ModifyNickNameActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.SafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.PhonePasswordActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.user_info_adapter.UserImageAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserImage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.BottomSwitchPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BitmapUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GetPhotoFromPhotoAlbum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

/*
用户信息
 */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout sixLinear;
    private LinearLayout birthdarLinear;
    private TextView sextext;
    private TextView birthdayText;
    private ImageView userImg;
    private  TextView name;
    private TextView nicheng;
    private TextView phoneNum;
    private  SimpleDateFormat sdf;
    private  String format;
    private  LinearLayout imageLinear;
    private  LinearLayout phoneLinear;
    private ArrayList<UserImage> urlList = new ArrayList<>();
    private Map<String, Object> dataImageList;//头像选择列表
    private Map<String, Object>  modifyImage;//修改头像
    private RecyclerView recyclerView;
    private UserImageAdapter userImageAdapter;
    private ImageView bigImage;
    private ImageView userImageReturn;
    private  PopupWindow popupWindow;
    private Button cancel;
    private Button sure;

    private ImageView userInfoImage;
    private TextView userInfoAccount;
    private LinearLayout nickNameLinear;
    private LinearLayout floatLinear;
    private   OptionsPickerView optionsPickerView;
    //生日选择时的最小时间
    private Calendar mixDate;
    //生日选择时的最大时间
    private Calendar maxDate;
    //时间选择时默认选中的时间
    private Calendar defaultDate;
    private View view;
    private LinearLayout account_linear;
    private String mobileNumSwitch;
    private BottomSwitchPop bottomSwitchPop;
    private  int REQUEST_PHOTO_CODE=12;
    private int REQUEST_CAMERA_CODE=110;
    private String realPath;
    private int editNicknameLevel;
    private int pointGrade;
    private Uri  uri;
    private File cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view =LayoutInflater.from(getContext()).inflate(R.layout.user_info_fragment,container,false);
        mobileNumSwitch = SharePreferencesUtil.getString(getContext(), "mobileNumSwitch", "");
        editNicknameLevel = SharePreferencesUtil.getInt(MyApplication.getInstance(),"editNicknameLevel",1);
        pointGrade = SharePreferencesUtil.getInt(MyApplication.getInstance(),CommonStr.GRADE,1);
        initView(view);
        initPerson();
        Utils.RequestUsingEquipment(getActivity(),this);
        mixDate = Calendar.getInstance();
        maxDate = Calendar.getInstance();
        mixDate.setTime(new Date());
        //月份和天数都需要减1()
        mixDate.set(mixDate.get(Calendar.YEAR)-100,0,30);
        maxDate.set(maxDate.get(Calendar.YEAR),maxDate.get(Calendar.MONTH),maxDate.get(Calendar.DAY_OF_MONTH));
        defaultDate=Calendar.getInstance();
        defaultDate.setTime(new Date());
        return view;
    }

    private void initPerson() {
        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq
        long userId =SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        aboutPerson.put("user_id",userId);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_13r, aboutPerson, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(getContext(),"userInfo",result);//储存用户信息
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                /*
                用户头像
                 */
                String image = memberInfo.getString("image");
                String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                String finalImgUrl="";
                if(!StringMyUtil.isEmptyString(image)){
                    image =image.replace(firstImageUrl,"");
                    finalImgUrl = firstImageUrl+image;
                }else {
                    finalImgUrl=Utils.getString(R.string.沒有头像);
                }
                SharePreferencesUtil.putString(getContext(),"oldUserImage",finalImgUrl);
                Glide.with(getContext())
                        .load(finalImgUrl)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .error(R.drawable.system_default_title)
                        .into(userInfoImage);
                   /*
                  用户名
                  */
                String nickname = memberInfo.getString("nickname");
                userInfoAccount.setText(nickname);
                /*
                昵称
                 */
                String userNickName = memberInfo.getString("userNickName");
                nicheng.setText(userNickName);

                String phone = memberInfo.getString("phone");
                if(phone==null){
                    phoneNum.setText(Utils.getString(R.string.未绑定));
                }
                else {
                    phoneNum.setText(phone);
                }
                /*
                生日
                 */
                String birthday = memberInfo.getString("birthday");
                if(!StringMyUtil.isEmptyString(birthday)){
                    String format = sdf.format(new Date(Long.valueOf(birthday)));
                    birthdayText.setText(format);
                }else{
                    birthdayText.setText("");
                }
                /*
                性别
                 */
                String sex = memberInfo.getString("sex");
                if(!StringMyUtil.isEmptyString(sex)){
                    if(sex.equals("2")){
                        sextext.setText(Utils.getString(R.string.保密));
                    }else  if(sex.equals("1")){
                        sextext.setText(Utils.getString(R.string.男));
                    }else if(sex.equals("0")){
                        sextext.setText(Utils.getString(R.string.女));
                    }
                }else {
                    sextext.setText("");
                }
                SharePreferencesUtil.putInt(getContext(), CommonStr.GRADE,memberInfo.getInteger(CommonStr.GRADE)+1);


            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        String photoPath="";
        String s="";
        if( requestCode ==1){
        if(resultCode == RESULT_OK){
            //修改昵称成功
            String nickname = data.getExtras().getString("nickname");
            nicheng.setText(nickname);
            SharePreferencesUtil.putString(getContext(),"userNickName",nickname);//保存修改成功后返回的用户昵称(聊天室需要用于展示)
        }
    } else if( requestCode ==REQUEST_PHOTO_CODE){
        if(resultCode == RESULT_OK){
            //    private String[] PHOTO_PERMISSIONS={"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};
             photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());

//                showBitMap(realPathFromUri);
             s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//图片压缩
            if (!StringMyUtil.isEmptyString(photoPath)) { ;
                uploadImg(s);
            } else {
                //压缩失败
                showToast(Utils.getString(R.string.系统出现错误请重试));
            }

        }
    }else  if(requestCode==REQUEST_CAMERA_CODE){
        if(resultCode==RESULT_OK){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  photoPath=String.valueOf(cameraSavePath);
                  s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//图片压缩
            }else {
                  photoPath = uri.getEncodedPath();
                  s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//图片压缩
            }
            uploadImg(s);
        }else {
            showToast(Utils.getString(R.string.未知错误请重试));
        }
    }
    }
    /*
    上传图片
     */
    private void uploadImg(String url) {
        HttpApiUtils.upload(getActivity(), RequestUtil.UPLOAD_IMAGE, url, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                realPath = jsonObject.getString("realUrl");
                GlideLoadViewUtil.FLoadNormalView(UserInfoFragment.this, Utils.checkImageUrl(realPath),userInfoImage);
                // 修改头像
                requestModifyImage(Utils.CPImageUrlCheck(MyApplication.getInstance(),realPath),realPath,SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l),false);

            }
            @Override
            public void onFailed(String msg) {
            }
        });
    }
    private void initView(View view) {
        sixLinear =view.findViewById(R.id.six_linear);
        birthdarLinear =view.findViewById(R.id.birthday_linear);
        account_linear =view.findViewById(R.id.account_linear);
        birthdayText =view.findViewById(R.id.birthday_text);
        sextext = view.findViewById(R.id.six_text);
        imageLinear= view .findViewById(R.id.image_frame);
        userInfoImage =view.findViewById(R.id.user_info_img);
        userInfoAccount =view.findViewById(R.id.user_info_account_tv);
        nicheng = view.findViewById(R.id.nicheng);
        phoneNum =view.findViewById(R.id.user_info_phone);
        phoneLinear =view.findViewById(R.id.user_phone_linear);
        if(mobileNumSwitch.equals("0")){
            phoneLinear.setVisibility(View.VISIBLE);
        }else {
            phoneLinear.setVisibility(View.GONE);
        }
        birthdarLinear.setOnClickListener(this);
        sixLinear.setOnClickListener(this);
        imageLinear.setOnClickListener(this);
        phoneLinear.setOnClickListener(this);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        nickNameLinear=view.findViewById(R.id.nick_name_linear);
        nickNameLinear.setOnClickListener(this);
        account_linear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday_linear:
                if(StringMyUtil.isEmptyString(birthdayText.getText())){//未设置生日前,textView内容为空,点击进入时间选择
//                    showPickerViewTime();//弹出出生日期界面
                    initPickerViewTime();
                }
                break;
            case R.id.six_linear:
                initPickerViewSix();
                break;
            case R.id.image_frame://弹出更换头像界面
                    if(bottomSwitchPop==null){
                        initBottomSwitchPop();
                    }
                    bottomSwitchPop.showAtLocation(birthdayText,Gravity.BOTTOM,0,0);
                    ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                break;
            case R.id.user_phone_linear:
                String havaePaypassword = SharePreferencesUtil.getString(getContext(), "havaPaypassword", "");//个人中心界面储存的值,判断有无设置安全密码
                String phone = SharePreferencesUtil.getString(getContext(), "phone", "");//个人中心界面储存的值,判断有无绑定手机
                if(havaePaypassword==null||havaePaypassword==""){
                    AlertDialog isExit = new AlertDialog.Builder(getContext()).create();
                    isExit.setTitle(Utils.getString(R.string.温馨提示));
                    isExit.setMessage(Utils.getString(R.string.未设置提款密码前往设置));
                    isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                             if(Utils.passwordIsEmpty()){
                                RouteUtils.skipVisitorSafeCenter(getContext());
                            }else {

                                 startActivity(new Intent(getContext(),SafeCenterActivity.class));
                             }
                        }
                    });
                    isExit.show();
                }
                else if(phone==null||phone==""){
                    Intent intent = new Intent(getContext(), PhonePasswordActivity.class);
                    intent.putExtra("havePhone",false);//跳转是传递信息,用于判断是否有设置手机
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getContext(), PhonePasswordActivity.class);
                    intent.putExtra("havePhone",true);//跳转是传递信息,用于判断是否有设置手机
                    startActivity(intent);
                }
                break;
            case  R.id.user_image_return:
                popupWindow.dismiss();
                break;
            case R.id.user_info_cancelbutton://点击头像选择的取消按钮
                popupWindow.dismiss();
                break;
            case R.id.user_info_sure_button://点击头像选择的确定按钮
                String url= SharePreferencesUtil.getString(getContext(),"newUserImage","");//点击图片后保存的url
                final String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                //传入去掉前缀的url(前端使用的图片是没有前缀的(加上前缀前端显示不了))
                final String replace = url.replace(firstImageUrl, "");
                long userId =SharePreferencesUtil.getLong(getContext(),"user_id",0L);
                popupWindow.dismiss();
                if(url!=null&&url!=""){//url不为空,将保存的url设置为头像
                    requestModifyImage(url,  replace, userId,true);
                }
                else {
                    //url为空,设置之前的头像url
                    String oldUserImage = SharePreferencesUtil.getString(getContext(), "oldUserImage", "");
                    Glide.with(getContext())
                            .load(oldUserImage)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(userInfoImage);
                    popupWindow.dismiss();
                }
                break;
            case R.id.nick_name_linear://修改昵称
//                 String userNickName = SharePreferencesUtil.getString(getContext(), "userNickName", "");
//               if(userNickName.contains(Utils.getString(R.string.萌新))){
                   startActivityForResult(new Intent(getContext(), ModifyNickNameActivity.class),1);
//               }
//                else {
//                    showtoast(Utils.getString(R.string.昵称只能修改一次));
//                }
                break;
            case R.id.account_linear:
                Utils.copyStr("userNickName",userInfoAccount.getText().toString());
                break;
            default:
                break;
        }
    }

    private void requestModifyImage(String url, String replace, long userId,boolean isSystem) {
        HashMap<String,Object> modifyImage =new HashMap<>();
        modifyImage.put("user_id",userId);
        if(isSystem){
            modifyImage.put("type",2);//2表示更改头像   修改类型(0.旧密码修改登录密码 1修改支付密码 2修改头像 3绑定手机 4修改性别 5修改生日 6通过安全密码修改登录密码 7修改昵称)
        }else {
            modifyImage.put("type",22);//2表示更改头像   修改类型(0.旧密码修改登录密码 1修改支付密码 2修改头像 3绑定手机 4修改性别 5修改生日 6通过安全密码修改登录密码 7修改昵称)

        }
        modifyImage.put("value",replace);
        Utils.docking(modifyImage, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {

                GlideLoadViewUtil.FLoadTitleView(UserInfoFragment.this,url,userInfoImage);

                SharePreferencesUtil.putString(getContext(),"image",url);
                //修改头像成功,返回个人中心时,需要更新头像
                FragmentActivity activity = getActivity();
                if(activity!=null&&!activity.isFinishing()){
                    Intent intent = activity.getIntent();
                    getActivity().setResult(1,intent);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
               showToast(messageHead.getInfo());
            }
        });
    }

    private void chooseSystemPictures() {
        LayoutInflater inflater = LayoutInflater.from(getContext());//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.user_info_image_popup_window, null);//读取布局管理器
        floatLinear=inflate.findViewById(R.id.float_linear);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        popupWindow.setAnimationStyle(R.style.popupAnimationNormol150);//设置进出动画
        popupWindow.showAtLocation(imageLinear , Gravity.BOTTOM, 0, 0); // 显示弹出窗口//显示弹出窗口
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);

                /*
                滑出更换头像页面相关设置
                 */
        initRecycle(inflate);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {//头像选择界面弹回时,清除保存的url
                if(SharePreferencesUtil.getString(getContext(),"newUserImage","")!=null){
                    SharePreferencesUtil.remove(getContext(),"newUserImage");
                    ProgressDialogUtil.darkenBackground(getActivity(),1f);
                }
            }
        });
    }
    /**
     * 检查相册权限
     */
    private void checkPhotoPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        goPhotoAlbum();
                    } else {
                        showToast(Utils.getString(R.string.拒绝必须权限后将无法正常使用app));
                    }
                });
    }

    /*
    检查相机权限
     */
    private void checkCameraPermission() {
        String[] CAMERA_PERMISSIONS ={  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(CAMERA_PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        goCamera();
                    } else {
                        showToast(Utils.getString(R.string.拒绝必须权限后将无法正常使用app));
                    }
                });
    }
    /*
调用相册
 */
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTO_CODE);
    }

    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID +".fileProvider", cameraSavePath);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION| FLAG_GRANT_WRITE_URI_PERMISSION);//允许临时的读和写
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //直接使用startActivityForResult 如果使用getActivity().startActivityForResult, activity onActivityResult中要加上super.onActivityResult(requestCode, resultCode, data)。
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        getActivity().startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    private void initBottomSwitchPop() {
        bottomSwitchPop = new BottomSwitchPop(getContext());
        bottomSwitchPop.setActionSheetClickListener(new BottomSwitchPop.ActionSheetClickListener() {
            @Override
            public void onActionSheetClickClick(int index, View view) {
                switch (index){
                    case 0://拍照
                        if(pointGrade>=editNicknameLevel){
                            checkCameraPermission();
                            bottomSwitchPop.dismiss();
                        }else {
                            showToast(String.format(Utils.getString(R.string.您需要达到达到vip才能使用本地图片),editNicknameLevel));
                        }
                        break;
                    case 1://相册
                        if(pointGrade>=editNicknameLevel){
                            checkPhotoPermissions();
                            bottomSwitchPop.dismiss();
                        }else {
                            showToast(String.format(Utils.getString(R.string.您需要达到达到vip才能使用本地图片),editNicknameLevel));
                        }
                        break;
                    case 2://系统图片
                        bottomSwitchPop.dismiss();
                        chooseSystemPictures();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initRecycle(final View inflate) {
        userImageReturn=inflate.findViewById(R.id.user_image_return);//弹回头像选择的按钮
        cancel =inflate.findViewById(R.id.user_info_cancelbutton);
        sure =inflate.findViewById(R.id.user_info_sure_button);
        bigImage =inflate.findViewById(R.id.big_image);
        recyclerView =  inflate.findViewById(R.id.user_info_recycle);
        userImageReturn.setOnClickListener(this);
        cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        dataImageList =new HashMap<>();
        Utils.docking(dataImageList, RequestUtil.REQUEST_09rzq, 1, new DockingUtil.ILoaderListener() {//获取头像列表
            @Override
            public void success(String content) {
                urlList.clear();
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray memberHeadPortraitList = jsonObject.getJSONArray("memberHeadPortraitList");
                for(int i=0;i<memberHeadPortraitList.size();i++){
                    JSONObject jsonObject1 = memberHeadPortraitList.getJSONObject(i);
                    String image = jsonObject1.getString("image");
                    String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                    image =image.replace(firstImageUrl,"");
                    String finalUrl = firstImageUrl+image;
                    UserImage userImage = new UserImage(finalUrl);
                    urlList.add(userImage);
                }
                String oldUserImage = SharePreferencesUtil.getString(getContext(), "oldUserImage", "");
                //头像为默认的头像,bigImage加载列表的第一个
//                if(oldUserImage.endsWith("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1562391789&di=85d934dfaf6e65bad4738912e6a174d3&src=http://hbimg.b0.upaiyun.com/69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658")){
                if(oldUserImage.endsWith(Utils.getString(R.string.沒有头像))){
                    Glide.with(getContext())
                            .load(urlList.get(0).getUrl())
                            .error(R.drawable.system_default_title)
                            .into(bigImage);
                }else {//头像不为默认,bigImage加载当前设置的头像
                    Glide.with(getContext())
                            .load(oldUserImage)
                            .error(R.drawable.system_default_title)
                            .into(bigImage);
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                userImageAdapter = new UserImageAdapter(urlList,getContext());
                recyclerView.setAdapter(userImageAdapter);
                setRecycleViewOnClickListener();//recycleView点击事件
                userImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {

            }

        });

    }
    /*
    修改头像pop,recycleView点击事件(点击item,显示图片到上方)
     */
    private void setRecycleViewOnClickListener() {

        userImageAdapter.setOnItemClickListener(new UserImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击图片,显示到上方大图片
                String url = urlList.get(position).getUrl();
                Glide.with(getContext())
                        .load(url)
                        .into(bigImage);
                floatLinear.setBackgroundColor(Color.WHITE);
                SharePreferencesUtil.putString(getContext(),"newUserImage",url);
            }
        });
    }

    private void initPickerViewTime() {
        TimePickerView  timePickerView=  new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(date);
                    defaultDate=instance;
                    format = sdf.format(date);
                    HashMap<String, Object> data = new HashMap<>();
                    Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
                    data.put("user_id",user_id);
                    data.put("type",5);
                    data.put("value",format);
                    data.put("oldvalue","");
                    Utils.docking(data, RequestUtil.REQUEST_10tb, 0, new DockingUtil.ILoaderListener() {
                        @Override
                        public void success(String content) {
                            JSONObject jsonObject = JSONObject.parseObject(content);
                            String message = jsonObject.getString("message");
                            birthdayText.setText(format);
//                        SharePreferencesUtil.putString(getContext(),"birthday",format);
                            showToast(message);
                        }

                        @Override
                        public void failed(MessageHead messageHead) {

                        }
                    });
                }
            })
                .setCancelText(Utils.getString(R.string.取消))//取消按钮文字
                .setSubmitText(Utils.getString(R.string.确定))//确认按钮文字
                .setTextXOffset(10,10,10,10,10,10)
                .setTitleSize(20)//标题文字大小
                .setTitleText(Utils.getString(R.string.设置生日))//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(Color.parseColor("#e6351a"))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setRangDate(mixDate,maxDate)//起始终止年月日设定
                .setLabel(Utils.getString(R.string.年),Utils.getString(R.string.月),Utils.getString(R.string.日),Utils.getString(R.string.时),Utils.getString(R.string.分),Utils.getString(R.string.秒))//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDate(defaultDate)
                .build();
        timePickerView.show();
    }

private void initPickerViewSix(){
    final ArrayList<String> list = new ArrayList<>();
    list.add(Utils.getString(R.string.保密));
    list.add(Utils.getString(R.string.男));
    list.add(Utils.getString(R.string.女));
    String toString = sextext.getText().toString();
    int selecterPosition = 0;
    for (int i = 0; i < list.size(); i++) {
        if(list.get(i).equals(toString)){
            selecterPosition=i;
        }
    }
    optionsPickerView= new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            final String sex = list.get(options1);
            int value;
            if(sex.equals(Utils.getString(R.string.保密))){
                value=2;
            }else if(sex.equals(Utils.getString(R.string.男))) {
                value=1;
            }else{
                value=0;
            }
            HashMap<String, Object> data = new HashMap<>();
            Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
            data.put("user_id",user_id);
            data.put("type",4);
            data.put("value",value);
//                data.put("oldvalue","");
            Utils.docking(data, RequestUtil.REQUEST_10tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    String message = jsonObject.getString("message");
                    sextext.setText(sex);
                    SharePreferencesUtil.putString(getContext(),"sex",sex);
                    showToast(message);
                }
                @Override
                public void failed(MessageHead messageHead) {
                    showToast(messageHead.getInfo());
                }
            });
        }
    })
            .setCancelText(Utils.getString(R.string.取消))//取消按钮文字
            .setSubmitText(Utils.getString(R.string.确定))//确认按钮文字
            .setTitleSize(20)//标题文字大小
            .setTitleText(Utils.getString(R.string.选择性别))//标题文字
            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
            .setTitleColor(Color.WHITE)//标题文字颜色
            .setSubmitColor(Color.WHITE)//确定按钮文字颜色
            .setCancelColor(Color.WHITE)//取消按钮文字颜色
            .setTitleBgColor(Color.parseColor("#e6351a"))//标题背景颜色 Night mode
            .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .setSelectOptions(selecterPosition)
            .build();
            optionsPickerView.setPicker(list);
            optionsPickerView.show();
}


    @Override
    public void onStart() {
        super.onStart();
        String phone = SharePreferencesUtil.getString(getContext(), "phone", "");
        phoneNum.setText(phone);
    }

}
