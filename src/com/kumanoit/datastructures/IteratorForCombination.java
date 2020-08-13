package com.kumanoit.datastructures;

/**
 * https://leetcode.com/problems/iterator-for-combination/
 * 1286. Iterator for Combination
 * Approach:
 * It would be easy if we consider this in terms of index.
 * Given a characters string as input[0....n] and output string out[0...p] we have following observation
 * the last index in out will have values from characters string which are located at any index in range[p...n]
 * the second last index in out will have values from characters string which are located at any index in range[p-1...n-1]
 * and so on
 * So, we start comparing character from last index and will move backward,
 * 1) if an index is lesser than it's maximum index value, then we will replace it with its next element
 * and will add appropriate prefix and suffix and return from the output
 * 2) If it is not, we move to previous index and do the same
 * Complexity
 * 1. Time: next() : O(n): Since we are looping
 * hasNext(): O(1)
 * 2. Space: O(n): Every time string is created
 *
 * @author akumar on 8/14/20 IST 2:43 AM
 */
public class IteratorForCombination {

    public static void main(String[] args) {
        test(new String[]{"CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"}, "abc", 2);
    }

    private static void test(String[] commands, String characters, int combinationLength) {
        CombinationIterator iterator = null;
        for (String command : commands) {
            if (command.equals("CombinationIterator")) {
                iterator = new CombinationIterator(characters, combinationLength);
            } else if (command.equals("next")) {
                System.out.println(iterator.next());
            } else if (command.equals("hasNext")) {
                System.out.println(iterator.hasNext());
            }
        }
    }
}

class CombinationIterator {

    String characters;
    int combinationLength;
    String presentString;
    String lastString;

    boolean calledAtLeastOnce;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        int start = characters.length() - combinationLength;
        lastString = characters.substring(start);
        calledAtLeastOnce = false;
    }

    public String next() {
        calledAtLeastOnce = true;
        if (presentString == null) {
            presentString = characters.substring(0, combinationLength);
        } else {
            int lastIndex = characters.length() - 1;
            for (int i = presentString.length() - 1; i >= 0; i--) {
                int currentCharacterIndex = characters.indexOf(presentString.charAt(i));
                if (currentCharacterIndex != lastIndex) {
                    int suffixLength = combinationLength - i;
                    String suffix = characters.substring(currentCharacterIndex + 1,
                            currentCharacterIndex + 1 + suffixLength);
                    String prefix = presentString.substring(0, i);
                    presentString = prefix + suffix;
                    break;
                } else {
                    lastIndex--;
                }
            }
        }
        return presentString;
    }

    public boolean hasNext() {
        return !(calledAtLeastOnce && lastString.equals(presentString));
    }
}
