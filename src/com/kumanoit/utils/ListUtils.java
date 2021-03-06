package com.kumanoit.utils;

import java.util.List;

/**
 * @author akumar on 7/22/20 IST 9:15 PM
 */
public class ListUtils {

    public static <T> void printList(List<T> list) {
        if (list == null) {
            System.out.println("Empty list.");
        }
        list.forEach(item -> {
            System.out.print(item + ", ");
        });
    }

    public static <T> void printListOfList(List<List<T>> lists) {
        if (lists == null) {
            System.out.println("Empty list of list.");
        }
        lists.forEach(list -> {
            printList(list);
            System.out.println();
        });
    }

}
