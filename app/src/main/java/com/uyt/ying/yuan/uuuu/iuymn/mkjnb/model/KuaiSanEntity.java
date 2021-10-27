/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import java.math.BigDecimal;
import java.util.List;

public class KuaiSanEntity {


    /**
     * gameRulelist : [{"code":"da","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4627,"img_url":"","isDelete":0,"lastModifiedDate":1571730049000,"model_id":1090,"name":Utils.getString(R.string.大),"odds":1.98,"play":0,"type_id":1},{"code":"xiao","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4628,"img_url":"","isDelete":0,"lastModifiedDate":1571730049000,"model_id":1091,"name":Utils.getString(R.string.小),"odds":1.98,"play":0,"type_id":1},{"code":"dan","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4629,"img_url":"","isDelete":0,"lastModifiedDate":1571730049000,"model_id":1100,"name":Utils.getString(R.string.单),"odds":1.98,"play":0,"type_id":1},{"code":"sh","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4630,"img_url":"","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1101,"name":Utils.getString(R.string.双),"odds":1.98,"play":0,"type_id":1},{"code":"1","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4631,"img_url":"upload/images/20190912/1568277358595.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1102,"name":"1","odds":2.346,"play":0,"type_id":1},{"code":"2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4632,"img_url":"upload/images/20190912/1568277336435.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1103,"name":"2","odds":2.346,"play":0,"type_id":1},{"code":"3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4633,"img_url":"upload/images/20190912/1568277319542.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1104,"name":"3","odds":2.346,"play":0,"type_id":1},{"code":"4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4634,"img_url":"upload/images/20190912/1568277306971.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1105,"name":"4","odds":2.346,"play":0,"type_id":1},{"code":"5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4635,"img_url":"upload/images/20190912/1568277289081.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1106,"name":"5","odds":2.346,"play":0,"type_id":1},{"code":"6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":1,"groupname":Utils.getString(R.string.三军、大小),"id":4636,"img_url":"upload/images/20190912/1568277274916.png","isDelete":0,"lastModifiedDate":1571730050000,"model_id":1107,"name":"6","odds":2.346,"play":0,"type_id":1},{"code":"1_1_1","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4637,"img_url":"upload/images/20190912/1568277258704.png","isDelete":0,"model_id":1108,"name":"1_1_1","odds":213.84,"play":0,"type_id":1},{"code":"2_2_2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4638,"img_url":"upload/images/20190912/1568277247607.png","isDelete":0,"model_id":1109,"name":"2_2_2","odds":213.84,"play":0,"type_id":1},{"code":"3_3_3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4639,"img_url":"upload/images/20190912/1568277230308.png","isDelete":0,"model_id":1110,"name":"3_3_3","odds":213.84,"play":0,"type_id":1},{"code":"4_4_4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4640,"img_url":"upload/images/20190912/1568277216258.png","isDelete":0,"model_id":1111,"name":"4_4_4","odds":213.84,"play":0,"type_id":1},{"code":"5_5_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4641,"img_url":"upload/images/20190912/1568277190299.png","isDelete":0,"model_id":1112,"name":"5_5_5","odds":213.84,"play":0,"type_id":1},{"code":"6_6_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":2,"groupname":Utils.getString(R.string.围骰、全骰),"id":4642,"img_url":"upload/images/20190912/1568277147337.png","isDelete":0,"model_id":1113,"name":"6_6_6","odds":213.84,"play":0,"type_id":1},{"code":"4dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4643,"isDelete":0,"model_id":1114,"name":Utils.getString(R.string.4点),"odds":71.28,"play":0,"type_id":1},{"code":"5dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4644,"isDelete":0,"model_id":1115,"name":Utils.getString(R.string.5点),"odds":35.64,"play":0,"type_id":1},{"code":"6dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4645,"isDelete":0,"model_id":1116,"name":Utils.getString(R.string.6点),"odds":21.384,"play":0,"type_id":1},{"code":"7dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4646,"isDelete":0,"model_id":1117,"name":Utils.getString(R.string.7点),"odds":14.256,"play":0,"type_id":1},{"code":"8dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4647,"isDelete":0,"model_id":1118,"name":Utils.getString(R.string.8点),"odds":10.187,"play":0,"type_id":1},{"code":"9dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4648,"isDelete":0,"model_id":1119,"name":Utils.getString(R.string.9点),"odds":8.554,"play":0,"type_id":1},{"code":"10dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4649,"isDelete":0,"model_id":1120,"name":Utils.getString(R.string.10点),"odds":7.92,"play":0,"type_id":1},{"code":"11dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4650,"isDelete":0,"model_id":1121,"name":Utils.getString(R.string.11点),"odds":7.92,"play":0,"type_id":1},{"code":"12dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4651,"isDelete":0,"model_id":1122,"name":Utils.getString(R.string.12点),"odds":8.554,"play":0,"type_id":1},{"code":"13dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4652,"isDelete":0,"model_id":1123,"name":Utils.getString(R.string.13点),"odds":10.187,"play":0,"type_id":1},{"code":"14dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4653,"isDelete":0,"model_id":1124,"name":Utils.getString(R.string.14点),"odds":14.256,"play":0,"type_id":1},{"code":"15dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4654,"isDelete":0,"model_id":1125,"name":Utils.getString(R.string.15点),"odds":21.384,"play":0,"type_id":1},{"code":"16dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4655,"isDelete":0,"model_id":1126,"name":Utils.getString(R.string.16点),"odds":35.64,"play":0,"type_id":1},{"code":"17dian","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":3,"groupname":Utils.getString(R.string.点数),"id":4656,"isDelete":0,"model_id":1127,"name":Utils.getString(R.string.17点),"odds":71.28,"play":0,"type_id":1},{"code":"1_2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4657,"img_url":"upload/images/20190912/1568277544243.png","isDelete":0,"model_id":1128,"name":"1_2","odds":7.128,"play":0,"type_id":1},{"code":"1_3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4658,"img_url":"upload/images/20190912/1568277534786.png","isDelete":0,"model_id":1129,"name":"1_3","odds":7.128,"play":0,"type_id":1},{"code":"1_4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4659,"img_url":"upload/images/20190912/1568277524497.png","isDelete":0,"model_id":1130,"name":"1_4","odds":7.128,"play":0,"type_id":1},{"code":"1_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4660,"img_url":"upload/images/20190912/1568277515212.png","isDelete":0,"model_id":1131,"name":"1_5","odds":7.128,"play":0,"type_id":1},{"code":"1_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4661,"img_url":"upload/images/20190912/1568277505103.png","isDelete":0,"model_id":1132,"name":"1_6","odds":7.128,"play":0,"type_id":1},{"code":"2_3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4662,"img_url":"upload/images/20190912/1568277489806.png","isDelete":0,"model_id":1133,"name":"2_3","odds":7.128,"play":0,"type_id":1},{"code":"2_4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4663,"img_url":"upload/images/20190912/1568277479338.png","isDelete":0,"model_id":1134,"name":"2_4","odds":7.128,"play":0,"type_id":1},{"code":"2_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4664,"img_url":"upload/images/20190912/1568277466484.png","isDelete":0,"model_id":1135,"name":"2_5","odds":7.128,"play":0,"type_id":1},{"code":"2_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4665,"img_url":"upload/images/20190912/1568277455815.png","isDelete":0,"model_id":1136,"name":"2_6","odds":7.128,"play":0,"type_id":1},{"code":"3_4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4666,"img_url":"upload/images/20190912/1568277445174.png","isDelete":0,"model_id":1137,"name":"3_4","odds":7.128,"play":0,"type_id":1},{"code":"3_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4667,"img_url":"upload/images/20190912/1568277435593.png","isDelete":0,"model_id":1138,"name":"3_5","odds":7.128,"play":0,"type_id":1},{"code":"3_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4668,"img_url":"upload/images/20190912/1568277426037.png","isDelete":0,"model_id":1139,"name":"3_6","odds":7.128,"play":0,"type_id":1},{"code":"4_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4669,"img_url":"upload/images/20190912/1568277415738.png","isDelete":0,"model_id":1140,"name":"4_5","odds":7.128,"play":0,"type_id":1},{"code":"4_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4670,"img_url":"upload/images/20190912/1568277405421.png","isDelete":0,"model_id":1141,"name":"4_6","odds":7.128,"play":0,"type_id":1},{"code":"5_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":4,"groupname":Utils.getString(R.string.长牌),"id":4671,"img_url":"upload/images/20190912/1568277392844.png","isDelete":0,"model_id":1142,"name":"5_6","odds":7.128,"play":0,"type_id":1},{"code":"1_1","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4672,"img_url":"upload/images/20190912/1568272132635.png","isDelete":0,"model_id":1143,"name":"1_1","odds":13.365,"play":0,"type_id":1},{"code":"2_2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4673,"img_url":"upload/images/20190912/1568272116843.png","isDelete":0,"model_id":1144,"name":"2_2","odds":13.365,"play":0,"type_id":1},{"code":"3_3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4674,"img_url":"upload/images/20190912/1568270058443.png","isDelete":0,"model_id":1145,"name":"3_3","odds":13.365,"play":0,"type_id":1},{"code":"4_4","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4675,"img_url":"upload/images/20190912/1568270045922.png","isDelete":0,"model_id":1146,"name":"4_4","odds":13.365,"play":0,"type_id":1},{"code":"5_5","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4676,"img_url":"upload/images/20190912/1568270031021.png","isDelete":0,"model_id":1147,"name":"5_5","odds":13.365,"play":0,"type_id":1},{"code":"6_6","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":5,"groupname":Utils.getString(R.string.短牌),"id":4677,"img_url":"upload/images/20190912/1568270006210.png","isDelete":0,"model_id":1148,"name":"6_6","odds":13.365,"play":0,"type_id":1}]
     * gameRulelisttwo : [{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4678,"isDelete":0,"model_id":1149,"name":Utils.getString(R.string.大),"odds":1.98,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4679,"isDelete":0,"model_id":1150,"name":Utils.getString(R.string.小),"odds":1.98,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4680,"isDelete":0,"model_id":1151,"name":Utils.getString(R.string.单),"odds":1.98,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4681,"isDelete":0,"model_id":1152,"name":Utils.getString(R.string.双),"odds":1.98,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4682,"isDelete":0,"model_id":1153,"name":"3","odds":213.84,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4683,"isDelete":0,"model_id":1154,"name":"4","odds":71.28,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4684,"isDelete":0,"model_id":1155,"name":"5","odds":35.64,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4685,"isDelete":0,"model_id":1156,"name":"6","odds":21.384,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4686,"isDelete":0,"model_id":1157,"name":"7","odds":14.256,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4687,"isDelete":0,"model_id":1158,"name":"8","odds":10.187,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4688,"isDelete":0,"model_id":1159,"name":"9","odds":8.554,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4689,"isDelete":0,"model_id":1160,"name":"10","odds":7.92,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4690,"isDelete":0,"model_id":1161,"name":"11","odds":7.92,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4691,"isDelete":0,"model_id":1162,"name":"12","odds":8.554,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4692,"isDelete":0,"model_id":1163,"name":"13","odds":10.187,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4693,"isDelete":0,"model_id":1164,"name":"14","odds":14.256,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4694,"isDelete":0,"model_id":1165,"name":"15","odds":21.384,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4695,"isDelete":0,"model_id":1166,"name":"16","odds":35.64,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4696,"isDelete":0,"model_id":1167,"name":"17","odds":71.28,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":6,"groupname":Utils.getString(R.string.和值),"id":4697,"isDelete":0,"model_id":1168,"name":"18","odds":213.84,"play":1,"type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":7,"groupname":Utils.getString(R.string.三同号通选),"id":4698,"isDelete":0,"model_id":1238,"name":Utils.getString(R.string.三同号通选),"odds":35.64,"play":1,"type_id":1},{"code":"1","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":8,"groupname":Utils.getString(R.string.三同号单选),"id":4699,"isDelete":0,"model_id":1239,"name":Utils.getString(R.string.三同号单选),"odds":213.84,"play":1,"remark":"1_1_1,2_2_2,3_3_3,4_4_4,5_5_5,6_6_6","type_id":1},{"code":"0","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":9,"groupname":Utils.getString(R.string.三连号通选),"id":4700,"isDelete":0,"model_id":1245,"name":Utils.getString(R.string.三连号通选),"odds":8.91,"play":1,"type_id":1},{"code":"3","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":10,"groupname":Utils.getString(R.string.三不同号),"id":4701,"isDelete":0,"model_id":1251,"name":Utils.getString(R.string.三不同号),"odds":35.64,"play":1,"remark":"1,2,3,4,5,6","type_id":1},{"code":"1","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":11,"groupname":Utils.getString(R.string.二同号复选),"id":4702,"isDelete":0,"model_id":1252,"name":Utils.getString(R.string.二同号复选),"odds":14.256,"play":1,"remark":"1_1,2_2,3_3,4_4,5_5,6_6","type_id":1},{"code":"2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":12,"groupname":Utils.getString(R.string.二同号单选),"id":4703,"isDelete":0,"model_id":1253,"name":Utils.getString(R.string.二同号单选),"odds":71.28,"play":1,"remark":"1_1,2_2,3_3,4_4,5_5,6_6,1,2,3,4,5,6","type_id":1},{"code":"2","createdDate":1568277568000,"createdUser":"xbgoogle","group_id":13,"groupname":Utils.getString(R.string.二不同号),"id":4704,"isDelete":0,"model_id":1254,"name":Utils.getString(R.string.二不同号),"odds":7.128,"play":1,"remark":"1,2,3,4,5,6","type_id":1}]
     * grouplisttwo : [{"oddsMin":1.98,"group_id":6,"groupname":Utils.getString(R.string.和值),"odds":213.84},{"oddsMin":35.64,"group_id":7,"groupname":Utils.getString(R.string.三同号通选),"odds":35.64},{"oddsMin":213.84,"group_id":8,"groupname":Utils.getString(R.string.三同号单选),"odds":213.84},{"oddsMin":8.91,"group_id":9,"groupname":Utils.getString(R.string.三连号通选),"odds":8.91},{"oddsMin":35.64,"group_id":10,"groupname":Utils.getString(R.string.三不同号),"odds":35.64},{"oddsMin":14.256,"group_id":11,"groupname":Utils.getString(R.string.二同号复选),"odds":14.256},{"oddsMin":71.28,"group_id":12,"groupname":Utils.getString(R.string.二同号单选),"odds":71.28},{"oddsMin":7.128,"group_id":13,"groupname":Utils.getString(R.string.二不同号),"odds":7.128}]
     * grouplist : [{"group_id":1,"groupname":Utils.getString(R.string.三军、大小)},{"group_id":2,"groupname":Utils.getString(R.string.围骰、全骰)},{"group_id":3,"groupname":Utils.getString(R.string.点数)},{"group_id":4,"groupname":Utils.getString(R.string.长牌)},{"group_id":5,"groupname":Utils.getString(R.string.短牌)}]
     * message : 操作成功
     * status : success
     */

