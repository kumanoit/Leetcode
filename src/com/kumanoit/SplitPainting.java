package com.kumanoit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/24/21 IST 8:26 PM
 */
public class SplitPainting {
    public static void main(String[] args) {
//        test(new int[][]{{1, 4, 5}, {4, 7, 7}, {1, 7, 9}});
//        test(new int[][]{{1, 4, 5}, {2, 4, 7}, {3, 4, 9}});
//        test(new int[][]{{1, 2, 9}, {1, 3, 7}, {1, 5, 9}});
//        test(new int[][]{{1, 7, 9}, {6, 8, 15}, {8, 10, 7}});
//        test(new int[][]{{1, 4, 5}, {1, 4, 7}, {4, 7, 1}, {4, 7, 11}});
//        test(new int[][]{{4,16,12},{9,10,15},{18,19,13},{3,13,20},{12,16,3},{2,10,10},{3,11,4},{13,16,6}});
        test(new int[][]{{30,35,383},{35,38,590},{38,42,825},{42,43,1162},{43,62,927},{62,81,836},{81,96,1263},{96,114,1568},
                {114,126,1263},{126,127,1409},{127,134,1430},{134,136,1773},{136,152,2251},{152,154,2747},{154,156,2909},{156,161,2702},{161,161,2728},{161,170,2864},{170,176,2386},{176,179,2360},{179,187,2396},{187,194,2053},{194,203,1626},{203,208,1970},{208,220,1474},{220,221,1130},{221,223,984},{223,233,848},{233,245,1065},{245,250,1536},{250,257,1634},{257,260,1297},{260,261,1199},{261,267,1635},{267,270,1199},{270,272,982},{272,272,1012},{272,276,850},{276,276,829},{276,278,1089},{278,278,618},{278,283,588},{283,285,328},{285,289,696},{289,290,734},{290,291,442},{291,292,404},{292,296,368},{299,300,479}});

    }

    private static void test(int[][] input) {
        splitPainting(input).forEach(item -> {
            item.forEach(i -> System.out.print(i + ", "));
            System.out.println();
        });
        System.out.println();
    }

    public static List<List<Long>> splitPainting(int[][] segments) {
        long sumSoFar = 0;
        int n = segments.length;
        Pair[] pairs = new Pair[n * 2];
        for (int i = 0, k = 0; i < segments.length; i++, k += 2) {
            pairs[k] = new Pair(segments[i][0], segments[i][2]);
            pairs[k + 1] = new Pair(segments[i][1], -segments[i][2]);
        }
        Arrays.sort(pairs);
//        for(Pair pair : pairs) {
//            System.out.print(pair.toString() + ", ");
//        }
//        System.out.println();

        List<List<Long>> solution = new ArrayList<>();
        Long start = pairs[0].index;

        for (int i = 0; i < pairs.length; i++) {
            if (start == 161) {
                System.out.println();
            }
            if (!start.equals(pairs[i].index)) {
                if (sumSoFar > 0) {
                    List<Long> sub = new ArrayList<>();
                    sub.add(start);
                    sub.add(pairs[i].index);
                    sub.add(sumSoFar);
                    solution.add(sub);
                }
                start = pairs[i].index;
            }
            sumSoFar += pairs[i].value;
        }
        return solution;
    }
}

class Pair implements Comparable<Pair> {
    Long index;
    Long value;

    Pair(int index, int value) {
        this.index = Long.valueOf(index);
        this.value = Long.valueOf(value);
    }

    @Override
    public int compareTo(Pair other) {
        if (this.index == other.index) {
            if (this.value * other.value < 0) {
                return this.value < 0 ? -1 : +1;
            } else {
                return (int) (this.value - other.value);
            }
        }
        return (int) (this.index - other.index);
    }

    @Override
    public String toString() {
        return "(" + this.index + ", " + this.value + ")";
    }
}
