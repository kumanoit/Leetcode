package com.kumanoit.dynamicprogramming;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/10/21 IST 8:52 PM
 */
public class EscapePrison {
    public static void main(String[] args) {
//        System.out.println(nearestExit(new char[][]{
//                        {'+', '.', '+', '+', '+', '+', '+'},
//                        {'+', '.', '+', '.', '.', '.', '+'},
//                        {'+', '.', '+', '.', '+', '.', '+'},
//                        {'+', '.', '.', '.', '+', '.', '+'},
//                        {'+', '+', '+', '+', '+', '.', '+'}},
//                new int[]{0, 1}));
        System.out.println(nearestExit(new char[][]{
                        {'.', '.', '.'},
                        {'+', '.', '.'},
                        {'.', '.', '.'}},
                new int[]{2, 1}));
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        int rowId = entrance[0];
        int colId = entrance[1];

        maze[rowId][colId] = '+';
        int down = compute(maze, rowId + 1, colId, rows, cols, new Integer[rows][cols]);
        int top = compute(maze, rowId - 1, colId, rows, cols, new Integer[rows][cols]);
        int right = compute(maze, rowId, colId + 1, rows, cols, new Integer[rows][cols]);
        int left = compute(maze, rowId, colId - 1, rows, cols, new Integer[rows][cols]);

        down = down == 0 ? Integer.MAX_VALUE : down;
        top = top == 0 ? Integer.MAX_VALUE : top;
        right = right == 0 ? Integer.MAX_VALUE : right;
        left = left == 0 ? Integer.MAX_VALUE : left;

        int solution = Math.min(Math.min(top, down), Math.min(left, right));

        return (solution < 0 || solution == Integer.MAX_VALUE) ? -1 : solution;
    }

    private static int compute(char[][] maze, int rowId, int colId, int rows, int cols, Integer[][] memo) {
        if (rowId < 0 || rowId == rows || colId < 0 || colId == cols) {
            // minSteps[0] = Math.min(minSteps[0], steps);
            return 0;
        }

        if (maze[rowId][colId] == '+') {
            return Integer.MAX_VALUE;
        }

        if (memo[rowId][colId] != null) {
            return memo[rowId][colId];
        }

        maze[rowId][colId] = '+';
        int down = compute(maze, rowId + 1, colId, rows, cols, memo);
        int top = compute(maze, rowId - 1, colId, rows, cols, memo);
        int right = compute(maze, rowId, colId + 1, rows, cols, memo);
        int left = compute(maze, rowId, colId - 1, rows, cols, memo);
        maze[rowId][colId] = '.';

        int minStepsSoFar = Math.min(Math.min(top, down), Math.min(left, right));
        memo[rowId][colId] = minStepsSoFar == Integer.MAX_VALUE ? Integer.MAX_VALUE : minStepsSoFar + 1;

        if (rowId == 2 && colId == 2) {
            for (int i = 0; i < rows; i++) {
                System.out.println();
                for (int j = 0; j < cols; j++) {
                    System.out.print(memo[i][j] + "\t\t");
                }
            }
        }
        return memo[rowId][colId];
    }
}
