package com.kumanoit.mathematics;

import com.kumanoit.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 * 118. Pascal's Triangle
 * Approach:
 * For the first two rows simply add 1 and {1,1}
 * Now, first and last element in each row will be 1 and 1
 * and for the rest number[i,j] = number[i-1,j-1] + number[i-1,j]
 * Complexity
 * 1. Time: O(n) because each output element is created only once and values are re-used
 * 2. Space: O(1) since space of created list has to be returned so not counting that
 *
 * @author akumar on 8/12/20 IST 7:20 PM
 */
public class PascalTriangle {

    public static void main(String[] args) {
        test(0);
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
        test(6);
        test(7);
    }

    private static void test(int numRows) {
        ListUtils.printListOfList(generate(numRows));
    }

    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return solution;
        }
        solution.add(Arrays.asList(1));
        if (numRows == 1) {
            return solution;
        }
        solution.add(Arrays.asList(1, 1));
        for (int i = 2; i < numRows; i++) {
            List<Integer> subSolution = new ArrayList<Integer>();
            subSolution.add(1);
            for (int j = 1; j < i; j++) {
                subSolution.add(solution.get(i - 1).get(j - 1) + solution.get(i - 1).get(j));
            }
            subSolution.add(1);
            solution.add(subSolution);
        }
        return solution;
    }
}
