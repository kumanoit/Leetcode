package com.kumanoit.leetcodecontest;

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
 * @author akumar on 6/9/21 IST 10:23 AM
 */
public class Sample {

    public static void main(String[] args) {
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "Amit");
        map.put(2L, "Bmit");
        map.put(3L, "CAmit");
        map.put(4L, "DAmit");
        System.out.println(map.getOrDefault(1L, null));
        System.out.println(map.getOrDefault(4L, null));
        System.out.println(map.getOrDefault(6L, null));
    }
}
