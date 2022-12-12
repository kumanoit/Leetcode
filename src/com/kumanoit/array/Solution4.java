package com.kumanoit.array;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/4/22 IST 7:17 PM
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println(deleteString("aaaa"));
    }

    public static int deleteString(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return compute(s, 0, memo);
    }

    private static int compute(String s, int start, int[] memo) {
        if (start == s.length()) {
            return 0;
        }

        if (memo[start] != -1) {
            return memo[start];
        }

        int max = 0;
        for(int i = start + 1; i < s.length(); i++) {
            String prefix = s.substring(start, i);
            if (s.startsWith(prefix, i)) {
                max = Math.max(max, compute(s, i, memo));
            }
        }
        memo[start] = max + 1;
        return memo[start];
    }
}
