package com.webservice.rating.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto extends CommonDto {
    String subject;
    List<StudentDto> studentDtos;

    public List<StudentDto> getStudentDtos() {
        return studentDtos;
    }

    public void setStudentDtos(List<StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
