package com.example.bst.service;

import com.example.bst.model.BstData;
import com.example.bst.repository.BstDataRepository;
import com.example.bst.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BstService {

    private final BstDataRepository bstDataRepository; // Inject repository

    // Constructor-based injection for repository
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

    // Balancing the BST
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
    public void saveTree(String numbers, String treeJson) {
        BstData bstData = new BstData();
        bstData.setInputNumbers(numbers);
        bstData.setTreeStructure(treeJson);
        bstDataRepository.save(bstData); // Corrected this line
    }


    // Get all saved trees from the database
    public List<BstData> getAllTrees() {
        return bstDataRepository.findAll(); // Corrected this line
    }
}
