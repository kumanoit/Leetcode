package com.kumanoit.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 7/22/20 IST 11:21 PM
 */
class TrieHashMapNode {
    private final Map<Character, TrieHashMapNode> map;
    private boolean isWord;

    public TrieHashMapNode() {
        this.map = new HashMap<>();
        this.isWord = false;
    }

    public TrieHashMapNode get(char ch) {
        return map.get(ch);
    }

    public void put(char ch) {
        if (!map.containsKey(ch)) {
            map.put(ch, new TrieHashMapNode());
        }
    }

    public boolean isPresent(char ch) {
        return map.containsKey(ch);
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord() {
        this.isWord = true;
    }
}

class TrieHashMap {
    private final TrieHashMapNode root;

    public TrieHashMap() {
        this.root = new TrieHashMapNode();
    }

    public void insert(String word) {
        TrieHashMapNode node = this.root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.isPresent(letter)) {
                node.put(letter);
            }
            if (i == word.length() - 1) {
                node.setWord();
            }
            node = node.get(letter);
        }
    }

    public boolean isWordPresent(String word) {
        TrieHashMapNode node = this.root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.isPresent(letter)) {
                return false;
            }
            node = node.get(letter);
        }
        return node.isWord();
    }

    public boolean startsWith(String word) {
        TrieHashMapNode node = this.root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.isPresent(letter)) {
                return false;
            }
            node = node.get(letter);
        }
        return true;
    }

    public String getParent(String word) {
        TrieHashMapNode node = this.root;
        String parent = null;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.isPresent(letter)) {
                break;
            }
            if (node.isWord()) {
                parent = word.substring(0, i + 1);
                break;
            }
            node = node.get(letter);
        }
        return parent;
    }
}

public class ReplaceWord {
    public static String replaceWords(List<String> dict, String sentence) {
        TrieHashMap trieHashMap = new TrieHashMap();
        for (int i = 0; i < dict.size(); i++) {
            trieHashMap.insert(dict.get(i));
        }
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String parent = trieHashMap.getParent(words[i]);
            if (parent == null) {
                sb.append(words[i]);
            } else {
                sb.append(parent);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] words) {
//        System.out.println(replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(replaceWords(Arrays.asList("cat", "bat", "t", "b", "rat"), "the cattle was rattled by the battery"));
    }
}
