package com.kumanoit.others;

/**
 * https://leetcode.com/problems/arranging-coins/
 * 
 * @author kumanoit 27-Dec-2019 12:04:20 AM
 */
public class AddDigitsToSingleDigit {

	public static void main(String[] args) {
		test(0);
		test(1);
		test(2);
		test(3);
		test(4);
		test(5);
		test(6);
		test(7);
		test(8);
		test(9);
		test(18);
		test(15);
		test(9234234);
		test(84753);
		test(2147483647);
	}

	private static void test(final int number) {
		System.out.println("Solution for " + number + " = " + addDigits(number));
	}

	public static int addDigits(int num) {
		return num < 10 ? num : (num % 9 == 0 ? 9 : num % 9);
	}
}
