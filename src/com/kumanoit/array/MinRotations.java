package com.kumanoit.array;

/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/submissions/
 * 
 * @author kumanoit 17-Dec-2019 2:00:07 AM
 * 
 *         Algorithm: First find the potential element which can be appear on
 *         all dices In worst case element should appear at least n times in
 *         total in both arrays of size n otherwise it can't be the solution. If
 *         the potential element appears n times, then other elements won't
 *         appear that many times. Once you have figured out the solution,
 *         iterate over both the array and check if that element is available in
 *         atleast one of the arrays.
 */
public class MinRotations {

	public static void main(String[] args) {
		int[] A = { 2, 2, 2, 3, 2, 1, 1, 1, 1, 1, 1 };
		int[] B = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2 };
		System.out.println(minDominoRotations(A, B));
	}

	public static int minDominoRotations(int[] A, int[] B) {
		int[] count = new int[6];
		for (int i = 0; i < A.length; i++) {
			count[A[i] - 1]++;
			count[B[i] - 1]++;
		}

		int maxCount = count[0];
		int maxElement = 1;
		for (int i = 1; i < count.length; i++) {
			if (maxCount < count[i]) {
				maxCount = count[i];
				maxElement = i + 1;
			}
		}
		// If there is only one value which is present in both arrays
		if (maxCount == 2 * A.length) {
			return 0;
		}
		int numberOfTimesANeedsSwap = 0;
		int numberOfTimesBNeedsSwap = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == maxElement && B[i] == maxElement) {
				continue;
			}
			if (A[i] != maxElement && B[i] != maxElement) {
				return -1;
			}
			if (A[i] != maxElement) {
				numberOfTimesANeedsSwap++;
			} else {
				numberOfTimesBNeedsSwap++;
			}
		}
		return Math.min(numberOfTimesANeedsSwap, numberOfTimesBNeedsSwap);
	}
}
