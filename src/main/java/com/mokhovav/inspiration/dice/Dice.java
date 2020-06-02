package com.mokhovav.inspiration.dice;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class Dice {
    private List<Integer> values;

    private String name;

    public Dice() {
        this.values = new ArrayList<>();
    }

    public Dice(String name) {
        this.name = name;
        this.values = new ArrayList<>();
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public void addValue(int value){
        values.add(new Integer(value));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
