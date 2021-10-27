
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil.factorial;

public class BetNumUtil {
    //大小单双 一星复式 算法(选中一个size加1)
    public static int oneToOne(ArrayList<SscOfiicialBetMedol> officialSelectorList){
        return officialSelectorList.size();
    }

    // 直选复式 算法(每个位置至少选择一位)
    public static int zhiXuanFuShi(ArrayList<SscOfiicialBetMedol> officialSelectorList,String typeOneName){
        int wanwei = 0,qianwei = 0,baiwei = 0,shiwei = 0,gewei = 0;
        for (int j = 0; j < officialSelectorList.size(); j++) {
            String name = officialSelectorList.get(j).getName();
            if(name.equals(Utils.getString(R.string.万位))){
                wanwei++;
            }else if(name.equals(Utils.getString(R.string.千位))){
                qianwei++;
            }else if(name.equals(Utils.getString(R.string.百位))){
                baiwei++;
            }else if(name.equals(Utils.getString(R.string.十位))){
                shiwei++;
            }else {
                gewei++;
            }
        }
       int resultCount=0;
        //||typeOneName.equals(Utils.getString(R.string.后二))||typeOneName.equals(Utils.getString(R.string.前三))||typeOneName.equals(Utils.getString(R.string.中三))||typeOneName.equals(Utils.getString(R.string.后三))
        if(typeOneName.equals(Utils.getString(R.string.前二))){
                resultCount=wanwei*qianwei;
        }else if(typeOneName.equals(Utils.getString(R.string.后二))){
                resultCount=shiwei*gewei;
        }else if(typeOneName.equals(Utils.getString(R.string.前三))){
            resultCount=wanwei*qianwei*baiwei;
        }else if(typeOneName.equals(Utils.getString(R.string.中三))){
            resultCount=qianwei*baiwei*shiwei;
        }else if(typeOneName.equals(Utils.getString(R.string.后三))){
            resultCount=baiwei*shiwei*gewei;
        } else if(typeOneName.equals(Utils.getString(R.string.四星))){
                resultCount=qianwei*baiwei*shiwei*gewei;
        }else if(typeOneName.equals(Utils.getString(R.string.五星))){
            resultCount=wanwei*qianwei*baiwei*shiwei*gewei;
        }
        return resultCount;
    }

