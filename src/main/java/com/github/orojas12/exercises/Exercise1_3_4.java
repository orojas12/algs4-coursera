package com.github.orojas12.exercises;

import com.github.orojas12.collections.LinkedStack;
import com.github.orojas12.collections.exceptions.EmptyStackException;

/**
 * Write a stack client that reads in a text stream from standard input
 * and uses a stack to determine whether its parentheses are properly
 * balanced. For example, your program should print true for
 * [()]{}{[()()]()} and false for [(]).
 */

public class Exercise1_3_4 {

    public static boolean validateParens(String parens) {
        final LinkedStack<Character> openParens = new LinkedStack<>();

        try {
            for (int i = 0, n = parens.length(); i < n; i++) {
                char paren = parens.charAt(i);
                if (paren == '[' || paren == '(' || paren == '{') {
                    openParens.push(paren);
                } else if (paren == ']' && openParens.peek() == '[') {
                    openParens.pop();
                } else if (paren == ')' && openParens.peek() == '(') {
                    openParens.pop();
                } else if (paren == '}' && openParens.peek() == '{') {
                    openParens.pop();
                } else {
                    return false;
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }

        return true;
    }
}
