package com.miracle.a;

/**
 * A
 * Simple Class description.
 *
 * @author SHL
 * @version 1.0
 * @date 2020/8/17
 **/
interface C {
    default void show(String s){
        System.out.println("i"+s);
    };
}

class B {
    void show(String s) {
        System.out.println(s);
    }
}

public class A extends B {
    public static void main(String[] args) {

    }

    @Override
    public void show(String s) {
        super.show(s);
    }
}

