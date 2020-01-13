package lesson4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedStack<T> implements Iterable{
    private Node first;
    private int size = 0;

    private class Node<T> {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public void push(T value) {
        Node newNode = new Node(value, first);
        first = newNode;
        size++;
    }

    public T pop() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = first.next;
        size--;
        return (T) oldFirst.value;
    }

    public T peek() {
        return (T) first.value;
    }

    public int search(T value) {
        return index(value);
    }

    private int index(T value) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }


    public boolean empty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (empty()) {
            return "[ ]";
        }
        Node current = first;
        StringBuilder sb = new StringBuilder("[ ");

        while (current != null) {
            sb.append(current.value.toString() + ", ");
            current = current.next;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        Node current = new Node(null,first);

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return (T)current.value;
        }
    }
}
