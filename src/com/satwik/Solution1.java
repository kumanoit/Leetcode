package com.satwik;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 4/9/22 IST 7:46 PM
 */
public class Solution1 {
    public static void main(String[] args) {
        List<List<Integer>> PawnLoc = new ArrayList<>();
        List<List<Integer>> KnightLoc = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        List<Integer> k1 = new ArrayList<>();
        p1.add(1); p1.add(2);
        p2.add(2); p2.add(4);
        k1.add(0); k1.add(0);
        PawnLoc.add(p1);
        PawnLoc.add(p2);
        KnightLoc.add(k1);
        System.out.println(StrategyForTakingAllPAwns(PawnLoc, KnightLoc));
    }

    public static int StrategyForTakingAllPAwns(List<List<Integer>> PawnLoc, List<List<Integer>> KnightLoc) {
        int pawns = PawnLoc.size();
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        int totalMoves = 0;
        for(List<Integer> location : KnightLoc) {
            queue.add(new int[]{location.get(0), location.get(1), 0});
        }

        Set<String> pawnLocation = new HashSet<>();
        for(List<Integer> pl : PawnLoc) {
            pawnLocation.add(pl.get(0) + "_" + pl.get(1));
        }
        while(pawns > 0 && !queue.isEmpty()) {
            int[] location = queue.remove();
            String key = location[0] + "_" + location[1];
            if (visited.contains(key)) {
                continue;
            }
            visited.add(key);
            if (pawnLocation.contains(key)) {
                totalMoves += location[2];
                pawns--;
//                System.out.println("Found: " + key + " totalMoves = " + totalMoves);
                location[2] = 0;
                pawnLocation.remove(key);
            }

            int[] rowIds = {-2,-2, -1,-1,  1,1, 2,2};
            int[] colIds = {1,-1,  2,-2,  2,-2,-1,1};
//            System.out.println(location[0] + ", " + location[1]);
            for(int i = 0; i < rowIds.length; i++) {
                int rId = rowIds[i] + location[0];
                int cId = colIds[i] + location[1];
//                System.out.println("\t" + rId + ", " + cId);
                if (rId < 0 || cId < 0 || rId > 7 || cId > 7 || visited.contains(rId + "_" + cId)) {
                    continue;
                }
//                System.out.println("\tPushing: " + rId + " " + cId + " with moves = ");
                queue.add(new int[]{rId, cId, location[2] + 1});
            }
        }
        return pawns > 0 ? -1 : totalMoves;
    }

}
