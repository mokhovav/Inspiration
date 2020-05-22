package com.mokhovav.inspiration.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LinkDrawData {
    private String name;
    @JsonProperty("from")
    private String positionFrom;
    @JsonProperty("to")
    private String positionTo;

    public LinkDrawData() {
    }

    public LinkDrawData(String name, String positionFrom, String positionTo) {
        this.name = name;
        this.positionFrom = positionFrom;
        this.positionTo = positionTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionFrom() {
        return positionFrom;
    }

    public void setPositionFrom(String positionFrom) {
        this.positionFrom = positionFrom;
    }

    public String getPositionTo() {
        return positionTo;
    }

    public void setPositionTo(String positionTo) {
        this.positionTo = positionTo;
    }
}