package com.github.orojas12.collections;

import com.github.orojas12.collections.exceptions.EmptyStackException;
import com.github.orojas12.collections.exceptions.FullStackException;

import java.util.Iterator;

public class FixedStack<T> implements Iterable<T> {

    private final T[] array;
    private int index;
    private int size;

    private final int maxSize;

    public FixedStack(int maxSize) {
        array = (T[]) new Object[maxSize];
        index = -1;
        size = 0;
        this.maxSize = maxSize;
    }

    public void push(T value) throws FullStackException {
        if (isFull()) {
            throw new FullStackException();
        } else {
            array[++index] = value;
            size++;
        }
    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T item = array[index];
            array[index--] = null;
            return item;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new FixedStackIterator();
    }

    private class FixedStackIterator implements Iterator<T> {

        private int iteratorIndex = index;

        @Override
        public boolean hasNext() {
            return iteratorIndex > -1;
        }

        @Override
        public T next() {
            return array[iteratorIndex--];
        }
    }

}
