package com.kumanoit.array;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * 
 * @author kumanoit 04-Jan-2020 1:05:24 PM
 */
public class MaximumConsecutiveOnesWithKReplacement {

	public static void main(String[] args) {
		test(new int[] { 0 }, 5); // 1
		test(new int[] { 1, 1 }, 5); // 2
		test(new int[] { 0, 1, 0 }, 1); // 2
		test(new int[] { 0, 1, 0 }, 0); // 1
		test(new int[] { 0, 1, 0 }, 2); // 3
		test(new int[] { 0, 1, 0 }, 5); // 3
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 1); // 1
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 2); // 2
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 3); // 3
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 4); // 4
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 5); // 5
		test(new int[] { 0, 0, 0, 0, 0, 0, 0 }, 6); // 6
	}

	private static void test(final int[] nums, final int k) {
		System.out.println(findMaxConsecutiveOnesWithKReplacement(nums, k));
	}

	private static int findMaxConsecutiveOnesWithKReplacement(int[] A, int K) {
		int maxLength = 0;
		int start = 0;
		int end = 0;
		while (end < A.length) {
			if (A[end] == 0) {
				if (K > 0) {
					K--;
				} else {
					while (A[start] != 0) {
						start++;
					}
					start++;
				}
			}
			maxLength = Math.max(maxLength, end - start + 1);
			end++;
		}
		return maxLength;
	}
}
