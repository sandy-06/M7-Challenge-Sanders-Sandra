package com.example.musicstorecatalog.repository;

import com.example.musicstorecatalog.models.Album;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository repo;

    @Before
    public void setUp() throws Exception {
        Album album = new Album();
        album.setTitle("Test Album");
        album.setReleaseDate(LocalDate.now());

        repo.save(album);
    }

    @Test
    public void shouldInteractWithAlbumTableInDatabase() {
        // Arrange
        // make a album
        Album album = new Album(2L, "Best of Billy Joel", 23L, LocalDate.of(2019, 1, 1), 11L, 12.99);
        Album expectedAlbum = new Album(2L, "Best of Billy Joel", 23L, LocalDate.of(2019, 1, 1), 11L, 12.99);
        // Act
        repo.save(album);
        expectedAlbum.setId(album.getId());

        // Assert
        assertEquals(expectedAlbum, album);

        // Act
        List<Album> allTheAlbum = repo.findAll();

        // Assert
        assertEquals(1, allTheAlbum.size());

        // Act
        repo.deleteById(album.getId());

        allTheAlbum = repo.findAll();
        assertEquals(0, allTheAlbum.size());
    }

    @After
    public void tearDown() throws Exception {
    }
}