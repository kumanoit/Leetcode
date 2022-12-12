package com.kumanoit.array;

import com.kumanoit.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/21/20 IST 1:05 PM
 */
public class SortArrayByParity {

    public static void main(String[] args) {
//        test(new Integer[]{});
//        test(new Integer[]{1});
//        test(new Integer[]{2});
//        test(new Integer[]{3, 1, 2, 4});
        test(new Integer[]{2, 4, 6, 8});
//        test(new Integer[]{1, 3, 5, 7, 9});
//        test(new Integer[]{1, 3, 5, 7, 9, 2, 4, 6, 8});
//        test(new Integer[]{2, 4, 6, 8, 1, 3, 5, 7, 9});
    }

    private static void test(Integer[] input) {
        ArrayUtils.print(input);
        sortArrayByParity(input);
        ArrayUtils.print(input);
    }

    private static Integer[] sortArrayByParity(Integer[] A) {
        if (A.length <= 1) {
            return A;
        }
        int evenIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] & 1) == 0) {
                ++evenIndex;
                // if ((A[evenIndex] & 1) != 0) {
                swap(evenIndex, i, A);
                // }
            }
        }
        return A;
    }

    private static void swap(int i, int j, Integer[] A) {
        if (i != j) {
            A[i] = A[i] ^ A[j];
            A[j] = A[i] ^ A[j];
            A[i] = A[i] ^ A[j];
        }
    }

    public List<List<Integer>> findSolution(int z) {
        List<List<Integer>> solution = new ArrayList<>();
        for(int i = 0; i <= z; i++) {
            for(int j = 0; j <= z; j++) {
                if (i + j == z) {
                    solution.add(Arrays.asList(new Integer[]{i, j}));
                }
            }
        }
        return solution;
    }
}
