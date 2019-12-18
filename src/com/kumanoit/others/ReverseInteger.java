package com.kumanoit.others;

/**
 * https://leetcode.com/problems/reverse-integer/submissions/
 * 
 * @author kumanoit
 * 19-Dec-2019 12:43:22 AM
 */
public class ReverseInteger {

	public static void main(String[] args) {
		test(123);
		test(-321);
		test(999999999);
		test(2147483647);
		test(-2147483648);
	}

	private static void test(int x) {
		System.out.println(reverse(x));
	}

	public static int reverse(int x) {
		int sign = x < 0 ? -1 : 1;
		int solution = 0;
		int number = sign == 1 ? x : -x;
		while (number > 0) {
			int remainder = number % 10;
			int product = solution * 10;
			if (product / 10 != solution) {
				return 0; // overflow
			}
			solution = product + remainder;
			if (solution < 0) {
				return 0;
			}
			number /= 10;
		}
		return solution * sign;
	}
}
