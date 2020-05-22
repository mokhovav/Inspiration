package com.mokhovav.inspiration.item;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Lazy
public class ItemDrawData {
    private String name;
    private String position;
    private Map<String, String> properties;

    public ItemDrawData() {
    }

    public ItemDrawData(String name, String position, Map<String, String> properties) {
        this.name = name;
        this.position = position;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
