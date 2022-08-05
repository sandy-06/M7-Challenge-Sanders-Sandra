package com.example.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label_recommendation")
public class LabelRecomendation implements Serializable {
    @Id
    @Column(name = "label_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "label_id")
    private long labelId;

    @Column(name = "artist_id")
    private long artistId;

    private Boolean liked;

    public LabelRecomendation(Long id, long labelId, long artistId, Boolean liked) {
        this.id = id;
        this.labelId = labelId;
        this.artistId = artistId;
        this.liked = liked;
    }

    public LabelRecomendation(long labelId, long artistId, Boolean liked) {
        this.labelId = labelId;
        this.artistId = artistId;
        this.liked = liked;
    }

    public LabelRecomendation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
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
        LabelRecomendation that = (LabelRecomendation) o;
        return labelId == that.labelId && artistId == that.artistId && Objects.equals(id, that.id) && Objects.equals(liked, that.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, labelId, artistId, liked);
    }

    @Override
    public String toString() {
        return "LabelRecomendation{" +
                "id=" + id +
                ", labelId=" + labelId +
                ", artistId=" + artistId +
                ", liked=" + liked +
                '}';
    }
}
