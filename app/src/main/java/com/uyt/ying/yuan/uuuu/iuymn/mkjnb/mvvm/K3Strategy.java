package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KuaiSanEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class K3Strategy implements GameRuleStrategy {
    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        KuaiSanEntity kuaiSanEntity = gson.fromJson(str, KuaiSanEntity.class);
        List<KuaiSanEntity.GameRulelistBean> gameRulelist = kuaiSanEntity.getGameRulelist();
        /**
         * 分3组 总和 三军 短牌 0-3  4-9 Groupname为短牌
         */
        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();
        //取总和和三军数据

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        for (int i = 0; i < 4; i++) {
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            KuaiSanEntity.GameRulelistBean gameRulelistBean = gameRulelist.get(i);
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(gameRulelistBean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.总和));
            betPopChildModel.setName(gameRulelistBean.getName());
            betPopChildModel.setOdds(gameRulelistBean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }
        for (int i = 4; i < 10; i++) {
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            KuaiSanEntity.GameRulelistBean gameRulelistBean = gameRulelist.get(i);
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(2);
            betPopChildModel.setRuleId(gameRulelistBean.getId());
            betPopChildModel.setGroupname(MyApplication.getInstance().getString(R.string.三军));
            betPopChildModel.setName(gameRulelistBean.getName());
            betPopChildModel.setOdds(gameRulelistBean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
        }
        //取短牌数据
        for (int i = 0; i < gameRulelist.size(); i++) {
            KuaiSanEntity.GameRulelistBean gameRulelistBean = gameRulelist.get(i);
            if (gameRulelistBean.getGroupname().equals(Utils.getString(R.string.短牌))) {
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(3);
                betPopChildModel.setRuleId(gameRulelistBean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.短牌));
                betPopChildModel.setName(gameRulelistBean.getName());
                betPopChildModel.setOdds(gameRulelistBean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
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
        betPopTabModel2.setName(MyApplication.getInstance().getString(R.string.三军));
        betPopTabModel2.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.短牌));
        betPopTabModel3.setSelect(false);


        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel2);
        betPopTabModelList.add(betPopTabModel3);

        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);

        return betPopAllModel;
    }
}
