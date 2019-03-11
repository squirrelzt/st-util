package com.util;

import java.util.UUID;

/**
 * 类名称: UuidUtils
 * 类描述: uuid生成工具类
 * @author squirrel
 * @date 2019-03-11
 */
public class UuidUtils {

    private UuidUtils() {}

    /**
     * 获取UUID
     * @return UUID
     */
    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取UUID,中划线去掉
     * @return UUID
     */
    private static String getUUIDDeleteStrike() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取UUID,中划线去掉
     * @return UUID
     */
    private static String getUUIDNoStrike() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(uuidStr.substring(0,8));
        sb.append(uuidStr.substring(9, 13));
        sb.append(uuidStr.substring(14, 18));
        sb.append(uuidStr.substring(19, 23));
        sb.append(uuidStr.substring(24));
        return sb.toString();
    }

    public static void main(String[] args) {
        String uuid = UuidUtils.getUUID();
        System.out.println(uuid);

        String deleteStrike = UuidUtils.getUUIDDeleteStrike();
        System.out.println(deleteStrike);

        String noStrike = UuidUtils.getUUIDNoStrike();
        System.out.println(noStrike);
    }
}
