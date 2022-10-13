package com.webservice.rating.controller;


import com.webservice.rating.dto.StudentDto;
import com.webservice.rating.dto.SubjectDto;
import com.webservice.rating.repository.StudentRepository;
import com.webservice.rating.vo.AssignmentVO;
import com.webservice.rating.vo.StudentVO;
import com.webservice.rating.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ComputeController {
    public static final String DEFAULT_POINTS = "100";
    public static final String DEFAULT_SUBMISSION_OCT_2022 = "6-Oct-2022";
    public static final String ONE_1 = "1";
    public static final String TWO_2 = "2";
    public static final String THREE_3 = "3";
    public static final String FOUR_4 = "4";
    public static final String TEST = "test";
    public static final String QUIZ = "quiz";
    public static final String LAB = "lab";
    public static final String PROJECT = "project";

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("average/score/student/{studentName}")
    public StudentDto averageStudentScore(@PathVariable("studentName") String studentName) {
        Map<String, List<StudentVO>> byStudentName =
                getAllStudents()
                        .stream()
                        .filter(studentVO -> studentVO.getName().equals(studentName))
                        .collect(Collectors.groupingBy(studentVO -> studentVO.getSubjects().getSubject()));
        return new StudentDto(getSubjectsByStudentName(byStudentName), studentName);
    }

    @GetMapping("average/score/subject/{subjectName}")
    public SubjectDto averageSubjectScore(@PathVariable("subjectName") String subjectName) {
        Map<String, List<StudentVO>> bySubjectName =
                getAllStudents()
                        .stream()
                        .filter(studentVO -> studentVO.getSubjects().getSubject().equals(subjectName))
                        .collect(Collectors.groupingBy(studentVO -> studentVO.getName()));

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setStudentDtos(getStudentsBySubjectName(bySubjectName));
        subjectDto.setSubject(subjectName);
        return subjectDto;
    }

    public List<StudentDto> getStudentsBySubjectName(Map<String, List<StudentVO>> bySubject) {
        return bySubject.entrySet()
                .stream()
                .map(stringListEntry -> {

                    String students = stringListEntry.getKey();
                    List<StudentVO> subjects = stringListEntry.getValue();

                    ArrayList<Double> test = new ArrayList();
                    ArrayList<Double> quiz = new ArrayList();
                    ArrayList<Double> lab = new ArrayList();
                    ArrayList<Double> project = new ArrayList();
                    subjects.stream()
                            .forEach(stdnt -> {
                                AssignmentVO assignmentVOList = stdnt.getSubjects().getAssignmentList();

                                if (assignmentVOList.getCategory().startsWith(TEST)) {
                                    test.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(QUIZ)) {
                                    quiz.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(LAB)) {
                                    lab.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(PROJECT)) {
                                    project.add(Double.valueOf(assignmentVOList.getPoints()));
                                }
                            });


                    Double testRatings = deriveRating(test, test.size(), 40.0);
                    Double quizRatings = deriveRating(quiz, quiz.size(), 20.0);
                    Double labRatings = deriveRating(lab, lab.size(), 10.0);
                    Double projectRatings = deriveRating(project, project.size(), 30.0);


                    StudentDto dto = new StudentDto();
                    dto.setName(students);

                    dto.setLab(getIfApplicable(lab, labRatings));
                    dto.setQuiz(getIfApplicable(quiz, quizRatings));
                    dto.setTest(getIfApplicable(test, testRatings));
                    dto.setProject(getIfApplicable(project, projectRatings));

                    Double ovrallRatings = labRatings + quizRatings + testRatings + projectRatings;
                    dto.setOverall(String.valueOf(ovrallRatings));
                    return dto;
                }).collect(Collectors.toList());
    }


    public List<SubjectDto> getSubjectsByStudentName(Map<String, List<StudentVO>> bySubject) {

        return bySubject.entrySet()
                .stream()
                .map(sujbectKey -> {
                    String subject = sujbectKey.getKey();
                    List<StudentVO> studentVOS = sujbectKey.getValue();

                    ArrayList<Double> test = new ArrayList();
                    ArrayList<Double> quiz = new ArrayList();
                    ArrayList<Double> lab = new ArrayList();
                    ArrayList<Double> project = new ArrayList();
                    studentVOS.stream()
                            .forEach(studentVO -> {
                                AssignmentVO assignmentVOList = studentVO.getSubjects().getAssignmentList();

                                if (assignmentVOList.getCategory().startsWith(TEST)) {
                                    test.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(QUIZ)) {
                                    quiz.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(LAB)) {
                                    lab.add(Double.valueOf(assignmentVOList.getPoints()));

                                } else if (assignmentVOList.getCategory().startsWith(PROJECT)) {
                                    project.add(Double.valueOf(assignmentVOList.getPoints()));
                                }
                            });


                    Double testRatings = deriveRating(test, test.size(), 40.0);
                    Double quizRatings = deriveRating(quiz, quiz.size(), 20.0);
                    Double labRatings = deriveRating(lab, lab.size(), 10.0);
                    Double projectRatings = deriveRating(project, project.size(), 30.0);


                    SubjectDto dto = new SubjectDto();
                    dto.setSubject(subject);

                    dto.setLab(getIfApplicable(lab, labRatings));
                    dto.setQuiz(getIfApplicable(quiz, quizRatings));
                    dto.setTest(getIfApplicable(test, testRatings));
                    dto.setProject(getIfApplicable(project, projectRatings));

                    Double ovrallRatings = labRatings + quizRatings + testRatings + projectRatings;
                    dto.setOverall(String.valueOf(ovrallRatings));
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public static String getIfApplicable(ArrayList<Double> lab, Double labRatings) {
        return lab.size() > 0 ? String.valueOf(labRatings) : "NA";
    }

    Double deriveRating(ArrayList<Double> assignments, int numberOfAssignments, Double assigmentCategory) {
        return assignments.stream()
                .map(score -> calculatePercentage(numberOfAssignments, score, assigmentCategory))
                .reduce((double) 0, (a, b) -> a + b);
    }


    Double calculatePercentage(int numberOfAssignments, Double points, Double assignmentCategory) {
        return numberOfAssignments != 0 ? (((assignmentCategory / numberOfAssignments) * points) / 100) : 0;
    }

    public List<StudentVO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(score -> {
                    AssignmentVO assignmentVO =
                            new AssignmentVO(score.getCategory(),
                                    String.valueOf(score.getPoints()),
                                    score.getSubmissionDate().toString());
                    SubjectVO subjectVO = new SubjectVO(assignmentVO, score.getSubject());
                    return new StudentVO(subjectVO, score.getName(), String.valueOf(score.getStudentId()));
                })
                .collect(Collectors.toList());
    }

}
