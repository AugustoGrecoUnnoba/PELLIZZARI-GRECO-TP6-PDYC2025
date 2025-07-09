package ar.edu.unnoba.pdyc.resource;

import ar.edu.unnoba.pdyc.dto.PlaylistDTO;
import ar.edu.unnoba.pdyc.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        List<PlaylistDTO> playlists = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id) {
        try {
            PlaylistDTO playlist = playlistService.getPlaylistById(id);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        try {
            PlaylistDTO createdPlaylist = playlistService.createPlaylist(playlistDTO);
            return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Long songId) {
        try {
            PlaylistDTO updatedPlaylist = playlistService.addSongToPlaylist(playlistId, songId);
            return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
