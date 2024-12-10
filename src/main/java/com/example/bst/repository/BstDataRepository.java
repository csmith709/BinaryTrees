package com.example.bst.repository;


import com.example.bst.model.BstData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BstDataRepository extends JpaRepository<BstData, Long> {

    public List<BstData> findByCreatedAtAfter(LocalDateTime date);

}