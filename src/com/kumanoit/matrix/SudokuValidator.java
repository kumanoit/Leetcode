package com.kumanoit.matrix;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * 
 * @author kumanoit 21-Dec-2019 11:59:53 AM
 */
public class SudokuValidator {

	public static void main(String[] args) {
		// no repeated values
		System.out.println(isValidSudoku(new char[][] { 
			{ '.', '.', '4', 	'1', '.', '.',	 '6', '3', '.' }, 
			{ '.', '.', '1', 	'.', '.', '.',	 '5', '.', '.' }, 
			{ '5', '.', '.', 	'.', '.', '.',	 '.', '9', '.' },

			{ '.', '.', '.', 	'5', '6', '.',	 '.', '.', '.' }, 
			{ '4', '.', '3', 	'.', '.', '.',	 '.', '.', '1' }, 
			{ '.', '.', '.', 	'7', '.', '.',	 '.', '.', '.' },

			{ '.', '.', '.', 	'4', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' } 

			}));

		// 4th column has repeated values
		System.out.println(isValidSudoku(new char[][] { 
			{ '.', '.', '4', 	'7', '.', '.',	 '6', '3', '.' }, 
			{ '.', '.', '1', 	'.', '.', '.',	 '5', '.', '.' }, 
			{ '5', '.', '.', 	'.', '.', '.',	 '.', '9', '.' },

			{ '.', '.', '.', 	'5', '6', '.',	 '.', '.', '.' }, 
			{ '4', '.', '3', 	'.', '.', '.',	 '.', '.', '1' }, 
			{ '.', '.', '.', 	'7', '.', '.',	 '.', '.', '.' },

			{ '.', '.', '.', 	'4', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' } 

			}));

		// last row has repeated values
		System.out.println(isValidSudoku(new char[][] { 
			{ '.', '.', '4', 	'.', '.', '.',	 '6', '3', '.' }, 
			{ '.', '.', '1', 	'.', '.', '.',	 '5', '.', '.' }, 
			{ '5', '.', '.', 	'.', '.', '.',	 '.', '9', '.' },

			{ '.', '.', '.', 	'5', '6', '.',	 '.', '.', '.' }, 
			{ '4', '.', '3', 	'.', '.', '.',	 '.', '.', '1' }, 
			{ '.', '.', '.', 	'7', '.', '.',	 '.', '.', '.' },

			{ '.', '.', '.', 	'4', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' }, 
			{ '1', '.', '.', 	'.', '.', '.',	 '.', '.', '1' }

			}));

		// middle square has repeated values
		System.out.println(isValidSudoku(new char[][] { 
			{ '.', '.', '4', 	'.', '.', '.',	 '6', '3', '.' }, 
			{ '.', '.', '1', 	'.', '.', '.',	 '5', '.', '.' }, 
			{ '5', '.', '.', 	'.', '.', '.',	 '.', '9', '.' },

			{ '.', '.', '.', 	'5', '6', '.',	 '.', '.', '.' }, 
			{ '4', '.', '3', 	'.', '.', '6',	 '.', '.', '1' }, 
			{ '.', '.', '.', 	'7', '.', '.',	 '.', '.', '.' },

			{ '.', '.', '.', 	'4', '.', '.',	 '.', '.', '.' }, 
			{ '.', '.', '.', 	'.', '.', '.',	 '.', '.', '.' }, 
			{ '1', '.', '.', 	'.', '.', '.',	 '.', '.', '1' }

			}));

	}

	public static boolean isValidSudoku(char[][] board) {
		// checking unique values for row
		for (int i = 0; i < 9; i++) {
			boolean[] visited = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int index = board[i][j] - '1';
				if (visited[index]) {
					return false;
				}
				visited[index] = true;
			}
		}

		// checking unique values for columns
		for (int i = 0; i < 9; i++) {
			boolean[] visited = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[j][i] == '.') {
					continue;
				}
				int index = board[j][i] - '1';
				if (visited[index]) {
					return false;
				}
				visited[index] = true;
			}
		}

		// checking unique values in all squares
		for (int m = 0; m < 9; m += 3) {
			int row_min = m;
			int row_max = m + 3;
			for (int n = 0; n < 9; n += 3) {
				int col_min = n;
				int col_max = n + 3;
				boolean[] blockVisited = new boolean[9];
				for (int i = row_min; i < row_max; i++) {
					for (int j = col_min; j < col_max; j++) {
						if (board[i][j] != '.') {
							int index = board[i][j] - '1';
							if (blockVisited[index]) {
								return false;
							}
							blockVisited[index] = true;
						}
					}
				}
			}
		}
		return true;
	}
}
