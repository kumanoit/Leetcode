package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/4/21 IST 12:42 AM
 */
public class BurningTree {

    public static void main(String[] args) {
        test(TreeUtils.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), 4);
        test(TreeUtils.createTree(new Integer[]{12, 13, 10, null, null, 14, 15, 21, 24, 22, 23}), 14);
        test(TreeUtils.createTree(new Integer[]{12, 19, 82, 41, null, 15, 95, null, 2, 21, null, 7, 16}), 41);
        test(TreeUtils.createTree(new Integer[]{12, 19, 82, 41, null, 15, 95, null, 2, 21, null, 7, 16}), 12);
        test(TreeUtils.createTree(new Integer[]{12, 19, 82, 41, null, 15, 95, null, 2, 21, null, 7, 16}), 21);
        test(TreeUtils.createTree(new Integer[]{12, 19, 82, 41, null, 15, 95, null, 2, 21, null, 7, 16}), 16);
        test(TreeUtils.createTree(new Integer[]{12, 19, 82, 41, null, 15, 95, null, 2, 21, null, 7, 16}), 7);
        test(TreeUtils.createTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6, null}), 1);
        test(TreeUtils.createTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6, null}), 1);
        test(TreeUtils.createTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6, null}), 2);
        test(TreeUtils.createTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6, null}), 2);
        test(TreeUtils.createTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6, null}), 3);
        test(TreeUtils.createTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6, null}), 3);
        test(TreeUtils.createTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6, null}), 4);
        test(TreeUtils.createTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6, null}), 4);
        test(TreeUtils.createTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6, null}), 6);
        test(TreeUtils.createTree(new Integer[]{1, 2, null, 3, null, 4, null, 5, null, 6, null}), 6);
    }

    private static void test(TreeNode root, int key) {
        Map<Integer, List<Integer>> solution = new HashMap<>();
        burnTheTree(root, key, solution);
        solution.forEach((k, v) -> {
            System.out.print(k + ": ");
            v.forEach(i -> {
                System.out.print(i + ", ");
            });
            System.out.println();
        });
        System.out.println();
    }

    private static int burnTheTree(final TreeNode root, final int key,
                                   final Map<Integer, List<Integer>> solution) {
        if (root == null) {
            return -1;
        }

        if (root.val == key) {
            burnInSubtree(root, solution, 0);
            return 1;
        }

        int keyPresentInLeftSubtree = burnTheTree(root.left, key, solution);
        if (keyPresentInLeftSubtree != -1) {
            if (!solution.containsKey(keyPresentInLeftSubtree)) {
                solution.put(keyPresentInLeftSubtree, new ArrayList<>());
            }
            solution.get(keyPresentInLeftSubtree).add(root.val);
            burnInSubtree(root.right, solution, keyPresentInLeftSubtree + 1);
            return keyPresentInLeftSubtree + 1;
        }

        int keyPresentInRightSubtree = burnTheTree(root.right, key, solution);
        if (keyPresentInRightSubtree != -1) {
            if (!solution.containsKey(keyPresentInRightSubtree)) {
                solution.put(keyPresentInRightSubtree, new ArrayList<>());
            }
            solution.get(keyPresentInRightSubtree).add(root.val);
            burnInSubtree(root.left, solution, keyPresentInRightSubtree + 1);
            return keyPresentInRightSubtree + 1;
        }
        return -1;
    }

    private static void burnInSubtree(TreeNode root, final Map<Integer, List<Integer>> solution, int index) {
        if (root == null) {
            return;
        }
        if (!solution.containsKey(index)) {
            solution.put(index, new ArrayList<>());
        }
        solution.get(index).add(root.val);
        burnInSubtree(root.left, solution, index + 1);
        burnInSubtree(root.right, solution, index + 1);
    }
}
