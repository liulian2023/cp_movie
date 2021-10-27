package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /*
     * 获取昨天的时间
     */
    public static Date getYesterdayDate(Date date){
//        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Calendar getYesterdayCalendar(Date date){
//        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar;
    }

    public  static String formatTime(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(Long.parseLong(StringMyUtil.isEmptyString(time)?"0":time));
        return format;
    }
    /*
     * 获取明天的时间
     */
    public static Date getTomorrowDate(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();
    }
    public static Calendar getTomorrowCalendar(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar;
    }

    /*
    获取今天的Calendar对象
     */
    public static Calendar getTodayCalendar(Date date){
//        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,0);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar;
    }
    /*
     * 获取指定天的起始时间
     */
    public static Date getStartTime(Calendar day) {
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }
    /*
     * 获取指定天的结束时间
     */
    public static Date getEndTime(Calendar day) {
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        return day.getTime();
    }


    /**
     * 获取指定日期所在月份开始的时间戳
     * @param date 指定日期
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTime();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 指定日期
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTime();
    }


    // 获得本周一0点时间
    @SuppressLint("WrongConstant")
    public static Date getWeekTimeStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }
    // 获得本周日24点时间
    public static Date getWeekTimeEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekTimeStart());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }


    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);//+后 -前
        return now.getTime();
    }
    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);//+后 -前
        return now.getTime();
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!TextUtils.isEmpty(s)){
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
        }
        return res;
    }

    /*
     * 将时间戳转(毫秒)换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间戳转换为倒计时格式时间
     */
    public static String stampToCountDown(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = Long.valueOf(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为年月日格式时间
     */
    public static String stampToY_M_D(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String timeMode(int num){

        String str=""+num;
        if (Utils.LengthNum(num)<=1){
            str = "0"+num;
        }
        return str;
    }
}
