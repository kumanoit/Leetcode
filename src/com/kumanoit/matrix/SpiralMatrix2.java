package com.kumanoit.matrix;

import com.kumanoit.utils.MatrixUtils;

public class SpiralMatrix2 {

	public static void main(String[] args) {
		test(0);
		test(1);
		test(2);
		test(3);
		test(4);
		test(5);
	}

	private static void test(int n) {
		System.out.print("\n\nGenerated matrix:");
		MatrixUtils.printMatrix(generateMatrix(n));
	}

	public static Integer[][] generateMatrix(int n) {
		Integer[][] solution = new Integer[n][n];
		int count = 1;
		int maxCount = n * n + 1;
		int minRow = 0;
		int minCol = -1;
		int maxRow = n;
		int maxCol = n;
		int i = 0;
		int j = 0;
		while (count < maxCount) {
			while (j < maxCol && count < maxCount) {
				solution[i][j++] = count++;
			}
			i++;
			j--;
			maxCol--;
			while (i < maxRow && count < maxCount) {
				solution[i++][j] = count++;
			}
			i--;
			j--;
			maxRow--;
			while (j > minCol && count < maxCount) {
				solution[i][j--] = count++;
			}
			i--;
			j++;
			minCol++;
			while (i > minRow && count < maxCount) {
				solution[i--][j] = count++;
			}
			i++;
			j++;
			minRow++;
		}
		return solution;
	}
}
