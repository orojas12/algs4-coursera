package com.github.orojas12.assignments.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] trialThresholds;

    public PercolationStats(int n, int trials) {
        trialThresholds = new double[trials];
        Stopwatch watch = new Stopwatch();
        System.out.println("Beginning percolation trials...");
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int[] randomSites = StdRandom.permutation(n * n);
            int openedSites = 0;
            for (int j = 0; j < randomSites.length && !p.percolates(); j++) {
                int[] coord = getCoord(randomSites[j] + 1, n);
                System.out.println(String.format("Opening (%d, %d)", coord[0], coord[1]));
                p.open(coord[0], coord[1]);
                openedSites += 1;
            }
            double threshold = (double) openedSites / (n * n);
            trialThresholds[i] = threshold;
            System.out.println("System percolated after opening " + openedSites + " sites");
            System.out.println("Percolation threshold: " + threshold);
        }
        double milliseconds = watch.elapsedTime() * 1000;
        System.out.println("=========================");
        System.out.println("Ran " + trials + " trials");
        System.out.println("Average percolation threshold: " + mean());
        System.out.println("Elapsed time: " + Math.round(milliseconds) + "ms");
    }
    
    private static int[] getCoord(int site, int n) {
        int[] coord = new int[2];
        int row = site / n;
        int col = site - n * row;
        if (col > 0) {
            row += 1;
        } else {
            col = n;
        }
        coord[0] = row;
        coord[1] = col;
        return coord;
    }

    public double mean() {
        return StdStats.mean(trialThresholds);
    }

    public double stddev() {
        return StdStats.stddev(trialThresholds);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trialThresholds.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trialThresholds.length);
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(50, 100);
    }
}
