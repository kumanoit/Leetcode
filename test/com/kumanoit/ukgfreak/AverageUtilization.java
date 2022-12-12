package com.kumanoit.ukgfreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AverageUtilization {
    public static void main(String[] args) {
//        System.out.println(getMinScore(6, Arrays.asList(1, 2, 2, 3, 4, 5), Arrays.asList(2, 4, 5, 5, 5, 6)));
//        System.out.println(getMinScore(6, Arrays.asList(1, 1, 2, 2, 2, 3, 4), Arrays.asList(2, 3, 3, 4, 4, 5)));
        mostCompetitive(new int[]{3, 5, 2, 6}, 4);
        mostCompetitive(new int[]{3, 5, 2, 6}, 2);
        mostCompetitive(new int[]{2,4,3,3,5,4,9,6}, 4);
    }

    public static int[] mostCompetitive(int[] nums, int k) {
        int end = 0;
        int[] solution = new int[k];
        solution[0] = nums[end];
        for (int i = 1; i < nums.length; i++) {
            int itemsLeftInArray = nums.length - i;
            while (end >= 0 && end + 1 + itemsLeftInArray > k && solution[end] > nums[i]) {
                end--;
            }
            if (end + 1 < solution.length) {
                solution[++end] = nums[i];
            }
        }
        for (int x : solution) {
            System.out.print(x + ", ");
        }
        System.out.println();
        return solution;
    }

    private static int getMinScore(int productNodes, List<Integer> productsFrom, List<Integer> productTo) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= productNodes; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < productTo.size(); i++) {
            int from = productsFrom.get(i);
            int to = productTo.get(i);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        graph.forEach((k, v) -> {
            System.out.print(k + ": ");
            v.forEach(item -> System.out.print(item + ", "));
            System.out.println();
        });
        int minScore = Integer.MAX_VALUE;
        for (int nodeA = 1; nodeA <= productNodes; nodeA++) {
            List<Integer> nodes = new ArrayList<>(graph.get(nodeA));
            if (nodes.size() >= 2) {
                for (int i = 0; i < nodes.size(); i++) {
                    int nodeB = nodes.get(i);
                    for (int j = i + 1; j < nodes.size(); j++) {
                        int nodeC = nodes.get(j);
                        if (graph.get(nodeB).contains(nodeC)) {
                            System.out.println(" >>> " + nodeA + ": " + graph.get(nodeA).size() + ", " + nodeB + ": " + graph.get(nodeB).size() + ", " + nodeC + ": " + graph.get(nodeC).size());
                            minScore = Math.min(minScore, graph.get(nodeA).size() + graph.get(nodeB).size() + graph.get(nodeC).size() - 6);
                        }
                    }
                }
            }
        }
        return minScore == Integer.MAX_VALUE ? -1 : minScore;
    }

    //    private static int finalInstances(int instances, List<Integer> averageUtil) {
//        int totalInstances = 0;
//        int index = 0;
//        while(index < averageUtil.size()) {
//            if (averageUtil.get(index) < 25) {
//                if (totalInstances > 1) {
//
//                }
//            }
//        }
//        return 0;
//    }
}
/**
 * First we need to create a graph. For this I have used an array of set of integer. Each index of this array will represent a set. The index will represent a node
 * and elements in the set will be its neighbours.
 * Now, we iterate over this graph
 * 1. Fetch a node from this graph. Say this node is A
 * 2. For each of its neighbours check if two neighbours are connected. This is done in two inner loops. If it is connected, lest say B and C are such connected nodes
 * then it means node A, B, C forms a triangular loop.
 * 3. Now, we need to add the count of nodes which are not part of this triangular loop and connected to A, B, C.
 * This is done as "graph.get(nodeA).size() + graph.get(nodeB).size() + graph.get(nodeC).size() - 6";
 * 4. minScore keeps track of minimum score.
 * 5. In case there are no triangles then we return -1
 */
