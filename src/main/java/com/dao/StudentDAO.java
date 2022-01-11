package com.dao;

import com.model.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);
    List<Student> list();
    void deleteStudent(int id);
    Student get(int id);
    void update(Student student);
}
