package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TwoChangLongMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoChangLongAdapter extends CommonAdapter<TwoChangLongAdapter.MyHolder, TwoChangLongMedol> {
    public TwoChangLongAdapter() {
    }

    public TwoChangLongAdapter(ArrayList<TwoChangLongMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        TwoChangLongMedol itemModel = getItemModel(position);
        String type = itemModel.getType();
        char indexOne = type.charAt(1);
        int game = itemModel.getGame();
        Map<String, String> data = new HashMap<>();
        if(game==8){//广东快乐10分
            data.put("1", Utils.getString(R.string.第一球)+"-");
            data.put("2",Utils.getString(R.string.第二球)+"-");
            data.put("3",Utils.getString(R.string.第三球)+"-");
            data.put("4",Utils.getString(R.string.第四球)+"-");
            data.put("5",Utils.getString(R.string.第五球)+"-");
            data.put("6",Utils.getString(R.string.第六球)+"-");
            data.put("7",Utils.getString(R.string.第一球)+"-");
            data.put("8",Utils.getString(R.string.第八球)+"-");
        }else if(game==3){//赛车
            data.put("1",Utils.getString(R.string.冠军)+"-");
            data.put("2",Utils.getString(R.string.亚军)+"-");
            data.put("3",Utils.getString(R.string.第三名)+"-");
            data.put("4",Utils.getString(R.string.第四名)+"-");
            data.put("5",Utils.getString(R.string.第五名)+"-");
            data.put("6",Utils.getString(R.string.第六名)+"-");
            data.put("7",Utils.getString(R.string.第七名)+"-");
            data.put("8",Utils.getString(R.string.第八名)+"-");
            data.put("9",Utils.getString(R.string.第九名)+"-");
            data.put("10",Utils.getString(R.string.第十名)+"-");
        }else if(game==2){//时时彩
            data.put("1",Utils.getString(R.string.第一球)+"-");
            data.put("2",Utils.getString(R.string.第二球)+"-");
            data.put("3",Utils.getString(R.string.第三球)+"-");
            data.put("4",Utils.getString(R.string.第四球)+"-");
            data.put("5",Utils.getString(R.string.第五球)+"-");
        }else{//11选5
            data.put("1",Utils.getString(R.string.第一球)+"-");
            data.put("2",Utils.getString(R.string.第二球)+"-");
            data.put("3",Utils.getString(R.string.第三球)+"-");
            data.put("4",Utils.getString(R.string.第四球)+"-");
            data.put("5",Utils.getString(R.string.第五球)+"-");;
        }

        if(type.contains("10")){
            type=data.get("10")+indexOne;
        }
        else if(type.contains("1")){
            type=data.get("1")+indexOne;
        }
        else if(type.contains("2")){
            type=data.get("2")+indexOne;
        }else if(type.contains("3")){
            type=data.get("3")+indexOne;
        }
        else if(type.contains("4")){
            type=data.get("4")+indexOne;
        }
        else if(type.contains("5")){
            type=data.get("5")+indexOne;
        }
        else if(type.contains("6")){
            type=data.get("6")+indexOne;
        }
        else if(type.contains("7")){
            type=data.get("7")+indexOne;
        }
        else if(type.contains("8")){
            type=data.get("8")+indexOne;
        }
        else if(type.contains("9")){
            type=data.get("9")+indexOne;
        }
        commonHolder.playTypeTv.setText(type);
        commonHolder.countTv.setText(itemModel.getCount());
    }

    @Override
    public int getLayOutRes() {
        return R.layout.two_changlong_recyle_item;
    }

    public static class MyHolder extends CommonHolder {

        TextView playTypeTv;
        TextView countTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            playTypeTv=itemView.findViewById(R.id.changlong_type);
            countTv=itemView.findViewById(R.id.changlong_count);
        }
    }
}
