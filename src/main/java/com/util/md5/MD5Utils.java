package com.util.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类名称：MD5Utils<br>
 * 类描述：md5加密工具类
 * @author squirrel
 * @date 2019-02-15
 *
 */
public class MD5Utils {

    private MD5Utils(){}

    /**
     *
     * @param plainText
     * @return
     */
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        String name = "squirrel";
        String job = "coder";
        StringBuffer sb = new StringBuffer();
        sb.append(name).append("&").append(job);
        String md5 = MD5Utils.stringToMD5(sb.toString());
        System.out.println(md5);
    }
}
