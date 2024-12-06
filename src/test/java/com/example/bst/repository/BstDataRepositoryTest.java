package com.example.bst.repository;

import com.example.bst.model.BstData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BstDataRepositoryTest {

    @Autowired
    private BstDataRepository bstDataRepository;

    @Test
    void testSaveAndRetrieveTree() {
        BstData bstData = new BstData();
        bstData.setInputNumbers("5,3,7");
        bstData.setTreeStructure("{\"value\":5,\"left\":{\"value\":3},\"right\":{\"value\":7}}");
        bstDataRepository.save(bstData);

        BstData savedBstData = bstDataRepository.findById(bstData.getId()).orElse(null);
        assertNotNull(savedBstData);
        assertEquals("5,3,7", savedBstData.getInputNumbers());
        assertEquals("{\"value\":5,\"left\":{\"value\":3},\"right\":{\"value\":7}}", savedBstData.getTreeStructure());
    }

    @Test
    void testFindAllTrees() {
        BstData bstData1 = new BstData();
        bstData1.setInputNumbers("5,3,7");
        bstData1.setTreeStructure("{\"value\":5,\"left\":{\"value\":3},\"right\":{\"value\":7}}");
        bstDataRepository.save(bstData1);

        BstData bstData2 = new BstData();
        bstData2.setInputNumbers("2,1,3");
        bstData2.setTreeStructure("{\"value\":2,\"left\":{\"value\":1},\"right\":{\"value\":3}}");
        bstDataRepository.save(bstData2);

        List<BstData> allTrees = bstDataRepository.findAll();
        assertEquals(2, allTrees.size());
    }
}
