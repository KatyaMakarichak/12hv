package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzzMultithreaded {
    private final int n;
    private final BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
    }

    public void start() {
        Thread fizzThread = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    outputQueue.add("fizz");
                }
            }
        });

        Thread buzzThread = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0 && i % 3 != 0) {
                    outputQueue.add("buzz");
                }
            }
        });

        Thread fizzbuzzThread = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 15 == 0) {
                    outputQueue.add("fizzbuzz");
                }
            }
        });

        Thread numberThread = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                boolean printed = false;

                if (i % 15 == 0) {
                    waitAndPrint("fizzbuzz");
                    printed = true;
                } else if (i % 3 == 0) {
                    waitAndPrint("fizz");
                    printed = true;
                } else if (i % 5 == 0) {
                    waitAndPrint("buzz");
                    printed = true;
                }

                if (!printed) {
                    System.out.println(i);
                }
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();
    }

    private void waitAndPrint(String expected) {
        try {
            while (true) {
                String val = outputQueue.take();
                if (val.equals(expected)) {
                    System.out.println(val);
                    break;
                } else {
                    outputQueue.put(val); // назад у чергу
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        new FizzBuzzMultithreaded(15).start();
    }
}
