package com.kumanoit.dynamicprogramming.matrixchainmultiplication;

/**
 * https://leetcode.com/problems/burst-balloons/
 *
 * 312. Burst Balloons
 * Approach:
 * A. BruteForce:
 * 1. Generate all permutations of given array and consider each permutation to be the order of bursting balloons
 * 2. For each permutation, compute the cost and keep track of maximum cost
 * 3. Return max cost
 *
 * Complexity
 * 1. Time: O(n!)
 *      There will be n! permutations. Computing cost for each permutation would take O(n^2). so essentially total cost will
 *      be O(n!)
 * 2. Space: O(n)
 *      Permutations can be made using recursion and we will need an array to hold one permutation.
 *
 * B. Top Down Recursion with DP
 * 1. Suppose function f(start, end) gives max cost for bursting balloons in range of [start, end]
 * 2. This function can be defined recursively as
 *      f(start, end) = maximum of (f(start, k - 1) + f(k + 1, end) + nums[start - 1] * nums[k] * nums[end + 1]) where k lies
 *      in range [start, end]
 *      Here,
 *      f(start, k - 1): means total maximum cost from left sub array
 *      nums[start - 1] * nums[k] * nums[end + 1]: Cost of bursting balloon k considering it is the last balloon bursted in
 *      this group from [start, end]
 *      f(k + 1, end): means total maximum cost from right sub array
 * Complexity
 * 1. Time: O(n^3)
 *      For filling each value in memo table, one loop is needed. Hence, to fill N^2 states, we need N^3 complexity
 * 2. Space: O(n^2) for memo table
 *
 * C. Bottom Up
 * 1. The above algorithm can be implemented in bottom up manner using nested loops
 * Complexity
 * 1. Time: O(N^3) There are 3 loops
 * 2. Space: O(N^2) : memo table
 *
 * @author kumanoit on 1/1/22 IST 4:54 PM
 */
public class BalloonBursting {

    public static void main(String[] args) {
        test(new int[]{4}); // 4
        test(new int[]{1, 5}); // 10
        test(new int[]{3, 1, 5, 8}); // 167
    }

    private static void test(int[] input) {
        System.out.println("With Recursion Top Down  : " + computeTopDown(input));
        System.out.println("With Recursion Bottom Up : " + computeBottomUp(input));
    }

    private static int computeTopDown(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        System.arraycopy(nums, 0, newNums, 1, n);
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;

        int[][] memo = new int[n + 2][n + 2];
        compute(newNums, memo, 1, n);
        return memo[1][n];
    }

    /**
     * recursive top down approach
     *
     * @param nums:  input array
     * @param memo:  memo table
     * @param start: starting index of array
     * @param end:   ending index of array
     * @return
     */
    private static int compute(int[] nums, int[][] memo, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        int maxValue = 0;
        for (int mid = start; mid <= end; mid++) {
            maxValue = Math.max(maxValue,
                    // nums[start - 1] means the rightmost unburst balloon from the left subarray
                    // nums[end + 1] means the leftmost unburst balloon from the right subarray
                    // the above two balloons will be needed to compute cost while bursting mid balloon.
                    nums[start - 1] * nums[mid] * nums[end + 1] +
                            compute(nums, memo, start, mid - 1) +
                            compute(nums, memo, mid + 1, end));
        }
        memo[start][end] = maxValue;
        return maxValue;
    }

    /**
     * This is an iterating way for above approach.
     *
     * @param nums
     * @return
     */
    private static int computeBottomUp(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        System.arraycopy(nums, 0, newNums, 1, n);
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;
        int[][] memo = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {
            for (int start = 0; start < n - len + 1; start++) {
                int end = start + len + 1;
                for (int mid = start + 1; mid < end; mid++) {
                    // start and end will point to array element which are out of bounds for current subarray.
                    // here since, mid is iterating from start + 1 to end - 1 hence, we will populate memo with those values
                    // only.
                    memo[start + 1][end - 1] = Math.max(memo[start + 1][end - 1],
                            newNums[start] * newNums[mid] * newNums[end] +
                                    memo[start + 1][mid - 1] +
                                    memo[mid + 1][end - 1]);
                }
            }
        }
        return memo[1][n];
    }
}
