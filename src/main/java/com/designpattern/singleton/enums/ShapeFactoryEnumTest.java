package com.designpattern.singleton.enums;

public class ShapeFactoryEnumTest {
    public static void main(String[] args) {
        ShapeFactoryEnum.getShape("CIRCLE").draw();
        ShapeFactoryEnum.getShape("RECTANGLE").draw();
        ShapeFactoryEnum.getShape("SQUARE").draw();
    }
}
