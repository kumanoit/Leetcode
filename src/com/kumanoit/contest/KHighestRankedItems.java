package com.kumanoit.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/22/22 IST 8:14 PM
 */
public class KHighestRankedItems {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 0, 1}, {1, 3, 0, 1}, {0, 2, 5, 1}}, new int[]{2, 5}, new int[]{0, 0}, 3);
        test(new int[][]{{1, 2, 0, 1}, {1, 3, 3, 1}, {0, 2, 5, 1}}, new int[]{2, 3}, new int[]{2, 3}, 2);
        test(new int[][]{{1, 1, 1}, {0, 0, 1}, {2, 3, 4}}, new int[]{2, 3}, new int[]{0, 0}, 3);
        test(new int[][]{{0, 2, 0}}, new int[]{2, 2}, new int[]{0, 1}, 1);
    }

    private static void test(int[][] grid, int[] pricing, int[] start, int k) {
        highestRankedKItems(grid, pricing, start, k).forEach(item -> {
            item.forEach(x -> System.out.print(x + ", "));
            System.out.println();
        });
        System.out.println();
    }

    public static List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        Queue<int[]> queue = new LinkedList<>();
        List<Item> items = new ArrayList<>();
        queue.add(start);
        int distance = 0;
        int[] rowIds = {-1, 1, 0, 0};
        int[] colIds = {0, 0, -1, 1};
        if (grid[start[0]][start[1]] >= pricing[0] && grid[start[0]][start[1]] <= pricing[1]) {
            items.add(new Item(0, grid[start[0]][start[1]], start[0], start[1]));
        }
        grid[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                int[] popped = queue.remove();
                for (int j = 0; j < rowIds.length; j++) {
                    int rowId = popped[0] + rowIds[j];
                    int colId = popped[1] + colIds[j];

                    if (rowId < 0 || rowId == grid.length || colId < 0 || colId == grid[0].length || grid[rowId][colId] == 0) {
                        continue;
                    }
                    if (grid[rowId][colId] >= pricing[0] && grid[rowId][colId] <= pricing[1]) {
                        items.add(new Item(distance, grid[rowId][colId], rowId, colId));
                    }
                    grid[rowId][colId] = 0;
                    queue.add(new int[]{rowId, colId});
                }
            }
            if (items.size() > k) {
                break;
            }
        }
        List<List<Integer>> solution = new ArrayList<>();

        Collections.sort(items);

        for (int i = 0; i < k && i < items.size(); i++) {
            Item item = items.get(i);
            solution.add(Arrays.asList(item.rowId, item.colId));
        }
        return solution;
    }
}

class Item implements Comparable<Item> {
    int distance;
    int price;
    int rowId;
    int colId;

    public Item(final int distance, final int price, final int rowId, final int colId) {
        this.distance = distance;
        this.price = price;
        this.rowId = rowId;
        this.colId = colId;
    }

    @Override
    public int compareTo(final Item other) {
        if (this.distance < other.distance) {
            return -1;
        } else if (this.distance > other.distance) {
            return 1;
        }
        if (this.price < other.price) {
            return -1;
        } else if (this.price > other.price) {
            return 1;
        }
        if (this.rowId < other.rowId) {
            return -1;
        } else if (this.rowId > other.rowId) {
            return 1;
        }
        if (this.colId < other.colId) {
            return -1;
        } else if (this.colId > other.colId) {
            return 1;
        }
        return 1;
    }

    public void print() {
        System.out.println(distance + " " + price + " " + rowId + " " + colId);
    }
}
