package com.mokhovav.inspiration.dice;

import com.mokhovav.base_spring_boot_project.baseClasses.BaseEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class Dice extends BaseEntity {
    private List<Integer> values;

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
}
