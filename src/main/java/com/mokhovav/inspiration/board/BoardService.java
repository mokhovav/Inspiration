package com.mokhovav.inspiration.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exceptions.ValidException;
import com.mokhovav.inspiration.dice.DiceService;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.field.FieldService;
import com.mokhovav.inspiration.item.Item;
import com.mokhovav.inspiration.item.ItemDrawData;
import com.mokhovav.inspiration.item.ItemFileData;
import com.mokhovav.inspiration.item.ItemService;
import com.mokhovav.inspiration.link.Link;
import com.mokhovav.inspiration.link.LinkDrawData;
import com.mokhovav.inspiration.link.LinkFileData;
import com.mokhovav.inspiration.link.LinkService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;


@Service
public class BoardService {

    private final LinkService linkService;
    private final FieldService fieldService;
    private final ItemService itemService;
    private final DiceService diceService;
    private final Logger logger;


    public BoardService(LinkService linkService, FieldService fieldService, ItemService itemService, DiceService diceService, Logger logger) {
        this.linkService = linkService;
        this.fieldService = fieldService;
        this.itemService = itemService;
        this.diceService = diceService;
        this.logger = logger;
    }

    @Tracking
    public BoardFileData convertToBoardFileData(Board board) throws ValidException {
        BoardFileData boardFileData = new BoardFileData();
        List<String> fieldList = boardFileData.getFieldList();
        List<String[]> fieldsPropertiesList = boardFileData.getFieldsPropertiesList();
        List<String[]> itemsPropertiesList = boardFileData.getItemsPropertiesList();
        List<LinkFileData> linkDataList = boardFileData.getLinkDataList();
        List<String[]> itemsList = boardFileData.getItemsList();

        for (Field field : board.getFieldList()) {
            fieldList.add(field.getName());
            Map<String, String> properties = field.getProperties();
            for (String s : properties.keySet()) {
                fieldsPropertiesList.add(new String[]{field.getName(),s,properties.get(s)});
            }
        }
        for (Link link : board.getLinkList()) {
            linkDataList.add(linkService.convertToLinkFileData(link));
        }

        for (Item item : board.getItemList()) {
            ItemFileData itemFileData = itemService.convertToItemFileData(item);
            itemsList.add(new String[]{itemFileData.getName(),itemFileData.getFieldName()});
            Map<String, String> properties = item.getProperties();
            for (String s : properties.keySet()) {
                itemsPropertiesList.add(new String[]{item.getName(),s,properties.get(s)});
            }
        }

        boardFileData.setDiceList(board.getDiceList());

        return boardFileData;
    }

    @Tracking
    public BoardDrawData convertToBoardDrawData(Board board, String positionField){
        BoardDrawData boardDrawData = new BoardDrawData();


        boardDrawData.setFieldList(board.getFieldList());
/*        for (Field field : board.getFieldList()) {
            boardDrawData.getFieldList().add(field);
        }/**/
        for (Link link : board.getLinkList()) {
            boardDrawData.getLinkDrawDataList().add(new LinkDrawData(link.getName(), link.getFirstField().getProperty(positionField),link.getSecondField().getProperty(positionField)));
        }
        for (Item item : board.getItemList()) {
            boardDrawData.getItemDrawDataList().add(new ItemDrawData(item.getName(),item.getField().getProperty(positionField), item.getProperties()));
        }
        return boardDrawData;
    }

    @Tracking
    public void convertToFile(Board board, String fileName) throws ValidException, IOException {

        BoardFileData boardData = convertToBoardFileData(board);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName),boardData);

    }

    @Tracking
    public Board getBoardFromFile(String fileName) throws ValidException {
        Board board = new Board(fieldService, this, linkService, diceService, logger);
        BoardFileData boardFileData;

        try {
            FileReader reader = new FileReader(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            boardFileData = objectMapper.readValue(reader, BoardFileData.class);
            /* Create list of fields */
            board.setFieldList(fieldService.getListOfFieldsFromFile(boardFileData));
            /* Create list of links */
            board.setLinkList(linkService.getListOfLinksFromFile(boardFileData, board.getFieldList()));
            /* Create list of items */
            board.setItemList(itemService.getListOfItemsFromFile(boardFileData, board.getFieldList()));
            /* Create list of dices */
            board.setDiceList(diceService.getListOfDicesFromFile(boardFileData));
        } catch (IOException e) {
            throw new ValidException(e.getMessage());
        }
        return board;
    }

    @Tracking
    public int makeAMove(Board board, String itemName, int steps) throws ValidException {
        Item item = itemService.getItemByName(board.getItemList(), itemName);
        if (item == null) throw new ValidException("BOARD: The item does not exist");
        Field field = item.getField();
        if (field == null) throw new ValidException("BOARD: The item is not on the field");
        Link link = linkService.getLinkFromField(board.getLinkList(), field);
        int count = 0;
        while (count < steps && link != null){
            field = link.getSecondField();
            item.setField(field);
            link = linkService.getLinkFromField(board.getLinkList(), field);
            count++;
        }
        return count;
    }

    @Tracking
    public String fileToString(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        while(line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        return stringBuilder.toString();
    }

    public void println(Board board) throws ValidException {
        if (board == null) throw new ValidException("The Link does not exist");
    }
}