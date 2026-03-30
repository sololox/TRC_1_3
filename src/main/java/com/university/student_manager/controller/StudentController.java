package com.university.student_manager.controller;

import com.university.student_manager.model.Student;
import com.university.student_manager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
}