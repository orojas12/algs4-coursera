package com.github.orojas12.assignments.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] siteIsOpen;
    private int openSites;
    private int n;
    private int size;
    private int topVirtualSite;
    private int bottomVirtualSite;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n*n;
        topVirtualSite = size;
        bottomVirtualSite = size + 1;
        this.n = n;
        // add two extra sites to be used as virtual sites
        // connected to the top and bottom row
        uf = new WeightedQuickUnionUF(size + 2);
        openSites = 0;
        siteIsOpen = new boolean[size];
        for (int i = 0; i < size; i++) {
            siteIsOpen[i] = false;
            // connect top row sites to top virtual site
            if (i < n) uf.union(i, topVirtualSite);
            // connect bottom row sites to bottom virtual site
            if (i > size - n) uf.union(i, bottomVirtualSite);
        }

    }

    private boolean siteIsInvalid(int row, int col) {
        return row < 1 || row > n || col < 1 || col > n;
    }

    // connect this site with all open adjacent sites
    public void open(int row, int col) {
        if (siteIsInvalid(row, col)) throw new IllegalArgumentException();

        int siteIndex = getSiteIndex(row, col);
        // the percolation grid is 1 index based (1,1)
        // but the uf is 0 index based
        int siteNum = siteIndex + 1;
        boolean inLeftEdge = siteNum % n == 1;
        boolean inRightEdge = siteNum % n == 0;
        boolean inTopRow = siteNum <= n;
        boolean inBottomRow = size - siteNum < n;
    
        siteIsOpen[siteIndex] = true;
        openSites++;

        // connect adjacent open sites
        if (!inTopRow) {
            // connect to site above
            int topSiteIndex = siteNum - n - 1;
            if (siteIsOpen[topSiteIndex]) {
                uf.union(siteIndex, topSiteIndex);
            }
        }

        if (!inBottomRow) {
            // connect site below to this one
            int bottomSiteIndex = siteIndex + n;
            if (siteIsOpen[bottomSiteIndex]) {
                uf.union(bottomSiteIndex, siteIndex);
            }
        }

        if (!inLeftEdge) {
            // connect site to left adjacent site
            if (siteIsOpen[siteIndex - 1]) {
                uf.union(siteIndex, siteIndex - 1);
            }
        }

        if (!inRightEdge) {
            // connect site to right adjacent site
            if (siteIsOpen[siteIndex + 1]) {
                uf.union(siteIndex, siteIndex + 1);
            }
        }
    }

    private int getSiteIndex(int row, int col) {
        return row * n - (n - col) - 1;
    }


    public boolean isOpen(int row, int col) {
        if (siteIsInvalid(row, col)) throw new IllegalArgumentException();
        return siteIsOpen[getSiteIndex(row, col)];
    }

    // is an open site that's connected to the top virtual site
    public boolean isFull(int row, int col) {
        if (siteIsInvalid(row, col)) throw new IllegalArgumentException();
        return isOpen(row, col) && uf.find(getSiteIndex(row, col)) == uf.find(topVirtualSite);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    // is there a full site in the bottom row?
    public boolean percolates() {
        // iterate over bottom row
        return uf.find(bottomVirtualSite) == uf.find(topVirtualSite);
    }

    public static void main (String[] args) {
        Stopwatch watch = new Stopwatch();
        int n = 3;
        Percolation p = new Percolation(n);
        int[] randomSites = StdRandom.permutation(n * n);
        int openedSites = 0;
        for (int i = 0; i < randomSites.length && !p.percolates(); i++) {
            int[] coord = getCoord(randomSites[i] + 1, n);
            System.out.println(String.format("Opening (%d, %d)", coord[0], coord[1]));
            p.open(coord[0], coord[1]);
            openedSites += 1;
        }
        double milliseconds = watch.elapsedTime() * 1000;
        System.out.println("System percolated after opening " + openedSites + " sites");
        System.out.println("Percolation threshold: " + (double) openedSites / (n * n));
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
}
