package com.ukgfreak;

import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/4/22 IST 6:37 PM
 */
public class SampleCode {
    public static void main(String[] args) {

    }

    public static int cardPackets(List<Integer> cardTypes) {
        int sol = Integer.MAX_VALUE;
        int n = 2;
        boolean shouldRun = true;
        while(shouldRun) {
            int needed = 0;
            for(int i = 0; i < cardTypes.size(); i++) {
                if (cardTypes.get(i) < n) {
                    shouldRun = false;
                    return sol;
                }
                if (cardTypes.get(i) % n != 0) {
                    needed += n - cardTypes.get(i) % n;
                }
            }
            sol = Math.min(sol, needed);
            n++;
        }
        return sol;
    }
}
