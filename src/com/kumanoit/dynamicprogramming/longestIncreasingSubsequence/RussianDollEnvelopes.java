package com.kumanoit.dynamicprogramming.longestIncreasingSubsequence;

import java.util.Arrays;
import java.util.Collections;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/23/21 IST 9:45 PM
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) ->
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
        );
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            memo[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }
        return memo[n - 1];
    }
}
