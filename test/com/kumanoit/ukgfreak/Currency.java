package com.kumanoit.ukgfreak;

public class Currency {

    public static void main(String[] args) throws Exception {
        System.out.println(monOperations(8));
        System.out.println(monOperations(333));
        System.out.println(monOperations(9));
        System.out.println(monOperations(6));
        System.out.println(monOperations(3));
        System.out.println(monOperations(0));
    }

    public static long monOperations(int n) {
        int minOperations = n;
        while(n > 0) {
            n = n >> 1;
            if (n != 0) {
                minOperations ^= n;
            }
        }
        return minOperations;
    }


}
