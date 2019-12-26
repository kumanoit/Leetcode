package com.kumanoit.others;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/ 
 * 
 * Approach: Keep on generating
 * square sum numbers and put in a Set. The moment square sum becomes 1 return
 * true otherwise return false if set contains same square sum.
 * 
 * @author kumanoit 26-Dec-2019 11:34:51 PM
 */
public class HappyNumber {

	public static void main(String[] args) {
		test(21);
	}

	private static void test(int n) {
		System.out.println(isHappy(n));
	}

	public static boolean isHappy(int n) {
		int num = n;
		int squareSum = 0;
		Set<Integer> set = new HashSet<>();
		do {
			set.add(num);
			squareSum = getSquareSum(num);
			if (squareSum == 1) {
				return true;
			}
			num = squareSum;
			System.out.println(num);
		} while (squareSum != n && !set.contains(num));
		return false;
	}

	private static int getSquareSum(int n) {
		int sum = 0;
		while (n > 0) {
			int remainder = n % 10;
			sum += remainder * remainder;
			n /= 10;
		}
		return sum;
	}
}
