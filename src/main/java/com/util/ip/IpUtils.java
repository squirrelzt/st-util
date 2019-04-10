package com.util.ip;

/**
 * 类名称: IpUtils
 * 类描述: IP地址转整型工具类
 * @author squirrel
 * @date 2019-04-10
 */
public class IpUtils {

    private IpUtils() {}

    /**
     * 获取IP整型数据
     * @param ip IP地址
     * @return IP地址对应整型
     */
    public static Long getIpNum(final String ip) {
        Long ipNum = 0L;
        final String ipStr = ip.trim();
        if (ip != null && ipStr.length() != 0) {
            final String[] subIps = ipStr.split("\\.");
            for (final String str: subIps) {
                ipNum = ipNum << 8;
                ipNum += Integer.parseInt(str);
            }
        }
        return ipNum;
    }

    /**
     *  根据IP整型数据获取IP
     * @param ipNum IP地址对应整型
     * @return IP地址
     */
    public static String getIpStr(final Long ipNum) {
        final Long andNumbers[] = { 0xff000000L, 0x00ff0000L, 0x0000ff00L, 0x000000ffL };
        final StringBuilder IpStrSb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            IpStrSb.append(String.valueOf((ipNum & andNumbers[i]) >> 8 * (3 - i)));
            if (i != 3) {
                IpStrSb.append(".");
            }
        }
        return IpStrSb.toString();
    }

    public static void main(String[] args) {
        final String ipStr = "192.168.1.1";
        Long ipNum = IpUtils.getIpNum(ipStr);
        System.out.println(ipNum);

        String ip = IpUtils.getIpStr(ipNum);
        System.out.println(ip);
    }
}
