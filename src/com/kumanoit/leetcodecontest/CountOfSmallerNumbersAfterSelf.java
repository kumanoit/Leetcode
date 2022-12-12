package com.kumanoit.leetcodecontest;

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
 * @author akumar on 6/27/21 IST 11:49 AM
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        test(new int[]{5, 2, 6, 1});
        test(new int[]{-1});
        test(new int[]{-1, -1});
        test(new int[]{1, 1, 1, 1, 1});
        test(new int[]{1, 2, 3, 4, 5});
        test(new int[]{5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1});

    }

    private static void test(int[] nums) {
        for (int x : countSmaller(nums)) {
            System.out.print(x + ", ");
        }
        System.out.println();
    }

    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        count[n - 1] = 0;
        TreeNode root = new TreeNode(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            count[i] = getSolution(root, nums[i]).count;
        }
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            solution.add(count[i]);
        }
        return solution;
    }

    private static NodeCount getSolution(TreeNode root, int val) {
        if (root == null) {
            NodeCount nodeCount = new NodeCount(new TreeNode(val), 0);
            return nodeCount;
        }

        if (root.val == val) {
            root.count++;
            return new NodeCount(root, root.leftCount);
        }

        if (root.val > val) {
            root.leftCount++;
            NodeCount nodeCount = getSolution(root.left, val);
            root.left = nodeCount.node;
            return new NodeCount(root, nodeCount.count);
        }
        root.rightCount++;
        NodeCount nodeCount = getSolution(root.right, val);
        root.right = nodeCount.node;
        return new NodeCount(root, root.leftCount + root.count + nodeCount.count);
    }
//    private static int getSmallerNodeCount(TreeNode root, int val) {
//        if (root == null) {
//            return 0;
//        }
//        if (root.val == val) {
//            return root.leftCount;
//        }
//
//        if (root.val > val) {
//            return getSmallerNodeCount(root.left, val);
//        }
//
//        return root.leftCount + root.count + getSmallerNodeCount(root.right, val);
//    }
//
//    private static TreeNode insert(TreeNode root, int val) {
//        if (root == null) {
//            return new TreeNode(val);
//        }
//        if (val == root.val) {
//            root.count++;
//        } else if (val < root.val) {
//            root.leftCount++;
//            root.left = insert(root.left, val);
//        } else {
//            root.rightCount++;
//            root.right = insert(root.right, val);
//        }
//
//        return root;
//    }
}

class NodeCount {
    TreeNode node;
    int count;
    public NodeCount(TreeNode root, int count) {
        this.node = root;
        this.count = count;
    }
}

class TreeNode {
    int val;
    int count;
    int leftCount;
    int rightCount;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.count = 1;
        this.leftCount = 0;
        this.rightCount = 0;
    }
}
