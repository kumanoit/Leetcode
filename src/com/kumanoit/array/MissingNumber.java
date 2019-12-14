package com.kumanoit.array;

/**
 * https://leetcode.com/problems/missing-number/submissions/
 * @author kumanoit
 * 14-Dec-2019 10:51:08 AM
 */
public class MissingNumber {

	public static void main(String[] args) {
		System.out.println(getMissingNumber(new int[] { 1 }));
		System.out.println(getMissingNumber(new int[] { 0 }));
		System.out.println(getMissingNumber(new int[] { 0, 1, 2, 3 }));
		System.out.println(getMissingNumber(new int[] { 1, 4, 3, 2, 0 }));
	}

	public static int getMissingNumber(int[] nums) {
		int missingNumber = nums.length;
		for (int i = 0; i < nums.length; i++) {
			missingNumber ^= nums[i] ^ i;
		}
		return missingNumber;
	}
}
