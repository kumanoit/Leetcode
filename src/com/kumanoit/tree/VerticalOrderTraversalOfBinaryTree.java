package com.kumanoit.tree;

import com.kumanoit.utils.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * 987. Vertical Order Traversal of a Binary Tree
 *
 * Approach:
 * Do a level order traversal using two Queues. One queue will keep track of node value and other vertical index.
 * Every time one node is picked up from queue it will be saved in a map which has key as vertical index and value
 * as list of tree node value and its horizontal index. This horizontal index will help to sort values in increasing
 * order which are in same horizontal index and vertical index.
 *
 * Complexity
 * 1. Time: O(nlg(n)) O(n) will be to traverse entire tree, and O(nlgn) for sorting nodes which are at same horizontal
 * level and vertical level. Although this sort will be very less because at a time at max 2 nodes will be at same
 * horizontal and vertical level. (right node of node X, left node of node Y where X & Y are siblings)
 *
 * 2. Space: O(n) for Queue and Map
 *
 * @author akumar on 8/7/20 IST 10:27 PM
 */
public class VerticalOrderTraversalOfBinaryTree {

    public static void main(final String[] args) {
        test(new Integer[]{1});
        test(new Integer[]{1, 2});
        test(new Integer[]{1, 2, 3});
        test(new Integer[]{3, 9, 20, null, null, 15, 7});
        test(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        test(new Integer[]{0, 2, 1, 3, null, null, null, 4, 5, null, 7, 6, null, 10, 8, 11, 9});
    }

    private static void test(final Integer[] nodes) {
        System.out.println("\nSolution list");
        ListUtils.printListOfList(verticalTraversal(TreeUtils.createTree(nodes)));
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        if (root == null) {
            return solution;
        }
        Map<Integer, List<Pair>> map = new TreeMap<>();
        Queue<TreeNode> queueTree = new LinkedList<TreeNode>();
        Queue<Integer> queueLevel = new LinkedList<>();
        queueTree.add(root);
        queueLevel.add(0);
        int horizontalLevel = 0;
        queueTree.add(null);
        while (!queueTree.isEmpty()) {
            TreeNode node = queueTree.remove();
            if (node == null) {
                if (queueTree.size() > 0) {
                    horizontalLevel++;
                    queueTree.add(null);
                    continue;
                } else {
                    break;
                }
            }
            int level = queueLevel.remove();
            if (!map.containsKey(level)) {
                map.put(level, new ArrayList<Pair>());
            }
            map.get(level).add(new Pair(node.val, horizontalLevel));
            if (node.left != null) {
                queueTree.add(node.left);
                queueLevel.add(level - 1);
            }
            if (node.right != null) {
                queueTree.add(node.right);
                queueLevel.add(level + 1);
            }
        }
        for (List<Pair> list : map.values()) {
            Collections.sort(list);
            List<Integer> newList = new ArrayList<>();
            for (Pair pair : list) {
                newList.add(pair.value);
            }
            solution.add(newList);
        }
        return solution;
    }

    static class Pair implements Comparable<Pair> {
        public int value;
        public int level;

        public Pair(int value, int level) {
            this.value = value;
            this.level = level;
        }

        @Override
        public int compareTo(Pair pair) {
            return this.level == pair.level ?
                    this.value - pair.value :
                    this.level - pair.level;
        }
    }

}
