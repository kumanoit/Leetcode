package com.kumanoit.array;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * 
 * @author Amit Kumar 12-Dec-2019 1:09:41 AM
 */
public class MergeSortedArrays {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 0, 0, 0, 0 };
		int[] nums2 = { 2, 5, 6 };
		merge(nums1, 3, nums2, nums2.length);
		for (int i = 0; i < nums1.length; i++) {
			System.out.print(nums1[i] + ", ");
		}
	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int i = m - 1, k = nums1.length - 1; i >= 0; i--, k--) {
			nums1[k] = nums1[i];
		}
		int i = nums1.length - m;
		int j = 0;
		int k = 0;
		while (i < nums1.length && j < n) {
			if (nums1[i] < nums2[j]) {
				nums1[k++] = nums1[i++];
			} else {
				nums1[k++] = nums2[j++];
			}
		}
		while (j < n) {
			nums1[k++] = nums2[j++];
		}
	}
}
