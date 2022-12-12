package com.satwik;

import java.util.Arrays;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 4/26/22 IST 9:46 PM
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(countDecreasingRatings(Arrays.asList(4,3,5,4,3)));
        System.out.println(countDecreasingRatings(Arrays.asList(2,1,3)));
        System.out.println(countDecreasingRatings(Arrays.asList(4,2,3,1)));
    }
    public static long countDecreasingRatings(List<Integer> ratings) {
        if (ratings.isEmpty()) {
            return 0;
        }
        long solution = 1;
        long lastLength = 1;
        for(int i = 1; i < ratings.size(); i++) {
            if (ratings.get(i) + 1 == ratings.get(i - 1)) {
                lastLength++;
            } else {
                lastLength = 1;
            }
            solution += lastLength;
        }
        return solution;
    }
}
