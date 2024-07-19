package com.javastudy.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * def. json 직렬화와 역직렬화 예제
 */
public class JsonSerializerMain {
    public static void main(String[] args) {
        jsonDeserializer();
    }

    private static void jsonSerializer() {
        // 자바 객체 생성
        MemberProfile profile = new MemberProfile();
        profile.setGender("남자");
        User user = new User();
        user.setEmail("afjl@test.com");
        user.setPasswd("4567");
        user.setUserProfile(profile);

        // ObjectMapper 인스턴스 생성
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 자바 객체를 문자열로 변환 (직렬화)
            String userStr = objectMapper.writeValueAsString(user);

            // 결과 출력
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void jsonDeserializer() {
        // JSON 문자열
        String jsonString = "{\"email\":\"example@example.com\",\"passwd\":\"password123\",\"profile\":{\"gender\":\"남자\"}}";

        // ObjectMapper 인스턴스 생성
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            /**
             * json 역직렬화는 객체의 메타정보가 없이 키-값 으로만 구성되므로 Object로 받고 형변환시 ClassCastException 발생
             */
            User user = objectMapper.readValue(jsonString, User.class);
            // 결과 출력
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
