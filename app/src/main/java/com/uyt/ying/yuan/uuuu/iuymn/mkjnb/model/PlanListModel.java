/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class PlanListModel implements Serializable {


    /**
     * gameInfoList : [{"classIds":"63,14,53,12,43","createdDate":1533806782000,"createdUser":"admin","id":3,"logo":"upload/images/20190407/1554619994711.png","imageBlack":"upload/images/20190407/1554619986970.png","isDelete":0,"lastModifiedDate":1560504900000,"lastModifiedUser":"lottery","listsort":1,"name":Utils.getString(R.string.赛车),"status":1},{"classIds":"59,60,61,62,8,36,54,55,41,42","createdDate":1533806644000,"createdUser":"admin","id":2,"logo":"upload/images/20190407/1554620219679.png","imageBlack":"upload/images/20190407/1554620226577.png","isDelete":0,"lastModifiedDate":1560504910000,"lastModifiedUser":"lottery","listsort":2,"name":Utils.getString(R.string.时时彩),"status":1},{"classIds":"15,40","createdDate":1533806827000,"createdUser":"admin","id":5,"logo":"upload/images/20190407/1554620302197.png","imageBlack":"upload/images/20190407/1554620295787.png","isDelete":0,"lastModifiedDate":1554620303000,"lastModifiedUser":"test@qq.com","listsort":3,"name":Utils.getString(R.string.六合彩),"status":1},{"classIds":"10,32,47,45,46,48,49,51,52,33,34,35","createdDate":1533796000000,"createdUser":"admin","id":1,"logo":"upload/images/20190423/1556021419853.png","imageBlack":"upload/images/20190423/1556021403356.png","isDelete":0,"lastModifiedDate":1556021422000,"lastModifiedUser":"gua888","listsort":4,"name":Utils.getString(R.string.快三),"status":1},{"classIds":"57,56,17","createdDate":1554363738000,"createdUser":"mogu","id":8,"logo":"upload/images/20190407/1554620367897.png","imageBlack":"upload/images/20190407/1554620372733.png","isDelete":0,"lastModifiedDate":1554620374000,"lastModifiedUser":"test@qq.com","listsort":5,"name":Utils.getString(R.string.pc蛋蛋),"status":1},{"classIds":"22,39,37,38","createdDate":1552031970000,"createdUser":"admin","id":7,"logo":"upload/images/20190407/1554620394438.png","imageBlack":"upload/images/20190407/1554620388259.png","isDelete":0,"lastModifiedDate":1554620396000,"lastModifiedUser":"test@qq.com","listsort":6,"name":Utils.getString(R.string.11选5),"status":1},{"classIds":"20,21,19","createdDate":1533806908000,"createdUser":"admin","id":6,"logo":"upload/images/20190407/1554620426133.png","imageBlack":"upload/images/20190407/1554620431258.png","isDelete":0,"lastModifiedDate":1554620435000,"lastModifiedUser":"test@qq.com","listsort":7,"name":Utils.getString(R.string.快乐彩),"status":1}]
     * message : 获取数据成功
     * gameClassList : [{"changeVersion":false,"createdDate":1513330453000,"createdUser":"sys","currentLotteryQiShu":"2019077","currentLotteryTime":1562678990000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":15,"logo":"upload/images/20190417/1555503796163.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"42,11,28,08,10,07,29","lastLotteryQiShu":"2019076","lastModifiedDate":1562555300000,"lastModifiedUser":"sys","listsort":0,"openjgtime":2,"remark":Utils.getString(R.string.一周三期),"size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":Utils.getString(R.string.香港六合彩),"version":110,"waitLotteryQiShu":"2019077"},{"changeVersion":false,"createdDate":1559980198000,"createdUser":"sys","currentLotteryQiShu":"1907081150","currentLotteryTime":1562584195000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":59,"logo":"upload/images/20190608/1559982678251.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"3,1,5,2,3","lastLotteryQiShu":"1907081149","lastModifiedDate":1562584148000,"lastModifiedUser":"sys","listsort":1,"openjgtime":60,"remark":Utils.getString(R.string.一分一期),"size":0,"status":1,"tqlasttime":5,"type_id":12,"typename":Utils.getString(R.string.腾讯分分彩),"version":65,"waitLotteryQiShu":"1907081150"},{"changeVersion":false,"createdDate":1559980198000,"createdUser":"sys","currentLotteryQiShu":"1907081150","currentLotteryTime":1562584195000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":60,"logo":"upload/images/20190608/1559982693251.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"5,2,0,4,3","lastLotteryQiShu":"1907081149","lastModifiedDate":1562584148000,"lastModifiedUser":"sys","listsort":2,"openjgtime":60,"remark":Utils.getString(R.string.一分一期),"size":0,"status":1,"tqlasttime":5,"type_id":13,"typename":Utils.getString(R.string.幸运分分彩),"version":68,"waitLotteryQiShu":"1907081150"},{"changeVersion":false,"createdDate":1560503797000,"createdUser":"sys","currentLotteryQiShu":"190708230","currentLotteryTime":1562584160000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":61,"logo":"upload/images/20190614/1560513790589.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,7,0,1,9","lastLotteryQiShu":"190708229","lastModifiedDate":1562583915000,"lastModifiedUser":"sys","listsort":3,"openjgtime":300,"remark":"","size":0,"status":1,"tqlasttime":40,"type_id":50,"typename":Utils.getString(R.string.河内5分彩),"version":41,"waitLotteryQiShu":"190708230"},{"changeVersion":false,"createdDate":1560503797000,"createdUser":"sys","currentLotteryQiShu":"50594608","currentLotteryTime":1562584380000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":62,"logo":"upload/images/20190614/1560513799645.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,1,8,0,6","lastLotteryQiShu":"50594607","lastModifiedDate":1562584147000,"lastModifiedUser":"sys","listsort":4,"openjgtime":300,"remark":"","size":0,"status":1,"tqlasttime":0,"type_id":51,"typename":Utils.getString(R.string.澳洲幸运5),"version":36,"waitLotteryQiShu":"50594608"},{"changeVersion":false,"createdDate":1560504416000,"createdUser":"sys","currentLotteryQiShu":"20603508","currentLotteryTime":1562584320000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":63,"logo":"upload/images/20190614/1560513808078.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,10,6,3,8,2,5,1,9,7","lastLotteryQiShu":"20603507","lastModifiedDate":1562584147000,"lastModifiedUser":"sys","listsort":5,"openjgtime":300,"remark":"","size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":Utils.getString(R.string.澳洲幸运10),"version":34,"waitLotteryQiShu":"20603508"},{"changeVersion":false,"createdDate":1514451867000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562585340000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":22,"logo":"upload/images/20190417/1555503610761.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,11,9,6,8","lastLotteryQiShu":"190708029","lastModifiedDate":1562584142000,"lastModifiedUser":"sys","listsort":6,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.广东11选5),"version":105,"waitLotteryQiShu":"190708030"},{"changeVersion":false,"createdDate":1512700661000,"createdUser":"sys","currentLotteryQiShu":"190708046","currentLotteryTime":1562585280000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":8,"logo":"upload/images/20190417/1555503647046.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"8,0,4,3,3","lastLotteryQiShu":"190708044","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":7,"openjgtime":1200,"remark":Utils.getString(R.string.全天59期),"size":0,"status":1,"tqlasttime":40,"type_id":1,"typename":Utils.getString(R.string.欢乐生肖),"version":107,"waitLotteryQiShu":"190708045"},{"changeVersion":false,"createdDate":1554010252000,"createdUser":"sys","currentLotteryQiShu":"1907081150","currentLotteryTime":1562584190000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":57,"logo":"upload/images/20190417/1555503659942.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":0,"lastLotteryNo":"2,3,5","lastLotteryQiShu":"1907081149","lastModifiedDate":1562584142000,"lastModifiedUser":"sys","listsort":8,"openjgtime":60,"remark":Utils.getString(R.string.1分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":6,"typename":Utils.getString(R.string.极速28),"version":107,"waitLotteryQiShu":"1907081150"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"1907080384","currentLotteryTime":1562584260000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":36,"logo":"upload/images/20190417/1555503814366.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"6,3,1,5,5","lastLotteryQiShu":"1907080383","lastModifiedDate":1562584151000,"lastModifiedUser":"sys","listsort":9,"openjgtime":180,"remark":Utils.getString(R.string.3分钟1期),"size":0,"status":1,"tqlasttime":60,"type_id":9,"typename":Utils.getString(R.string.三分时时彩),"version":104,"waitLotteryQiShu":"1907080384"},{"changeVersion":false,"createdDate":1513063958000,"createdUser":"sys","currentLotteryQiShu":"190708074","currentLotteryTime":1562584410000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":14,"logo":"upload/images/20190417/1555503829619.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"5,2,6,10,8,4,3,9,1,7","lastLotteryQiShu":"190708072","lastModifiedDate":1562584113000,"lastModifiedUser":"sys","listsort":10,"openjgtime":300,"remark":Utils.getString(R.string.全天180期),"size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":Utils.getString(R.string.幸运飞艇),"version":99,"waitLotteryQiShu":"190708073"},{"changeVersion":false,"createdDate":1552494782000,"createdUser":"sys","currentLotteryQiShu":"190708230","currentLotteryTime":1562584190000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":53,"logo":"upload/images/20190417/1555503847678.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"5,10,4,6,7,1,9,8,2,3","lastLotteryQiShu":"190708229","lastModifiedDate":1562583911000,"lastModifiedUser":"sys","listsort":11,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":8,"typename":Utils.getString(R.string.五分赛车),"version":100,"waitLotteryQiShu":"190708230"},{"changeVersion":false,"createdDate":1512700951000,"createdUser":"sys","currentLotteryQiShu":"190708033","currentLotteryTime":1562585280000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":10,"logo":"upload/images/20190420/1555774828395.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,2,6","lastLotteryQiShu":"190708031","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":12,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.江苏快三),"version":103,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1527860066000,"createdUser":"sys","currentLotteryQiShu":"1907081150","currentLotteryTime":1562584190000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":32,"logo":"upload/images/20190420/1555775514175.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"1,1,4","lastLotteryQiShu":"1907081149","lastModifiedDate":1562584142000,"lastModifiedUser":"sys","listsort":13,"openjgtime":60,"remark":Utils.getString(R.string.1分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":2,"typename":Utils.getString(R.string.一分快三),"version":97,"waitLotteryQiShu":"1907081150"},{"changeVersion":false,"createdDate":1512700974000,"createdUser":"sys","currentLotteryQiShu":"735890","currentLotteryTime":1562585320000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":12,"logo":"upload/images/20190417/1555503682262.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,7,5,10,3,4,2,1,8,9","lastLotteryQiShu":"735888","lastModifiedDate":1562584126000,"lastModifiedUser":"sys","listsort":14,"openjgtime":1200,"remark":Utils.getString(R.string.全天44期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.北京赛车),"version":104,"waitLotteryQiShu":"735889"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708032","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":47,"logo":"upload/images/20190420/1555775553666.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,2,4","lastLotteryQiShu":"190708031","lastModifiedDate":1562583627000,"lastModifiedUser":"sys","listsort":15,"openjgtime":1200,"remark":Utils.getString(R.string.全天40期),"size":0,"status":1,"tqlasttime":0,"type_id":9,"typename":Utils.getString(R.string.安徽快三),"version":104,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"2447527","currentLotteryTime":1562584000000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":56,"logo":"upload/images/20190417/1555503909369.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"2,8,2","lastLotteryQiShu":"2447525","lastModifiedDate":1562583791000,"lastModifiedUser":"sys","listsort":16,"openjgtime":200,"remark":Utils.getString(R.string.全天964期),"size":0,"status":1,"tqlasttime":0,"type_id":5,"typename":Utils.getString(R.string.加拿大28),"version":103,"waitLotteryQiShu":"2447526"},{"changeVersion":false,"createdDate":1513675573000,"createdUser":"sys","currentLotteryQiShu":"961803","currentLotteryTime":1562584160000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":17,"logo":"upload/images/20190417/1555503932123.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"8,5,1","lastLotteryQiShu":"961802","lastModifiedDate":1562584019000,"lastModifiedUser":"sys","listsort":17,"openjgtime":300,"remark":Utils.getString(R.string.全天179期),"size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":Utils.getString(R.string.北京pc蛋蛋),"version":104,"waitLotteryQiShu":"961803"},{"changeVersion":false,"createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562584680000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":54,"logo":"upload/images/20190417/1555503951696.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,4,2,4,4","lastLotteryQiShu":"190708030","lastModifiedDate":1562583650000,"lastModifiedUser":"sys","listsort":18,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":Utils.getString(R.string.天津时时彩),"version":108,"waitLotteryQiShu":"190708031"},{"changeVersion":false,"createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"190708028","currentLotteryTime":1562584680000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":55,"logo":"upload/images/20190417/1555503964676.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,2,2,6","lastLotteryQiShu":"190708027","lastModifiedDate":1562583626000,"lastModifiedUser":"sys","listsort":19,"openjgtime":1200,"remark":Utils.getString(R.string.全天48期),"size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":Utils.getString(R.string.新疆时时彩),"version":101,"waitLotteryQiShu":"190708028"},{"changeVersion":false,"createdDate":1534931922000,"createdUser":"sys","currentLotteryQiShu":"1907080115","currentLotteryTime":1562584190000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":40,"logo":"upload/images/20190417/1555503978423.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":0,"lastLotteryNo":"24,26,32,15,45,31,38","lastLotteryQiShu":"1907080114","lastModifiedDate":1562583602000,"lastModifiedUser":"sys","listsort":20,"openjgtime":600,"remark":Utils.getString(R.string.10分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":2,"typename":Utils.getString(R.string.十分六合彩),"version":102,"waitLotteryQiShu":"1907080114"},{"changeVersion":false,"createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"190708767","currentLotteryTime":1562584210000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":43,"logo":"upload/images/20190417/1555503992666.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"8,7,2,3,10,9,4,5,6,1","lastLotteryQiShu":"190708766","lastModifiedDate":1562584151000,"lastModifiedUser":"sys","listsort":21,"openjgtime":90,"remark":Utils.getString(R.string.全天956期),"size":0,"status":1,"tqlasttime":20,"type_id":6,"typename":Utils.getString(R.string.幸运赛车),"version":103,"waitLotteryQiShu":"190708767"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"139451","currentLotteryTime":1562584800000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":45,"logo":"upload/images/20190420/1555775531661.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,5","lastLotteryQiShu":"139450","lastModifiedDate":1562583686000,"lastModifiedUser":"sys","listsort":22,"openjgtime":1200,"remark":Utils.getString(R.string.全天44期),"size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":Utils.getString(R.string.北京快三),"version":106,"waitLotteryQiShu":"139451"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562585280000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":46,"logo":"upload/images/20190420/1555775568294.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,4,6","lastLotteryQiShu":"190708029","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":23,"openjgtime":1200,"remark":Utils.getString(R.string.全天40期),"size":0,"status":1,"tqlasttime":0,"type_id":8,"typename":Utils.getString(R.string.广西快三),"version":98,"waitLotteryQiShu":"190708030"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708033","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":48,"logo":"upload/images/20190420/1555775595241.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,3,4","lastLotteryQiShu":"190708032","lastModifiedDate":1562583568000,"lastModifiedUser":"sys","listsort":24,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":Utils.getString(R.string.吉林快三),"version":101,"waitLotteryQiShu":"190708033"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708033","currentLotteryTime":1562584380000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":49,"logo":"upload/images/20190420/1555775608990.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,5","lastLotteryQiShu":"190708032","lastModifiedDate":1562583269000,"lastModifiedUser":"sys","listsort":25,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":12,"typename":Utils.getString(R.string.江西快三),"version":104,"waitLotteryQiShu":"190708033"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708029","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":50,"logo":"upload/images/20190420/1555775623545.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,6","lastLotteryQiShu":"190708028","lastModifiedDate":1562584015000,"lastModifiedUser":"sys","listsort":26,"openjgtime":1200,"remark":Utils.getString(R.string.全天36期),"size":0,"status":1,"tqlasttime":0,"type_id":13,"typename":Utils.getString(R.string.内蒙古快三),"version":101,"waitLotteryQiShu":"190708029"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708033","currentLotteryTime":1562585280000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":51,"logo":"upload/images/20190420/1555775638912.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,2,5","lastLotteryQiShu":"190708031","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":27,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":14,"typename":Utils.getString(R.string.福建快三),"version":104,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"190708028","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":52,"logo":"upload/images/20190420/1555775652370.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,5,6","lastLotteryQiShu":"190708027","lastModifiedDate":1562583627000,"lastModifiedUser":"sys","listsort":28,"openjgtime":1200,"remark":Utils.getString(R.string.全天36期),"size":0,"status":1,"tqlasttime":0,"type_id":15,"typename":Utils.getString(R.string.甘肃快三),"version":101,"waitLotteryQiShu":"190708028"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708032","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":33,"logo":"upload/images/20190420/1555775665503.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,4,5","lastLotteryQiShu":"190708031","lastModifiedDate":1562583568000,"lastModifiedUser":"sys","listsort":29,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":Utils.getString(R.string.上海快三),"version":105,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562584680000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":34,"logo":"upload/images/20190420/1555775681987.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,4,6","lastLotteryQiShu":"190708030","lastModifiedDate":1562583686000,"lastModifiedUser":"sys","listsort":30,"openjgtime":1200,"remark":Utils.getString(R.string.全天39期),"size":0,"status":1,"tqlasttime":0,"type_id":5,"typename":Utils.getString(R.string.湖北快三),"version":104,"waitLotteryQiShu":"190708031"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708033","currentLotteryTime":1562585280000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":35,"logo":"upload/images/20190420/1555775583040.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,6,6","lastLotteryQiShu":"190708031","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":31,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":6,"typename":Utils.getString(R.string.河北快三),"version":104,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562585220000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":39,"logo":"upload/images/20190417/1555504102079.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,6,2,11,8","lastLotteryQiShu":"190708029","lastModifiedDate":1562584022000,"lastModifiedUser":"sys","listsort":32,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":Utils.getString(R.string.江西11选5),"version":98,"waitLotteryQiShu":"190708030"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708031","currentLotteryTime":1562584680000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":37,"logo":"upload/images/20190417/1555504111597.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,10,7,4,5","lastLotteryQiShu":"190708030","lastModifiedDate":1562583800000,"lastModifiedUser":"sys","listsort":33,"openjgtime":1200,"remark":Utils.getString(R.string.全天45期),"size":0,"status":1,"tqlasttime":0,"type_id":2,"typename":Utils.getString(R.string.上海11选5),"version":100,"waitLotteryQiShu":"190708031"},{"changeVersion":false,"createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"190708032","currentLotteryTime":1562584830000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":38,"logo":"upload/images/20190417/1555504119517.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,6,5,11,10","lastLotteryQiShu":"190708031","lastModifiedDate":1562583741000,"lastModifiedUser":"sys","listsort":34,"openjgtime":1200,"remark":Utils.getString(R.string.全天43期),"size":0,"status":1,"tqlasttime":0,"type_id":3,"typename":Utils.getString(R.string.山东11选5),"version":101,"waitLotteryQiShu":"190708032"},{"changeVersion":false,"createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"1907080231","currentLotteryTime":1562584380000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":41,"logo":"upload/images/20190417/1555505715558.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"0,0,0,1,3","lastLotteryQiShu":"1907080229","lastModifiedDate":1562584082000,"lastModifiedUser":"sys","listsort":35,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":120,"type_id":5,"typename":Utils.getString(R.string.快乐五分彩),"version":98,"waitLotteryQiShu":"1907080230"},{"changeVersion":false,"createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"1907080767","currentLotteryTime":1562584210000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":42,"logo":"upload/images/20190417/1555505730548.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"6,6,7,1,3","lastLotteryQiShu":"1907080766","lastModifiedDate":1562584151000,"lastModifiedUser":"sys","listsort":36,"openjgtime":90,"remark":Utils.getString(R.string.全天956期),"size":0,"status":1,"tqlasttime":20,"type_id":8,"typename":Utils.getString(R.string.极速时时彩),"version":104,"waitLotteryQiShu":"1907080767"},{"changeVersion":false,"createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"19070831","currentLotteryTime":1562584710000,"game":8,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":20,"logo":"upload/images/20190417/1555505774086.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"5,7,14,19,9,12,20,1","lastLotteryQiShu":"19070830","lastModifiedDate":1562583736000,"lastModifiedUser":"sys","listsort":38,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.广东快乐十分),"version":103,"waitLotteryQiShu":"19070831"},{"changeVersion":false,"createdDate":1514362907000,"createdUser":"sys","currentLotteryQiShu":"961804","currentLotteryTime":1562584410000,"game":6,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":21,"logo":"upload/images/20190417/1555505786404.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"03,05,09,15,17,19,25,26,28,33,35,48,55,56,57,63,64,66,78,79","lastLotteryQiShu":"961802","lastModifiedDate":1562584113000,"lastModifiedUser":"sys","listsort":39,"openjgtime":300,"remark":Utils.getString(R.string.全天179期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.北京快乐8),"version":97,"waitLotteryQiShu":"961803"},{"changeVersion":false,"createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"190708046","currentLotteryTime":1562584680000,"game":7,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":19,"logo":"upload/images/20190417/1555505796822.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":0,"lastLotteryNo":"1,18,13,14,8,11,9,12","lastLotteryQiShu":"190708045","lastModifiedDate":1562583706000,"lastModifiedUser":"sys","listsort":40,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.重庆幸运农场),"version":104,"waitLotteryQiShu":"190708046"}]
     * status : success
     */

    private String message;
    private String status;
    private List<GameInfoListBean> gameInfoList;
    private List<GameClassListBean> gameClassList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GameInfoListBean> getGameInfoList() {
        return gameInfoList;
    }

    public void setGameInfoList(List<GameInfoListBean> gameInfoList) {
        this.gameInfoList = gameInfoList;
    }

    public List<GameClassListBean> getGameClassList() {
        return gameClassList;
    }

    public void setGameClassList(List<GameClassListBean> gameClassList) {
        this.gameClassList = gameClassList;
    }

    public static class GameInfoListBean {
        /**
         * classIds : 63,14,53,12,43
         * createdDate : 1533806782000
         * createdUser : admin
         * id : 3
         * logo : upload/images/20190407/1554619994711.png
         * imageBlack : upload/images/20190407/1554619986970.png
         * isDelete : 0
         * lastModifiedDate : 1560504900000
         * lastModifiedUser : lottery
         * listsort : 1
         * name : 赛车
         * status : 1
         */

        private String classIds;
        private long createdDate;
        private String createdUser;
        private long id;
        private String image;
        private String imageBlack;
        private int isDelete;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int listsort;
        private String name;
        private int status;

        public String getClassIds() {
            return classIds;
        }

        public void setClassIds(String classIds) {
            this.classIds = classIds;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageBlack() {
            return imageBlack;
        }

        public void setImageBlack(String imageBlack) {
            this.imageBlack = imageBlack;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class GameClassListBean {
        /**
         * changeVersion : false
         * createdDate : 1513330453000
         * createdUser : sys
         * currentLotteryQiShu : 2019077
         * currentLotteryTime : 1562678990000
         * game : 4
         * gameTouZhuAmount : 0
         * gameTouZhuRadio : 0
         * id : 15
         * logo : upload/images/20190417/1555503796163.png
         * index : 0
         * isDelete : 0
         * isHot : 1
         * isLottery : 0
         * isStart : 1
         * iscustom : 0
         * isopenOffice : 0
         * lastLotteryNo : 42,11,28,08,10,07,29
         * lastLotteryQiShu : 2019076
         * lastModifiedDate : 1562555300000
         * lastModifiedUser : sys
         * listsort : 0
         * openjgtime : 2
         * remark : 一周三期
         * size : 0
         * status : 1
         * tqlasttime : 10
         * type_id : 1
         * typename : 香港六合彩
         * version : 110
         * waitLotteryQiShu : 2019077
         */

        private boolean changeVersion;
        private long createdDate;
        private String createdUser;
        private String currentLotteryQiShu;
        private long currentLotteryTime;
        private int game;
        private int gameTouZhuAmount;
        private int gameTouZhuRadio;
        private long id;
        private String image;
        private int index;
        private int isDelete;
        private int isHot;
        private int isLottery;
        private int isStart;
        private int iscustom;
        private int isopenOffice;
        private String lastLotteryNo;
        private String lastLotteryQiShu;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private int listsort;
        private int openjgtime;
        private String remark;
        private int size;
        private int status;
        private int tqlasttime;
        private int type_id;
        private String typename;
        private int version;
        private String waitLotteryQiShu;

        public boolean isChangeVersion() {
            return changeVersion;
        }

        public void setChangeVersion(boolean changeVersion) {
            this.changeVersion = changeVersion;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public String getCurrentLotteryQiShu() {
            return currentLotteryQiShu;
        }

        public void setCurrentLotteryQiShu(String currentLotteryQiShu) {
            this.currentLotteryQiShu = currentLotteryQiShu;
        }

        public long getCurrentLotteryTime() {
            return currentLotteryTime;
        }

        public void setCurrentLotteryTime(long currentLotteryTime) {
            this.currentLotteryTime = currentLotteryTime;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public int getGameTouZhuAmount() {
            return gameTouZhuAmount;
        }

        public void setGameTouZhuAmount(int gameTouZhuAmount) {
            this.gameTouZhuAmount = gameTouZhuAmount;
        }

        public int getGameTouZhuRadio() {
            return gameTouZhuRadio;
        }

        public void setGameTouZhuRadio(int gameTouZhuRadio) {
            this.gameTouZhuRadio = gameTouZhuRadio;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsLottery() {
            return isLottery;
        }

        public void setIsLottery(int isLottery) {
            this.isLottery = isLottery;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
        }

        public int getIscustom() {
            return iscustom;
        }

        public void setIscustom(int iscustom) {
            this.iscustom = iscustom;
        }

        public int getIsopenOffice() {
            return isopenOffice;
        }

        public void setIsopenOffice(int isopenOffice) {
            this.isopenOffice = isopenOffice;
        }

        public String getLastLotteryNo() {
            return lastLotteryNo;
        }

        public void setLastLotteryNo(String lastLotteryNo) {
            this.lastLotteryNo = lastLotteryNo;
        }

        public String getLastLotteryQiShu() {
            return lastLotteryQiShu;
        }

        public void setLastLotteryQiShu(String lastLotteryQiShu) {
            this.lastLotteryQiShu = lastLotteryQiShu;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public int getListsort() {
            return listsort;
        }

        public void setListsort(int listsort) {
            this.listsort = listsort;
        }

        public int getOpenjgtime() {
            return openjgtime;
        }

        public void setOpenjgtime(int openjgtime) {
            this.openjgtime = openjgtime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTqlasttime() {
            return tqlasttime;
        }

        public void setTqlasttime(int tqlasttime) {
            this.tqlasttime = tqlasttime;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getWaitLotteryQiShu() {
            return waitLotteryQiShu;
        }

        public void setWaitLotteryQiShu(String waitLotteryQiShu) {
            this.waitLotteryQiShu = waitLotteryQiShu;
        }
    }
}
