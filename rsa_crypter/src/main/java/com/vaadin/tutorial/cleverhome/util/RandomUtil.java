package com.vaadin.tutorial.cleverhome.util;

import java.util.Random;

/**
 * Created by vlk on 27.07.2014.
 */
public class RandomUtil {

    public static long randomInt(int min, int max){

        Random rnd = new Random();

        long randomNumber = rnd.nextInt((max - min) + 1) + min;

        return randomNumber;

    }

}
