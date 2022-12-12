package com.kumanoit.strings;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/12/22 IST 12:21 AM
 */
public class RemoveAllOccurrences {
    public static void main(String[] args) {
        System.out.println(compute("daabcbaabcbc", "abc"));
    }

    private static String compute(String s, String part) {
        int index = s.indexOf(part);
        while (index != -1) {
            s = s.replaceFirst(part, "");
            System.out.println(s);
            index = s.indexOf(part);
        }
        return s;
    }
}
