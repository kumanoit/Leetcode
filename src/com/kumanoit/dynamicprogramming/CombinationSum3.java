package com.kumanoit.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * 216. Combination Sum III
 *
 * Approach:
 * It is very similar to https://github.com/kumanoit/Leetcode/blob/master/src/com/kumanoit/dynamicprogramming/CombinationSum.java
 * Only two more checks are more needed. One is that size of list should never get more than k
 * and duplicates should be avoided
 *
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/5/20 IST 1:37 AM
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        test(3, 7);
        test(3, 9);
    }

    private static void test(int k, int n) {
        List<List<Integer>> solution = combinationSum3(k, n);
        System.out.println("\nSolution 2: ");
        printList(solution);
    }

    private static void printList(final List<List<Integer>> combinationSum) {
        if (combinationSum != null || !combinationSum.isEmpty()) {
            combinationSum.forEach(list -> {
                list.forEach(item -> {
                    System.out.print(item + ", ");
                });
                System.out.println();
            });
        } else {
            System.out.println(combinationSum);
        }
        System.out.println();
    }

    private static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> solution = new ArrayList<>();
        compute(n, k, solution, new ArrayList<>(), 1);
        return solution;
    }

    private static void compute(int n, int k, List<List<Integer>> solution, List<Integer> subSolution, int start) {
        if (n == 0 && k == 0) {
            solution.add(new ArrayList<>(subSolution));
            return;
        }
        if (n <= 0 || k <= 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            subSolution.add(i);
            // k is decremented to ensure that only k elements should be used
            // i + 1 is used to avoid duplication of number
            compute(n - i, k - 1, solution, subSolution, i + 1);
            subSolution.remove(subSolution.size() - 1);
        }
    }
}
