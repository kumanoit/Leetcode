package com.kumanoit.matrix;

import java.util.ArrayList;
import java.util.List;

import com.kumanoit.utils.MatrixUtils;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * 
 * @author kumanoit 08-Jan-2020 11:46:16 PM
 */
public class SpiralMatrix {

	public static void main(String[] args) {
		test(new Integer[][] { { 1, 2 }, { 3, 4 } });
		test(new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		test(new Integer[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } });
	}

	private static void test(final Integer[][] matrix) {
		System.out.print("\n\nGiven matrix");
		MatrixUtils.printMatrix(matrix);
		System.out.println("\nSolution: ");
		spiralOrder(matrix).forEach(item -> {
			System.out.print(item + ", ");
		});
	}

	public static List<Integer> spiralOrder(Integer[][] matrix) {
		List<Integer> solution = new ArrayList<Integer>();
		int rows = matrix.length;
		if (rows == 0) {
			return solution;
		}
		int cols = matrix[0].length;
		int count = rows * cols;
		int minRow = 0;
		int minCol = -1;
		int maxRow = rows;
		int maxCol = cols;
		int i = 0;
		int j = 0;
		while (count > 0) {
			while (j < maxCol && count > 0) {
				solution.add(matrix[i][j]);
				j++;
				count--;
			}
			i++;
			j--;
			maxCol--;
			while (i < maxRow && count > 0) {
				solution.add(matrix[i][j]);
				i++;
				count--;
			}
			i--;
			j--;
			maxRow--;
			while (j > minCol && count > 0) {
				solution.add(matrix[i][j]);
				j--;
				count--;
			}
			i--;
			j++;
			minCol++;
			while (i > minRow && count > 0) {
				solution.add(matrix[i][j]);
				i--;
				count--;
			}
			i++;
			j++;
			minRow++;
		}
		return solution;
	}
}
