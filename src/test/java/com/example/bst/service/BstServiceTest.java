package com.example.bst.service;

import com.example.bst.model.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BstServiceTest {

    @Autowired
    private final BstService bstService = new BstService(null); // Mock the repository if needed

    @Test
    public void testCreateBst() {
        int[] numbers = {5, 3, 8, 1, 4};
        TreeNode root = bstService.createBst(numbers);
        assertNotNull(root);
        assertEquals(5, root.getValue());
    }


}
