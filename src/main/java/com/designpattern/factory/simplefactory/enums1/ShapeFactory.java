package com.designpattern.factory.simplefactory.enums1;

import com.designpattern.factory.simplefactory.enums.Circle;
import com.designpattern.factory.simplefactory.enums.Rectangle;
import com.designpattern.factory.simplefactory.enums.Shape;
import com.designpattern.factory.simplefactory.enums.Square;

import javax.lang.model.type.UnknownTypeException;

public class ShapeFactory {

    public static Shape getShape(ShapeType type){
        switch(type){
            case CIRCLE:
                return new Circle();
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            default:
                return null;
        }
    }
}
