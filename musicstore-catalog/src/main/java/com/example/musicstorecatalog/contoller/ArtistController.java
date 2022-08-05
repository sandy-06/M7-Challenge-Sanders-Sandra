package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Artist;
import com.example.musicstorecatalog.repository.AlbumRepository;
import com.example.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {
    private ArtistRepository repo;
    @Autowired
    public ArtistController(ArtistRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/artists")
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return repo.findAll();
    }

    @GetMapping("/artists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtist(@PathVariable Long id) {
            return repo.findById(id).get();
    }

    @PostMapping("/artists")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) {
        return repo.save(artist);
    }

    @PutMapping("/artists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return repo.save(artist);
    }

    @DeleteMapping("/artists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArtist(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

