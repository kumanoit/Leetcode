package com.kumanoit.Strings;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 * 
 * @author kumanoit 18-Dec-2019 8:34:59 PM
 * 
 *         Algorithm: Traverse string character by character and keep on
 *         generating number This has many edge cases. Check test cases to
 *         understand all cases that need to be covered
 */
public class StringToInteger {

	public static void main(String[] args) {
		test("+0 234");
		// happy case
		test("238");
		test("+238");
		test("-238");

		// sign check
		test("0");
		test("+");
		test("-");
		test("+    ");
		test("-   ");
		test("   -   ");
		test("   +  ");

		// zero handling
		test("+00");
		test("-00");

		// no digits only text
		test("no digits     ");

		// digits with text
		test("238 with words");
		test("with 238 words");
		test("238with words");

		// multiple sign
		test("+238-");
		test("-238+");
		test("++238");
		test("--238");

		// multiple space
		test(" 9  305");
		test(" +9  305");
		test(" -9  305");

		// multiple sign with string
		test("++238 words");
		test("--238 words");
		test("238+ words");
		test("238- words");
		test("238+305 words");
		test("238-305 words");
		test("238+- words");
		test("238-+ words");
		test("238+-305 words");
		test("238-+305 words");
		test("238++ words");
		test("238-- words");

		// integer limit test
		test("2147483647");
		test("-2147483648");
		test("2147483648");
		test("-2147483647");

		// -ve test case for limit
		test("4143056690");
		test("9999999999");
		test("-4143056690");
		test("-9999999999");
	}

	private static void test(String input) {
		System.out.println(input + "\t" + myAtoi(input));
	}

	public static int myAtoi(String str) {
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		str = str.trim();
		int number = 0;
		int sign = 1;
		boolean isSignFound = false;
		boolean isDigitFound = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ' && !isDigitFound) {
				continue;
			}
			if (str.charAt(i) == '+' && !isSignFound) {
				sign = 1;
				isSignFound = true;
			} else if (str.charAt(i) == '-' && !isSignFound) {
				sign = -1;
				isSignFound = true;
			} else if (!isDigit(str.charAt(i))) {
				break;
			} else {
				isDigitFound = true;
				isSignFound = true;
				int diff = (str.charAt(i) - '0');
				int newNumber = number * 10;
				if (number == newNumber / 10) {
					number = newNumber + diff * sign;
					if (sign == 1 && number < 0) {
						number = max;
						break;
					} else if (sign == -1 && number > 0) {
						number = min;
						break;
					}
				} else {
					if (sign == 1) {
						number = max;
						break;
					} else {
						number = min;
						break;
					}
				}
			}
		}
		return number;
	}

	private static boolean isDigit(char ch) {
		return ch >= '0' && ch <= '9';
	}
}
