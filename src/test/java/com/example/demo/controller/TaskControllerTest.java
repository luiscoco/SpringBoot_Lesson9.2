package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void getTaskById_returnsOkAndJsonBody() throws Exception {
        // Arrange
        long id = 1L;
        TaskDto dto = new TaskDto(id, "Test Task", false);
        when(taskService.getTaskById(id)).thenReturn(dto);

        // Basic auth for in-memory user (user:password)
        String basicAuth = "Basic dXNlcjpwYXNzd29yZA==";

        // Act + Assert
        mockMvc.perform(get("/tasks/{id}", id)
                        .header("Authorization", basicAuth)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Test Task"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}

