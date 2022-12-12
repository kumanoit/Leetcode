package com.ukgfreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PancakeSorting {

    public static void main(String[] args) {
        System.out.println(getMinimumMoves(Arrays.asList(5, 1, 3, 2)));
        System.out.println(getMinimumMoves(Arrays.asList(1, 2, 3, 5)));
        System.out.println(getMinimumMoves(Arrays.asList(5, 4, 3, 2, 1)));
    }

    private static int getMinimumMoves(List<Integer> arr) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        List<Integer> sortedArray = new ArrayList<>(arr);
        Collections.sort(sortedArray);
        for (int i = 0; i < arr.size(); i++) {
            numToIndex.put(arr.get(i), i);
        }
        int i = arr.size() - 1;
        int j = i;
        int count = 0;
        while (i >= 0 && j >= 0) {
            if (arr.get(i) == -1) {
                i--;
                continue;
            }
            if (sortedArray.get(j) != arr.get(i)) {
                arr.set(numToIndex.get(sortedArray.get(j)), -1);
                count++;
                j--;
            } else {
                i--;
                j--;
            }
        }
        return count;
    }
}
