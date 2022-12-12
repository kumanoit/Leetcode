package com.kumanoit.array;

import java.util.ArrayList;
import java.util.List;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 10/6/22 IST 12:05 AM
 */
public class Amazon1 {
    public static void main(String[] args) {
        System.out.println(getSolution("cccaabbccc"));
    }
    private static int findMax(List<Integer> list) {

        int sumSoFar = 0;
        int max = 0;
        for(int i = 0; i < list.size(); i++) {
            sumSoFar = Math.max(sumSoFar + list.get(i), 0);
            max = Math.max(max, sumSoFar);
        }
        System.out.println(" max = " + max);

        List<Integer> newList = new ArrayList<>();
        int s = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            if ((list.get(i) < 0 && s < 0) || (list.get(i) > 0 && s > 0)) {
                s += list.get(i);
            } else {
                newList.add(s);
                s = list.get(i);
            }
        }
        newList.add(s);
        int posCount = 0;
        for(int x : newList) {
            if (x > 0) {
                posCount++;
            }
        }

        return posCount == 1 ? max - 1 : max;
    }
    public static int getSolution(String s) {
        int max = 0;
        for(char i = 'a'; i <= 'z'; i++) {
            for(char j = 'a'; j <= 'z'; j++) {
                if(i == j) {
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                boolean a = false, b = false;
                for(char ch : s.toCharArray()) {
                    if (ch == i) {
                        list.add(1);
                        a = true;
                    } else if (ch == j) {
                        list.add(-1);
                        b = true;
                    }
                }
                if (list.size() > max && a && b) {
                    System.out.println("i = " + i + " j = " + j);
                    list.forEach(x -> System.out.print(x + ", "));
                    System.out.println();
                    max = Math.max(max, findMax(list));
                }
            }
        }
        return max;
    }
}
/**
 //        int[] maxSum = new int[list.size()];
 //        maxSum[0] = list.get(0);
 //        for (int i = 1 ; i < list.size(); i++) {
 //            maxSum[i] = Math.max(list.get(i), maxSum[i - 1] + list.get(i));
 //        }
 //
 //        int sum = 0 ;
 //        for (int i = 0 ; i < 2; i++) {
 //            sum += list.get(i);
 //        }
 //
 //        int ans = sum;
 //        for (int i = 2 ; i < list.size(); i++) {
 //            sum = sum + list.get(i) - list.get(i - 2);
 //            ans = Math.max(ans, sum);
 //            ans = Math.max(ans, sum + maxSum[i - 2]);
 //        }
 //
 //        return ans;
 **/
/**
 * Brute Force:
 * Consider all substrings and count the frequency of each characer in that substring. Take difference of minimum and maximum
 * frequency and keep track of maximum difference in another variable. At the end return that variable. Time complexity will
 * be O(n^3) as there are O(n^2) substrings and it will take O(n) time to compute the frequency  
 **/
