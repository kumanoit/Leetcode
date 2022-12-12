package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/8/22 IST 7:46 PM
 */
public class SubSets11 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(7);
        list.add(7);
        list.add(8);
        list.add(7);
        list.add(1);
        subsetsWithDup(list).forEach(x -> {
            x.forEach(y -> System.out.print(y + ", "));
            System.out.println();
        });
    }
    public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        compute(A, 0, solution, new ArrayList<>());
        Collections.sort(solution, (a, b) -> {
            int ia = 0;
            int ib = 0;
             if (a.isEmpty()) {
                 return -1;
             } else if (b.isEmpty()) {
                 return 1;
             }
            while(ia < a.size() && ib < b.size()) {
                if (a.get(ia) < b.get(ib)) {
                    return -1;
                } else if (a.get(ia) > b.get(ib)) {
                    return 1;
                }
                ia++;
                ib++;
            }
//            return ia == a.size() ? -1 : (ib == b.size() ? -1 : 1);
            return ia == a.size() ? -1 : 1;
        });
        return solution;
    }

    private static void compute(ArrayList<Integer> list, int index, ArrayList<ArrayList<Integer>> solution,
                          ArrayList<Integer> subList) {
        if (index == list.size()) {
            solution.add(new ArrayList<>(subList));
            return;
        }

        subList.add(list.get(index));

        compute(list, index + 1, solution, subList);
        subList.remove(subList.size() - 1);
        while(index  + 1 < list.size() && list.get(index) == list.get(index + 1)) {
            index++;
        }
        compute(list, index + 1, solution, subList);
    }
}
