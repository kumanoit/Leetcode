package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/16/22 IST 10:17 PM
 */
public class ComponentWithSameValue {
    public static void main(String[] args) {
        System.out.println(componentValue(new int[]{6, 2, 2, 2, 6}, new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}}));
    }

    public static int componentValue(int[] nums, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for(int key : graph.keySet()) {
            if (graph.get(key).size() == 1) {
                leaves.add(key);
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if ((sum % i == 0) && runBFS(graph, Arrays.copyOf(nums, nums.length), sum / i, new LinkedList<>(leaves))) {
                return i;
            }
        }
        return -1;
    }

    private static boolean runBFS(Map<Integer, List<Integer>> graph, int[] nums, int desiredSum, Queue<Integer> leaves) {
        while (!leaves.isEmpty()) {
            int popped = leaves.remove();
            if (nums[popped] > desiredSum) {
                return false;
            }
            if (nums[popped] == desiredSum) {
                for (int x : graph.get(popped)) {
                    if (nums[x] != 0) {
                        leaves.add(x);
                    }
                }
            } else {
                for (int x : graph.get(popped)) {
                    if (nums[x] != 0) {
                        nums[x] += nums[popped];
                        leaves.add(x);
                    }
                }
            }
            nums[popped] = 0;
        }
        return true;
    }
}
