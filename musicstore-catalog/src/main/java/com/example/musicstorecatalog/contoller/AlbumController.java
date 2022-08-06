package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Album;
import com.example.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {
    private AlbumRepository repo;
    @Autowired
    public AlbumController(AlbumRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {

        return repo.findAll();
        }


    @GetMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbum(@PathVariable Long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/albums")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) {
        return repo.save(album);
    }

    @PutMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        return repo.save(album);
    }


    @DeleteMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
