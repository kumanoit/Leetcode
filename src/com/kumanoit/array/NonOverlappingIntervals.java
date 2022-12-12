package com.kumanoit.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/16/20 IST 1:50 AM
 */
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        test(new int[][]{{1, 2}, {3, 4}, {1, 3}, {2, 3}});
        test(new int[][]{{1, 2}, {1, 2}, {1, 2}});
        test(new int[][]{{1, 2}, {2, 3}});
    }

    private static void test(final int[][] intervals) {
        System.out.println(eraseOverlapIntervals(intervals));
    }

    private static int eraseOverlapIntervals(final int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
                    @Override
                    public int compare(final int[] o1, final int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        int count = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }

}