    private String message;
    private String status;
    private List<GameRulelistBean> gameRulelist;
    private List<GameRulelisttwoBean> gameRulelisttwo;
    private List<GrouplisttwoBean> grouplisttwo;
    private List<GrouplistBean> grouplist;

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

    public List<GameRulelistBean> getGameRulelist() {
        return gameRulelist;
    }

    public void setGameRulelist(List<GameRulelistBean> gameRulelist) {
        this.gameRulelist = gameRulelist;
    }

    public List<GameRulelisttwoBean> getGameRulelisttwo() {
        return gameRulelisttwo;
    }

    public void setGameRulelisttwo(List<GameRulelisttwoBean> gameRulelisttwo) {
        this.gameRulelisttwo = gameRulelisttwo;
    }

    public List<GrouplisttwoBean> getGrouplisttwo() {
        return grouplisttwo;
    }

    public void setGrouplisttwo(List<GrouplisttwoBean> grouplisttwo) {
        this.grouplisttwo = grouplisttwo;
    }

    public List<GrouplistBean> getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(List<GrouplistBean> grouplist) {
        this.grouplist = grouplist;
    }

    public static class GameRulelistBean {
        /**
         * code : da
         * createdDate : 1568277568000
         * createdUser : xbgoogle
         * group_id : 1
         * groupname : 三军、大小
         * id : 4627
         * img_url :
         * isDelete : 0
         * lastModifiedDate : 1571730049000
         * model_id : 1090
         * name : 大
         * odds : 1.98
         * play : 0
         * type_id : 1
         */

