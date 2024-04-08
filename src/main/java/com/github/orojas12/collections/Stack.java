package com.github.orojas12.collections;

import com.github.orojas12.collections.exceptions.EmptyStackException;

public interface Stack<T> extends Iterable<T> {

    void push(T value);

    T pop() throws EmptyStackException;

    boolean isEmpty();

    int size();

}

