package com.util.sign;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 类名称: SignUtils
 * 类描述: 加签、验签工具类
 * @author squirrel
 * @date 2019-04-18
 */
public class SignUtils {

    private SignUtils() {}

    /**
     * 加签类型: RSA
     */
    private static final String SIGN_TYPE_RSA = "RSA";

    /**
     * 加签类型: RSA2
     */
    private static final String SIGN_TYPE_RSA2 = "RSA2";

    /**
     * SHA1安全哈希算法
     */
    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA2安全哈希算法
     */
    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    /**
     * 内容加签关键字: sign
     */
    private static final String CONTENT_SIGN_KEY = "sign";

    /**
     * 内容加签类型关键字: signType
     */
    private static final String CONTENT_SIGN_TYPE_KEY = "signType";

    /**
     * 拼接加签数据
     * @param sortedParams {@link Map}
     * @return 拼接后的数据
     */
    private static String getSignContent(Map<String, String> sortedParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            String value = sortedParams.get(key);
            if (!Objects.isNull(key) && !Objects.isNull(value)) {
                content.append(index == 0 ? "" : "&");
                content.append(key);
                content.append("=");
                content.append(value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * rsa内容签名
     * @param content 加签内容
     * @param privateKey 私钥
     * @param charset 编码格式
     * @param signType 加签类型
     * @return 加签结果
     * @throws SignatureException 异常
     */
    private static String rsaSign(String content, String privateKey, String charset, String signType) throws SignatureException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            Signature signature = getsignatureBySignType(signType);
            signature.initSign(priKey);
            if (Objects.isNull(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    /**
     * RSA 加签
     * @param params 待加签数据
     * @param privateKey 私钥
     * @param charset 编码格式
     * @return 加签结果
     * @throws SignatureException 异常
     */
    private static String rsaSign(Map<String, String> params, String privateKey, String charset) throws SignatureException {
        String signContent = getSignContent(params);
        return rsaSign(signContent, privateKey, charset, SIGN_TYPE_RSA);
    }

    /**
     *
     * @param algorithm 算法
     * @param ins 输入流
     * @return 私钥
     * @throws Exception 异常
     */
    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || Objects.isNull(algorithm)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = StreamUtil.readText(ins).getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * 获取待验签数据，包含sign和signType
     * @param params 原始数据
     * @return 待验签数据
     */
    private static String getSignCheckContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        params.remove(CONTENT_SIGN_KEY);
        params.remove(CONTENT_SIGN_TYPE_KEY);
        return getSignContent(params);
    }

    /**
     * 验签，Map格式
     * @param params 待验签数据
     * @param publicKey 公钥
     * @param charset 编码格式
     * @return 验签加签是否成功
     * @throws SignatureException 异常
     */
    private static boolean rsaCheck(Map<String, String> params, String publicKey, String charset) throws SignatureException {
        String sign = params.get(CONTENT_SIGN_KEY);
        String content = getSignCheckContent(params);
        return rsaCheck(content, sign, publicKey, charset, SIGN_TYPE_RSA);
    }

    /**
     * 验签，Map格式，包含验签类型
     * @param params 待验签数据
     * @param publicKey 公钥
     * @param charset 编码格式
     * @param signType 加签类型
     * @return 验签是否成功
     * @throws SignatureException 异常
     */
    private static boolean rsaCheck(Map<String, String> params, String publicKey, String charset,String signType) throws SignatureException {
        String sign = params.get(CONTENT_SIGN_KEY);
        String content = getSignCheckContent(params);
        return rsaCheck(content, sign, publicKey, charset, signType);
    }

    /**
     * 验签，字符串格式
     * @param content 原数据
     * @param sign 加签数据
     * @param publicKey 公钥
     * @param charset 编码格式
     * @param signType 加签类型
     * @return 验签是否成功
     * @throws SignatureException 异常
     */
    private static boolean rsaCheck(String content, String sign, String publicKey, String charset, String signType) throws SignatureException {
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA,new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = getsignatureBySignType(signType);
            signature.initVerify(pubKey);
            if (Objects.isNull(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, e);
        }
    }

    /**
     * 根据加签类型获取Signature实例
     * @param signType 加签类型
     * @return Signature市里
     * @throws Exception 异常
     */
    private static Signature getsignatureBySignType(String signType) throws Exception {
        if (SIGN_TYPE_RSA.equals(signType)) {
            return Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            return Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new SignatureException("Sign Type is Not Support : signType=" + signType);
        }
    }

    /**
     *
     * @param algorithm 算法
     * @param ins 输入流
     * @return 公钥
     * @throws Exception 异常
     */
    private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static void main(String[] args) throws Exception {
        String priveteKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGwgPaCdKEgRMSRK8XiGPE0KFNSXWIuBW5loxNMhNeM+VHtd9EwTErwTBSLszyIbiXSKn5YabJliw5XYunNBBB84WMPhn3/p+6bsVfjKXSxj47NYfWYr70DrwOi5YEH+BvDzPDDYBTdMSDPmY/nco2tDV1+mNUEZKCYAdrSkff/B+KGSLufEL/84she5F+XEbSaGu0ZQ+l4Sf61t7tT0WwGMQOABncXfhUNRgyabDqPd7PS0wxY5uCInttKrIib0ufWmZ/C+WtvNJ//JFablFcYIkjXqjAlL9/p1/tTJDggJjO4fqY28D/DvyD1c5Y7YXwOHpSNloII39GHWRETuuxAgMBAAECggEAbaU3FRWg4IdVSSvxeyhSZTWLySLYKyH9K0A/cCAqeh+nkq18uKiopX1PH89Mmkattwo8oti9qJAz84kVtDo8cC0AoJd28b2PoVW36oK5+cGrqOlpWRqHXHUQtpUEvt5g8nxgFXjKzRzZOCBxeZmdit0eEpxkbWbO2p3f64BI8wk1i0HOCYt1e52398bBHObt8df1hMJejcESrMaOkjlOOP/djfh1njMEmUG46MW13DFTxviK/B1uqErbquHQ1Tcsmbpr5jyWfJ0ZnWlgNrPlwODY5W70QvPiMOaYPGuMpRFfkBIKkNsOQ7SzF9MLlRxc/8AjpiDAL0VUt/OVCED3QQKBgQDOIprDBNT5A0BvqEVp1K3MljKnEegn/vUZuLYkqzdnEJHLQ0OKpLjywADOKsRdu1wHd1Lq2yBKj/I1y1jJ/lDon2RRpd4zHmVhG4i8YLgzBCjsp8C4BFFB5A9ePYho4oRU5OJclautXuFOx2Mvv/bgYuIeNU898lYDn0tEeXMH/QKBgQCnWzTleab8hnL9QmlKCdoOMYEuyZYkMxvBOYm8FCjOCiJfVD39+DZ+No66Awsqu1H9LD4eEH7BqVfK7vjQbfmLw6XwINzAegI1LYQ5Sx0pXRUYJD4Mw15BMLQ/0G6jd36dWzunSwAmOHJk4EVcLB57yMQq4kD4Z7xzHoOO+wy+xQKBgQCfW587Tvv6eioRaAwpkdK+zArin/RTH0aKmQKu/HJ7ShL8CccoYsncWNUGjYd7UwIFf5jpmXewTPIZ5leA6QvRmo+qk0lhSYlkdi3SPA+K2RduRQ229ReAkffTikxvkVZubvgUTV0ITcZu6bPjUUthEvDuG5ZkNHmMmVRN3nowXQKBgQCFpq8dvAOBlBXs78aKHEgKIbrbFJ5VFoPJ0huPGkJkGW/N1ccUNc+/B+7/2kJmw+X8XALXm7C/FHBKXoJSYiFyXrL8jCH/jPCKa2k3ICePywh3mSn8o2F4il7Iv7o7zchN/ngraBgcbePdb8W0kH9ft7wBy2K7fozfGrVR0GgjSQKBgGxdS/RJ+wF3fCjyN0LTP9Aat4RjQ4my4EHTUMRwpeRd6ph3TAG4zQscGrqrIexSuAeBhpv8O1bPZsV2s1qeK6HLwDw12jeBtRaAiQn7jD+X0Bq2t82xa52sTFcvjyuleIazs/WBm6jHunc7YK54P8D0lDCjgk5SptrViJhBlMBK";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhsID2gnShIETEkSvF4hjxNChTUl1iLgVuZaMTTITXjPlR7XfRMExK8EwUi7M8iG4l0ip+WGmyZYsOV2LpzQQQfOFjD4Z9/6fum7FX4yl0sY+OzWH1mK+9A68DouWBB/gbw8zww2AU3TEgz5mP53KNrQ1dfpjVBGSgmAHa0pH3/wfihki7nxC//OLIXuRflxG0mhrtGUPpeEn+tbe7U9FsBjEDgAZ3F34VDUYMmmw6j3ez0tMMWObgiJ7bSqyIm9Ln1pmfwvlrbzSf/yRWm5RXGCJI16owJS/f6df7UyQ4ICYzuH6mNvA/w78g9XOWO2F8Dh6UjZaCCN/Rh1kRE7rsQIDAQAB";
        Map<String, String> dataMap = new HashMap<>(2);
        dataMap.put("acctNum", "1234567890");
        dataMap.put("acctName", "太阳系银行");
        String content = SignUtils.getSignContent(dataMap);
        System.out.println(content);

        String signContent = SignUtils.rsaSign(content, priveteKey, "utf-8", "RSA");
        System.out.println(signContent);

        String signContentMap = SignUtils.rsaSign(dataMap, priveteKey, "utf-8");
        System.out.println(signContentMap);

        Map<String, String> signMap = new HashMap<>(3);
        signMap.put("sign", signContent);
        signMap.put("acctNum", "1234567890");
        signMap.put("acctName", "太阳系银行");
        signMap.put("signType", "RSA");
        boolean checkMap = SignUtils.rsaCheck(signMap, publicKey, "utf-8");
        System.out.println("checkMap" + "\t" + checkMap);

        Map<String, String> signMapSignType = new HashMap<>(3);
        signMapSignType.put("sign", signContent);
        signMapSignType.put("acctNum", "1234567890");
        signMapSignType.put("acctName", "太阳系银行");
        signMapSignType.put("signType", "RSA");
        boolean checkMapSignType = SignUtils.rsaCheck(signMapSignType, publicKey, "utf-8", "RSA");
        System.out.println("checkMapSignType" + "\t" + checkMapSignType);

        Map<String, String> signMapV2 = new HashMap<>(3);
        signMapV2.put("sign", signContent);
        signMapV2.put("acctNum", "1234567890");
        signMapV2.put("acctName", "太阳系银行");
        boolean checkMapV2 = SignUtils.rsaCheck(signMapV2, publicKey, "utf-8");
        System.out.println("checkMap" + "\t" + checkMapV2);

        Map<String, String> signMapSignTypeV2 = new HashMap<>(3);
        signMapSignTypeV2.put("sign", signContent);
        signMapSignTypeV2.put("acctNum", "1234567890");
        signMapSignTypeV2.put("acctName", "太阳系银行");
        signMapSignTypeV2.put("signType", "RSA");
        boolean checkMapSignTypeV2 = SignUtils.rsaCheck(signMapSignTypeV2, publicKey, "utf-8", "RSA");
        System.out.println("checkMapSignType" + "\t" + checkMapSignTypeV2);

        boolean check = SignUtils.rsaCheck(content, signContent, publicKey, "utf-8", "RSA");
        System.out.println("check" + "\t" + check);
    }
}
