package com.webservice.rating.controller;

import com.webservice.rating.dto.CategoryResponse;
import com.webservice.rating.model.AssignmentCategory;
import com.webservice.rating.repository.AssignmentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AssignmentCategoryController {

    public static final String ASSIGNMENT_CATEGORY = "assignment/category";
    @Autowired
    AssignmentCategoryRepository assignmentCategoryRepository;

    @GetMapping(ASSIGNMENT_CATEGORY)
    public CategoryResponse getAssignmentCategory() {
        List<AssignmentCategory> assignmentCategories = assignmentCategoryRepository.findAll();
        return CategoryResponse.builder()
                .assignmentCategories(assignmentCategories).build();
    }

    @PostMapping(ASSIGNMENT_CATEGORY)
    public String addAssignmentCategory(@RequestBody AssignmentCategory assignmentCategory) {
        assignmentCategoryRepository.insert(assignmentCategory);
        return "SUCCESS";
    }

    @DeleteMapping(ASSIGNMENT_CATEGORY + "/{id}")
    public String removeAssignmentCategory(@PathVariable("id") String id) {
        assignmentCategoryRepository.remove(Long.valueOf(id));
        return "SUCCESS";
    }

}
