package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {

	/**
	 * This function creates a tree from given array in level order manner.
	 * 
	 * @param array
	 * @return
	 */
	public static TreeNode createTree(Integer[] array) {
		if (array.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(array[0]);
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int i = 1;
		while (i < array.length) {
			TreeNode node = queue.remove();
			if (node == null) {
				continue;
			}
			if (array[i] != null) {
				node.left = new TreeNode(array[i]);
				queue.add(node.left);
			} else {
				node.left = null;
			}
			i++;

			if (i < array.length && array[i] != null) {
				node.right = new TreeNode(array[i]);
				queue.add(node.right);
			} else {
				node.right = null;
			}
			i++;
		}
		return root;
	}

	/**
	 * This function will print preorder traversal of a tree
	 * 
	 * @param root
	 */
	public static void preorder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + ", ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * This function will print inorder traversal of a tree
	 * 
	 * @param root
	 */
	public static void inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.val + ", ");
		inorder(root.right);
	}

	/**
	 * This function will print postorder traversal of a tree
	 * 
	 * @param root
	 */
	public static void postorder(TreeNode root) {
		if (root == null) {
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.val + ", ");
	}

	/**
	 * This function will print level order traversal of a tree
	 * 
	 * @param root
	 */
	public static void printLevelOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			System.out.print(node.val + ", ");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		System.out.println();
	}

	public static void printTree(TreeNode root) {
		System.out.print("\nPreorder   : ");
		preorder(root);
		System.out.print("\nInorder    : ");
		inorder(root);
		System.out.print("\nPostorder  : ");
		postorder(root);
		System.out.print("\nLevel order: ");
		printLevelOrder(root);
		System.out.println();
	}

	public static NaryTreeNode createNaryTreeNode(final Integer[] array) {
		if (array.length == 0) {
			return null;
		}
		NaryTreeNode root = new NaryTreeNode(array[0]);
		Queue<NaryTreeNode> queue = new LinkedList<NaryTreeNode>();
		queue.add(root);
		int i = 2;
		while (i < array.length) {
			NaryTreeNode ptr = queue.remove();
			List<NaryTreeNode> children = new ArrayList<NaryTreeNode>();
			while (i < array.length && array[i] != null) {
				NaryTreeNode child = new NaryTreeNode(array[i]);
				queue.add(child);
				children.add(child);
				i++;
			}
			if (!children.isEmpty()) {
				ptr.children = children;
			}
			i++;
		}
		return root;
	}
}
