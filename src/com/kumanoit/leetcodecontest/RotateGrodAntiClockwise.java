package com.kumanoit.leetcodecontest;

import com.kumanoit.utils.ArrayUtils;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/27/21 IST 9:20 AM
 */
public class RotateGrodAntiClockwise {

    public static void main(String[] args) {
        int[][] sol = rotateGrid(new int[][]{{40, 10}, {30, 20}}, 1);
        for (int[] s : sol) {
            for(int x : s) {
                System.out.print(x + ", ");
            }
            System.out.println();
        }
    }

    public static int[][] rotateGrid(int[][] grid, int K) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int layer = 0; layer < cols / 2; layer++) {
            int i = layer;
            int j = layer;
            int maxSize = 2 * (rows + cols - 4 * layer) - 4;
            int[] temp = new int[maxSize];
            int k = 0;
            while (i < rows - layer) {
                temp[k++] = grid[i++][j];
            }
            i--;
            j++;
            while (j < cols - layer) {
                temp[k++] = grid[i][j++];
            }
            j--;
            i--;
            while (i >= layer) {
                temp[k++] = grid[i--][j];
            }
            i++;
            j--;
            while (j >= layer) {
                temp[k++] = grid[i][j--];
            }
            j++;
            i++;

            temp = rotate(temp, K);
            k = 0;
            while (i < rows - layer) {
                grid[i++][j] = temp[k++];
            }
            k--;
            i--;
            while (j < cols - layer) {
                grid[i][j++] = temp[k++];
            }
            k--;
            j--;
            while (i >= layer) {
                grid[i--][j] = temp[k++];
            }
            k--;
            i++;
            while (j >= layer) {
                grid[i][j--] = temp[k++];
            }
            j++;
            k--;
        }
        return grid;
    }

    private static int[] rotate(int[] array, int k) {
        k = k % array.length;
        int[] sol = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            sol[(i + k) % array.length] = array[i];
        }
        return sol;
    }
}
