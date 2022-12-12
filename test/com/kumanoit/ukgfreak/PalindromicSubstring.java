package com.kumanoit.ukgfreak;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(threePalindromicSubstrings("aaaaaaa"));
        System.out.println(threePalindromicSubstrings("jalaj"));
        System.out.println(threePalindromicSubstrings("tenet"));
    }
    public static List<String> threePalindromicSubstrings(String word) {
        char[] input = word.toCharArray();
        int size = input.length;
        boolean[][] isPalindrome = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            isPalindrome[i][i] = true;
        }
        for(int i = 0; i < size - 1; i++) {
            isPalindrome[i][i + 1] = input[i] == input[i+1];
        }

        for(int len = 3; len <= size; len++) {
            for(int start = 0; start < size - len + 1; start++) {
                int end = start + len - 1;
                isPalindrome[start][end] = isPalindrome[start + 1][end - 1] && input[start] == input[end];
            }
        }

        List<Integer> midIndices = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            if (isPalindrome[i][size - 1]) {
                midIndices.add(i);
            }
        }
        List<String> solution = new ArrayList<>();
        for(int mid : midIndices) {
            for(int start = 0; start < mid; start++) {
                if (isPalindrome[0][start] && isPalindrome[start + 1][mid - 1]) {
                    solution.add(word.substring(0, start + 1));
                    solution.add(word.substring(start + 1, mid));
                    solution.add(word.substring(mid));
                    return solution;
                }
            }
        }
        if (solution.isEmpty()) {
            solution.add("Impossible");
        }
        return solution;
    }

}
