package com.mokhovav.inspiration.link;

import com.mokhovav.inspiration.field.Field;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Link{
    private Field firstField;
    private Field secondField;
    private Direction direction;
    private String name;

    public Link() {
    }

    public Link(String name, Field firstField, Field secondField, Direction direction) {
        this.name = name;
        this.firstField = firstField;
        this.secondField = secondField;
        this.direction = direction;
    }

    public Field getFirstField() {
        return firstField;
    }

    public void setFirstField(Field firstField) {
        this.firstField = firstField;
    }

    public Field getSecondField() {
        return secondField;
    }

    public void setSecondField(Field secondField) {
        this.secondField = secondField;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
