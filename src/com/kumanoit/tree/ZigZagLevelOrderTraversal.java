package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * 103. Binary Tree Zigzag Level Order Traversal
 * <p>
 * Approach-1:
 * Take two stacks and in one stack fill left child first and then right child and in
 * other stack right child first and the left child.
 * Complexity
 * 1. Time: O(n): for visiting each node once
 * 2. Space: O(n): even if there are two stacks each element will be saved once in either of stacks
 * <p>
 * Approach-2:
 * Take one queue and recursively populate element into it.
 * Based on level fill elements either from left to right or right to left
 * Complexity
 * 1. Time: O(n): for visiting each node once
 * 2. Space: O(n): for saving each element. In case if this is not considered as we need to return this information,
 * then space will be height of tree which O(lg(n)). In worst case, the tree can be left/right skewed and again
 * complexity will be O(n)
 *
 * @author akumar on 7/22/20 IST 8:33 PM
 */
public class ZigZagLevelOrderTraversal {

    public static List<List<Integer>> getZigZagLevelOrderTraversalWithRecursion(final TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        if (root == null) {
            return solution;
        }
        traverse(root, 0, solution);
        return solution;
    }

    public static List<List<Integer>> getZigZagLevelOrderTraversalUsingTwoStacks(final TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        if (root == null) {
            return solution;
        }
        Stack<TreeNode> leftRight = new Stack<TreeNode>();
        Stack<TreeNode> rightLeft = new Stack<TreeNode>();
        leftRight.push(root);
        while (!(leftRight.isEmpty() && rightLeft.isEmpty())) {
            List<Integer> subSolution = new ArrayList<Integer>();
            while (!leftRight.isEmpty()) {
                TreeNode ptr = leftRight.pop();
                subSolution.add(ptr.val);
                if (ptr.left != null) {
                    rightLeft.push(ptr.left);
                }
                if (ptr.right != null) {
                    rightLeft.push(ptr.right);
                }
            }
            if (!subSolution.isEmpty()) {
                solution.add(subSolution);
            }
            subSolution = new ArrayList<Integer>();
            while (!rightLeft.isEmpty()) {
                TreeNode ptr = rightLeft.pop();
                subSolution.add(ptr.val);
                if (ptr.right != null) {
                    leftRight.push(ptr.right);
                }
                if (ptr.left != null) {
                    leftRight.push(ptr.left);
                }
            }
            if (!subSolution.isEmpty()) {
                solution.add(subSolution);
            }
        }
        return solution;
    }

    private static void traverse(final TreeNode root, int level, List<List<Integer>> solution) {
        if (root == null) {
            return;
        }
        if (solution.size() <= level) {
            solution.add(new ArrayList<>());
        }
        List<Integer> subSolution = solution.get(level);
        if (level % 2 == 0) {
            subSolution.add(root.val);
        } else {
            subSolution.add(0, root.val);
        }
        traverse(root.left, level + 1, solution);
        traverse(root.right, level + 1, solution);
    }
}
