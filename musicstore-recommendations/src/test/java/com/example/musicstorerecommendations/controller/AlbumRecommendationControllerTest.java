package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.AlbumRecommendation;
import com.example.musicstorerecommendations.respository.AlbumRecommendationRepository;
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
@WebMvcTest(AlbumRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumRecommendationControllerTest {
    @MockBean
    private AlbumRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllAlbumRecommendationsShouldReturnListAnd200()throws Exception{
        AlbumRecommendation albumRecommendation =new AlbumRecommendation(1L,1,1,true);
        List<AlbumRecommendation> albumRecommendationList= Arrays.asList(albumRecommendation);
        String expectedJsonValue =mapper.writeValueAsString(albumRecommendationList);
        doReturn(albumRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/albumRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }


    @Test
    public void createAlbumRecommendationShouldReturnNewLabel()throws Exception{
        AlbumRecommendation outputAlbumRecommendation=new AlbumRecommendation(1L,1,1,true);
        AlbumRecommendation inputAlbumRecommendation= new AlbumRecommendation(1,1,true);
        String outputAlbumRecommendationJson=mapper.writeValueAsString(outputAlbumRecommendation);
        String inputAlbumRecommendationJson = mapper.writeValueAsString(inputAlbumRecommendation);
        when(repo.save(inputAlbumRecommendation)).thenReturn(outputAlbumRecommendation);

        mockMvc.perform(post("/albumRecommendations")
                        .content(inputAlbumRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumRecommendationJson));
    }
    @Test
    public void getOneArtistShouldReturn()throws Exception{
        AlbumRecommendation albumRecommendation=new AlbumRecommendation(1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(albumRecommendation);

        doReturn(Optional.of(albumRecommendation)).when(repo).findById(1L);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/albumRecommendations/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation( 1L,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(albumRecommendation);
        mockMvc.perform(
                        put("/albumRecommendations/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation( 1L,1,1,true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/albumRecommendations/1")).andExpect(status().isNoContent());
    }
}