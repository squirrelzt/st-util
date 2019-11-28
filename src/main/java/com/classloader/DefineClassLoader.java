package com.classloader;

public class DefineClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = DefineClassLoader.class.getClassLoader();
        while (true) {
            System.out.println(classLoader);
            if (null == classLoader) {
                break;
            }
            classLoader = classLoader.getParent();
        }
    }
}
