package com.designpattern.factory.simplefactory.enums;

public class ShapeEnumFactoryTest {
    public static void main(String[] args) {
        ShapeEnumFactory.getShape("CIRCLE").draw();
        ShapeEnumFactory.getShape("RECTANGLE").draw();
        ShapeEnumFactory.getShape("SQUARE").draw();
    }
}
