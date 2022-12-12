package com.ukgfreak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/5/22 IST 8:58 PM
 */
public class Question3 {
    public static void main(String[] args) {
//        test(8, new int[][]{{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}});
        test(5, new int[][]{{0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}});
    }

    private static void test(int size, int[][] input) {
        List<List<Integer>> solution = getAncestors(8, input);
        for(int i = 0; i < solution.size(); i++) {
            System.out.println(i + " : ");
            solution.get(i).forEach(item -> System.out.print(item + ", "));
            System.out.println();
        }
    }
    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>(n);
        List<Set<Integer>> descendants = new ArrayList<>(n);
        List<Set<Integer>> ancestors = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
            descendants.add(new HashSet<>());
            ancestors.add(new HashSet<>());
        }
        for(int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
        }


        for(int i = 0; i < n; i++) {
            compute(adjacencyList, descendants, ancestors, i);
        }
        final List<List<Integer>> solution = new ArrayList<>();
        for(Set<Integer> ancestor : ancestors) {
            List<Integer> sorted = new ArrayList<>(ancestor);
            Collections.sort(sorted);
            solution.add(sorted);
        }
        return solution;
    }

    private static void compute(List<List<Integer>> adjacencyList,
                                List<Set<Integer>> descendants,
                                List<Set<Integer>> ancestors,
                                int source) {
        if (!descendants.get(source).isEmpty()) {
            return;
        }

        Set<Integer> myDescendants = new HashSet<>();
        for(int child : adjacencyList.get(source)) {
            compute(adjacencyList, descendants, ancestors, child);
            myDescendants.addAll(descendants.get(child));
            myDescendants.add(child);
        }

        for(int desc : myDescendants) {
            ancestors.get(desc).add(source);
        }
        descendants.set(source, myDescendants);
    }

}
