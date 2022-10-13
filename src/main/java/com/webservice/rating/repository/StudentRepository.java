package com.webservice.rating.repository;

import com.webservice.rating.model.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public StudentScore findById(Long id) {
        return jdbcTemplate.queryForObject("select * from Student_Score where STUDENT_ID=?",
                new BeanPropertyRowMapper<>(StudentScore.class), id);
    }

    public List<StudentScore> findAll() {
        BeanPropertyRowMapper<StudentScore> rowMapper = BeanPropertyRowMapper.newInstance(StudentScore.class);
        return jdbcTemplate.query("select * from Student_Score", rowMapper);
    }

    public int insert(StudentScore studentScore) {
        return jdbcTemplate.update("insert into Student_Score (STUDENT_ID,NAME,SUBJECT,CATEGORY,SUBMISSION_DATE,POINTS)"
                        + "values(?, ?, ?, ?, ?, ?)",
                studentScore.getStudentId(),
                studentScore.getName(),
                studentScore.getSubject(),
                studentScore.getCategory(),
                studentScore.getSubmissionDate(),
                studentScore.getPoints()
        );
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from Student_Score where STUDENT_ID=?", id);
    }

    public int update(StudentScore studentScore) {
        return jdbcTemplate.update("update Student_Score set  name =?, subject=?, category=?, submissionDate=?, points=?",
                studentScore.getName(),
                studentScore.getSubject(),
                studentScore.getCategory(),
                studentScore.getSubmissionDate(),
                studentScore.getPoints()
        );
    }
}
