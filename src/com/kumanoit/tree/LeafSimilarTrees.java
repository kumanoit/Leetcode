package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/leaf-similar-trees/
 * 
 * Algorithm: Do a preorder traversal and find all leaves. Compare these leaves
 * across two trees.
 * 
 * @author kumanoit 25-Dec-2019 10:47:12 PM
 */
public class LeafSimilarTrees {

	public static void main(String[] args) {
		test(new Integer[] {}, new Integer[] {});
		test(new Integer[] { 1 }, new Integer[] { 1 });
		test(new Integer[] { 1, 2, 3 }, new Integer[] { 1, 3, 2 });
		test(new Integer[] { 1, null, 2, null, 3, null, 4, null }, new Integer[] { 1, 2, null, 3, null, 4, null });
	}

	private static void test(final Integer[] nodes1, final Integer[] nodes2) {
		TreeNode root1 = TreeUtils.createTree(nodes1);
		TreeNode root2 = TreeUtils.createTree(nodes2);
		TreeUtils.printTree(root1);
		TreeUtils.printTree(root2);
		System.out.println("Is leaf similar trees: " + leafSimilar(root1, root2));
	}

	private static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> leaf1 = new ArrayList<Integer>();
		List<Integer> leaf2 = new ArrayList<Integer>();
		findLeaf(root1, leaf1);
		findLeaf(root2, leaf2);
		if (leaf1.size() != leaf2.size()) {
			return false;
		}
		for (int i = 0; i < leaf1.size(); i++) {
			if (leaf1.get(i) != leaf2.get(i)) {
				return false;
			}
		}
		return true;
	}

	private static void findLeaf(TreeNode root, List<Integer> leaves) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			leaves.add(root.val);
			return;
		}
		findLeaf(root.left, leaves);
		findLeaf(root.right, leaves);
	}
}
