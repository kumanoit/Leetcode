package com.kumanoit.leetcodecontest;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/13/21 IST 12:00 PM
 */
public class MinimumNumberOfRefuelingStops {
    public static void main(String[] args) {
        System.out.println(minRefuelStops(100,10, new int[][]{{10,60},{20,30},{30,30},{60,40}}));
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel == target) {
            return 0;
        }
        Arrays.sort(stations, (a, b) -> a[0] - b[0]);
        int[] memo = new int[stations.length];
        int[] minFuel = new int[stations.length];
        if (startFuel < stations[0][0]) {
            return -1;
        }
        memo[0] = 1;
        minFuel[0] = stations[0][1] + startFuel - stations[0][0];
        for (int i = 1; i < stations.length; i++) {
            for (int j = 0; j < i; j++) {
                if (minFuel[j] >= stations[i][0]) {
                    memo[i] = memo[j] + 1;
                    minFuel[i] = minFuel[j] - stations[i][0] + stations[i][1];
                    break;
                }
            }
            if (stations[i][0] == target) {
                return memo[i];
            }
        }
        return -1;
    }
}
