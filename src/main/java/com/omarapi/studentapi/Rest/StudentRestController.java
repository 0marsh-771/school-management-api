package com.omarapi.studentapi.Rest;

import com.omarapi.studentapi.Entity.Student;
import com.omarapi.studentapi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentService;

    private JsonMapper jsonMapper;

    @Autowired
    public StudentRestController(StudentService theStudentService, JsonMapper theJsonMapper){
        studentService = theStudentService;
        jsonMapper = theJsonMapper;
    }

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        Student theStudent = studentService.findById(studentId);

        if(theStudent == null){
            throw new RuntimeException("Could not find the Student " + studentId);
        }

        return theStudent;

    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student theStudent){

        theStudent.setId(0);

        Student dbStudent = studentService.save(theStudent);

        return dbStudent;

    }

    @PatchMapping("/students/{studentId}")
    public Student patchStudent(@PathVariable int studentId,
                                 @RequestBody Map<String, Object> patchPayload){

        Student tempStudent = studentService.findById(studentId);

        if(tempStudent == null){
            throw new RuntimeException("Student not found " + studentId);
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Patch body cannot contain id " + studentId);
        }

        Student patchStudent = jsonMapper.updateValue(tempStudent, patchPayload);

        Student dbStudent = studentService.save(patchStudent);

        return dbStudent;

    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){

        Student dbStudent = studentService.save(theStudent);

        return dbStudent;

    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){

        Student tempStudent = studentService.findById(studentId);

        if(tempStudent == null){
            throw new RuntimeException("Could not find the Student " + studentId);
        }

        studentService.deleteById(studentId);

        return "Deleted the Student " + studentId;

    }

}
