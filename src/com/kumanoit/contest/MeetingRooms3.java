package com.kumanoit.contest;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/4/22 IST 3:42 PM
 */
public class MeetingRooms3 {
    public static void main(String[] args) {
        System.out.println(mostBooked(2, new int[][]{{0,10},{1,5},{2,7},{3,4}}));
        Boolean flag = true;
        System.out.println(flag.equals(Boolean.TRUE));
    }
    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        Queue<Integer> availableRooms = new PriorityQueue<>();
        Queue<Pair<Integer, Integer>> running = new PriorityQueue<>((a, b) -> (int) (a.getValue() == b.getValue() ?
                a.getKey() - b.getKey() :
                a.getValue() - b.getValue()));

        int[] count = new int[n];
        int i = 0;
        for(i = 0; i < n; i++) {
            availableRooms.add(i);
        }

        for(int[] meeting : meetings) {
            while(!running.isEmpty() && running.peek().getValue() < meeting[0]) {
                availableRooms.add(running.remove().getKey());
            }

            int roomId = -1;
            int startTime = meeting[0];
            int duration = meeting[1] - meeting[0];
            if (availableRooms.isEmpty()) {
                startTime = running.peek().getValue();
                roomId = running.remove().getKey();
                availableRooms.add(roomId);
            }
            roomId = availableRooms.remove();
            count[roomId]++;
            running.add(new Pair(roomId, startTime + duration));
        }

        int rid = -1;
        int maxCount = 0;
        for(i = 0; i < count.length; i++) {
            if (maxCount < count[i]) {
                maxCount = count[i];
                rid = i;
            }
        }
        return rid;
    }
}
