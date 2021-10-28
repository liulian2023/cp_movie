package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;


import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

/**
 * WebServiceVariable.java 存放webservice使用的常量.
 * 
 * @author cly
 * @since JDK 1.
 * @see
 */
public class WebServiceConstant{
    
    /**
     * CODE_DEFAULT:默认状态00成功.
     */
    public static String CODE_DEFAULT="00";//
    /**
     * CODE_ERROR_99:token校验不成功.
     */
    public static String CODE_ERROR_99="99";//token校验不成功
    
    /**
     * CODE_ERROR_404:数据不存在.
     */
    public static String CODE_ERROR_404="404";
    
    /**
     * CODE_ERROR_405:无可用票数.
     */
    public static String CODE_ERROR_405="405";
    
    /**
     * CODE_ERROR_406:无可用票数(冻结票据).
     */
    public static String CODE_ERROR_406="406";
    
    /**
     * CODE_ERROR_407:无可用票数(解冻票据).
     */
    public static String CODE_ERROR_407="407";
    
    /**
     * CODE_ERROR_408:查无与资源编码匹配的产品.
     */
    public static String CODE_ERROR_408="408";
    
    /**
     * CODE_ERROR_409:票据状态为已检票，无法冻结.
     */
    public static String CODE_ERROR_409="409";
    
    /**
     * CODE_ERROR_410:该票据已使用.
     */
    public static String CODE_ERROR_410="410";
    
    /**
     * CODE_ERROR_411:该票据已退票.
     */
    public static String CODE_ERROR_411="411";
    
    /**
     * CODE_ERROR_501:产品不在可验票时间范围内.
     */
    public static String CODE_ERROR_501="501";
    
    /**
     * CODE_ERROR_502:串码无效
     */
    public static String CODE_ERROR_502="502";
    /**
     * CODE_ERROR_503: 支付失败
     */
    public static String CODE_ERROR_503="503";
    
    /**
     * CODE_ERROR_504:无订单信息
     */
    public static String CODE_ERROR_504="504";
    
    /**
     * CODE_ERROR_505:票据无效
     */
    public static String CODE_ERROR_505="505";
    
    /**
     * CODE_ERROR_506：票据不存在
     */
    public static String CODE_ERROR_506="506";
    
    
    /**
     * CODE_ERROR_MINUS_1:非空参数有空值.
     */
    public static String CODE_ERROR_MINUS_1="-1";//非空参数有空值
    /**
     * CODE_ERROR_MINUS_2:系统异常状态.
     */
    public static String CODE_ERROR_MINUS_2="-2";//系统异常状态
    /**
     * CODE_ERROR_MINUS_3:系统异常状态.
     */
    public static String CODE_ERROR_MINUS_3="-3";//非可用IP
    
    /**
     * INFO_DEFAULT:默认信息正确返回.
     */
    public static String INFO_DEFAULT=Utils.getString(R.string.正确返回);//默认信息正确返回
    
    /**
     * STATE_TRUE:接口状态值true.
     */
    public static String STATE_TRUE="true";//接口状态true
    /**
     * STATE_FALSE:接口状态false.
     */
    public static String STATE_FALSE="false";//接口状态false
    
    /**
     * STATE_INFO_SUCCESS:默认信息操作成功.
     */
    public static String STATE_INFO_SUCCESS=Utils.getString(R.string.操作成功);//默认信息操作成功
    /**
     * STATE_INFO_FAIL:操作失败.
     */
    public static String STATE_INFO_FAIL=Utils.getString(R.string.操作失败);//操作失败
    /**
     * STATE_INFO_EMPTY:请求参数不能为空.
     */

    /**
     * STATE_INFO_IP_NOT_FOUND:请求参数不能为空.
     */

    /**
     * ERROR_CODE_TOKEN_WRONGFUL:token校验失败.
     */

    /**
     * ERROR_CODE_SYSTEM_EXCEPTION:系统出现异常.
     */
    /**
     * ERROR_CODE_REQUIRED_DATA_IS_NULL:非空参数有空值.
     */
    /**
     * ERROR_CODE_NOT_FIND:数据不存在.
     */
    /**
     * ERROR_TICKET_NOT_FOUND:票据不存在！
     */

    /**
     * ERROR_MESSAGE_BODY_IS_NULL:消息体不能为空.
     */

    /**
     * ERROR_MESSAGE_TICKET_NOT_ENOUGH:剩余票数不足无法检票.
     */

    /**
     * ERROR_MESSAGE_TICKET_NOT_ENOUGH:剩余票数不足无法冻结.
     */

    /**
     * ERROR_MESSAGE_TICKET_NOT_ENOUGH:票据状态为已检票，无法冻结.
     */

    /**
     * ERROR_MESSAGE_TICKET_CAN_NOT_UNLOCK:冻结票数不足无法解冻.
     */

    /**
     * ERROR_MESSAGE_RODERS_NOT_FOUND:订单不存在
     */

    /**
     * ERROR_MESSAGE_RESOURCECODE_NOT_FOUND:资源编码不存在
     */

    /**
     * ERROR_MESSAGE_PRODUCT_NOT_IN_VALIDTIME:该产品不在可验票时间范围内
     */

    /**
     * ERROR_MESSAGE_PRODUCT_NOT_IN_RESOURCE:查无与资源编码匹配的产品
     */

    /**
     * ERROR_MESSAGE_PRODUCT_NOT_EFFECTIVE:该产品未生效！
     */

    /**
     * ERROR_MESSAGE_PRODUCT_OVERDUE:该产品已过期
     */

    /**
     * ERROR_MESSAGE_TICKET_HAVEUSE:该票据已使用
     */

    /**
     * ERROR_MESSAGE_TICKET_REFUND:票据已退票
     */

    /**
     * ERROR_MESSAGE_TICKET_OVERDUE:该票据已过期！
     */

    /**
     * ERROR_MESSAGE_TICKET_CODE_HAVEUSED:该串码已使用！
     */

    /**
     * ERROR_MESSAGE_TICKET_CODE_OVERDUE:该串码已过期
     */

    /**
     * ERROR_MESSAGE_TICKET_CODE_NOTFOUND:该串码不存在
     */

    /**
     * ERROR_MESSAGE_TICKET_CODE_INVALID:串码无效
     */
    /**
     * ERROR_MESSAGE_PAY_FAIL:支付失败
     */

    /**
     * ERROR_MESSAGE_IDCARD_NOTFOUND:
     */

    /**
     * ERROR_MESSAGE_TICKET_NUMBER_INVALID:票据无效
     */

}