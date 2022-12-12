package com.kumanoit.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/24/21 IST 5:58 PM
 */
public class WordLadder2 {
    public static void main(String[] args) {
//        test(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}, "hit", "cog");
//        test(new String[]{"si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye"}, "qa",
//                "sq");
        //"hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        String words = "IN:agriculture|care|childcare|driver|education|mednurse|personal";
        Arrays.stream(words.split("\\|")).forEach(x -> System.out.println(x));
    }

    private static void test(String[] words, String beginWord, String endWord) {
        List<String> wordList = new ArrayList<>();
        for (String w : words) {
            wordList.add(w);
        }
        findLadders(beginWord, endWord, wordList).forEach(i -> System.out.println(i + ","));
        System.out.println();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        int n = wordList.size();
        int source = n - 1;
        int destination = -1;
        for (int i = 0; i < n; i++) {
            if (wordList.get(i).equals(endWord)) {
                destination = i;
            } else if (wordList.get(i).equals(beginWord)) {
                source = i;
            }
            for (int j = i + 1; j < n; j++) {
                if (differsByOne(wordList.get(i).toCharArray(), wordList.get(j).toCharArray())) {
                    if (!map.containsKey(i)) {
                        map.put(i, new ArrayList<>());
                    }
                    map.get(i).add(j);

                    if (!map.containsKey(j)) {
                        map.put(j, new ArrayList<Integer>());
                    }
                    map.get(j).add(i);
                }
            }
        }

        for (int key : map.keySet()) {
            System.out.print(wordList.get(key) + "(" + key + ")" + ": ");
            for (int i : map.get(key)) {
                System.out.print(wordList.get(i) + "(" + i + ")" + ", ");
            }
            System.out.println();
        }
        int[] minDistance = new int[1];
        minDistance[0] = n + 1;
        boolean[] visited = new boolean[n];
        visited[source] = true;
//        List<List<String>> solution = new ArrayList<List<String>>();
//        compute(map, source, destination, 0, minDistance, new ArrayList<Integer>(), solution, wordList, visited);

        List<List<String>> solution = new ArrayList<>();
        Map<Integer, List<List<Integer>>> memo = new HashMap<>();
        calculate(map, source, destination, visited, memo);

        return solution;
    }

    private static List<List<Integer>> calculate(Map<Integer, List<Integer>> graph,
                                                 int source,
                                                 int destination,
                                                 boolean[] visited,
                                                 Map<Integer, List<List<Integer>>> memo) {
        List<List<Integer>> solution = new ArrayList<>();
        if (source == destination) {
            solution.add(Arrays.asList(destination));
            return solution;
        }
        if (!graph.containsKey(source)) {
            return solution;
        }
        if (memo.containsKey(source)) {
            return memo.get(source);
        }
        int minDistance = graph.size() + 1;
        visited[source] = true;
        for (Integer neighbour : graph.get(source)) {
            if (visited[neighbour]) {
                continue;
            }
            List<List<Integer>> localPaths = calculate(graph, neighbour, destination, visited, memo);
            for (List<Integer> path : localPaths) {
                if (path.size() > minDistance) {
                    continue;
                }
                if (path.size() < minDistance) {
                    minDistance = path.size();
                    solution.clear();
                }
                List<Integer> subSolution = new ArrayList<>();
                subSolution.add(source);
                subSolution.addAll(path);
                solution.add(subSolution);
            }
        }
        visited[source] = false;
        memo.put(source, solution);
        return solution;
    }

    private static void compute(Map<Integer, List<Integer>> graph, int source, int destination, int distance, int[] minDistance,
                                List<Integer> subSolution, List<List<String>> solution, List<String> wordList,
                                boolean[] visited) {
        if (source == destination) {
            if (distance > minDistance[0]) {
                return;
            }
            if (distance < minDistance[0]) {
                minDistance[0] = distance;
                solution.clear();
            }
            List<String> ss = new ArrayList<>();
            for (int i = 0; i < subSolution.size(); i++) {
                ss.add(wordList.get(subSolution.get(i)));
            }
            ss.add(wordList.get(destination));
            solution.add(ss);
            return;
        }
        if (!graph.containsKey(source)) {
            return;
        }
        subSolution.add(source);
        for (int i : graph.get(source)) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            compute(graph, i, destination, distance + 1, minDistance, subSolution, solution, wordList, visited);
            visited[i] = false;
        }
        subSolution.remove(subSolution.size() - 1);
    }

    private static boolean differsByOne(char[] s1, char[] s2) {
        boolean found = false;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                if (found) {
                    return false;
                }
                found = true;
            }
        }
        return true;
    }
}
