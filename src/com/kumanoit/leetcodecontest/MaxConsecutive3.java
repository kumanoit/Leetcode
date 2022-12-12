package com.kumanoit.leetcodecontest;

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
 * @author akumar on 6/29/21 IST 1:37 PM
 */
public class MaxConsecutive3 {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    public static int longestOnes(int[] nums, int k) {
        List<String> list = Arrays.asList("amit", "kumar");
        list.forEach(item -> System.out.println(item));
        int end = 0;
        int start = 0;
        int maxLength = 0;
        for (end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                k--;
            }
            if (k < 0) {
                while (start <= end && nums[start++] != 0) {
                }
                k = 0;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}
