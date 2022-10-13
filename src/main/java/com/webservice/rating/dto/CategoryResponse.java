package com.webservice.rating.dto;

import com.webservice.rating.model.AssignmentCategory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryResponse {

    List<AssignmentCategory> assignmentCategories;
}
