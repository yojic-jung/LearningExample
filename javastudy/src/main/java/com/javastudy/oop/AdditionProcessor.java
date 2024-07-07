package com.javastudy.oop;

public class AdditionProcessor {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return add(a, b) + c;
    }

    public long add(int a, int b, int c, Long d) {
        return add(a, b, c) + d;
    }
}
