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
????????????
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
    //????????????
    private Uri  uri;
    //?????????????????????url
    private String realPath;
    private boolean havePhoto=false;
    CommonProblemModel problemModel;//?????????????????????

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
        ????????????????????????
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
        ??????????????????
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
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.????????????),"1"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.???????????????),"2"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.????????????),"3"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.??????????????????),"4"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.????????????),"5"));
            meetModelList.add(new CommonProblemModel(Utils.getString(R.string.????????????),"6"));
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
     * ????????????
     */
    private void requestCommit() {
        String str = problemEtv.getText().toString();
        if(StringMyUtil.isEmptyString(str)){
            showToast(Utils.getString(R.string.???????????????????????????));
        }else if(str.length()<10||str.length()>100){
            showToast(Utils.getString(R.string.??????????????????10???100??????));
        }else if(!isSelectorItem()){
            showToast(Utils.getString(R.string.?????????????????????????????????????????????????????????));
        }else {
                //????????????
            String uniqueId = DeviceUtils.getUniqueId(getContext());//?????????
            HashMap<String, Object> data = new HashMap<>();
            data.put("labelId",problemId);
            data.put("content",str);
//            data.put("deviceNumber",SystemUtil.getSystemModel());
//                    data.put("deviceNumber", SystemUtil.getDeviceBrand()+";"+ SystemUtil.getSystemModel()+"("+uniqueId+")");//????????????
            data.put("userAppVersion", AppUtil.getVersionName(getContext()));//app?????????
//                    data.put("movieId",feedBackActivity.movieId);
            data.put("problem",problemModel.getProblemStr());
            if(StringMyUtil.isEmptyString(realPath)&&havePhoto){
                showToast(Utils.getString(R.string.????????????????????????));
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
                    showToast(Utils.getString(R.string.????????????));
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
   ???????????????????????????(meetModelList??????status???1,?????????)
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
     * ??????????????????
     */
    private void checkPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Utils.logE(TAG, "init: ??????????????????");
                        goPhotoAlbum();
                    } else {
                        Utils.logE(TAG, "init: ??????????????????");
                        showToast(Utils.getString(R.string.??????????????????????????????????????????app));
                    }
                });
    }

    /*
    ????????????
     */
    private void checkCameraPermission() {
        if(EasyPermissions.hasPermissions(getContext(), CAMERA_PERMISSIONS)){
            goCamera();
        }else {
            EasyPermissions.requestPermissions(this,Utils.getString(R.string.????????????????????????????????????),REQUEST_CAMERA_CODE, CAMERA_PERMISSIONS);
        }
    }

    private void checkPhotoPermisson(){
        if(EasyPermissions.hasPermissions(getContext(),PHOTO_PERMISSIONS)){
            goPhotoAlbum();
        }else {
            EasyPermissions.requestPermissions(this,Utils.getString(R.string.????????????????????????????????????),REQUEST_PHOTO_CODE,PHOTO_PERMISSIONS);
        }
    }
    //??????????????????
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID +".fileProvider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //????????????startActivityForResult ????????????getActivity().startActivityForResult, activity onActivityResult????????????super.onActivityResult(requestCode, resultCode, data)???
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        getActivity().startActivityForResult(intent, REQUEST_CAMERA_CODE);
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
        //????????????
        if(requestCode==REQUEST_CAMERA_CODE){
            if(resultCode==RESULT_OK){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoPath=String.valueOf(cameraSavePath);
//                    showBitMap(String.valueOf(cameraSavePath));
//                    showBitMap(photoPath);
                    s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//????????????
                }else {
                    photoPath = uri.getEncodedPath();
                    s = BitmapUtils.compressReSave(photoPath, getContext(), 400);//????????????
                }
                havePhoto=true;
                uploadImg(s);
            }else {
                showToast(Utils.getString(R.string.?????????????????????));
            }
        }
        //????????????
        else if(requestCode==REQUEST_PHOTO_CODE){
            if(resultCode==RESULT_OK){
                //    private String[] PHOTO_PERMISSIONS={"android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};
                String realPathFromUri = GetPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());
                photoPath = realPathFromUri;
//                showBitMap(realPathFromUri);
                s = BitmapUtils.compressReSave(realPathFromUri, getContext(), 400);//????????????
                if (!StringMyUtil.isEmptyString(photoPath)) {
                    havePhoto=true;
                    uploadImg(s);
                } else {
                    //????????????
                    showToast(Utils.getString(R.string.???????????????????????????));
                }

            }else {
                showToast(Utils.getString(R.string.???????????????????????????));
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
                GlideLoadViewUtil.FLoadNormalView(WantToFeedbackFragment.this, Utils.checkImageUrl(realPath),addIv);
            }
            @Override
            public void onFailed(String msg) {
             havePhoto=false;
            }
        });
    }


        /*
        ?????????????????????????????????,??????????????????????????????
         */
    private void showBitMap(String path) {
        //??????????????????????????????????????????????????????
        Bitmap camorabitmap = BitmapFactory.decodeFile(path);
        if(null!=camorabitmap){
            int scale = ImageThumbnail.reckonThumbnail(camorabitmap.getWidth(),camorabitmap.getHeight(), addIv.getWidth(), addIv.getHeight());
            Bitmap bitMap = ImageThumbnail.PicZoom(camorabitmap, camorabitmap.getWidth() / scale,camorabitmap.getHeight() / scale);
            //??????Bitmap????????????????????????????????????????????????????????????out of memory??????
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
        showToast(Utils.getString(R.string.?????????????????????????????????????????????));
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
