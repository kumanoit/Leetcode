package com.kumanoit.dynamicprogramming;

/**
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/submissions/
 * 
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 * 
 * Approach DP:
 * This is solved like Matrix Chain Multiplication.
 * 1. First save xor of all value from index i to j in memo[i][j]
 * 2. Then use three loop.
 * 2.1 first loop will fix i
 * 2.2 second loop will fix j
 * 2.3 third loop will iterate for k from i + 1 to j
 * In this loop check if memo[i][k - 1] == memo[k][j] then increment counter.
 * 3. return counter.
 * 
 * Complexity:
 * Time: O(n^3): mainly due to three loops
 * Space: O(n^2)
 * 
 * 
 * Approach Optimized DP:
 * Create prefix and suffix xor arrays
 * when prefix[0...i-1] ^ prefix[0....k] == suffix[k + 1] ^ suffix[j + 1]
 * increment counter
 * 
 * 
 * Best Optimization DP;
 * Create only one prefix array and use it.
 * @author kumanoit May 11, 2020 7:28:29 PM
 *
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {

	public static void main(String[] args) {
		test(new int[] { 1 });
		test(new int[] { 2, 3 });
		test(new int[] { 1, 2, 3 });
		test(new int[] { 2, 3, 1, 6, 7 });
		test(new int[] { 1, 1, 1, 1, 1 });
		test(new int[] { 2, 3 });
		test(new int[] { 1, 3, 5, 7, 9 });
		test(new int[] { 7, 11, 12, 9, 5, 2, 7, 17, 22 });
	}

	private static void test(final int[] array) {
		System.out.println("\nCount DP = " + countTripletsDP(array));
		System.out.println("Count optimized DP = " + countTripletOptimized(array));
		System.out.println("Best optimized DP = " + countTripletDPOptimized(array));
	}

	/**
	 * This is the best approach
	 * Make a prefix array and use it to find i and k
	 * xor of elements from index [i...(j-1)] = prefix[i-1]^prefix[j-1]
	 * xor of elements from index [j.......k] = prefix[j-1]^prefix[k]
	 * Above both expression should be equal. which means that prefix[j-1] will cancel out.
	 * Using 2 nested loops i and k such that 
	 * prefix[i-1] = prefix[k]
	 * 
	 * Complexity:
	 * Time: O(n^2) nested loops
	 * Space: O(n) prefix array
	 * @param array
	 * @return
	 */
	private static int  countTripletDPOptimized(int[] array) {
		int n = array.length;
		int[] prefix = new int[n];
		prefix[0] = array[0];
		for(int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] ^ array[i];
		}
		// xor of elements from index [i...(j-1)] = prefix[i-1]^prefix[j-1]
		// xor of elements from index [j.......k] = prefix[j-1]^prefix[k]
		// prefix[i-1] = prefix[k]
		int count = 0;
		for(int i = 0; i < n - 1; i++) {
			for(int k = i; k < n; k++) {
				if  ((i == 0 && prefix[k] == 0) || (i > 0 && prefix[i - 1] == prefix[k])) {
					count += (k - i);
				}
			}
		}
		return count;
	}

	private static int countTripletOptimized(int[] array) {
		int n = array.length;
		int[] prefix = new int[n + 2];
		int[] suffix = new int[n + 2];
		prefix[1] = array[0];
		suffix[n] = array[n - 1];
		for(int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] ^ array[i];
			suffix[n - i] = suffix[n - i + 1] ^ array[n - i - 1];
		}
		
		int count = 0;
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n; j++) {
				for(int k = i + 1; k <= j; k++) {
					if ((prefix[i] ^ prefix[k]) == (suffix[k + 1] ^ suffix[j + 2])) {
//						System.out.println(i + ", " + k + ", " + j);
						count++;
					}
				}
			}
		}
		return count;
	}
	private static int countTripletsDP(int[] arr) {
		int n = arr.length;
		int[][] memo = new int[n][n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			memo[i][i] = arr[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				memo[i][j] = memo[i][j - 1] ^ arr[j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = i + 1; k <= j; k++) {
					if (memo[i][k - 1] == memo[k][j]) {
//						System.out.println(i + ", " + k + ", " + j);
						count++;
					}
				}
			}
		}
//		printMatrix(memo);
		return count;
	}

	private static void printMatrix(int[][] memo) {
		System.out.println();
		for (int i = 0; i < memo.length; i++) {
			System.out.println();
			for (int j = 0; j < memo[i].length; j++) {
				System.out.print(Integer.toBinaryString(memo[i][j]) + " ");
			}
		}
	}

}
