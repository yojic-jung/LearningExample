package com.javastudy.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Critical Section Problem의 해결책인 Peterson's Solution
 */
public class PetersonSolutionWithHardwareSupport {
    static AtomicBoolean[] flag = new AtomicBoolean[2];
    static int turn = 1;
    static AtomicInteger sum = new AtomicInteger(0); // 공유 데이터

    static {
        flag[0] = new AtomicBoolean();
        flag[1] = new AtomicBoolean();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(new Producer());
        Thread th2 = new Thread(new Consumer());
Object
        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(sum);
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                /** entry section **/
                turn = 1;
                flag[0].set(true);
                while (flag[1].get() && turn == 1) ;

                /** critical section **/
                sum.incrementAndGet();

                /** exit section **/
                flag[0].set(false);
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                /** entry section **/
                turn = 0;
                flag[1].set(true);
                while (flag[0].get() && turn == 0) ;

                /** critical section **/
                sum.decrementAndGet();

                /** exit section **/
                flag[1].set(false);
            }
        }
    }
}
