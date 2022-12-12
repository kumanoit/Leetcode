package com.stocks;

import java.util.Random;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/10/22 IST 7:42 PM
 */
public class Share {
    public static void main(String[] args) {
        int days = 500;
        double zerothDayCost = 3000.0;
        double[] sharePrice = getSharePrice(days, zerothDayCost, 5.0);
        double[] totalPurchaseCost = new double[days];
        double[] totalSellingValue = new double[days];
        totalPurchaseCost[0] = sharePrice[0];
        totalSellingValue[0] = sharePrice[0];
        for(int i = 1; i < days; i++) {
            totalPurchaseCost[i] = totalPurchaseCost[i - 1] + sharePrice[i];
            totalSellingValue[i] = (i + 1) * sharePrice[i];
        }
        for(int i = 0; i < days; i++) {
            System.out.println("day: " + (i + 1) + ";  selling cost = " + totalSellingValue[i] +
                    ";  profit = " + (totalSellingValue[i] - totalPurchaseCost[i]) +
                    ";  share price " + sharePrice[i] +
                    ";  average cost " + (totalPurchaseCost[i] / (i + 1)));
//            System.out.println(String.format("day: %.3f, selling cost: %10.3f, profit: %10.3f, share price: %10.3f", (i + 1),
//                    totalSellingValue[i], totalPurchaseCost[i], sharePrice[i]));
        }
    }

    private static double[] getSharePrice(int days, double zerothDayCost, double highestPercentage) {
        double[] stockPrice = new double[days];
        Random random = new Random();
        Random timer = new Random(System.currentTimeMillis());
        double lastDayCost = zerothDayCost;
        for(int i = 0; i < days; i++) {
            double percentageChange = highestPercentage * random.nextDouble() * (timer.nextInt() % 2 == 0 ? 1 : -1);
            stockPrice[i] = lastDayCost * (1 + percentageChange / 100.0);
            lastDayCost = stockPrice[i];
        }
        return stockPrice;
    }

}
