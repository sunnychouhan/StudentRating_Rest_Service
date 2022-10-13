package com.webservice.rating.controller;

import com.webservice.rating.model.StudentScore;
import com.webservice.rating.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@WebMvcTest(ComputeController.class)
class ComputeControllerTest {

    public static final String ELECTRO_FIELDS = "Electro Fields";
    public static final String COMPUTING_TECHNIQUES = "Computing Techniques";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @Test
    void averageStudentScoreTest() throws Exception {

        when(studentRepository.findAll()).thenReturn(getStudents());


        mockMvc.perform(MockMvcRequestBuilders.get("/average/score/student/Ananth"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ananth"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjects").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjects", hasSize(2)));

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void averageSubjectScoreTest() throws Exception {
        when(studentRepository.findAll()).thenReturn(getStudents());

        mockMvc.perform(MockMvcRequestBuilders.get("/average/score/subject/" + ELECTRO_FIELDS))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subject").value(ELECTRO_FIELDS))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentDtos").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentDtos", hasSize(1)));

        verify(studentRepository, times(1)).findAll();
    }

    private List<StudentScore> getStudents() {
        return Arrays.asList(
                StudentScore.builder()
                        .name("Ananth").points(100).category("test_1").studentId(1l).submissionDate(LocalDate.now())
                        .subject(COMPUTING_TECHNIQUES).build(),
                StudentScore.builder()
                        .name("Chaya").points(70).category("lab_1").studentId(2l).submissionDate(LocalDate.now())
                        .subject(COMPUTING_TECHNIQUES).build(),
                StudentScore.builder()
                        .name("Ananth").points(90).category("project_1").studentId(3l).submissionDate(LocalDate.now())
                        .subject(ELECTRO_FIELDS).build());
    }

}