package com.kumanoit.dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * 322. Coin Change
 * <p>
 * Approach:
 * The minimum number of coins needed to form an amount X will contain solution of sub-problem which means in order
 * to form a sum of X we need to calculate minimum coin count for values lesser than X and use it in solving for X.
 * Ex. Let f(X) is a function which denotes minimum count to form a sum of X and let there is a coin of value Y,
 * such that (Y<X) then we can use Y to get sum of X. this can be denoted as f(X) = f(X-Y) + 1 where 1 is for using
 * coin with value of Y.
 * In this problem, we have many coins which can be re-used
 * So, we can write a function as f(amount, coins[], index) where amount is what we try to get and coins is the array
 * showing coin values, index is the index in coins array which(coins[index]) gives value of coin which we want to
 * use. Now there are two possible cases.
 * 1. if we use coin at index: inclusive = f(amount - coins[index], coins, index) + 1
 * Since the coin is used amount is reduced by that coins[index].
 * +1 is used to denote that 1 coin is used
 * 2. if we don't use coin: exclusive = f(amount, coins, index)
 * the minimum of these two values will be the solution.
 * <p>
 * few base cases
 * 1. when amount is 0 f(0, coins, index) = 0
 * 2. when amount is less than 0: then we return some sentinel value to denote that denomination cannot be achieved.
 * Here for simplicity we use that sentinel as the one more than maximum number of coins that can be use to get
 * amount.
 * How can amount be lesser than 0?
 * In inclusive: f(amount - coins[index], coins, index) + 1, when coins[index] > amount, it will lead to amount to
 * be less than 0
 * <p>
 * Complexity: There are three methods and complexity are given above each.
 *
 * @author akumar on 8/3/20 IST 12:36 AM
 */

public class CoinChangeMinCount {

    public static void main(final String[] args) {
        test(new int[]{0}, 0);
        test(new int[]{2}, 3);
        test(new int[]{1, 2, 5}, 0);
        test(new int[]{1, 2, 5}, 1);
        test(new int[]{1, 2, 5}, 2);
        test(new int[]{1, 2, 5}, 5);
        test(new int[]{1, 2, 5}, 7);
        test(new int[]{1, 2, 5}, 8);
        test(new int[]{1, 2, 5}, 11);
        test(new int[]{186, 419, 83, 408}, 6249);
    }

    private static void test(int[] coins, int amount) {
        System.out.println("Solution  1: " + coinChange1(coins, amount));
        System.out.println("Solution  2: " + coinChange2(coins, amount));
        System.out.println("Recursive 3: " + coinChangeRecursively(coins, amount));
        System.out.println();
    }

    /**
     * Complexity:
     * 1. Time: O(coins.length * amount) to fill all values in memo
     * 2. Space: O(coins.length * amount)
     */
    private static int coinChange1(int[] coins, int amount) {
        int[][] memo = new int[amount + 1][coins.length + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, amount + 1);
        }
        Arrays.fill(memo[0], 0);
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                if (coins[j - 1] > i) {
                    memo[i][j] = memo[i][j - 1];
                    continue;
                }
                int inclusive = memo[i - coins[j - 1]][j] + 1;
                int exclusive = memo[i][j - 1];
                memo[i][j] = Math.min(inclusive, exclusive);
            }
        }
        return memo[amount][coins.length] == amount + 1 ? -1 : memo[amount][coins.length];
    }

    /**
     * Complexity:
     * 1. Time: O(coins.length * amount)
     * 2. Space: O(amount): for memo array
     */
    private static int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, amount + 1);
        memo[0] = 0;
        Arrays.sort(coins);
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                memo[i] = Math.min(memo[i], memo[i - coin] + 1);
            }
        }
        return memo[amount] == amount + 1 ? -1 : memo[amount];
    }

    private static int coinChangeRecursively(int[] coins, int amount) {
        Arrays.sort(coins);
        int minCoins = coinChangeRecursively(coins, coins.length - 1, amount, amount);
        return minCoins == amount + 1 ? -1 : minCoins;
    }

    /**
     * This has multiple overlapping sub-problems.
     * Complexity:
     * 1. Time: O(2^amount)
     * 2. Space: O(amount) for stack in case if denomination is of 1 then amount will get decremented by 1 each time
     * and will create a skewed binary tree.
     */
    private static int coinChangeRecursively(int[] coins, int index, int amount, int totalAmount) {
        if (amount == 0) {
            return 0;
        }
        if (index < 0 || amount < 0) {
            return totalAmount + 1;
        }
        int inclusive = coinChangeRecursively(coins, index, amount - coins[index], totalAmount) + 1;
        int exclusive = coinChangeRecursively(coins, index - 1, amount, totalAmount);
        int min = Math.min(inclusive, exclusive);
        return min;
    }
}
