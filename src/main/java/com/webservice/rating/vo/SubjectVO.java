package com.webservice.rating.vo;

public class SubjectVO {
    AssignmentVO assignmentVOList;
    String subject;

    public SubjectVO(AssignmentVO assignmentVOList, String subject) {
        this.assignmentVOList = assignmentVOList;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public AssignmentVO getAssignmentList() {
        return assignmentVOList;
    }

    public void setAssignmentList(AssignmentVO assignmentVOList) {
        this.assignmentVOList = assignmentVOList;
    }
}
