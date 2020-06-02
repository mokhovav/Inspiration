package com.mokhovav.inspiration.link;

import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exceptions.ValidException;
import com.mokhovav.inspiration.board.BoardFileData;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.field.FieldService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {

    private final LinkValid linkValid;
    private final FieldService fieldService;

    public LinkService(LinkValid linkValid, FieldService fieldService) {
        this.linkValid = linkValid;
        this.fieldService = fieldService;
    }

    @Tracking
    public List<Link> createLineLinks(List<Field> fieldList, String baseName){
        if (baseName == null) baseName = "";
        List<Link> linkList= new ArrayList<>();
        /* Create links */
        int max = fieldList.size() - 1;
        for (int i = 0; i < max; i++) {
            linkList.add(new Link(baseName + (i+1), fieldList.get(i),fieldList.get(i+1), Direction.FORWARD));
        }
        return linkList;
    }

    @Tracking
    public List<Link> createLineLinks(List<Field> fieldList){
        return createLineLinks(fieldList, "L");
    }

    @Tracking
    public List<Link> createCircularLinks(List<Field> fieldList, String baseName){
        List<Link> linkList = createLineLinks(fieldList, baseName);
        if (baseName == null) baseName = "";
        int size = fieldList.size();
        linkList.add(new Link(baseName + (fieldList.size()), fieldList.get(size-1),fieldList.get(0), Direction.FORWARD));
        return linkList;
    }

    @Tracking
    public List<Link> createCircularLinks(List<Field> fieldList){
        return createCircularLinks (fieldList, "L");
    }

    @Tracking
    public List<Link> createRectangleLinks(List<Field> fieldList, String baseName, int wight, int height) throws ValidException {
        if (wight <= 0 || height < 0 || wight * height > fieldList.size()) throw new ValidException("LINKS: Not enough fields to form a rectangle");
        if (baseName == null) baseName = "";
        List<Link> linkList= new ArrayList<>();
        int max = fieldList.size() - 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wight - 1; j++) {
                Field from = fieldList.get(j + i*wight);
                Field to = fieldList.get(j+1 + i*wight);
                linkList.add(new Link(baseName + from.getName() + to.getName(),from,to, Direction.FORWARD));
            }
        }
        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < height - 1; j++) {
                Field from = fieldList.get(i + j*wight);
                Field to = fieldList.get(i + (j+1)*wight);
                linkList.add(new Link(baseName + from.getName() + to.getName(),from,to, Direction.FORWARD));
            }
        }
        return linkList;
    }

    @Tracking
    public List<Link> createDiagonalLinks(List<Field> fieldList, String baseName, int wight, int height) throws ValidException {
        if (wight <= 0 || height < 0 || wight * height > fieldList.size()) throw new ValidException("LINKS: Not enough fields to form a rectangle");
        if (baseName == null) baseName = "";
        List<Link> linkList= new ArrayList<>();
        return linkList;
    }

    @Tracking
    public List<Link> createRectangleLinks(List<Field> fieldList){
        return createCircularLinks(fieldList, "L");
    }

    public void println(Link link) throws ValidException {
        LinkFileData linkData = convertToLinkFileData(link);
        if (linkData == null) throw new ValidException("LINKS: The Link does not exist");
        System.out.println("name: " + linkData.getName() + " from: " + linkData.getFrom() + " to: " + linkData.getTo());
    }

    public void printlnLinks(List<Link> linkList) throws ValidException {
        if (linkList == null) throw new ValidException("LINKS: Links do not exist");
        for (Link link : linkList) {
            println(link);
        }
    }

    public LinkFileData convertToLinkFileData(Link link) throws ValidException {
        if (link == null) throw new ValidException("LINKS: The Link does not exist");
        Field temp = link.getFirstField();
        String from = null;
        if (temp != null) from = temp.getName();
        String to = null;
        temp = link.getSecondField();
        if (temp != null) to = temp.getName();
        return new LinkFileData(link.getName(), from, to, Direction.FORWARD.name());
    }

    public Link getLinkByName(List<Link> linkList, String name){
        for (Link link : linkList) {
            if (link.getName().equals(name)) return link;
        }
        return null;
    }

    @Tracking
    public List<Link> getListOfLinksFromFile(BoardFileData boardFileData, List<Field> fieldList) throws ValidException {
        List<LinkFileData> linkDataList = boardFileData.getLinkDataList();
        List<Link> linkList = new ArrayList<>();
        for (LinkFileData linkData: linkDataList) {
            if (linkValid.nullOrEmpty(linkData.getName())) throw new ValidException("LINKS: The name of link must not be empty");
            if (getLinkByName(linkList,linkData.getName()) != null) throw new ValidException("LINKS: Link name is already in use");
            if (linkValid.nullOrEmpty(linkData.getFrom()) || linkValid.nullOrEmpty(linkData.getTo())) throw new ValidException("Field name must not be empty");
            Field firstField = fieldService.getFieldByName(fieldList,linkData.getFrom());
            Field secondField = fieldService.getFieldByName(fieldList,linkData.getTo());
            if(firstField == null || secondField == null) throw new ValidException("LINKS: Field does not exist");
            linkList.add(new Link(linkData.getName(), firstField, secondField, Direction.valueOf(linkData.getDirection())));
        }
        return linkList;
    }
    @Tracking
    public Link getLinkFromField(List<Link> linkList, Field field) throws ValidException {
        if (linkList == null) throw new ValidException("LINKS: The list of links does not exist");
        if (field == null) throw new ValidException("LINKS: The field does not exist");
        for (Link link : linkList) {
            if (link.getFirstField() == field) return link;
        }
        return null;
    }
}
