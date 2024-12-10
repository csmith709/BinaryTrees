package com.example.bst.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TreeNodeTest {

    @Test
    public void testHeight() {
        // Create nodes
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(15);

        // Build the tree
        root.setLeft(left);
        root.setRight(right);

        // Assert height
        assertEquals(2, root.height());

        // Add more levels and check height again
        TreeNode leftLeft = new TreeNode(3);
        left.setLeft(leftLeft);
        assertEquals(3, root.height());
    }

    @Test
    public void testIsBalanced() {
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        root.setLeft(left);

        assertTrue(root.isBalanced());

        TreeNode leftLeft = new TreeNode(3);
        left.setLeft(leftLeft);

        assertFalse(root.isBalanced());
    }

    @Test
    public void testBalance() {
        // Create an unbalanced tree
        TreeNode root = new TreeNode(10);
        root.setLeft(new TreeNode(5));
        root.getLeft().setLeft(new TreeNode(3));

        assertFalse(root.isBalanced());

        // Balance the tree
        TreeNode balancedTree = root.balance();

        assertTrue(balancedTree.isBalanced());
        assertEquals(5, balancedTree.getValue()); // Middle value should be the new root
    }


    @Test
    public void testToMap() {
        // Create nodes
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(15);
        root.setLeft(left);
        root.setRight(right);

        // Convert to map
        Map<String, Object> map = root.toMap();

        // Assert map structure
        assertEquals(10, map.get("value"));
        assertNotNull(map.get("left"));
        assertNotNull(map.get("right"));

        // Check nested structure
        Map<String, Object> leftMap = (Map<String, Object>) map.get("left");
        assertEquals(5, leftMap.get("value"));


        Map<String, Object> rightMap = (Map<String, Object>) map.get("right");
        assertEquals(15, rightMap.get("value"));
    }
}
