package ru.kashin;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int N = 1000;
        int testsCount = 1000;

//        Locale.setDefault(new Locale("en", "US"));
//
//        for (int m = 0; m < N * N; m += 10) {
//            int percolates = 0;
//            for (int testIdx = 0; testIdx < testsCount; testIdx++) {
//                Random rng = new Random();
//                PercolationGrid grid = new PercolationGrid(N);
//                while (grid.getOpenedCellsCount() < m) {
//                    int i = rng.nextInt(N);
//                    int j = rng.nextInt(N);
//                    grid.openCell(i, j);
//                }
//                if (grid.hasPercolation())
//                    percolates++;
//            }
//            System.out.printf("%.3f, %.3f\n", ((double) m) / (N * N), ((double) percolates) / testsCount);
//        }

        double[] results = new double[testsCount];

        for (int testIdx = 0; testIdx < testsCount; testIdx++) {
            Random rng = new Random();
            PercolationGrid grid = new PercolationGrid(N);
            while (!grid.hasPercolation()) {
                int i = rng.nextInt(N);
                int j = rng.nextInt(N);
                grid.openCell(i, j);
            }
            //System.out.printf("Percolation Threshold = %.5f\n", (double) grid.getOpenedCellsCount() / (N * N));
            results[testIdx] = ((double) grid.getOpenedCellsCount()) / (N * N);
        }

        System.out.println("Mean: " + Arrays.stream(results).average().getAsDouble());
        System.out.println("Variance: " + variance(results));
    }

    private static double variance(double[] a) {
        double avg = Arrays.stream(a).average().getAsDouble();
        double sum = 0.;

        for (double v : a) {
            sum += (v - avg) * (v - avg);
        }

        return sum / a.length;
    }
}
