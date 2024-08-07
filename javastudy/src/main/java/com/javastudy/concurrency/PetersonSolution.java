package com.javastudy.concurrency;

/**
 * Critical Section Problem의 해결책인 Peterson's Solution
 */
public class PetersonSolution {
    static boolean[] flag = new boolean[2];
    static int turn = 1;
    static int sum = 0; // 공유 데이터

    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(new Producer());
        Thread th2 = new Thread(new Consumer());

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
                flag[0] = true;
                while (flag[1] && turn == 1) ;

                /** critical section **/
                sum++;

                /** exit section **/
                flag[0] = false;
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                /** entry section **/
                turn = 0;
                flag[1] = true;
                while (flag[0] && turn == 0) ;

                /** critical section **/
                sum--;

                /** exit section **/
                flag[1] = false;
            }
        }
    }
}
