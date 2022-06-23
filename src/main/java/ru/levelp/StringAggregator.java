package ru.levelp;

public class StringAggregator {
    private String value = "";
    private int counter = 0;

    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    public synchronized void addCharSequence(String str) {
        synchronized (lock1) {
            value += str;
        }
    }

    public void addCharSequence2(String str) {
        synchronized (lock1) {
            value += str;
        }
    }

    public void anotherMethod(int value) {
        synchronized (lock2) {
            counter += value;
        }
    }
}
