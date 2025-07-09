package ar.edu.unnoba.pdyc.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaylistSong> playlistSongs = new ArrayList<>();

    // Constructores
    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
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

    public List<PlaylistSong> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<PlaylistSong> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public void addSong(Long songId) {
        PlaylistSong playlistSong = new PlaylistSong(this, songId);
        playlistSongs.add(playlistSong);
    }
}
