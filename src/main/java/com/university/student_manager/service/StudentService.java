package com.university.student_manager.service;

import com.university.student_manager.model.Student;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    public List <Student> getAllStudents(){
        return new ArrayList<>(students);
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Student createStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return getStudentById(id).map(existingStudent -> {
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setAge(updatedStudent.getAge());
            return existingStudent;
        });
    }

    public boolean deleteStudent(Long id) {
        return students.removeIf(s -> s.getId().equals(id));
    }
}
