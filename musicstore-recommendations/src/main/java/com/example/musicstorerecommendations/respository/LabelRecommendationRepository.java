package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRecommendationRepository extends JpaRepository<LabelRecommendation, Long> {

}

