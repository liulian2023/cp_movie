package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.io.Serializable;
import java.util.List;

public class NavigateEntity implements Serializable {


    /**
     * gameInfoList : [{"classIds":"15,40","createdDate":1533806827000,"createdUser":"admin","id":5,"image":"upload/images/20191102/1572682106934.png","imageBlack":"upload/images/20191102/1572682103443.png","isDelete":0,"lastModifiedDate":1572682108000,"lastModifiedUser":"xbgoogle","listsort":1,"name":Utils.getString(R.string.六合彩),"status":1},{"classIds":"62,59,42,54,55,8","createdDate":1533806644000,"createdUser":"admin","id":2,"image":"upload/images/20191102/1572682124575.png","imageBlack":"upload/images/20191102/1572682121475.png","isDelete":0,"lastModifiedDate":1578051493000,"lastModifiedUser":"xbgoogle","listsort":2,"name":Utils.getString(R.string.时时彩),"status":1},{"classIds":"63,53,12,14,85,43","createdDate":1533806782000,"createdUser":"admin","id":3,"image":"upload/images/20191102/1572682142460.png","imageBlack":"upload/images/20191102/1572682138947.png","isDelete":0,"lastModifiedDate":1572682144000,"lastModifiedUser":"xbgoogle","listsort":3,"name":Utils.getString(R.string.赛车),"status":1},{"classIds":"88,89,10,45,46,35,32,34,48,47,49,51,52,33,78","createdDate":1533796000000,"createdUser":"admin","id":1,"image":"upload/images/20191102/1572682158436.png","imageBlack":"upload/images/20191102/1572682155170.png","isDelete":0,"lastModifiedDate":1578056033000,"lastModifiedUser":"xbgoogle","listsort":4,"name":Utils.getString(R.string.快三),"status":1},{"classIds":"57,17","createdDate":1554363738000,"createdUser":"mogu","id":8,"image":"upload/images/20191102/1572682176927.png","imageBlack":"upload/images/20191102/1572682173326.png","isDelete":0,"lastModifiedDate":1572748390000,"lastModifiedUser":"kugua","listsort":5,"name":Utils.getString(R.string.pc蛋蛋),"status":1},{"classIds":"22,38,37,39,65","createdDate":1552031970000,"createdUser":"admin","id":7,"image":"upload/images/20191102/1572682192000.png","imageBlack":"upload/images/20191102/1572682188903.png","isDelete":0,"lastModifiedDate":1572682193000,"lastModifiedUser":"xbgoogle","listsort":6,"name":Utils.getString(R.string.11选5),"status":1},{"classIds":"19,21,20","createdDate":1533806908000,"createdUser":"admin","id":6,"image":"upload/images/20191102/1572682210444.png","imageBlack":"upload/images/20191102/1572682205997.png","isDelete":0,"lastModifiedDate":1572682212000,"lastModifiedUser":"xbgoogle","listsort":7,"name":Utils.getString(R.string.快乐彩),"status":1}]
     * message : 获取数据成功
     * gameClassList : [{"changeVersion":false,"code":"liuhe","createdDate":1513330453000,"createdUser":"sys","currentLotteryQiShu":"2019147","currentLotteryTime":1578403790000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":15,"image":"upload/images/20191102/1572682251634.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"37,29,15,34,30,17,40","lastLotteryQiShu":"2020001","lastLotteryTime":1577972087000,"lastModifiedDate":1578373712000,"lastModifiedUser":"mogu","listsort":0,"openjgtime":2,"remark":Utils.getString(R.string.每周3期),"size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":Utils.getString(R.string.香港六合彩),"version":908,"waitLotteryQiShu":"2019147"},{"changeVersion":false,"code":"sfks","createdDate":1578049109000,"createdUser":"xbgoogle","currentLotteryQiShu":"2001070358","currentLotteryTime":1578390830000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":88,"image":"upload/images/20200103/1578049204640.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"1,4,2","lastLotteryQiShu":"2001060431","lastLotteryTime":1578317580000,"lastModifiedDate":1578390653000,"lastModifiedUser":"sys","listsort":0,"openjgtime":180,"remark":Utils.getString(R.string.3分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":25,"typename":Utils.getString(R.string.3分快3),"version":11,"waitLotteryQiShu":"2001070357"},{"changeVersion":false,"code":"shifenliuhe","createdDate":1534931922000,"createdUser":"sys","currentLotteryQiShu":"2001070087","currentLotteryTime":1578378590000,"game":4,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":40,"image":"upload/images/20191102/1572682401143.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"39,31,04,35,08,37,49","lastLotteryQiShu":"2001070112","lastLotteryTime":1578393600000,"lastModifiedDate":1578393603000,"lastModifiedUser":"sys","listsort":1,"openjgtime":600,"remark":Utils.getString(R.string.10分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":2,"typename":Utils.getString(R.string.10分六合彩),"version":890,"waitLotteryQiShu":"2001070112"},{"changeVersion":false,"code":"sfk3","createdDate":1578055989000,"createdUser":"xbgoogle","currentLotteryQiShu":"2001070087","currentLotteryTime":1578378480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":89,"image":"upload/images/20200103/1578056017660.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,2,6","lastLotteryQiShu":"2001060129","lastLotteryTime":1578317400000,"lastModifiedDate":1578378012000,"lastModifiedUser":"sys","listsort":1,"openjgtime":600,"remark":Utils.getString(R.string.10分钟1期),"size":0,"status":1,"tqlasttime":120,"type_id":26,"typename":Utils.getString(R.string.10分快3),"version":5,"waitLotteryQiShu":"2001070080"},{"changeVersion":false,"code":"azxy5","createdDate":1560503797000,"createdUser":"sys","currentLotteryQiShu":"50647255","currentLotteryTime":1578378480000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":62,"image":"upload/images/20191102/1572682894218.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,5,2,9,9","lastLotteryQiShu":"50647306","lastLotteryTime":1578393842000,"lastModifiedDate":1578393869000,"lastModifiedUser":"sys","listsort":2,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":0,"type_id":51,"typename":Utils.getString(R.string.澳洲幸运5),"version":706,"waitLotteryQiShu":"50647306"},{"changeVersion":false,"code":"azxy10","createdDate":1560504416000,"createdUser":"sys","currentLotteryQiShu":"20656155","currentLotteryTime":1578378420000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":63,"image":"upload/images/20191102/1572682980329.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,8,9,2,6,5,7,4,1,10","lastLotteryQiShu":"20656206","lastLotteryTime":1578393844000,"lastModifiedDate":1578393869000,"lastModifiedUser":"sys","listsort":3,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":Utils.getString(R.string.澳洲幸运10),"version":614,"waitLotteryQiShu":"20656206"},{"changeVersion":false,"code":"wufen","createdDate":1552494782000,"createdUser":"sys","currentLotteryQiShu":"200107173","currentLotteryTime":1578378290000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":53,"image":"upload/images/20191102/1572682388721.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"9,7,6,1,2,8,3,4,5,10","lastLotteryQiShu":"200107225","lastLotteryTime":1578393900000,"lastModifiedDate":1578393912000,"lastModifiedUser":"sys","listsort":4,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":8,"typename":Utils.getString(R.string.5分赛车),"version":678,"waitLotteryQiShu":"200107225"},{"changeVersion":false,"code":"xuanwu","createdDate":1514451867000,"createdUser":"sys","currentLotteryQiShu":"200107016","currentLotteryTime":1578378540000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":22,"image":"upload/images/20191102/1572682448807.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,10,8,11,4","lastLotteryQiShu":"200107028","lastLotteryTime":1578393079000,"lastModifiedDate":1578393089000,"lastModifiedUser":"sys","listsort":5,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.广东11选5),"version":727,"waitLotteryQiShu":"200107028"},{"changeVersion":false,"code":"tencentffc","createdDate":1559980198000,"createdUser":"sys","currentLotteryQiShu":"2001071077","currentLotteryTime":1578391015000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":59,"image":"upload/images/20191102/1572682877034.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,1,0,0,2","lastLotteryQiShu":"2001071128","lastLotteryTime":1578394080000,"lastModifiedDate":1578394083000,"lastModifiedUser":"sys","listsort":6,"openjgtime":60,"remark":Utils.getString(R.string.1分钟1期),"size":0,"status":1,"tqlasttime":5,"type_id":12,"typename":Utils.getString(R.string.腾讯分分彩),"version":723,"waitLotteryQiShu":"2001071128"},{"changeVersion":false,"code":"farm","createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"200107032","currentLotteryTime":1578379080000,"game":7,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":19,"image":"upload/images/20191102/1572682541381.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"10,5,1,6,11,18,9,14","lastLotteryQiShu":"200107044","lastLotteryTime":1578393686000,"lastModifiedDate":1578393719000,"lastModifiedUser":"sys","listsort":7,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.重庆幸运农场),"version":665,"waitLotteryQiShu":"200107044"},{"changeVersion":false,"code":"happy8","createdDate":1514362907000,"createdUser":"sys","currentLotteryQiShu":"993256","currentLotteryTime":1578380010000,"game":6,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":21,"image":"upload/images/20191102/1572682556155.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,11,12,18,20,21,27,38,39,41,43,47,49,59,65,66,70,71,72,79","lastLotteryQiShu":"993302","lastLotteryTime":1578394009000,"lastModifiedDate":1578394019000,"lastModifiedUser":"sys","listsort":8,"openjgtime":300,"remark":Utils.getString(R.string.全天179期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.北京快乐8),"version":651,"waitLotteryQiShu":"993302"},{"changeVersion":false,"code":"xingyu","createdDate":1514362584000,"createdUser":"sys","currentLotteryQiShu":"20010717","currentLotteryTime":1578379110000,"game":8,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":20,"image":"upload/images/20191102/1572682568476.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"11,19,1,14,20,7,10,18","lastLotteryQiShu":"20010729","lastLotteryTime":1578393699000,"lastModifiedDate":1578393719000,"lastModifiedUser":"sys","listsort":9,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.广东快乐10分),"version":654,"waitLotteryQiShu":"20010729"},{"changeVersion":false,"code":"JSK3","createdDate":1512700951000,"createdUser":"sys","currentLotteryQiShu":"200107018","currentLotteryTime":1578378480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":10,"image":"upload/images/20191102/1572682329277.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,5,6","lastLotteryQiShu":"200107030","lastLotteryTime":1578392989000,"lastModifiedDate":1578392999000,"lastModifiedUser":"sys","listsort":11,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.江苏快3),"version":919,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"BJK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"147181","currentLotteryTime":1578379200000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":45,"image":"upload/images/20191102/1572683422933.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,3,3","lastLotteryQiShu":"147193","lastLotteryTime":1578393639000,"lastModifiedDate":1578393659000,"lastModifiedUser":"sys","listsort":12,"openjgtime":1200,"remark":Utils.getString(R.string.全天44期),"size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":Utils.getString(R.string.北京快3),"version":919,"waitLotteryQiShu":"147193"},{"changeVersion":false,"code":"HXK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107016","currentLotteryTime":1578378480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":46,"image":"upload/images/20191102/1572682908193.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,4,6","lastLotteryQiShu":"200107028","lastLotteryTime":1578392983000,"lastModifiedDate":1578393000000,"lastModifiedUser":"sys","listsort":13,"openjgtime":1200,"remark":Utils.getString(R.string.全天40期),"size":0,"status":1,"tqlasttime":0,"type_id":8,"typename":Utils.getString(R.string.广西快3),"version":918,"waitLotteryQiShu":"200107028"},{"changeVersion":false,"code":"HEBK3","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107018","currentLotteryTime":1578378480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":35,"image":"upload/images/20191102/1572682634425.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,4,6","lastLotteryQiShu":"200107030","lastLotteryTime":1578392961000,"lastModifiedDate":1578392999000,"lastModifiedUser":"sys","listsort":14,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":6,"typename":Utils.getString(R.string.河北快3),"version":920,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"FHK3","createdDate":1527860066000,"createdUser":"sys","currentLotteryQiShu":"2001071077","currentLotteryTime":1578391010000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":32,"image":"upload/images/20191102/1572682238734.png","index":0,"isDelete":0,"isHot":1,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,1,1","lastLotteryQiShu":"2001071128","lastLotteryTime":1578394080000,"lastModifiedDate":1578394083000,"lastModifiedUser":"sys","listsort":15,"openjgtime":60,"remark":Utils.getString(R.string.1分钟5期),"size":0,"status":1,"tqlasttime":10,"type_id":2,"typename":Utils.getString(R.string.1分快3),"version":900,"waitLotteryQiShu":"2001071128"},{"changeVersion":false,"code":"HBK3","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107017","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":34,"image":"upload/images/20191102/1572682650132.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,2,5","lastLotteryQiShu":"200107029","lastLotteryTime":1578393639000,"lastModifiedDate":1578393659000,"lastModifiedUser":"sys","listsort":16,"openjgtime":1200,"remark":Utils.getString(R.string.全天39期),"size":0,"status":1,"tqlasttime":0,"type_id":5,"typename":Utils.getString(R.string.湖北快3),"version":925,"waitLotteryQiShu":"200107029"},{"changeVersion":false,"code":"JLK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107019","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":48,"image":"upload/images/20191102/1572682946449.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,6,6","lastLotteryQiShu":"200107031","lastLotteryTime":1578393567000,"lastModifiedDate":1578393599000,"lastModifiedUser":"sys","listsort":17,"openjgtime":1200,"remark":Utils.getString(R.string.全天40期),"size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":Utils.getString(R.string.吉林快3),"version":919,"waitLotteryQiShu":"200107031"},{"changeVersion":false,"code":"NHK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107018","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":47,"image":"upload/images/20191102/1572682732405.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"1,2,4","lastLotteryQiShu":"200107030","lastLotteryTime":1578393607000,"lastModifiedDate":1578393629000,"lastModifiedUser":"sys","listsort":18,"openjgtime":1200,"remark":Utils.getString(R.string.全天40期),"size":0,"status":1,"tqlasttime":0,"type_id":9,"typename":Utils.getString(R.string.安徽快3),"version":940,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"JXK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107019","currentLotteryTime":1578378780000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":49,"image":"upload/images/20191102/1572683029639.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,2,6","lastLotteryQiShu":"200107031","lastLotteryTime":1578393186000,"lastModifiedDate":1578393209000,"lastModifiedUser":"sys","listsort":19,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":12,"typename":Utils.getString(R.string.江西快3),"version":935,"waitLotteryQiShu":"200107031"},{"changeVersion":false,"code":"NMGK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107015","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":50,"image":"upload/images/20191102/1572683061044.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,4,5","lastLotteryQiShu":"200107027","lastLotteryTime":1578393962000,"lastModifiedDate":1578393989000,"lastModifiedUser":"sys","listsort":20,"openjgtime":1200,"remark":Utils.getString(R.string.全天36期),"size":0,"status":1,"tqlasttime":0,"type_id":13,"typename":Utils.getString(R.string.内蒙古快3),"version":945,"waitLotteryQiShu":"200107027"},{"changeVersion":false,"code":"FJK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107018","currentLotteryTime":1578378480000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":51,"image":"upload/images/20191102/1572683046964.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,4,5","lastLotteryQiShu":"200107030","lastLotteryTime":1578393208000,"lastModifiedDate":1578393210000,"lastModifiedUser":"sys","listsort":21,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":14,"typename":Utils.getString(R.string.福建快3),"version":931,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"GSK3","createdDate":1552110671000,"createdUser":"sys","currentLotteryQiShu":"200107014","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":52,"image":"upload/images/20191102/1572683071812.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"2,6,6","lastLotteryQiShu":"200107026","lastLotteryTime":1578393597000,"lastModifiedDate":1578393600000,"lastModifiedUser":"sys","listsort":22,"openjgtime":1200,"remark":Utils.getString(R.string.全天36期),"size":0,"status":1,"tqlasttime":0,"type_id":15,"typename":Utils.getString(R.string.甘肃快3),"version":935,"waitLotteryQiShu":"200107026"},{"changeVersion":false,"code":"SHK3","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107018","currentLotteryTime":1578379080000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":33,"image":"upload/images/20191102/1572682663534.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,5,6","lastLotteryQiShu":"200107030","lastLotteryTime":1578393527000,"lastModifiedDate":1578393539000,"lastModifiedUser":"sys","listsort":23,"openjgtime":1200,"remark":Utils.getString(R.string.全天41期),"size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":Utils.getString(R.string.上海快3),"version":933,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"5fk3","createdDate":1568258147000,"createdUser":"xbgoogle","currentLotteryQiShu":"2001070173","currentLotteryTime":1578378290000,"game":1,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":78,"image":"upload/images/20191102/1572682303475.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"5,3,5","lastLotteryQiShu":"2001060259","lastLotteryTime":1578317700000,"lastModifiedDate":1578377996000,"lastModifiedUser":"sys","listsort":24,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":24,"typename":Utils.getString(R.string.5分快3),"version":473,"waitLotteryQiShu":"2001070172"},{"changeVersion":false,"code":"BJrace","createdDate":1512700974000,"createdUser":"sys","currentLotteryQiShu":"743621","currentLotteryTime":1578380920000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":12,"image":"upload/images/20191102/1572682368118.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"9,2,3,4,7,6,1,5,10,8","lastLotteryQiShu":"743631","lastLotteryTime":1578393145000,"lastModifiedDate":1578393179000,"lastModifiedUser":"sys","listsort":25,"openjgtime":1200,"remark":Utils.getString(R.string.全天44期),"size":0,"status":1,"tqlasttime":0,"type_id":1,"typename":Utils.getString(R.string.北京赛车),"version":663,"waitLotteryQiShu":"743631"},{"changeVersion":false,"code":"feiting","createdDate":1513063958000,"createdUser":"sys","currentLotteryQiShu":"200107022","currentLotteryTime":1578380010000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":14,"image":"upload/images/20191102/1572682290307.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,8,2,9,5,3,7,10,6,1","lastLotteryQiShu":"200107068","lastLotteryTime":1578393878000,"lastModifiedDate":1578393899000,"lastModifiedUser":"sys","listsort":26,"openjgtime":300,"remark":Utils.getString(R.string.全天180期),"size":0,"status":1,"tqlasttime":0,"type_id":7,"typename":Utils.getString(R.string.幸运飞艇),"version":663,"waitLotteryQiShu":"200107068"},{"changeVersion":false,"code":"jssc","createdDate":1569467187000,"createdUser":"xbgoogle","currentLotteryQiShu":"2001071077","currentLotteryTime":1578391010000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":85,"image":"upload/images/20191102/1572682265312.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"3,1,2,9,8,7,10,6,5,4","lastLotteryQiShu":"2001061295","lastLotteryTime":1578317700000,"lastModifiedDate":1578390955000,"lastModifiedUser":"sys","listsort":27,"openjgtime":60,"remark":Utils.getString(R.string.1分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":13,"typename":Utils.getString(R.string.极速赛车),"version":193,"waitLotteryQiShu":"2001071076"},{"changeVersion":false,"code":"xn","createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"200107715","currentLotteryTime":1578390730000,"game":3,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":43,"image":"upload/images/20191102/1572682507638.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"9,7,4,8,6,2,5,10,3,1","lastLotteryQiShu":"200107752","lastLotteryTime":1578394080000,"lastModifiedDate":1578394092000,"lastModifiedUser":"sys","listsort":28,"openjgtime":90,"remark":Utils.getString(R.string.全天956期),"size":0,"status":1,"tqlasttime":20,"type_id":6,"typename":Utils.getString(R.string.幸运赛车),"version":665,"waitLotteryQiShu":"200107752"},{"changeVersion":false,"code":"jisushishicai","createdDate":1551110003000,"createdUser":"sys","currentLotteryQiShu":"2001070715","currentLotteryTime":1578390735000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":42,"image":"upload/images/20191102/1572682480218.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"1,2,2,5,0","lastLotteryQiShu":"2001070752","lastLotteryTime":1578394080000,"lastModifiedDate":1578394083000,"lastModifiedUser":"sys","listsort":29,"openjgtime":90,"remark":Utils.getString(R.string.全天957期),"size":0,"status":1,"tqlasttime":15,"type_id":8,"typename":Utils.getString(R.string.极速时时彩),"version":750,"waitLotteryQiShu":"2001070752"},{"changeVersion":false,"code":"tjssc","createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"200107017","currentLotteryTime":1578379080000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":54,"image":"upload/images/20191102/1572682355651.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"3,2,7,2,7","lastLotteryQiShu":"200107029","lastLotteryTime":1578393649000,"lastModifiedDate":1578393689000,"lastModifiedUser":"sys","listsort":30,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":10,"typename":Utils.getString(R.string.天津时时彩),"version":776,"waitLotteryQiShu":"200107029"},{"changeVersion":false,"code":"xjssc","createdDate":1552662316000,"createdUser":"sys","currentLotteryQiShu":"200107014","currentLotteryTime":1578379080000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":55,"image":"upload/images/20191102/1572682414770.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"6,7,2,8,4","lastLotteryQiShu":"200107026","lastLotteryTime":1578393611000,"lastModifiedDate":1578393659000,"lastModifiedUser":"sys","listsort":31,"openjgtime":1200,"remark":Utils.getString(R.string.全天48期),"size":0,"status":1,"tqlasttime":0,"type_id":11,"typename":Utils.getString(R.string.新疆时时彩),"version":762,"waitLotteryQiShu":"200107026"},{"changeVersion":false,"code":"CQsscai","createdDate":1512700661000,"createdUser":"sys","currentLotteryQiShu":"200107031","currentLotteryTime":1578378480000,"game":2,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":8,"image":"upload/images/20191102/1572682343205.png","index":0,"isDelete":0,"isHot":1,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"4,3,4,4,1","lastLotteryQiShu":"200107043","lastLotteryTime":1578393057000,"lastModifiedDate":1578393089000,"lastModifiedUser":"sys","listsort":32,"openjgtime":1200,"remark":Utils.getString(R.string.全天59期),"size":0,"status":1,"tqlasttime":40,"type_id":1,"typename":Utils.getString(R.string.欢乐生肖),"version":763,"waitLotteryQiShu":"200107043"},{"changeVersion":false,"code":"SDxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107019","currentLotteryTime":1578380430000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":38,"image":"upload/images/20191102/1572682595560.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"7,6,3,5,9","lastLotteryQiShu":"200107030","lastLotteryTime":1578393723000,"lastModifiedDate":1578393749000,"lastModifiedUser":"sys","listsort":34,"openjgtime":1200,"remark":Utils.getString(R.string.全天43期),"size":0,"status":1,"tqlasttime":0,"type_id":3,"typename":Utils.getString(R.string.山东11选5),"version":729,"waitLotteryQiShu":"200107030"},{"changeVersion":false,"code":"SHxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107017","currentLotteryTime":1578379080000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":37,"image":"upload/images/20191102/1572682607884.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,9,4,6,7","lastLotteryQiShu":"200107029","lastLotteryTime":1578393704000,"lastModifiedDate":1578393719000,"lastModifiedUser":"sys","listsort":35,"openjgtime":1200,"remark":Utils.getString(R.string.全天45期),"size":0,"status":1,"tqlasttime":0,"type_id":2,"typename":Utils.getString(R.string.上海11选5),"version":722,"waitLotteryQiShu":"200107029"},{"changeVersion":false,"code":"JXxuanwu","createdDate":1528276455000,"createdUser":"sys","currentLotteryQiShu":"200107016","currentLotteryTime":1578378420000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":39,"image":"upload/images/20191102/1572682619680.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"5,11,6,2,9","lastLotteryQiShu":"200107028","lastLotteryTime":1578393079000,"lastModifiedDate":1578393090000,"lastModifiedUser":"sys","listsort":36,"openjgtime":1200,"remark":Utils.getString(R.string.全天42期),"size":0,"status":1,"tqlasttime":0,"type_id":4,"typename":Utils.getString(R.string.江西11选5),"version":726,"waitLotteryQiShu":"200107028"},{"changeVersion":false,"code":"Wfen11xuan5","createdDate":1564199687000,"createdUser":"sys","currentLotteryQiShu":"200107173","currentLotteryTime":1578378290000,"game":9,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":65,"image":"upload/images/20191102/1572682678925.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"7,2,4,1,11","lastLotteryQiShu":"200107225","lastLotteryTime":1578393900000,"lastModifiedDate":1578393903000,"lastModifiedUser":"sys","listsort":38,"openjgtime":300,"remark":Utils.getString(R.string.5分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":6,"typename":Utils.getString(R.string.5分11选5),"version":611,"waitLotteryQiShu":"200107225"},{"changeVersion":false,"code":"js28","createdDate":1554010252000,"createdUser":"sys","currentLotteryQiShu":"2001071077","currentLotteryTime":1578391010000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":57,"image":"upload/images/20191102/1572682924228.png","index":0,"isDelete":0,"isHot":0,"isLottery":1,"isStart":1,"iscustom":1,"isopenOffice":1,"lastLotteryNo":"4,5,6","lastLotteryQiShu":"2001071128","lastLotteryTime":1578394080000,"lastModifiedDate":1578394083000,"lastModifiedUser":"sys","listsort":39,"openjgtime":60,"remark":Utils.getString(R.string.1分钟1期),"size":0,"status":1,"tqlasttime":10,"type_id":6,"typename":Utils.getString(R.string.极速28),"version":718,"waitLotteryQiShu":"2001071128"},{"changeVersion":false,"code":"dan","createdDate":1513675573000,"createdUser":"sys","currentLotteryQiShu":"993256","currentLotteryTime":1578380060000,"game":5,"gameTouZhuAmount":0,"gameTouZhuRadio":0,"id":17,"image":"upload/images/20191102/1572682436684.png","index":0,"isDelete":0,"isHot":0,"isLottery":0,"isStart":1,"iscustom":0,"isopenOffice":1,"lastLotteryNo":"8,5,0","lastLotteryQiShu":"993302","lastLotteryTime":1578394009000,"lastModifiedDate":1578394019000,"lastModifiedUser":"sys","listsort":40,"openjgtime":300,"remark":Utils.getString(R.string.全天179期),"size":0,"status":1,"tqlasttime":10,"type_id":1,"typename":Utils.getString(R.string.北京28),"version":709,"waitLotteryQiShu":"993302"}]
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
         * classIds : 15,40
         * createdDate : 1533806827000
         * createdUser : admin
         * id : 5
         * image : upload/images/20191102/1572682106934.png
         * imageBlack : upload/images/20191102/1572682103443.png
         * isDelete : 0
         * lastModifiedDate : 1572682108000
         * lastModifiedUser : xbgoogle
         * listsort : 1
         * name : 六合彩
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
         * code : liuhe
         * createdDate : 1513330453000
         * createdUser : sys
         * currentLotteryQiShu : 2019147
         * currentLotteryTime : 1578403790000
         * game : 4
         * gameTouZhuAmount : 0
         * gameTouZhuRadio : 0
         * id : 15
         * image : upload/images/20191102/1572682251634.png
         * index : 0
         * isDelete : 0
         * isHot : 1
         * isLottery : 0
         * isStart : 1
         * iscustom : 0
         * isopenOffice : 1
         * lastLotteryNo : 37,29,15,34,30,17,40
         * lastLotteryQiShu : 2020001
         * lastLotteryTime : 1577972087000
         * lastModifiedDate : 1578373712000
         * lastModifiedUser : mogu
         * listsort : 0
         * openjgtime : 2
         * remark : 每周3期
         * size : 0
         * status : 1
         * tqlasttime : 10
         * type_id : 1
         * typename : 香港六合彩
         * version : 908
         * waitLotteryQiShu : 2019147
         */
        private boolean isHistory;
        private boolean isXian;
        private boolean changeVersion;
        private String code;
        private long createdDate;
        private String createdUser;
        private String currentLotteryQiShu;
        private long currentLotteryTime;
        private int game = 1;
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
        private long lastLotteryTime;
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

        public boolean isHistory() {
            return isHistory;
        }

        public void setHistory(boolean history) {
            isHistory = history;
        }

        public boolean isXian() {
            return isXian;
        }

        public void setXian(boolean xian) {
            isXian = xian;
        }

        public boolean isChangeVersion() {
            return changeVersion;
        }

        public void setChangeVersion(boolean changeVersion) {
            this.changeVersion = changeVersion;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public long getLastLotteryTime() {
            return lastLotteryTime;
        }

        public void setLastLotteryTime(long lastLotteryTime) {
            this.lastLotteryTime = lastLotteryTime;
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
