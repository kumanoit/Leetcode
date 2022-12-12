package com.kumanoit.array;

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
 * @author akumar on 10/4/22 IST 7:34 PM
 */
public class scalersol {
    public static void main(String[] args) {
        ArrayList<List<Integer>> solution = new ArrayList<>();
        solution.add(Arrays.asList(2));
//        solution.add(Arrays.asList(3, 4));
//        solution.add(Arrays.asList(6, 5, 7));
//        solution.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(solution));
    }

    public static int minimumTotal(ArrayList<List<Integer>> a) {
        int[] last = new int[1];
        last[0] = a.get(0).get(0);
        System.out.println(Arrays.toString(last));
        for(int i = 1; i < a.size(); i++) {
            int[] curr = new int[i + 1];
            curr[0] = last[0] + a.get(i).get(0);
            for(int j = 1; j < i; j++) {
                curr[j] = Math.min(last[j - 1], last[j]) + a.get(i).get(j);
            }
            curr[i] = last[i - 1] + a.get(i).get(i);
            last = curr;
            System.out.println(Arrays.toString(last));
        }

        int min = last[0];
        for(int i = 1; i < last.length; i++) {
            min = Math.min(last[i], min);
        }
        return min;
    }
}
