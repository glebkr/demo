package com.example.demo.student.service;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void insertStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(UUID id, Student student) {
        Student studentFromDb = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("The student wasn't found"));
        studentFromDb.setName(student.getName());
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public void deleteStudentById(UUID id) {
        studentRepository.deleteById(id);
    }

}
