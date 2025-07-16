package com.example;

public class TimerTask extends Thread {
    public void run() {
        int seconds = 0;
        while (!isInterrupted()) {
            try {
                Thread.sleep(1000);
                seconds++;
                System.out.println("Минуло секунд: " + seconds);
                if (seconds % 5 == 0) {
                    System.out.println("Минуло 5 секунд");
                }
            } catch (InterruptedException e) {
                interrupt(); // Повторне переривання
            }
        }
    }
}