package com.kumanoit.matrix;

import com.kumanoit.utils.MatrixUtils;

/**
 * https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 * 
 * Approach: First find the maximum value in each row and column Then based on
 * above information check how much a value at grid[i][j] can be increased. This
 * would be equal to difference of minimum of maximum value in row-i and col-j
 * with value at grid[i][j] Keep adding this value for all elements in matrix
 * 
 * Time complexity = O(rows * cols) Matrix has been traversed 3 times.
 * 
 * @author kumanoit 06-Feb-2020 9:42:45 PM
 */
public class MaxIncreaseToKeepCitySkyline {

	public static void main(String[] args) {
		test(new Integer[][] { { 1 } });
		test(new Integer[][] { { 1, 3, 5, 7 } });
		test(new Integer[][] { { 1 }, { 3 }, { 5 }, { 7 } });
		test(new Integer[][] { { 3, 0, 8, 4 }, { 2, 4, 5, 7 }, { 9, 2, 6, 3 }, { 0, 3, 1, 0 } });
	}

	private static void test(final Integer[][] grid) {
		MatrixUtils.printMatrix(grid);
		System.out.println("\nSolution = " + maxIncreaseKeepingSkyline(grid));
	}

	private static int maxIncreaseKeepingSkyline(final Integer[][] grid) {
		int rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		int cols = grid[0].length;
		int solution = 0;
		int[] rowMax = new int[rows];
		int[] colMax = new int[cols];
		for (int i = 0; i < rows; i++) {
			rowMax[i] = grid[i][0];
			for (int j = 1; j < cols; j++) {
				rowMax[i] = Math.max(rowMax[i], grid[i][j]);
			}
		}
		for (int j = 0; j < cols; j++) {
			colMax[j] = grid[0][j];
			for (int i = 1; i < rows; i++) {
				colMax[j] = Math.max(colMax[j], grid[i][j]);
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				solution += Math.min(rowMax[i], colMax[j]) - grid[i][j];
			}
		}
		return solution;
	}
}
