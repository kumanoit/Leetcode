package com.kumanoit.leetcodecontest;

import java.util.Arrays;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff(new int[]{1, 10, 4, 4, 2, 7}, new int[]{9, 3, 5, 1, 7, 4}));
    }

    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] diff = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
        }

        Arrays.sort(nums1);
        int maxDiff = -1;
        int maxDiffIndex = -1;
        for (int i = 0; i < diff.length; i++) {
            int md = getMinimumDifference(nums1, nums2[i]);
            if (md > 0 && md < diff[i] && md > maxDiff) {
                maxDiff = md;
                maxDiffIndex = i;
            }
        }
        if (maxDiffIndex != -1) {
            diff[maxDiffIndex] = maxDiff;
        }
        int solution = 0;
        for (int i = 0; i < diff.length; i++) {
            solution = (solution + diff[i]) % 1_000_000_007;
        }
        return solution;
    }

    private static int getMinimumDifference(int[] sorted, int key) {
        int index = Arrays.binarySearch(sorted, key);
        if (index >= 0) {
            return 0;
        }

        index = -(index + 1);
        int diffCeil = 0;
        if (index == sorted.length) {
            diffCeil = 0;
        } else {
            diffCeil = Math.abs(key - sorted[index]);
        }

        int diffFloor = 0;
        if (index == 0) {
            diffFloor = 0;
        } else {
            diffFloor = Math.abs(key - sorted[index - 1]);
        }

        return Math.min(diffCeil, diffFloor);
    }
}
