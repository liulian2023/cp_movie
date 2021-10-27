package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class LiveViewModel extends BaseViewModel<LiveRepo> {

    private MutableLiveData<STATE> pageState;
    private MutableLiveData<List<LiveEntity.AnchorMemberRoomListBean>> liveData;
    private MutableLiveData<Integer>pageNum;
    LiveEntity.AnchorMemberRoomListBean copyAnchorRoomBean ;


    public LiveViewModel(@NonNull Application application) {
        super(application);
    }


    //页面状态
    public enum STATE {
        NORMAL,
        REFRESH,
        CLOSE
    }

    public MutableLiveData<STATE> getPageState() {
        if (pageState == null) {
            pageState = new MutableLiveData<>();
        }
        return pageState;
    }

    public MutableLiveData<List<LiveEntity.AnchorMemberRoomListBean>> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }
    public MutableLiveData<Integer> getPageNum() {
        if (pageNum == null) {
            pageNum = new MutableLiveData<>();
        }
        return pageNum;
    }

    /**
     * 增加第一条当前直播间数据
     * @param anchorRoomBean
     * @return
     */
    public MutableLiveData<List<LiveEntity.AnchorMemberRoomListBean>> addAnchorRoomData(LiveEntity.AnchorMemberRoomListBean anchorRoomBean){
        liveData = getLiveData();
        List<LiveEntity.AnchorMemberRoomListBean> liveroomDataList = liveData.getValue();
        if (liveroomDataList==null){
            liveroomDataList  = new ArrayList<>();
        }
        liveroomDataList.add(anchorRoomBean);
        liveData.postValue(liveroomDataList);
        return liveData;
    }

    /**
     * 分页 请求直播间数据  以及 去重 当前直播列表第一条数据
     * @param pageNo
     * @param pageSize
     */
    public void reqLiveData(int pageNo, int pageSize,String categoryId,String area,LiveEntity.AnchorMemberRoomListBean anchorRoomBean) {
        copyAnchorRoomBean = anchorRoomBean;
        mRepository.getDataList(pageNo, pageSize, categoryId,area,new CallBack() {
            @Override
            public void Success(Object object) {
                if(object instanceof  LiveEntity){
                    LiveEntity liveEntity = (LiveEntity) object;
        /*            if (liveEntity==null||liveEntity.getAnchorMemberRoomList()==null||liveEntity.getAnchorMemberRoomList().size()==0){
                        ToastUtils.showToast(Utils.getString(R.string.我是有底线的~));
                        return;
                    }*/
    /*                pageNum = getPageNum();
                    pageNum.postValue(liveEntity.getPageNo());*/
                    liveData = getLiveData();
                    List<LiveEntity.AnchorMemberRoomListBean> AnchorRoomList = liveData.getValue();
                    if (AnchorRoomList==null){
                        AnchorRoomList = new ArrayList<>();
                    }
                    /**
                     * 对请求返回的数据先筛选掉第一条的数据
                     * 然后把addll后的数据根据主播account 去重
                     */
                    int position=-1;//copyAnchorRoomBean不为空时,copyAnchorRoomBean在当前list的下标(用于添加position之后的数据)
                    boolean isContainAnchorRoomBean=false;

                    List<LiveEntity.AnchorMemberRoomListBean> reqList = liveEntity.getAnchorMemberRoomList();
                    /**
                     * 如果当前不是第一页且reqList为空,需要重置pageNo,重新请求第一页的数据(达到循环上拉的效果)
                     */
                    if(pageNo!=1&&reqList.size()==0){
                        pageNum.postValue(1);
                        reqLiveData(1,pageSize,categoryId,area);
                    }

                    for (int i=0;i<reqList.size();i++){
//                        if (reqList.get(i).getAnchorAccount().equals(AnchorRoomList.get(0).getAnchorAccount())){
                        if (reqList.get(i).getAnchorAccount().equals(copyAnchorRoomBean.getAnchorAccount())){
                            reqList.remove(reqList.get(i));
                            position = i;
                            isContainAnchorRoomBean=true;
                            break;
                        }
                    }
                    //过滤掉筛选不出cpId的房间
                    String  navigateList = SharePreferencesUtil.getString(MyApplication.getInstance(),"navigateList","");
                    JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//解析json
                    JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                    if(isContainAnchorRoomBean){
                        if(position == reqList.size()){
                            //筛选到了传入的bean且为当前请求结果的最后一条数据,需要pageNo++ 请求下一页数据
                            pageNum.postValue(liveEntity.getPageNo()+1);
                            reqLiveData(liveEntity.getPageNo()+1,pageSize,categoryId,area);
                        }else {
                            addLiveData(AnchorRoomList, position, reqList, lotteryArray);
                        }
                    }else {
                        addLiveData(AnchorRoomList, 0, reqList, lotteryArray);
                    }

                    liveData.postValue(AnchorRoomList);
                }
            }
            @Override
            public void Faild(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }
    public void reqLiveData(int pageNo, int pageSize,String categoryId ,String area) {
        mRepository.getDataList(pageNo, pageSize, categoryId,area,new CallBack() {
            @Override
            public void Success(Object object) {
                if(object instanceof  LiveEntity){
                    LiveEntity liveEntity = (LiveEntity) object;
       /*             if (liveEntity==null||liveEntity.getAnchorMemberRoomList()==null||liveEntity.getAnchorMemberRoomList().size()==0){
                        ToastUtils.showToast(Utils.getString(R.string.我是有底线的~));
                        return;
                    }*/
                    pageNum = getPageNum();
                    pageNum.postValue(liveEntity.getPageNo());
                    liveData = getLiveData();
                    List<LiveEntity.AnchorMemberRoomListBean> AnchorRoomList = liveData.getValue();
                    if (AnchorRoomList==null){
                        AnchorRoomList = new ArrayList<>();
                    }
                    List<LiveEntity.AnchorMemberRoomListBean> reqList = liveEntity.getAnchorMemberRoomList();
                    /**
                     * 如果当前不是第一页且reqList为空,需要重置pageNo,重新请求第一页的数据(达到循环上拉的效果)
                     */
                    if(pageNo!=1&&reqList.size()==0){
                        pageNum.postValue(1);
                        reqLiveData(1,pageSize,categoryId,area);
                    }
                    //过滤掉筛选不出cpId的房间
                    String  navigateList = SharePreferencesUtil.getString(MyApplication.getInstance(),"navigateList","");
                    JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//解析json
                    JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");

                    addLiveData(AnchorRoomList, 0, reqList, lotteryArray);
//                    AnchorRoomList.addAll(reqList);
                    /**
                     * 去重 by account
                     */
//                    AnchorRoomList = CPDataParseUtils.removeDuplicateAnchor(AnchorRoomList);
                    liveData.postValue(AnchorRoomList);
                }
            }
            @Override
            public void Faild(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }
    private void addLiveData(List<LiveEntity.AnchorMemberRoomListBean> anchorRoomList, int position, List<LiveEntity.AnchorMemberRoomListBean> reqList, JSONArray lotteryArray) {
        for (int i = position; i < reqList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = reqList.get(i);
            long cpId = anchorMemberRoomListBean.getCpId();
            for (int j = 0; j < lotteryArray.size(); j++) {
                JSONObject objectLottery = lotteryArray.getJSONObject(j);
                long lotteryId = objectLottery.getLong("id");
                if (lotteryId == cpId) {
                    String typename = objectLottery.getString("typename");
                    anchorMemberRoomListBean.setLotteryName(typename);
                    String game = objectLottery.getString("game");
                    anchorMemberRoomListBean.setGame(game);
                    //不能去重,否则达不到循环滑动的效果
//                    if (!anchorRoomList.contains(anchorMemberRoomListBean)) {
                        anchorRoomList.add(anchorMemberRoomListBean);
//                    }
                    break;
                }
            }
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
