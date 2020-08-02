package com.kumanoit.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 * 310. Minimum Height Trees
 * <p>
 * Approach:
 * 1. Find all leaf nodes
 * 2. Remove all leaf nodes find out in step 1.
 * 3. This will create few new leaf nodes. So repeat step 1 and 2 until there are 1 or 2 nodes left.
 * Those nodes which are left forms the solution.
 * Complexity
 * 1. Time: O(n) Since each node is traversed once.
 * 2. Space: O(n) for queue.
 *
 * @author akumar on 7/27/20 IST 11:37 PM
 */
public class MinimumHeightTree {

    public static void main(String[] args) {
        test(0, new int[][]{});
        test(1, new int[][]{{}});
        test(2, new int[][]{{0, 1}});
        test(2, new int[][]{{0, 1}, {1, 2}});
        test(4, new int[][]{{1, 0}, {1, 2}, {1, 3}});
        test(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}});
    }

    private static void test(int n, int[][] edges) {
        System.out.println("By method 1: " + findMinHeightTrees(n, edges));
        System.out.println("By method 2: " + findMinHeightTreeSimpleCode(n, edges));
    }

    public static List<Integer> findMinHeightTreeSimpleCode(int n, int[][] edges) {
        if (n == 0 || edges.length == 0 || edges[0].length == 0) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.put(edges[i][0], new ArrayList<>());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            if (!graph.containsKey(edges[i][1])) {
                graph.put(edges[i][1], new ArrayList<>());
            }
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (Integer key : graph.keySet()) {
            if (graph.get(key).size() == 1) {
                leaves.add(key);
            }
        }
        while (n > 2) {
            n -= leaves.size();
            final List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                graph.get(leaf).forEach(neighbour -> {
                    graph.get(neighbour).remove(Integer.valueOf(leaf));
                    if (graph.get(neighbour).size() == 1) {
                        newLeaves.add(neighbour);
                    }
                });
                leaves = newLeaves;
            }
        }
        return leaves;
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) {
            return new ArrayList<>();
        }
        if (edges.length == 0 || edges[0].length == 0) {
            // just to pass leetcode test cases.
            return Arrays.asList(0);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(null);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.put(edges[i][0], new ArrayList<Integer>());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            if (!graph.containsKey(edges[i][1])) {
                graph.put(edges[i][1], new ArrayList<Integer>());
            }
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        int count = n;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                queue.add(entry.getKey());
                count--;
            }
        }
        while (true) {
            Integer popped = queue.remove();
            if (popped == null) {
                if (queue.size() <= 2 && count == 0) {
                    break;
                }
                queue.add(null);
            } else {
                Integer friend = graph.get(popped).get(0);
                graph.get(friend).remove(popped);
                if (graph.get(friend).size() == 1) {
                    queue.add(friend);
                    count--;
                }
            }
        }
        return new ArrayList<>(queue);
    }
}
