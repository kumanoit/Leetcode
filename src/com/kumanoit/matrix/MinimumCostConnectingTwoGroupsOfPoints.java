package com.kumanoit.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 9/20/20 IST 3:54 PM
 */
public class MinimumCostConnectingTwoGroupsOfPoints {

    public static void main(String[] args) {
//        test(new int[][]{{1, 3, 5}, {4, 1, 1}, {1, 5, 3}});
        test(new int[][]{{2, 5, 1}, {3, 4, 7}, {8, 1, 2}, {6, 2, 4}, {3, 8, 8}});
    }

    private static void test(int[][] matrix) {
        List<List<Integer>> cost = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            cost.add(new ArrayList<>());
            for (int j = 0; j < matrix[0].length; j++) {
                cost.get(i).add(matrix[i][j]);
            }
        }
        System.out.println(connectTwoGroups(cost));
    }

    public static int connectTwoGroups(List<List<Integer>> cost) {
        int rows = cost.size();
        int cols = cost.get(0).size();
        int total = 0;

        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                queue.add(new Node(i, j, cost.get(i).get(j)));
            }
        }
        Set<Integer> rowIds = new HashSet<>();
        Set<Integer> colIds = new HashSet<>();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (!(rowIds.contains(node.rowId) && colIds.contains(node.colId))) {
                total += node.weight;
                rowIds.add(node.rowId);
                colIds.add(node.colId);
                if (rowIds.size() == rows && colIds.size() == cols) {
                    break;
                }
            }
        }
        return total;
    }
}

class Node implements Comparable<Node> {
    int rowId;
    int colId;
    int weight;

    public Node(int rowId, int colId, int weight) {
        this.rowId = rowId;
        this.colId = colId;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "(" + this.rowId + ", " + this.colId + ", " + this.weight + ")";
    }
}
