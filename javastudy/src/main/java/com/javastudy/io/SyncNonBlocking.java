package com.javastudy.io;

/**
 * Def. 동기-논블로킹 동작 원리 예제
 * - CalleeThread 클래스와 함께 예제로 사용함
 */
public class SyncNonBlocking {
    public static void main(String[] args) {
        // 별도 스레드 생성
        CalleeThread th = new CalleeThread();
        Thread callee = new Thread(new CalleeThread());

        // 작업 시작
        System.out.println("1. [caller] : callee 호출");
        callee.start();

        // 호출한 작업의 완료여부를 계속 판단함
        int downloadStatus = 0;
        while (callee.isAlive()) {
            if (th.workStatus % 33 == 0 && downloadStatus != th.workStatus) {
                downloadStatus = th.workStatus;
                System.out.println("[caller] : callee의 작업 " + th.workStatus + "% 처리됨을 직접 확인함");
            }
        }

        // 작업 완료
        System.out.println("4. [caller] : 작업 완료");
    }
}
