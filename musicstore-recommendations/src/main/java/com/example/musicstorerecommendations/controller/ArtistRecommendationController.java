package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.models.ArtistRecommendation;
import com.example.musicstorerecommendations.respository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistRecommendationController {
    @Autowired
    private ArtistRecommendationRepository repo;


   // public ArtistRecommendationController(ArtistRecommendationRepository repo) {
    //    this.repo = repo;
  //  }

    @GetMapping("/artistRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtists() {

        return repo.findAll();
        }

    @RequestMapping(value = "/artistRecommendation/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ArtistRecommendation findOneArtistRecommendation(@PathVariable Long id){
        Optional<ArtistRecommendation> artist = repo.findById(id);

        if (artist.isPresent() == false) {

            throw new IllegalArgumentException("invalid id");

        } else {

            return artist.get();
        }
    }

    @PostMapping("/artistRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation createArtistRecommendation(@RequestBody ArtistRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    @PutMapping("/artistRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation updateArtistRecommendation(@PathVariable long id, @RequestBody ArtistRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    @DeleteMapping("/artistRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable long id) {
        repo.deleteById(id);
    }
}
