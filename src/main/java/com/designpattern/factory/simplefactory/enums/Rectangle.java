package com.designpattern.factory.simplefactory.enums;

/**
 * 类名称: Rectangle
 * 类描述: 矩形
 * @author squirrel
 * @date 2019-09-17
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
