package ar.edu.unnoba.pdyc.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistWithSongsDTO {
    private Long id;
    private String name;
    private List<SongDTO> songs;
    private List<Long> songIds;

    public PlaylistWithSongsDTO(Long id, String name, List<SongDTO> songs, List<Long> songIds) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.songIds = songIds;
    }

    public PlaylistWithSongsDTO(Long id, String name, List<SongDTO> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.songIds = new ArrayList<>();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }
}
