package com.omarapi.studentapi.Service;

import com.omarapi.studentapi.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(int theId);

    Student save(Student theStudent);

    public void deleteById(int theId);
}
