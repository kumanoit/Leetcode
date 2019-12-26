package com.kumanoit.others;

/**
 * https://leetcode.com/problems/ugly-number/
 * 
 * @author kumanoit 27-Dec-2019 12:16:18 AM
 */
public class UglyNumber {

	public static void main(String[] args) {
		test(0);
		test(1);
		test(12);
		test(15);
		test(2147483647);
		test(2345434);
	}

	private static void test(final int number) {
		System.out.println("Number: " + number + " : " + isUgly(number));
	}

	private static boolean isUgly(int num) {
		if (num == 1) {
			return true;
		}
		if (num == 0) {
			return false;
		}
		while (num % 5 == 0) {
			num /= 5;
		}
		while (num % 3 == 0) {
			num /= 3;
		}
		while (num % 2 == 0) {
			num /= 2;
		}
		return num == 1;
	}
}
