package com.mokhovav.inspiration.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mokhovav.inspiration.dice.Dice;
import com.mokhovav.inspiration.link.LinkFileData;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class BoardFileData {
    @JsonProperty("FIELDS")
    private List<String> fieldList;
    @JsonProperty("LINKS")
    private List<LinkFileData> linkDataList;
    @JsonProperty("FIELDS_PROPERTIES")
    private List<String[]> fieldsPropertiesList;
    @JsonProperty("ITEMS")
    private List<String[]> itemsList;
    @JsonProperty("ITEMS_PROPERTIES")
    private List<String[]> itemsPropertiesList;
    @JsonProperty("DICES")
    protected List<Dice> diceList;

    public BoardFileData() {
        this.fieldList = new ArrayList<>();
        this.linkDataList = new ArrayList<>();
        this.fieldsPropertiesList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.itemsPropertiesList = new ArrayList<>();
    }

    public BoardFileData(List<String> fieldList, List<LinkFileData> linkDataList, List<String[]> fieldsPropertiesList, List<String[]> itemsList, List<String[]> itemsPropertiesList, List<Dice> diceList) {
        this.fieldList = fieldList;
        this.linkDataList = linkDataList;
        this.fieldsPropertiesList = fieldsPropertiesList;
        this.itemsList = itemsList;
        this.itemsPropertiesList = itemsPropertiesList;
        this.diceList = diceList;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public List<LinkFileData> getLinkDataList() {
        return linkDataList;
    }

    public void setLinkDataList(List<LinkFileData> linkDataList) {
        this.linkDataList = linkDataList;
    }

    public List<String[]> getFieldsPropertiesList() {
        return fieldsPropertiesList;
    }

    public void setFieldsPropertiesList(List<String[]> fieldsPropertiesList) {
        this.fieldsPropertiesList = fieldsPropertiesList;
    }

    public List<String[]> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<String[]> itemsList) {
        this.itemsList = itemsList;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }

    public List<String[]> getItemsPropertiesList() {
        return itemsPropertiesList;
    }

    public void setItemsPropertiesList(List<String[]> itemsPropertiesList) {
        this.itemsPropertiesList = itemsPropertiesList;
    }
}