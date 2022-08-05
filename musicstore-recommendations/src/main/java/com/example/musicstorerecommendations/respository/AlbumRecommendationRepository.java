package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Long> {

}

