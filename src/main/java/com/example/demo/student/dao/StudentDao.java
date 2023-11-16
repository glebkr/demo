package com.example.demo.student.dao;

import com.example.demo.student.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {

    List<Student> selectAllStudents();

    void insertStudent(UUID id, Student student);

    default void insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        insertStudent(id, student);
    }

    void deleteStudentById(UUID id);

    Optional<Student> getStudentById(UUID id);

    void updateStudent(UUID id, Student student);

}
