package com.kumanoit.dynamicprogramming.longestIncreasingSubsequence;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Approach 1:
 * Brute Force: Consider all possible subsequence and check if a subsequence is strictly increasing or not.
 * Total Subsequence = 2^n
 * Checking if a subsequence is strictly increasing or not can be done in linear time.
 * Complexity
 * 1. Time: n*2^n
 * 2. Space: O(1) if we go ahead with binary number approach to find all subsequence
 * O(lg(n)) if we use recursion to find all possible subsequences
 *
 * Approach 2:
 * Let's memo[i] denotes maximum length subsequence upto an index i including element array[i] then it can be
 * represented as
 * memo[i] = Math.max(memo[i], memo[j] + 1) where 0 <= j < i and array[j] < array[i]
 * We can use two loops in nested form to compute memo array.
 * Time Complexity: O(n^2)
 * Space Complexity: O(n) to make memo array
 *
 * @author akumar on 3/23/21 IST 8:25 PM
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLisDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLisDP(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLisDP(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(lengthOfLisDP(new int[]{5}));
        System.out.println(lengthOfLisDP(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(lengthOfLisDP(new int[]{6, 5, 4, 3, 2, 1}));
        System.out.println(lengthOfLisDP(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}));
    }

    private static int lengthOfLisDP(int[] nums) {
        int[] memo = new int[nums.length];
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            memo[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, memo[i]);
        }
        return maxLength;
    }
}
