package com.kumanoit.bitwise;

/**
 * https://leetcode.com/problems/power-of-four/
 * 342. Power of Four
 * <p>
 * Approach 1:
 * Every number x which is some power of 4 (x=4^y) will also be power of 2 (x=(2*2)^y).
 * So, we can put a first check that if a number is not power of 2 then it can never be power of 4. Any number which is
 * power of 2 will have only one set bit and it can be checked using this condition ((num & (num - 1)) == 0)
 * Now, for a number to be power of 4, it must have even number of 0 in binary notation on right side of only set bit.
 * Ex. 4 = 100, 16 = 10000, 64 = 1000000 and so on. So we can use bit wise operator to count number of zeros after that
 * set bit. Since we have to check only for count to be even we can do it using boolean variable.
 *
 * Complexity
 * 1. Time: O(1) because every time loop will run for at most 32 times
 * 2. Space: O(1) no extra space which depends on input
 * <p>
 *
 * <p>
 * Approach 2:
 * 1. first check that if a number is not power of 2
 * 2. check if that set bit is present at even place. since here number is 32 bits long, we can mask number with
 * 0x555555555 and resultant number should not be 0
 * <p>
 *
 * Complexity
 * 1. Time: O(1)
 * 2. Space: O(1)
 *
 * @author akumar on 8/4/20 IST 9:22 PM
 */
public class PowerOf4 {

    public static void main(String[] args) {
        test(-2147483648);
        test(-64);
        test(-16);
        test(-8);
        test(-4);
        test(-1);
        test(0);
        test(1);
        test(2);
        test(4);
        test(5);
        test(6);
        test(8);
        test(10);
        test(12);
        test(16);
        test(24);
        test(64);
        test(128);
        test(256);
        test(1073741824);
        test(2147483647);
    }

    private static void test(int number) {
        System.out.println("Is " + number + " power of 4? " + isPowerOf4(number));
        System.out.println("Is " + number + " power of 4? " + isPowerOf4SimpleSolution(number));
        System.out.println();
    }

    private static boolean isPowerOf4(int number) {
        if (number <= 0) {
            return false;
        }
        if (!isPowerOf2(number)) {
            return false;
        }
        int temp = number;
        boolean isPowerOf4 = true;
        while (temp != 1) {
            temp >>= 1;
            isPowerOf4 = !isPowerOf4;
        }
        return isPowerOf4;
    }

    private static boolean isPowerOf2(int number) {
        return (number & (number - 1)) == 0;
    }

    private static boolean isPowerOf4SimpleSolution(int number) {
        return (number > 0) && (isPowerOf2(number)) && ((number & 0x55555555) != 0);
    }

}
