package com.kumanoit.strings;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TopKFrequentWords {

    Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(final Node node1, final Node node2) {
            return node1.frequency == node2.frequency ?
                    node1.word.compareTo(node2.word) :
                    node2.frequency - node1.frequency;
        }
    };

    Map<Node, Integer> map = new TreeMap<>(nodeComparator);

}

class Node {
    String word;
    int frequency;
}
//
//class MyComparator implements Comparator<Node> {
//    @Override
//    public int compare(Node node1, Node node2) {
//
//    }
//
//}
