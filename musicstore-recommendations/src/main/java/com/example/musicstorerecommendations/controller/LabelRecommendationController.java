package com.example.musicstorerecommendations.controller;


import com.example.musicstorerecommendations.models.LabelRecommendation;

import com.example.musicstorerecommendations.respository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelRecommendationController {

    private LabelRecommendationRepository repo;

    @Autowired
    public LabelRecommendationController(LabelRecommendationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/labelRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllAlbums() {

        return repo.findAll();
    }

    @GetMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendation(@PathVariable long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/labelRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@RequestBody LabelRecommendation labelRecommendation) {
        return repo.save(labelRecommendation);
    }

    @PutMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation updateLabelRecommendation(@PathVariable long id, @RequestBody LabelRecommendation labelRecommendation) {
        return repo.save(labelRecommendation);
    }

    @DeleteMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable long id) {
        repo.deleteById(id);
    }





}
