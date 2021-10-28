package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.feedback_fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MeetProblemAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateMineFeedback;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CommonProblemModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.AppUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BitmapUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DeviceUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GetPhotoFromPhotoAlbum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ImageThumbnail;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.greenrobot.eventbus.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import pub.devrel.easypermissions.EasyPermissions;
/*
我要反馈
 */
public class WantToFeedbackFragment extends BaseFragment implements View.OnClickListener , EasyPermissions.PermissionCallbacks, TextWatcher {
    @BindView(R.id.commit_button)
    Button commitBtn;
    @BindView(R.id.problem_etv)
    EditText problemEtv;
    @BindView(R.id.add_image)
    ImageView addIv;
    @BindView(R.id.mine_problem_recycle)
    RecyclerView meetRecycle;
    @BindView(R.id.edit_text_length_tv)
    TextView lengthTv;
    private MeetProblemAdapter meetProblemAdapter;
    private ArrayList<CommonProblemModel> meetModelList=new ArrayList<>();

    private PopupWindow addImagePop;
    private TextView takeCameraTv;
    private TextView choosePhotoTv;
    private TextView cancelTv;
    public static String TAG="WantToFeedbackFragment";
    private String CAMERA_PERMISSION= Manifest.permission.CAMERA;
    private String[] PHOTO_PERMISSIONS={  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private String[] CAMERA_PERMISSIONS ={  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private int REQUEST_CAMERA_CODE=1;
    private int REQUEST_PHOTO_CODE=2;
    private int READ_EXTERNAL_CODE=3;
    private File cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
    private String problemId;
    //拍照返回
    private Uri  uri;
    //图片上传返回的url
    private String realPath;
    private boolean havePhoto=false;
    CommonProblemModel problemModel;//当前选中的问题

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_want_to_feedback, container, false);
        ButterKnife.bind(this,view);
        initMeetRecy();
        problemEtv.addTextChangedListener(this);
        commitBtn.setOnClickListener(this);
        addIv.setOnClickListener(this);
        return view;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
        我遇到的问题列表
         */
    private void initMeetRecy() {
        meetProblemAdapter=new MeetProblemAdapter(meetModelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        meetRecycle.setLayoutManager(gridLayoutManager);
        meetRecycle.setAdapter(meetProblemAdapter);
        meetProblemAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                problemModel = meetModelList.get(position);
                problemId=problemModel.getProblemId();
            }
        });
        /*
        所有问题列表
         */
        getAllProblem();
    }

    private void getAllProblem() {
/*        HttpApiUtils.CpRequest(getActivity(), RequestUtil.ALL_PROBLEM, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONArray data = JSONObject.parseObject(result).getJSONArray("data");
                for (int i = 0; i < data.size(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String id = jsonObject.getString("id");
                    meetModelList.add(new CommonProblemModel(name,id));
                }
                meetProblemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFaild(String msg) {

            }
        });*/
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.我有建议),"1"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.有杂音产生),"2"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.等待超时),"3"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.网络连接失败),"4"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.购彩问题),"5"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.直播问题),"6"));
        meetProblemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_image:
                addIv.setClickable(false);
                if(addImagePop==null){
                    initAddImagePop();
                }
                addImagePop.showAtLocation(meetRecycle, Gravity.BOTTOM,0,0);
                ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                break;
            case R.id.take_camera_textview:
                checkCameraPermission();
                addImagePop.dismiss();
                break;
            case R.id.choose_photo_textview:
