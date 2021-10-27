package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeTopGameEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HotTopEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class HotTopRecyclerAdapter extends BaseQuickAdapter<HotTopEntity.ExtensionNoticeInfoListBean, BaseViewHolder> {
    public HotTopRecyclerAdapter(int layoutResId, @Nullable List<HotTopEntity.ExtensionNoticeInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HotTopEntity.ExtensionNoticeInfoListBean extensionNoticeInfoListBean) {
        ImageView home_top_iv = baseViewHolder.getView(R.id.home_top_iv);
        TextView home_top_tv = baseViewHolder.getView(R.id.home_top_tv);
        int liveShowPage = extensionNoticeInfoListBean.getLiveShowPage();
        String image = extensionNoticeInfoListBean.getImage();
        if(liveShowPage == 1){
            /**
             * liveShowPage == 1 为棋牌/彩票游戏,  分类名取content中的 typename
             */
            String content = extensionNoticeInfoListBean.getContent();
            if(StringMyUtil.isNotEmpty(content)){
                HomeTopGameEntity homeTopGameEntity = JSONObject.parseObject(content, HomeTopGameEntity.class);
                // image 为手动上传的图片, 优先显示 , 为空显示content中默认的图片
                if(StringMyUtil.isNotEmpty(image)){
                    GlideLoadViewUtil.LoadNormalView(getContext(),image,home_top_iv);
                }else {
                    GlideLoadViewUtil.LoadNormalView(getContext(),homeTopGameEntity.getImage(),home_top_iv);
                }
                home_top_tv.setText(homeTopGameEntity.getTypename());
            }
        }else {
            /**
             * liveShowPage不为1 , 图片取image 分类名去theme
             */
            GlideLoadViewUtil.LoadNormalView(getContext(),image,home_top_iv);
            home_top_tv.setText(extensionNoticeInfoListBean.getTheme());
        }

    }
}
