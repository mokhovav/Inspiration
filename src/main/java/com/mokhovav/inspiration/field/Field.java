package com.mokhovav.inspiration.field;

import com.mokhovav.base_spring_boot_project.baseClasses.BaseEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
public class Field extends BaseEntity {
    private Map<String, String> properties;

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
}
