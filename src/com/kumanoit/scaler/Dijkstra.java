package com.kumanoit.scaler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author akumar on 10/21/22 IST 7:07 PM
 */
public class Dijkstra {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
//        input.add(getList(new int[]{0, 4, 9}));
//        input.add(getList(new int[]{3, 4, 6}));
//        input.add(getList(new int[]{1, 2, 1}));
//        input.add(getList(new int[]{2, 5, 1}));
//        input.add(getList(new int[]{2, 4, 5}));
//        input.add(getList(new int[]{0, 3, 7}));
//        input.add(getList(new int[]{0, 1, 1}));
//        input.add(getList(new int[]{4, 5, 7}));
//        input.add(getList(new int[]{0, 5, 1}));

        input.add(getList(new int[]{0, 3, 4}));
        input.add(getList(new int[]{2, 3, 3}));
        input.add(getList(new int[]{0, 1, 9}));
        input.add(getList(new int[]{3, 4, 10}));
        input.add(getList(new int[]{1, 3, 8}));

        solve(5, input, 4).forEach(x -> System.out.print(x + ", "));
    }

    private static ArrayList<Integer> getList(int[] input) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int x : input) {
            list.add(x);
        }
        return list;
    }

    public static ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B, int C) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, Integer> dist = new HashMap<>();
        for (ArrayList<Integer> list : B) {
            int s = list.get(0);
            int d = list.get(1);

            int distance = list.get(2);
            if (!graph.containsKey(s)) {
                graph.put(s, new ArrayList<>());
            }
            if (!graph.containsKey(d)) {
                graph.put(d, new ArrayList<>());
            }

            graph.get(s).add(new int[]{d, distance});
            graph.get(d).add(new int[]{s, distance});
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{C, 0});
        dist.put(C, 0);
        while (!queue.isEmpty()) {
            int[] pop = queue.remove();
            int nodeId = pop[0];
            int distance = pop[1];
            // if (dist[nodeId] != (int)1e9) {
            //     continue;
            // }

            if (graph.containsKey(nodeId)) {
                for (int[] children : graph.get(nodeId)) {
                    int childNodeId = children[0];
                    int childDistance = children[1];
                    if (!dist.containsKey(childNodeId) || dist.get(childNodeId) > distance + childDistance) {
                        dist.put(childNodeId, childDistance + distance);
                        queue.add(new int[]{childNodeId, dist.get(childNodeId)});
                    }
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            if (dist.containsKey(i)) {
                list.add(dist.get(i));
            } else {
                list.add(-1);
            }
        }
        return list;
    }
}
