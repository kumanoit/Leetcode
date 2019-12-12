package com.kumanoit.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/submissions/
 * 
 * @author kumanoit
 * 12-Dec-2019 11:32:51 PM
 */
public class NQueensPuzzle {

	public static void main(String[] args) {
		int n = 8;
		solveNQueens(n);
	}

	public static List<List<String>> solveNQueens(int n) {
		List<List<String>> solutions = new ArrayList<List<String>>();
		getSolution(n, solutions, new int[n], 0);
		solutions.forEach(set -> {
			set.forEach(x -> System.out.println(x));
			System.out.println();
		});
		return solutions;
	}

	private static void getSolution(int boardSize, List<List<String>> solutions, int[] columns, int columnIndex) {
		if (columnIndex == boardSize) {
			List<String> sol = new ArrayList<String>();
			for (int i = 0; i < boardSize; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < boardSize; j++) {
					sb.append(j == columns[i] ? "Q" : ".");
				}
				sol.add(sb.toString());
			}
			solutions.add(sol);
			return;
		}

		for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
			columns[columnIndex] = rowIndex;
			if (isPlacedCorrectly(columns, rowIndex, columnIndex)) {
				getSolution(boardSize, solutions, columns, columnIndex + 1);
			}
		}
	}

	private static boolean isPlacedCorrectly(int[] columns, int rowIndex, int columnIndex) {
		for (int i = 0; i < columnIndex; i++) {
			int diff = Math.abs(columns[i] - rowIndex);
			if (diff == 0 || columnIndex - i == diff) {
				return false;
			}
		}
		return true;
	}
}
