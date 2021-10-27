package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanWuEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XuanwuStrategy implements GameRuleStrategy {


    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {
        /**
         * 总和大小 龙虎  第一球两面
         */
        Gson gson = new GsonBuilder().serializeNulls().create();
        XuanWuEntity xuanWuEntity = gson.fromJson(str,XuanWuEntity.class);
        List<XuanWuEntity.RulelistAllBean> rulelistAll = xuanWuEntity.getRulelistAll();
        XuanWuEntity.RulelistAllBean rulelistAllBean = rulelistAll.get(8) ;
        List<XuanWuEntity.ChildBean> childList = gson.fromJson(rulelistAllBean.getRulelist(),
                new TypeToken<List<XuanWuEntity.ChildBean>>() {}.getType());

        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        for (int i=0;i<4;i++){
            XuanWuEntity.ChildBean bean = childList.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.总和大小));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        for (int i=6;i<8;i++){
            XuanWuEntity.ChildBean bean = childList.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(2);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.龙虎));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        int count =0;
        for (int i =0;i<childList.size();i++){
            if (childList.get(i).getBalled()==1){
                XuanWuEntity.ChildBean bean = childList.get(i);
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(3);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.第一球两面));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
                count++;
                if (count>=4){
                    break;
                }
            }
        }
        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.第一球两面));
        betPopTabModel3.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel1.setId(1);
        betPopTabModel1.setName(Utils.getString(R.string.总和大小));
        betPopTabModel1.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel2.setId(2);
        betPopTabModel2.setName(Utils.getString(R.string.龙虎));
        betPopTabModel2.setSelect(false);


        betPopTabModelList.add(betPopTabModel3);
        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel2);


        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);

        return betPopAllModel;





    }
}
