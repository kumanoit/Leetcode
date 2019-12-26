package com.kumanoit.others;

/**
 * Arranging Coins
 * 
 * @author kumanoit 27-Dec-2019 12:07:21 AM
 */
public class ArrangingCoins {

	public static void main(String[] args) {
		test(0);
		test(1);
		test(5);
		test(8);
		test(234235);
		test(2147483647);
	}

	private static void test(int number) {
		System.out.println("Brute Force: " + arrangeCoins(number));
		System.out.println("Optimized Force: " + arrangeCoins2(number));
		System.out.println();
	}

	private static int arrangeCoins(int n) {
		if (n == 0) {
			return 0;
		}
		int count = 1;
		while (n >= count) {
			n -= count;
			count++;
		}
		return count - 1;
	}

	private static int arrangeCoins2(int n) {
		return (int) (-1 + Math.floor(Math.sqrt(1.0 + 8.0 * n))) / 2;
	}
}
