package com.satwik;

import java.util.ArrayList;
import java.util.List;

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
public class Solution2 {
    public static void main(String[] args) {
        MyWorkingHours(50, 100, 5000).forEach(x -> {
            System.out.println(x);
        });
    }
    public static List<Integer> MyWorkingHours(int PeakRate, int OffPeakRate, int XEarnings) {
        int perCarEarning = XEarnings / 10;
        List<Integer> solution = new ArrayList<>();
        if (PeakRate >= OffPeakRate) {
            int peakHours = Math.min(4, perCarEarning / PeakRate);
            int amountRemaining = perCarEarning - peakHours * PeakRate;
            int offHours = amountRemaining / OffPeakRate;
            if (offHours * OffPeakRate < amountRemaining) {
                offHours++;
            }
            solution.add(peakHours);
            solution.add(offHours);
        } else {
            int offHours = Math.min(20, perCarEarning / OffPeakRate);
            int amountRemaining = perCarEarning - offHours * OffPeakRate;
            int peakHours = amountRemaining / PeakRate;
            if (peakHours * PeakRate < amountRemaining) {
                peakHours++;
            }
            solution.add(peakHours);
            solution.add(offHours);
        }
        return solution;
    }
}
