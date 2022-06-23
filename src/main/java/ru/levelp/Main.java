package ru.levelp;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static final char[] alphabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'i', 'k', 'l', 'm', 'n', 'o', 'p'};
    private static final char[] digits = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    final static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();

//        Thread t1 = new Thread(() -> {
//            Random random = new SecureRandom();
//            synchronized (lock) {
//                for (int i = 0; i < 10; i++) {
//                    builder.append(alphabet[random.nextInt(alphabet.length)]);
//                }
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            Random random = new SecureRandom();
//            synchronized (lock) {
//                for (int i = 0; i < 10; i++) {
//                    builder.append(digits[random.nextInt(digits.length)]);
//                }
//            }
//        });

        CountDownLatch latch = new CountDownLatch(2);

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<String> future1 = pool.submit(() -> {
            Random random = new SecureRandom();
            String result = "";
            for (int i = 0; i < 10; i++) {
                result += alphabet[random.nextInt(alphabet.length)];
            }
            Thread.sleep(5000);
            return result;
        });

        Future<String> future2 = pool.submit(() -> {
            Random random = new SecureRandom();
            String result = "";
            for (int i = 0; i < 10; i++) {
                result += digits[random.nextInt(digits.length)];
            }
            Thread.sleep(5000);
            return result;
        });

        String s1 = future1.get();
        String s2 = future2.get();

        System.out.println(s1 + s2);

//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

        System.out.println("Current string: " + builder);

        //        pool.shutdownNow();
    }
}
