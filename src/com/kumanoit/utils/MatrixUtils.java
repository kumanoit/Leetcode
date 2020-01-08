package com.kumanoit.utils;

public class MatrixUtils {

	/**
	 * Prints matrix
	 * 
	 * @param matrix
	 */
	public static <T> void printMatrix(T[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println();
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + ", ");
			}
		}
	}
}
