package com.ukgfreak;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/6/20 IST 8:43 AM
 */
public class Sol1 {
    public static void main(String[] args) {
        index = 0;
        String s = "3[a]2[bc]";
        StringBuilder sb = new StringBuilder();
        while(index < s.length()) {
            sb.append(compute(s.toCharArray()));
        }
        System.out.println(sb.toString());
    }




    static int index = 0;
    private static StringBuilder compute(char[] input) {
        StringBuilder sb = new StringBuilder();

        while(index < input.length && isCharacter(input[index])) {
            sb.append(input[index++]);
        }

        int number = 0;
        while(index < input.length && isDigit(input[index])) {
            number = (number * 10) + (input[index++] - '0');
        }

        if (number > 0) {
            index++;
            String repeatingString = compute(input).toString();
            for(int i = 0; i < number; i++) {
                sb.append(repeatingString);
            }
        } else {
            index++;
        }
        return sb;
    }

    private static boolean isCharacter(char ch) {
        return (ch >= 'a' && ch <= 'z');
    }

    private static boolean isDigit(char ch) {
        return (ch >= '0' && ch <= '9');
    }













    public static String modifyString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if (currChar == '?') {
                if (i == 0) {
                    if (i + 1 == s.length() || s.charAt(i + 1) == '?') {
                        sb.append('a');
                    } else {
                        char next = s.charAt(i + 1);
                        sb.append((char)('a' + (next + 1) % 26));
                    }
                } else {
                    char prevChar = sb.toString().charAt(i - 1);
                    char newChar = (char)('a' + (prevChar + 1) % 26);
                    if (i + 1 == s.length() || s.charAt(i + 1) == '?') {
                        sb.append(newChar);
                    } else {
                        char next = s.charAt(i + 1);
                        if (newChar == next) {
                            newChar = (char)('a' + (newChar + 1) % 26);
                        }
                        sb.append(newChar);
                    }
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
