package com.kumanoit.ukgfreak;

public class AmazonSongs {
    public static void main(String[] args) {
        System.out.println(getTotalPairs(new int[]{37, 23, 60, 60}));
        System.out.println(getTotalPairs(new int[]{37, 23, 37, 60, 1, 59}));
    }

    private static int getTotalPairs(int[] songs) {
        int[] sum = new int[60];
        for (int i = 0; i < songs.length; i++) {
            sum[songs[i] % 60]++;
        }

        int solution = sum[0] * (sum[0] - 1) / 2;
        for (int i = 1; i < 30; i++) {
            solution += sum[i] * sum[60 - i];
        }
        solution += sum[30] * (sum[30] - 1) / 2;
        return solution;
    }
}
