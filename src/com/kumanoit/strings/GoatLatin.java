package com.kumanoit.strings;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/19/20 IST 6:25 PM
 */
public class GoatLatin {

    public static void main(String[] args) {
        test("I speak Goat Latin");
        test("c d e");
        test(" ");
        test("I   speak     Goat      Latin");
        test("");
        test("a  e  i      o      u");
        test("The quick brown fox jumped over the lazy dog");
        test("          d     r      p     ");
    }

    private static void test(String input) {
        System.out.println("Input = " + input);
        System.out.println("Output = " + toGoatLatin(input));
    }

    private static boolean startsWithVowel(String word) {
        char firstCharacter = word.charAt(0);
        return (firstCharacter == 'a' || firstCharacter == 'A' ||
                firstCharacter == 'e' || firstCharacter == 'E' ||
                firstCharacter == 'i' || firstCharacter == 'I' ||
                firstCharacter == 'o' || firstCharacter == 'O' ||
                firstCharacter == 'u' || firstCharacter == 'U');
    }

    private static String toGoatLatin(String S) {
        if (S.length() == 0) {
            return "";
        }
        String[] words = S.trim().split("\\s+");
        if (words.length == 0 || words[0].length() == 0) {
            return "";
        }
        StringBuilder solution = new StringBuilder();
        StringBuilder appender = new StringBuilder("a");
        for (String word : words) {
            if (startsWithVowel(word)) {
                solution.append(word).append("ma");
            } else {
                char firstCharacter = word.charAt(0);
                String suffix = word.substring(1);
                if (suffix != null) {
                    solution.append(suffix).append(firstCharacter);
                } else {
                    solution.append(word);
                }
                solution.append("ma");
            }
            solution.append(appender.toString()).append(" ");
            appender.append("a");
        }
        return solution.toString().trim();
    }
}
