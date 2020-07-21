package com.kumanoit.utils;

public class ArrayUtils {

    public static <T> void print(T[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
    }
}
