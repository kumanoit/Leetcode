package com.kumanoit.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/6/22 IST 6:11 PM
 */
public class Codex2 {
    public static void main(String[] args) {
        int[][] graph = {{1,2},{1,3},{2,3}};
        System.out.println(numberOfPaths(3, graph));
    }
    public static int numberOfPaths(int A, int[][] B) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] num : B) {
            int source = num[0];
            int destin = num[1];
            if (!graph.containsKey(source)) {
                graph.put(source, new ArrayList<>());
            }
            graph.get(source).add(destin);
        }

        Integer[] memo = new Integer[A + 1];
        compute(graph, 1, A, memo);
        return memo[1];
    }

    private static int compute(Map<Integer, List<Integer>> graph, int source, int destination, Integer[] memo) {
        if (source == destination) {
            return 1;
        }

        System.out.println("source = " + source);
        if (memo[source] != null) {
            return memo[source];
        }

        int total = 0;
        if (graph.containsKey(source)) {
            for(int dest : graph.get(source)) {
                total += compute(graph, dest, destination, memo);
            }
        }
        memo[source] = total;
        return total;
    }

}
