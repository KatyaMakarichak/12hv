package com.example;

public class TimerExample {
    public static void main(String[] args) {
        Thread timerThread = new Thread(() -> {
            int seconds = 0;
            while (true) {
                System.out.println("Минуло секунд: " + seconds);
                seconds++;
                try {
                    Thread.sleep(1000); // 1 секунда
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // кожні 5 секунд
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timerThread.start();
        messageThread.start();
    }
}
