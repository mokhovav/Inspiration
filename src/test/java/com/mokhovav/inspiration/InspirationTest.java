package com.mokhovav.inspiration;

import com.mokhovav.base_spring_boot_project.annotations.Tracking;
import com.mokhovav.base_spring_boot_project.exception.ValidException;
import com.mokhovav.inspiration.dice.Dice;
import com.mokhovav.inspiration.dice.DiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Inspiration.class })
@SpringBootTest
public class InspirationTest {
    @Autowired private Logger logger;
    @Autowired private DiceService diceService;

    @Test
    public void autowiredTest(){
        logger.debug("In InspirationTest");
    }

    @Test
    public void diceServiceTest(){
        int size = 10;
        int sampleSize = 10000000;
        double expectedValue = 0;
        double dispersion = 0;
        int[] result = new int[size + 1];
        try {
            Dice test = diceService.createSimpleDice("Test dice", size);
            for (int i = 0; i < sampleSize; i++) {
               result[diceService.rollTheDice(test)]++;
            }
            expectedValue = (double)sampleSize / size;
            for (int i = 1; i < size + 1; i++) {
                dispersion +=  Math.pow(expectedValue - result[i],2);
                System.out.println("Count of dice value " + i + " : "+ result[i]);
            }
            dispersion = Math.sqrt(dispersion / size);
            System.out.println(String.format("Expected value = %.3f", expectedValue));
            System.out.println(String.format("Dispersion = %.3f  in percents : %.3f%%", dispersion,(dispersion/expectedValue)*100));

        } catch (ValidException e)
        {
            logger.debug("Can't to create or to roll the dice");
        }

    }
}