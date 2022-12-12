package com.kumanoit.leetcodecontest;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/17/21 IST 3:37 PM
 */
public class SubArrayLowHigh {
    public static void main(String[] args) {
        System.out.println(numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
    }

    public static int numSubarrayBoundedMax(int[] nums, int low, int high) {
        int count = 0;
        int start = 0;
        while (nums[start] < low || nums[start] > high) {
            start++;
        }
        if (start == nums.length) {
            return count;
        }
        int max = nums[start];
        for (int end = start + 1; end < nums.length; end++) {
            max = Math.max(max, nums[end]);
            if (max < low || max > high) {
                int n = end - start;
                count += n * (n + 1) / 2;
                max = -1;
                start = end + 1;
            }
        }
        if (start != nums.length) {
            int n = nums.length - start;
        }
        return count;
    }
}
