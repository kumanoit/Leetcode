package com.kumanoit.tree;

import sun.reflect.generics.tree.Tree;

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
public class SerializeDeserialize {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root1 = TreeUtils.createTree(new Integer[] {33,null,22,null,53,null,64});
        TreeNode root2 = TreeUtils.createTree(new Integer[] {1,2,3,4});
        String serialized1 = codec.serialize(root1);
        String serialized2 = codec.serialize(root2);
        TreeNode deserialized1 = codec.deserialize(serialized1);
        TreeNode deserialized2 = codec.deserialize(serialized2);
        System.out.println(TreeUtils.areTreesIdentical(root1, deserialized1));
        System.out.println(TreeUtils.areTreesIdentical(root2, deserialized2));
    }
}
