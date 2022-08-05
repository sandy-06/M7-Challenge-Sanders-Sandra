package com.example.musicstorecatalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track")
public class Track  implements Serializable {

    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "album_id")
    private long albumId;

    private String title;

    @Column(name = "run_time")
    private int runtime;

    public Track(long id, long albumId, String title, int runtime) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.runtime = runtime;
    }

    public Track(long albumId, String title, int runtime) {
        this.albumId = albumId;
        this.title = title;
        this.runtime = runtime;
    }
    public Track() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id && albumId == track.albumId && runtime == track.runtime && Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, title, runtime);
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                '}';
    }
}