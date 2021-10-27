package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RaceStrategy implements GameRuleStrategy {

    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        RaceEntity raceEntity = gson.fromJson(str, RaceEntity.class);
        List<RaceEntity.RulelistAllBean> rulelistAll = raceEntity.getRulelistAll();
        /**
         * 冠军单码  冠亚和  冠军两面
         */
        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        List<RaceEntity.RaceChildBean> childList1 = gson.fromJson(rulelistAll.get(0).getRulelist(), new TypeToken<List<RaceEntity.RaceChildBean>>() {
        }.getType());
        for (int i = 0; i < 10; i++) {
            RaceEntity.RaceChildBean bean = childList1.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.冠军单码));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        List<RaceEntity.RaceChildBean> childList2 = gson.fromJson(rulelistAll.get(3).getRulelist(), new TypeToken<List<RaceEntity.RaceChildBean>>() {
        }.getType());
        for (int i = childList2.size() - 4; i < childList2.size(); i++) {
            RaceEntity.RaceChildBean bean = childList2.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(2);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.冠亚和));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }
        List<RaceEntity.RaceChildBean> childList3 = gson.fromJson(rulelistAll.get(4).getRulelist(), new TypeToken<List<RaceEntity.RaceChildBean>>() {
        }.getType());
        for (int i = 0; i < 4; i++) {
            RaceEntity.RaceChildBean bean = childList3.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(3);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.冠军两面));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel1.setId(1);
        betPopTabModel1.setName(Utils.getString(R.string.冠军单码));
        betPopTabModel1.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel2.setId(2);
        betPopTabModel2.setName(Utils.getString(R.string.冠亚和));
        betPopTabModel2.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.冠军两面));
        betPopTabModel3.setSelect(false);

        betPopTabModelList.add(betPopTabModel3);
        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel2);


        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);


        return betPopAllModel;
    }
}
