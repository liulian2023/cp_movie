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
????????????
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
    private Map<String, Object> dataImageList;//??????????????????
    private Map<String, Object>  modifyImage;//????????????
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
    //??????????????????????????????
    private Calendar mixDate;
    //??????????????????????????????
    private Calendar maxDate;
    //????????????????????????????????????
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
        //???????????????????????????1()
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
                SharePreferencesUtil.putString(getContext(),"userInfo",result);//??????????????????
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                /*
                ????????????
                 */
                String image = memberInfo.getString("image");
                String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                String finalImgUrl="";
                if(!StringMyUtil.isEmptyString(image)){
                    image =image.replace(firstImageUrl,"");
                    finalImgUrl = firstImageUrl+image;
                }else {
                    finalImgUrl=Utils.getString(R.string.????????????);
                }
                SharePreferencesUtil.putString(getContext(),"oldUserImage",finalImgUrl);
                Glide.with(getContext())
                        .load(finalImgUrl)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .error(R.drawable.system_default_title)
                        .into(userInfoImage);
                   /*
                  ?????????
                  */
                String nickname = memberInfo.getString("nickname");
                userInfoAccount.setText(nickname);
                /*
                ??????
                 */
                String userNickName = memberInfo.getString("userNickName");
                nicheng.setText(userNickName);

                String phone = memberInfo.getString("phone");
                if(phone==null){
                    phoneNum.setText(Utils.getString(R.string.?????????));
                }
                else {
                    phoneNum.setText(phone);
                }
                /*
                ??????
                 */
                String birthday = memberInfo.getString("birthday");
                if(!StringMyUtil.isEmptyString(birthday)){
                    String format = sdf.format(new Date(Long.valueOf(birthday)));
                    birthdayText.setText(format);
                }else{
                    birthdayText.setText("");
                }
                /*
                ??????
                 */
                String sex = memberInfo.getString("sex");
                if(!StringMyUtil.isEmptyString(sex)){
                    if(sex.equals("2")){
                        sextext.setText(Utils.getString(R.string.??????));
                    }else  if(sex.equals("1")){
                        sextext.setText(Utils.getString(R.string.???));
                    }else if(sex.equals("0")){
                        sextext.setText(Utils.getString(R.string.???));
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
            //??????????????????
            String nickname = data.getExtras().getString("nickname");
            nicheng.setText(nickname);
            SharePreferencesUtil.putString(getContext(),"userNickName",nickname);//??????????????????????????????????????????(???????????????????????????)
        }
    } else if( requestCode ==REQUEST_PHOTO_CODE){
        if(resultCode == RESULT_OK){
            //    private String[] PHOTO_PERMISSIONS={"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};
             photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());

//                showBitMap(realPathFromUri);
             s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//????????????
            if (!StringMyUtil.isEmptyString(photoPath)) { ;
                uploadImg(s);
            } else {
                //????????????
                showToast(Utils.getString(R.string.???????????????????????????));
            }

        }
    }else  if(requestCode==REQUEST_CAMERA_CODE){
        if(resultCode==RESULT_OK){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  photoPath=String.valueOf(cameraSavePath);
                  s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//????????????
            }else {
                  photoPath = uri.getEncodedPath();
                  s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//????????????
            }
            uploadImg(s);
        }else {
            showToast(Utils.getString(R.string.?????????????????????));
        }
    }
    }
    /*
    ????????????
     */
    private void uploadImg(String url) {
        HttpApiUtils.upload(getActivity(), RequestUtil.UPLOAD_IMAGE, url, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                realPath = jsonObject.getString("realUrl");
                GlideLoadViewUtil.FLoadNormalView(UserInfoFragment.this, Utils.checkImageUrl(realPath),userInfoImage);
                // ????????????
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
                if(StringMyUtil.isEmptyString(birthdayText.getText())){//??????????????????,textView????????????,????????????????????????
//                    showPickerViewTime();//????????????????????????
                    initPickerViewTime();
                }
                break;
            case R.id.six_linear:
                initPickerViewSix();
                break;
            case R.id.image_frame://????????????????????????
                    if(bottomSwitchPop==null){
                        initBottomSwitchPop();
                    }
                    bottomSwitchPop.showAtLocation(birthdayText,Gravity.BOTTOM,0,0);
                    ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                break;
            case R.id.user_phone_linear:
                String havaePaypassword = SharePreferencesUtil.getString(getContext(), "havaPaypassword", "");//??????????????????????????????,??????????????????????????????
                String phone = SharePreferencesUtil.getString(getContext(), "phone", "");//??????????????????????????????,????????????????????????
                if(havaePaypassword==null||havaePaypassword==""){
                    AlertDialog isExit = new AlertDialog.Builder(getContext()).create();
                    isExit.setTitle(Utils.getString(R.string.????????????));
                    isExit.setMessage(Utils.getString(R.string.?????????????????????????????????));
                    isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.??????), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.??????), new DialogInterface.OnClickListener() {
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
                    intent.putExtra("havePhone",false);//?????????????????????,?????????????????????????????????
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getContext(), PhonePasswordActivity.class);
                    intent.putExtra("havePhone",true);//?????????????????????,?????????????????????????????????
                    startActivity(intent);
                }
                break;
            case  R.id.user_image_return:
                popupWindow.dismiss();
                break;
            case R.id.user_info_cancelbutton://?????????????????????????????????
                popupWindow.dismiss();
                break;
            case R.id.user_info_sure_button://?????????????????????????????????
                String url= SharePreferencesUtil.getString(getContext(),"newUserImage","");//????????????????????????url
                final String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                //?????????????????????url(???????????????????????????????????????(??????????????????????????????))
                final String replace = url.replace(firstImageUrl, "");
                long userId =SharePreferencesUtil.getLong(getContext(),"user_id",0L);
                popupWindow.dismiss();
                if(url!=null&&url!=""){//url?????????,????????????url???????????????
                    requestModifyImage(url,  replace, userId,true);
                }
                else {
                    //url??????,?????????????????????url
                    String oldUserImage = SharePreferencesUtil.getString(getContext(), "oldUserImage", "");
                    Glide.with(getContext())
                            .load(oldUserImage)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(userInfoImage);
                    popupWindow.dismiss();
                }
                break;
            case R.id.nick_name_linear://????????????
