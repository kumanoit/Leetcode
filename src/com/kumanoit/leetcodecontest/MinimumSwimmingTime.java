package com.kumanoit.leetcodecontest;

import java.util.PriorityQueue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/20/21 IST 3:31 PM
 */
public class MinimumSwimmingTime {
    public static void main(String[] args) {

    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] isVisited = new boolean[n][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(grid.length, (a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        queue.add(new int[]{0, 0});
        int maxSoFar = grid[0][0];
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] coord = queue.remove();
            int rowId = coord[0];
            int colId = coord[1];
            maxSoFar = Math.max(maxSoFar, grid[coord[0]][coord[1]]);
            if (coord[0] == n - 1 && coord[1] == n - 1) {
                break;
            }

            int[] rows = {rowId - 1, rowId, rowId, rowId + 1};
            int[] cols = {colId, colId - 1, colId + 1, colId};
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] < 0 || rows[i] >= n || cols[i] < 0 || cols[i] >= n || isVisited[rows[i]][cols[i]]) {
                    continue;
                }
                isVisited[rows[i]][cols[i]] = true;
                queue.add(new int[]{rows[i], cols[i]});
            }
        }
        return maxSoFar;
    }
}
