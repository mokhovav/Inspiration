package com.mokhovav.inspiration.field;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
public class Field {
    private Map<String, String> properties;

    private String name;

    public Field() {
        properties = new HashMap<>();
    }

    public Field(String name) {
        super();
        this.name = name;
        properties = new HashMap<>();
    }

    public void addProperty(String name, String value){
        properties.put(name,value);
    }

    public String getProperty (String name){
        return properties.get(name);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
