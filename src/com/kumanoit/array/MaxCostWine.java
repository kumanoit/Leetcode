package com.kumanoit.array;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/7/22 IST 11:22 PM
 */
public class MaxCostWine {
    public static void main(String[] args) {

    }
    private static int getMaxCost(int[] wines) {
        int n = wines.length;
        Integer[][] memo = new Integer[n][n];
        return 0;
    }
    private static int compute(int[] wines, int start, int end, Integer[][] memo, int day) {
        if (memo[start][end] != null) {
            return day * memo[start][end];
        }
        if (start == end) {
            memo[start][end] = wines[start];
        } else if (start + 1 == end){

        } else {

        }
        return day * memo[start][end];
    }
}
