package com.mokhovav.inspiration.dice;

import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exception.ValidException;
import com.mokhovav.inspiration.board.BoardFileData;
import com.mokhovav.inspiration.field.Field;
import com.mokhovav.inspiration.item.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {
    private final DiceValid diceValid;

    public DiceService(DiceValid diceValid) {
        this.diceValid = diceValid;
    }

    @Tracking
    public Dice createSimpleDice(String name, int size) throws ValidException {
        if (size <= 0) throw new ValidException("The size of the new dice is less than or equal to zero.");
        Dice dice = new Dice(name);
        for (int i = 1; i <= size; i++) {
            dice.addValue(i);
        }
        return dice;
    }

    public void println(Dice dice) throws ValidException {
        if (diceValid.nullOrEmpty(dice)) throw new ValidException("The Dice does not exist");
        dice.getValues().stream().peek((v)-> System.out.print(v.intValue() + " ")).toArray();
        System.out.println();
    }

    public int rollTheDice(Dice dice){
        if (diceValid.nullOrEmpty(dice)) return 0;
        int size = dice.getValues().size();
        int index = (int)(Math.random() * size);
        if (index == size) index--;
        return dice.getValues().get(index).intValue();
    }

    @Tracking
    public List<Dice> getListOfDicesFromFile(BoardFileData boardFileData){
        return boardFileData.getDiceList();
    }
}
