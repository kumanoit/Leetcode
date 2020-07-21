package com.kumanoit.trie;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * <p>
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * Approach:
 * 1. Insert: Iterate through word character by character. If a character is present in trie, move to next character. Else
 * insert that character
 * 2. Search: Iterate through word character by character and also on trie.
 * 3. StartsWith: Iterate through word character by character and also on trie.
 * <p>
 * Complexity for a word of length n
 * 1. Time:
 * 1.1 Insert: O(n)
 * 1.2 Search: O(n)
 * 1.3 startsWith: O(n)
 * <p>
 * 2. Space:
 * 2.1 Insert: O(26*n) since each node has 26 TrieNode object
 * 2.1 Search: O(1)
 * 2.1 Insert: O(1)
 *
 * @author akumar on 7/22/20 IST 1:35 AM
 */
public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.isPresent(word.charAt(i))) {
                node.insert(word.charAt(i));
            }
            node = node.get(word.charAt(i));
        }
        node.setWord();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.isPresent(word.charAt(i))) {
                return false;
            }
            node = node.get(word.charAt(i));
        }
        return node.isWord();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.isPresent(prefix.charAt(i))) {
                return false;
            }
            node = node.get(prefix.charAt(i));
        }
        return true;
    }
}
