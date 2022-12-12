package com.kumanoit.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/8/22 IST 12:01 AM
 */
public class SlidingPuzzle773 {
    private static final int[] rowIds = {-1, 1, 0, 0};
    private static final int[] colIds = {0, 0, -1, 1};

    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }
    public static int slidingPuzzle(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) {
                    int cost = compute(board, i, j, rows, cols, new HashSet<String>(), new HashMap<>(), "");
                    return cost == Integer.MAX_VALUE ? -1 : cost;
                }
            }
        }
        return -1;
    }


    private static int compute(int[][] board, int rowId, int colId, int rows, int cols,
                               Set<String> visited, Map<String, Integer> memo, String prefix) {
        String hash = getHash(board);
        if (rowId < 0 || colId < 0 || rowId == rows || colId == cols || visited.contains(hash)) {
            System.out.println(prefix + hash + " : " + Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        System.out.println(prefix + hash);

//        if (memo.containsKey(hash)) {
//            System.out.println(prefix + hash + " : " + memo.get(hash));
//            return memo.get(hash);
//        }
        if (hash.equals("123450")) {
            System.out.println(prefix + hash + " : " + 0);
            return 0;
        }

        visited.add(hash);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rowIds.length; i++) {
            int r = rowIds[i] + rowId;
            int c = colIds[i] + colId;

            if (r < 0 || c < 0 || r == rows || c == cols) {
                continue;
            }

            swap(board, r, c, rowId, colId);
            int cost = compute(board, r, c, rows, cols, visited, memo, prefix + "\t");
            // System.out.println(getHash(board) + " " + cost);
            if (cost != Integer.MAX_VALUE) {
                min = Math.min(min, cost);
            }
            swap(board, r, c, rowId, colId);
        }
        visited.remove(hash);
//        memo.put(hash, min == Integer.MAX_VALUE ? min : min + 1);
//        System.out.println(prefix + hash + " : " + memo.get(hash));
//        return memo.get(hash);
        return min == Integer.MAX_VALUE ? min : min + 1;
    }

    private static void swap(int[][] board, int r1, int c1, int r2, int c2) {
        int temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }

    private static String getHash(int[][] board) {
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                hash.append(board[i][j]);
            }
        }
        return hash.toString();
    }
}
