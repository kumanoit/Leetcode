package com.kumanoit.leetcodecontest;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 4/11/21 IST 11:23 AM
 */
public class Test {
    public  void main(String[] args) {
//        test("cba");
        test("aabaa");
        test("cdbea");
        test("leetcodeleetcodeleetcode");
    }

    private  void test(String input) {
        System.out.println(makeStringSorted(input));
    }
    public  int makeStringSorted(String s) {
        char[] input = s.toCharArray();
        int count = 0;

        while(true) {
            int i = getI(input);
            if (i == -1) {
                break;
            }
            int j = getJ(input, i);
            swap(input, i - 1, j);
            reverse(input, i);
            count++;
            String output = " i = " + i + ", j = " + j + " = " + (new String(input));
            System.out.println(output);
        }
        return count;
    }

    private  void reverse(char[] input, int i) {
        for(int start = i, end = input.length - 1; start < end; start++, end--) {
            char temp = input[start];
            input[start] = input[end];
            input[end] = temp;
        }
    }
    private  void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    private  int getJ(char[] input, int i) {
        char prev = input[i - 1];
        for(int j = i + 1; j < input.length; j++) {
            if (input[j] > prev) {
                return j - 1;
            }
        }
        return input.length - 1;
    }
    private  int getI(char[] input) {
        int i = input.length - 1;
        while(i - 1 >= 0 && input[i - 1] <= input[i]) {
            i--;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }
}
