package com.example.demo.student.service;

import com.example.demo.student.dao.StudentDao;
import com.example.demo.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("studentPsql") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    public void updateStudent(UUID id, Student student) {
        studentDao.updateStudent(id, student);
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentDao.getStudentById(id);
    }

    public void deleteStudentById(UUID id) {
        studentDao.deleteStudentById(id);
    }

}
