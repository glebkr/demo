
package com.example.demo.student.api;

import com.example.demo.student.model.Student;
import com.example.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "/api/v1/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
    }

    @GetMapping(path = "{id}")
    public Optional<Student> getStudentById(@PathVariable("id") UUID id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") UUID id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

}
