package com.kumanoit.dynamicprogramming.longestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/largest-divisible-subset/
 * <p>
 * Approach 1:
 * Brute Force:
 * Sort the array
 * Consider all possible subsequence and check if a subset satisfies given condition
 * Checking if a subsequence is strictly increasing or not can be done in linear time. a[i] % a[j] == 0 i < j
 * Complexity
 * 1. Time: O(nlg(n)) + O(n*2^n) = O(n*2^n)
 * 2. Space: O(1) if we go ahead with binary number approach to find all subsequence
 * O(lg(n)) if we use recursion to find all possible subsequences
 * <p>
 * Approach 2:
 * Sort the array
 * Let's memo[i] denotes maximum length subset upto an index i including element array[i] then it can be
 * represented as
 * memo[i] = Math.max(memo[i], memo[j] + 1) where 0 <= j < i and array[i] % array[j] == 0
 * We can use two loops in nested form to compute memo array.
 * Time Complexity: O(nlg(n)) + O(n^2) = O(n^2)
 * Space Complexity: O(n) to make memo array
 *
 * @author akumar on 3/23/21 IST 9:16 PM
 */
public class LargestDivisibleSubset {

    public static void main(String[] args) {
        test(new int[]{1});
        test(new int[]{3, 2, 1});
        test(new int[]{1, 2, 3});
        test(new int[]{8, 4, 2, 1});
        test(new int[]{1, 2, 4, 8});
        test(new int[]{3, 5, 7, 11, 13});
        test(new int[]{7, 11, 71, 97});
    }

    private static void test(int[] nums) {
        largestDivisibleSubset(nums).forEach(item -> System.out.print(item + ", "));
        System.out.println();
    }

    /**
     * In this function the array is sorted in descending order and the processing is being done from the last.
     *
     * @param nums
     * @return
     */
    private static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLengthIndex = n - 1;
        int[] memo = new int[n];
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            memo[i] = 1;
            next[i] = n;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] % nums[i] == 0) {
                    if (memo[j] + 1 > memo[i]) {
                        next[i] = j;
                        memo[i] = memo[j] + 1;
                    }
                }
            }
            if (memo[maxLengthIndex] < memo[i]) {
                maxLengthIndex = i;
            }
        }

        List<Integer> solution = new ArrayList<Integer>();
        while (maxLengthIndex < n) {
            solution.add(nums[maxLengthIndex]);
            maxLengthIndex = next[maxLengthIndex];
        }
        return solution;
    }

    /**
     * In this I am using Collections.reverseOrder() function to sort array in descending order but for that I have to
     * pass array as Integer object
     *
     * @param nums
     * @return
     */
    private static List<Integer> largestDivisibleSubset(Integer[] nums) {
        Arrays.sort(nums, Collections.reverseOrder());
        int n = nums.length;
        int[] memo = new int[n];
        int[] parent = new int[n];
        int maxLengthIndex = 0;
        for (int i = 0; i < n; i++) {
            memo[i] = 1;
            parent[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[j] % nums[i] == 0) {
                    if (memo[i] < memo[j] + 1) {
                        memo[i] = memo[j] + 1;
                        parent[i] = j;
                    }
                }
            }
            if (memo[maxLengthIndex] < memo[i]) {
                maxLengthIndex = i;
            }
        }

        List<Integer> solution = new ArrayList<>();
        while (maxLengthIndex != -1) {
            solution.add(nums[maxLengthIndex]);
            maxLengthIndex = parent[maxLengthIndex];
        }

        return solution;
    }
}
