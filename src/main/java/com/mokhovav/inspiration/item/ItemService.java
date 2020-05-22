package com.mokhovav.inspiration.item;

import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exception.ValidException;
import com.mokhovav.inspiration.board.BoardFileData;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.field.FieldService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {
    private final ItemValid itemValid;
    private final FieldService fieldService;

    public ItemService(ItemValid itemValid, FieldService fieldService) {
        this.itemValid = itemValid;
        this.fieldService = fieldService;
    }

    public void println(Item item) throws ValidException {
        ItemFileData itemFileData = convertToItemFileData(item);
        if (itemFileData == null) throw new ValidException("The Item does not exist");
        System.out.println("name: " + itemFileData.getName() + " field: " + itemFileData.getFieldName());
    }

    public void printlnItems(List<Item> itemList) throws ValidException {
        if (itemList == null) throw new ValidException("Items do not exist");
        for (Item item : itemList) {
            println(item);
        }
    }
    @Tracking
    public ItemFileData convertToItemFileData(Item item) throws ValidException {
        if (item == null) throw new ValidException("The Item does not exist");
        Field temp = item.getField();
        String field = null;
        if (temp != null) field = temp.getName();
        ItemFileData itemFileData = new ItemFileData(item.getName(), field, item.getProperties());
        return itemFileData;
    }

    public Item getItemByName(List<Item> itemList, String name) throws ValidException {
        if (itemList == null) throw new ValidException("The item list does not exist");
        for (Item item : itemList) {
            if (item.getName().equals(name)) return item;
        }
        return null;
    }

    @Tracking
    public List<Item> getListOfItemsFromFile(BoardFileData boardFileData, List<Field> fieldList) throws ValidException {
        List<String[]> itemsStringList = boardFileData.getItemsList();
        List<Item> itemsList = new ArrayList<>();
        for (String[] strings : itemsStringList) {
            if (strings == null || strings.length != 2) throw new ValidException("ITEMS: Property set incorrectly");
            if(itemValid.nullOrEmpty(strings[0])) throw new ValidException("ITEMS: The item with the specified name does not exist");
            if(itemValid.nullOrEmpty(strings[1])) throw new ValidException("ITEMS: The Field with the specified name does not exist");
            Item item = getItemByName(itemsList,  strings[1]);
            if(item != null) throw new ValidException("ITEMS: The Item is already exist" + " strings[0] = " + strings[0]);
            Field field = fieldService.getFieldByName(fieldList, strings[1]);
            if(field == null) throw new ValidException("ITEMS: The Field does not exist" + " strings[1] = " + strings[1]);
            itemsList.add(new Item(strings[0], field));
        }

        /* Add properties of fields */
        List<String[]> propertiesList = boardFileData.getItemsPropertiesList();
        for (String[] strings : propertiesList) {
            if (strings == null || strings.length != 3) throw new ValidException("LISTS_PROPERTIES: Property set incorrectly");
            if(itemValid.nullOrEmpty(strings[0])) throw new ValidException("LISTS_PROPERTIES: The field with the specified name does not exist");
            if(itemValid.nullOrEmpty(strings[1])) throw new ValidException("LISTS_PROPERTIES: Property name must not be empty");
            if(itemValid.nullOrEmpty(strings[2])) throw new ValidException("LISTS_PROPERTIES: Property value must not be empty");
            Item item = getItemByName(itemsList, strings[0]);
            if(item == null) throw new ValidException("LISTS_PROPERTIES: The Field does not exist" + " strings[0] = " + strings[0]);
            if (item.getProperty(strings[1]) != null) throw new ValidException("LISTS_PROPERTIES: The specified property is already set.");
            item.addProperty(strings[1], strings[2]);
        }

        return itemsList;
    }

}