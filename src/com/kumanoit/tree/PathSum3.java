package com.kumanoit.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * <p>
 * 437. Path Sum III
 * <p>
 * Approach:
 * <p>
 * Traverse from root to leaf and save cumulative sum in a list. Check if cumulativeSum-sum number is present in the list.
 * If it is present then it means that sum is present while adding some/all nodes on the current path. If not, then sum
 * is not present in this path. Now, what would happen if there are more than such group of numbers which lead to sum?
 * To handle this case, use a map where key will be cumulative sum so far and value will be the number of times this
 * cumulative sum was found.
 * Complexity
 * 1. Time: O(n) (O(n) to traverse tree + O(1) for lookup in map)
 * 2. Space: O(n) for creating a map
 *
 * @author akumar on 8/8/20 IST 6:51 PM
 */

public class PathSum3 {

    public static void main(String[] args) {
        test(new Integer[]{0}, 0);//1
        test(new Integer[]{0, 1}, 1);//2
        test(new Integer[]{0, 0, 0}, 5);//0
        test(new Integer[]{0, -1, 1}, 0);//1
        test(new Integer[]{1, 2, 3, 4, 5, 6, 7}, 7);//3
        test(new Integer[]{0, -1, -1, 1, 1, 1, 1}, 0);//9
        test(new Integer[]{1, 2, null, 3, null, 4, null, 5}, 5);//2
        test(new Integer[]{1, 2, null, 3, null, 4, null, 5}, 15);//1
        test(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}, 8);//3
        test(new Integer[]{1, 2, null, -1, null, 1, null, 4, null, -4}, -1);//1
    }

    private static void test(final Integer[] nodes, int sum) {
        TreeNode root = TreeUtils.createTree(nodes);
        System.out.println(pathSum(root, sum));
    }
//
//    private static int pathSum(TreeNode root, int sum) {
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(0, 1);
//        return find(root, sum, map, 0);
//    }
//
//    private static int find(TreeNode root, int sum, Map<Integer, Integer> map, int sumSoFar) {
//        if (root == null) {
//            return 0;
//        }
//        sumSoFar += root.val;
//        int totalWays = map.getOrDefault(sumSoFar - sum, 0);
//        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0) + 1);
//        totalWays += find(root.left, sum, map, sumSoFar);
//        totalWays += find(root.right, sum, map, sumSoFar);
//        map.put(sumSoFar, map.get(sumSoFar) - 1);
//        return totalWays;
//    }

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        int count = find(root, sum, map, 0);
        return count;
    }
    private static int find(TreeNode root, int sum, Map<Integer, Integer> map, int sumSoFar) {
        if (root == null) {
            return 0;
        }
        sumSoFar += root.val;
        int totalWays = map.getOrDefault(sumSoFar - sum, 0);
        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0) + 1);
        totalWays += find(root.left, sum, map, sumSoFar);
        totalWays += find(root.right, sum, map, sumSoFar);
        map.put(sumSoFar, map.get(sumSoFar) - 1);
        return totalWays;
    }
}
