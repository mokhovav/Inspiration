package com.mokhovav.inspiration.item;

import com.mokhovav.base_spring_boot_project.baseClasses.BaseEntity;
import com.mokhovav.inspiration.field.Field;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
public class Item  extends BaseEntity {
    private Map<String, String> properties;
    private Field field;

    public Item() {
        this.properties = new HashMap<>();
    }

    public Item(String name, Field field) {
        this.name = name;
        this.field = field;
        this.properties = new HashMap<>();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getProperty (String name){
        return properties.get(name);
    }

    public void addProperty(String name, String value) {
        properties.put(name,value);
    }
}
