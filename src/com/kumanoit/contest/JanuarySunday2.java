package com.kumanoit.contest;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/2/22 IST 8:18 AM
 */
public class JanuarySunday2 {
    public static void main(String[] args) {
//        test(81583, new int[]);
    }
    private static void test(int mass, int[] asteroids) {
        System.out.println(asteroidsDestroyed(mass, asteroids));
    }
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        for(int asteroid : asteroids) {
            if (mass < asteroid) {
                return false;
            }
            System.out.println(mass + " : " + asteroid);
            mass += asteroid;
        }
        return true;
    }
}
