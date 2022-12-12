package com.ukgfreak;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/5/22 IST 8:30 PM
 */
public class Question22 {
    public static void main(String[] args) {
        print(sortJumbled(new int[]{7,9,4,1,0,3,8,6,2,5}, new int[]{47799,19021,162535,454,95,51890378,404}));
    }

    public static int[] sortJumbled(int[] mapping, int[] nums) {
        int[][] withSorted = new int[nums.length][2];
        for(int i = 0; i < nums.length; i++) {
            withSorted[i][0] = getMaximum(mapping, nums[i]);
            withSorted[i][1] = i;
        }
        for(int i = 0; i < withSorted.length; i++) {
            System.out.println(nums[withSorted[i][1]] + " : " + withSorted[i][0]);
        }

        Arrays.sort(withSorted, (a, b) -> a[0] - b[0]);
        int[] sorted = new int[nums.length];
        for(int i = 0; i < sorted.length; i++) {
            System.out.println(withSorted[i][0] + " >> " + withSorted[i][1] + " : " + nums[withSorted[i][1]]);
            sorted[i] = nums[withSorted[i][1]];
        }
        return sorted;

//        return Arrays.stream(nums)
//                .boxed()
//                .sorted((a, b) -> (getMaximum(mapping, a) - getMaximum(mapping, b)))
//                .mapToInt(i -> i)
//                .toArray();
    }

    private static void print(int[] array) {
        System.out.println();
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }
    private static int getMaximum(int[] mapping, int num) {

        String input = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            sb.append(mapping[input.charAt(i) - '0']);
        }
        return Integer.parseInt(sb.toString());
    }
}
