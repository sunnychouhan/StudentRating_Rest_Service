package com.webservice.rating.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentScore {
    Long studentId;
    String name;
    String subject;
    String category;
    LocalDate submissionDate;
    Integer points;
}
