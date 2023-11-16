package com.example.demo.student.dao;

import com.example.demo.person.model.Person;
import com.example.demo.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("studentPsql")
public class StudentDataAccessService implements StudentDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> selectAllStudents() {
        final String sql = "SELECT id, name FROM student";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Student(id, name);
        });
    }

    @Override
    public void insertStudent(UUID id, Student student) {
        final String sql = "INSERT INTO student (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, student.name());
    }

    @Override
    public void deleteStudentById(UUID id) {
        final String sql = "DELETE FROM student WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Student> getStudentById(UUID id) {
        final String sql = "SELECT id, name FROM student WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(
                sql, (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Student(personId, name);
                }, id);
        return Optional.ofNullable(student);
    }

    @Override
    public void updateStudent(UUID id, Student student) {
        final String sql = "UPDATE student SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.name(), id);
    }


}
