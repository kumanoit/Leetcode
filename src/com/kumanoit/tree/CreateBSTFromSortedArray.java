package com.kumanoit.tree;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * 
 * @author kumanoit
 * 22-Dec-2019 3:50:39 PM
 */
public class CreateBSTFromSortedArray {

	public static void main(String[] args) {
		test(new int[] {});
		test(new int[] { 1 });
		test(new int[] { 1, 2 });
		test(new int[] { 1, 2, 3 });
		test(new int[] { 1, 2, 3, 4 });
		test(new int[] { 1, 2, 3, 4, 5 });
		test(new int[] { 1, 2, 3, 4, 5, 6 });
		test(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		test(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		test(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
	}

	private static void test(int[] nums) {
		TreeUtils.printTree(create(nums, 0, nums.length));
		TreeUtils.printTree(create(nums, 0, nums.length));
		System.out.println();
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		// return create(nums, 0, nums.length);
		return create(nums, nums.length);
	}

	private static int index = 0;

	/**
	 * This approach creates tree in Bottom-up manner. It keeps a track of size of
	 * nodes which are left. For this we need an index which will determine which
	 * array element should be used to create root.
	 * 
	 * Complexity: 
	 * Space: O(lg(n)): The height of tree on stack 
	 * Time: O(n): one has to iterate over all elements once.
	 * 
	 * @param nums
	 * @param size
	 * @return
	 */
	private static TreeNode create(int[] nums, int size) {
		if (size <= 0) {
			return null;
		}
		TreeNode left = create(nums, size / 2);
		TreeNode root = new TreeNode(nums[index++]);
		TreeNode right = create(nums, size - size / 2 - 1);
		root.left = left;
		root.right = right;
		return root;
	}

	/**
	 * This creates a tree in top down manner. It figures out the root element based
	 * on size of tree and then creates left and right sub-trees.
	 * 
	 * Complexity: 
	 * Space: O(lg(n)): The height of tree on stack 
	 * Time: O(n): one has to iterate over all elements once.
	 * 
	 * @param nums
	 * @param start
	 * @param size
	 * @return
	 */
	private static TreeNode create(int[] nums, int start, int size) {
		if (size <= 0) {
			return null;
		}
		int middleIndex = start + size / 2;
		TreeNode root = new TreeNode(nums[middleIndex]);
		root.left = create(nums, start, size / 2);
		root.right = create(nums, middleIndex + 1, size - (size / 2 + 1));
		return root;
	}
}
