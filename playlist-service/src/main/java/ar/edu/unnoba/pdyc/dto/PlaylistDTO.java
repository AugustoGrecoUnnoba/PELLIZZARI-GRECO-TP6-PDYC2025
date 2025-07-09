package ar.edu.unnoba.pdyc.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private Long id;
    private String name;
    private List<Long> songIds;

    public PlaylistDTO() {
        this.songIds = new ArrayList<>();
    }

    public PlaylistDTO(Long id, String name) {
        this.id = id;
        this.name = name;
        this.songIds = new ArrayList<>();
    }

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

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

}
