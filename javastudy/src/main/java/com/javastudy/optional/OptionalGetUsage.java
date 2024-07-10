package com.javastudy.optional;

import java.util.Optional;

/**
 * Optional 객체 가져오기 사용법
 */
public class OptionalGetUsage {
    public static void main(String[] args) {
        Optional<String> optVal = Optional.of("abc");
        Optional<String> optNull = Optional.empty();

        String optStr1 = optVal.get(); // null일 경우 예외 발생
        System.out.println("객체 존재시 get결과 : " + optStr1);

        try {
            optNull.get();
        } catch (Exception e) {
            System.out.println("NULL시 get결과 : " + e);
        }

        String optStr2 = optNull.orElse("대체 값"); // null일 때 대체 값 선언 가능
        System.out.println("NULL시 orElse : " + optStr2);


        String optStr3 = optNull.orElseGet(() -> "람다 함수로 대체"); // null 대체값 람다함수 사용 가능
        System.out.println("NULL시 orElseGet 결과 : " + optStr3);

        try {
            optNull.orElseThrow(); // 인자값 비워두면 NoSuchElementException, 대체값 예외로 설정 가능
        } catch (Exception e) {
            System.out.println("NULL시 orElseThrow 결과 : " + e);
        }

        try {
            optNull.orElseThrow(NullPointerException::new); // 대체값 예외로 설정 가능
        } catch (Exception e) {
            System.out.println("NULL시 orElseThrow 결과 : " + e);
        }

        findUserEmailOrElse();

        findUserEmailOrElseGet();
    }


    public static void findUserEmailOrElse() {
        String str = "Empty";
        String result = Optional.ofNullable(str).orElse(getValue());

        System.out.println(result);
    }

    public static void findUserEmailOrElseGet() {
        String str = "Empty";
        String result = Optional.ofNullable(str).orElseGet(OptionalGetUsage::getValue);

        System.out.println(result);
    }

    private static String getValue() {
        System.out.println("value");
        return "value";
    }


}
