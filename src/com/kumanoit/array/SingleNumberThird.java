package com.kumanoit.array;

/**
 * https://leetcode.com/problems/single-number-iii/
 * 260. Single Number III
 * Approach:
 * The two unique number will differ in some bit.
 * So, first take xor of all numbers. This will remove all bits of numbers which are repeating.
 * Now, choose a bit from above xor where the two unique number differs. This can be done by doing xor & ~(xor - 1);
 * This will return last bit which is different. Based on this bit again take xor of numbers which has this bit set
 * int one variable and for those numbers where this bit is not set, take xor in other variable.
 * Bits of repeating numbers will get unset and only those two unique numbers will be left.
 * Complexity
 * 1. Time: O(n) since, there are two traversal of input integer array
 * 2. Space: O(1) Constant space
 *
 * @author akumar on 7/23/20 IST 11:05 PM
 */
public class SingleNumberThird {

    public static Integer[] getSingleNumber(Integer[] nums) {
        int xor = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        int mask = xor & ~(xor - 1);
        for (int i = 0; i < nums.length; i++) {
            if ((mask & nums[i]) > 0) {
                x ^= nums[i];
            } else {
                y ^= nums[i];
            }
        }
        return new Integer[]{x, y};
    }
}
