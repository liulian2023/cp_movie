package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.ChooseAreaAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChooseAreaEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ProvinceJsonBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GetJsonDataUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import java.util.ArrayList;

public class ChooseAreaPop extends BasePopupWindow2 {
    private RecyclerView choose_area_recycler;
    private ChooseAreaAdapter chooseAreaAdapter;
    private ArrayList<ChooseAreaEntity>chooseAreaEntityArrayList = new ArrayList<>();

    public ChooseAreaPop(Context context, boolean focusable) {
        super(context, focusable);
        setAnimationStyle(R.style.popAlphaanim0_3);
        initRecycle();
        initJsonData();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.choose_area_pop_layout,null);
        choose_area_recycler= rootView.findViewById(R.id.choose_area_recycler);
    }

    private void initJsonData() {//解析数据


        String JsonData = new GetJsonDataUtil().getJson(mContext, "province.json");//获取assets目录下的json文件数据

        ArrayList<ProvinceJsonBean> provinceJsonBean = parseData(JsonData);
        /**
         * 默认选中用户ip所在地的item model
         */
        int scrollPosition;
        String province = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.PROVINCE, "");
        ChooseAreaEntity areaEntity = new ChooseAreaEntity(Utils.getString(R.string.不限));
        if(province.equals(Utils.getString(R.string.未知))||province.equals(Utils.getString(R.string.保留))){
            areaEntity.setStatus(1);
        }
        chooseAreaEntityArrayList.add(areaEntity);
        ChooseAreaEntity chooseAreaEntity;
        for (int i = 0; i < provinceJsonBean.size(); i++) {
            String cityName = provinceJsonBean.get(i).getName();
             chooseAreaEntity = new ChooseAreaEntity(cityName);
            if(cityName.contains(province)||province.contains(cityName)){
                chooseAreaEntity.setStatus(1);
            }
            chooseAreaEntityArrayList.add(chooseAreaEntity);
            if(chooseAreaEntity!=null){
                choose_area_recycler.scrollToPosition(chooseAreaEntityArrayList.indexOf(chooseAreaEntity));
            }
            chooseAreaAdapter.notifyDataSetChanged();
        }
    }

    public ArrayList<ProvinceJsonBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceJsonBean> detail = new ArrayList<>();
        try {
            org.json.JSONArray data = new org.json.JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
    private void initRecycle() {
        chooseAreaAdapter = new ChooseAreaAdapter(R.layout.choose_area_recycler_item,chooseAreaEntityArrayList);
        choose_area_recycler.setLayoutManager(new GridLayoutManager(mContext,3));
        choose_area_recycler.setAdapter(chooseAreaAdapter);
        chooseAreaAdapter.addChildClickViewIds(R.id.choose_area_recycler_name_rbtn);
        chooseAreaAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()){
                    case R.id.choose_area_recycler_name_rbtn:
                        checked(position);
                        if(mOnPopItemClick!=null){
                            mOnPopItemClick.onPopItemClick(view,position);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void checked(int position) {

        for (int a = 0; a< chooseAreaEntityArrayList.size(); a++){
            chooseAreaEntityArrayList.get(a).setStatus(0);
        }
        chooseAreaEntityArrayList.get(position).setStatus(1);
        chooseAreaAdapter.notifyDataSetChanged();
    }
}
