package com.kumanoit.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * 994. Rotting Oranges
 * <p>
 * Approach:
 * Considering this as a graph where every cell is a node. We can do a BFS traversal starting from all nodes which have
 * a rotten oranges and then moving outward from here keep on rotting any neighbouring fresh orange one at a time.
 * 1. Initially we can put all rotten oranges in a queue (say queue-1).
 * 2. Now, pop one element from this queue and look for neighbouring fresh oranges. If there is any fresh orange then
 * mark it as rotten by initializing it with value of 2.
 * 3. Now, save this node for next round in another queue (say queue-2)
 * 4. Do this for all elements in queue-1
 * 5. Now, queue-2 will have all oranges which were rotten in first minute. So, track this in a variable as timeTaken
 * and increment it by 1
 * 6. Make it as queue-1 and repeat steps 2 to 5 until queue-2 is empty
 * 7. timeTaken will give the solution
 * <p>
 * Corner cases:
 * 1. what will happen if there are no oranges in grid. Iterate over grid and check if there are no 1 or 2 values in
 * grid. Return 0 in this case.
 * 2. what will happen if there are no rotten orange OR there is an isolated fresh orange which is not adjacent to any
 * orange. In this case return -1
 * <p>
 * Complexity
 * 1. Time: O(rows * cols) since each node was visited once initially and then while finding out for adjacent nodes, each
 * node in worst case will be visited by all 4 of its adjacent nodes (top, bottom, left, right). So total time complexity
 * will be 4*rows*cols
 * 2. Space: O(rows * cols) for putting each element in queue at least once.
 *
 * @author akumar on 8/9/20 IST 2:02 PM
 */
public class RottenOranges {
    public static void main(String[] args) {
        test(new int[][]{{0}});
        test(new int[][]{{0, 0, 0, 0, 0, 0, 0}});
        test(new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
        test(new int[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}});
        test(new int[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}});
        test(new int[][]{{2, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}});
        test(new int[][]{{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}});
        test(new int[][]{{2, 1, 1, 1, 1, 1, 1}});
        test(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
        test(new int[][]{{1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}});
        test(new int[][]{{2, 1, 1, 1, 1, 0}});
        test(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        test(new int[][]{{2, 1, 1, 1, 1, 0, 1}});
        test(new int[][]{{2}, {1}, {1}, {1}, {1}, {1}, {1}});
        test(new int[][]{{2}, {1}, {1}, {1}, {1}, {1}, {0}});
        test(new int[][]{{2}, {1}, {1}, {1}, {1}, {0}, {1}});
    }

    private static void test(int[][] grid) {
        System.out.println(orangesRotting(grid));
    }

    private static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        int oranges = 0;
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                    oranges++;
                } else if (grid[i][j] == 1) {
                    oranges++;
                }
            }
        }
        if (oranges == 0) {
            // when there is no orange present
            return 0;
        }
        if (queue.isEmpty()) {
            // when there are no rotten oranges
            return -1;
        }
        queue.add(null);
        int totalDays = 0;
        while (!queue.isEmpty()) {
            Pair node = queue.remove();
            if (node == null) {
                if (queue.isEmpty()) {
                    break;
                }
                totalDays++;
                queue.add(null);
            } else {
                oranges--;
                List<Pair> adjacentFreshOranges = getAdjacentFreshOranges(node, rows, cols, grid);
                for (Pair pair : adjacentFreshOranges) {
                    grid[pair.getKey()][pair.getValue()] = 2; // rotting this apple
                    queue.add(pair);
                }
            }
        }
        return oranges == 0 ? totalDays : -1;
    }

    private static List<Pair> getAdjacentFreshOranges(Pair node, int totalRows, int totalCols, int[][] grid) {
        int i = node.getKey();
        int j = node.getValue();
        int[] rows = {i - 1, i, i, i + 1};
        int[] cols = {j, j - 1, j + 1, j};
        List<Pair> freshOrangesCoordinates = new ArrayList<>();
        for (int k = 0; k < rows.length; k++) {
            if (rows[k] >= 0 && rows[k] < totalRows && cols[k] >= 0 && cols[k] < totalCols && grid[rows[k]][cols[k]] == 1) {
                freshOrangesCoordinates.add(new Pair(rows[k], cols[k]));
            }
        }
        return freshOrangesCoordinates;
    }
}

class Pair {
    int key;
    int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

}
