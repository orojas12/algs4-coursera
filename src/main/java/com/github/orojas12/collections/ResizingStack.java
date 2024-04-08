package com.github.orojas12.collections;

import com.github.orojas12.collections.exceptions.EmptyStackException;

import java.util.Iterator;

public class ResizingStack<T> implements Stack<T> {

    private T[] array;
    private int index = -1;

    public ResizingStack(int maxSize) {
        array = (T[]) new Object[maxSize];
    }

    public ResizingStack() {
        array = (T[]) new Object[1];
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            resize(array.length * 2);
        }
        array[++index] = value;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T item = array[index];
        array[index--] = null;

        if (isQuarterFull()){
            resize(array.length / 2);
        }

        return item;
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    public boolean isFull() {
        return index + 1 == array.length;
    }

    public boolean isQuarterFull() {
        return index + 1 == array.length / 4;
    }

    private void resize(int maxSize) {
        T[] newArray = (T[]) new Object[maxSize];
        for (int i = 0; i <= index; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public int size() {
        return index + 1;
    }

    public int maxSize() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ResizingStackIterator();
    }

    private class ResizingStackIterator implements Iterator<T> {

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
