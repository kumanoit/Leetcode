package com.kumanoit.binarySearch;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/7/21 IST 6:11 PM
 */
public class BinarySearch {

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 1);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 2);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 5);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 6);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 7);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 0);
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 8);
        test(new int[]{1, 2, 3, 5, 6, 7}, 4);
    }

    private static void test(int[] array, int k) {
        System.out.print(binarySearchIter(array, k) + ", ");
        System.out.println(binarySearchRecursive(array, 0, array.length - 1, k));
    }

    private static int binarySearchIter(int[] array, int k) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == k) {
                return mid;
            }
            if (array[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] array, int low, int high, int k) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (array[mid] == k) {
            return mid;
        }
        if (array[mid] > k) {
            return binarySearchRecursive(array, low, mid - 1, k);
        }
        return binarySearchRecursive(array, mid + 1, high, k);
    }
}
