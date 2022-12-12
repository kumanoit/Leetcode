package com.ukgfreak;

import java.util.ArrayList;

public class Groups {

    static int total = 0;

    public static void main(String[] args) {
        countOptions(1, 1);
        countOptions(4, 4);
        countOptions(4, 3);
        countOptions(7, 3);
        countOptions(9, 3);
    }

    private static void countOptions(int people, int groups) {
        total = 0;
        countOptions(people, groups, 1, new ArrayList<>());
        System.out.println("Possible ways: " + total);
        System.out.println();
    }

    private static void countOptions(int people, int groups, int lastGroupPeopleCount, ArrayList<Integer> list) {
        if (groups == 0 && people == 0) {
            total++;
            for(int x : list) {
                System.out.print(x + ", ");
            }
            System.out.println();
            return;
        }
        if (groups == 0 || people == 0 || (people < groups * lastGroupPeopleCount)) {
            return;
        }

        for(int i = lastGroupPeopleCount; i <= people; i++) {
            list.add(i);
            countOptions(people - i, groups - 1, i, list);
            list.remove(list.size() - 1);
        }
    }

}
