package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzTask {
    private final int n;
    private final AtomicInteger current = new AtomicInteger(1);

    public FizzBuzzTask(int n) {
        this.n = n;
    }

    public void start() {
        Thread fizz = new Thread(() -> runTask(i -> i % 3 == 0 && i % 5 != 0, "fizz"));
        Thread buzz = new Thread(() -> runTask(i -> i % 5 == 0 && i % 3 != 0, "buzz"));
        Thread fizzbuzz = new Thread(() -> runTask(i -> i % 3 == 0 && i % 5 == 0, "fizzbuzz"));
        Thread number = new Thread(() -> runTask(i -> i % 3 != 0 && i % 5 != 0, null));

        fizz.start();
        buzz.start();
        fizzbuzz.start();
        number.start();

        try {
            fizz.join();
            buzz.join();
            fizzbuzz.join();
            number.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runTask(java.util.function.IntPredicate predicate, String toPrint) {
        while (true) {
            int i = current.get();
            if (i > n) break;
            if (predicate.test(i)) {
                if (current.compareAndSet(i, i + 1)) {
                    System.out.println(toPrint != null ? toPrint : i);
                }
            }
        }
    }
}