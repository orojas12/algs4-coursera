package com.github.orojas12.exercises;

import com.github.orojas12.collections.LinkedStack;
import com.github.orojas12.collections.Queue;
import com.github.orojas12.collections.exceptions.EmptyQueueException;
import com.github.orojas12.collections.exceptions.EmptyStackException;

import java.util.Objects;

/**
 * Suppose that a client performs an intermixed sequence of
 * (stack) push and pop operations. The push operations put
 * the integers 0 through 9 in order onto the stack; the pop
 * operations print out the return values. Which of the
 * following sequence(s) could NOT occur?
 * <br>
 * <ul>
 * <li>a. 4 3 2 1 0 9 8 7 6 5</li>
 * <li>b. 4 6 8 7 5 3 2 9 0 1</li>
 * <li>c. 2 5 6 7 4 8 9 3 1 0</li>
 * <li>d. 4 3 2 1 0 5 6 7 8 9</li>
 * <li>e. 1 2 3 4 5 6 9 8 7 0</li>
 * <li>f. 0 4 6 5 3 8 1 7 2 9</li>
 * <li>g. 1 4 7 9 8 6 5 3 0 2</li>
 * <li>h. 2 1 4 3 6 5 8 7 9 0</li>
 * </ul>
 * */
public class Exercise1_3_3 {
    private static final Queue<Integer> integers = new Queue<>();
    private static final LinkedStack<Integer> stack = new LinkedStack<>();

    public static void main(String[] args) throws EmptyQueueException, EmptyStackException {
        for (int i = 0; i < 10; i++) {
            integers.enqueue(i);
        }

        stack.push(integers.dequeue());

        Integer[] pops = {4, 3, 2, 1, 0, 9, 8, 7, 6, 5};

        try {
            for (int i = 0; i < pops.length; i++) {
                while (!Objects.equals(stack.peek(), pops[i])) {
                    if (integers.isEmpty()) {
                        throw new InvalidSequenceException();
                    }
                    stack.push(integers.dequeue());
                }
                stack.pop();
            }
            System.out.println("This sequence is valid");
        } catch (InvalidSequenceException e) {
            System.out.println("This sequence is not valid");
        }
    }

    private static class InvalidSequenceException extends Exception {}
}
