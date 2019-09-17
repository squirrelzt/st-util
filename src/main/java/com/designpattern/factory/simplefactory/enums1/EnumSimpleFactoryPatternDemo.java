package com.designpattern.factory.simplefactory.enums1;

import com.designpattern.factory.simplefactory.enums.Shape;

public class EnumSimpleFactoryPatternDemo {
    public static void main(String[] args) {
        // 获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = ShapeFactory.getShape(ShapeType.CIRCLE);

        // 调用 Circle 的 draw 方法
        shape1.draw();

        // 获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = ShapeFactory.getShape(ShapeType.RECTANGLE);

        // 调用 Rectangle 的 draw 方法
        shape2.draw();

        // 获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = ShapeFactory.getShape(ShapeType.SQUARE);

        // 调用 Square 的 draw 方法
        shape3.draw();
    }
}
