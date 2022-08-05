package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.models.AlbumRecomendation;
import com.example.musicstorerecommendations.respository.AlbumRecomendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumRecomendationController {

    private AlbumRecomendationRepository repo;

    @Autowired
    public AlbumRecomendationController(AlbumRecomendationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/album_recommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecomendation> getAllAlbums() {

        return repo.findAll();
        }

    @GetMapping("/albumRecomendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecomendation getAlbumRecomendation(@PathVariable long id) {
        return repo.findById(id).get();
    }


}