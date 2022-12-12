package com.kumanoit.contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/6/22 IST 6:42 PM
 */
public class CodexQ4 {
    public static void main(String[] args) {
        for(int i = 1; i < 100; i++) {
            int x = solve(i);
            int y = solve1(i);
            System.out.println(i + "\t" + x + "\t" + y + " \t" + (x - y + 2) % x);
        }
        for(int i = 1; i < 100; i *= 2) {
            int x = solve(i);
            int y = solve1(i);
            System.out.println(i + "\t" + x + "\t" + y + " \t" + (x - y + 2) % x);
        }
        for(int i = 1; i < 1000; i *= 3) {
            int x = solve(i);
            int y = solve1(i);
            System.out.println(i + "\t" + x + "\t" + y + " \t" + (x - y + 2) % x);
        }
        for(int i = 1; i < 10000; i *= 7) {
            int x = solve(i);
            int y = solve1(i);
            System.out.println(i + "\t" + x + "\t" + y + " \t" + (x - y + 2) % x);
        }
    }

    public static int solve(int n) {
        if (n == 1) {
            return 1;
        }
        return (solve(n - 1) + 1) % n + 1;
    }

    public static int solve1(int A) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = A; i >= 1; i--) {
            queue.add(i);
        }
        while(queue.size() > 1) {
            queue.remove();
            queue.add(queue.remove());
        }
        return queue.remove();
//
//        List<Integer> list = new ArrayList<>();
//        int[] array = new int[A];
//
//        for(int i = A; i >= 1; i--) {
//            list.add(i);
//        }
//
//        while(list.size() > 1) {
//            int k = 0;
//            List<Integer> newList = new ArrayList<>();
//            while(k < list.size()) {
//                k++;
//                if (k < list.size()) {
//                    newList.add(list.get(k++));
//                }
//            }
//            list = newList;
//        }
//        return list.get(0);
    }

}
