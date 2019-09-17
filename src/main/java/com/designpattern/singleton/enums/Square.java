package com.designpattern.singleton.enums;

/**
 * 类名称: Square
 * 类描述: 正方形
 * @author squirrel
 * @date 2019-09-17
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
