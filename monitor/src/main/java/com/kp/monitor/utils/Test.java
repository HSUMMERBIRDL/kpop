package com.kp.monitor.utils;

import java.util.Random;

/**
 * des:
 * Created by HL
 * on 2017-05-12.
 */

public class Test {

    public static void main(String[] args){


        int max=20;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);
    }
}
