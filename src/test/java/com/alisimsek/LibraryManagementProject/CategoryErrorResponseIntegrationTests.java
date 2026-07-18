package com.alisimsek.LibraryManagementProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryErrorResponseIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void missingCategoryUsesTheForksGlobalErrorResponse() throws Exception {
        mockMvc.perform(get("/api/v1/categories/{id}", 999999L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Category with ID 999999 not found in the system."));
    }
}
