package com.javastudy.inheritance;

public class Mother {
    public static Father staticObject = new Child();

    public void move() {
        System.out.println("Mother");
    }
}
