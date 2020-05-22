package com.mokhovav.inspiration.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.item.ItemDrawData;
import com.mokhovav.inspiration.link.LinkDrawData;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Lazy
public class BoardDrawData {
    @JsonProperty("FIELDS")
    private List<Field> fieldList;
    @JsonProperty("LINKS")
    private List<LinkDrawData> linkDrawDataList;
    @JsonProperty("ITEMS")
    private List<ItemDrawData> itemDrawDataList;
    @JsonProperty("MESSAGES")
    private Map<String, String> messages;

    public BoardDrawData() {
        this.fieldList = new ArrayList<>();
        this.linkDrawDataList = new ArrayList<>();
        itemDrawDataList = new ArrayList<>();
        messages = new HashMap<>();
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<LinkDrawData> getLinkDrawDataList() {
        return linkDrawDataList;
    }

    public void setLinkDrawDataList(List<LinkDrawData> linkDrawDataList) {
        this.linkDrawDataList = linkDrawDataList;
    }

    public List<ItemDrawData> getItemDrawDataList() {
        return itemDrawDataList;
    }

    public void setItemDrawDataList(List<ItemDrawData> itemDrawDataList) {
        this.itemDrawDataList = itemDrawDataList;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> message) {
        this.messages = message;
    }
}
