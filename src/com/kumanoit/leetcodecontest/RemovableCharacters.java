package com.kumanoit.leetcodecontest;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/13/21 IST 8:20 AM
 */
public class RemovableCharacters {

    public static void main(String[] args) {
        System.out.println(maximumRemovals("abcacb", "c", new int[]{0,1,3,4}));
    }

    public static int maximumRemovals(String s, String p, int[] removable) {
        // char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();

        int start = 0;
        int end = removable.length - 1;
        int maxLength = 0;
        while (start < end) {
            int mid = start + (end - start) / 2;
            char[] temp = (s).toCharArray();
            convert(temp, removable, mid);
            if (isSubSequence(temp, pattern)) {
                maxLength = mid + 1;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

//         for(int k = 0; k < removable.length; k++) {
//             text[removable[k]] = '$';
//             if (!isSubSequence(text, pattern)) {
//                 return k;
//             }
//         }
        // return removable.length;
        return maxLength;
    }

    private static void convert(char[] text, int[] removable, int index) {
        for (int i = 0; i <= index; i++) {
            text[removable[i]] = '$';
        }
    }

    private static boolean isSubSequence(char[] text, char[] pattern) {
        // System.out.println((new String(text)) + ", " + (new String(pattern)));
        int i = 0;
        int j = 0;
        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                j++;
            }
            i++;
        }
        return j == pattern.length;
    }
}
