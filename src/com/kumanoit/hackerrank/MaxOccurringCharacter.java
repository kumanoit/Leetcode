package com.kumanoit.hackerrank;

public class MaxOccurringCharacter {

    public static void main(String[] args) {
        System.out.println(maximumOccurringCharacter("helloworld"));
        System.out.println(maximumOccurringCharacter("hellos111sW43341O456RLD54563454563456345orld"));
        System.out.println(maximumOccurringCharacter("hellowozzzzzrld"));
        System.out.println(maximumOccurringCharacter("helloAAAAworAAAAld"));
        System.out.println(maximumOccurringCharacter("hellowoZZZZZrld"));
    }

    public static char maximumOccurringCharacter(String text) {
        int[] count = new int[62];
        char maxOccurring = ' ';
        int maxCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (isLowercase(ch)) {
                count[ch - 'a']++;
            } else if (!isNumber(ch)) {
                count[ch - 'A' + 26]++;
            } else {
                count[ch - '0' + 52]++;
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxOccurring = (i > 51) ? (char) (i + '0' - 52) : ((i > 25) ? (char) (i + 'A' - 26) : (char) (i + 'a'));
            }
        }
        return maxOccurring;
    }

    private static boolean isLowercase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
