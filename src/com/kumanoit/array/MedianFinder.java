package com.kumanoit.array;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/submissions/
 * 
 * @author kumanoit 17-Dec-2019 1:51:10 AM
 * 
 *         Algorithm: Use two heaps: 
 *         1) MaxHeap for all numbers lesser than median 
 *         2) Min heap for all numbers greater than median
 *         Whenever new number is coming keep filling both heap in such a manner
 *         that size of both heaps should not differ by more than 1.
 *         Median will be mean of root value when both heaps have same size
 *         else: it will be the root of larger heap
 */
public class MedianFinder {

	static Queue<Integer> minHeap;
	static Queue<Integer> maxHeap;

	/** initialize your data structure here. */
	public MedianFinder() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}

	public void addNum(int num) {

		if (maxHeap.size() <= minHeap.size()) {
			// insert on left side
			if (maxHeap.size() == 0 || num <= maxHeap.peek() || num <= minHeap.peek()) {
				maxHeap.add(num);
			} else {
				int removed = minHeap.remove();
				maxHeap.add(removed);
				minHeap.add(num);
			}
		} else {
			if (minHeap.size() == 0) {
				if (num >= maxHeap.peek()) {
					minHeap.add(num);
				} else {
					int removedMax = maxHeap.remove();
					maxHeap.add(num);
					minHeap.add(removedMax);
				}
			} else if (num >= minHeap.peek() || num >= maxHeap.peek()) {
				minHeap.add(num);
			} else {
				int removedMax = maxHeap.remove();
				maxHeap.add(num);
				minHeap.add(removedMax);
			}
		}
	}

	public double findMedian() {
		if (minHeap.size() == maxHeap.size()) {
			return ((double) minHeap.peek() + maxHeap.peek()) / 2;
		}
		if (minHeap.size() > maxHeap.size()) {
			return minHeap.peek();
		}
		return maxHeap.peek();
	}
}
