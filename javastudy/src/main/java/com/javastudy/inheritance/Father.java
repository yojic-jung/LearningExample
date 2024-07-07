package com.javastudy.inheritance;

interface Father {
    default void move() {
        System.out.println("Father");
    }
}
