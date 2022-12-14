package com.webservice.rating.vo;


public class StudentVO {
    SubjectVO subjects;
    String name;

    String serialNumber;

    public StudentVO(SubjectVO subjects, String name, String serialNumber) {
        this.subjects = subjects;
        this.name = name;
        this.serialNumber= serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectVO getSubjects() {
        return subjects;
    }

    public void setSubjects(SubjectVO subjects) {
        this.subjects = subjects;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
