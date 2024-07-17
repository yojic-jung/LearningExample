package com.javastudy;

public class StringMain {
    public static void main(String[] args) {
        String aaa = "aaa";
        System.out.println(System.identityHashCode(aaa));
        aaa.concat("bbb");
        System.out.println(aaa);
        System.identityHashCode(aaa);
        System.out.println(System.identityHashCode(aaa));

    }
}
