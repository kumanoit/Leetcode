package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Print the longest path from root to leaf node.
 * <p>
 * Approach:
 * Traverse the tree and keep a list of all visited nodes. Keep a solution list which is initialized to null.
 * This solution list will have all nodes in longest path. Whenever leaf node is encountered check if size of current
 * list is greater than solution list. If it is, then save it in solution list. At the end, return solution list
 * <p>
 * Complexity
 * 1. Time: O(n) because each node is traversed only once.
 * 2. Space: O(n) in average case it is O(lg(n)) for stack space. But if tree is skewed than complexity will be O(n)
 *
 * @author akumar on 8/2/20 IST 8:10 PM
 */
public class LongestRootToLeafPath {

    private static List<Integer> longestPath = null;

    public static void main(final String[] args) {
        test(new Integer[]{});
        test(new Integer[]{null});
        test(new Integer[]{1});
        test(new Integer[]{1, 2, 3});
        test(new Integer[]{1, null, 2, null, 3, null, 4});
        test(new Integer[]{1, 2, null, 3, null, 4});
        test(new Integer[]{1, 2, 3, 4, null, null, 6, null, 5, 7, 9, null, null, null, 8});
    }

    private static void test(final Integer[] nodes) {
        if (nodes.length == 0) {
            return;
        }
        TreeNode root = TreeUtils.createTree(nodes);
        List<Integer> longestPath = find(root);
        if (longestPath.isEmpty()) {
            System.out.print("There is no path.");
        } else {
            longestPath.forEach(item -> {
                System.out.print(item + ", ");
            });
        }
        System.out.println();
    }

    private static List<Integer> find(TreeNode root) {
        longestPath = null;
        findLongestPath(root, new ArrayList<>());
        return longestPath;
    }

    private static void findLongestPath(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        nodes.add(root.val);
        if (root.left == null && root.right == null && (longestPath == null || longestPath.size() < nodes.size())) {
            longestPath = new ArrayList<>(nodes);
        } else {
            findLongestPath(root.left, nodes);
            findLongestPath(root.right, nodes);
        }
        nodes.remove(nodes.size() - 1);
    }
}
