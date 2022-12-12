package com.kumanoit.contest;

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
 * @author akumar on 2/19/22 IST 8:38 PM
 */
public class February19Q3 {
    public static void main(String[] args) {
        test(12);
        test(28);
        test(10_000_000_000l);
    }
    private static void test(final long finalSum) {

        maximumEvenSplit(finalSum).forEach(item -> System.out.print(item + ", "));
        System.out.println();
//        List<Long> solution = new ArrayList<Long>();
//        compute(finalSum, solution, finalSum / 2, "");
//        solution.forEach(item -> System.out.print(item + ", "));
//        System.out.println();
    }
    public static List<Long> maximumEvenSplit(long finalSum) {
        if ((finalSum & 1) == 1) {
            return new ArrayList<>();
        }

        List<Long> solution = new ArrayList<Long>();
        int[] maxCount = new int[(int) finalSum / 2 + 1];
        int[] indices = new int[(int) finalSum / 2 + 1];
        Arrays.fill(maxCount, -1);
        Arrays.fill(indices, -1);
        maxCount[0] = 0;
        indices[0] = 0;
        for(int i = 1; i <= finalSum / 2; i++) {
            for(int j = 1; j <= i; j++) {
                if (maxCount[i - j] >= 0) {
                    if (maxCount[i] < maxCount[j] + 1) {
                        maxCount[i] = maxCount[j] + 1;
                        indices[i] = j;
                    }
                }
            }
        }

        for(int i = 0; i <= finalSum / 2; i++) {
            System.out.println("i = " + i + " maxCount = " + maxCount[i] + " index = " + indices[i]);
        }
        // fill(solution, indices, finalSum);
        return solution;
    }

    private static boolean compute(long number, List<Long> solution, long factor, String prefix) {
        if (number == 0) {
            return true;
        }
        if (number < 0 || factor > number) {
            return false;
        }
//        System.out.println(prefix + "Including" + number + " : " + factor);
        solution.add(factor);
        boolean include = compute(number - factor, solution, factor - 2, prefix + "\t");
        if (include) {
            return true;
        }

//        System.out.println(prefix + "Excluding" + number + " : " + factor);
        solution.remove(solution.size() - 1);
        return compute(number, solution, factor - 2, prefix + "\t");
    }
}
