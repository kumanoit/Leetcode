package com.kumanoit.array;

/**
 * https://leetcode.com/problems/max-consecutive-ones/
 * 
 * @author kumanoit 04-Jan-2020 12:49:37 PM
 */
public class MaximumConsecutiveOnes {

	public static void main(String[] args) {
		test(new int[] { 0 }); // 0
		test(new int[] { 1 }); // 1
		test(new int[] { 1, 0 }); // 1
		test(new int[] { 0, 1 }); // 1
		test(new int[] { 1, 0, 1, 0, 1, 0 }); // 1
		test(new int[] { 0, 1, 0, 1, 0, 1 }); // 1
		test(new int[] { 1, 1, 1, 1, 1, 1, 1 }); // 7
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }); // 0
	}

	private static void test(final int[] nums) {
		System.out.println(findMaxConsecutiveOnes(nums));
	}

	public static int findMaxConsecutiveOnes(int[] nums) {
		int maxSum = 0;
		int localSum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				maxSum = Math.max(maxSum, localSum);
				localSum = 0;
			} else {
				localSum += nums[i];
			}
		}
		maxSum = Math.max(maxSum, localSum);
		return maxSum;
	}
}
