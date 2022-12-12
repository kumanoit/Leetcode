package com.kumanoit.leetcodecontest;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/20/21 IST 9:10 PM
 */
public class Solution {
    public int maxScore(int[] nums) {
        Queue<Node> queue = new PriorityQueue<Node>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.add(new Node(i, j, getGcd(nums[i], nums[j])));
            }
        }

        boolean[] isUsed = new boolean[n];
        int result = 0;
        int i = n / 2;
        while (i > 0) {
            Node node = queue.remove();
            if (!(isUsed[node.i] || isUsed[node.j])) {
                result += i * node.gcd;
                i--;
            }
        }
        return result;
    }

    private int getGcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        if (y > x) {
            return getGcd(y, x);
        }
        return getGcd(y, x % y);
    }
}

class Node implements Comparator<Node> {
    int i;
    int j;
    int gcd;

    public Node(int i, int j, int gcd) {
        this.i = i;
        this.j = j;
        this.gcd = gcd;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return node2.gcd - node1.gcd;
    }
}
