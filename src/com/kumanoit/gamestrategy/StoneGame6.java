package com.kumanoit.gamestrategy;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/22/22 IST 7:26 PM
 */
public class StoneGame6 {
    public static void main(String[] args) {
        test(new int[]{9, 8, 3, 8}, new int[]{10, 6, 9, 5});
    }

    private static void test(int[] aliceValues, int[] bobValues) {
        System.out.println(stoneGameVI(aliceValues, bobValues));
    }

    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] diff = new int[n][2];
        for (int i = 0; i < n; i++) {
            diff[i] = new int[]{i, aliceValues[i] - bobValues[i]};
        }
        Arrays.sort(diff, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < diff.length; i++) {
            System.out.println(diff[i][0] + " " + diff[i][1]);
        }
        System.out.println();
        int start = 0;
        int end = n - 1;
        int aSum = 0;
        int bSum = 0;
        boolean aChoice = true;
        while (start <= end) {
            if (aChoice) {
                if (Math.abs(diff[start][1]) > Math.abs(diff[end][1])) {
                    aSum += aliceValues[diff[start++][0]];
                } else {
                    aSum += aliceValues[diff[end--][0]];
                }
            } else {
                if (Math.abs(diff[start][1]) > Math.abs(diff[end][1])) {
                    bSum += bobValues[diff[start++][0]];
                } else {
                    bSum += bobValues[diff[end--][0]];
                }
            }
            aChoice = !aChoice;
        }
        System.out.println("asum = " + aSum + " bSum= " + bSum);
        return aSum > bSum ? 1 : aSum < bSum ? -1 : 0;
    }
}
