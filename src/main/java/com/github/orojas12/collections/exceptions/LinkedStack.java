package com.github.orojas12.collections.exceptions;

import java.util.Iterator;

import com.github.orojas12.collections.LinkedList;
import com.github.orojas12.collections.Stack;

public class LinkedStack<T> implements Stack<T> {

    LinkedList<T> list = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T pop() throws EmptyStackException {
        return null;
    }

    @Override
    public void push(T value) {
        list.addFirst(value);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
