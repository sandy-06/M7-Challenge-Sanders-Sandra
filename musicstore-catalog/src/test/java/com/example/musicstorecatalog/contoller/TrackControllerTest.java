package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Album;

import com.example.musicstorecatalog.models.Track;
import com.example.musicstorecatalog.repository.AlbumRepository;

import com.example.musicstorecatalog.repository.ArtistRepository;
import com.example.musicstorecatalog.repository.LabelRepository;
import com.example.musicstorecatalog.repository.TrackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackControllerTest {

    @MockBean
    private AlbumRepository repo;
    @MockBean
    private ArtistRepository artistRepo;
    @MockBean
    private LabelRepository labelRepo;
    @MockBean
    private TrackRepository trackRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setUp() throws Exception {
        setUpTrackRepositoryMock();
    }
    private void setUpTrackRepositoryMock() {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        when(trackRepo.findAll()).thenReturn(tracks);
        when(trackRepo.findById(2L)).thenReturn(Optional.of(track));
        when(trackRepo.save(track)).thenReturn(track);
    }

    @Test
    public void testGetAllTracks() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        ResultActions resultActions = mockMvc.perform(get("/tracks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testGetTrackById() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        ResultActions resultActions = mockMvc.perform(get("/tracks/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testCreateTrack() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        ResultActions resultActions = mockMvc.perform(post("/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(track)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testUpdateTrack() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        ResultActions resultActions = mockMvc.perform(put("/tracks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(track)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testDeleteTrack() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        List<Track> tracks = Arrays.asList(track);
        ResultActions resultActions = mockMvc.perform(delete("/tracks/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllTracks() {
    }

    @Test
    public void getTrack() {
    }

    @Test
    public void createTrack() {
    }

    @Test
    public void updateTrack() {
    }

    @Test
    public void deleteTrack() {
    }
}