package com.ukgfreak;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/5/22 IST 7:47 PM
 */
public class Question2 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 3}, new int[]{2, 3, 1, 4}, 6));
        System.out.println(solution(new int[]{1, 2, 4, 5}, new int[]{2, 3, 5, 6}, 6));
    }

    private static int solution(int[] A, int[] B, int N) {
        int[] nodes = new int[N + 1];
        for (int i = 0; i < A.length; i++) {
            nodes[A[i]]++;
            nodes[B[i]]++;
        }

        int maxCount = 0;
        for (int i = 0; i < A.length; i++) {
            maxCount = Math.max(maxCount, nodes[A[i]] + nodes[B[i]] - 1);
        }
        return maxCount;
    }
}
