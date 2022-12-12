package com.kumanoit.array;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * 253. Meeting Rooms II
 * <p>
 * Approach:
 * We can iterate over all intervals in chronological order of its starting points and keep a track of all running
 * meetings using a variable which gets incremented whenever a meeting starts and gets decremented whenever a meeting
 * ends. The maximum number of meetings which are running at the same time, will be the number of conference room needed.
 * <p>
 * Complexity
 * 1. Time: O(nlgn) to iterate over all intervals will have complexity of O(n)
 * and inserting in treemap in sorted manner will have additional cost of O(lg(n)) for one element. So for n elements
 * this will become O(nlg(n))
 * 2. Space: O(n) to save 2*n values in treemap.
 *
 * @author akumar on 8/2/20 IST 10:09 PM
 */
public class MeetingRooms2 {

    public static void main(final String[] args) {
        test(new int[][]{{2, 4}});
        test(new int[][]{{2, 4}, {2, 4}});
        test(new int[][]{{7, 10}, {2, 4}});
        test(new int[][]{{1, 10}, {2, 9}, {3, 8}, {4, 7}, {5, 6}});
        test(new int[][]{{0, 30}, {5, 10}, {15, 20}});
    }

    private static void test(final int[][] intervals) {
        System.out.println(getMinimumConferenceRoomCount(intervals));
    }

    private static int getMinimumConferenceRoomCount(final int[][] intervals) {
        if (intervals.length < 2) {
            return intervals.length;
        }

        final Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], map.getOrDefault(intervals[i][0], 0) + 1);
            map.put(intervals[i][1], map.getOrDefault(intervals[i][1], 0) - 1);
        }
        int minConferenceRoomCount = 0;
        int currentConferenceRoomCount = 0;
        for (int value : map.values()) {
            currentConferenceRoomCount += value;
            minConferenceRoomCount = Math.max(minConferenceRoomCount, currentConferenceRoomCount);
        }
        return minConferenceRoomCount;
    }

}
