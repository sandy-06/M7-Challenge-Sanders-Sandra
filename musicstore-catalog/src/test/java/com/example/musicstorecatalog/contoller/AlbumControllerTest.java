package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Album;
import com.example.musicstorecatalog.models.Label;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import com.fasterxml.jackson.datatype:jackson-datatype-jsr310;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AlbumControllerTest {

    @MockBean
    private AlbumRepository repository;
    @MockBean
    private ArtistRepository artistRepository;
    @MockBean
    private LabelRepository labelRepository;
    @MockBean
    private TrackRepository trackRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper = new ObjectMapper();
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);
    @Before
    public void setUp() throws Exception {
        setupAlbumRepositoryMock();
    }
    private void setupAlbumRepositoryMock() {
        LocalDate releaseDate;
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);

        Album album = new Album(1L, "Best of Billy Joel", 50L,date, 24l, new BigDecimal("1.19"));
        Album albumWithoutId = new Album("Best of Billy Joel", 50L,date, 24l, new BigDecimal("1.19"));
        List<Album> albumList = Arrays.asList(album);

        doReturn(albumList).when(repository).findAll();
        doReturn(album).when(repository).save(albumWithoutId);
    }

    @Test
    public void getAllAlbumsShouldReturnAListAnd200() throws Exception {
        LocalDate releaseDate;

        // Arrange
        Album album = new Album(121L, "Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
        List<Album> albumList = Arrays.asList(album);

        // Act
        // Assert
        mockMvc.perform(get("/albums"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(albumList)));
    }

    @Test
    public void createAlbumShouldReturnTheAlbumAnd201() throws Exception {
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate releaseDate;
        // Arrange
        Album album = new Album(121L, "Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
        Album albumWithoutId = new Album("Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
        List<Album> albumList = Arrays.asList(album);
        // Act
        // Assert
        mockMvc.perform(post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(albumWithoutId)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(album)));
    }

    @Test

    public void updateAlbumShouldReturnTheAlbumAnd200() throws Exception {
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate releaseDate;
        // Arrange
        Album album = new Album(121L, "Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
        List<Album> albumList = Arrays.asList(album);
        // Act
        // Assert
        mockMvc.perform(put("/albums/121")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(album)))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test

    public void deleteAlbumShouldReturn204() throws Exception {
        // Arrange
        Album album = new Album(121L, "Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
        List<Album> albumList = Arrays.asList(album);
        // Act
        // Assert
        mockMvc.perform(delete("/albums/121"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
//    public void getAlbumById() throws Exception {
//        LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);
//        // Arrange
//        Album album = new Album(121L, "Best of Billy Joel", 50L, date, 24L, new BigDecimal("1.19"));
//        doReturn (Optional.of(album)).when(repository).findById(121L);
//        // Assert
//        mockMvc.perform(get("/albums/121"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(mapper.writeValueAsString(album)));
//    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAlbums() {
    }

    @Test
    public void getAlbum() {
    }

    @Test
    public void createAlbum() {
    }

    @Test
    public void updateAlbum() {
    }

    @Test
    public void deleteAlbum() {
    }
}