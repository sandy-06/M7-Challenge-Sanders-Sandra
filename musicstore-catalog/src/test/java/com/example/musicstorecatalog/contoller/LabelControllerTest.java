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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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


import static java.awt.Color.orange;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import com.fasterxml.jackson.datatype:jackson-datatype-jsr310;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelControllerTest {

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
    LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);


    @Before
    public void setUp() throws Exception {
        setupLabelRepositoryMock();
    }

    private void setupLabelRepositoryMock() {

        Label label = new Label( 2L, "Sony", "www.sony.com");
        Label labelWithoutId = new Label("Sony","www.sony.com");
        List<Label> labelList = Arrays.asList(label);

        doReturn(label).when(labelRepo).save(label);
        doReturn(labelList).when(repo).findAll();
    }

    @Test
    public void testGetAllLabels() throws Exception {
        Label label = new Label(2L, "Sony", "sony.com");
        List<Label> labelList = Arrays.asList(label);

        mockMvc.perform(get("/labels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(labelList)));
    }

    @Test
    public void testGetLabelById() throws Exception {
        Label label = new Label(2L, "Sony", "sony.com");
        mockMvc.perform(get("/labels/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(label)));
    }
    @Test
    public void createLabel() throws Exception {
        Label label = new Label(2L, "Sony", "sony.com");
        mockMvc.perform(post("/labels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(label)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(label)));
    }
    @Test
    public void updateLabel() throws Exception {
        Label label = new Label(2L, "Sony", "sony.com");
        mockMvc.perform(put("/labels/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(label)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(label)));
    }
    @Test
    public void deleteLabel() throws Exception {
        mockMvc.perform(delete("/labels/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllLabels() {
    }

    @Test
    public void getLabel() {
    }






}