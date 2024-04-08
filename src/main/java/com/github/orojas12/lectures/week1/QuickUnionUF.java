package com.github.orojas12.lectures.week1;

public class QuickUnionUF {
    private final int[] roots;

    public QuickUnionUF(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        roots[pRoot] = qRoot;
    }

    public int root(int i) {
        while (i != roots[i]) i = roots[i];
        return i;
    }
}
