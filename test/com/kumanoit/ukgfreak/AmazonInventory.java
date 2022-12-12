package com.kumanoit.ukgfreak;

public class AmazonInventory {
    public static void main(String[] args) {
        test("|**|*|", new int[]{1, 1}, new int[]{5, 6});
        test("|**|*|****", new int[]{1, 1}, new int[]{5, 6});
        test("|**|*|****", new int[]{3, 4}, new int[]{5, 6});
    }

    private static void test(String s, int[] startIndices, int[] endIndices) {
        int[] sol = numberOfItems1(s, startIndices, endIndices);
        for (int item : sol) {
            System.out.println(item + ", ");
        }
        System.out.println();
    }

    private static int[] numberOfItems1(String s, int[] startIndices, int[] endIndices) {
        char[] input = s.toCharArray();
        int[] asterisks = new int[input.length];
        int[] nearestLeftPipeIndex = new int[input.length];
        int k = 0;
        asterisks[0] = input[0] == '*' ? 1 : 0;
        nearestLeftPipeIndex[0] = input[0] == '|' ? 0 : -1;
        for (int i = 1; i < input.length; i++) {
            asterisks[i] = (input[i] == '*') ? asterisks[i - 1] + 1 : asterisks[i - 1];
            nearestLeftPipeIndex[i] = (input[i] == '|') ? i : nearestLeftPipeIndex[i - 1];
        }
        int[] nearestRightPipeIndex = new int[input.length];
        nearestRightPipeIndex[input.length - 1] = input[input.length - 1] == '|' ? input.length - 1 : -1;
        for (int i = input.length - 2; i >= 0; i--) {
            nearestRightPipeIndex[i] = (input[i] == '|') ? i : nearestRightPipeIndex[i + 1];
        }

        int[] solution = new int[startIndices.length];
        for (int i = 0; i < startIndices.length; i++) {
//            int startPipe = s.indexOf('|', startIndices[i] - 1);
//            int endPipe = s.lastIndexOf('|', endIndices[i] - 1);
//            solution[i] = asterisks[endPipe] - asterisks[startPipe];
            int startPipeIndex = nearestRightPipeIndex[startIndices[i] - 1];
            int endPipeIndex = nearestLeftPipeIndex[endIndices[i] - 1];
            if (startPipeIndex != -1 && endPipeIndex != -1) {
                solution[i] = asterisks[endPipeIndex] - asterisks[startPipeIndex];
            }
        }
        return solution;
    }
}
