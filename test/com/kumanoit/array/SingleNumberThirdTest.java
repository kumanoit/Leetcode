package com.kumanoit.array;

import com.kumanoit.utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/single-number-iii/
 * 260. Single Number III
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/23/20 IST 11:06 PM
 */
public class SingleNumberThirdTest {

    private static final Map<Integer[], Integer[]> testcases = new HashMap<>();

    public static boolean test() {
        testcases.forEach((input, expectedOutput) -> {
            Integer[] actualOutput = SingleNumberThird.getSingleNumber(input);
            if (!Arrays.equals(actualOutput, expectedOutput)) {
                System.out.println("Failing for input.");
                ArrayUtils.print(input);
                ArrayUtils.print(actualOutput);
                ArrayUtils.print(expectedOutput);
            }
        });
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is test passing: " + test());
    }

    static {
        testcases.put(new Integer[]{1, 2}, new Integer[]{1, 2});
        testcases.put(new Integer[]{1, 2, 1, 3, 2, 5}, new Integer[]{3, 5});
        testcases.put(new Integer[]{1, 1, 2, 2, 3, 4}, new Integer[]{3, 4});
    }
}
