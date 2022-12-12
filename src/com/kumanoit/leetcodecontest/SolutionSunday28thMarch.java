package com.kumanoit.leetcodecontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
 * @author akumar on 3/28/21 IST 8:49 AM
 */
public class SolutionSunday28thMarch {
    public static void main(String[] args) {

        int[] array = {1,3,5,7};
        System.out.println(Arrays.binarySearch(array, 7));
        System.out.println(Arrays.binarySearch(array, 8));
        System.out.println(Arrays.binarySearch(array, 4));
        System.out.println(Arrays.binarySearch(array, 18));
        System.out.println(Arrays.binarySearch(array, 0));
//        generatePermutation("1");
//        generatePermutation("12");
//        generatePermutation("123");
//        generatePermutation("zeeighsixtroosixsixnethreetwo");
//        System.out.println(reinitializePermutation(0));
//        System.out.println(reinitializePermutation(2));
//        System.out.println(reinitializePermutation(4));
//        System.out.println(reinitializePermutation(6));
//        System.out.println(reinitializePermutation(8));
//        test("(name)is(age)yearsold", new String[][]{{"name","bob"},{"age","two"}});
    }

    private static void test(String s, String[][] strings) {
        List<List<String>> map = new ArrayList<>();
        for(String[] input : strings) {
            List<String> list = new ArrayList<>();
            list.add(input[0]);
            list.add(input[1]);
            map.add(list);
        }
        System.out.println(evaluate(s, map));
    }

    public static String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> kv : knowledge) {
            map.put(kv.get(0), kv.get(1));
        }
        StringBuilder sb = new StringBuilder();
        // int end = 0;
        int start = 0;
        while (start < s.length()) {
            System.out.println(start);
            int openBracket = s.indexOf('(', start);
            if (openBracket != -1) {
                sb.append(s, start, openBracket);
                int closedBracket = s.indexOf(')', openBracket);
                String key = s.substring(openBracket + 1, closedBracket);
                start = closedBracket + 1;
                sb.append(map.get(key));
            } else {
                break;
            }
        }
        sb.append(s.substring(start));
        return sb.toString();
    }

    public static int reinitializePermutation(int n) {
        int[] perm = new int[n];
        for(int i = 0; i < n; i++) {
            perm[i] = i;
        }
        boolean isSame = false;
        int k = 0;
        printArray(perm);
        while(!isSame) {
            isSame = true;
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                int newIndex = (i % 2 == 0) ? (i / 2) : (n / 2 + (i - 1) / 2);
                arr[i] = perm[newIndex];
                if (arr[i] != i) {
                    isSame = false;
                }
            }
            printArray(arr);
            perm = arr;
            k++;
        }
        return k;
    }
    private static void printArray(int[] number) {
        for(int n : number) {
            System.out.print(n + ", ");
        }
        System.out.println();
    }

    private static void generatePermutation(String input) {
        generatePermutation(input.toCharArray(), 0, input.length() - 1);
    }
    private static void generatePermutation(char[] input, int beg, int end) {
        if (beg == end) {
            for(int i = 0; i < input.length; i++) {
                System.out.print(input[i]);
            }
            System.out.println();
            return;
        }
        for(int i = beg; i <= end; i++) {
            swap(input, i, beg);
            generatePermutation(input, beg + 1, end);
            swap(input, i, beg);
        }
    }
    private static void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
