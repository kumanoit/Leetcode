package com.kumanoit.graphs;

import com.kumanoit.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search/
 * <p>
 * Approach: Do a DFS traversal till you find the word.
 *
 * Complexity:
 * Shorter method
 * There are mn elements present in word matrix. There are 4 recursive call inside recursive function. The recursive
 * call will go to length of given word. Let the length be L so total 4^L calls will be made for each element in
 * words matrix. Total characters inside word matrix = mn
 * Total complexity = O(mn*4^L)
 *
 * @author kumanoit 11-Jan-2020 9:03:29 PM
 */
public class WordSearch {

    public static void main(String[] args) {
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "ABCCED");
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "SEE");
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "ABCB");
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "CFSADEESECBA");
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'}
                },
                "ABCE");
        test(
                new char[][]{
                        {'A'},
                        {'S'},
                        {'P'},
                        {'Q'}
                },
                "ASPQ");
        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'}
                },
                "ECBA");
        test(
                new char[][]{
                        {'A'},
                        {'S'},
                        {'P'},
                        {'Q'}
                },
                "QPSA");

        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "A");

        test(
                new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                },
                "Z");

        test(
                new char[][]{
                        {'B'}
                 }, "B");

        test(
                new char[][]{
                        {'A', 'B', 'A', 'A'},
                        {'S', 'A', 'A', 'A'},
                        {'A', 'A', 'A', 'A'}
                },
                "AAAAAAAAAA");

        test(
                new char[][]{
                        {'A', 'A', 'A', 'A'},
                        {'A', 'S', 'S', 'A'},
                        {'A', 'A', 'A', 'A'}
                },
                "AAAAAAAAAA");

        test(
                new char[][]{
                        {'A', 'A', 'A', 'A'},
                        {'A', 'S', 'S', 'S'},
                        {'A', 'A', 'A', 'A'}
                },
                "AAAAAAAAAA");

        test(
                new char[][]{
                        {'U', 'M', 'A', 'R'},
                        {'K', 'A', 'H', 'C'},
                        {'T', 'I', 'M', 'A'}
                },
                "AMITKUMAR");

        test(
                new char[][]{
                        {'U', 'M', 'A', 'R'},
                        {'K', 'A', 'H', 'C'},
                        {'T', 'I', 'M', 'A'}
                },
                "AMITKUMARCHA");

        test(
                new char[][]{
                        {'U', 'M', 'A', 'R'},
                        {'K', 'A', 'H', 'C'},
                        {'T', 'I', 'M', 'A'}
                },
                "AMITKUMARCHA");

        test(
                new char[][]{
                        {'A'},
                        {'B'},
                        {'C'}
                },
                "ABC");

        test(
                new char[][]{
                        {'A'},
                        {'B'},
                        {'C'}
                },
                "CBA");

        test(
                new char[][]{
                        {'A', 'B', 'C'}
                },
                "ABC");

        test(
                new char[][]{
                        {'A', 'B', 'C'}
                },
                "CBA");
    }

    /****************************************************************************************************************
     *****************************************************************************************************************/

    private static void test(char[][] board, String word) {
        System.out.println(word);
        System.out.println("Solution from longer code: " + exist(board, word));
        System.out.println("Solution from shorter code: " + existShortCode(board, word));
        System.out.println();
    }

    /****************************************************************************************************************
     *****************************************************************************************************************/

    public static boolean existShortCode(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] board, String word, int index, int row, int col) {
        if (word.length() == index) {
            return true;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
            return false;
        }
        if (word.charAt(index) != board[row][col]) {
            return false;
        }
        board[row][col] ^= 256; // for changing current value to mark it as visited
        boolean isFound =
                search(board, word, index + 1, row - 1, col) ||
                        search(board, word, index + 1, row, col - 1) ||
                        search(board, word, index + 1, row, col + 1) ||
                        search(board, word, index + 1, row + 1, col);

        board[row][col] ^= 256; // reverting change
        return isFound;
    }

    /****************************************************************************************************************
     *****************************************************************************************************************/

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
        int[] rows = {i - 1, i, i, i + 1};
        int[] cols = {j, j - 1, j + 1, j};
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
