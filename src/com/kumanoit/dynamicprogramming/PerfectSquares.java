package com.kumanoit.dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/perfect-squares/
 * 279. Perfect Squares
 * Approach:
 * It is very similar to https://github.com/kumanoit/Leetcode/blob/master/src/com/kumanoit/dynamicprogramming/CoinChangeMinCount.java
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/5/20 IST 1:57 AM
 */
public class PerfectSquares {
    public static void main(String[] args) {
        for (int i = 0; i <= 12; i++) {
            test(i);
        }
    }

    private static void test(int target) {
        System.out.println("DP: Minimum number of perfect squares needed to make sum of " + target + ": " + computeDP(target));
        System.out.println("Recursive: Minimum number of perfect squares needed to make sum of " + target + ": " + computeRecursive(target));
        System.out.println();
    }

    private static int computeDP(int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, target + 1);
        memo[0] = 0;
        for (int j = 1; j * j <= target; j++) {
            for (int i = 1; i <= target; i++) {
                if (j * j <= i) {
                    memo[i] = Math.min(memo[i], memo[i - j * j] + 1);
                }
            }
        }
        return memo[target];
    }

    private static int computeRecursive(int target) {
        if (target == 0) {
            return 0;
        }
        int totalWays = 0;
        for(int i = 1; i * i <= target; i++) {
            if (computeRecursive(target - i * i) == 0) {
                totalWays++;
            }
        }
        return totalWays;
    }
}
