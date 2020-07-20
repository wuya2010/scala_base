package com.scala.DF_DS_Test;

import java.util.Random;

/**
 * @author kylinWang
 * @data 2018/6/10 17:15
 */
public class getJaveRandom {
    public static void main(String[] args) {
        Random random = new Random(123456789);
        int i = random.nextInt(10);
        System.out.println(i);

    }
}
