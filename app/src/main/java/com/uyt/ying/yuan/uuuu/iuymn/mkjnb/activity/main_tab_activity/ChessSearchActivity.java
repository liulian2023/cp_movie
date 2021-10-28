package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.ChessSearchAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessSearchEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SaveChessLotteryUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;

public class ChessSearchActivity extends BaseActivity implements CommonAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.chess_search_back_linear)
    LinearLayout chess_search_back_linear;
    @BindView(R.id.chess_search_edit)
    EditText chess_search_edit;
    @BindView(R.id.chess_search_tv)
    TextView chess_search_tv;
    @BindView(R.id.chess_search_recycler)
    RecyclerView chess_search_recycler;
    @BindView(R.id.clear_edit_iv)
    ImageView clear_edit_iv;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    ChessSearchAdapter chessSearchAdapter;
    ArrayList<ChessSearchEntity.DataBean>chessSearchEntityArrayList = new ArrayList<>();
    static String SEARCH= Utils.getString(R.string.搜索);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_search);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        initRecycler();
        chess_search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    chess_search_tv.performClick();
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消eventBus注册
    }

    public static  void startAty(Context context){
        Intent intent = new Intent(context, ChessSearchActivity.class);
        context.startActivity(intent);
    }
    private void initRecycler() {
        chessSearchAdapter = new ChessSearchAdapter(chessSearchEntityArrayList,this);
        chess_search_recycler.setLayoutManager(new GridLayoutManager(this,4));
        chess_search_recycler.setAdapter(chessSearchAdapter);
        chessSearchAdapter.setOnItemClickListener(this);

    }
    /**
     * 更新是否是限定彩票
     * @param hbGameClassModel  包含限定彩种的idList的model
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }

    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < chessSearchEntityArrayList.size(); i++) {
            ChessSearchEntity.DataBean dataBean = chessSearchEntityArrayList.get(i);
            for (int j = 0; j < idList.size(); j++) {
                if((dataBean.getId()+"").equals(idList.get(j))){
                    dataBean.setXian(true);
                    break;
                }
            }
        }
        if(null!=chessSearchAdapter){
            chessSearchAdapter.notifyDataSetChanged();
        }
    }
    @OnTextChanged(value=R.id.chess_search_edit, callback=OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChanged(Editable s) {
        String editStr = s.toString();
        if(editStr.length()>0){
            clear_edit_iv.setVisibility(View.VISIBLE);
        }else {
            clear_edit_iv.setVisibility(View.INVISIBLE);

        }
    }
    @OnClick({R.id.chess_search_tv,R.id.clear_edit_iv,R.id.chess_search_back_linear})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.chess_search_tv:
                String inputText = chess_search_edit.getText().toString();
                if(StringMyUtil.isEmptyString(inputText)){
                    showToast(Utils.getString(R.string.请输入您要搜索的内容));
                }else {
                    requestSearchData(inputText);
                }


                break;
            case R.id.clear_edit_iv:
                chess_search_edit.setText("");
                break;
            case R.id.chess_search_back_linear:
                finish();
                break;
                default:
                    break;
        }
    }

    private void requestSearchData(String inputText) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("typename", inputText);
        data.put("loginType", 1);
        HttpApiUtils.cpShowLoadRequest(ChessSearchActivity.this, null, RequestUtil.CHESS_LIST, data, loading_linear, error_linear, reload_tv, chess_search_recycler, false, false, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(!chessSearchAdapter.haveHeaderView()){
                    View HeadView = LayoutInflater.from(ChessSearchActivity.this).inflate(R.layout.chess_search_recycle_head_layout,null);
                    chessSearchAdapter.addHeaderView(HeadView);
                }
                ChessSearchEntity chessSearchEntity = JSONObject.parseObject(result, ChessSearchEntity.class);
                List<ChessSearchEntity.DataBean> dataBeanList = chessSearchEntity.getData();
                RefreshUtil.success(1,null,loading_linear,nodata_linear,dataBeanList.size(),false,false,chessSearchEntityArrayList);
                chessSearchEntityArrayList.addAll(dataBeanList);
                chessSearchAdapter.notifyDataSetChanged();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
                    selectorId(instance);
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onItemClick(View view, int position) {
        ChessSearchEntity.DataBean dataBean = chessSearchEntityArrayList.get(position);
        int game = dataBean.getGame();
        if(game>=50){
            //点击棋牌.存入浏览记录
            String jsonString = JSONObject.toJSONString(dataBean);
            SaveChessLotteryUtil saveChessLotteryUtil = new SaveChessLotteryUtil();
            saveChessLotteryUtil.saveChessLotteryHistory(jsonString);
            String id = dataBean.getId();
            HashMap<String, Object> data = new HashMap<>();
            data.put("game",game);
            data.put("id",id);
            data.put("loginType",1);
            HttpApiUtils.CpRequest(ChessSearchActivity.this, null,RequestUtil.PLAY_CHESS, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String url = jsonObject.getString("url");
                    ChessTxWebViewActivity.startAty(ChessSearchActivity.this,url,game,id);
                }

                @Override
                public void onFailed(String msg) {
                }
            });
        }else {
            ToBetAtyUtils.toBetActivity(ChessSearchActivity.this,dataBean.getGame(),dataBean.getTypename(),Integer.parseInt(dataBean.getType_id()),dataBean.getIsopenOffice()+"",dataBean.getIsStart()+"");
        }
    }
}
