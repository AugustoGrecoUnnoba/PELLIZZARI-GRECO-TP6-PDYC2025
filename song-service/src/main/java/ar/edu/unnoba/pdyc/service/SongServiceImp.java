package ar.edu.unnoba.pdyc.service;

import ar.edu.unnoba.pdyc.dto.SongDTO;
import ar.edu.unnoba.pdyc.model.Song;
import ar.edu.unnoba.pdyc.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongServiceImp implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream()
                .map(song -> new SongDTO(song.getId(), song.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public SongDTO getSongById(Long id) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            return new SongDTO(song.getId(), song.getName());
        } else {
            throw new Exception("No se encontró la canción con ID: " + id);
        }
    }

    @Override
    public SongDTO createSong(SongDTO songDTO) throws Exception {
        if (songDTO.getName() == null || songDTO.getName().isEmpty()) {
            throw new Exception("El nombre de la canción no puede estar vacío");
        }
        Song song = new Song();
        song.setName(songDTO.getName());
        song = songRepository.save(song);
        return new SongDTO(song.getId(), song.getName());
    }
}
