package com.kumanoit;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SmallestChair {
    public static void main(String[] args) {
        test(new int[][]{{3, 10}, {1, 5}, {2, 6}}, 0);
        test(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1);
        test(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 0);
        test(new int[][]{{4, 5}, {12, 13}, {5, 6}, {1, 2}, {8, 9}, {9, 10}, {6, 7}, {3, 4}, {7, 8}, {13, 14}, {15, 16}, {14, 15}, {10, 11}, {11, 12}, {2, 3}, {16, 17}}, 15);
    }

    private static void test(int[][] times, int targetFriend) {
        System.out.println(smallestChair(times, targetFriend));
    }

    public static int smallestChair(int[][] times, int targetFriend) {
        int[] seatNumber = new int[times.length];
        Time[] clocks = new Time[times.length * 2];
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0, k = 0; i < times.length; i++, k += 2) {
            clocks[k] = new Time(i, times[i][0], true);
            clocks[k + 1] = new Time(i, times[i][1], false);
            queue.add(i);
        }
        Arrays.sort(clocks);
        for (int i = 0; i < clocks.length; i++) {
            if (clocks[i].in) {
                seatNumber[clocks[i].id] = queue.remove();
                System.out.println("Friend id: " + clocks[i].id + "\t  arrival Time: " + clocks[i].time + "\tseat alloted : " + seatNumber[clocks[i].id]);
            } else {
                queue.add(seatNumber[clocks[i].id]);
                System.out.println("Friend id: " + clocks[i].id + "\tdeparture Time: " + clocks[i].time + "\tseat alloted : " + seatNumber[clocks[i].id]);
            }
            if (clocks[i].id == targetFriend) {
                return seatNumber[clocks[i].id];
            }
        }
        return -1;
    }
}

class Time implements Comparable<Time> {
    int id;
    int time;
    boolean in;

    Time(int id, int time, boolean in) {
        this.id = id;
        this.in = in;
        this.time = time;
    }

    @Override
    public int compareTo(Time other) {
        if (this.time == other.time) {
            if (!this.in) {
                return -1;
            }
//            if (!other.in) {
//                return -1;
//            }
            return 1;
        }
        return this.time - other.time;
    }
}
