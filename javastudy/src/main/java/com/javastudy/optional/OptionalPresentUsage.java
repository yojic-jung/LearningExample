package com.javastudy.optional;


import java.util.Optional;

/**
 * Optional present 메서드 사용법
 */
public class OptionalPresentUsage {
    public static void main(String[] args) {

        Optional<String> optVal = Optional.of("abc");
        Optional<String> optNull = Optional.ofNullable(null);

        // isPresent null 체크(객체 존재시 true, null이면 false)
        System.out.println("객체 존재 시 : " + optVal.isPresent());
        System.out.println("객체 미존재 시 : " + optNull.isPresent());

        // ifPresent는 객체 존재시에만 수행됨
        optVal.ifPresent(System.out::println);
        optNull.ifPresent(System.out::println);

    }
}
