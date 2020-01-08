package com.kumanoit.matrix;

import com.kumanoit.utils.MatrixUtils;

/**
 * https://leetcode.com/problems/rotate-image/
 * 
 * @author kumanoit 08-Jan-2020 11:22:50 PM
 */
public class RotateImage {

	public static void main(String[] args) {
		rotateMatrix(new Integer[][] { {} });
		rotateMatrix(new Integer[][] { { 1 } });
		rotateMatrix(new Integer[][] { { 1, 2 }, { 3, 4 } });
		rotateMatrix(new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		rotateMatrix(new Integer[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } });
	}

	private static void rotateMatrix(final Integer[][] matrix) {
		System.out.print("\nMatrix before rotation.");
		MatrixUtils.printMatrix(matrix);
		rotate(matrix);
		System.out.print("\nMatrix after rotation.");
		MatrixUtils.printMatrix(matrix);
		System.out.println();
	}

	public static void rotate(Integer[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i; j < n - i - 1; j++) {
				int start_i = i;
				int start_j = j;
				int old_i = start_i;
				int old_j = start_j;
				int oldValue = matrix[old_i][old_j];
				do {
					int next_i = old_j;
					int next_j = (n - 1) - old_i;
					int newValue = matrix[next_i][next_j];
					matrix[next_i][next_j] = oldValue;
					old_i = next_i;
					old_j = next_j;
					oldValue = newValue;
				} while (!(start_i == old_i && start_j == old_j));
			}
		}
	}
}
