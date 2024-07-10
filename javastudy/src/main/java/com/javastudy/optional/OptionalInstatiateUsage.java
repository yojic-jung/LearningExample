package com.javastudy.optional;

import java.util.Optional;

/**
 * Optional 객체 생성 사용법
 */
public class OptionalInstatiateUsage {
    public static void main(String[] args) {

    }

    // optional 초기화 및 생성
    private void instantiateOptional() {
        Optional<String> optVal = null; // null로 초기화 바람직하지 않음
        Optional<String> optVal2 = Optional.empty(); // 빈 객체로 초기화

        Optional<String> optStr = Optional.of("abc");
        Optional<String> optStr2 = Optional.of(null);  // npe 발생
        Optional<String> optStr3 = Optional.ofNullable(null);
    }
}