//                checkPhotoPermisson();
                checkPermissions();
                addImagePop.dismiss();
                break;
            case R.id.modify_title_pop_cencel:
                addImagePop.dismiss();
                break;
            case R.id.commit_button:
                requestCommit();
                break;
            default:
                break;
        }
    }

    /**
     * 提交反馈
     */
    private void requestCommit() {
        String str = problemEtv.getText().toString();
        if(StringMyUtil.isEmptyString(str)){
            showToast(Utils.getString(R.string.请描述您遇到的问题));
        }else if(str.length()<10||str.length()>100){
            showToast(Utils.getString(R.string.问题描述仅限10到100个字));
        }else if(!isSelectorItem()){
            showToast(Utils.getString(R.string.请选择问题分类方便我们更快为您解决问题));
        }else {
                //提交问题
            String uniqueId = DeviceUtils.getUniqueId(getContext());//设备号
            HashMap<String, Object> data = new HashMap<>();
            data.put("labelId",problemId);
            data.put("content",str);
//            data.put("deviceNumber",SystemUtil.getSystemModel());
//                    data.put("deviceNumber", SystemUtil.getDeviceBrand()+";"+ SystemUtil.getSystemModel()+"("+uniqueId+")");//设备信息
            data.put("userAppVersion", AppUtil.getVersionName(getContext()));//app版本号
//                    data.put("movieId",feedBackActivity.movieId);
            data.put("problem",problemModel.getProblemStr());
            if(StringMyUtil.isEmptyString(realPath)&&havePhoto){
                showToast(Utils.getString(R.string.图片上传中请稍后));
                return;
            }
            data.put("img_url",null==realPath?"":realPath);
            HttpApiUtils.CpRequest(getActivity(), this,RequestUtil.COMMIT_PROBLEM, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    realPath="";
                    havePhoto=false;
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    showToast(jsonObject.getString("message"));
                    for (int i = 0; i < meetModelList.size(); i++) {
                        meetModelList.get(i).setStatus(0);
                    }
                    meetProblemAdapter.notifyDataSetChanged();
                    addIv.setImageResource(R.drawable.add_image);
                    problemEtv.setText("");
                    lengthTv.setText(0+"/100");
                    showToast(Utils.getString(R.string.反馈成功));
                    UpdateMineFeedback event = new UpdateMineFeedback();
                    event.setUpdate(true);
                    EventBus.getDefault().postSticky(event);
                }
                @Override
                public void onFailed(String msg) {
                }
            });
        }
    }

    private void initAddImagePop() {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.take_camera_pop_layout,null);
        takeCameraTv =view.findViewById(R.id.take_camera_textview);
        choosePhotoTv =view.findViewById(R.id.choose_photo_textview);
        cancelTv=view.findViewById(R.id.modify_title_pop_cencel);
        takeCameraTv.setOnClickListener(this);
        choosePhotoTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);
        addImagePop=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        addImagePop.setAnimationStyle(R.style.pop_bottom_to_top_150);
        addImagePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                addIv.setClickable(true);
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
            }
        });
    }
    /*
   是否有选择问题分类(meetModelList中有status为1,即选中)
     */
    private boolean isSelectorItem(){
        for (int i = 0; i < meetModelList.size(); i++) {
            int status = meetModelList.get(i).getStatus();
            if(status==1){
                return  true;
            }
        }
        return false;
    }

    /**
     * 检查相机权限
     */
    private void checkPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Utils.logE(TAG, "init: 权限申请成功");
                        goPhotoAlbum();
                    } else {
                        Utils.logE(TAG, "init: 权限申请失败");
                        showToast(Utils.getString(R.string.拒绝必须权限后将无法正常使用app));
                    }
                });
    }

    /*
    检查权限
     */
    private void checkCameraPermission() {
        if(EasyPermissions.hasPermissions(getContext(), CAMERA_PERMISSIONS)){
            goCamera();
        }else {
            EasyPermissions.requestPermissions(this,Utils.getString(R.string.需要获取您的相机使用权限),REQUEST_CAMERA_CODE, CAMERA_PERMISSIONS);
        }
    }

    private void checkPhotoPermisson(){
        if(EasyPermissions.hasPermissions(getContext(),PHOTO_PERMISSIONS)){
            goPhotoAlbum();
        }else {
            EasyPermissions.requestPermissions(this,Utils.getString(R.string.需要获取访问您相册的权限),REQUEST_PHOTO_CODE,PHOTO_PERMISSIONS);
        }
    }
    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID +".fileProvider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //直接使用startActivityForResult 如果使用getActivity().startActivityForResult, activity onActivityResult中要加上super.onActivityResult(requestCode, resultCode, data)。
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        getActivity().startActivityForResult(intent, REQUEST_CAMERA_CODE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photoPath;
        String s;
        //相机返回
        if(requestCode==REQUEST_CAMERA_CODE){
            if(resultCode==RESULT_OK){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoPath=String.valueOf(cameraSavePath);
//                    showBitMap(String.valueOf(cameraSavePath));
//                    showBitMap(photoPath);
                    s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//图片压缩
                }else {
                    photoPath = uri.getEncodedPath();
                    s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//图片压缩
                }
                havePhoto=true;
                uploadImg(s);
            }else {
                showToast(Utils.getString(R.string.未知错误请重试));
            }
        }
        //相册返回
        else if(requestCode==REQUEST_PHOTO_CODE){
            if(resultCode==RESULT_OK){
                //    private String[] PHOTO_PERMISSIONS={"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};
                String realPathFromUri = GetPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());
                photoPath = realPathFromUri;
//                showBitMap(realPathFromUri);
                s = BitmapUtils.compressReSave(realPathFromUri, getContext(), 400);//图片压缩
                if (!StringMyUtil.isEmptyString(photoPath)) {
                    havePhoto=true;
                    uploadImg(s);
                } else {
                    //压缩失败
                    showToast(Utils.getString(R.string.系统出现错误请重试));
                }

            }else {
                showToast(Utils.getString(R.string.图片选取出错请重试));
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
                GlideLoadViewUtil.FLoadNormalView(WantToFeedbackFragment.this, Utils.checkImageUrl(realPath),addIv);
            }
            @Override
            public void onFailed(String msg) {
             havePhoto=false;
            }
        });
    }


        /*
        选择图片或者拍照返回后,在界面显示图片缩略图
         */
    private void showBitMap(String path) {
        //将拍照的图片取出并缩小后显示在界面上
        Bitmap camorabitmap = BitmapFactory.decodeFile(path);
        if(null!=camorabitmap){
            int scale = ImageThumbnail.reckonThumbnail(camorabitmap.getWidth(),camorabitmap.getHeight(), addIv.getWidth(), addIv.getHeight());
            Bitmap bitMap = ImageThumbnail.PicZoom(camorabitmap, camorabitmap.getWidth() / scale,camorabitmap.getHeight() / scale);
            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
            camorabitmap.recycle();
            addIv.setImageBitmap(bitMap);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode==REQUEST_CAMERA_CODE){
            goCamera();
        }else if(requestCode==REQUEST_PHOTO_CODE){
            goPhotoAlbum();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast(Utils.getString(R.string.请同意权限否则相关功能无法使用));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = problemEtv.getText().toString().length();
        if(length<10||length>100){
            if(length!=0){
                String str="<font color=\"#FF0000\">"+length+"</font>"+"/100";
                lengthTv.setText(Html.fromHtml(str));
            }
        }else {
            lengthTv.setText(length+"/100");
        }
    }
}
