package com.omarapi.studentapi.Service;

import com.omarapi.studentapi.Entity.Student;
import com.omarapi.studentapi.Repository.StudentJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentJpaRepository studentRepository;

    public StudentServiceImpl(StudentJpaRepository theRepository){
        studentRepository = theRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {

        Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;

        if(result.isPresent()){
            theStudent = result.get();
        }
        else {
            throw new RuntimeException("Could not find the student " + theId);
        }

        return theStudent;

    }

    @Override
    @Transactional
    public Student save(Student theStudent) {
        return studentRepository.save(theStudent);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        studentRepository.deleteById(theId);

    }
}
