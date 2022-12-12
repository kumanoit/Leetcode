package com.kumanoit.array;

import java.util.ArrayList;
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
 * @author akumar on 12/28/20 IST 11:37 AM
 */
public class MinJumps4 {

    public static void main(String[] args) {
//        test(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404});
        test(new int[]{8, 9, 8, 11});
        test(new int[]{8, 9, 8, 8});
    }

    public static void test(int[] arr) {
        System.out.println("\ncost = " + minJumps(arr));
    }

    public static int minJumps(int[] arr) {
        Map<Integer, List<Integer>> valueIndex = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!valueIndex.containsKey(arr[i])) {
                valueIndex.put(arr[i], new ArrayList<>());
            }
            valueIndex.get(arr[i]).add(i);
        }

        boolean[] isVisited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int totalCost = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer popped = queue.remove();
                isVisited[popped] = true;
                List<Integer> neighbours = valueIndex.get(arr[popped]);

                neighbours.add(popped - 1);
                neighbours.add(popped + 1);
                for (int neigh : neighbours) {
                    if (neigh == arr.length - 1) {
                        return totalCost;
                    }
                    if (neigh >= 0 && neigh < arr.length && !isVisited[neigh]) {
                        queue.add(neigh);
                        isVisited[neigh] = true;
                    }
                }
            }
            totalCost++;
        }

        return 0;
    }
}
