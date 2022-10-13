package com.webservice.rating.repository;

import com.webservice.rating.model.AssignmentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentCategoryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public AssignmentCategory findById(Long id) {
        return jdbcTemplate.queryForObject("select * from ASSIGNMENT_CATEGORY where STUDENT_ID=?",
                new BeanPropertyRowMapper<>(AssignmentCategory.class), id);
    }

    public List<AssignmentCategory> findAll() {
        BeanPropertyRowMapper<AssignmentCategory> rowMapper = BeanPropertyRowMapper.newInstance(AssignmentCategory.class);
        return jdbcTemplate.query("select * from ASSIGNMENT_CATEGORY", rowMapper);
    }


    public int insert(AssignmentCategory assignmentCategory) {
        return jdbcTemplate.update("insert into ASSIGNMENT_CATEGORY (CATEGORY_ID,CATEGORY_TYPE,CATEGORY_WEIGHT)"
                        + "values(?, ?, ?)",
                assignmentCategory.getCategoryId(),
                assignmentCategory.getCategoryType(),
                assignmentCategory.getCategoryWeight()
        );
    }

    public int remove(Long id) {
        return jdbcTemplate.update("delete from ASSIGNMENT_CATEGORY where CATEGORY_ID=?", id);
    }
}
