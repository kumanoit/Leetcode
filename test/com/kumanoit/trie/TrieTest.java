package com.kumanoit.trie;

public class TrieTest {

    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("apple");
        System.out.println("Expected : true, Found: " + root.search("apple"));
        System.out.println("Expected : true, Found: " + root.startsWith("apple"));

        root.insert("animal");
        System.out.println("Expected : false, Found: " + root.search("ani"));
        System.out.println("Expected : true, Found: " + root.startsWith("ani"));

    }
}
