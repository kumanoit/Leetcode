package com.kumanoit.array;

/**
 * https://leetcode.com/problems/smallest-range-i/
 * 
 * Approach: In order to reduce the difference, we need to first find min and
 * max values from array and try to bring them closer. The values can be
 * increased/decreased by k. Thus we can increase smaller number at max by k and
 * reduce bigger number at max by k. So, both number can be brought closed to
 * each other by at max of 2*k
 * 
 * Complexity:
 * Time: O(n) for iterating array and finding out min and max values.
 * Space: O(1) no extra memory is taken
 * 
 * @author kumanoit 23-Jan-2020 12:42:14 AM
 */
public class SmallestRange1 {

	public static void main(String[] args) {
		test(new Integer[] { 1, 2 }, 0);
		test(new Integer[] { 1, 2 }, -1);
		test(new Integer[] { 1, 2 }, -2);
		test(new Integer[] { 1, 2 }, -3);
		test(new Integer[] { -1, -2 }, -0);
		test(new Integer[] { -1, -2 }, -1);
		test(new Integer[] { -1, -2 }, -2);
		test(new Integer[] { -1, -2 }, -3);
	}

	private static void test(final Integer[] array, int k) {
		System.out.println(smallestRangeI(array, k));
	}

	public static int smallestRangeI(final Integer[] A, final int K) {
		int min = A[0];
		int max = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] < min) {
				min = A[i];
			} else if (A[i] > max) {
				max = A[i];
			}
		}
		int smallestRange = Math.abs(max - min) - 2 * K;
		return smallestRange <= 0 ? 0 : smallestRange;
	}
}
