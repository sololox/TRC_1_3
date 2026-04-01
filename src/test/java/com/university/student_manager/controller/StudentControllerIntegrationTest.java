package com.university.student_manager.controller;

import com.university.student_manager.model.Student;
import com.university.student_manager.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetAllStudents() throws Exception {
        Student student = new Student(1L, "John", "Doe", "john@gmail.com", 20);
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student(1L, "John", "Doe", "john@gmail.com", 20);
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetStudentById_notFound() throws Exception {
        when(studentService.getStudentById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/students/99"))
                .andExpect(status().isInternalServerError());
    }
}