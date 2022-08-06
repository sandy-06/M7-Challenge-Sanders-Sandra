package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.ArtistRecommendation;
import com.example.musicstorerecommendations.respository.ArtistRecommendationRepository;
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
@WebMvcTest(ArtistRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistRecommendationControllerTest {
    @MockBean
    private ArtistRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllArtistRecommendationsShouldReturnListAnd200()throws Exception{
        ArtistRecommendation artistRecommendation =new ArtistRecommendation(1L,1,1,true);
        List<ArtistRecommendation> artistRecommendationList= Arrays.asList(artistRecommendation);
        String expectedJsonValue =mapper.writeValueAsString(artistRecommendationList);
        doReturn(artistRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/artistRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }


    @Test
    public void createArtistRecommendationShouldReturnNewLabel()throws Exception{
        ArtistRecommendation outputArtistRecommendation=new ArtistRecommendation(1L,1,1,true);
        ArtistRecommendation inputArtistRecommendation= new ArtistRecommendation(1,1,true);
        String outputArtistRecommendationJson=mapper.writeValueAsString(outputArtistRecommendation);
        String inputArtistRecommendationJson = mapper.writeValueAsString(inputArtistRecommendation);
        when(repo.save(inputArtistRecommendation)).thenReturn(outputArtistRecommendation);

        mockMvc.perform(post("/artistRecommendations")
                        .content(inputArtistRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistRecommendationJson));
    }
    @Test
    public void getOneArtistShouldReturn()throws Exception{
        ArtistRecommendation artistRecommendation=new ArtistRecommendation(1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(artistRecommendation);

        doReturn(Optional.of(artistRecommendation)).when(repo).findById(1L);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/artistRecommendations/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation( 1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(artistRecommendation);
        mockMvc.perform(
                        put("/artistRecommendations/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation( 1L,1,1,true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/artistRecommendations/1")).andExpect(status().isNoContent());
    }
}