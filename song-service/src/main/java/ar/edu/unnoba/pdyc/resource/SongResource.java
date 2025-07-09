package ar.edu.unnoba.pdyc.resource;

import ar.edu.unnoba.pdyc.dto.SongDTO;
import ar.edu.unnoba.pdyc.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongResource {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songs = songService.getAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongById(@PathVariable Long id) {
        try {
            SongDTO song = songService.getSongById(id);
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO songDTO) {
        try {
            SongDTO createdSong = songService.createSong(songDTO);
            return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
