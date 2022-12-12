package com.kumanoit.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/25/21 IST 12:09 PM
 */
public class AdvantageShuffle {
    public static void main(String[] args) {
        test(new int[]{2, 0, 4, 1, 2}, new int[]{1, 3, 0, 0, 2});
    }

    private static void test(int[] A, int[] B) {
        int[] solution = advantageCount(A, B);
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + ", ");
        }
        System.out.println();
    }

    public static int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        Integer[] bSortedIndex = new Integer[B.length];
        for (int i = 0; i < bSortedIndex.length; i++) {
            bSortedIndex[i] = i;
        }
        Arrays.sort(bSortedIndex, (a, b) -> B[b] - B[a]);

        int[] solution = new int[A.length];
        int start = 0;
        int end = A.length - 1;
        for(int i = 0; i < bSortedIndex.length; i++) {
            int bValue = B[bSortedIndex[i]];
            if (bValue >= A[end]) {
                solution[bSortedIndex[i]] = A[start++];
            } else {
                solution[bSortedIndex[i]] = A[end--];
            }
        }
        return solution;
    }
}
