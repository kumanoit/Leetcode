package com.kumanoit.dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * 377. Combination Sum IV
 * Approach:
 * Brute Force:
 * Try all possible combination using below recursion formula
 * fun(target, nums) = summation of all values for each num in nums array(fun(target-num, nums))
 * <p>
 * Complexity
 * 1. Time: O(nums.length^target) exponential
 * 2. Space: O(target) recursion will use space on stack. In worst case it will be height of tree when num=1 each time
 * target will get reduced by 1.
 * <p>
 * Here we can see that there are many overlapping sub problems
 * Ex for nums=[1,2,3] and target as 5, fun(5) = fun(5-1=4) + fun(5-2=3) + fun(5-3=2) = fun(4) + fun(3) + fun(2)
 * fun(4) = fun(3) + fun(2) + fun(1)
 * <p>
 * It can be optimized by saving these intermediate results in some kind of lookup table.
 * This is done in dynamic programming function.
 * <p>
 * Complexity:
 * Time: O(target * nums.length) : O(n^2)
 * Space: O(target) for saving results in lookup table of size target
 *
 * @author akumar on 8/4/20 IST 1:15 AM
 */
public class CombinationSum {

    public static void main(String[] args) {
        test(0, new int[]{});
        test(0, new int[]{2});
        test(3, new int[]{});
        test(5, new int[]{1, 2, 5});
        test(3, new int[]{2});
        test(10, new int[]{10});
        test(83, new int[]{186, 419, 83, 408});
        test(186 + 419, new int[]{186, 419, 83, 408});
        test(408 + 186 + 83, new int[]{186, 419, 83, 408});
        test(1096, new int[]{186, 419, 83, 408});
        test(2436, new int[]{186, 419, 83, 408});
        test(2528, new int[]{186, 419, 83, 408});
    }

    private static void test(int target, int[] nums) {
        System.out.println("Recursive approach solution: " + getTotalCombinationToGetSumRecursive(nums, target));
        System.out.println("Dynamic Programming approach solution: " + getTotalCombinationToGetSum(nums, target));
        System.out.println();
    }

    private static int getTotalCombinationToGetSumRecursive(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        int totalWays = 0;
        for (int i = 0; i < nums.length; i++) {
            totalWays += getTotalCombinationToGetSumRecursive(nums, target - nums[i]);
        }
        return totalWays;
    }

    private static int getTotalCombinationToGetSum(int[] nums, int target) {
        int[] memo = new int[target + 1];
        memo[0] = 1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    memo[i] += memo[i - num];
                } else {
                    break;
                }
            }
        }
        return memo[target];
    }
}
