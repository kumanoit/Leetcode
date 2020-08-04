package com.kumanoit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/combination-sum
 * 39. Combination Sum
 *
 * Approach:
 * Solve this recursively. Create two variables:
 * subSolution: list of all integers whose sum is equal to target
 * solution: which is list of all subSolution
 * In recursive call, form the list of numbers which sums to target using DFS approach. When target is met save that
 * subSolution list in solution. Do this for all combinations.
 *
 * Complexity
 * 1. Time: In worst case all combination of number will be checked. For an array of length n, it would be closer to n^k
 * 2. Space: stack is used which will take space of O(target) if there is an element 1 in array.
 *
 * @author akumar on 8/4/20 IST 1:37 AM
 */
public class CombinationSum {

    public static void main(String[] args) {
        test(new int[]{2, 3, 5}, 5);
        test(new int[]{2, 3, 5}, 7);
        test(new int[]{2, 3, 5}, 4);
        test(new int[]{2, 3, 5}, 8);
        test(new int[]{2, 3, 6, 7}, 0);
        test(new int[]{2, 3, 6, 7}, 7);
    }

    private static void test(int[] candidates, int target) {
        System.out.println("Input: " + Arrays.asList(candidates));
        System.out.println("Solution 1: ");
        List<List<Integer>> combinationSum = getCombinationSum(candidates, target);
        printList(combinationSum);

        List<List<Integer>> solution = new ArrayList<>();
        calculate(candidates, target, solution, new ArrayList<>(), 0);
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

    private static void calculate(int[] candidates, int target, List<List<Integer>> solution, List<Integer> subSolution,
                                  int start) {
        if (target == 0) {
            solution.add(new ArrayList<>(subSolution));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            subSolution.add(candidates[i]);
            calculate(candidates, target - candidates[i], solution, subSolution, i);
            subSolution.remove(subSolution.size() - 1);
        }
    }

    private static List<List<Integer>> getCombinationSum(int[] candidates, int target) {
        Map<Integer, List<List<Integer>>> memo = new HashMap<>();
        memo.put(0, new ArrayList<>());
        memo.get(0).add(new ArrayList<>());
        Arrays.sort(candidates);
        for (int candidate : candidates) {
            for (int i = candidate; i <= target; i++) {
                int diff = i - candidate;
                if (memo.containsKey(diff)) {
                    if (!memo.containsKey(i)) {
                        memo.put(i, new ArrayList<>());
                    }
                    List<List<Integer>> presentList = new ArrayList<>();
                    for (List<Integer> list : memo.get(diff)) {
                        List<Integer> newlist = new ArrayList<>(list);
                        newlist.add(candidate);
                        presentList.add(newlist);
                    }
                    memo.get(i).addAll(presentList);
                }
            }
        }
        return memo.get(target);
    }
}
