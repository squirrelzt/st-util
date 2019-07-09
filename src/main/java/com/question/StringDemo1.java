package com.question;

public class StringDemo1 {
    public static void main(String[] args) {
        /**
         * final变量和普通变量的区别：当fianl变量是基本数据类型及String类型时，
         * 如果在编译期间能知道他的确切值，则编译器会把他当做编译期常量使用。
         * 也就是说在用到该final变量的地方，相当于直接访问的这个常量，不需要在运行时确定。
         * 这种和C语言中的宏替换有点像。因此在上面的一段代码中，由于变量b被final修饰，因此会被当做编译器常量，
         * 所以在使用到b 的地方会直接将变量b替换为他的值。而对于变量d的访问却需要在运行时通过链接来运行。
         */
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));

        System.out.println("-----------------------");
        String str1 = new StringBuilder("计算机").append("软件").toString();
        // String str3= new StringBuilder("计算机软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("Java(TM) SE ").append("Runtime Environment").toString();
        System.out.println(str2.intern() == str2);
    }
}
