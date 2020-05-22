package com.mokhovav.inspiration.item;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Lazy
public class ItemFileData {
    private String name;
    private String fieldName;
    private Map<String, String> properties;

    public ItemFileData() {
    }

    public ItemFileData(String name, String fieldName, Map<String, String> properties) {
        this.name = name;
        this.fieldName = fieldName;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
