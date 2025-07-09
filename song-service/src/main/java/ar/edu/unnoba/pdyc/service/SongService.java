package ar.edu.unnoba.pdyc.service;

import ar.edu.unnoba.pdyc.dto.SongDTO;
import java.util.List;

public interface SongService {
    List<SongDTO> getAllSongs();
    SongDTO getSongById(Long id) throws Exception;

    SongDTO createSong(SongDTO songDTO) throws Exception;
}