package com.example.bst.repository;

import com.example.bst.model.BstData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<BstData, Long> {
    // Define tree-specific queries, if needed
}
