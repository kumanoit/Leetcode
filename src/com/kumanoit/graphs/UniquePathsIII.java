package com.kumanoit.graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/19/22 IST 7:41 PM
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> l1 = new ArrayList<>();//ArrayList<Integer>) Arrays.asList(1,0,0,0);
        ArrayList<Integer> l2 = new ArrayList<>();//(ArrayList<Integer>) Arrays.asList(0,0,0,0);
        ArrayList<Integer> l3 = new ArrayList<>();//(ArrayList<Integer>) Arrays.asList(0,0,2,-1);
        l1.add(1); l1.add(0); l1.add(0); l1.add(0);
        l2.add(0); l2.add(0); l2.add(0); l2.add(0);
        l3.add(0); l3.add(0); l3.add(2); l3.add(-1);
        list.add(l1);
        list.add(l2);
        list.add(l3);
        System.out.println(solve(list));
    }

    public static int solve(ArrayList<ArrayList<Integer>> A) {
        int rows = A.size();
        int cols = A.get(0).size();
        int walkingSquares = 0;
        int[] start = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A.get(i).get(j) == 1) {
                    start = new int[]{i, j};
                    A.get(i).set(j, 0);
                    walkingSquares++;
                } else if (A.get(i).get(j) == 0) {
                    walkingSquares++;
                }
            }
        }
        return compute(A, start[0], start[1], walkingSquares, "");
    }

    private static int compute(ArrayList<ArrayList<Integer>> A, int rid, int cid, int size, String prefix) {
        if (size < 0 ||
                rid < 0 || rid == A.size() ||
                cid == A.get(0).size() || cid < 0 ||
                A.get(rid).get(cid) == -1 || A.get(rid).get(cid) == 1) {
            return 0;
        }
        System.out.println(prefix + A.get(rid).get(cid) + " > ");
        if (A.get(rid).get(cid) == 2) {
            if (size == 0) {
                return 1;
            }
            return 0;
        }
        int[] rows = {-1, 0, 0, 1};
        int[] cols = {0, -1, 1, 0};
        int total = 0;
        A.get(rid).set(cid, -1);
        for (int k = 0; k < rows.length; k++) {
            total += compute(A, rid + rows[k], cid + cols[k], size - 1, prefix + "\t");
        }
        A.get(rid).set(cid, 0);
        return total;
    }
}
