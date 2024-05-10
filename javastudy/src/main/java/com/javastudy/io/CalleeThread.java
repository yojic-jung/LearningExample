package com.javastudy.io;

/**
 * Def. 동기-논블로킹 동작 원리 예제
 * - SyncNonBlocking 클래스와 함께 예제로 사용함
 */
public class CalleeThread implements Runnable {
    // 작업 진척률
    public int workStatus = 0;

    @Override
    public void run() {
        System.out.println("2. [callee] : 작업 시작");
        // workStatus가 100일 떄까지 작업 진행
        while (workStatus != 100) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            workStatus++;
            if (workStatus == 50) System.out.println("[callee] : 작업 50% 처리함");
        }
        System.out.println("3. [callee] : 작업 완료");
    }
}
