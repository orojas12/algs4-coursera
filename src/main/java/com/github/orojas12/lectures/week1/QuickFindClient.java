package com.github.orojas12.lectures.week1;

import java.util.Scanner;

import com.github.orojas12.util.Stopwatch;

public class QuickFindClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sites = sc.nextInt();
        QuickFindUF uf = new QuickFindUF(sites);
        Stopwatch sw = new Stopwatch();
        System.out.println("Processing...");
        sw.start();
        while (sc.hasNextInt()) {
            uf.union(sc.nextInt(), sc.nextInt());
        }
        sw.stop();
        System.out.println("Total running time: " + sw.getElapsedTime().toMillis() + "ms");
        sc.close();
    }
}
