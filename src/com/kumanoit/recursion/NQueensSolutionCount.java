package com.kumanoit.recursion;

/**
 * https://leetcode.com/problems/n-queens-ii/submissions/
 * @author kumanoit
 * 13-Dec-2019 12:19:09 AM
 */
public class NQueensSolutionCount {

	public static void main(String[] args) {
		System.out.println(totalNQueens(0));
		System.out.println(totalNQueens(1));
		System.out.println(totalNQueens(2));
		System.out.println(totalNQueens(3));
		System.out.println(totalNQueens(4));
		System.out.println(totalNQueens(8));
	}

	public static int totalNQueens(int n) {
		int[] solution = new int[1];
		count(n, new int[n], 0, solution);
		return solution[0];
	}

	public static void count(int boardSize, int[] columns, int columnIndex, int[] count) {
		if (columnIndex == boardSize) {
			count[0]++;
			return;
		}
		for (int i = 0; i < boardSize; i++) {
			columns[columnIndex] = i;
			if (isPlacedCorrectly(columns, columnIndex, i)) {
				count(boardSize, columns, columnIndex + 1, count);
			}
		}
	}

	private static boolean isPlacedCorrectly(int[] columns, int columnIndex, int index) {
		for (int i = 0; i < columnIndex; i++) {
			int diff = Math.abs(columns[i] - columns[columnIndex]);
			if (diff == 0 || columnIndex - i == diff) {
				return false;
			}
		}
		return true;
	}
}
