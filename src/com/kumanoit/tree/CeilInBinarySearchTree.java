package com.kumanoit.tree;

import sun.reflect.generics.tree.Tree;

/**
 * Solution 1:
 * Brute Force Solution:
 * Do an inorder traversal and save result into an array. Iterate over the array to get an element equal to or greater
 * than current key.
 * Time Complexity: O(n)
 * Space Complexity: O(n) for auxillary array to save inorder representation of tree.
 * <p>
 * <p>
 * Solution 2:
 * Brute Force Solution:
 * Do an inorder traversal and save result into an array.Since array is sorted do a binary search over the array to get an
 * element equal to or greater than current key.
 * Time Complexity: O(n) for traversal of tree and O(lg(n)) for binary search in array. Total = O(n)
 * Space Complexity: O(n) for auxillary array to save inorder representation of tree.
 * <p>
 * <p>
 * Solution 3: Optimal
 * We can do a DFS search on given tree in following fashion.
 * i) if root is null then return null because then ceil doesn't exist
 * ii) If key is lesser than root value than ceil will be in right subtree so call recursively on right subtree
 * iii) if key is greater than current root, then either
 * a) the root is ceil
 * b) ceil is in left subtree: call for left subtree. If left subtree returns a non null value then that will be ceil
 * otherwise the root is ceil
 */
public class CeilInBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = TreeUtils.createTree(new Integer[]{30,20,40,10,25,35,50});
        test(root, 30);
        test(root, 25);
        test(root, 5);
        test(root, 60);
        test(root, 23);
        test(root, 37);
        test(root, 49);
        test(root, 31);
    }
    private static void test(TreeNode root, int key) {
        TreeNode sol = getCeil(root, key);
        if (sol != null) {
            System.out.println(" key = " + key + "  " + sol.val);
        } else {
            System.out.println(" key = " + key + "  " + sol);
        }
    }
    public static TreeNode getCeil(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            return root;
        }

        if (root.val < key) {
            return getCeil(root.right, key);
        }

        TreeNode result = getCeil(root.left, key);
        return result == null ? root : result;
    }
}
