package com.example.bst.controller;

import com.example.bst.model.BstData;
import com.example.bst.service.BstService;
import com.example.bst.service.TreeService;
import com.example.bst.model.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TreeController {
    private final BstService bstService;

    public TreeController(BstService bstService) {
        this.bstService = bstService;
    }

    public BstData getTreeById(Long treeId) {
        return bstService.getTreeById(treeId);
    }



    @GetMapping("/balance-tree")
    public String balanceTree(@RequestParam Long treeId, Model model) {
        BstData bstData = bstService.getTreeById(treeId);
        if (bstData == null) {
            model.addAttribute("error", "Tree not found.");
            return "error"; // Error page if tree is not found
        }

        TreeNode rootNode = bstService.deserializeTree(bstData.getTreeStructure());
        if (rootNode == null) {
            model.addAttribute("error", "Tree structure could not be deserialized.");
            return "error"; // Error page if deserialization fails
        }

        TreeNode balancedTree = bstService.balanceTree(rootNode);
        if (balancedTree == null) {
            model.addAttribute("error", "Tree could not be balanced.");
            return "error"; // Error page if balancing fails
        }

        model.addAttribute("tree", balancedTree);
        return "balanced-tree"; // View to display the balanced tree
    }



    @GetMapping("/api/tree")
    @ResponseBody
    public Map<String, Object> getCurrentTree() {
        // Retrieve the current tree stored in the service
        TreeNode tree = bstService.getCurrentTree();
        if (tree == null) {
            return Map.of("error", "No tree available");
        }
        Map<String, Object> treeMap = tree.toMap();  // Convert TreeNode to a Map for JSON
        return treeMap;
    }
}
