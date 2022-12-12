package com.kumanoit.tree;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 2/21/22 IST 9:51 PM
 */
public class Codec {
    static TreeNode temp;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        temp = root;
        return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return temp;
    }
}
