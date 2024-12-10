package com.example.bst.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    // Default constructor for Jackson deserialization
    public TreeNode() {
    }

    // Constructor with a value
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Getter and Setter for value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // Getter and Setter for left
    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    // Getter and Setter for right
    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    // Method to convert TreeNode to a Map
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("value", this.value);

        if (this.left != null) {
            map.put("left", this.left.toMap()); // Recursively convert left subtree
        } else {
            map.put("left", null); // Ensure null is serialized
        }

        if (this.right != null) {
            map.put("right", this.right.toMap()); // Recursively convert right subtree
        } else {
            map.put("right", null); // Ensure null is serialized
        }

        return map;
    }

    // Height of the tree
    public int height() {
        int leftHeight = (this.left != null) ? this.left.height() : 0;
        int rightHeight = (this.right != null) ? this.right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }


    // Check if the tree is balanced
    public boolean isBalanced() {
        return Math.abs((this.left != null ? this.left.height() : 0) -
                (this.right != null ? this.right.height() : 0)) <= 1 &&
                (this.left == null || this.left.isBalanced()) &&
                (this.right == null || this.right.isBalanced());
    }


    // Balance the tree (Balanced BST)
    public TreeNode balance() {
        if (this == null) return null;
        List<Integer> sortedValues = new ArrayList<>();
        inOrderTraversal(this, sortedValues);
        return buildBalancedBst(sortedValues, 0, sortedValues.size() - 1);
    }


    // Helper method for in-order traversal
    private void inOrderTraversal(TreeNode root, List<Integer> sortedValues) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, sortedValues);
        sortedValues.add(root.value);
        inOrderTraversal(root.right, sortedValues);
    }

    // Helper method to build a balanced BST from sorted values
    private TreeNode buildBalancedBst(List<Integer> sortedValues, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(sortedValues.get(mid));

        node.left = buildBalancedBst(sortedValues, start, mid - 1);
        node.right = buildBalancedBst(sortedValues, mid + 1, end);

        return node;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
