package com.kumanoit.mathematics;

import com.kumanoit.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 * 119. Pascal's Triangle II
 * Approach:
 * This problem can be solved by two ways:
 * 1. Creating complete Pascal's triangle upto current row
 * 2. Using bonomial series. In Binomial series also, instead of calculating each term, we can use
 * result of previous term to compute next term: nC(k+1) = (n-k) / (k+1) * nCk
 * Complexity
 * 1. Time: O(n): Constant time is taken to create each element
 * 2. Space: O(1)
 *
 * @author akumar on 8/14/20 IST 2:36 AM
 */
public class PascalTriangle2 {

    public static void main(String[] args) {
        test(0);
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
        test(6);
        test(7);
        test(10);
    }

    private static void test(int rowIndex) {
        ListUtils.printList(getRow(rowIndex));
        System.out.println();
    }

    private static List<Integer> getRow(int rowIndex) {
        List<Integer> solution = new ArrayList<>();
        solution.add(1);

        int end = rowIndex / 2;
        for (int k = 1; k <= end; k++) {
            solution.add((int) ((long) solution.get(k - 1) * (rowIndex - (k - 1)) / k));
        }

        if ((rowIndex & 1) == 0) {
            end--;
        }

        while (end >= 0) {
            solution.add(solution.get(end--));
        }

        return solution;
    }
}
