package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.AlbumRecommendation;

import java.util.List;

public interface AlbumRecommendationRepository {
    List<AlbumRecommendation> findAll();

    List<AlbumRecommendation> findById(long id);

    void deleteById(long id);

    AlbumRecommendation save(AlbumRecommendation albumRecommendation);
}