//                 String userNickName = SharePreferencesUtil.getString(getContext(), "userNickName", "");
//               if(userNickName.contains(Utils.getString(R.string.??????))){
                   startActivityForResult(new Intent(getContext(), ModifyNickNameActivity.class),1);
//               }
//                else {
//                    showtoast(Utils.getString(R.string.????????????????????????));
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
            modifyImage.put("type",2);//2??????????????????   ????????????(0.??????????????????????????? 1?????????????????? 2???????????? 3???????????? 4???????????? 5???????????? 6???????????????????????????????????? 7????????????)
        }else {
            modifyImage.put("type",22);//2??????????????????   ????????????(0.??????????????????????????? 1?????????????????? 2???????????? 3???????????? 4???????????? 5???????????? 6???????????????????????????????????? 7????????????)

        }
        modifyImage.put("value",replace);
        Utils.docking(modifyImage, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {

                GlideLoadViewUtil.FLoadTitleView(UserInfoFragment.this,url,userInfoImage);

                SharePreferencesUtil.putString(getContext(),"image",url);
                //??????????????????,?????????????????????,??????????????????
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
        LayoutInflater inflater = LayoutInflater.from(getContext());//??????LayoutInflater??????
        View inflate = inflater.inflate(R.layout.user_info_image_popup_window, null);//?????????????????????
        floatLinear=inflate.findViewById(R.id.float_linear);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
        popupWindow.setAnimationStyle(R.style.popupAnimationNormol150);//??????????????????
        popupWindow.showAtLocation(imageLinear , Gravity.BOTTOM, 0, 0); // ??????????????????//??????????????????
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);

                /*
                ????????????????????????????????????
                 */
        initRecycle(inflate);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {//???????????????????????????,???????????????url
                if(SharePreferencesUtil.getString(getContext(),"newUserImage","")!=null){
                    SharePreferencesUtil.remove(getContext(),"newUserImage");
                    ProgressDialogUtil.darkenBackground(getActivity(),1f);
                }
            }
        });
    }
    /**
     * ??????????????????
     */
    private void checkPhotoPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        goPhotoAlbum();
                    } else {
                        showToast(Utils.getString(R.string.??????????????????????????????????????????app));
                    }
                });
    }

    /*
    ??????????????????
     */
    private void checkCameraPermission() {
        String[] CAMERA_PERMISSIONS ={  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(CAMERA_PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        goCamera();
                    } else {
                        showToast(Utils.getString(R.string.??????????????????????????????????????????app));
                    }
                });
    }
    /*
????????????
 */
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTO_CODE);
    }

    //??????????????????
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID +".fileProvider", cameraSavePath);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION| FLAG_GRANT_WRITE_URI_PERMISSION);//????????????????????????
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //????????????startActivityForResult ????????????getActivity().startActivityForResult, activity onActivityResult????????????super.onActivityResult(requestCode, resultCode, data)???
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        getActivity().startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    private void initBottomSwitchPop() {
        bottomSwitchPop = new BottomSwitchPop(getContext());
        bottomSwitchPop.setActionSheetClickListener(new BottomSwitchPop.ActionSheetClickListener() {
            @Override
            public void onActionSheetClickClick(int index, View view) {
                switch (index){
                    case 0://??????
                        if(pointGrade>=editNicknameLevel){
                            checkCameraPermission();
                            bottomSwitchPop.dismiss();
                        }else {
                            showToast(String.format(Utils.getString(R.string.?????????????????????vip????????????????????????),editNicknameLevel));
                        }
                        break;
                    case 1://??????
                        if(pointGrade>=editNicknameLevel){
                            checkPhotoPermissions();
                            bottomSwitchPop.dismiss();
                        }else {
                            showToast(String.format(Utils.getString(R.string.?????????????????????vip????????????????????????),editNicknameLevel));
                        }
                        break;
                    case 2://????????????
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
        userImageReturn=inflate.findViewById(R.id.user_image_return);//???????????????????????????
        cancel =inflate.findViewById(R.id.user_info_cancelbutton);
        sure =inflate.findViewById(R.id.user_info_sure_button);
        bigImage =inflate.findViewById(R.id.big_image);
        recyclerView =  inflate.findViewById(R.id.user_info_recycle);
        userImageReturn.setOnClickListener(this);
        cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        dataImageList =new HashMap<>();
        Utils.docking(dataImageList, RequestUtil.REQUEST_09rzq, 1, new DockingUtil.ILoaderListener() {//??????????????????
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
                //????????????????????????,bigImage????????????????????????
//                if(oldUserImage.endsWith("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1562391789&di=85d934dfaf6e65bad4738912e6a174d3&src=http://hbimg.b0.upaiyun.com/69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658")){
                if(oldUserImage.endsWith(Utils.getString(R.string.????????????))){
                    Glide.with(getContext())
                            .load(urlList.get(0).getUrl())
                            .error(R.drawable.system_default_title)
                            .into(bigImage);
                }else {//??????????????????,bigImage???????????????????????????
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
                setRecycleViewOnClickListener();//recycleView????????????
                userImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {

            }

        });

    }
    /*
    ????????????pop,recycleView????????????(??????item,?????????????????????)
     */
    private void setRecycleViewOnClickListener() {

        userImageAdapter.setOnItemClickListener(new UserImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //????????????,????????????????????????
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
                .setCancelText(Utils.getString(R.string.??????))//??????????????????
                .setSubmitText(Utils.getString(R.string.??????))//??????????????????
                .setTextXOffset(10,10,10,10,10,10)
                .setTitleSize(20)//??????????????????
                .setTitleText(Utils.getString(R.string.????????????))//????????????
                .setOutSideCancelable(true)//???????????????????????????????????????????????????????????????
                .isCyclic(false)//??????????????????
                .setTitleColor(Color.WHITE)//??????????????????
                .setSubmitColor(Color.WHITE)//????????????????????????
                .setCancelColor(Color.WHITE)//????????????????????????
                .setTitleBgColor(Color.parseColor("#e6351a"))//?????????????????? Night mode
                .setBgColor(Color.WHITE)//?????????????????? Night mode
                .setRangDate(mixDate,maxDate)//???????????????????????????
                .setLabel(Utils.getString(R.string.???),Utils.getString(R.string.???),Utils.getString(R.string.???),Utils.getString(R.string.???),Utils.getString(R.string.???),Utils.getString(R.string.???))//?????????????????????????????????
                .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                .setDate(defaultDate)
                .build();
        timePickerView.show();
    }

private void initPickerViewSix(){
    final ArrayList<String> list = new ArrayList<>();
    list.add(Utils.getString(R.string.??????));
    list.add(Utils.getString(R.string.???));
    list.add(Utils.getString(R.string.???));
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
            if(sex.equals(Utils.getString(R.string.??????))){
                value=2;
            }else if(sex.equals(Utils.getString(R.string.???))) {
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
            .setCancelText(Utils.getString(R.string.??????))//??????????????????
            .setSubmitText(Utils.getString(R.string.??????))//??????????????????
            .setTitleSize(20)//??????????????????
            .setTitleText(Utils.getString(R.string.????????????))//????????????
            .setOutSideCancelable(true)//???????????????????????????????????????????????????????????????
            .setTitleColor(Color.WHITE)//??????????????????
            .setSubmitColor(Color.WHITE)//????????????????????????
            .setCancelColor(Color.WHITE)//????????????????????????
            .setTitleBgColor(Color.parseColor("#e6351a"))//?????????????????? Night mode
            .setBgColor(Color.WHITE)//?????????????????? Night mode
            .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
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
