package com.designpattern.singleton.enums;

import lombok.Getter;

/**
 * 类名称: ShapeFactoryEnum
 * 类描述: 枚举实现工厂模式
 * @author squirrel
 * @date 2019-09-17
 */
@Getter
public enum ShapeFactoryEnum {

    /**
     * 圆形
     */
    CIRCLE(new Circle(), "CIRCLE"),

    /**
     * 矩形
     */
    RECTANGLE(new Rectangle(),"RECTANGLE"),

    /**
     * 正方形
     */
    SQUARE(new Square(),"SQUARE");

    /**
     * 形状
     */
    private Shape shape;

    /**
     * 名称
     */
    private String name;

    private ShapeFactoryEnum(Shape shape, String name) {
        this.shape = shape;
        this.name = name;
    }

    public static Shape getShape(String name) {
        for (ShapeFactoryEnum c : ShapeFactoryEnum.values()) {
            if (c.name == name) {
                return c.shape;
            }
        }
        return null;
    }
}
