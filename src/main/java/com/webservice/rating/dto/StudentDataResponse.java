package com.webservice.rating.dto;

import com.webservice.rating.model.StudentScore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class StudentDataResponse {

    List<StudentScore> studentScores;
}
