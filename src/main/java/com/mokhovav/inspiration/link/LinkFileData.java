package com.mokhovav.inspiration.link;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LinkFileData {
    private String name;
    private String from;
    private String to;
    private String direction;

    public LinkFileData() {
    }

    public LinkFileData(String name, String from, String to, String direction) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
