package com.javastudy.optional;

import java.util.Optional;

/**
 * Optional Empty 원리
 */
public class OptionalEmpty {
    public static void main(String[] args) {
        Optional<String> optVal1 = Optional.empty();
        Optional<String> optVal2 = Optional.empty();

        System.out.println("optional empty는 값이 동일함 : " + (optVal1 == optVal2));
    }
}
