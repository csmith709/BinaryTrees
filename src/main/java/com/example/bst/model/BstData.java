package com.example.bst.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class BstData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inputNumbers;
    private String treeStructure;
    private LocalDateTime createdAt = LocalDateTime.now();

//    Getters

    public Long getId() {
        return id;
    }

    public String getInputNumbers() {
        return inputNumbers;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

//    Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

}
