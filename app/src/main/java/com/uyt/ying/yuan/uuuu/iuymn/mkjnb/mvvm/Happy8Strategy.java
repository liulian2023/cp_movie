package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8Entity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Happy8Strategy implements GameRuleStrategy {

    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {
        /**
         * 总和  前后和  五行
          */
        Gson gson = new GsonBuilder().serializeNulls().create();
        Happy8Entity happy8Entity = gson.fromJson(str,Happy8Entity.class);
        List<Happy8Entity.RulelistAllBean.RulelistBean>  rulelist = happy8Entity.getRulelistAll().get(0).getRulelist();

        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        for (int i=0;i<4;i++){
            Happy8Entity.RulelistAllBean.RulelistBean bean = rulelist.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.总和));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }
        for (int i=0;i<rulelist.size();i++){
            Happy8Entity.RulelistAllBean.RulelistBean bean = rulelist.get(i);
            if (bean.getGroupname().equals(Utils.getString(R.string.前后和))){
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(2);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.前后和));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
        }
        for (int i=0;i<rulelist.size();i++){
            Happy8Entity.RulelistAllBean.RulelistBean bean = rulelist.get(i);
            if (bean.getGroupname().equals(Utils.getString(R.string.五行))){
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(3);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.五行));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
        }

        BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel1.setId(1);
        betPopTabModel1.setName(Utils.getString(R.string.总和));
        betPopTabModel1.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel2.setId(2);
        betPopTabModel2.setName(Utils.getString(R.string.前后和));
        betPopTabModel2.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.五行));
        betPopTabModel3.setSelect(false);

        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel2);
        betPopTabModelList.add(betPopTabModel3);

        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);

        return betPopAllModel;

    }
}
