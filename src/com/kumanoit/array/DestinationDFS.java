package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @author akumar on 10/13/22 IST 7:59 PM
 */
public class DestinationDFS {

    public static void main(String[] args) {
        System.out.println(solve(Arrays.asList(1, 1, 1, 3, 3, 2, 2, 7, 6), 9, 1));
    }

    public static int solve(List<Integer> A, final int B, final int C) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < A.size(); i++) {
            if (!graph.containsKey(A.get(i))) {
                graph.put(A.get(i), new ArrayList<>());
            }
            graph.get(A.get(i)).add(i + 1);
        }
        graph.forEach((k, v) -> {
            System.out.print(k + " :> ");
            v.forEach(x -> System.out.print(x + ", "));
            System.out.println();
        });
        return isReachable(graph, C, B, new boolean[A.size() + 1], new boolean[A.size() + 1]);
    }

    private static int isReachable(Map<Integer, List<Integer>> graph, int source, int dest, boolean[] isVisited,
                                   boolean[] isFeasible) {
        if (isVisited[source]) {
            return 0;
        }

        if (source == dest) {
            return 1;
        }

        isVisited[source] = true;
        boolean isFound = false;
        if (graph.containsKey(source)) {
            for (int child : graph.get(source)) {
                if (isReachable(graph, child, dest, isVisited, isFeasible) == 1) {
                    isFound = true;
                    break;
                }
            }
        }
        isFeasible[source] = isFound;
        isVisited[source] = false;
        return isFound ? 1 : 0;
    }
}
