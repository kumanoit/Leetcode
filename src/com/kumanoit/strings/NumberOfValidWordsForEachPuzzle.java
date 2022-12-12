package com.kumanoit.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 11/9/21 IST 9:00 PM
 */
public class NumberOfValidWordsForEachPuzzle {
    public static void main(String[] args) {
        test(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"});
    }

    private static void test(String[] words, String[] puzzles) {
        List<Integer> solution = findNumOfValidWords(words, puzzles);
        solution.forEach(index -> {
            System.out.println(index);
        });
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int[] wordsMap = getMap(words);
        int[] puzzlesMap = getMap(puzzles);
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < wordsMap.length; i++) {
            int wordHash = wordsMap[i];
            for (int j = 0; j < puzzlesMap.length; j++) {
                if (solution.size() == j) {
                    solution.add(0);
                }
                char firstLetterPuzzle = puzzles[j].charAt(0);
                if (
                        ((wordHash & (1 << (firstLetterPuzzle - 'a'))) > 0) &&
                                ((wordHash & puzzlesMap[j]) == wordHash)
                ) {
                    solution.set(j, solution.get(j) + 1);
                }
            }
        }
        return solution;
    }

    private static int[] getMap(String[] words) {
        int[] wordsMap = new int[words.length];
        for (int index = 0; index < words.length; index++) {
            wordsMap[index] = getHash(words[index].toCharArray());
        }
        return wordsMap;
    }

    private static int getHash(char[] input) {
        int number = 0;
        for (char ch : input) {
            number |= (1 << (ch - 'a'));
        }
        return number;
    }
}
