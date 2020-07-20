package com.elianda.test1;

import org.apache.calcite.util.Static;

/**
 * @author kylinWang
 * @data 2018/12/24 12:55
 */
public class ClosedJava {
    public static void main(String[] args) {
        int a = 1;
         int b = 1;
         //设置为静态变量； 常量池的概念
        System.out.println(getInt(a,b));
    }

    public static int getInt(int a, int b){
        return a+b;
    }
}
