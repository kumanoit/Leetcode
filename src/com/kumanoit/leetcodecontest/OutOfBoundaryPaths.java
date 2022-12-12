package com.kumanoit.leetcodecontest;

public class OutOfBoundaryPaths {

    public static void main(String[] args) {
        System.out.println(findPaths(1,3,3,0,1));
    }

    private static Integer compute(int rowId, int colId, int rows, int cols, int maxMove, Integer[][][] memo) {
        if (rowId < 0 || rowId == rows || colId < 0 || colId == cols) {
            return 1;
        }

        if (maxMove <= 0) {
            memo[rowId][colId][maxMove] = 0;
            return 0;
        }

        if (memo[rowId][colId][maxMove] != null) {
            return memo[rowId][colId][maxMove];
        }

        memo[rowId][colId][maxMove] =
                compute(rowId - 1, colId, rows, cols, maxMove - 1, memo) +
                compute(rowId + 1, colId, rows, cols, maxMove - 1, memo) +
                compute(rowId, colId - 1, rows, cols, maxMove - 1, memo) +
                compute(rowId, colId + 1, rows, cols, maxMove - 1, memo);
        return memo[rowId][colId][maxMove];
    }

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Integer[][][] memo = new Integer[m][n][maxMove + 1];
        compute(startRow, startColumn, m, n, maxMove, memo);
        int MOD = 1000000007;
        int total = 0;
        for (int i = 1; i <= maxMove; i++) {
            total = (total + (memo[startRow][startColumn][i] != null ? memo[startRow][startColumn][i] : 0)) % MOD;
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 1; k <= maxMove; k++) {
                    System.out.println(i + ", " + j + ", " + k + ", " + memo[i][j][k]);
                }
            }
        }
        return total;
    }
}
