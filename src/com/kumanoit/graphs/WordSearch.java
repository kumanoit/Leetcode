package com.kumanoit.graphs;

import java.util.ArrayList;
import java.util.List;

import com.kumanoit.utils.Pair;

/**
 * https://leetcode.com/problems/word-search/
 * 
 * Approach: Do a DFS traversal till you find the word.
 * 
 * @author kumanoit 11-Jan-2020 9:03:29 PM
 */
public class WordSearch {

	public static void main(String[] args) {
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }, 
					{ 'S', 'F', 'C', 'S' }, 
					{ 'A', 'D', 'E', 'E' } 
					}, 
				"ABCCED"));
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }, 
					{ 'S', 'F', 'C', 'S' }, 
					{ 'A', 'D', 'E', 'E' } 
					}, 
				"SEE"));
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }, 
					{ 'S', 'F', 'C', 'S' }, 
					{ 'A', 'D', 'E', 'E' } 
					}, 
				"ABCB"));
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }, 
					{ 'S', 'F', 'C', 'S' }, 
					{ 'A', 'D', 'E', 'E' } 
					}, 
				"CFSADEESECBA"));
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }
					}, 
				"ABCE"));
		System.out.println(exist(
				new char[][] { 
					{ 'A' }, 
					{ 'S' }, 
					{ 'P' }, 
					{ 'Q' } 
					}, 
				"ASPQ"));
		System.out.println(exist(
				new char[][] { 
					{ 'A', 'B', 'C', 'E' }
					}, 
				"ECBA"));
		System.out.println(exist(
				new char[][] { 
					{ 'A' }, 
					{ 'S' }, 
					{ 'P' }, 
					{ 'Q' } 
					}, 
				"QPSA"));
		}

	public static boolean exist(char[][] board, String word) {
		List<Pair> visited = new ArrayList<Pair>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0) && found(board, word, 0, visited, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean found(char[][] board, String word, int start, List<Pair> visited, int i, int j) {
		if (start == word.length() - 1) {
			return true;
		}
		visited.add(new Pair(i, j));
		List<Pair> neighbours = getAllNeighbours(i, j, visited, board.length, board[0].length);
		for (Pair neighbour : neighbours) {
			if (board[neighbour.x][neighbour.y] == word.charAt(start + 1) && found(board, word, start + 1, visited, neighbour.x, neighbour.y)) {
				return true;
			}
		}
		visited.remove(visited.size() - 1);
		return false;
	}

	private static List<Pair> getAllNeighbours(int i, int j, List<Pair> visited, int maxRow, int maxCol) {
		int[] rows = { i - 1, i, i, i + 1 };
		int[] cols = { j, j - 1, j + 1, j };
		List<Pair> neighbours = new ArrayList<Pair>();
		for (int k = 0; k < rows.length; k++) {
			if (rows[k] >= 0 && rows[k] < maxRow && cols[k] >= 0 && cols[k] < maxCol) {
				Pair newPair = new Pair(rows[k], cols[k]);
				if (!visited.contains(newPair)) {
					neighbours.add(newPair);
				}
			}
		}
		return neighbours;
	}
}