        private String code;
        private long createdDate;
        private String createdUser;
        private int group_id;
        private String groupname;
        private long id;
        private String img_url;
        private int isDelete;
        private long lastModifiedDate;
        private int model_id;
        private String name;
        private BigDecimal odds;
        private int play;
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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
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

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }
    }

    public static class GameRulelisttwoBean {
        /**
         * code : 0
         * createdDate : 1568277568000
         * createdUser : xbgoogle
         * group_id : 6
         * groupname : 和值
         * id : 4678
         * isDelete : 0
         * model_id : 1149
         * name : 大
         * odds : 1.98
         * play : 1
         * type_id : 1
         * remark : 1_1_1,2_2_2,3_3_3,4_4_4,5_5_5,6_6_6
         */

        private String code;
        private long createdDate;
        private String createdUser;
        private int group_id;
        private String groupname;
        private long id;
        private int isDelete;
        private int model_id;
        private String name;
        private double odds;
        private int play;
        private int type_id;
        private String remark;

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

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class GrouplisttwoBean {
        /**
         * oddsMin : 1.98
         * group_id : 6
         * groupname : 和值
         * odds : 213.84
         */

        private double oddsMin;
        private int group_id;
        private String groupname;
        private double odds;

        public double getOddsMin() {
            return oddsMin;
        }

        public void setOddsMin(double oddsMin) {
            this.oddsMin = oddsMin;
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

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }
    }

    public static class GrouplistBean {
        /**
         * group_id : 1
         * groupname : 三军、大小
         */

        private int group_id;
        private String groupname;

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
    }
}
