package com.kumanoit.tree;

import com.kumanoit.utils.ListUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * 103. Binary Tree Zigzag Level Order Traversal
 * <p>
 *
 * @author akumar on 7/22/20 IST 8:41 PM
 */
public class ZigZagLevelOrderTraversalTest {
    final static Map<TreeNode, List<List<Integer>>> testcase = new HashMap<>();

    private static void addTestCase(final Integer[] treeNodes, final Integer[][] solution) {
        testcase.put(TreeUtils.createTree(treeNodes), Arrays.stream(solution)
                .map(Arrays::asList)
                .collect(Collectors.toList()));
    }

    private static void setUp() {
        addTestCase(new Integer[]{}, new Integer[][]{});
        addTestCase(new Integer[]{1}, new Integer[][]{{1}});
        addTestCase(new Integer[]{1, 2, 3}, new Integer[][]{{1}, {3, 2}});
        addTestCase(new Integer[]{1, 2, 3, null, null, 6, 7}, new Integer[][]{{1}, {3, 2}, {6, 7}});
        addTestCase(new Integer[]{1, 2, 3, 4, 5, 6, 7}, new Integer[][]{{1}, {3, 2}, {4, 5, 6, 7}});
        addTestCase(new Integer[]{1, 2, null, 3, null, 4}, new Integer[][]{{1}, {2}, {3}, {4}});
        addTestCase(new Integer[]{1, null, 2, null, 3, null, 4}, new Integer[][]{{1}, {2}, {3}, {4}});
        addTestCase(new Integer[]{1, 2, 3, 4, null, null, 5, 6, null, null, 7}, new Integer[][]{{1}, {3, 2}, {4, 5}, {7, 6}});
    }

    public static void getZigZagLevelOrderTraversalUsingTwoStacksTest() {
        setUp();
        testcase.forEach((treeNode, solution) -> {
            List<List<Integer>> children = ZigZagLevelOrderTraversal.getZigZagLevelOrderTraversalUsingTwoStacks(treeNode);
            if (!children.equals(solution)) {
                System.out.println("Failed test case: ");
                System.out.println("Expected:");
                ListUtils.printListOfList(solution);
                System.out.println("Actual:");
                ListUtils.printListOfList(children);
                System.out.println("\n\n");
            } else {
                System.out.println("Test cases pass");
            }
            TreeUtils.printLevelOrder(treeNode);
        });
    }

    public static void getZigZagLevelOrderTraversalUsingRecursionTest() {
        setUp();
        testcase.forEach((treeNode, solution) -> {
            List<List<Integer>> children = ZigZagLevelOrderTraversal.getZigZagLevelOrderTraversalWithRecursion(treeNode);
            if (!children.equals(solution)) {
                System.out.println("Failed test case: ");
                System.out.println("Expected:");
                ListUtils.printListOfList(solution);
                System.out.println("Actual:");
                ListUtils.printListOfList(children);
                System.out.println("\n\n");
            } else {
                System.out.println("Test cases pass");
            }
            TreeUtils.printLevelOrder(treeNode);
        });
    }

    public static void main(String[] args) {
//        getZigZagLevelOrderTraversalUsingTwoStacksTest();
        getZigZagLevelOrderTraversalUsingRecursionTest();
    }
}
