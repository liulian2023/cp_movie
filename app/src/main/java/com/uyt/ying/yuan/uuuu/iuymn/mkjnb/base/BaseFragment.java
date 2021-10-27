package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.toast.ToastUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.receiver.NetWorkStateReceiver;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MemoryLeakUtil;


public class BaseFragment extends MySupportFragment implements   NetWorkStateReceiver.NetChangeListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.main_fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //加载列表数据失败,重新加载
    public void errorRefresh(){

    }
    public void showToast(String str){
        if(StringMyUtil.isEmptyString(str)||str.equalsIgnoreCase("timeout")){
            return;
        }
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        ToastUtils.show(str);

    }

    @Nullable
    @Override
    public Context getContext() {
        Activity activity = getActivity();
        return activity;
    }




    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        ProgressDialogUtil.stop(getContext());
        MemoryLeakUtil.fixLeak(getContext());
    }
}
