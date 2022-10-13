package com.webservice.rating.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto extends CommonDto {
    String name;
    List<SubjectDto> subjects;


    public StudentDto(List<SubjectDto> subjects, String name) {
        this.subjects = subjects;
        this.name = name;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentDto() {
    }
}
