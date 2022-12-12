package com.kumanoit.leetcodecontest;

import java.util.TreeMap;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/11/21 IST 11:09 AM
 */
class MyCalendar {
    TreeMap<Integer, Integer> startEnd;
    TreeMap<Integer, Integer> endStart;
    public MyCalendar() {
        startEnd = new TreeMap<>();
        endStart = new TreeMap<>();
    }

    // [[],[10,20],[15,25],[20,30]]
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }

    public boolean book(int start, int end) {
        Integer floorStart = startEnd.floorKey(start);

        if (floorStart == null) {
            if (!startEnd.isEmpty() && startEnd.firstKey() < end) {
                return false;
            }
        } else if (floorStart == start || (!startEnd.isEmpty() && startEnd.get(floorStart) > start)) {
            return false;
        }

        Integer ceilEnd = endStart.ceilingKey(end);
        if (ceilEnd == null) {
            if (!endStart.isEmpty() && start < endStart.lastKey()) {
                return false;
            }
        } else if (!endStart.isEmpty() && endStart.get(ceilEnd) < end) {
            return false;
        }

        startEnd.put(start, end);
        endStart.put(end, start);
        return true;
    }
}
