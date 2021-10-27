package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;

/**
 * 策略抽象类 处理不同彩种数据分类 为betPop用
 */
public interface GameRuleStrategy {

    public BetPopAllModel parseBetPopAllData(String str, GameTypeEnum typeEnum);

}
