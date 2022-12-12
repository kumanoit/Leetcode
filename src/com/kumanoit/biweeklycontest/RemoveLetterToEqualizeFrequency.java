package com.kumanoit.biweeklycontest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/5/22 IST 7:10 PM
 */
public class RemoveLetterToEqualizeFrequency {
    public static void main(String[] args) {
        System.out.println(equalFrequency("aazz"));
        System.out.println(equalFrequency("aazz"));
        System.out.println(equalFrequency("aazz"));
        System.out.println(equalFrequency("aazz"));
    }

    public static boolean equalFrequency(String word) {
        int[] memo = new int[26];
        for (char ch : word.toCharArray()) {
            memo[ch - 'a']++;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : memo) {
            if (x > 0) {
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }
        if (map.size() > 2) {
            return (new ArrayList<>(map.keySet()).get(0) == 1);
        }

        if (map.size() == 1) {
            return false;
        }
        int[] keys = new int[2];
        int i = 0;
        for (int key : map.keySet()) {
            keys[i++] = key;
        }

        if (Math.abs(keys[0] - keys[1]) != 1) {
            return false;
        }
        if (keys[0] > keys[1]) {
            int temp = keys[0];
            keys[0] = keys[1];
            keys[1] = temp;
        }
        int v1 = map.get(keys[0]);
        int v2 = map.get(keys[1]);
        if (v1 > v2) {
            return v2 == 1;
        }
        return v1 == 1;
    }
}
