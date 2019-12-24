package com.kumanoit.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree
 * 
 * @author kumanoit
 * 25-Dec-2019 4:29:08 AM
 */
public class AverageOfLevelsInBinaryTree {

	public static void main(String[] args) {
		test(new Integer[] { 1, null, 3, 2, 4, null, 5, 6 });
	}

	private static void test(final Integer[] nums) {
		TreeNode root = TreeUtils.createTree(nums);
		averageOfLevels(root).forEach(item -> System.out.println(item + ", "));
		averageOfLevels2(root).forEach(item -> System.out.println(item + ", "));
	}

	/**
	 * calculated average by creating a queue and doing level order traversal
	 * 
	 * @param root
	 * @return
	 */
	public static List<Double> averageOfLevels(TreeNode root) {
		List<List<Integer>> levelOrderNodes = new ArrayList<List<Integer>>();
		List<Double> answer = new ArrayList<Double>();
		if (root == null) {
			return answer;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		queue.add(null);
		List<Integer> localSolution = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode temp = queue.remove();
			if (temp == null) {
				levelOrderNodes.add(localSolution);
				Double sum = 0.0;
				for (int i = 0; i < localSolution.size(); i++) {
					sum += localSolution.get(i);
				}
				answer.add(sum / localSolution.size());
				if (queue.isEmpty()) {
					break;
				}
				localSolution = new ArrayList<Integer>();
				queue.add(null);
			} else {
				localSolution.add(temp.val);
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}
			}
		}
		return answer;
	}

	/**
	 * Approach: Created a hashmap with key as height of tree and value as sum of
	 * all nodes at that height.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Double> averageOfLevels2(TreeNode root) {
		List<Double> solution = new ArrayList<Double>();
		Map<Integer, Pair> sumMap = new HashMap<Integer, Pair>();
		traverse(root, sumMap, 0);
		for (Integer height : sumMap.keySet()) {
			solution.add(sumMap.get(height).getAverage());
		}
		return solution;
	}

	private static void traverse(TreeNode root, Map<Integer, Pair> sumMap, int height) {
		if (root == null) {
			return;
		}
		if (!sumMap.keySet().contains(height)) {
			sumMap.put(height, new Pair(0D, 0D));
		}
		Pair pair = sumMap.get(height);
		pair.count++;
		pair.total += root.val;
		traverse(root.left, sumMap, height + 1);
		traverse(root.right, sumMap, height + 1);
	}
}

class Pair {
	Double count;
	Double total;

	public Pair(Double count, Double total) {
		this.count = count;
		this.total = total;
	}

	public double getAverage() {
		return (double) total / count;
	}
}
