package ru.kashin;

import java.util.Arrays;

public class PercolationGrid {
    private final int N;
    private boolean[] cells;
    private WeightedQuickUnionPathCompressionUF dsu;
    private int openedCellsCount;

    public PercolationGrid(int size) {
        N = size;
        cells = new boolean[N * N];
        Arrays.fill(cells, false);
        openedCellsCount = 0;
        dsu = new WeightedQuickUnionPathCompressionUF(N * N + 2);
    }

    public void openCell(int i, int j) {
        int idx = i * N + j;
        if (cells[idx])
            return;

        cells[idx] = true;
        openedCellsCount++;

        if (i == 0)
            dsu.union(idx, N * N);
        if (i == N - 1)
            dsu.union(idx, N * N + 1);

        if ((i > 0) && cells[(i - 1) * N + j])
            dsu.union(idx, (i - 1) * N + j);
        if ((i < N - 1) && cells[(i + 1) * N + j])
            dsu.union(idx, (i + 1) * N + j);
        if ((j > 0) && cells[i * N + j - 1])
            dsu.union(idx, i * N + j - 1);
        if ((j < N - 1) && cells[i * N + j + 1])
            dsu.union(idx, i * N + j + 1);
    }

    public boolean hasPercolation() {
        return dsu.find(N * N) == dsu.find(N * N + 1);
    }

    public int getOpenedCellsCount() {
        return openedCellsCount;
    }
}
