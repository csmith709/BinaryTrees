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

    @GetMapping("/")
    public String showInputForm() {
        return "index"; // This returns enter-numbers.html
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers,
                                 @RequestParam(required = false) boolean balanced,
                                 Model model) throws JsonProcessingException {
        // Convert input numbers into an array of integers
        int[] numArray = Arrays.stream(numbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        // Create the BST
        TreeNode tree = bstService.createBst(numArray);

        // If balanced option is selected, balance the tree
        if (balanced) {
            tree = tree.balance();
        }

        // Convert TreeNode to a Map representation
        Map<String, Object> treeMap = tree.toMap();

        // Serialize the Map to JSON string
        String treeJson = new ObjectMapper().writeValueAsString(treeMap);

        // Save the tree structure to the repository, passing the TreeNode, not treeJson
        bstService.saveTree(numbers, tree);

        // Pass the tree JSON data to the results page
        model.addAttribute("tree", treeJson); // Adding the tree JSON data to the model
        System.out.println(treeJson);

        bstService.setCurrentTree(tree); // Save the created tree as the current tree
        return "results"; // Direct rendering for results.html
    }


    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BstData> trees = bstService.getAllTrees();  // Fetch the list of trees
        model.addAttribute("trees", trees);  // Pass trees to the view
        return "previous-trees";  // This returns the view that will display the trees
    }


    @ExceptionHandler(NumberFormatException.class)
    public String handleInvalidNumberFormat(Exception ex, Model model) {
        model.addAttribute("error", "Invalid number format: " + ex.getMessage());
        return "error"; // Return an error page if the user enters invalid numbers
    }
}
