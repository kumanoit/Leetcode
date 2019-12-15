package com.kumanoit.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-paths-iii/
 * 
 * @author kumanoit 15-Dec-2019 3:56:52 PM
 * 
 *         Algorithm: Look the matrix as a graph and do a DFS. Whenever you
 *         reach to the destination node and find out that all empty nodes has
 *         been visited then return 1 ; otherwise return 0
 * 
 */
public class PossibleUniquePathsWithEachNodeTraversedOnce {

	private static final int OBSTACLE = -1;
	private static final int EMPTY = 0;
	private static final int START = 1;
	private static final int END = 2;

	public static void main(String[] args) {
		test(new int[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 2, -1 } }); // 2
		test(new int[][] { { 1, 0, 0, 0 }, { 0, -1, -1, 0 }, { 0, 0, 0, 2 } }); // 0
		test(new int[][] { { 1, 2 } }); // 1
		test(new int[][] { { 1 }, { 2 } }); // 1
		test(new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 2, 0, 0 } }); // 1
		test(new int[][] { { 1, 0, 0, 0 }, { 0, 2, 0, 0 }, { 0, 0, 0, -1 } }); // 2

	}

	public static void test(int[][] matrix) {
		System.out.println("Total paths found above = " + uniquePathsWithObstacles(matrix));
		System.out.println();
	}

	public static int uniquePathsWithObstacles(int[][] matrix) {
		int rows = matrix.length;
		if (rows == 0) {
			return 0;
		}
		int cols = matrix[0].length;
		int[] totalEmptyCells = { 0 };
		Pair start = null;
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == START) {
					start = new Pair(i, j);
				} else if (matrix[i][j] == EMPTY) {
					totalEmptyCells[0]++;
				}
			}
		}
		List<Pair> path = new ArrayList<Pair>();
		path.add(start);
		int totalPaths = getUniquePathCount(matrix, start, visited, totalEmptyCells, path);
		return totalPaths;
	}

	private static int getUniquePathCount(int[][] obstacleGrid, Pair cell, boolean[][] visited, int[] totalEmptyCells, List<Pair> path) {
		int row = cell.x;
		int col = cell.y;
		if (obstacleGrid[row][col] == END) {
			if (totalEmptyCells[0] == 0) {
				path.forEach(item -> System.out.print(item));
				System.out.println();
				return 1;
			} else {
				return 0;
			}
		}
		if (visited[row][col] || obstacleGrid[row][col] == OBSTACLE) {
			return 0;
		}
		visited[row][col] = true;
		boolean isEmptyCell = obstacleGrid[row][col] == EMPTY;
		totalEmptyCells[0] -= isEmptyCell ? 1 : 0;
		List<Pair> neighbours = getNeighbours(obstacleGrid, cell, visited);
		int totalPaths = 0;
		for (Pair neighbour : neighbours) {
			path.add(neighbour);
			totalPaths += getUniquePathCount(obstacleGrid, neighbour, visited, totalEmptyCells, path);
			path.remove(neighbour);
		}
		visited[row][col] = false;
		totalEmptyCells[0] += isEmptyCell ? 1 : 0;
		return totalPaths;
	}

	private static List<Pair> getNeighbours(int[][] obstacleGrid, Pair cell, boolean[][] visited) {
		int x = cell.x;
		int y = cell.y;
		List<Pair> neighbours = new ArrayList<Pair>();
		int[] rows = { x - 1, x, x, x + 1 };
		int[] cols = { y, y - 1, y + 1, y };
		for (int i = 0; i < rows.length; i++) {
			if (isValidCell(rows[i], cols[i], obstacleGrid) && !visited[rows[i]][cols[i]]) {
				neighbours.add(new Pair(rows[i], cols[i]));
			}
		}
		return neighbours;
	}

	private static boolean isValidCell(int row, int col, int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		return (row >= 0 && col >= 0 && row < rows && col < cols);
	}

}

class Pair {
	int x;
	int y;

	Pair(int row, int col) {
		this.x = row;
		this.y = col;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
