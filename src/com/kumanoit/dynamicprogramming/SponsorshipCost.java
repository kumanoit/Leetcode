package com.kumanoit.dynamicprogramming;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/12/22 IST 5:56 PM
 */
public class SponsorshipCost {

    public static void main(String[] args) {
        System.out.println(test(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
        System.out.println(test(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15}));
        System.out.println(test(new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2, 7, 15}));
    }

    private static int test(int[] days, int[] costs) {
        int[][] costss = new int[][]{{1, costs[0]}, {7, costs[1]}, {30, costs[2]}};
        return minSponsorshipCost(days, costss);
    }

    public static int minSponsorshipCost(int[] days, int[][] costs) {
        int[] dp = new int[366];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // cost of sponsoring 0-th day is 0
        int k = 0;
        int size = days.length;
        int lastDay = days[size - 1];
        int dayIndex = 0;
        for (int currentDay = 1; currentDay <= lastDay; currentDay++) {
            if (currentDay == days[dayIndex]) {
                // this means that we need to have job live on this current day
                int minCost = Integer.MAX_VALUE;
                // finding min cost for the current day using all sponsorship costs
                for (int j = 0; j < costs.length; j++) {
                    int dayCovered = costs[j][0];
                    int cost = costs[j][1];
                    dp[currentDay] = Math.min(
                            dp[currentDay],
                            // this check is for if we can lookup few days back based on dayCovered or not
                            currentDay > dayCovered ?
                                    dp[currentDay - dayCovered] + cost :
                                    cost
                    );
                }
                // incrementing this so as to consider next day when sponsorship is needed
                dayIndex++;
            } else {
                dp[currentDay] = dp[currentDay - 1];
            }
        }
        return dp[lastDay];
    }

    private static void printArray(int[] array, int days) {
        for(int i = 0; i <= days; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }
}
