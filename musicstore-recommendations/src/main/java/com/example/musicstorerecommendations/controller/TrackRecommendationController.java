package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.models.LabelRecommendation;
import com.example.musicstorerecommendations.models.TrackRecommendation;
import com.example.musicstorerecommendations.respository.LabelRecommendationRepository;
import com.example.musicstorerecommendations.respository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TrackRecommendationController {
    private TrackRecommendationRepository repo;

    @Autowired
    public TrackRecommendationController(TrackRecommendationRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/trackRecommendation")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllAlbums() {

        return repo.findAll();
    }

    @GetMapping("/trackRecommendation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendation(@PathVariable long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/trackRecommendation")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {
        return repo.save(trackRecommendation);
    }

    @PutMapping("/trackRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation updateTrackRecommendation(@PathVariable long id, @RequestBody TrackRecommendation trackRecommendation) {
        return repo.save(trackRecommendation);
    }

    @DeleteMapping("/trackRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable long id) {
        repo.deleteById(id);
    }




}
