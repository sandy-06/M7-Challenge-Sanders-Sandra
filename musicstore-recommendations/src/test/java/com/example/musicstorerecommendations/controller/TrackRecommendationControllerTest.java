package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.TrackRecommendation;
import com.example.musicstorerecommendations.respository.TrackRecommendationRepository;;
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
@WebMvcTest(TrackRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackRecommendationControllerTest {
    @MockBean
    private TrackRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;


    @Test
    public void getAllTrackRecommendationsShouldReturnListAnd200() throws Exception {
        TrackRecommendation trackRecommendation = new TrackRecommendation(1L, 1, 1, true);
        List<TrackRecommendation> trackRecommendationList = Arrays.asList(trackRecommendation);
        String expectedJsonValue = mapper.writeValueAsString(trackRecommendationList);
        doReturn(trackRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/trackRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }


    @Test
    public void createTrackRecommendationShouldReturnNewLabel() throws Exception {
        TrackRecommendation outputTrackRecommendation = new TrackRecommendation(1L, 1, 1, true);
        TrackRecommendation inputTrackRecommendation = new TrackRecommendation(1, 1, true);
        String outputTrackRecommendationJson = mapper.writeValueAsString(outputTrackRecommendation);
        String inputTrackRecommendationJson = mapper.writeValueAsString(inputTrackRecommendation);
        when(repo.save(inputTrackRecommendation)).thenReturn(outputTrackRecommendation);

        mockMvc.perform(post("/trackRecommendations")
                        .content(inputTrackRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackRecommendationJson));
    }

    @Test
    public void getOneArtistShouldReturn() throws Exception {
        TrackRecommendation trackRecommendation = new TrackRecommendation(1L, 1, 1, true);
        String expectedJsonValue = mapper.writeValueAsString(trackRecommendation);

        doReturn(Optional.of(trackRecommendation)).when(repo).findById(1L);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/trackRecommendations/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    }

    ;


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        TrackRecommendation trackRecommendation = new TrackRecommendation(1L, 1, 1, true);
        String expectedJsonValue = mapper.writeValueAsString(trackRecommendation);
        mockMvc.perform(
                        put("/trackRecommendations/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        TrackRecommendation trackRecommendation = new TrackRecommendation(1L, 1, 1, true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/trackRecommendations/1")).andExpect(status().isNoContent());
    }


}