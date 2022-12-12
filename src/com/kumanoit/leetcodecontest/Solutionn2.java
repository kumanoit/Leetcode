package com.kumanoit.leetcodecontest;

import java.util.Arrays;
import java.util.Comparator;
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
 * @author akumar on 4/18/21 IST 8:21 AM
 */
public class Solutionn2 {
    public static void main(String[] args) {
        int a1 = 3;
        int b1 = 5;
        int b2 = 6;
        int val = (a1 & b1) ^ (a1 & b2);
        int val2 = (a1 ^ b1) & (b1 ^ b2);
        System.out.println(val + " " + val2);
//        test(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}});
//        test(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}});
//        test(new int[][]{{19, 13}, {16, 9}, {21, 10}, {32, 25}, {37, 4}, {49, 24}, {2, 15}, {38, 41}, {37, 34}, {33, 6}, {45, 4}, {18, 18}, {46, 39}, {12, 24}});
    }

    private static void test(int[][] tasks) {
        int[] order = getOrder(tasks);
        for (int item : order) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    public static int[] getOrder(int[][] tasks) {
        int[][] tasksIndex = new int[tasks.length][3];
        for (int i = 0; i < tasks.length; i++) {
            tasksIndex[i][0] = tasks[i][0];
            tasksIndex[i][1] = tasks[i][1];
            tasksIndex[i][2] = i;
        }

        Arrays.sort(tasksIndex, new Comparator<int[]>() {
            @Override
            public int compare(final int[] o1, final int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        Queue<int[]> availableTasks = new PriorityQueue<int[]>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b [1]);
        int[] result = new int[tasks.length];
        int k = 0;
        availableTasks.add(tasksIndex[0]);
        int lastInsertedIndexInQueue = 1;
        int timeElapsed = tasksIndex[0][0];
        while (!availableTasks.isEmpty() && k < tasks.length) {

            int[] info = availableTasks.remove();
            result[k++] = info[2];
            timeElapsed += info[1];
            while (lastInsertedIndexInQueue < tasks.length &&
                    tasksIndex[lastInsertedIndexInQueue][0] <= timeElapsed) {
                availableTasks.add(tasksIndex[lastInsertedIndexInQueue++]);
            }
        }
        return result;
    }

    public static int maxIceCream(int[] costs, int coins) {
        // int[] memo = new int[coins + 1];
        // Arrays.sort(costs);
        // // memo[0] = 1;
        // for(int cost : costs) {
        //     for(int i = cost; i <= coins; i++) {
        //         memo[i] = memo[i - cost] + 1;
        //     }
        // }

        Arrays.sort(costs);
        int[][] memo = new int[coins + 1][costs.length + 1];
        // Arrays.fill(memo[0], 1);
        int max = 0;
        for (int i = 1; i <= coins; i++) {
            System.out.println();
            for (int j = 1; j <= costs.length; j++) {
                if (costs[j - 1] <= i) {
                    memo[i][j] = memo[i - costs[j - 1]][j - 1] + 1;
                    max = Math.max(memo[i][j], max);
                    System.out.print(memo[i][j] + ", ");
                } else {
                    break;
                }
            }
        }
//        for (int[] rows : memo) {
//            System.out.println();
//            for (int i : rows) {
//                System.out.print(i + ", ");
//            }
//        }
        for (int i = 0; i < memo.length; i++) {
            System.out.println();
            for (int j = 0; j < memo[0].length; j++) {
                System.out.print(memo[i][j] + ",");
            }
        }
        System.out.println();
        return max;
    }
}
