package com.designpattern.factory.simplefactory.enums;

/**
 * 类名称: Circle
 * 类描述: 圆形
 * @author squirrel
 * @date 2019-09-17
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
