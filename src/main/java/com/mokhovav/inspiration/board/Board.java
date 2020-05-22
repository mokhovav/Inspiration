package com.mokhovav.inspiration.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mokhovav.inspiration.dice.Dice;
import com.mokhovav.inspiration.dice.DiceService;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.field.FieldService;
import com.mokhovav.inspiration.item.Item;
import com.mokhovav.inspiration.link.Link;
import com.mokhovav.inspiration.link.LinkService;
import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Lazy
public class Board{
    @JsonIgnore
    protected final FieldService fieldService;
    @JsonIgnore
    protected final BoardService boardService;
    @JsonIgnore
    protected final LinkService linkService;
    @JsonIgnore
    protected final DiceService diceService;
    @JsonIgnore
    protected final Logger logger;

    protected List<Field> fieldList;
    protected List<Link> linkList;
    protected List<Item> itemList;
    protected List<Dice> diceList;

    public Board(FieldService fieldService, BoardService boardService, LinkService linkService, DiceService diceService, Logger logger) {
        this.fieldService = fieldService;
        this.boardService = boardService;
        this.linkService = linkService;
        this.diceService = diceService;
        this.logger = logger;
        this.fieldList = new ArrayList<>();
        this.linkList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.diceList = new ArrayList<>();
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }
}
