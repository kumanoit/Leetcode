package com.kumanoit.leetcodecontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/9/22 IST 9:16 AM
 */
public class WordCoutn {
    public static void main(String[] args) {
        System.out.println(wordCount(new String[]{"ace", "bdf"}, new String[]{"acei","abce","acde","abdf"}));
    }

    public static int wordCount(String[] startWords, String[] targetWords) {
        Map<Integer, List<char[]>> map = new HashMap<>();
        int count = 0;
        for (String word : startWords) {
            int len = word.length();
            if (!map.containsKey(len)) {
                map.put(len, new ArrayList<>());
            }
            char[] wordSorted = word.toCharArray();
            Arrays.sort(wordSorted);
            map.get(len).add(wordSorted);
        }

        for (String word : targetWords) {
            char[] pattern = word.toCharArray();
            Arrays.sort(pattern);
            System.out.println(new String(pattern));
            if (!map.containsKey(pattern.length - 1)) {
                System.out.println("asdf");
                continue;
            }

            for (char[] text : map.get(pattern.length - 1)) {
                int start = 0;
                for (start = 0; start < text.length; start++) {
                    if (text[start] != pattern[start]) {
                        // start--;
                        break;
                    }
                }

                if (start == text.length) {
                    count++;
                    break;
                }

                int end = text.length - 1;
                for (; end >= 0; end--) {
                    if (text[end] != pattern[end + 1]) {
                        // end++;
                        break;
                    }
                }
                if (end < 0) {
                    count++;
                    break;
                }

                int lengthMatched = start + text.length - end;
                System.out.println("start = " + start + " end = " + end);
                if (lengthMatched + 1 == pattern.length) {
                    count++;
                }
            }
        }
        return count;
    }
}
