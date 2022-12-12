package com.kumanoit.array;

import java.util.ArrayList;
import java.util.List;
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
 * @author akumar on 10/15/22 IST 10:56 PM
 */
public class Frquency {
    public static int getLengthOfOptimalCompression(String s, int k) {
        return 0;
    }
//
//        List<Integer> list = new ArrayList<>();
//        int end = 0;
//        char[] input = s.toCharArray();
//        while (end < s.length()) {
//            int count = 1;
//            while (end + 1 < input.length && input[end] == input[end + 1]) {
//                end++;
//                count++;
//            }
//            list.add(count);
//        }
//
//        Queue<Integer> q1 = new PriorityQueue<>();
//        Queue<Integer> q2 = new PriorityQueue<>();
//        Queue<Integer> q10 = new PriorityQueue<>();
//        Queue<Integer> q100 = new PriorityQueue<>();
//        for (int x : list) {
//            if (x == 100) {
//                q100.add(x);
//            } else if (x > 9) {
//                q10.add(x);
//            } else if (x > 1) {
//                q2.add(x);
//            } else {
//                q1.add(x);
//            }
//        }
//
//        Queue<MyPair> queue = new PriorityQueue<>((a, b) -> (a.queue.peek() - a.id - b.queue.peek() - b.id));
//
//        if (!q1.isEmpty()) {
//            queue.add(new MyPair(1, q1));
//        }
//        if (!q2.isEmpty()) {
//            queue.add(new MyPair(2, q2));
//        }
//        if (!q10.isEmpty()) {
//            queue.add(new MyPair(10, q10));
//        }
//        if (!q100.isEmpty()) {
//            queue.add(new MyPair(100, q100));
//        }
//
//        while(k > 0) {
//            MyPair pair = queue.remove();
//            int top = pair.queue.remove();
//            top--;
//            if (top != 0) {
//                if (top == 100) {
//                    q100.add(top);
//                } else if (top > 9) {
//                    q10.add(top);
//                } else if (top > )
//            }
//
//        }
//    }
}

class MyPair {
    int id;
    Queue<Integer> queue;

    public MyPair(int id, Queue<Integer> queue) {
        this.id = id;
        this.queue = queue;
    }
}
