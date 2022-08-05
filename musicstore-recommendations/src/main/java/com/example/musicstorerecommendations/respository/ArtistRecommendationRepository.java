package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Long> {

}




