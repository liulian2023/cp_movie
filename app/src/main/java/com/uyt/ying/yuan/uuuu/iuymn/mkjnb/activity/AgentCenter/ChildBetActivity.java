package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import com.google.android.material.tabs.TabLayout;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.PagerAdapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildBetAndDealAllPupopwindowAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.FragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.ChildBetEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetAndDealAllPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ChildBetActivity extends BaseActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    private TextView back;
    private TabLayout mtab;//tab导航栏
//    private String[] titles = new String[]{Utils.getString(R.string.全部),Utils.getString(R.string.已中奖),Utils.getString(R.string.未中奖),Utils.getString(R.string.等待开奖)};//tab Title数组
    private ArrayList<String> titles=new ArrayList<>();
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPopArrayList =new ArrayList<>();//pupopwindoew弹出窗数据
    private LinearLayout chooseLinear;//筛选按钮
    private View inflate;
    private PopupWindow popupWindow;
    private LinearLayout wrapLinear;
    private ChildBetAndDealAllPupopwindowAdapter childBetAndDealAllPupopwindowAdapter;//pupopwindoew弹出窗适配器
    private RadioButton today;
    private RadioButton yesToday;
    private RadioButton seven;
    private Button chooseButton;
    private EditText seaechEdit;
    private int RadioButtonPosition;
    private TextView seachText;
    private String status;
    private int pageNum=1;
    private int pageSize=15;
    private Date startDate;
    private Date endDate;
    private String game;
    private String typoeId;

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_bet);
        bindView();
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {
        mtab=findViewById(R.id.mtab);
        titles.add(Utils.getString(R.string.全部));
        titles.add(getString(R.string.已中奖));
        titles.add(getString(R.string.未中奖));
        titles.add(getString(R.string.等待开奖));
        for(int i=0;i<titles.size();i++){
            mtab.addTab(mtab.newTab());
        }
        for(int i=0;i<titles.size();i++){
            mtab.getTabAt(i).setText(titles.get(i));
        }
        mtab.addOnTabSelectedListener(this);
//        ArrayList<SoftReference<Fragment>> fragmentArrayList=new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("tabName",titles.get(i));
            ChildBetFragment childBetFragment = new ChildBetFragment();
            childBetFragment.setArguments(bundle);
//            fragmentArrayList.add(new SoftReference<>(childBetFragment));
            fragmentArrayList.add(childBetFragment);
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
//        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
        viewPager.setAdapter(fragmentAdapter);
        mtab.setupWithViewPager(viewPager);

        RecyclerView recyclerViewPup =inflate.findViewById(R.id.child_bet_pupopwindow_recycle);
        childBetAndDealAllPupopwindowAdapter = new ChildBetAndDealAllPupopwindowAdapter(childBetAndDealAllPopArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewPup.setLayoutManager(gridLayoutManager);
        recyclerViewPup.setAdapter(childBetAndDealAllPupopwindowAdapter);
        childBetAndDealAllPupopwindowAdapter.setOnItemClickListener(new ChildBetAndDealAllPupopwindowAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(ChildBetAndDealAllPupopwindowAdapter.MyHolder view, int position) {
                RadioButtonPosition=position;
                game = childBetAndDealAllPopArrayList.get(RadioButtonPosition).getGame();
                typoeId = childBetAndDealAllPopArrayList.get(RadioButtonPosition).getTypoeId();
            }
        });

        Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {//彩票分类
            @Override
            public void success(String content) {
                childBetAndDealAllPopArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray gameClassList = jsonObject1.getJSONArray("gameClassListUtils.getString(R.string.);
                ChildBetAndDealAllPop child = new ChildBetAndDealAllPop(getString(R.string.全部彩票), )", "", "");
                child.setStatus(1);//默认点击
                childBetAndDealAllPopArrayList.add(child);
                for (int i=0;i<gameClassList.size();i++) {
                    JSONObject jsonObject = gameClassList.getJSONObject(i);
                    String game = jsonObject.getString("game");//大分类
                    String type_id = jsonObject.getString("type_id");//二级分类
                    String image = jsonObject.getString("image");
                    String firstImageUrl = SharePreferencesUtil.getString(ChildBetActivity.this, "FirstImageUrl", "");
                    String finalImg = firstImageUrl + image;//图片路劲
                    String typename = jsonObject.getString("typename");//彩票名称
                   childBetAndDealAllPopArrayList.add(new ChildBetAndDealAllPop(typename,finalImg,game,type_id));

                }
                childBetAndDealAllPupopwindowAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });

    }

    private void bindView() {

        back=findViewById(R.id.agent_journaling_return);
        chooseLinear=findViewById(R.id.choose_linear_bet);
        wrapLinear =findViewById(R.id.linear);
        seaechEdit=findViewById(R.id.search_edit);
        seachText=findViewById(R.id.search_text);
        chooseLinear.setOnClickListener(this);
        seachText.setOnClickListener(this);
        back.setOnClickListener(this);
        viewPager=findViewById(R.id.view_pager);
        LayoutInflater inflater = LayoutInflater.from(this);//布局管理器
        inflate = inflater.inflate(R.layout.child_bet_dealall_choose_pupopwindow, null); //筛选弹出窗视图
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindow.setTouchable(true);//响应内部点击
        today =inflate.findViewById(R.id.today);
        yesToday =inflate.findViewById(R.id.yestoday);
        seven =inflate.findViewById(R.id.this_week);
        chooseButton =inflate.findViewById(R.id.choose_button_all);
        chooseButton.setOnClickListener(this);
        today.setOnClickListener(this);
        yesToday.setOnClickListener(this);
        seven.setOnClickListener(this);
        today.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_linear_bet:
            popupWindow.showAsDropDown(wrapLinear,Gravity.BOTTOM,0,0);
            break;
            case R.id.choose_button_all:
                pageNum=1;
                initTime();
                EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,true));
                popupWindow.dismiss();
            break;
            case R.id.search_text:
                pageNum=1;
                initTime();
                EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,true));
                break;
            case R.id.agent_journaling_return:
                finish();
                break;
        }
    }

    private void initTime() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int j = 0; j <fragments.size() ; j++) {
            View view = fragments.get(j).getView();
            if(view!=null){

                RefreshLayout refreshLayout= view.findViewById(R.id.refresh);
                refreshLayout.resetNoMoreData();
            }
        }
        if(today.isChecked()){
            Date start = new Date();
            Date end = new Date();
            startDate=start;
            endDate=end;
        }
        else if(yesToday.isChecked()){
            Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());
            Date startTime = DateUtil.getStartTime(yesterdayCalendar);
            Date endTime = DateUtil.getEndTime(yesterdayCalendar);
            startDate=startTime;
            endDate=endTime;
        }
        else if(seven.isChecked()){
            Date start = DateUtil.getDateBefore(new Date(), 6);
            Date end = new Date();
            startDate=start;
            endDate=end;
        }
    }

    private String initEdit(){
       String editText = seaechEdit.getText().toString();
        if (editText.isEmpty()) {
            editText = null;
        }
        return  editText;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getText().equals(Utils.getString(R.string.全部))){
            pageNum=1;
            status="";
            initTime();
            EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,false));
        }
        else if(tab.getText().equals(getString(R.string.已中奖))){
            pageNum=1;
            status=getString(R.string.中奖);
            initTime();
            EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,false));
        }
        else if(tab.getText().equals(getString(R.string.未中奖))){
            pageNum=1;
            status=getString(R.string.未中);
            initTime();
            EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,false));
        }
        else if(tab.getText().equals(getString(R.string.等待开奖))){
            pageNum=1;
            status=getString(R.string.等待开奖);
            initTime();
            EventBus.getDefault().postSticky(new ChildBetEvenModel(pageNum,pageSize,status,startDate,endDate,initEdit(),game,typoeId,false,false));
        }
    }



    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
