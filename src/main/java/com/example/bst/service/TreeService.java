package com.example.bst.service;

import com.example.bst.model.BstData;
import com.example.bst.model.TreeNode;
import com.example.bst.repository.BstDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class TreeService {

    private final BstDataRepository bstDataRepository;
    private final ObjectMapper objectMapper;

    public TreeService(BstDataRepository bstDataRepository) {
        this.bstDataRepository = bstDataRepository;
        this.objectMapper = new ObjectMapper();
    }

    public BstData getTreeById(Long id) {
        return bstDataRepository.findById(id).orElse(null);
    }

    public TreeNode deserializeTree(String treeStructure) {
        try {
            return objectMapper.readValue(treeStructure, TreeNode.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing tree structure", e);
        }
    }


    public TreeNode balanceTree(TreeNode root) {
        return root != null ? root.balance() : null; // Balance the tree
    }
}
