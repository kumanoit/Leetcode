package com.kumanoit.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 * 
 * 1443. Minimum Time to Collect All Apples in a Tree
 * 
 * Approach:
 * 1. Find out parent of each node from edges and save it in a parent array.
 * 2. Add each node which has an apple into a queue
 * 3. Pop an element from queue until it is empty:
 * 3.1 Add its parent into queue if parent is not already processed. 
 * 3.2 Mark parent as processed to avoid its re-processing.
 * 3.3 increment totalTime variable by 2. This is the total time to get to the 
 *   popped node and then return from it.
 * 
 * Complexity:
 * Time: O(n): because each node is processed once
 * Space: O(n) used in parent array, queue and isProcessed.
 * 
 * @author kumanoit May 11, 2020 3:11:27 PM
 *
 */
public class MinimumTimeToCollectAllApples {

	public static void main(String[] args) {
		test(7, new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},	 Arrays.asList(new Boolean[] {false,true,true,false,true,true,false}	));// output = 8
		test(7, new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},	 Arrays.asList(new Boolean[] {false,false,true,false,false,true,false}	));// output = 6
		test(1, new int[][]{},										 Arrays.asList(new Boolean[] {true}	));// output = 0
		test(1, new int[][]{},										 Arrays.asList(new Boolean[] {false}	));// output = 0
		test(5, new int[][]{{0,1},{1,2},{2,3},{3,4}},				 Arrays.asList(new Boolean[] {false, true, false, false, false}	));// output = 2
		test(5, new int[][]{{0,1},{1,2},{2,3},{3,4}},				 Arrays.asList(new Boolean[] {false, false, true, false, false}	));// output = 4
		test(5, new int[][]{{0,1},{1,2},{2,3},{3,4}},				 Arrays.asList(new Boolean[] {false, false, false, true, false}	));// output = 6
		test(5, new int[][]{{0,1},{1,2},{2,3},{3,4}},				 Arrays.asList(new Boolean[] {false, true, true, false, true}	));// output = 8
		test(5, new int[][]{{0,1},{1,2},{2,3},{3,4}},				 Arrays.asList(new Boolean[] {false, true, true, true, true}));// output = 8

	}

	private static void test(final int n, final int[][] edges, List<Boolean> hasApple) {
		System.out.println(minTime(n, edges, hasApple));
		
	}

	public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
		boolean[] isProcessed = new boolean[n];
		int[] parent = new int[n];
		Queue<Integer> appleQueue = new LinkedList<Integer>();
		for (int i = 0; i < edges.length; i++) {
			parent[edges[i][1]] = edges[i][0];
		}
		for (int i = 0; i < n; i++) {
			if (hasApple.get(i)) {
				appleQueue.add(i);
				isProcessed[i] = true;
			}
		}
		int totalTime = 0;
		parent[0] = -1; // root doesn't have any parent
		while (!appleQueue.isEmpty()) {
			int pop = appleQueue.remove();
			if (pop == 0) {
				continue;
			}
			totalTime += 2; // total time taken to reach and return from popped node
			int par = parent[pop];
			if (!isProcessed[par]) {
				appleQueue.add(par);
				isProcessed[par] = true;
			}
		}
		return totalTime;
	}
}
