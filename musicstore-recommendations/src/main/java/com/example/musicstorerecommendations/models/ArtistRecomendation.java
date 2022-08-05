package com.example.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist_recommendation")
public class ArtistRecomendation implements Serializable {
    @Id
    @Column(name = "artist_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "artist_id")
    private long artistId;
    @Column(name = "u")
    private long userId;
    private Boolean liked;

    public ArtistRecomendation(Long id, long artistId, long userId, Boolean liked) {
        this.id = id;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public ArtistRecomendation(long artistId, long userId, Boolean liked) {
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public ArtistRecomendation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistRecomendation that = (ArtistRecomendation) o;
        return artistId == that.artistId && userId == that.userId && Objects.equals(id, that.id) && Objects.equals(liked, that.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artistId, userId, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecomendation{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}