package com.webservice.rating.controller;

import com.webservice.rating.dto.StudentDataResponse;
import com.webservice.rating.model.StudentScore;
import com.webservice.rating.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentEnrollmentController {

    public static final String STUDENT_ENROLLMENT = "student/enrollment";
    public static final String SUCCESS = "SUCCESS";

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(STUDENT_ENROLLMENT)
    public String enrollStudent(@RequestBody StudentScore studentScore) {
        studentRepository.insert(studentScore);
        return SUCCESS;
    }

    @GetMapping(STUDENT_ENROLLMENT)
    public StudentDataResponse showAllEnrollment() {
        List<StudentScore> studentScore = studentRepository.findAll();
        return StudentDataResponse.builder().studentScores(studentScore).build();
    }

    @GetMapping(STUDENT_ENROLLMENT + "/{id}")
    public StudentScore getEnrollmentbyId(@PathVariable("id") String id) {
        StudentScore studentScore = studentRepository.findById(Long.valueOf(id));
        return studentScore;
    }

    @PutMapping(STUDENT_ENROLLMENT)
    public String updateEnrolment(@RequestBody StudentScore studentScore) {
        studentRepository.update(studentScore);
        return SUCCESS;
    }

    @DeleteMapping(STUDENT_ENROLLMENT + "/{id}")
    public String removeEnrollment
            (@PathVariable("id") String id) {
        studentRepository.deleteById(Long.valueOf(id));
        return SUCCESS;
    }
}
