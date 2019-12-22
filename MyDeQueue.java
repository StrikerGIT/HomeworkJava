package lesson3;

import java.util.EmptyStackException;

public class MyDeQueue<T> {

    private T[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;
    private int begin = 0;
    private int end = 0;

    public MyDeQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = (T[]) new Object[capacity];
    }

    public MyDeQueue() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void insertFirst(T value) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        size++;
        end = nextIndex(end);

        T lastValue;
        T newValue = value;
           for (int i = 0; i < end; i++) {
               lastValue = list[i];
               list[i] = newValue;
               newValue = lastValue;
           }

    }

    public void insertLast(T value) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[end] = value;
        size++;
        end = nextIndex(end);
    }

    public T removeFirst() {
        T temp = peekFirst();
        for (int i = 0; i < end; i++) {
            list[i] = list[i + 1];
        }
        list[end] = null;
        size--;
        end--;
        return temp;
    }

    public T removeLast() {
        T temp = peekLast();
        list[end] = null;
        size--;
        end--;
        return temp;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[end];
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[begin];
    }

    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }

    public boolean isFull() {
        return size == list.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
