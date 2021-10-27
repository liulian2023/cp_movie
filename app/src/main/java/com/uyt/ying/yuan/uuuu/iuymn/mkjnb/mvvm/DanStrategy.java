package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DanDanEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DanStrategy implements GameRuleStrategy {

    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {

        /**
         * 大小单双 混合  波色
         */
        Gson gson = new GsonBuilder().serializeNulls().create();
        DanDanEntity danDanEntity = gson.fromJson(str,DanDanEntity.class);
        List<DanDanEntity.DanRulelistBean> danRulelist = danDanEntity.getDanRulelist();

        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();

        /**
         * id 标志位置 通过indx 自+
         */
        int index = 0;
        for (int i=0;i<4;i++){
            DanDanEntity.DanRulelistBean bean = danRulelist.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(1);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.大小单双));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3, BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        for (int i=4;i<8;i++){
            DanDanEntity.DanRulelistBean bean = danRulelist.get(i);
            BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
            betPopChildModel.setId(index);
            betPopChildModel.setParentId(2);
            betPopChildModel.setRuleId(bean.getId());
            betPopChildModel.setGroupname(Utils.getString(R.string.混合));
            betPopChildModel.setName(bean.getName());
            betPopChildModel.setOdds(bean.getOdds().setScale(3, BigDecimal.ROUND_HALF_UP));
            betPopChildModel.setSelect(false);
            betPopChildModelList.add(betPopChildModel);
            index++;
        }

        for (int i=0;i<danRulelist.size();i++){
            DanDanEntity.DanRulelistBean bean = danRulelist.get(i);
            if (bean.getGroupname().equals(Utils.getString(R.string.波色))){
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(3);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.波色));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3, BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
        }

        BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel1.setId(1);
        betPopTabModel1.setName(Utils.getString(R.string.大小单双));
        betPopTabModel1.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel2.setId(2);
        betPopTabModel2.setName(Utils.getString(R.string.混合));
        betPopTabModel2.setSelect(false);

        BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
        betPopTabModel3.setId(3);
        betPopTabModel3.setName(Utils.getString(R.string.波色));
        betPopTabModel3.setSelect(false);


        betPopTabModelList.add(betPopTabModel1);
        betPopTabModelList.add(betPopTabModel2);
        betPopTabModelList.add(betPopTabModel3);

        betPopAllModel.setBetPopTabModelList(betPopTabModelList);
        betPopAllModel.setBetPopChildModelList(betPopChildModelList);

        return betPopAllModel;

    }
}
