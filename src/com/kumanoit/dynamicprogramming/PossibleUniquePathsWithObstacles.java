package com.kumanoit.dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths-ii/submissions/
 * 
 * @author kumanoit 15-Dec-2019 11:36:54 AM
 *
 *         Algorithm: The core idea is that once you have an obstacle then total
 *         ways to reach other node from that will be zero. Hence, mark total
 *         ways for that cell as 0. Also, consider cases for first row and
 *         column. If there is an obstacle in any cell then all cells after it
 *         will become unreachable.
 */
public class PossibleUniquePathsWithObstacles {
	public static void main(String[] args) {
		test(new int[][] { { 1 } }); // 0
		test(new int[][] { { 0 } }); // 1
		test(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }); // 2
		test(new int[][] { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } }); // 0
		test(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 1, 0 } }); // 1
		test(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }); // 6
		test(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }); // 0
	}

	private static void test(final int[][] obstacleGrid) {
		long startTime = System.currentTimeMillis();
		System.out.println("Dynamic programming " + uniquePathsWithObstaclesDP(obstacleGrid));
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken by DP : " + (endTime - startTime));
		System.out.println();
	}

	public static int uniquePathsWithObstaclesDP(final int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		int[][] memo = new int[rows][cols];

		// Setting row value to 0 if there is any obstacle (cell = 1) then all further
		// cells will be unreachable
		int rowValue = 1;
		for (int i = 0; i < cols; i++) {
			if (obstacleGrid[0][i] == 1) {
				rowValue = 0;
			}
			memo[0][i] = rowValue;
		}

		// Setting col value to 0 if there is any obstacle (cell = 1) then all further
		// cells will be unreachable
		int colValue = 1;
		for (int i = 0; i < rows; i++) {
			if (obstacleGrid[i][0] == 1) {
				colValue = 0;
			}
			memo[i][0] = colValue;
		}

		// If a cell is obstacle then total ways to reach it will be marked as 0
		// otherwise it will total ways to reach from above cell and left cell
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				memo[i][j] = obstacleGrid[i][j] == 1 ? 0 : memo[i - 1][j] + memo[i][j - 1];
			}
		}
		return memo[rows - 1][cols - 1];
	}
}
