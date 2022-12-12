package com.ukgfreak;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/8/21 IST 8:50 AM
 */
public class CountSubarray {
    public static void main(String[] args) {
//        System.out.println(pthFactor(20, 3));
//        System.out.println(pthFactor(20, 1));
//        System.out.println(pthFactor(20, 5));
//        System.out.println(pthFactor(20, 6));
//        System.out.println(pthFactor(10, 5));
//        System.out.println(getSubarrayCount(new int[]{1,2,3,4}, 1));
    }

    private void generateNumbers(int[] factors, int p) {

    }
    private static long pthFactor(long n, long p) {
        if (p == 1) {
            return 1;
        }
        List<Integer> factors = new ArrayList<>();

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
            while (n % i == 0) {
                n /= i;
            }
        }

        int[] fact = new int[factors.size()];
        for(int i = 0; i < factors.size(); i++) {
            fact[i] = factors.get(i);
        }
        p--;
        if (factors.size() >= p) {
            return factors.get((int) (p - 1));
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < factors.size(); i++) {
            queue.add(factors.get(i));
        }
        return 0;
    }

    private static int getSubarrayCount(List<Integer> numbers, int k) {
        if (numbers.size() == 0) {
            return 0;
        }
        int solution = 0;
        int n = numbers.size();
        int[] oddCount = new int[n];
        int oddCountLength = 0;
        for (int i = 0; i < n; i++) {
            if ((numbers.get(i) & 1) == 1) {
                oddCountLength++;
            }
            oddCount[i] = oddCountLength;

            int countSoFar = 0;
            for (int j = 0; j <= i; j++) {
                if (oddCount[i] - countSoFar <= k) {
                    solution++;
                }
                if ((numbers.get(j) & 1) == 1) {
                    countSoFar++;
                }
            }
        }
        return solution;
    }

    private static void printArray(int[] nums, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
    }

}

//
//1
//2
//3
//5
//
//1,2,3,5
