package com.github.orojas12.lectures.week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String champion = "";

        for (int i = 1; !StdIn.isEmpty(); i++) {
            String word = StdIn.readString();
            boolean selected = StdRandom.bernoulli(1.0 / i);
            if (selected) champion = word;
        }

        StdOut.println(champion);
    }
}
