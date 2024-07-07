package com.javastudy.inheritance;

interface Person {
    default void move() {
        System.out.println("Person");
    }
}
