package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SScStrategy implements GameRuleStrategy {

    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        SscEntity sscEntity = gson.fromJson(str, SscEntity.class);
        List<SscEntity.RulelistAllBean> rulelistAll = sscEntity.getRulelistAll();

        /**
         *  总和-龙虎和  第一球两面   前三
         */
        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        for (int i = 0; i < 4; i++) {
            SscEntity.RulelistAllBean.RulelistBean bean = rulelistAll.get(2).getRulelist().get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.总和龙虎和));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }
        for (int i = 0; i < rulelistAll.get(2).getRulelist().size(); i++) {
            if (rulelistAll.get(2).getRulelist().get(i).getGroupname().equals(Utils.getString(R.string.第一球))) {
                SscEntity.RulelistAllBean.RulelistBean bean = rulelistAll.get(2).getRulelist().get(i);
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(2);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.第一球两面));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
        }
        for (int i = 0; i < rulelistAll.get(0).getRulelist().size(); i++) {
            if (rulelistAll.get(0).getRulelist().get(i).getGroupname().equals(Utils.getString(R.string.前三))) {
                SscEntity.RulelistAllBean.RulelistBean bean = rulelistAll.get(0).getRulelist().get(i);
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(3);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.前三));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
        }

        BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel1.setId(1);
        betPopTabModel1.setName(Utils.getString(R.string.总和龙虎和));
        betPopTabModel1.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel2.setId(2);
        betPopTabModel2.setName(Utils.getString(R.string.第一球两面));
        betPopTabModel2.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.前三));
        betPopTabModel3.setSelect(false);


        betPopTabModelList.add(betPopTabModel2);
        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel3);

        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);

        return betPopAllModel;
    }
}
