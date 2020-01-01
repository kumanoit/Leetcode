package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/submissions/
 * 
 * Approach: Do an inorder traversal and save both the trees. Since, it is
 * Binary Search Tree, inorder traversal will give sorted array. Merge these two
 * sorted arrays.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * @author kumanoit 01-Jan-2020 11:50:02 PM
 */
public class AllElementsInTwoBinarySearchTree {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {});
		test(new Integer[] { 1 }, new Integer[] {});
		test(new Integer[] {}, new Integer[] { 1 });
		test(new Integer[] { 1 }, new Integer[] { 1 });
		test(new Integer[] { 50, 40, null, 30, null, 20, null, 10 }, new Integer[] { 5, null, 15, null, 25, null, 35, null, 45, null });
	}

	private static void test(final Integer[] numbers1, final Integer[] numbers2) {
		TreeNode root1 = TreeUtils.createTree(numbers1);
		TreeNode root2 = TreeUtils.createTree(numbers2);
		System.out.print("\nTree 1: Inorder traversal  : ");
		TreeUtils.inorder(root1);
		System.out.print("\nTree 2: Inorder traversal  : ");
		TreeUtils.inorder(root2);
		System.out.print("\nAfter merging two trees    : ");
		getAllElements(root1, root2).forEach(item -> System.out.print(item + ", "));
		System.out.println();
	}

	public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = new ArrayList<Integer>();
		getInorder(root1, list1);
		List<Integer> list2 = new ArrayList<Integer>();
		getInorder(root2, list2);
		List<Integer> solution = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		while (i < list1.size() && j < list2.size()) {
			if (list1.get(i) < list2.get(j)) {
				solution.add(list1.get(i++));
			} else {
				solution.add(list2.get(j++));
			}
		}
		while (i < list1.size()) {
			solution.add(list1.get(i++));
		}
		while (j < list2.size()) {
			solution.add(list2.get(j++));
		}
		return solution;
	}

	private static void getInorder(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}
		getInorder(root.left, list);
		list.add(root.val);
		getInorder(root.right, list);
	}
}
