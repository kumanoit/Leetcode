package com.kumanoit.tree;

/**
 * 
 * @author Amit Kumar 12-Dec-2019 2:09:11 AM
 * 
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * 
 */
public class ConvertTreeToBst_Easy {

	int maxValue = 0;

	public TreeNode convertBST(TreeNode root) {
		convert(root);
		return root;
	}

	void convert(TreeNode root) {
		if (root == null) {
			return;
		}
		convertBST(root.right);
		root.val += maxValue;
		maxValue = root.val;
		convertBST(root.left);
	}
}
