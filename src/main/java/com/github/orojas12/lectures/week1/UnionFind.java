package com.github.orojas12.lectures.week1;

public abstract class UnionFind {
    protected int[] sites;
    protected int roots;

    public UnionFind(int n) {
        sites = new int[n];
        roots = n;
        for (int i = 0; i < n; i++) {
            sites[i] = i;
        }
    }

    public abstract void union(int p, int q);

    protected abstract int findRoot(int p);

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public int countRoots() {
        return roots;
    }
}
