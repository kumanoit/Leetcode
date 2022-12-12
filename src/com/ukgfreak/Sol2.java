package com.ukgfreak;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/6/20 IST 8:54 AM
 */
public class Sol2 {
    public static void main(String[] args) {
        System.out.println(numTriplets(new int[]{1,1}, new int[]{1,1,1}));
    }

    public static int numTriplets(int[] nums1, int[] nums2) {
        int count = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        count = countArray(nums1, nums2) + countArray(nums2, nums1);
        return count;
    }

    static int countArray(int[] nums1, int[] nums2) {
        int count = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int j = 0; j < nums2.length; j++) {
            Long x = new Long(nums2[j]);
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int i = 0; i < nums1.length; i++) {
            long square = nums1[i] * nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                if (square % nums2[j] == 0) {
                    long quotient = square / nums2[j];
                    if (map.containsKey(quotient)) {
                        count += map.get(quotient);
                        if (quotient == nums2[j]) {
                            count--;
                        }
//                        System.out.println(nums1[i] + ", " + nums2[j] + " " + quotient);
                    }
                }
            }
        }
        return count / 2;
    }
}
