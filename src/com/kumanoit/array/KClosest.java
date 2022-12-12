package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/29/22 IST 7:58 PM
 */
public class KClosest {

    public static void main(String[] args) {
        findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5);
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int index = Arrays.binarySearch(arr, x);
        int start = -1;
        int end = -1;
        if (index < 0) {
            start = -index - 1;
            end = -index;
        } else {
            start = index;
            end = index + 1;
        }

        while (start >= 0 && end < arr.length && k > 0) {
            int diff1 = Math.abs(arr[start] - x);
            int diff2 = Math.abs(arr[end] - x);
            if (diff1 <= diff2) {
                list.add(arr[start--]);
            } else {
                list.add(arr[end++]);
            }
            k--;
        }

        while (start >= 0 && k > 0) {
            list.add(arr[start--]);
            k--;
        }

        while (end < arr.length && k > 0) {
            list.add(arr[end++]);
            k--;
        }
        Collections.sort(list);
        return list;
    }
}
