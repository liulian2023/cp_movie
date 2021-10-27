package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;

/**
 * 场景类
 */
public class GameRuleContext {

        private GameRuleStrategy strategy;

        public BetPopAllModel parseBetPopAllData(String str, Integer gameType){
            strategy = GameRuleStrateFactory.getInstance().creator(gameType);
            return strategy.parseBetPopAllData(str, GameTypeEnum.valueOf(gameType));
        }

        public GameRuleStrategy getStrategy(){
            return strategy;
        }

        public void setStrategy(GameRuleStrategy strategy){
            this.strategy = strategy;
        }

}
