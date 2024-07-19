package com.javastudy.serialize;

import java.io.*;

/**
 * def. 자바 직렬화와 역직렬화 예제
 */
public class JavaSerializerMain {
    public static void main(String[] args) {
        inheritSerialize();
        inheritDeserialize();
    }

    // 자바 직렬화
    private static void serialize() {
        // 객체 생성
        MemberProfile profile = new MemberProfile();
        profile.setGender("남자");
        Member member = new Member();
        member.setEmail("serializer@test.com");
        member.setPasswd("1234");
        member.setMemberProfile(profile);

        // 파일 쓰기
        String fileName = "serializeTest.ser";
        try (
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos)
        ) {
            // 직렬화 데이터 파일에 쓰기
            out.writeObject(member);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자바 역직렬화
    private static void deserialize() {
        // 파일 읽기
        String fileName = "serializeTest.ser";
        try (
            FileInputStream fos = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fos)
        ) {
            /**
             * 자바 직렬화는 객체 메타 정보를 포함하므로 Object 타입으로 선언하여 역직렬화 이후 실제 클래스 타입으로 변환가능
             */
            Object memberObj = in.readObject();
            System.out.println(memberObj instanceof Member); // 실제 인스턴스 타입을 들고 있음

            Member member = (Member) memberObj; // 형변환 가능
            System.out.println(member);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void inheritSerialize() {
        // 객체 생성
        Eagle eagle = new Eagle();
        eagle.setWing("날개");
        eagle.setClaw("발톱");

        // 파일 쓰기
        String fileName = "inheritSerializeTest.ser";
        try (
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos)
        ) {
            // 직렬화 데이터 파일에 쓰기
            out.writeObject(eagle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자바 역직렬화
    private static void inheritDeserialize() {
        // 파일 읽기
        String fileName = "inheritSerializeTest.ser";
        try (
            FileInputStream fos = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fos)
        ) {
            Eagle eagle = (Eagle) in.readObject();
            System.out.println(eagle);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
