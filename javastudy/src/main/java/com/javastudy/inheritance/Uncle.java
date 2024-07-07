package com.javastudy.inheritance;

public interface Uncle {
    default void move() {
        System.out.println("Uncle");
    }
}
