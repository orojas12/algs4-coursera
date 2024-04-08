package com.github.orojas12.collections;

import com.github.orojas12.collections.exceptions.EmptyListException;
import com.github.orojas12.collections.exceptions.EmptyQueueException;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private final LinkedList<T> list = new LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T peek() {
        return list.get(0);
    }

    public T get(int index) {
        return list.get(index);
    }

    public T dequeue() throws EmptyQueueException {
        try {
            return list.removeFirst();
        } catch (EmptyListException e) {
                throw new EmptyQueueException();
            }
        }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

}
