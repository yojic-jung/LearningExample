package com.javastudy.io;

import java.nio.ByteBuffer;

/**
 * Def. 버퍼의 동작 방식
 * - 버퍼는 큐 공간
 * - 버퍼는 읽기와 쓰기 모두 가능함
 * - 버퍼의 읽기 쓰기 모두 변환은 방향전환으로 진행
 * - position은 현재 읽기 또는 쓰기 가능한 인덱스를 나타냄
 * - limit은 읽기 쓰기 가능한 최대 인덱스보다 하나 큰 값을 리턴함(불가능한 첫번째 인덱스)
 * - capacity는 버퍼 크기를 나타냄
 */
public class BufferExample {
    public static void main(String[] args) {
        // 8바이트 할당
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        System.out.println("버퍼 생성(쓰기 모드)");
        System.out.println("P : " + byteBuffer.position());
        System.out.println("L : " + byteBuffer.limit());

        // 버퍼에 데이터 넣기
        byteBuffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd');
        System.out.println("버퍼에 4바이트만큼 데이터 할당");
        System.out.println("P : " + byteBuffer.position());
        System.out.println("L : " + byteBuffer.limit());

        // 읽기모드로 변환
        byteBuffer.flip();
        System.out.println("버퍼 읽기 모드");
        System.out.println("P : " + byteBuffer.position());
        System.out.println("L : " + byteBuffer.limit());

        System.out.println("버퍼에서 데이터 두개 뽑아냄");
        char firstData = (char) byteBuffer.get();
        char secondData = (char) byteBuffer.get();
        System.out.println(firstData);
        System.out.println(secondData);

        System.out.println("P : " + byteBuffer.position());
        System.out.println("L : " + byteBuffer.limit());

        // 쓰기모드로 변환
        System.out.println("다시 쓰기 모드");
        byteBuffer.compact();
        System.out.println("P : " + byteBuffer.position());
        System.out.println("ㅣ : " + byteBuffer.limit());
    }
}
