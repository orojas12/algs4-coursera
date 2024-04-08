package com.github.orojas12.lectures.week1;

public class WeightedQuickUnionUF {
    private int[] roots;
    private int[] rootSizes;

    public WeightedQuickUnionUF(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
            rootSizes[i] = 1;
        }
    }

    private int root(int i) {
        while (i != roots[i]) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;
        if (rootSizes[pRoot] < rootSizes[qRoot]) {
            roots[pRoot] = qRoot;
            rootSizes[qRoot] += rootSizes[pRoot];
        } else {
            roots[qRoot] = pRoot;
            rootSizes[pRoot] += rootSizes[qRoot];
        }
    }
}
