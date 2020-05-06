package com.kumanoit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/submissions/
 * 
 * Algorithm: At any time the maximum profit can be given by only those jobs
 * which are finished until this time. So, first sort all jobs by its endtime.
 * Now, either a job will be there in solution set or not. Use this logic to go
 * ahead. But the problem is this logic will have exponential complexity because
 * total number of such set will be 2^n.
 * 
 * Do a recursive solution where either keep an element or ignore it. Keep a
 * memo for the same.
 * 
 * Time complexity: O(n^2)
 * 
 * Space complexity: O(n) for creating an object which contains all elements in
 * object form.
 * 
 * @author kumanoit May 7, 2020 12:40:08 AM
 *
 */
public class MaximumProfitInJobScheduling {

	private static Map<Integer, Integer> memo;

	public static void main(String[] args) {
		test(new int[] { 1, 2, 3, 3 }, new int[] { 3, 4, 5, 6 }, new int[] { 50, 10, 40, 70 });
		test(new int[] { 1, 2, 3, 4, 6 }, new int[] { 3, 5, 10, 6, 9 }, new int[] { 20, 20, 100, 70, 60 });
		test(new int[] { 1, 1, 1 }, new int[] { 2, 3, 4 }, new int[] { 5, 6, 4 });
	}

	private static void test(int[] startTime, int[] endTime, int[] profit) {
		int size = startTime.length;
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < size; i++) {
			jobs.add(new Job(startTime[i], endTime[i], profit[i]));
		}
		Collections.sort(jobs);
//		jobs.forEach(item -> System.out.print(item.toString() + ", "));
		memo = new HashMap<Integer, Integer>();
		System.out.println("\nRecursive: " + getSolutionRecursiveWithMemoization(jobs, size));
		System.out.println("Iterative: " + getSolutionIterative(jobs));
//		memo.forEach((k, v) -> System.out.println("(" + k + ":" + v + "), "));
	}

	private static int getSolutionRecursiveWithMemoization(List<Job> jobs, int size) {
		if (size <= 0) {
			return 0;
		}
		if (memo.containsKey(size)) {
			return memo.get(size);
		}
		int k = size - 1;
		while (k > 0 && jobs.get(k - 1).endTime > jobs.get(size - 1).startTime) {
			k--;
		}
		int inclusive = jobs.get(size - 1).profit + getSolutionRecursiveWithMemoization(jobs, k);
		int exclusive = getSolutionRecursiveWithMemoization(jobs, size - 1);
		memo.put(size, Math.max(inclusive, exclusive));
		return memo.get(size);
	}

	private static int getSolutionIterative(List<Job> jobs) {
		Collections.sort(jobs);
		int[] maxProfit = new int[jobs.size()];
		maxProfit[0] = jobs.get(0).profit;
		for (int i = 1; i < jobs.size(); i++) {
			int inclusive = jobs.get(i).profit;
			int lastInclusivableJobIndex = i - 1;
			while (lastInclusivableJobIndex >= 0
			        && jobs.get(lastInclusivableJobIndex).endTime > jobs.get(i).startTime) {
				lastInclusivableJobIndex--;
			}
			if (lastInclusivableJobIndex >= 0) {
				inclusive += maxProfit[lastInclusivableJobIndex];
			}
			int exclusive = maxProfit[i - 1];
			maxProfit[i] = Math.max(inclusive, exclusive);
		}
		return maxProfit[jobs.size() - 1];
	}

}

class Job implements Comparable<Job> {
	int startTime;
	int endTime;
	int profit;

	Job(int startTime, int endTime, int profit) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}

	@Override
	public int compareTo(Job job) {
		int endTime = this.endTime - job.endTime;
		return endTime == 0 ? this.startTime - job.startTime : endTime;
	}

	@Override
	public String toString() {
		return ("(" + this.startTime + ", " + this.endTime + ", " + this.profit + ")");
	}
}