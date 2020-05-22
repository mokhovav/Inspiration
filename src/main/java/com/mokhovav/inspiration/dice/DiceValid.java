package com.mokhovav.inspiration.dice;

import com.mokhovav.base_spring_boot_project.baseClasses.BaseValid;
import org.springframework.stereotype.Service;

@Service
public class DiceValid extends BaseValid {
    public boolean nullOrEmpty(Dice dice) {
        return dice == null || dice.getValues() == null || dice.getValues().size() == 0;
    }
}
