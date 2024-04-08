package com.github.orojas12.collections;

import java.util.Iterator;

import com.github.orojas12.collections.exceptions.EmptyListException;
import com.github.orojas12.collections.exceptions.EmptyStackException;
import com.github.orojas12.collections.exceptions.LinkedListOutOfBoundsException;

public class LinkedStack<T> implements Stack<T> {

    private final LinkedList<T> list = new LinkedList<>();

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public void push(T value) {
        list.addFirst(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        try {
            return list.removeFirst();
        } catch (EmptyListException e) {
                throw new EmptyStackException();
        }
    }

    public T peek() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

}
