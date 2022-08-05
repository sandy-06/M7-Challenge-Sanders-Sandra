package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Long> {

}

