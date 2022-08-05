package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Track;
import com.example.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrackController {
    @Autowired
    private TrackRepository repo;


    @GetMapping("/tracks")
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() {
        return repo.findAll();
    }

    @GetMapping("/tracks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrack(@PathVariable Long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/tracks")
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTrack(@RequestBody Track track) {
        return repo.save(track);
    }

    @PutMapping("/tracks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track updateTrack(@PathVariable Long id, @RequestBody Track track) {
        return repo.save(track);
    }

    @DeleteMapping("/tracks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrack(@PathVariable Long id) {
        repo.deleteById(id);
    }
    }

