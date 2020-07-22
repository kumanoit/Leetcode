package com.kumanoit.utils;

import java.util.List;

/**
 * @author akumar on 7/22/20 IST 9:15 PM
 */
public class ListUtils {

    public static <T> void printList(List<T> list) {
        list.forEach(item -> {
            System.out.print(item + ", ");
        });
    }

    public static <T> void printListOfList(List<List<T>> lists) {
        lists.forEach(list -> {
            printList(list);
            System.out.println();
        });
    }

}
