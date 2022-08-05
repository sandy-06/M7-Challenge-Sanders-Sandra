package com.example.musicstorerecommendations.respository;

import com.example.musicstorerecommendations.models.AlbumRecomendation;

import java.util.List;

public interface AlbumRecomendationRepository {
    List<AlbumRecomendation> findAll();
}
