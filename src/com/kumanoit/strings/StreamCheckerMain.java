package com.kumanoit.strings;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/23/20 IST 4:58 PM
 */
public class StreamCheckerMain {
    public static void main(String[] args) {
//        test(new String[]{"cd", "f", "kl", "c"}, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'});
        test(new String[]{"abcde"}, new char[]{'a', 'b', 'c', 'd', 'e'});
        test(new String[]{"edcba"}, new char[]{'a', 'b', 'c', 'd', 'e'});
        test(new String[]{"aaaaa"}, new char[]{'a', 'a', 'a', 'a', 'a'});
        test(new String[]{"c"}, new char[]{'a', 'a', 'a', 'a', 'a'});
    }

    private static void test(String[] words, char[] queries) {
        System.out.println();
        StreamChecker streamChecker = new StreamChecker(words);
        for (char query : queries) {
            System.out.println(query + " " + streamChecker.query(query));
        }
    }
}

class StreamChecker {
    Trie trie;
    StringBuilder queryStringSoFar;

    public StreamChecker(String[] words) {
        trie = new Trie();
        for (String word : words) {
            trie.insertWord(word);
        }
        queryStringSoFar = new StringBuilder();
    }

    public boolean query(char letter) {
        queryStringSoFar.append(letter);
        TrieNode iter = trie.getRoot();
        for (int i = queryStringSoFar.length() - 1; i >= 0; i--) {
            char ch = queryStringSoFar.charAt(i);
            iter = iter.getNode(ch);
            if (iter == null) {
                break;
            }
            if (iter != null && iter.isWord()) {
                return true;
            }
        }
        return false;
    }
}

class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insertWord(String word) {
        if (word.length() == 0) {
            return;
        }
        TrieNode iter = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if (!iter.contains(ch)) {
                iter.add(ch);
            }
            iter = iter.getNode(ch);
        }
        iter.setWord();
    }
}

class TrieNode {
    private final TrieNode[] children;
    private boolean isWord;

    public TrieNode() {
        this.isWord = false;
        this.children = new TrieNode[26];
    }

    public boolean contains(char ch) {
        return this.children[ch - 'a'] != null;
    }

    public void add(char ch) {
        this.children[ch - 'a'] = new TrieNode();
    }

    public void setWord() {
        this.isWord = true;
    }

    public TrieNode getNode(char ch) {
        return this.children[ch - 'a'];
    }

    public boolean isWord() {
        return isWord;
    }
}
