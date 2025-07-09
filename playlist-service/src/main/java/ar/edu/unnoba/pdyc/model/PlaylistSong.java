package ar.edu.unnoba.pdyc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "playlists_songs")
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @Column(name = "song_id")
    private Long songId;

    // Constructores
    public PlaylistSong() {
    }

    public PlaylistSong(Playlist playlist, Long songId) {
        this.playlist = playlist;
        this.songId = songId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
