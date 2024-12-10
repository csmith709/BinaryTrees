package com.example.bst.service;

import com.example.bst.model.BstData;
import com.example.bst.repository.BstDataRepository;
import com.example.bst.model.TreeNode;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class BstService {

    private final BstDataRepository bstDataRepository;

    public BstService(BstDataRepository bstDataRepository) {
        this.bstDataRepository = bstDataRepository;
    }

    public TreeNode createBst(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty.");
        }
        TreeNode root = null;
        for (int number : numbers) {
            root = insertIntoBst(root, number);
        }
        return root;
    }

    private TreeNode insertIntoBst(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insertIntoBst(root.getLeft(), value));
        } else {
            root.setRight(insertIntoBst(root.getRight(), value));
        }
        return root;
    }

    public TreeNode balanceTree(TreeNode root) {
        List<Integer> sortedValues = new ArrayList<>();
        inOrderTraversal(root, sortedValues);
        return buildBalancedBst(sortedValues, 0, sortedValues.size() - 1);
    }

    private void inOrderTraversal(TreeNode root, List<Integer> sortedValues) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.getLeft(), sortedValues);
        sortedValues.add(root.getValue());
        inOrderTraversal(root.getRight(), sortedValues);
    }

    private TreeNode buildBalancedBst(List<Integer> sortedValues, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(sortedValues.get(mid));
        node.setLeft(buildBalancedBst(sortedValues, start, mid - 1));
        node.setRight(buildBalancedBst(sortedValues, mid + 1, end));
        return node;
    }

    // Save the tree data to the database
    public void saveTree(String numbers, TreeNode root) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String treeJson = objectMapper.writeValueAsString(root);

            BstData bstData = new BstData();
            bstData.setInputNumbers(numbers);
            bstData.setTreeStructure(treeJson);

            bstDataRepository.save(bstData);
        } catch (Exception e) {
            throw new RuntimeException("Error saving tree structure", e);
        }
    }

    // In BstService.java
    public TreeNode deserializeTree(String treeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(treeJson, TreeNode.class);  // Deserialize to TreeNode
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing tree structure", e);
        }
    }

    // Get all saved trees from the database
    public List<BstData> getAllTrees() {
        return bstDataRepository.findAll();
    }

    private TreeNode currentTree;

    public TreeNode getCurrentTree() {
        // Retrieve the latest tree from your repository, session, or wherever it's stored
        return currentTree; // Replace with actual storage logic
    }
    public void setCurrentTree(TreeNode tree) {
        this.currentTree = tree;  // Set the current tree
    }

    // In BstService.java
    public BstData getTreeById(Long treeId) {
        return bstDataRepository.findById(treeId).orElse(null);  // Return null if not found
    }
}
