package com.kumanoit.array;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 12/21/20 IST 11:59 AM
 */
/*
Input: S = "leet2code3", K = 10
Output: "o"
Explanation:
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation:
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation:
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 */
public class DecodedString {

    public static void main(String[] args) {
        test("leet2code3", 10);
        test("ha22", 5);
        test("a2345678999999999999999", 1);
    }

    private static void test(String S, int K) {
        char[] input = S.toCharArray();
//        getCharAtIndex(input, K);
        System.out.println(getCharAtIndex(input, K) + "");
    }

    private static char getCharAtIndex(char[] input, int K) {
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if (isDigit(input[i])) {
                int digit = input[i] - '0';
                if (count * digit >= K) {
                    return getCharAtIndex(input, K % count == 0 ? count : K % count);
                }
                count *= digit;
            } else {
                count++;
                if (count == K) {
                    return input[K - 1];
                }
            }
        }
        return '\0';
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }


}
