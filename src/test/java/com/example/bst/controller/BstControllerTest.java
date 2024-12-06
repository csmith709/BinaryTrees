package com.example.bst.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;  // Add this import
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BstControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testProcessNumbers() throws Exception {
        String numbers = "5,3,7";

        mockMvc.perform(post("/process-numbers")  // Use the post method correctly
                        .param("numbers", numbers))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.value").value(5))
                .andExpect(jsonPath("$.left.value").value(3))
                .andExpect(jsonPath("$.right.value").value(7));
    }

    @Test
    void showInputForm() {
    }

    @Test
    void processNumbers() {
    }

    @Test
    void showPreviousTrees() {
    }

    @Test
    void handleInvalidNumberFormat() {
    }

    @Test
    void testShowInputForm() {
    }

    @Test
    void testShowPreviousTrees() {
    }
}
