package com.kumanoit;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

class CupMap {
    String name;
    Integer number;
    public CupMap(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "{" + this.name + ": " + this.number + "}";
    }
}
public class Sample {

    //    public static void main(String[] args) {
//
//    }
//
//    public float maximumCompatibilityFactor(String[] input1, int input2) {
//
//    }
    public static long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] == b[1] ? (a[0] == b[0] ? b[2] - a[2] : a[0] - b[0]) : a[1] - b[1]);
        for (int[] num : rides) {
            System.out.println(num[0] + ", " + num[1] + ", " + num[2]);
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxProfit = 0;
        for (int i = 0; i < rides.length; i++) {
            Integer floor = map.floorKey(rides[i][0]);
            int lastProfit = 0;
            if (floor != null) {
                lastProfit = map.get(floor);
            }
            int currentProfit = (rides[i][1] - rides[i][0] + rides[i][2]) + lastProfit;
            if (!map.containsKey(rides[i][1]) || map.get(rides[i][1]) < currentProfit) {
                map.put(rides[i][1], currentProfit);
            }
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        for (int key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
        return maxProfit;
    }

    static void add(Integer a) {
        a += 10;
    }
    public static void main(String[] args) {
        CupMap amit = new CupMap("amit", 100);
        CupMap sticky = new CupMap("sticky", 50);
        CupMap kumar = new CupMap("kumar", 78);
        CupMap amit2 = new CupMap("mug", 100);
        List<CupMap> list = new ArrayList<>();
        list.add(amit);
        list.add(sticky);
        list.add(kumar);
        list.add(amit2);
        Collections.sort(list, (a, b) -> {
            if (a.number == b.number) {
                return a.name.compareTo(b.name);
            } else {
                return b.number - a.number;
            }
        });
        list.forEach(c -> {
            System.out.println(c.toString());
        });
        Integer x = 10;
        System.out.println(x);
        add(x);
        System.out.println(x);
//        maxTaxiEarnings(10, new int[][]{{9, 10, 2}, {4, 5, 6}, {6, 8, 1}, {1, 5, 5}, {4, 9, 5}, {1, 6, 5}, {4, 8, 3}, {4, 7, 10}, {1, 9, 8}, {2, 3, 5}});
//        System.out.println(canKillTheBoss(1, 10));
//        System.out.println(canKillTheBoss(2, 10));
//        System.out.println(canKillTheBoss(3, 10));
//        System.out.println(canKillTheBoss(4, 10));
//        System.out.println(canKillTheBoss(5, 10));
//        System.out.println(canKillTheBoss(6, 10));
//        System.out.println(canKillTheBoss(7, 10));
//        System.out.println(canKillTheBoss(8, 10));
//        System.out.println(canKillTheBoss(9, 10));
//        System.out.println(canKillTheBoss(1, 0) + "\n\n");
//        System.out.println(canKillTheBoss(10, 10) + "\n\n");
//        System.out.println(canKillTheBoss(10, 11) + "\n\n");
//        System.out.println(canKillTheBoss(11, 10) + "\n\n");
//        System.out.println(canKillTheBoss(12, 10) + "\n\n");
//        System.out.println(canKillTheBoss(20, 300) + "\n\n");
//        System.out.println(canKillTheBoss(19, 300) + "\n\n");
//        System.out.println(canKillTheBoss(18, 300) + "\n\n");
//        System.out.println(canKillTheBoss(17, 300) + "\n\n");
//        System.out.println(canKillTheBoss(16, 300) + "\n\n");
    }

    private static String canKillTheBoss(int input1, int input2) {
        int liveBombs = (1 << (input1 + 1)) - 1;
        return calculate(input2, liveBombs, new Boolean[liveBombs + 1][input2 + 1], true) ? "true" : "false";
    }

    private static boolean calculate(int N, int liveBombs, Boolean[][] memo, boolean player1) {
        int index = 21;
        while (index > 0 && ((liveBombs & (1 << index)) == 0)) {
            index--;
        }
        if (index >= N) {
            System.out.println((player1 ? "P1" : "P2") + " " + index);
            return true;
        }
        if (index == 0) {
            return false;
        }
        if (memo[liveBombs][N] != null) {
            return memo[liveBombs][N];
        }

        for (int i = 1; i <= index; i++) {
            if ((liveBombs & (1 << i)) == 0) {
                continue;
            }
            int temp = liveBombs ^ (1 << i);
            boolean isPossibleByOtherPlayer = calculate(N - i, temp, memo, !player1);
            if (!isPossibleByOtherPlayer) {
                System.out.println((player1 ? "P1" : "P2") + " " + i);
                memo[liveBombs][N] = true;
                return true;
            }
        }
        memo[liveBombs][N] = false;
        return false;
    }
//
//    private static boolean calculate(int N, int liveBombs, String prefix, Boolean[][] memo) {
////        System.out.println(prefix + Integer.toBinaryString(liveBombs));
//
//        int index = 20;
//        while(index > 0 && ((liveBombs & (1 << index)) == 0)) {
//            index--;
//        }
//        if (index >= N) {
//            return true;
//        }
//        if (index == 0) {
//            return false;
//        }
//        if (memo[liveBombs][N] != null) {
//            return memo[liveBombs][N];
//        }
//
////        System.out.println(prefix + index);
//        for(int i = index; i > 0; i--) {
//            if ((liveBombs & (1 << i)) == 0) {
//                continue;
//            }
//            int temp = liveBombs ^ (1 << i);
//            boolean isPossibleByOtherPlayer = calculate(N, temp, prefix + "\t", memo);
//            if (!isPossibleByOtherPlayer) {
//                memo[liveBombs][N] = true;
//                return true;
//            }
//        }
//        memo[liveBombs][N] = false;
//        return false;
//    }
//
//    private static boolean calculate(int N, boolean[] bombs, String prefix) {
//
//        int index = bombs.length - 1;
//        while(index > 0 && bombs[index] == false) {
//            index--;
//        }
//        if (index >= N) {
//            return true;
//        }
//        System.out.println(prefix + index);
//        for(int i = index; i >= 1; i--) {
//            if (!bombs[i]) {
//                continue;
//            }
//            bombs[i] = false;
//            boolean isPossibleByOtherPlayer = calculate(N, bombs, prefix + "\t");
//            bombs[i] = true;
//            if (!isPossibleByOtherPlayer) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean calculate(int N, TreeSet<Integer> set) {
//        if (set.isEmpty()) {
//            return false;
//        }
//        if (set.last() >= N) {
//            return true;
//        }
//
//
//        for(int ele : set) {
//            int last = set.last();
//            set.remove(last);
//            boolean isPossibleByOtherPlayer = calculate(N - ele, set);
//            set.add(last);
//            if (!isPossibleByOtherPlayer) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private boolean calculate(int n, int N, Boolean[][] isPossible) {
//        if (n >= N) {
//            return true;
//        }
//
//        if (isPossible[n][N] != null) {
//            return isPossible[n][N];
//        }
//        for(int i = 1; i <= n; i++) {
//            if (!calculate(n, N - i, isPossible)) {
//                isPossible[n][N] = true;
//                return true;
//            }
//        }
//        isPossible[n][N] = false;
//        return false;
//    }
}
