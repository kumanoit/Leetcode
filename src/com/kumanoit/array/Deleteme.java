package com.kumanoit.array;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/6/22 IST 8:14 PM
 */
public class Deleteme {
    public static void main(String[] args) {
        test(740819855);
        test(2147483647);
        test(4);
        test(11);
    }

    private static void test(int A) {
        int root = sqrt(A);
        int sq1 = root * root;
        int sq2 = (root + 1) * (root + 1);
        if (sq1 == A || (sq1 < A && sq2 > A)) {
            System.out.println("valid: " + root);
        } else {
            System.out.println("invalid: " + root);
        }
    }
    public static int sqrt(int A) {
        if (A == 0) {
            return A;
        }
        long low = 1;
        long high = 2;
        while(high * high < A) {
            low = high;
            high *= 2;
        }
        if (high * high == A) {
            return (int) high;
        }
        while(low <= high) {
            long mid = low + (high - low) / 2;
            long sq = mid * mid;
            if (sq == A) {
                return (int) mid;
            } else if (sq > A) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int)high;
    }
}
