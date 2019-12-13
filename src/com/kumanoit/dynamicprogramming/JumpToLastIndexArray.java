package com.kumanoit.dynamicprogramming;

/**
 * https://leetcode.com/problems/jump-game/
 * 
 * @author kumanoit 14-Dec-2019 1:42:11 AM
 */
public class JumpToLastIndexArray {

	public static void main(String[] args) {
		test(new int[] { 2, 3, 1, 1, 4 });
		test(new int[] { 1, 1, 1, 1, 1 });
		test(new int[] { 1 });
		test(new int[] { 1, 2, 3, 4, 5 });
		test(new int[] { 3, 2, 1, 0, 5 });
	}

	private static void test(int[] nums) {
		long startTime = System.currentTimeMillis();
		System.out.println("Brute force m = " + isReachableBF(nums, 0, nums.length - 1));
		long midTime = System.currentTimeMillis();
		System.out.println("Dynamic Programming   = " + isReachableDP(nums));
		long endTime = System.currentTimeMillis();
		System.out.println("Dynamic Programming 2 = " + isReachableDP2(nums));
		long endTime2 = System.currentTimeMillis();
		System.out.println("Time taken by BF : " + (midTime - startTime));
		System.out.println("Time taken by DP : " + (endTime - midTime));
		System.out.println("Time taken by DP2 : " + (endTime2 - endTime));
		System.out.println();
	}

	/**
	 * Brute Force solution
	 * 
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	private static boolean isReachableBF(int[] nums, int start, int end) {
		if (start == end) {
			return true;
		}
		for (int i = 1; i <= nums[start]; i++) {
			if (isReachableBF(nums, start + i, end)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * DP approach
	 * 
	 * @param nums
	 * @return
	 */
	private static boolean isReachableDP(int[] nums) {
		boolean[] memo = new boolean[nums.length];
		memo[0] = true;
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (memo[j] && nums[j] + j >= i) {
					memo[i] = true;
					break;
				}
			}
		}
		return memo[nums.length - 1];
	}

	/**
	 * Even better than above DP
	 * 
	 * @param nums
	 * @return
	 */
	private static boolean isReachableDP2(int[] nums) {
		int lastTrueIndex = nums[0];
		for (int i = 1; i <= lastTrueIndex && i < nums.length; i++) {
			lastTrueIndex = Math.max(lastTrueIndex, i + nums[i]);
		}
		return lastTrueIndex >= nums.length - 1;
	}
}
