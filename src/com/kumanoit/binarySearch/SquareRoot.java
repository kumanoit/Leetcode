package com.kumanoit.binarySearch;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/7/21 IST 6:19 PM
 */
public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(getSquareRoot(50,3));
    }

    private static double getMaximumArray(int[] array, int k) {
        int l = 0;
        int h = array.length - 1;
        int min = Integer.MIN_VALUE;
        while(l <= h) {
            int mid = l + (h - l) / 2;
            if (array[mid] < k) {
                min = array[mid];
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return min;
    }

    private static double getSquareRoot(int num, int precision) {
        double low = 0;
        double high = num;
        double sqr = 0.0;
        double mid = 0.0;
        do {
            mid = low + (high - low) / 2.0;
            sqr = mid * mid;
            if (num - sqr >= Math.pow(10, -precision)) {
                break;
            }
            if (num > sqr) {
                low = mid;
            } else {
                high = mid;
            }
        } while(true);
        return mid;
    }
}
