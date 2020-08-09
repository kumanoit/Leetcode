package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/relative-sort-array/
 * 1122. Relative Sort Array
 * Approach:
 * The sort order of numbers is changed which is given in second array. We can create another object as Pair which will
 * have information about the priority and later we can sort based on this priority value.
 * Complexity
 * 1. Time: O(nlgn) for sorting
 * 2. Space: O(n) for saving
 *
 * @author akumar on 8/9/20 IST 11:10 PM
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        test(new int[]{}, new int[]{});
        test(new int[]{9, 5, 6, 3, 1, 4, 6, 8}, new int[]{});
        test(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    }

    private static void test(int[] arr1, int[] arr2) {
        arr1 = relativeSortArray(arr1, arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ", ");
        }
        System.out.println();
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        int k = arr2.length;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            if (!map.containsKey(arr1[i])) {
                map.put(arr1[i], arr1[i] + k);
            }
            list.add(new Pair(arr1[i], map.get(arr1[i])));
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            arr1[i] = list.get(i).value;
        }
        return arr1;
    }

    static class Pair implements Comparable<Pair> {
        int value;
        int priority;

        public Pair(final int value, final int priority) {
            this.value = value;
            this.priority = priority;
        }

        @Override
        public int compareTo(final Pair o) {
            return this.priority - o.priority;
        }
    }
}
