package com.satwik;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 4/26/22 IST 9:56 PM
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println(findPasswordStrength("good"));
        System.out.println(findPasswordStrength("test"));
        System.out.println(findPasswordStrength("abc"));
    }
    public static long findPasswordStrength(String password) {
        char[] input = password.toCharArray();
        int[] unique = new int[26];
        long solution = 0;
        int count = 0;
        for(int i = 0; i < input.length; i++) {
            if (unique[input[i] - 'a'] == 0) {
                count++;
            }
            unique[input[i] - 'a']++;
            int[] subArray = new int[26];
            int countCopy = count;
            for(int j = 0; j < i; j++) {
                int index = input[j] - 'a';
                subArray[index]++;
                solution += countCopy;
                if (unique[index] == subArray[index]) {
                    countCopy--;
                }
            }
            solution++;
        }
        return solution;
    }
}
