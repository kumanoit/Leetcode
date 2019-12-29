package com.kumanoit.array;

/**
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 * 
 * Time: O(n) 
 * Space: O(1) not counting size of array that has to be returned.
 * 
 * @author kumanoit 29-Dec-2019 10:53:03 AM
 */
public class FindNUniqueIntegersSumuptoZero {

	public static void main(String[] args) {
		test(1);
		test(2);
		test(5);
		test(9);
		test(1000);
	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}

	private static int[] sumZero(int n) {
		int start = n / 2;
		int[] solution = new int[n];
		for (int i = 0, j = n - 1; i < j; i++, j--) {
			solution[i] = -start;
			solution[j] = start;
			start--;
		}
		return solution;
	}

	private static void test(int n) {
		printArray(sumZero(n));
	}

}
