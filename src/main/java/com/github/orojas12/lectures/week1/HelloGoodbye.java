package com.github.orojas12.lectures.week1;

import edu.princeton.cs.algs4.StdOut;

public class HelloGoodbye {
    public static void main(String[] args) {
        int length = args.length;
        StringBuilder sb = new StringBuilder();

        if (length == 0) return;

        // build hello string
        sb.append("Hello ").append(args[0]);
        for (int i = 1; i < length; i++) {
            sb.append(" and ").append(args[i]);
        }
        sb.append(".");

        StdOut.println(sb.toString());

        // build goodbye string
        sb = new StringBuilder();
        sb.append("Goodbye ").append(args[length-1]);
        for (int i = length-2; i >= 0; i--) {
            sb.append(" and ").append(args[i]);
        }
        sb.append(".");

        StdOut.println(sb.toString());
    }
}
