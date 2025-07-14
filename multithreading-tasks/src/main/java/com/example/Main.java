package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Таймер ===");
        TimerTask timer = new TimerTask();
        timer.start();

        try {
            Thread.sleep(12000); // працює 12 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.interrupt();

        System.out.println("\n=== FizzBuzz ===");
        FizzBuzzTask fizzBuzz = new FizzBuzzTask(20);
        fizzBuzz.start();
    }
}