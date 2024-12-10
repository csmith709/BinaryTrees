package com.example.bst.service;

import com.example.bst.model.BstData;
import com.example.bst.model.TreeNode;
import com.example.bst.repository.BstDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TreeServiceTest {

    @InjectMocks
    private TreeService treeService;

    @Mock
    private BstDataRepository bstDataRepository;

    private BstData bstData;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bstData = new BstData();
        bstData.setTreeStructure("{\"value\": 5, \"left\": {\"value\": 3}, \"right\": {\"value\": 7}}");
    }

    @Test
    public void testGetTreeById() {
        when(bstDataRepository.findById(1L)).thenReturn(java.util.Optional.of(bstData));

        BstData result = treeService.getTreeById(1L);
        assertNotNull(result);
        assertEquals(bstData, result);
    }

    @Test
    public void testBalanceTree() {
        TreeNode rootNode = treeService.deserializeTree(bstData.getTreeStructure());
        TreeNode balancedTree = treeService.balanceTree(rootNode);

        assertNotNull(balancedTree);
        // Additional assertions to verify that the tree is balanced
    }
}
