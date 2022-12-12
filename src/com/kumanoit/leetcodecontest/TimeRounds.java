package com.kumanoit.leetcodecontest;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 6/20/21 IST 8:27 AM
 */
public class TimeRounds {
    public static void main(String[] args) {
        System.out.println(numberOfRounds("00:01","00:00"));
    }

    public static int numberOfRounds(String startTime, String finishTime) {
        int startH = Integer.parseInt(startTime.split(":")[0]);
        int startM = Integer.parseInt(startTime.split(":")[1]);
        int endH = Integer.parseInt(finishTime.split(":")[0]);
        int endM = Integer.parseInt(finishTime.split(":")[1]);

        int startCeil = getCeil(startM);

        if (endH < startH || (endH == startH && endM < startM)) {
            endH += 24;
        }

        if (startM != 0 && startCeil == 0) {
            startH++;
            startM = startCeil;
        }

        if (startH > endH) {
            return 0;
        }

        endM = getBase(endM);

        if (endM < startM) {
            if (endH == startH) {
                return 0;
            }
            endH--;
            endM += 60;
        }

        int minDiff = endM - startM;
        int hrDiff = endH - startH;
        return minDiff / 15 + hrDiff * 4;

        // while(startH < endH) {
        //     startMin = (startMin + 15) % 60;
        //     if (startMin == 0) {
        //         startH++;
        //     }
        //     if (startMin < endMin) {
        //         count++;
        //     }
        // }System.out.println(numberOfRounds("23:46","23:59));
    }

    private static int getCeil(int min) {
        if (min > 0 && min < 15) {
            return 15;
        }
        if (min > 15 && min < 30) {
            return 30;
        }
        if (min > 30 && min < 45) {
            return 45;
        }
        if (min > 45) {
            return 0;
        }
        return min;
    }

    private static int getBase(int min) {
        if (min < 15) {
            return 00;
        }
        if (min > 15 && min < 30) {
            return 15;
        }
        if (min > 30 && min < 45) {
            return 30;
        }
        if (min > 45 && min < 60) {
            return 45;
        }
        return min;
    }
}
