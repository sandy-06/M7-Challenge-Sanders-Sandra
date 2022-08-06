package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Artist;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import com.fasterxml.jackson.datatype:jackson-datatype-jsr310;
import static org.mockito.Mockito.doReturn;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ArtistControllerTest {

    @MockBean
    private ArtistRepository  artistRepo;

    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private LabelRepository labelRepository;
    @MockBean
    private TrackRepository trackRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        setupArtistRepositoryMock();
    }

    private void setupArtistRepositoryMock() {
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");
        Artist artistWithoutId = new Artist("Billy Joel", "@billyjoel", "@billyrock");
        List<Artist> artistList = Arrays.asList(artist);
        doReturn(artistList).when(artistRepo).findAll();

        doReturn(artist).when(artistRepo).save(artistWithoutId);
    }

    @Test
    public void testGetAllArtists() throws Exception {

        //Arrange
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");
        List<Artist> artistList = Arrays.asList(artist);

        //Act
        //Assert
        mockMvc.perform(get("/artists"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(artistList)));
    }

    @Test
    public void createArtist() throws Exception {
        //Arrange
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");
        Artist artistWithoutId = new Artist("Billy Joel", "@billyjoel", "@billyrock");
        List<Artist> artistList = Arrays.asList(artist);
        doReturn(artist).when(artistRepo).save(artistWithoutId);
        //Act
        //Assert
        mockMvc.perform(post("/artists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(artistWithoutId)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(artist)));
    }

    @Test
    public void updateArtist() throws Exception {
        //Arrange
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");

        List<Artist> artistList = Arrays.asList(artist);
        //doReturn(artist).when(repository).save(artist);
        //Act
        //Assert
        mockMvc.perform(put("/artists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(artist)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getArtistById() throws Exception {
        //Arrange
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");

        //Act
        //Assert
        doReturn (Optional.of(artist)).when(artistRepo).findById(1L);
        mockMvc.perform(get("/artists/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(artist)));
    }

    @Test

    public void deleteArtist() throws Exception {
        //Arrange
        Artist artist = new Artist(1L, "Billy Joel", "@billyjoel", "@billyrock");
        List<Artist> artistList = Arrays.asList(artist);
        //doReturn(artist).when(repository).save(artist);
        //Act
        //Assert
        mockMvc.perform(delete("/artists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(artist)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllArtists() {
    }








}