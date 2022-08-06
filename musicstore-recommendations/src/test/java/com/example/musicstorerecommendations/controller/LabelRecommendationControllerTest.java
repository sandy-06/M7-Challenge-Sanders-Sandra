package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.LabelRecommendation;
import com.example.musicstorerecommendations.respository.LabelRecommendationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelRecommendationControllerTest {
    @MockBean
    private LabelRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllLabelRecommendationsShouldReturnListAnd200()throws Exception{
        LabelRecommendation labelRecommendation =new LabelRecommendation(1L,1,1,true);
        List<LabelRecommendation> labelRecommendationList= Arrays.asList(labelRecommendation);
        String expectedJsonValue =mapper.writeValueAsString(labelRecommendationList);
        doReturn(labelRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/labelRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }


    @Test
    public void createLabelRecommendationShouldReturnNewLabel()throws Exception{
        LabelRecommendation outputLabelRecommendation=new LabelRecommendation(1L,1,1,true);
        LabelRecommendation inputLabelRecommendation= new LabelRecommendation(1,1,true);
        String outputLabelRecommendationJson=mapper.writeValueAsString(outputLabelRecommendation);
        String inputLabelRecommendationJson = mapper.writeValueAsString(inputLabelRecommendation);
        when(repo.save(inputLabelRecommendation)).thenReturn(outputLabelRecommendation);

        mockMvc.perform(post("/labelRecommendations")
                        .content(inputLabelRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelRecommendationJson));
    }
    @Test
    public void getOneArtistShouldReturn()throws Exception{
        LabelRecommendation labelRecommendation=new LabelRecommendation(1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(labelRecommendation);

        doReturn(Optional.of(labelRecommendation)).when(repo).findById(1L);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/labelRecommendations/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        LabelRecommendation labelRecommendation = new LabelRecommendation( 1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(labelRecommendation);
        mockMvc.perform(
                        put("/labelRecommendations/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        LabelRecommendation labelRecommendation = new LabelRecommendation( 1L,1,1,true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/labelRecommendations/1")).andExpect(status().isNoContent());
    }


}