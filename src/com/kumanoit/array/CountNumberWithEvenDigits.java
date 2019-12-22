package com.kumanoit.array;

/**
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 * 
 * @author kumanoit
 * 22-Dec-2019 4:22:58 PM
 */
public class CountNumberWithEvenDigits {

	public static void main(String[] args) {
		test(new int[] { 1, 2, 3, 4 }); // 0
		test(new int[] { 1, 22, 333, 4444 }); // 2
		test(new int[] { 11, 22, 33, 44 }); // 4
	}

	private static void test(int[] nums) {
		System.out.println(findNumbers(nums));
	}

	public static int findNumbers(int[] nums) {
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			if (getDigitCount(nums[i]) % 2 == 0) {
				total++;
			}
		}
		return total;
	}

	private static int getDigitCount(int number) {
		int count = 0;
		while (number > 0) {
			count++;
			number /= 10;
		}
		return count;
	}
}
