package com.kumanoit.array;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/12/20 IST 8:24 PM
 */
public class StockBuySell2Transactions {

    public static void main(String[] args) {
        test(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        test(new int[]{1, 2, 3, 4, 5});
        test(new int[]{7, 6, 4, 3, 1});
        test(new int[]{1});
    }

    private static void test(int[] prices) {
        System.out.println(getMaxProfit(prices));
    }

    private static int getMaxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MAX_VALUE;
        int sell2 = 0;
        StringBuilder buy11 = new StringBuilder();
        StringBuilder buy22 = new StringBuilder();
        StringBuilder sell11 = new StringBuilder();
        StringBuilder sell22 = new StringBuilder();
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]); // buying at minimum price
            sell1 = Math.max(sell1, prices[i] - buy1); // selling at current price and thus making profit w.r.t buy1
            buy2 = Math.min(buy2, prices[i] - sell1); // buying second stock at next minimum price
            sell2 = Math.max(sell2, prices[i] - buy2);
            buy11.append(buy1).append(" ");
            buy22.append(buy2).append(" ");
            sell11.append(sell1).append(" ");
            sell22.append(sell2).append(" ");
        }
        System.out.println(buy11);
        System.out.println(sell11);
        System.out.println(buy22);
        System.out.println(sell22);
        return sell2;
    }
}
