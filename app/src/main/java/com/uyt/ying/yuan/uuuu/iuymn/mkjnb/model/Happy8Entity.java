/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class Happy8Entity  {

    /**
     * rulelistAll : [{"Rulelist":[{"code":"heda","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":101,"isDelete":0,"isZM":0,"lastModifiedDate":1554019134000,"model_id":1,"name":Utils.getString(R.string.总和大),"odds":1.98,"type_id":1},{"code":"hexiao","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":102,"isDelete":0,"isZM":0,"lastModifiedDate":1554019134000,"model_id":2,"name":Utils.getString(R.string.总和小),"odds":1.98,"type_id":1},{"code":"hedan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":103,"isDelete":0,"isZM":0,"lastModifiedDate":1554019135000,"model_id":3,"name":Utils.getString(R.string.总和单),"odds":1.98,"type_id":1},{"code":"hesh","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":104,"isDelete":0,"isZM":0,"lastModifiedDate":1554019135000,"model_id":4,"name":Utils.getString(R.string.总和双),"odds":1.98,"type_id":1},{"code":"he810","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":105,"isDelete":0,"isZM":0,"lastModifiedDate":1554020889000,"model_id":5,"name":Utils.getString(R.string.总和810),"odds":108.9,"type_id":1},{"code":"dadan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":106,"isDelete":0,"isZM":0,"lastModifiedDate":1554019136000,"model_id":6,"name":Utils.getString(R.string.总大单),"odds":3.96,"type_id":1},{"code":"dash","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":107,"isDelete":0,"isZM":0,"lastModifiedDate":1554019136000,"model_id":7,"name":Utils.getString(R.string.总大双),"odds":3.96,"type_id":1},{"code":"xiaodan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":108,"isDelete":0,"isZM":0,"lastModifiedDate":1554019137000,"model_id":8,"name":Utils.getString(R.string.总小单),"odds":3.96,"type_id":1},{"code":"xiaosh","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":109,"isDelete":0,"isZM":0,"lastModifiedDate":1554019137000,"model_id":9,"name":Utils.getString(R.string.总小双),"odds":3.96,"type_id":1},{"code":"qiand","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":110,"isDelete":0,"isZM":0,"lastModifiedDate":1554017388000,"model_id":10,"name":Utils.getString(R.string.前(多)),"odds":1.98,"type_id":1},{"code":"houd","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":111,"isDelete":0,"isZM":0,"lastModifiedDate":1554017388000,"model_id":11,"name":Utils.getString(R.string.后(多)),"odds":1.98,"type_id":1},{"code":"qianhouh","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":112,"isDelete":0,"isZM":0,"lastModifiedDate":1554017389000,"model_id":12,"name":Utils.getString(R.string.前后(和)),"odds":1.98,"type_id":1},{"code":"dand","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":113,"isDelete":0,"isZM":0,"lastModifiedDate":1554017404000,"model_id":13,"name":Utils.getString(R.string.单(多)),"odds":1.98,"type_id":1},{"code":"shd","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":114,"isDelete":0,"isZM":0,"lastModifiedDate":1554017404000,"model_id":14,"name":Utils.getString(R.string.双(多)),"odds":1.98,"type_id":1},{"code":"danshd","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":115,"isDelete":0,"isZM":0,"lastModifiedDate":1554017405000,"model_id":15,"name":Utils.getString(R.string.单双(和)),"odds":1.98,"type_id":1},{"code":"jin","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":116,"isDelete":0,"isZM":0,"lastModifiedDate":1554021050000,"model_id":16,"name":Utils.getString(R.string.金),"odds":9.227,"type_id":1},{"code":"mu","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":117,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":17,"name":Utils.getString(R.string.木),"odds":4.623,"type_id":1},{"code":"shui","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":118,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":18,"name":Utils.getString(R.string.水),"odds":2.416,"type_id":1},{"code":"huo","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":119,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":19,"name":Utils.getString(R.string.火),"odds":4.623,"type_id":1},{"code":"tu","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":120,"isDelete":0,"isZM":0,"lastModifiedDate":1554021052000,"model_id":20,"name":Utils.getString(R.string.土),"odds":9.227,"type_id":1}],"isZM":0},{"Rulelist":[{"code":"1","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":121,"isDelete":0,"isZM":1,"lastModifiedDate":1554008951000,"model_id":21,"name":"1","odds":3.96,"type_id":1},{"code":"2","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":122,"isDelete":0,"isZM":1,"lastModifiedDate":1554008952000,"model_id":22,"name":"2","odds":3.96,"type_id":1},{"code":"3","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":123,"isDelete":0,"isZM":1,"lastModifiedDate":1554008953000,"model_id":23,"name":"3","odds":3.96,"type_id":1},{"code":"4","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":124,"isDelete":0,"isZM":1,"lastModifiedDate":1554008953000,"model_id":24,"name":"4","odds":3.96,"type_id":1},{"code":"5","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":125,"isDelete":0,"isZM":1,"lastModifiedDate":1554008954000,"model_id":25,"name":"5","odds":3.96,"type_id":1},{"code":"6","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":126,"isDelete":0,"isZM":1,"lastModifiedDate":1554008954000,"model_id":26,"name":"6","odds":3.96,"type_id":1},{"code":"7","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":127,"isDelete":0,"isZM":1,"lastModifiedDate":1554008955000,"model_id":27,"name":"7","odds":3.96,"type_id":1},{"code":"8","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":128,"isDelete":0,"isZM":1,"lastModifiedDate":1554008955000,"model_id":28,"name":"8","odds":3.96,"type_id":1},{"code":"9","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":129,"isDelete":0,"isZM":1,"lastModifiedDate":1554008956000,"model_id":29,"name":"9","odds":3.96,"type_id":1},{"code":"10","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":130,"isDelete":0,"isZM":1,"lastModifiedDate":1554008956000,"model_id":30,"name":"10","odds":3.96,"type_id":1},{"code":"11","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":131,"isDelete":0,"isZM":1,"lastModifiedDate":1554008957000,"model_id":31,"name":"11","odds":3.96,"type_id":1},{"code":"12","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":132,"isDelete":0,"isZM":1,"lastModifiedDate":1554008957000,"model_id":32,"name":"12","odds":3.96,"type_id":1},{"code":"13","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":133,"isDelete":0,"isZM":1,"lastModifiedDate":1554008958000,"model_id":33,"name":"13","odds":3.96,"type_id":1},{"code":"14","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":134,"isDelete":0,"isZM":1,"lastModifiedDate":1554008959000,"model_id":34,"name":"14","odds":3.96,"type_id":1},{"code":"15","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":135,"isDelete":0,"isZM":1,"lastModifiedDate":1554008959000,"model_id":35,"name":"15","odds":3.96,"type_id":1},{"code":"16","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":136,"isDelete":0,"isZM":1,"lastModifiedDate":1554008960000,"model_id":36,"name":"16","odds":3.96,"type_id":1},{"code":"17","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":137,"isDelete":0,"isZM":1,"lastModifiedDate":1554008960000,"model_id":37,"name":"17","odds":3.96,"type_id":1},{"code":"18","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":138,"isDelete":0,"isZM":1,"lastModifiedDate":1554008961000,"model_id":38,"name":"18","odds":3.96,"type_id":1},{"code":"19","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":139,"isDelete":0,"isZM":1,"lastModifiedDate":1554008961000,"model_id":39,"name":"19","odds":3.96,"type_id":1},{"code":"20","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":140,"isDelete":0,"isZM":1,"lastModifiedDate":1554008962000,"model_id":40,"name":"20","odds":3.96,"type_id":1},{"code":"21","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":141,"isDelete":0,"isZM":1,"lastModifiedDate":1554008962000,"model_id":41,"name":"21","odds":3.96,"type_id":1},{"code":"22","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":142,"isDelete":0,"isZM":1,"lastModifiedDate":1554008963000,"model_id":42,"name":"22","odds":3.96,"type_id":1},{"code":"23","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":143,"isDelete":0,"isZM":1,"lastModifiedDate":1554008964000,"model_id":43,"name":"23","odds":3.96,"type_id":1},{"code":"24","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":144,"isDelete":0,"isZM":1,"lastModifiedDate":1554008965000,"model_id":44,"name":"24","odds":3.96,"type_id":1},{"code":"25","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":145,"isDelete":0,"isZM":1,"lastModifiedDate":1554008965000,"model_id":45,"name":"25","odds":3.96,"type_id":1},{"code":"26","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":146,"isDelete":0,"isZM":1,"lastModifiedDate":1554008966000,"model_id":46,"name":"26","odds":3.96,"type_id":1},{"code":"27","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":147,"isDelete":0,"isZM":1,"lastModifiedDate":1554008966000,"model_id":47,"name":"27","odds":3.96,"type_id":1},{"code":"28","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":148,"isDelete":0,"isZM":1,"lastModifiedDate":1554008967000,"model_id":48,"name":"28","odds":3.96,"type_id":1},{"code":"29","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":149,"isDelete":0,"isZM":1,"lastModifiedDate":1554008967000,"model_id":49,"name":"29","odds":3.96,"type_id":1},{"code":"30","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":150,"isDelete":0,"isZM":1,"lastModifiedDate":1554008968000,"model_id":50,"name":"30","odds":3.96,"type_id":1},{"code":"31","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":151,"isDelete":0,"isZM":1,"lastModifiedDate":1554008969000,"model_id":51,"name":"31","odds":3.96,"type_id":1},{"code":"32","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":152,"isDelete":0,"isZM":1,"lastModifiedDate":1554008969000,"model_id":52,"name":"32","odds":3.96,"type_id":1},{"code":"33","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":153,"isDelete":0,"isZM":1,"lastModifiedDate":1554008970000,"model_id":53,"name":"33","odds":3.96,"type_id":1},{"code":"34","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":154,"isDelete":0,"isZM":1,"lastModifiedDate":1554008971000,"model_id":54,"name":"34","odds":3.96,"type_id":1},{"code":"35","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":155,"isDelete":0,"isZM":1,"lastModifiedDate":1554008971000,"model_id":55,"name":"35","odds":3.96,"type_id":1},{"code":"36","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":156,"isDelete":0,"isZM":1,"lastModifiedDate":1554008972000,"model_id":56,"name":"36","odds":3.96,"type_id":1},{"code":"37","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":157,"isDelete":0,"isZM":1,"lastModifiedDate":1554008972000,"model_id":57,"name":"37","odds":3.96,"type_id":1},{"code":"38","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":158,"isDelete":0,"isZM":1,"lastModifiedDate":1554008973000,"model_id":58,"name":"38","odds":3.96,"type_id":1},{"code":"39","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":159,"isDelete":0,"isZM":1,"lastModifiedDate":1554008973000,"model_id":59,"name":"39","odds":3.96,"type_id":1},{"code":"40","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":160,"isDelete":0,"isZM":1,"lastModifiedDate":1554008974000,"model_id":60,"name":"40","odds":3.96,"type_id":1},{"code":"41","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":161,"isDelete":0,"isZM":1,"lastModifiedDate":1554008975000,"model_id":61,"name":"41","odds":3.96,"type_id":1},{"code":"42","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":162,"isDelete":0,"isZM":1,"lastModifiedDate":1554008975000,"model_id":62,"name":"42","odds":3.96,"type_id":1},{"code":"43","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":163,"isDelete":0,"isZM":1,"lastModifiedDate":1554008976000,"model_id":63,"name":"43","odds":3.96,"type_id":1},{"code":"44","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":164,"isDelete":0,"isZM":1,"lastModifiedDate":1554008976000,"model_id":64,"name":"44","odds":3.96,"type_id":1},{"code":"45","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":165,"isDelete":0,"isZM":1,"lastModifiedDate":1554008977000,"model_id":65,"name":"45","odds":3.96,"type_id":1},{"code":"46","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":166,"isDelete":0,"isZM":1,"lastModifiedDate":1554008977000,"model_id":66,"name":"46","odds":3.96,"type_id":1},{"code":"47","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":167,"isDelete":0,"isZM":1,"lastModifiedDate":1554008978000,"model_id":67,"name":"47","odds":3.96,"type_id":1},{"code":"48","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":168,"isDelete":0,"isZM":1,"lastModifiedDate":1554008979000,"model_id":68,"name":"48","odds":3.96,"type_id":1},{"code":"49","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":169,"isDelete":0,"isZM":1,"lastModifiedDate":1554008979000,"model_id":69,"name":"49","odds":3.96,"type_id":1},{"code":"50","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":170,"isDelete":0,"isZM":1,"lastModifiedDate":1554008980000,"model_id":70,"name":"50","odds":3.96,"type_id":1},{"code":"51","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":171,"isDelete":0,"isZM":1,"lastModifiedDate":1554008981000,"model_id":71,"name":"51","odds":3.96,"type_id":1},{"code":"52","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":172,"isDelete":0,"isZM":1,"lastModifiedDate":1554008981000,"model_id":72,"name":"52","odds":3.96,"type_id":1},{"code":"53","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":173,"isDelete":0,"isZM":1,"lastModifiedDate":1554008982000,"model_id":73,"name":"53","odds":3.96,"type_id":1},{"code":"54","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":174,"isDelete":0,"isZM":1,"lastModifiedDate":1554008982000,"model_id":74,"name":"54","odds":3.96,"type_id":1},{"code":"55","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":175,"isDelete":0,"isZM":1,"lastModifiedDate":1554008983000,"model_id":75,"name":"55","odds":3.96,"type_id":1},{"code":"56","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":176,"isDelete":0,"isZM":1,"lastModifiedDate":1554008983000,"model_id":76,"name":"56","odds":3.96,"type_id":1},{"code":"57","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":177,"isDelete":0,"isZM":1,"lastModifiedDate":1554008984000,"model_id":77,"name":"57","odds":3.96,"type_id":1},{"code":"58","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":178,"isDelete":0,"isZM":1,"lastModifiedDate":1554008985000,"model_id":78,"name":"58","odds":3.96,"type_id":1},{"code":"59","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":179,"isDelete":0,"isZM":1,"lastModifiedDate":1554008985000,"model_id":79,"name":"59","odds":3.96,"type_id":1},{"code":"60","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":180,"isDelete":0,"isZM":1,"lastModifiedDate":1554008986000,"model_id":80,"name":"60","odds":3.96,"type_id":1},{"code":"61","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":181,"isDelete":0,"isZM":1,"lastModifiedDate":1554008987000,"model_id":81,"name":"61","odds":3.96,"type_id":1},{"code":"62","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":182,"isDelete":0,"isZM":1,"lastModifiedDate":1554008987000,"model_id":82,"name":"62","odds":3.96,"type_id":1},{"code":"63","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":183,"isDelete":0,"isZM":1,"lastModifiedDate":1554008988000,"model_id":83,"name":"63","odds":3.96,"type_id":1},{"code":"64","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":184,"isDelete":0,"isZM":1,"lastModifiedDate":1554008988000,"model_id":84,"name":"64","odds":3.96,"type_id":1},{"code":"65","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":185,"isDelete":0,"isZM":1,"lastModifiedDate":1554008989000,"model_id":85,"name":"65","odds":3.96,"type_id":1},{"code":"66","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":186,"isDelete":0,"isZM":1,"lastModifiedDate":1554008989000,"model_id":86,"name":"66","odds":3.96,"type_id":1},{"code":"67","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":187,"isDelete":0,"isZM":1,"lastModifiedDate":1554008990000,"model_id":87,"name":"67","odds":3.96,"type_id":1},{"code":"68","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":188,"isDelete":0,"isZM":1,"lastModifiedDate":1554008991000,"model_id":88,"name":"68","odds":3.96,"type_id":1},{"code":"69","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":189,"isDelete":0,"isZM":1,"lastModifiedDate":1554008991000,"model_id":89,"name":"69","odds":3.96,"type_id":1},{"code":"70","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":190,"isDelete":0,"isZM":1,"lastModifiedDate":1554008992000,"model_id":90,"name":"70","odds":3.96,"type_id":1},{"code":"71","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":191,"isDelete":0,"isZM":1,"lastModifiedDate":1554008992000,"model_id":91,"name":"71","odds":3.96,"type_id":1},{"code":"72","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":192,"isDelete":0,"isZM":1,"lastModifiedDate":1554008993000,"model_id":92,"name":"72","odds":3.96,"type_id":1},{"code":"73","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":193,"isDelete":0,"isZM":1,"lastModifiedDate":1554008993000,"model_id":93,"name":"73","odds":3.96,"type_id":1},{"code":"74","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":194,"isDelete":0,"isZM":1,"lastModifiedDate":1554008994000,"model_id":94,"name":"74","odds":3.96,"type_id":1},{"code":"75","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":195,"isDelete":0,"isZM":1,"lastModifiedDate":1554008995000,"model_id":95,"name":"75","odds":3.96,"type_id":1},{"code":"76","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":196,"isDelete":0,"isZM":1,"lastModifiedDate":1554008995000,"model_id":96,"name":"76","odds":3.96,"type_id":1},{"code":"77","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":197,"isDelete":0,"isZM":1,"lastModifiedDate":1554008996000,"model_id":97,"name":"77","odds":3.96,"type_id":1},{"code":"78","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":198,"isDelete":0,"isZM":1,"lastModifiedDate":1554008996000,"model_id":98,"name":"78","odds":3.96,"type_id":1},{"code":"79","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":199,"isDelete":0,"isZM":1,"lastModifiedDate":1554008997000,"model_id":99,"name":"79","odds":3.96,"type_id":1},{"code":"80","createdDate":1536820439000,"createdUser":"sys","group_id":4,"groupname":Utils.getString(R.string.正码),"id":200,"isDelete":0,"isZM":1,"lastModifiedDate":1554008997000,"model_id":100,"name":"80","odds":3.96,"type_id":1}],"isZM":1}]
     * message : 操作成功
     * status : success
     */

    private String message;
    private String status;
    private List<RulelistAllBean> rulelistAll;

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

    public List<RulelistAllBean> getRulelistAll() {
        return rulelistAll;
    }

    public void setRulelistAll(List<RulelistAllBean> rulelistAll) {
        this.rulelistAll = rulelistAll;
    }

    public static class RulelistAllBean {
        /**
         * Rulelist : [{"code":"heda","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":101,"isDelete":0,"isZM":0,"lastModifiedDate":1554019134000,"model_id":1,"name":Utils.getString(R.string.总和大),"odds":1.98,"type_id":1},{"code":"hexiao","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":102,"isDelete":0,"isZM":0,"lastModifiedDate":1554019134000,"model_id":2,"name":Utils.getString(R.string.总和小),"odds":1.98,"type_id":1},{"code":"hedan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":103,"isDelete":0,"isZM":0,"lastModifiedDate":1554019135000,"model_id":3,"name":Utils.getString(R.string.总和单),"odds":1.98,"type_id":1},{"code":"hesh","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":104,"isDelete":0,"isZM":0,"lastModifiedDate":1554019135000,"model_id":4,"name":Utils.getString(R.string.总和双),"odds":1.98,"type_id":1},{"code":"he810","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":105,"isDelete":0,"isZM":0,"lastModifiedDate":1554020889000,"model_id":5,"name":Utils.getString(R.string.总和810),"odds":108.9,"type_id":1},{"code":"dadan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":106,"isDelete":0,"isZM":0,"lastModifiedDate":1554019136000,"model_id":6,"name":Utils.getString(R.string.总大单),"odds":3.96,"type_id":1},{"code":"dash","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":107,"isDelete":0,"isZM":0,"lastModifiedDate":1554019136000,"model_id":7,"name":Utils.getString(R.string.总大双),"odds":3.96,"type_id":1},{"code":"xiaodan","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":108,"isDelete":0,"isZM":0,"lastModifiedDate":1554019137000,"model_id":8,"name":Utils.getString(R.string.总小单),"odds":3.96,"type_id":1},{"code":"xiaosh","createdDate":1536820439000,"createdUser":"sys","group_id":1,"groupname":Utils.getString(R.string.总和),"id":109,"isDelete":0,"isZM":0,"lastModifiedDate":1554019137000,"model_id":9,"name":Utils.getString(R.string.总小双),"odds":3.96,"type_id":1},{"code":"qiand","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":110,"isDelete":0,"isZM":0,"lastModifiedDate":1554017388000,"model_id":10,"name":Utils.getString(R.string.前(多)),"odds":1.98,"type_id":1},{"code":"houd","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":111,"isDelete":0,"isZM":0,"lastModifiedDate":1554017388000,"model_id":11,"name":Utils.getString(R.string.后(多)),"odds":1.98,"type_id":1},{"code":"qianhouh","createdDate":1536820439000,"createdUser":"sys","group_id":0,"groupname":Utils.getString(R.string.前后和),"id":112,"isDelete":0,"isZM":0,"lastModifiedDate":1554017389000,"model_id":12,"name":Utils.getString(R.string.前后(和)),"odds":1.98,"type_id":1},{"code":"dand","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":113,"isDelete":0,"isZM":0,"lastModifiedDate":1554017404000,"model_id":13,"name":Utils.getString(R.string.单(多)),"odds":1.98,"type_id":1},{"code":"shd","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":114,"isDelete":0,"isZM":0,"lastModifiedDate":1554017404000,"model_id":14,"name":Utils.getString(R.string.双(多)),"odds":1.98,"type_id":1},{"code":"danshd","createdDate":1536820439000,"createdUser":"sys","group_id":2,"groupname":Utils.getString(R.string.单双和),"id":115,"isDelete":0,"isZM":0,"lastModifiedDate":1554017405000,"model_id":15,"name":Utils.getString(R.string.单双(和)),"odds":1.98,"type_id":1},{"code":"jin","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":116,"isDelete":0,"isZM":0,"lastModifiedDate":1554021050000,"model_id":16,"name":Utils.getString(R.string.金),"odds":9.227,"type_id":1},{"code":"mu","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":117,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":17,"name":Utils.getString(R.string.木),"odds":4.623,"type_id":1},{"code":"shui","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":118,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":18,"name":Utils.getString(R.string.水),"odds":2.416,"type_id":1},{"code":"huo","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":119,"isDelete":0,"isZM":0,"lastModifiedDate":1554021051000,"model_id":19,"name":Utils.getString(R.string.火),"odds":4.623,"type_id":1},{"code":"tu","createdDate":1536820439000,"createdUser":"sys","group_id":3,"groupname":Utils.getString(R.string.五行),"id":120,"isDelete":0,"isZM":0,"lastModifiedDate":1554021052000,"model_id":20,"name":Utils.getString(R.string.土),"odds":9.227,"type_id":1}]
         * isZM : 0
         */

        private int isZM;
        private List<RulelistBean> Rulelist;

        public int getIsZM() {
            return isZM;
        }

        public void setIsZM(int isZM) {
            this.isZM = isZM;
        }

        public List<RulelistBean> getRulelist() {
            return Rulelist;
        }

        public void setRulelist(List<RulelistBean> Rulelist) {
            this.Rulelist = Rulelist;
        }

        public static class RulelistBean {
            /**
             * code : heda
             * createdDate : 1536820439000
             * createdUser : sys
             * group_id : 1
             * groupname : 总和
             * id : 101
             * isDelete : 0
             * isZM : 0
             * lastModifiedDate : 1554019134000
             * model_id : 1
             * name : 总和大
             * odds : 1.98
             * type_id : 1
             */

            private String code;
            private long createdDate;
            private String createdUser;
            private int group_id;
            private String groupname;
            private long id;
            private int isDelete;
            private int isZM;
            private long lastModifiedDate;
            private int model_id;
            private String name;
            private BigDecimal odds;
            private int type_id;

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

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public int getIsZM() {
                return isZM;
            }

            public void setIsZM(int isZM) {
                this.isZM = isZM;
            }

            public long getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(long lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public BigDecimal getOdds() {
                return odds;
            }

            public void setOdds(BigDecimal odds) {
                this.odds = odds;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }
        }
    }
}
