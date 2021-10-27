package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类
 */
public class GameRuleStrateFactory {

    private static GameRuleStrateFactory gameRuleStrateFactory = new GameRuleStrateFactory();

    private static Map<Integer,GameRuleStrategy> strategyMap = new HashMap<>();


    static {
        strategyMap.put(GameTypeEnum.KUAISAN.getValue(),new K3Strategy());
        strategyMap.put(GameTypeEnum.SSC.getValue(),new SScStrategy());
        strategyMap.put(GameTypeEnum.RACE.getValue(),new RaceStrategy());
        strategyMap.put(GameTypeEnum.MARKSIX.getValue(),new MarksixStrategy());
        strategyMap.put(GameTypeEnum.DANDAN.getValue(),new DanStrategy());
        strategyMap.put(GameTypeEnum.HAPPY8.getValue(),new Happy8Strategy());
        strategyMap.put(GameTypeEnum.LUCKFARM.getValue(),new LuckFarmStrategy());
        strategyMap.put(GameTypeEnum.HAPPY10.getValue(),new Happy10Strategy());
        strategyMap.put(GameTypeEnum.XUANWU.getValue(),new XuanwuStrategy());
    }
    public GameRuleStrategy creator(Integer gameType){
        return strategyMap.get(gameType);
    }

    public static GameRuleStrateFactory getInstance(){
        return gameRuleStrateFactory;
    }

}
