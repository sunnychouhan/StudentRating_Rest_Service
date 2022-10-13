package com.webservice.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentCategory {
    Long categoryId;
    String categoryType;
    Integer categoryWeight;
}
