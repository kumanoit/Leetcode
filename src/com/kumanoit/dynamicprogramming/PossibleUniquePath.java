package com.kumanoit.dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths/
 * @author kumanoit
 * 14-Dec-2019 12:50:58 AM
 */
public class PossibleUniquePath {

	public static void main(String[] args) {
		test(1, 4);
		test(4, 1);
		test(1, 1);
		test(4, 200);
	}

	private static void test(int m, int n) {
		long startTime = System.currentTimeMillis();
		System.out.println("Brute force m = " + m + " n = " + n + uniquePathsBF(m, n));
		long midTime = System.currentTimeMillis();
		System.out.println("Dynamic Programming force m = " + m + " n = " + n + uniquePathsDP(m, n));
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken by BF : " + (midTime - startTime));
		System.out.println("Time taken by DP : " + (endTime - midTime));
	}

	/**
	 * Brute Force
	 */
	private static int uniquePathsBF(int m, int n) {
		if (m == 1 || n == 1) {
			return 1;
		}
		return uniquePathsBF(m - 1, n) + uniquePathsBF(m, n - 1);
	}

	/**
	 * Dynamic Programming
	 */
	private static int uniquePathsDP(int m, int n) {
		int[][] possibleWays = new int[m][n];
		for (int i = 0; i < m; i++) {
			possibleWays[i][0] = 1;
		}
		for (int i = 0; i < n; i++) {
			possibleWays[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				possibleWays[i][j] = possibleWays[i - 1][j] + possibleWays[i][j - 1];
			}
		}
		return possibleWays[m - 1][n - 1];
	}
}
