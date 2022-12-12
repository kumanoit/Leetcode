package com.kumanoit.ukgfreak;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoinsEnergy {
    public static void main(String[] args) {
        System.out.println(getRich(0, Arrays.asList(2, 1, 1), Arrays.asList(11, 5, 7)));
        System.out.println(getRich(1, Arrays.asList(1,5,3,3,1), Arrays.asList(3,23,9,2,2)));
    }

    public static int getRich(long initialEnergy, List<Integer> energy, List<Integer> coins) {
        int size = energy.size();
        int[][] memo = new int[size+1][size+1];
        for(int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        for (int last = size - 1; last >= 0; last--) {
            for (int energyLevel = 0; energyLevel <= size; energyLevel++) {
                if (last == size - 1) {
                    memo[last][energyLevel] = coins.get(last);
                } else {
                    if (energyLevel - 1 >= 0) {
                        memo[last][energyLevel] = memo[last + 1][Math.min(energyLevel + energy.get(last) - 1, size)];
                        memo[last][energyLevel] = Math.max(memo[last][energyLevel], coins.get(last) + memo[last + 1][energyLevel - 1]);
                    }
                    if (energyLevel == 0) {
                        memo[last][energyLevel] = Math.max(memo[last][energyLevel], coins.get(last));
                    }
                }
            }
        }
        int energyIndex = (int) Math.min(size, initialEnergy);
        return memo[0][energyIndex];
    }
//    public static int getRich(long initialEnergy, List<Integer> energy, List<Integer> coins) {
//        int size = coins.size();
//        int[] memo = new int[size + 1];
//        Arrays.fill(memo, -1);
//        int coin = coins.get(0);
//        int enr = energy.get(0);
//        memo[(int) Math.min(size, initialEnergy)] = coin;
//        int ener = (int) Math.min(size, initialEnergy + enr);
//        memo[ener] = Math.max(memo[ener], 0);
//
//        int solution = coin;
//        for (int i = 1; i < size; i++) {
//            int[] subSolution = new int[size + 1];
//            Arrays.fill(subSolution, -1);
//            int co = coins.get(i);
//            int en = energy.get(i);
//            for (int currentEnergy = 1; currentEnergy <= size; currentEnergy++) {
//                if (memo[currentEnergy] == -1) {
//                    continue;
//                }
//                int nextCoin = memo[currentEnergy] + co;
//                subSolution[currentEnergy - 1] = Math.max(subSolution[currentEnergy - 1], nextCoin);
//                solution = Math.max(solution, nextCoin);
//
//                int nextEnergy = Math.min(size, currentEnergy - 1 + en);
//                subSolution[nextEnergy] = Math.max(subSolution[nextEnergy], memo[currentEnergy]);
//            }
//
//            memo = subSolution;
//        }
//        return solution;
//    }
}
