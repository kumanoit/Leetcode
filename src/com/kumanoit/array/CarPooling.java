package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/car-pooling/
 * 1094. Car Pooling
 * <p>
 * Approach:
 * In car, at every pickup point passenger count will increase and every drop points passenger count will decrease.
 * So, we will create an array with size of total drop points. For every pickup point we will increase passenger count
 * and at every drop point we will decrease passenger count. Now, iterate through this array and keep adding passenger
 * count and save it in variable current passenger count. If this count gets greater than capacity of vehicle, then it
 * is not possible to pickup and drop all passengers. Thus, return false.
 * <p>
 * <p>
 * Complexity
 * 1. Time: O(n) to traverse all pickup/drop points
 * 2. Space: O(n=1001) kind of constant... for saving passenger count information in an array.
 * It is definitely possible to optimize this using hashmap. But it should be a treemap to keep pickup and drop points
 * sorted. Due to having treemap it will slow down because every insertion will have a complexity of O(lg(n)) to insert
 * and for n nodes it will become O(nlg(n)). Since here we know total number of pickup/drop points it is feasible to use
 * array of predefined size. Had it been not given, we have to use hashmap.
 * This problem is very similar to https://leetcode.com/problems/meeting-rooms-ii/
 * I can't push solution for above meeting rooms problem because it is in paid version.
 *
 * @author akumar on 8/2/20 IST 9:44 PM
 */
public class CarPooling {

    private static final int MAXIMUM_PICKUP_DROP_POINTS = 1001;

    public static void main(final String[] args) {
        test(new int[][]{{2, 1, 5}}, 4);
        test(new int[][]{{2, 1, 5}}, 2);
        test(new int[][]{{2, 1, 5}}, 1);
        test(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4);
        test(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5);
        test(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3);
        test(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11);
    }

    private static void test(final int[][] trips, int capacity) {
        System.out.println("Solution 1: " + isPossibleToPickAndDrop1(trips, capacity));
        System.out.println("Solution 2: " + isPossibleToPickAndDrop2(trips, capacity));
    }

    private static boolean isPossibleToPickAndDrop1(final int[][] trips, int capacity) {
        int[] pickDropPoints = new int[MAXIMUM_PICKUP_DROP_POINTS];
        for (int i = 0; i < trips.length; i++) {
            pickDropPoints[trips[i][1]] += trips[i][0];
            pickDropPoints[trips[i][2]] -= trips[i][0];
        }
        int currentCapacity = 0;
        for (int i = 0; i < pickDropPoints.length; i++) {
            currentCapacity += pickDropPoints[i];
            if (currentCapacity > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * This algorithm is similar to above. It is a bit more structured.
     *
     * @param trips
     * @param capacity
     * @return
     */
    private static boolean isPossibleToPickAndDrop2(int[][] trips, int capacity) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            pairs.add(new Pair(trips[i][1], 's', trips[i][0]));
            pairs.add(new Pair(trips[i][2], 'e', trips[i][0]));
        }
        Collections.sort(pairs);
        int totalPassengers = 0;
        for (Pair pair : pairs) {
            if (pair.type == 's') {
                totalPassengers += pair.passenger;
                if (totalPassengers > capacity) {
                    return false;
                }
            } else {
                totalPassengers -= pair.passenger;
            }
        }
        return true;
    }
}

class Pair implements Comparable<Pair> {
    int point;
    char type;
    int passenger;

    Pair(int point, char type, int passenger) {
        this.point = point;
        this.type = type;
        this.passenger = passenger;
    }

    @Override
    public int compareTo(Pair object) {
        if (this.point == object.point) {
            return this.type == 's' ? 1 : -1;
        }
        return this.point - object.point;
    }
}
