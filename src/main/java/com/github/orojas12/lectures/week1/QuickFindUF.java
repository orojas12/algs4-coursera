package com.github.orojas12.lectures.week1;

import java.util.Scanner;

public class QuickFindUF extends UnionFind {

    public QuickFindUF(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return;
        for (int i = 0; i < sites.length; i++) {
            if (sites[i] == pRoot) {
                sites[i] = qRoot;
                roots--;
            }
        }
    }

    @Override
    public int findRoot(int p) {
        return sites[p];
    }

}
