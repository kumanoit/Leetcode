package com.kumanoit.dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change-2/
 * <p>
 * Algorithm: This is a Knapsack kind of problem. Either a coin will be used or
 * not. if it is used then amount left will be reduced by the amount of used
 * coin. Since, coin repetition is allowed, so there is no need to reduce length
 * when coin is used.
 * <p>
 * Time Complexity: O(n^2) for DP, 2^n for recursive, O(n^2) for DP optimized
 * Space Complexity: O(amount*coins.length) for an array to hold values, O(amount) for DP optimized
 *
 * @author kumanoit May 7, 2020 12:52:24 AM
 */
public class CoinChange {

	public static void main(String[] args) {
		test(0, new int[]{});
		test(0, new int[]{2});
		test(3, new int[]{});
		test(5, new int[]{1, 2, 5});
		test(3, new int[]{2});
		test(10, new int[]{10});
		test(6249, new int[]{186, 419, 83, 408});
	}

	private static void test(int amount, int[] denominations) {
		System.out.println("BruteForceSolution: " + getSolution(amount, denominations, denominations.length));
		System.out.println("Dynamic Programming Solution: " + getSolutionDP(amount, denominations));
		System.out.println("Dynamic Programming Solution optimized in space: " + getSolutionDPOptimized(amount, denominations));
	}

	private static int getSolution(int amount, int[] denominations, int n) {
		if (n <= 0 || amount < 0) {
			return 0;
		}
		if (amount == 0) {
			return 1;
		}
		return getSolution(amount, denominations, n - 1) + getSolution(amount - denominations[n - 1], denominations, n);
	}

	private static int getSolutionDP(int amount, int[] denominations) {
		int n = denominations.length;
		int[][] memo = new int[amount + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			memo[0][i] = 1;
		}

		for (int i = 1; i <= amount; i++) {
			for (int j = 1; j <= n; j++) {
				if (denominations[j - 1] <= i) {
					memo[i][j] = memo[i - denominations[j - 1]][j] + memo[i][j - 1];
				} else {
					memo[i][j] = memo[i][j - 1];
				}
			}
		}
		return memo[amount][n];

	}

	public static int getSolutionDPOptimized(int amount, int[] coins) {
		int[] memo = new int[amount + 1];
		memo[0] = 1;
		Arrays.sort(coins);
		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				memo[i] += memo[i - coin];
			}
		}
		return memo[amount];
	}
}
