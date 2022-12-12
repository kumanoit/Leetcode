package com.kumanoit.array;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/17/20 IST 8:29 PM
 */
public class StockBuySellKTransactions {

    public static void main(String[] args) {
//        test(1, new int[]{3, 2, 6, 5, 0, 3});
//        test(2, new int[]{3, 2, 6, 5, 0, 3});
        test(2, new int[]{1,2,4,2,5,7,2,4,9,0});
    }

    private static void test(int k, int[] prices) {
        System.out.println(maxProfit(k, prices));
    }

    private static int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int minSoFar = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                int profit = prices[i - 1] - minSoFar;
                if (profit > 0 ) {
                     addToQueue(minHeap, k, profit);
                }
                minSoFar = prices[i];
            }
        }
        if (prices[prices.length - 1] > minSoFar) {
            int profit = prices[prices.length - 1] - minSoFar;
            if (profit > 0) {
                addToQueue(minHeap, k, profit);
            }
        }

        int totalProfit = 0;
        while (!minHeap.isEmpty()) {
            totalProfit += minHeap.remove();
//            System.out.println("k = " + k + "  " + totalProfit);
        }
        return totalProfit;
    }

    private static void addToQueue(Queue<Integer> minHeap, int capacity, int value) {
        if (minHeap.size() == capacity) {
            if (minHeap.peek() < value) {
                minHeap.remove();
                minHeap.add(value);
            }
        } else {
            minHeap.add(value);
        }
    }
}
