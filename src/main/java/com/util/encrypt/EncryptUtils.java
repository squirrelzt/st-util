package com.util.encrypt;

import com.alibaba.fastjson.JSON;
import com.util.json.test.Person;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.util.Base64;

/**
 * 类名称: EncryptUtils
 * 类描述: 加密、解密工具类
 * @author squirrel
 * @date 2019-04-18
 */
public class EncryptUtils {

    private EncryptUtils() {}

    private final static String AES_IV = "ed16234234kjd8d4";

    private final static String CHARSET = "utf-8";

    private final static String AES_ALGORITHMS = "AES";
    /**
     * 算法/模式/补码方式
     */
    private final static String ALGORITHMS_PATTERNS_COMPLEMENTS = "AES/CBC/PKCS5Padding";

    /**
     * 加密
     * @param content 加密内容
     * @param key 公钥
     * @return 解密结果
     * @throws SignatureException 异常
     */
    private static String encrypt(String content,String key) throws SignatureException {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMS_PATTERNS_COMPLEMENTS);
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_ALGORITHMS);
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes(CHARSET));
            // 此处使用BASE64做转码
//            String encrypt =  new BASE64Encoder().encode(encrypted);
            String encrypt =  Base64.getEncoder().encode(encrypted).toString();
            return encrypt.replaceAll("[\\s*\t\n\r]", "");
        }catch (Exception ex){
            throw new SignatureException(ex);
        }
    }

    /**
     * 解密
     * @param content 解密字符串
     * @param key 公钥
     * @return 解密内容
     * @throws SignatureException 异常
     */
    private static String decrypt(String content,String key) throws SignatureException {
        try {
            byte[] raw = key.getBytes(CHARSET);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_ALGORITHMS);
            Cipher cipher = Cipher.getInstance(ALGORITHMS_PATTERNS_COMPLEMENTS);
            IvParameterSpec iv = new IvParameterSpec(AES_IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // 先用base64解密
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            byte[] encrypted1 = Base64.getDecoder().decode(content);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, CHARSET);
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "7d11b70ca35cd9e31fa05565ba25e55f";

        Person person = new Person();
        person.setName("squirrel");
        person.setAge(20);
        String content = JSON.toJSONString(person);
        String encryptData = EncryptUtils.encrypt(content, publicKey);
        System.out.println(encryptData);

        String decryptData = EncryptUtils.decrypt(encryptData, publicKey);
        System.out.println(decryptData);
    }
}
