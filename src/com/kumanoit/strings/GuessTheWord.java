package com.kumanoit.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/5/20 IST 11:32 AM
 */
public class GuessTheWord {

    public static void main(String[] args) {
        findSecretWord(new String[] {"acckzz","ccbazz","eiowzz","abcczz"});
    }
    public static void findSecretWord(String[] wordlist) {
        int n = wordlist.length;
        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                map[i][j] = getMatchCount(wordlist[i], wordlist[j]);
            }
        }

        List<Integer> indices = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            indices.add(i);
        }
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            System.out.println(indices.size());
            int index = rand.nextInt(indices.size());
            String guessedWord = wordlist[indices.get(index)];
            System.out.println(guessedWord);
            int match = guessWord(guessedWord);
            if (match == 6) {
                break;
            }
            if (match > 0) {
                List<Integer> newIndices = new ArrayList<>();
                for (int j = 0; j < indices.size(); j++) {
                    int k = indices.get(j);
                    if (index != k && map[index][k] >= match) {
                        newIndices.add(k);
                    }
                }
                indices = newIndices;
            }
        }
    }

    private static int guessWord(String guessedWord) {
        return getMatchCount("acckzz", guessedWord);
    }

    private static int getMatchCount(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
