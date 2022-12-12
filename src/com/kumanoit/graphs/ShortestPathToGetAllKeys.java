package com.kumanoit.graphs;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/3/21 IST 12:19 AM
 */
public class ShortestPathToGetAllKeys {
    public static void main(String[] args) {
//        test(new String[]{"@.a.#", "###.#", "b.A.B"});
//        test(new String[]{"@..aA", "..B#.", "....b"});
        test(new String[]{"@a...", ".#...", ".#...", ".b...", "....."});
        test(new String[]{"@a...", ".#...", ".#...", "b#...", ".#..."});
        test(new String[]{"@a#..", ".#...", ".#...", "b#...", ".#..."});
        test(new String[]{"@a#", "b##"});
        test(new String[]{"@a#", "###", "b.."});
        test(new String[]{"@Aa"});
    }

    private static void test(String[] input) {
        System.out.println(input);
        System.out.println(shortestPathAllKeys(input));
    }

    public static int shortestPathAllKeys(String[] grid) {

        if (grid.length == 0) {
            return 0;
        }

        int[][] origin = new int[1][2];
        int[][] destination = new int[1][2];
        int totalKeys = 0;

        char[][] matrix = new char[grid.length][grid[0].length()];

        for (int i = 0; i < grid.length; i++) {
            matrix[i] = grid[i].toCharArray();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '@') {
                    origin[0][0] = i;
                    origin[0][1] = j;
                } else if (isKey(matrix[i][j])) {
                    int keyNumber = matrix[i][j] - 'a' + 1;
                    if (keyNumber > totalKeys) {
                        totalKeys = keyNumber;
                        destination[0][0] = i;
                        destination[0][1] = j;
                    }
                }
            }
        }

        int[] count = new int[totalKeys];
        Arrays.fill(count, Integer.MAX_VALUE);

//        for (int i = 0; i < count.length; i++) {
//            System.out.println((char) (i + 'a') + " : " + count[i]);
//        }
        compute(matrix, origin[0][0], origin[0][1], destination, count, 0);

        int result = count[count.length - 1];
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static boolean isKey(char ch) {
        return ch >= 'a' && ch <= 'f';
    }

    private static boolean isLock(char ch) {
        return ch >= 'A' && ch <= 'F';
    }

    private static void compute(char[][] matrix, int i, int j, int[][] destination, int[] count, int counter) {
        if (i < 0 || j < 0 || i == matrix.length || j == matrix[0].length || matrix[i][j] == '#' ||
                (isLock(matrix[i][j]) && count[matrix[i][j] - 'A'] == Integer.MAX_VALUE) ||
                (isKey(matrix[i][j]) && (matrix[i][j] != 'a' && count[matrix[i][j] - 'a' - 1] == Integer.MAX_VALUE))
        ) {
            return;
        }

//        System.out.println(">> " + matrix[i][j]);
        if (counter > count[count.length - 1] || counter > matrix.length * matrix[0].length) {
            return;
        }

        if (i == destination[0][0] && j == destination[0][1]) {
            count[count.length - 1] = Math.min(count[count.length - 1], counter);
            return;
        }

        if (isKey(matrix[i][j])) {
            count[matrix[i][j] - 'a'] = 1;
        }

        compute(matrix, i - 1, j, destination, count, counter + 1);
        compute(matrix, i, j - 1, destination, count, counter + 1);
        compute(matrix, i, j + 1, destination, count, counter + 1);
        compute(matrix, i + 1, j, destination, count, counter + 1);

        if (isKey(matrix[i][j])) {
            count[matrix[i][j] - 'a'] = Integer.MAX_VALUE;
        }

    }
}
