package com.useage.format;

import java.text.MessageFormat;

/**
 * 类名称: MessageFormatUsage
 * 类描述: MessageFormat占位符用法
 * @author squirrel
 * @date 2019-09-03
 */
public class MessageFormatUsage {

    public static void main(String[] args) {
        String a= "aaa";
        String b= "bb";
        String c= "c";
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(b).append(c);
        System.out.println(MessageFormat.format(" {0} {1} {2} {3}", a, b,"",sb));
        System.out.println(MessageFormat.format(" ''{0}'' '{1}' {2} {3}", a, b,"",sb.toString()));
    }
}
