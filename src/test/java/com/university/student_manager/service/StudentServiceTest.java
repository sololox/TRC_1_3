package com.university.student_manager.service;

import com.university.student_manager.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void shouldCreateStudentWithId() {
        Student student = new Student(null, "Oleg", "Ivanov", "oleg@test.com", 21);

        Student savedStudent = studentService.createStudent(student);

        assertNotNull(savedStudent.getId());
        assertEquals(1L, savedStudent.getId());
        assertEquals("Oleg", savedStudent.getFirstName());
    }

    @Test
    void shouldFindStudentById() {
        Student student = studentService.createStudent(new Student(null, "Ivan", "Sirko", "ivan@sirko.com", 25));

        Optional<Student> found = studentService.getStudentById(student.getId());

        assertTrue(found.isPresent());
        assertEquals("Ivan", found.get().getFirstName());
    }

    @Test
    void shouldDeleteStudent() {
        Student student = studentService.createStudent(new Student(null, "Test", "User", "test@user.com", 18));

        boolean deleted = studentService.deleteStudent(student.getId());

        assertTrue(deleted);
        assertTrue(studentService.getAllStudents().isEmpty());
    }
}