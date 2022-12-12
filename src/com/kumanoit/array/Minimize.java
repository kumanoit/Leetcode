package com.kumanoit.array;

import java.util.Stack;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/15/22 IST 9:13 PM
 */
public class Minimize {
    public static void main(String[] args) {
        System.out.println(minimizeArrayValue(new int[]{0,0,0,0,6}));
    }

    public static int minimizeArrayValue(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] < stack.peek()) {
                stack.push(nums[i]);
            } else {
                int top = stack.pop();
                int sum = top + nums[i];
                int half = sum / 2;
                if ((sum & 1) == 1) {
                    stack.push(half + 1);
                } else {
                    stack.push(half);
                }
                stack.push(half);
            }
        }

        while (stack.size() != 1) {
            int top = stack.pop();
            int second = stack.pop();
            int sum = top + second;
            int half = sum / 2;
            if ((sum & 1) == 1) {
                stack.push(half + 1);
            } else {
                stack.push(half);
            }
        }
        return stack.pop();
    }
}
