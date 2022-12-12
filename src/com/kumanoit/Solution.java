package com.kumanoit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// time : in hrs = hh (00-12)
//tim in min = mm (00-59)
public class Solution {
    public static void main(String[] args) {
//        getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
//        getSkyline(new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 3}, {4, 5, 4}});
//        getSkyline(new int[][]{{0,1,3}});
//        getSkyline(new int[][]{{0,2,3},{2,5,3}});
//        System.out.println(countPairs(new int[]{2160, 1936, 3, 29, 27, 5, 2503, 1593, 2, 0, 16, 0, 3860, 28908, 6, 2, 15, 49, 6246, 1946, 23, 105, 7996, 196, 0, 2, 55, 457, 5, 3, 924, 7268, 16, 48, 4, 0, 12, 116, 2628, 1468}));
        System.out.println(countPairs2(new int[]{1, 3, 5, 7, 9}));
        System.out.println(countPairs2(new int[]{2,2,2,2,2,2,2}));
        System.out.println(countPairs2(new int[]{32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32}));
        System.out.println(countPairs2(new int[]{1, 1, 1, 3, 3, 3, 7}));
    }

    public static int countPairs2(int[] deliciousness) {
        int MOD = 1_000_000_007;
        long solution = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < deliciousness.length; i++) {
            if (!map.containsKey(deliciousness[i])) {
                map.put(deliciousness[i], 0);
            }
            map.put(deliciousness[i], map.get(deliciousness[i]) + 1);
        }
        int powerSum = 1;
        for (int i = 0; i <= 31; i++) {
            for (Integer key : map.keySet()) {

                if (map.containsKey(powerSum - key)) {
                    if (key == powerSum - key) {
                        solution += map.get(key) * (map.get(key) - 1);
                        System.out.println(key + " : " + (powerSum - key));
                    } else {
                        solution += map.get(key) * map.get(powerSum - key);
                        System.out.println(key + " : " + (powerSum - key));
                    }
                }
            }
            powerSum *= 2;
        }

        solution = solution / 2;
        return (int) (solution % MOD);
    }

    public static int countPairs(int[] deliciousness) {
        int MOD = 1_000_000_007;
        long solution = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < deliciousness.length; i++) {
            if (!map.containsKey(deliciousness[i])) {
                map.put(deliciousness[i], 0);
                list.add(deliciousness[i]);
            }
            map.put(deliciousness[i], map.get(deliciousness) + 1);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                int sum = list.get(i) + list.get(j);
                if ((sum > 0) && (sum & (sum - 1)) == 0) {
                    if (i == j) {
                        sum += map.get(i) * (map.get(j) - 1) / 2;
                    } else {
                        sum += map.get(i) * map.get(j);
                    }
                }
            }
        }
        System.out.println(solution);
        return (int) (solution % MOD);
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {

        int[][] heights = new int[buildings.length * 2][2];
        int k = 0;
        for (int i = 0; i < buildings.length; i++) {
            heights[k][0] = buildings[i][0];
            heights[k++][1] = buildings[i][2];
            heights[k][0] = -buildings[i][1]; // negative means that this height is ending
            heights[k++][1] = buildings[i][2];
        }
        printArray(heights);

        Arrays.sort(heights, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : (Math.abs(a[0]) == Math.abs(b[0]) ? (a[0] > 0 ? -1 : 1) : (Math.abs(a[0]) - Math.abs(b[0])));
        });

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(-1);
        int maxHeight = 0;
        printArray(heights);
        List<List<Integer>> solution = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            System.out.print("\n" + heights[i][0] + ", ");
            if (heights[i][0] > 0) {
                if (heights[i][1] > queue.peek()) {
                    System.out.println(" Pushing: " + heights[i][1]);
                    queue.add(heights[i][1]);
                    List<Integer> subsol = new ArrayList<Integer>();
                    subsol.add(heights[i][0]);
                    subsol.add(heights[i][1]);
                    solution.add(subsol);
                } else {
                    queue.add(heights[i][1]);
                }
            } else {
                if (queue.peek() == heights[i][1]) {
                    queue.remove();
                    List<Integer> subsol = new ArrayList<Integer>();
                    subsol.add(Math.abs(heights[i][0]));
                    subsol.add(queue.peek());
                    solution.add(subsol);
                } else {
                    queue.remove(heights[i][1]);
                }
            }
        }
        return solution;
    }

    private static void printArray(int[][] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i][0] + ", " + array[i][1] + "\t\t");
        }
    }

//    private int compare(int[] a, int[] b) {
//        if (a[0] == b[0]) {
//            return a[1] - b[1];
//        }
//        if (a[0] * b[0] < 0) {
//            return Math.max(a[0], b[0]);
//        }
//    }
}

