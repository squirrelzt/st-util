package com.useage.regex;

/**
 * 类名称: LetterNumberMix
 * 类描述: 校验规则：15位，大写字母和数字连续组合，即n为大写字母和15-n为数字
 * @author squirrel
 * @date 2019-09-10
 */
public class LetterNumberMix {

    public static void main(String[] args) {
        String str = "AB1909030000513";
        checkByRegex(str);
        check(str);
    }

    /**
     * 正则表达式实现
     * @param str 字符串
     * @return 校验是否通过
     */
    private static String checkByRegex(String str) {
        String reg = "^[A-Z]{1,14}[0-9]{1,14}$";
        if (!str.matches(reg)) {
            return "请输入大写字母&数字组成的字符串";
        }
        return "success";
    }

    /**
     * 普通方法实现
     * @param str 字符串
     * @return 校验是否通过
     */
    private static String check(String str) {
        if (15 != str.length()) {
            return "字符串长度必须是15位";
        }
        char[] chars = str.toCharArray();
        if (!Character.toString(chars[0]).matches("^[A-Z]+$")) {
            return "字符串首位必须为大写字母";
        }
        int indexDevidor = 1;
        for (int i = 1; i < chars.length; i++) {
            if (!Character.toString(chars[i]).matches("^[A-Z]+$")) {
                if (!Character.toString(chars[i]).matches("^[0-9]+$")) {
                    return "字符串第 " + i + "位不是大写字母和数字";
                } else {
                    indexDevidor = i;
                    break;
                }
            }
        }

        String numeric = str.substring(indexDevidor, 15);
        if (!numeric.matches("^[0-9]+$")) {
            return "字符串"+(15-indexDevidor)+"包含非数值型数据";
        }
        return "success";
    }
}
