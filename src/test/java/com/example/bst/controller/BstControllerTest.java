package com.example.bst.controller;

import com.example.bst.model.BstData;
import com.example.bst.model.TreeNode;
import com.example.bst.service.BstService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BstControllerTest {

    @Mock
    private BstService bstService;

    @Mock
    private Model model;

    @InjectMocks
    private BstController bstController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showInputForm() {
        String result = bstController.showInputForm();
        assertEquals("index", result, "The view name for the input form should be 'index'.");
    }

    @Test
    void processNumbers() throws JsonProcessingException {
        String numbers = "3,1,4,1,5";
        boolean balanced = false; // Add this to match the method signature
        int[] numArray = {3, 1, 4, 1, 5};
        TreeNode mockTree = mock(TreeNode.class);
        Map<String, Object> mockTreeMap = Map.of(
                "value", 3,
                "left", Map.of(), // Use an empty map to represent a missing or null subtree
                "right", Map.of()
        );

        when(bstService.createBst(numArray)).thenReturn(mockTree);
        when(mockTree.toMap()).thenReturn(mockTreeMap);

        // Include the balanced parameter when calling the method
        String result = bstController.processNumbers(numbers, balanced, model);

        verify(bstService).createBst(numArray);
        verify(bstService).saveTree(eq(numbers), eq(mockTree));
        verify(model).addAttribute(eq("tree"), anyString());
        assertEquals("results", result, "The view name for the results page should be 'results'.");
    }


    @Test
    void showPreviousTrees() {
        List<BstData> mockTrees = List.of(new BstData(), new BstData());

        when(bstService.getAllTrees()).thenReturn(mockTrees);

        String result = bstController.showPreviousTrees(model);

        verify(model).addAttribute("trees", mockTrees);
        assertEquals("previous-trees", result, "The view name for previous trees should be 'previous-trees'.");
    }

    @Test
    void handleInvalidNumberFormat() {
        NumberFormatException ex = new NumberFormatException("Invalid number");
        String result = bstController.handleInvalidNumberFormat(ex, model);

        verify(model).addAttribute("error", "Invalid number format: Invalid number");
        assertEquals("error", result, "The view name for handling errors should be 'error'.");
    }
}
