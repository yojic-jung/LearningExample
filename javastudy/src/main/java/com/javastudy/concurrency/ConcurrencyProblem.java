package com.javastudy.concurrency;

/**
 * 동시성 문제 발생 상황
 * <p>
 * Producer-Consumer Problem
 */
public class ConcurrencyProblem {
    public static int COUNT = 0;

    public static void main(String[] args) {
        Thread producerTh = new Thread(new Producer());
        Thread consumerTh = new Thread(new Consumer());

        producerTh.start();
        consumerTh.start();

        System.out.println(COUNT);
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000000; i++) {
                COUNT++;
            }
        }

    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000000; i++) {
                COUNT--;
            }
        }
    }
}
