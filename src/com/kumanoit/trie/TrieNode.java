package com.kumanoit.trie;

/**
 * This is TrieNode data structure
 *
 * @author akumar on 7/22/20 IST 1:35 AM
 */
public class TrieNode {

    private static final int BASE = 26;

    private final TrieNode[] children;
    private boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[BASE];
        this.isWord = false;
    }

    public boolean isPresent(final char letter) {
        return this.children[letter - 'a'] != null;
    }

    public void insert(final char letter) {
        this.children[letter - 'a'] = new TrieNode();
    }

    public TrieNode get(final char letter) {
        return this.children[letter - 'a'];
    }

    public void setWord() {
        this.isWord = true;
    }

    public boolean isWord() {
        return this.isWord;
    }
}
