package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SixEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MarksixStrategy implements GameRuleStrategy {
    @Override
    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum) {

        /**
         * 特码两面  特码生肖  特码波色
         */
        Gson gson = new GsonBuilder().serializeNulls().create();
        SixEntity sixEntity = gson.fromJson(str,SixEntity.class);
        List<SixEntity.MarksixRulelistBean> marksixRulelist = sixEntity.getMarksixRulelist();
        BetPopAllModel betPopAllModel = new BetPopAllModel();
        List<BetPopAllModel.BetPopTabModel> betPopTabModelList = new ArrayList<>();
        List<BetPopAllModel.BetPopChildModel> betPopChildModelList = new ArrayList<>();
        if (marksixRulelist!=null){
            int index = 0;
            for (int i=0;i<4;i++){
                SixEntity.MarksixRulelistBean bean = marksixRulelist.get(i);
                BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                betPopChildModel.setId(index);
                betPopChildModel.setParentId(1);
                betPopChildModel.setRuleId(bean.getId());
                betPopChildModel.setGroupname(Utils.getString(R.string.特码两面));
                betPopChildModel.setName(bean.getName());
                betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                betPopChildModel.setSelect(false);
                betPopChildModelList.add(betPopChildModel);
                index++;
            }
            for (int i=0;i<marksixRulelist.size();i++){
                if (marksixRulelist.get(i).getGroup_id()==2){
                    SixEntity.MarksixRulelistBean bean = marksixRulelist.get(i);
                    BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                    betPopChildModel.setId(index);
                    betPopChildModel.setParentId(2);
                    betPopChildModel.setRuleId(bean.getId());
                    betPopChildModel.setGroupname(Utils.getString(R.string.特码生肖));
                    betPopChildModel.setName(bean.getName());
                    betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                    betPopChildModel.setSelect(false);
                    betPopChildModelList.add(betPopChildModel);
                    index++;
                }
            }
            int j = 0;  //取group_id==4 的前3个
            for (int i=0;i<marksixRulelist.size();i++){
                if (marksixRulelist.get(i).getGroup_id()==4){
                    SixEntity.MarksixRulelistBean bean = marksixRulelist.get(i);
                    BetPopAllModel.BetPopChildModel betPopChildModel = new BetPopAllModel.BetPopChildModel();
                    betPopChildModel.setId(index);
                    betPopChildModel.setParentId(3);
                    betPopChildModel.setRuleId(bean.getId());
                    betPopChildModel.setGroupname(Utils.getString(R.string.特码波色));
                    betPopChildModel.setName(bean.getName());
                    betPopChildModel.setOdds(bean.getOdds().setScale(3,BigDecimal.ROUND_HALF_UP));
                    betPopChildModel.setSelect(false);
                    betPopChildModelList.add(betPopChildModel);
                    index++;
                    j++;
                }
                //跳出循环
                if (j>=3){
                    break;
                }
            }

            BetPopAllModel.BetPopTabModel betPopTabModel1 = new BetPopAllModel.BetPopTabModel();
            betPopTabModel1.setId(1);
            betPopTabModel1.setName(Utils.getString(R.string.特码两面));
            betPopTabModel1.setSelect(false);

            BetPopAllModel.BetPopTabModel betPopTabModel2 = new BetPopAllModel.BetPopTabModel();
            betPopTabModel2.setId(2);
            betPopTabModel2.setName(Utils.getString(R.string.特码生肖));
            betPopTabModel2.setSelect(false);

            BetPopAllModel.BetPopTabModel betPopTabModel3 = new BetPopAllModel.BetPopTabModel();
            betPopTabModel3.setId(3);
            betPopTabModel3.setName(Utils.getString(R.string.特码波色));
            betPopTabModel3.setSelect(false);

            betPopTabModelList.add(betPopTabModel1);
            betPopTabModelList.add(betPopTabModel2);
            betPopTabModelList.add(betPopTabModel3);

            betPopAllModel.setBetPopTabModelList(betPopTabModelList);
            betPopAllModel.setBetPopChildModelList(betPopChildModelList);


        }
        /**
         * id 标志位置 通过indx 自+
         */
        return betPopAllModel;

    }
}
