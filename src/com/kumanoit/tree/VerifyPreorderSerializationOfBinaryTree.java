package com.kumanoit.tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * 
 * @author kumanoit 17-Feb-2020 8:28:35 PM
 */
public class VerifyPreorderSerializationOfBinaryTree {

	public static void main(String[] args) {
		test(""); // empty tree
		test("#"); // empty tree
		test("1"); // single node invalid tree
		test("1,#"); // right child is not null
		test("1,#,#"); // valid root is leaf node
		test("1,#,#,1,#,#"); // there are two disjoint trees
		test("9,#,#,1"); // invalid
		test("9,3,4,#,#,1,#,#,2,#,6,#,#"); // valid
		test("9,3,4,#,#,1,#,#,#,2,#,6,#,#");
	}

	private static void test(final String input) {
		System.out.println("Input: " + input);
		System.out.println("Solution 1: " + solution1(input));
		System.out.println("Solution 2: " + solution2(input));
		System.out.println("Solution 3: " + solution3(input));
		System.out.println("==========================================");
	}

	/**
	 * Approach: Create a stack which will hold the child count of each non-# node.
	 * All non-null nodes should have two children. Iterate over input and check if
	 * node is null or a regular node.
	 * 
	 * 1. If it is a regular node then pop element from stack. The element popped
	 * will be some node whose both children haven't been iterated. So, pop it and
	 * increment its child count and push it back to stack if children count for
	 * that popped element is not 2. If children count reaches 2 then there is no
	 * need to push it back. Push new element to stack with value 0. 0 value
	 * signifies that at present current node doesn't have any children.
	 * 
	 * 2. If iterated element is a null node then check if there is any node in
	 * stack whose child can this null node be. If it exists then do the same
	 * operation as did in last step. If there is no element in stack return false
	 * 
	 * At the end stack should be empty for preorder to be a valid binary tree.
	 * 
	 * Complexity
	 * 
	 * Time: O(n) to traverse
	 * 
	 * Space: O(n) to save splitted string, O(lg(n)) for stack.
	 * 
	 * @param preorder
	 * @return
	 */
	private static boolean solution1(final String preorder) {
		String[] words = preorder.split(",");
		if (words.length == 0) {
			return true;
		}
		Stack<Integer> stack = new Stack<>();
		if (words.length == 1) {
			return words[0].equals("#");
		}
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals("#")) {
				if (stack.isEmpty()) {
					return false;
				}
				int popped = stack.pop();
				if (popped < 1) {
					stack.push(popped + 1);
				}
			} else {
				if (!stack.isEmpty()) {
					int popped = stack.pop();
					if (popped < 1) {
						stack.push(popped + 1);
					}
				}
				stack.push(0);
			}
			if (stack.isEmpty() && (i + 1) < words.length) {
				return false;
			}
		}
		return stack.isEmpty();
	}

	/**
	 * Approach: Every non-null will create two node space. So, this count can help
	 * to find solution. Initialize node count with 1. Every time you iterate on an
	 * element, decrement this count. If iterated element is non-null then it will
	 * create two more space and hence, increment 2 to node count. At the end node
	 * count should be zero.
	 * 
	 * Complexity
	 * 
	 * Time: O(n) to traverse
	 * 
	 * Space: O(n) to save splitted string
	 * 
	 * @param preorder
	 * @return
	 */
	private static boolean solution2(final String preorder) {
		String[] words = preorder.split(",");
		int totalNodes = 1;
		for (String word : words) {
			totalNodes--;
			if (totalNodes < 0) {
				return false;
			}
			if (!word.equals("#")) {
				totalNodes += 2;
			}
		}
		return totalNodes == 0;
	}

	/**
	 * Approach: Every non-null will create two node space. So, this count can help
	 * to find solution. Initialize node count with 1. Every time you iterate on an
	 * element, decrement this count. If iterated element is non-null then it will
	 * create two more space and hence, increment 2 to node count. At the end node
	 * count should be zero. For iteration use "," as a delimiter.
	 * 
	 * Complexity
	 * 
	 * Time: O(n) to traverse
	 * 
	 * Space: O(1) to save node count
	 * 
	 * @param preorder
	 * @return
	 */
	private static boolean solution3(final String preorder) {
		int totalNodes = 1;
		for (int i = 0; i < preorder.length(); i++) {
			if (preorder.charAt(i) == ',') {
				totalNodes--;
				if (totalNodes < 0) {
					return false;
				}
				if (preorder.charAt(i - 1) != '#') {
					totalNodes += 2;
				}
			}
		}
		totalNodes = preorder.length() > 0 && preorder.charAt(preorder.length() - 1) == '#'
				? totalNodes - 1
				: totalNodes + 2;
		return totalNodes == 0;
	}
}
