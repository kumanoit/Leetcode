package com.kumanoit.recursion;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/1/21 IST 12:59 AM
 */
public class CanPartitionKSubsets {
    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        return isPossible(nums, 0, new int[k], 0, sum / k, new boolean[nums.length], "");
    }

    private static boolean isPossible(int[] nums, int index, int[] subset, int subIndex, int targetSum, boolean[] picked,
                                      String prefix) {
        System.out.println(index);
        if (subset[subIndex] == targetSum) {
            if (subIndex == subset.length - 2) {
                System.out.println(prefix + "F index = " + index + " targetSum = " + targetSum);
                return true;
            }
            System.out.println(prefix + "F index = " + index + " targetSum = " + targetSum);
            return isPossible(nums, 0, subset, subIndex + 1, targetSum, picked, prefix + "\t");
        }

        for (int i = index; i < nums.length; i++) {
            if (picked[i]) {
                continue;
            }
            int sum = subset[subIndex] + nums[i];
            if (sum <= targetSum) {
                System.out.println(prefix + "index = " + i + " sum = " + sum);
                subset[subIndex] += nums[i];
                picked[i] = true;
                if (isPossible(nums, i + 1, subset, subIndex, targetSum, picked, prefix + "\t")) {
                    return true;
                }
                picked[i] = false;
                subset[subIndex] -= nums[i];
            }
        }
        return false;
    }
}
