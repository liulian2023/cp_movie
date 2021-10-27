package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES对称加密和解密，有偏移量
 * @author tyg
 * @date   2018年6月28日下午12:48:01
 */
@SuppressWarnings("restriction")
public class AESUtil {

    // 密匙
    private static final String KEY = "qV7H9B@X3m3iZYTe";
    // 偏移量
    private static final String OFFSET = "25!Kb$yPfR^17Xr2";
    // 编码
    private static final String ENCODING = "UTF-8";
    //算法
    private static final String ALGORITHM = "AES";
    // 默认的加密算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";


    public static String encrypt(String cleartext) {
        return encrypt(cleartext.getBytes());
    }
    /**
     * 加密
     * @param cleartext 需加密字段
     * @return
     */
    public static String encrypt(byte[] cleartext){
        IvParameterSpec zeroIv = new IvParameterSpec(OFFSET.getBytes());
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(cleartext);
            return new String(android.util.Base64.encode(encryptedData, Base64.DEFAULT));
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (BadPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (InvalidAlgorithmParameterException e) {
        }
        return null;
    }

    /**
     * 解密
     *
     * @param data
     * @return String
     * @throws Exception
     * @author tyg
     * @date 2018年6月28日下午2:50:43
     */
    public static String decrypt(String data) throws Exception {
        if(StringMyUtil.isEmptyString(data)){
            return "";
        }
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
   /*     byte[] buffer =Base64.decodeBase64(data);
        byte[] encrypted = cipher.doFinal(buffer);*/
        byte[] bytes = decodeBase64String(data);
        byte[] encrypted = cipher.doFinal(bytes);
        return new String(encrypted, ENCODING);//此处使用BASE64做转码。
    }
    public static byte[] decodeBase64String(String input) throws UnsupportedEncodingException {
        if(StringMyUtil.isEmptyString(input)){
            return null;
        }
        byte[] decode = android.util.Base64.decode(input.getBytes(ENCODING), android.util.Base64.NO_WRAP);
        return decode;
        //return new String(Base64.decodeBase64(input),"utf-8");
    }

}