package com.github.orojas12.collections;

import java.util.Iterator;

import com.github.orojas12.collections.exceptions.EmptyListException;
import com.github.orojas12.collections.exceptions.LinkedListOutOfBoundsException;

public class LinkedList<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int size = 0;

    public void addLast(T item) {
        if (isEmpty()) {
            Node node = new Node(item);
            first = node;
            last = node;
        } else {
            Node node = new Node(item);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            Node node = new Node(item);
            first = node;
            last = node;
        } else {
            Node node = new Node(item);
            node.next = first;
            first = node;
        }
        size++;
    }

    public void insertAt(int index, T item) throws LinkedListOutOfBoundsException {
        if (index == 0) {
            addFirst(item);
        } else if (index < 0 || index > size - 1) {
            throw new LinkedListOutOfBoundsException();
        } else {
            Node newNode = new Node(item);
            Node prev = first;
            Node current = first;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            newNode.next = current;
            prev.next = newNode;
            size++;
        }
    }

    public T removeFirst() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        } else {
            T item = first.item;
            first = first.next;
            size--;
            return item;
        }
    }

    public T removeLast() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        } else {
            Node node = first;
            // traverse to the second to last node
            for (int i = 0; i < size - 2; i++) {
                node = node.next;
            }
            T item = node.next.item;
            node.next = null;
            last = node;
            size--;
            return item;
        }
    }

    public T removeAt(int index) throws EmptyListException, LinkedListOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyListException();
        } else if (index < 0) {
            throw new LinkedListOutOfBoundsException();
        } else if (index == 0) {
            return removeFirst();
        } else {
            Node prev = first;
            Node current = first;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            if (current == null) {
                throw new LinkedListOutOfBoundsException();
            }
            prev.next = current.next;
            current.next = null;
            size--;
            return current.item;
        }
    }

    public T get(int index) {
        if (isEmpty()) return null;

        Node node = first;
        for (int i = 0; i < index; i++) {
            if (node == null) break;
            node = node.next;
        }
        
        if (node == null) {
            return null;
        } else {
            return node.item;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        T item;
        Node next;

        public Node() {}

        public Node(T item) {
            this.item = item;
        }
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T item = node.item;
            node = node.next;
            return item;
        }
        
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

}
