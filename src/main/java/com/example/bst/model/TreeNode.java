package com.example.bst.model;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    // Constructor
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

    public int height() {
        // Base case: height of an empty tree is 0
        return 1 + Math.max(
                this.left != null ? this.left.height() : 0,
                this.right != null ? this.right.height() : 0
        );
    }


    public boolean isBalanced() {
        return Math.abs((this.left != null ? this.left.height() : 0) -
                (this.right != null ? this.right.height() : 0)) <= 1;
    }

}
