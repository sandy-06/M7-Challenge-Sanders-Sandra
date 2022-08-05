package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.models.AlbumRecommendation;

import com.example.musicstorerecommendations.respository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumRecommendationController {

    private AlbumRecommendationRepository repo;

    @Autowired
    public AlbumRecommendationController(AlbumRecommendationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/albumRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbums() {

        return repo.findAll();
        }

    @GetMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendation(@PathVariable long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/albumRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@RequestBody AlbumRecommendation albumRecommendation) {
        return repo.save(albumRecommendation);
    }

    @PutMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation updateAlbumRecommendation(@PathVariable long id, @RequestBody AlbumRecommendation albumRecommendation) {
        return repo.save(albumRecommendation);
    }

    @DeleteMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable long id) {
        repo.deleteById(id);
    }




}