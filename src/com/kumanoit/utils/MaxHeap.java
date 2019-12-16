package com.kumanoit.utils;

public class MaxHeap {

	private int MAX_SIZE = 20;

	int[] data;
	int size;

	public MaxHeap() {
		this.data = new int[MAX_SIZE];
		this.size = 0;
	}

	public void insert(int number) {
		if (size == MAX_SIZE) {
			MAX_SIZE = 2 * MAX_SIZE;
		}
		int[] newArray = new int[MAX_SIZE];
		System.arraycopy(data, 0, newArray, 0, data.length);
		this.data = newArray;
		this.data[size++] = number;
		maxHeapifyBottomUp(getParent(size - 1));
	}

	private int getParent(int index) {
		return (index - 1) / 2;
	}

	private int getLeftIndex(int index) {
		return 2 * index + 1;
	}

	private int getRightIndex(int index) {
		return 2 * index + 2;
	}

	private void maxHeapifyBottomUp(int index) {

		int maxIndex = index;
		int leftIndex = getLeftIndex(index);
		if (leftIndex < size) {
			if (data[leftIndex] > data[maxIndex]) {
				maxIndex = leftIndex;
			}
		}
		int rightIndex = getRightIndex(index);
		if (rightIndex < size) {
			if (data[rightIndex] > data[maxIndex]) {
				maxIndex = rightIndex;
			}
		}
		if (maxIndex != index) {
			swap(index, maxIndex);
			maxHeapifyBottomUp(getParent(index));
		}
	}

	private void swap(int index1, int index2) {
		data[index1] = data[index1] ^ data[index2];
		data[index2] = data[index1] ^ data[index2];
		data[index1] = data[index1] ^ data[index2];
	}

	public void printHeap() {
		System.out.println();
		for (int i = 0; i < this.size; i++) {
			System.out.print(data[i] + ", ");
		}
	}

	private void maxHeapifyTopDown(int index) {

		int maxIndex = index;
		int leftIndex = getLeftIndex(index);
		if (leftIndex < size) {
			if (data[leftIndex] > data[maxIndex]) {
				maxIndex = leftIndex;
			}
		}
		int rightIndex = getRightIndex(index);
		if (rightIndex < size) {
			if (data[rightIndex] > data[maxIndex]) {
				maxIndex = rightIndex;
			}
		}
		if (maxIndex != index) {
			swap(index, maxIndex);
			maxHeapifyTopDown(maxIndex);
		}
	}

	public int remove() {
		if (size == 0) {
			throw new NullPointerException("Underflow");
		}
		int removed = data[0];
		size--;
		data[0] = data[size];
		maxHeapifyTopDown(0);
		return removed;
	}

	public int getMaxElement() {
		return size > 0 ? data[0] : -1;
	}

	public int size() {
		return this.size;
	}

}
