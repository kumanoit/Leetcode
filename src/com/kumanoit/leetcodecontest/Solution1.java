package com.kumanoit.leetcodecontest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    public static void main(String[] args) {
        String[] list = spellchecker(new String[]{"KiTe", "kite", "hare", "Hare"}, new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"});
        for (String a : list) {
            System.out.println(a);
        }
    }

    public static String[] spellchecker(String[] wordlist, String[] queries) {
//        Map<String, List<String>> wordMap = new HashMap<>();
//        for (String word : wordlist) {
//            String w = word.toLowerCase();
//            if (!wordMap.containsKey(w)) {
//                wordMap.put(w, new ArrayList<>());
//            }
//            wordMap.get(w).add(word);
//        }

        String[] solution = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
//            String query = queries[i].toLowerCase();
            // if (!wordMap.containsKey(query)) {
            //     solution[i] = "";
            // } else {
            String capitalMatch = null;
            String vowelMatch = null;
            String perfectMatch = null;
            for (String word : wordlist) {
                int matchType = getMatchType(queries[i], word);
                if ((matchType & 1) == 1) {
                    perfectMatch = word;
                    break;
                } else if ((matchType & 4) != 0) {
                    if (capitalMatch == null) {
                        capitalMatch = word;
                    }
                } else if ((matchType & 2) != 0) {
                    if (vowelMatch == null) {
                        vowelMatch = word;
                    }
                }
            }
            if (perfectMatch != null) {
                solution[i] = perfectMatch;
            } else if (capitalMatch != null) {
                solution[i] = capitalMatch;
            } else if (vowelMatch != null) {
                solution[i] = vowelMatch;
            } else {
                solution[i] = "";
            }
            // }
        }
        return solution;
    }

    private static int getMatchType(String query, String word) {
        int isPerfectMatch = 1;
        int isVowelMatch = 2;
        int isCapitalMatch = 4;

        for (int i = 0; i < word.length(); i++) {
            if (query.charAt(i) != word.charAt(i)) {
                isPerfectMatch = 0;
                if (!(isVowel(query.charAt(i)) && isVowel(word.charAt(i)))) {
                    isVowelMatch = 0;
                }
                if (Character.toLowerCase(query.charAt(i)) != Character.toLowerCase(word.charAt(i))) {
                    isCapitalMatch = 0;
                }
            }

            if (isPerfectMatch == 0 && isVowelMatch == 0 && isCapitalMatch == 0) {
                break;
            }
        }
        return isPerfectMatch | isVowelMatch | isCapitalMatch;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
