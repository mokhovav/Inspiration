package com.mokhovav.inspiration.field;


import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exception.ValidException;
import com.mokhovav.inspiration.board.BoardFileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {

    private final FieldValid fieldValid;

    public FieldService(FieldValid fieldValid) {
        this.fieldValid = fieldValid;
    }

    @Tracking
    public List<Field> createFields(int count, String baseName) throws ValidException {
        if (count <= 0) throw new ValidException("The number of fields must be greater than 0");
        if (baseName == null) baseName = "";
        List<Field> fieldList= new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            fieldList.add(new Field(baseName + i));
        }
        return fieldList;
    }

    @Tracking
    public List<Field> createFields(int count) throws ValidException {
        return createFields(count,"F");
    }

    public void println(Field field) throws ValidException {
        if (field == null) throw new ValidException("The Field does not exist");
        System.out.print("name: " + field.getName());
        Map<String, String> properties = field.getProperties();
        for (String s : properties.keySet()) {
            System.out.print(" " + s + ": " + properties.get(s));
        }
        System.out.println();
    }

    public void printlnFields(List<Field> fieldList) throws ValidException {
        if (fieldList == null) throw new ValidException("Fields do not exist");
        for (Field field : fieldList) {
            println(field);
        }
    }

    public Field getFieldByName(List<Field> fieldList, String name) throws ValidException {
        for (Field field : fieldList) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }

    @Tracking
    public List<Field> getListOfFieldsFromFile(BoardFileData boardFileData) throws ValidException {
        List<String> fieldStringList = boardFileData.getFieldList();
        List<Field> fieldList = new ArrayList<>();
        for (String field: fieldStringList) {
            if(fieldValid.nullOrEmpty(field)) throw new ValidException("FIELDS: The field must not be empty");
            if(getFieldByName(fieldList,field) != null) throw new ValidException("FIELDS: Field name is already in use");
            fieldList.add(new Field(field));
        }

        /* Add properties of fields */
        List<String[]> propertiesList = boardFileData.getFieldsPropertiesList();
        for (String[] strings : propertiesList) {
            if (strings == null || strings.length != 3) throw new ValidException("FIELDS_PROPERTIES: Property set incorrectly");
            if(fieldValid.nullOrEmpty(strings[0])) throw new ValidException("FIELDS_PROPERTIES: The field with the specified name does not exist");
            if(fieldValid.nullOrEmpty(strings[1])) throw new ValidException("FIELDS_PROPERTIES: Property name must not be empty");
            if(fieldValid.nullOrEmpty(strings[2])) throw new ValidException("FIELDS_PROPERTIES: Property value must not be empty");
            Field field = getFieldByName(fieldList, strings[0]);
            if(field == null) throw new ValidException("FIELDS_PROPERTIES: The Field does not exist" + " strings[0] = " + strings[0]);
            if (field.getProperty(strings[1]) != null) throw new ValidException("FIELDS_PROPERTIES: The specified property is already set.");
            field.addProperty(strings[1], strings[2]);
        }

        return fieldList;
    }

}