    /**
     * 直选和值 算法(竞彩开奖号码前n位数字之和)
     * @param officialSelectorList 选中容器
     * @param containDuizi 是否包含对子
     * @param ballCount 球数  例:前二  ballCount=2  前三 ballCount=3
     * @return 总注数
     */
    public static int zhiXuanHeZhi(ArrayList<SscOfiicialBetMedol> officialSelectorList,boolean containDuizi,int ballCount){
        int diffCount = 0;
        int duiZiCount = 0;
        for (int a = 0; a < officialSelectorList.size(); a++) {
            String codes = officialSelectorList.get(a).getCodes();
            int sum = Integer.parseInt(codes);
            if(ballCount==2){
                for (int i = 0; i <= 9; i++) {
                    if (i > sum) {
                        break;
                    }
                    for (int j = 0; j<= 9 ; j++) {
                        if (j > sum) {
                            break;
                        }
                        int countSum = i;
                        countSum+=j;
                        if (countSum == sum) {
                            if (i == j) {
                                duiZiCount ++;
                            }else{
                                diffCount ++;
                            }
                        }
                    }
                }
            }else if(ballCount==3){
                for (int i = 0; i <= 9; i++) {
                    if (i > sum) {
                        break;
                    }
                    for (int j = 0; j<= 9 ; j++) {
                        if (j > sum) {
                            break;
                        }
                        for (int k = 0; k<= 9 ; k++) {
                            if (k > sum) {
                                break;
                            }
                            int countSum = i + j;
                            countSum+=k;
                            if (countSum == sum) {
                                if (i == j || i == k || j == k) {
                                    duiZiCount ++;
                                }else{
                                    diffCount ++;
                                }
                            }
                        }
                    }
                }

            }
        }

        int resultCount  = 0;
        if (containDuizi) {
            resultCount += duiZiCount;
        }
        resultCount+= diffCount;
        return resultCount;
    }
    /**
     * 组合算法
     * @param size 选中容器的size
     * @param ballCount 球数 (几个数字组成一注)
     * @return 总注数
     */
    public  static long combineWithArraySize(int size, int ballCount){
        return (size >= ballCount) ? factorial(size) / factorial(size - ballCount) / factorial(ballCount) : 0;
    }
    /**
     *组选和值
     * @param officialSelectorList  选中集合
     * @param ballCount 球数
     * @return 注数
     */
//    @RequiresApi(api = Build.VERSION_CODES.N)
    public  static long zhuXuanHeZhi(ArrayList<SscOfiicialBetMedol> officialSelectorList, int ballCount){
        HashSet<ArrayList<Integer>> allBetSet = new HashSet<>();
        for (SscOfiicialBetMedol sscOfiicialBetMedol:officialSelectorList) {
            String s = sscOfiicialBetMedol.getCodes();
            int sum = Integer.parseInt(s);
            if (ballCount == 2) {
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j<= 9 ; j++) {
                        int countSum = i;
                        countSum+=j;
                        if (countSum == sum) {
                            if (i == j) {

                            }else{
                                ArrayList<Integer> oneBetArray = new ArrayList<>();
                                oneBetArray.add(i);
                                oneBetArray.add(j);
                                Collections.sort(oneBetArray);
/*                                oneBetArray.sort(new Comparator<Integer>() {//排序
                                    @Override
                                    public int compare(Integer o1, Integer o2) {
                                        int i = o1 > o2 ? 1 : -1;
                                        return i;
                                    }
                                });*/
                                allBetSet.add(oneBetArray);
                            }
                        }
                    }
                }
            }else  if (ballCount == 3) {

                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j<= 9 ; j++) {
                        for (int k = 0; k<= 9 ; k++) {

                            int countSum = i + j;
                            countSum+=k;
                            if (countSum == sum) {
                                if (i == j && j == k) {

                                }else{
                                    ArrayList<Integer> oneBetArray = new ArrayList<>();
                                    oneBetArray.add(i);
                                    oneBetArray.add(j);
                                    oneBetArray.add(k);
                                    Collections.sort(oneBetArray);
/*                                    oneBetArray.sort(new Comparator<Integer>() {
                                        @Override
                                        public int compare(Integer o1, Integer o2) {
                                            int i = o1 > o2 ? 1 : -1;
                                            return i;
                                        }
                                    });*/
                                    allBetSet.add(oneBetArray);
                                }
                            }
                        }
                    }
                }
            }

        }


        return  allBetSet.size();
    }

    /**
     * 判断组选玩法是否全都选中
     * @param officialSelectorList
     * @param
     * @return
     */
    public  static   HashMap<String, ArrayList<SscOfiicialBetMedol>> seletedMap(ArrayList<SscOfiicialBetMedol> officialSelectorList){
        HashMap<String, ArrayList<SscOfiicialBetMedol>> medolMap = new HashMap<>();
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = officialSelectorList.get(i);
            String name = sscOfiicialBetMedol.getName();
            if(!medolMap.containsKey(name)){
                ArrayList<SscOfiicialBetMedol> list = new ArrayList<>();
                list.add(sscOfiicialBetMedol);
                medolMap.put(name,list) ;
            }else {
                ArrayList<SscOfiicialBetMedol> list = medolMap.get(name);
                list.add(sscOfiicialBetMedol);
            }

        }
        return medolMap;
    }

    /**
     * 组选 12  4  60  20
     * @param officialSelectorList 选中容器
     * @param sersionTwoBallCount  单号位几个一注
     * @param sersionCount   itemCount
     * @return
     */
    public  static int combinZuXuan12_4_60_20WithSectionArray(ArrayList<SscOfiicialBetMedol> officialSelectorList,int sersionTwoBallCount,int sersionCount ){
        ArrayList<SscOfiicialBetMedol> listOne=null;
        ArrayList<SscOfiicialBetMedol> listTwo=null;
        HashMap<String, ArrayList<SscOfiicialBetMedol>> selectedMap = seletedMap(officialSelectorList);
        if(sersionCount!=selectedMap.size()){
            return 0;
        }
            int resultCount=0;
                listOne = selectedMap.get(Utils.getString(R.string.二重号位));//n重号位
            if(listOne==null){
                listOne = selectedMap.get(Utils.getString(R.string.三重号位));//n重号位
            }
            listTwo = selectedMap.get(Utils.getString(R.string.单号位));

          long combineWithArraySize=0;
        for (SscOfiicialBetMedol medolOne:listOne) {
            int size =listTwo.size();
            if(isContainsMedolOne(listTwo,medolOne)){//去重复
                size--;
            }
            combineWithArraySize = combineWithArraySize(size, sersionTwoBallCount);
            resultCount+=combineWithArraySize;
        }
        return resultCount;
    }


    /**
     * 五星组选 30
     * @param officialSelectorList 选中容器
     * @param sersionTwoBallCount  二重号位几个一注
     * @param sersionCount   itemCount
     * @return
     */
    public  static int combinZuXuan30WithSectionArray(ArrayList<SscOfiicialBetMedol> officialSelectorList,int sersionTwoBallCount,int sersionCount ){
        ArrayList<SscOfiicialBetMedol> listOne=null;
        ArrayList<SscOfiicialBetMedol> listTwo=null;
        HashMap<String, ArrayList<SscOfiicialBetMedol>> selectedMap = seletedMap(officialSelectorList);
        if(sersionCount!=selectedMap.size()){
            return 0;
        }
        int resultCount=0;
        listOne = selectedMap.get(Utils.getString(R.string.单号位));//单位号 至少选择1个
        listTwo = selectedMap.get(Utils.getString(R.string.二重号位));//二重号位 至少选择2个

        long combineWithArraySize=0;
        for (SscOfiicialBetMedol medolOne:listOne) {
            int size =listTwo.size();
            if(isContainsMedolOne(listTwo,medolOne)){//去重复
                size--;
            }
            combineWithArraySize = combineWithArraySize(size, sersionTwoBallCount);
            resultCount+=combineWithArraySize;
        }
        return resultCount;
    }

    /**
     * 五星组选 10 5
     * @param officialSelectorList 选中容器
     * @param sersionTwoBallCount  n重号位几个一注
     * @param sersionCount   itemCount
     * @return
     */
    public  static int combinZuXuan10_5WithSectionArray(ArrayList<SscOfiicialBetMedol> officialSelectorList,String typeThreeName,int sersionTwoBallCount,int sersionCount ){
        ArrayList<SscOfiicialBetMedol> listOne=null;
        ArrayList<SscOfiicialBetMedol> listTwo=null;
        HashMap<String, ArrayList<SscOfiicialBetMedol>> selectedMap = seletedMap(officialSelectorList);
        if(sersionCount!=selectedMap.size()){
            return 0;
        }
        int resultCount=0;
        if(typeThreeName.equals(Utils.getString(R.string.组选10))){
            listOne = selectedMap.get(Utils.getString(R.string.二重号位));
            listTwo = selectedMap.get(Utils.getString(R.string.三重号位));
        }else if(typeThreeName.equals(Utils.getString(R.string.组选5))){
            listOne = selectedMap.get(Utils.getString(R.string.单号位));
            listTwo = selectedMap.get(Utils.getString(R.string.四重号位));
        }


        long combineWithArraySize=0;
        for (SscOfiicialBetMedol medolOne:listOne) {
            int size =listTwo.size();
            if(isContainsMedolOne(listTwo,medolOne)){//去重复
                size--;
            }
            combineWithArraySize = combineWithArraySize(size, sersionTwoBallCount);
            resultCount+=combineWithArraySize;
        }
        return resultCount;
    }

    /*
     去重复
     */
    public static boolean isContainsMedolOne( ArrayList<SscOfiicialBetMedol> listTwo,SscOfiicialBetMedol medolOne){
        for (SscOfiicialBetMedol medol:listTwo) {
            if(medol.getCodes().equals(medolOne.getCodes())){
                return true;
            }
        }
        return false;
    }
}
