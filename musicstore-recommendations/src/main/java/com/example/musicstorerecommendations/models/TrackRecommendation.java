package com.example.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "track_recommendation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class TrackRecommendation implements Serializable {

    @Id
    @Column(name = "track_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private long userId;

    private Boolean liked;

    public TrackRecommendation(Long id, long userId, Boolean liked) {
        this.id = id;
        this.userId = userId;
        this.liked = liked;
    }

    public TrackRecommendation(long userId, Boolean liked) {
        this.userId = userId;
        this.liked = liked;
    }

    public TrackRecommendation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        TrackRecommendation that = (TrackRecommendation) o;
        return userId == that.userId && Objects.equals(id, that.id) && Objects.equals(liked, that.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, liked);
    }

    @Override
    public String toString() {
        return "TrackRecomendation{" +
                "id=" + id +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
