package com.kumanoit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * 40. Combination Sum II
 * <p>
 * Approach:
 * It is very similar to https://github.com/kumanoit/Leetcode/blob/master/src/com/kumanoit/dynamicprogramming/CombinationSum.java
 * With only caveat that repeation and duplicates are avoided.
 * <p>
 * Complexity
 * 1. Time: In worst case all combination of number will be checked. For an array of length n, it would be closer to n^k
 * 2. Space: stack is used which will take space of O(target) if there is an element 1 in array.
 *
 * @author akumar on 8/5/20 IST 1:18 AM
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        test(new int[]{2, 3, 5}, 5);
        test(new int[]{2, 3, 5}, 7);
        test(new int[]{2, 3, 5}, 4);
        test(new int[]{2, 3, 5}, 8);
        test(new int[]{2, 3, 6, 7}, 0);
        test(new int[]{2, 3, 6, 7}, 7);
        test(new int[]{2, 5, 2, 1, 2}, 5);
        test(new int[]{2, 5, 2, 1, 2}, 6);
        test(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }

    private static void test(int[] candidates, int target) {
        List<List<Integer>> solution = combinationSum2(candidates, target);
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

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> solution = new ArrayList<>();
        // sorting is much needed to avoid duplication
        Arrays.sort(candidates);
        compute(candidates, target, solution, new ArrayList<Integer>(), 0);
        return solution;
    }

    private static void compute(int[] candidates, int target, List<List<Integer>> solution, List<Integer> subSolution, int start) {
        if (target == 0) {
            solution.add(new ArrayList<>(subSolution));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            // check to handle duplication
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            subSolution.add(candidates[i]);
            // i + 1 is used so that same candidate is not reused
            compute(candidates, target - candidates[i], solution, subSolution, i + 1);
            subSolution.remove(subSolution.size() - 1);
        }
    }
}
