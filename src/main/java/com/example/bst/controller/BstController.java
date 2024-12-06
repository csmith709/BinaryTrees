package com.example.bst.controller;

import com.example.bst.model.BstData;
import com.example.bst.service.BstService;
import com.example.bst.model.TreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class BstController {
    private final BstService bstService;

    public BstController(BstService bstService) {
        this.bstService = bstService;
    }

    @GetMapping("/enter-numbers")
    public String showInputForm() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestParam String numbers) throws JsonProcessingException {
        // Convert input numbers into an array of integers
        int[] numArray = Arrays.stream(numbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        // Create the BST
        TreeNode tree = bstService.createBst(numArray);

        // Convert TreeNode to a Map representation
        Map<String, Object> treeMap = tree.toMap();

        // Serialize the Map to JSON string
        String treeJson = new ObjectMapper().writeValueAsString(treeMap);

        // Save the tree structure to the repository
        bstService.saveTree(numbers, treeJson);

        // Return the serialized JSON tree
        return treeJson;
    }


    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BstData> trees = bstService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleInvalidNumberFormat(Exception ex, Model model) {
        model.addAttribute("error", "Invalid number format: " + ex.getMessage());
        return "error";
    }


}
