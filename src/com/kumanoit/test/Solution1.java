package com.kumanoit.test;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/27/22 IST 9:29 PM
 */
public class Solution1 {
    public static void main(String[] args) {
        String s1 = "Z";
        String s2 = "z";
        String s3 = "y";
        s1.toLowerCase();
        s3.replace('y', 'z');
        System.out.println(s1.equals(s2) + ", " + s2.equals(s3));
    }
}

class hack_thread extends Thread {
    public void run() {
        System.out.println("hi");
    }
}
