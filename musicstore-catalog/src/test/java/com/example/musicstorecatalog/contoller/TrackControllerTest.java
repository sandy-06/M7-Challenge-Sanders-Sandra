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
import java.util.ArrayList;
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
@WebMvcTest(TrackController.class)
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

//   String trackJson;
//    List<Track> trackList = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
       setUpTrackRepositoryMock();
//        Track track = new Track(2L,4L, "Fun, Fun,Fun", 3);
//        trackJson = mapper.writeValueAsString(track);
//        trackList.add(track);
    }
   private void setUpTrackRepositoryMock() {
       Track track = new Track(2L,4L, "Fun, Fun,Fun", 3);
        Track trackWithoutId = new Track( 4L,"Fun, Fun,Fun", 3);
        List<Track> trackList = Arrays.asList(track);
        doReturn(trackList).when(trackRepo).findAll();

        doReturn(track).when(trackRepo).save(track);
    }

    @Test
    public void testGetAllTracks() throws Exception {
        Track track = new Track(2L, 4L, "Fun, Fun,Fun", 3);
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        doReturn(trackList).when(trackRepo).findAll();
        mockMvc.perform(get("/tracks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(trackList)));
    }
    @Test
    public void testGetTrackById() throws Exception {
        Track track = new Track(2L,2L, "Fun, Fun,Fun", 3);
        doReturn(Optional.of(track)).when(trackRepo).findById(2L);
        mockMvc.perform(get("/tracks/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testCreateTrack() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        doReturn(track).when(trackRepo).save(track);
        mockMvc.perform(post("/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(track)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(track)));
    }
    @Test
    public void testUpdateTrack() throws Exception {
        Track track = new Track(2L, "Fun, Fun,Fun", 3);
        mockMvc.perform(put("/tracks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(track)))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void testDeleteTrack() throws Exception {
        mockMvc.perform(delete("/tracks/2"))
                .andDo(print())
                .andExpect(status().isOk());
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