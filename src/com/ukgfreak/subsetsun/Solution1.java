package com.ukgfreak.subsetsun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

    public static void main(String[] args) {
        int[] arr = {180,40,50,115,55,26,145,52,134,150,252,120,150};
        Arrays.sort(arr);
        System.out.println(isFeasible(arr, 500));
    }

    private static boolean isFeasible(int[] numbers, int target) {
        boolean[][] isFeasible = new boolean[numbers.length + 1][target + 1];
        for (int i = 0; i < isFeasible.length; i++) {
            isFeasible[i][0] = true;
        }

        for (int i = 1; i < isFeasible.length; i++) {
            for (int sum = 1; sum <= target; sum++) {
                isFeasible[i][sum] = isFeasible[i - 1][sum] || (sum >= numbers[i - 1] &&
                        isFeasible[i - 1][sum - numbers[i - 1]]);
            }
        }
        for (boolean[] row : isFeasible) {
            System.out.println();
            for (boolean x : row) {
                System.out.print(x + "\t");
            }
        }
        System.out.println("\nPossible combination...");
        if (isFeasible[numbers.length][target]) {
//            printCombinations(new ArrayList<>(), isFeasible, target, numbers.length, numbers);
            printOneSubset(isFeasible, target, numbers.length, numbers);
        }

        return isFeasible[numbers.length][target];
    }

    private static void printCombinations(List<Integer> list, boolean[][] isFeasible, int sum, int row, int[] numbers) {
        if (sum == 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(numbers[list.get(i) - 1] + "\t");
            }
            System.out.println();
            return;
        }
        if (row == 0) {
            return;
        }
        if (isFeasible[row - 1][sum]) {
            printCombinations(list, isFeasible, sum, row - 1, numbers);
        }
        if (sum >= numbers[row - 1] && isFeasible[row - 1][sum - numbers[row - 1]]) {
            list.add(row);
            printCombinations(list, isFeasible, sum - numbers[row - 1], row - 1, numbers);
            list.remove(list.size() - 1);
        }
    }

    private static void printOneSubset(boolean[][] isFeasible, int sum, int row, int[] numbers) {
        if (sum == 0) {
            return;
        }
        if (row == 0 || sum < 0) {
            return;
        }

        if (isFeasible[row - 1][sum]) {
            printOneSubset(isFeasible, sum, row - 1, numbers);
        } else {
            if (isFeasible[row - 1][sum - numbers[row - 1]]) {
                printOneSubset(isFeasible, sum - numbers[row - 1], row - 1, numbers);
                System.out.println((row - 1) + " : " + numbers[row - 1]);
            }
        }
    }
}


