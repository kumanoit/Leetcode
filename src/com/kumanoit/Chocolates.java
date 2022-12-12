package com.kumanoit;

public class Chocolates {
    public static void main(String[] args) {
//        System.out.println(getMaximumChocolates(new int[]{2, 12, 50, 100}, 5));
        System.out.println(getMaximumChocolates(new int[]{1, 10, 20, 25, 100}, 5));
    }

    private static int getMaximumChocolates(int[] input1, int input2) {
        int[] children = new int[2];
        calculate(input1, 0, 3, children, 0, "");
        System.out.println(children[0] + " " + children[1]);
        return children[0];
    }

    private static void calculate(int[] chocolates, int startIndex, int maxK, int[] children, int childrenId, String prefix) {
        if (startIndex >= chocolates.length) {
            return;
        }
        int sum = 0;
        int maxSum = 0;
        int lastIndex = Math.min(startIndex + maxK, chocolates.length);
        System.out.println(prefix + " Range: {" + startIndex + " : " + lastIndex + "} childrenId: " + childrenId);

        if (startIndex + maxK >= chocolates.length) {
            for (int i = startIndex; i < lastIndex; i++) {
                maxSum += chocolates[i];
            }
        } else {
            for (int i = startIndex; i < lastIndex; i++) {
                sum += chocolates[i];
                calculate(chocolates, i + 1, 3 * (i - startIndex + 1), children, childrenId ^ 1, prefix + "\t");
                maxSum = Math.max(sum + children[childrenId], maxSum);
            }
        }
        children[childrenId] = maxSum;
        System.out.println(prefix + " ChildrenId: " + childrenId + " : " + children[childrenId]);
    }
}
