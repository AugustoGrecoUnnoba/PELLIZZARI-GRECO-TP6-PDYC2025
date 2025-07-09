package ar.edu.unnoba.pdyc.service;

import ar.edu.unnoba.pdyc.dto.PlaylistDTO;
import ar.edu.unnoba.pdyc.dto.PlaylistWithSongsDTO;
import ar.edu.unnoba.pdyc.dto.SongDTO;
import ar.edu.unnoba.pdyc.model.Playlist;
import ar.edu.unnoba.pdyc.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImp implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;


    @Autowired
    private SongServiceClient songServiceClient;


    @Override
    public List<PlaylistDTO> getAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();

        return playlists.stream()
                .map(playlist -> {
                    PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getName());

                    Playlist playlistWithSongs;
                    try {
                        playlistWithSongs = playlistRepository.findByIdWithSongs(playlist.getId())
                                .orElse(playlist);

                        List<Long> songIds = playlistWithSongs.getPlaylistSongs().stream()
                                .map(ps -> ps.getSongId())
                                .collect(Collectors.toList());

                        playlistDTO.setSongIds(songIds);
                    } catch (Exception e) {
                        System.err.println("Error al cargar canciones para playlist " + playlist.getId() + ": " + e.getMessage());
                    }

                    return playlistDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistDTO getPlaylistById(Long id) throws Exception {
        Playlist playlist = playlistRepository.findByIdWithSongs(id)
                .orElseThrow(() -> new Exception("No se encontró la lista de reproducción con ID: " + id));

        PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getName());

        // Extraer los IDs de las canciones de la lista de PlaylistSong
        List<Long> songIds = playlist.getPlaylistSongs().stream()
                .map(ps -> ps.getSongId())
                .collect(Collectors.toList());

        playlistDTO.setSongIds(songIds);

        return playlistDTO;
    }

    @Override
    public PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) throws Exception {
        if (playlistDTO.getName() == null || playlistDTO.getName().isEmpty()) {
            throw new Exception("El nombre de la lista de reproducción no puede estar vacío");
        }
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist = playlistRepository.save(playlist);
        return new PlaylistDTO(playlist.getId(), playlist.getName());
    }


    @Override
    @Transactional
    public PlaylistDTO addSongToPlaylist(Long playlistId, Long songId) throws Exception {
        // Verificar que la canción existe
        SongDTO songDTO = songServiceClient.getSongById(songId);
        if (songDTO == null) {
            throw new Exception("No se encontró la canción con ID: " + songId);
        }

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("No se encontró la lista de reproducción con ID: " + playlistId));

        System.out.println("Añadiendo canción con ID " + songId + " a playlist " + playlist.getName());
        System.out.println("Canciones antes: " + playlist.getPlaylistSongs().size());

        playlist.addSong(songId);

        System.out.println("Canciones después: " + playlist.getPlaylistSongs().size());

        playlist = playlistRepository.save(playlist);

        System.out.println("Playlist guardada con " + playlist.getPlaylistSongs().size() + " canciones");

        // Crear DTO y establecer los IDs de canciones
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getName());

        // Extraer los IDs de las canciones de la lista de PlaylistSong
        List<Long> songIds = playlist.getPlaylistSongs().stream()
                .map(ps -> ps.getSongId())
                .collect(Collectors.toList());

        playlistDTO.setSongIds(songIds);

        return playlistDTO;
    }

    @Override
    public PlaylistWithSongsDTO getPlaylistWithSongs(Long playlistId) throws Exception {
        Playlist playlist = playlistRepository.findByIdWithSongs(playlistId)
                .orElseThrow(() -> new Exception("No se encontró la lista de reproducción con ID: " + playlistId));

        List<SongDTO> songs = new ArrayList<>();
        List<Long> songIds = new ArrayList<>();

        System.out.println("Playlist " + playlist.getName() + " tiene " + playlist.getPlaylistSongs().size() + " canciones");

        for (var playlistSong : playlist.getPlaylistSongs()) {
            System.out.println("Procesando canción con ID: " + playlistSong.getSongId());
            songIds.add(playlistSong.getSongId());

            try {
                SongDTO songDTO = songServiceClient.getSongById(playlistSong.getSongId());
                songs.add(songDTO);
            } catch (Exception e) {
                System.err.println("Error al obtener la canción con ID: " + playlistSong.getSongId() + " - " + e.getMessage());
            }
        }

        PlaylistWithSongsDTO result = new PlaylistWithSongsDTO(playlist.getId(), playlist.getName(), songs);
        result.setSongIds(songIds);
        System.out.println("DTO contiene " + songIds.size() + " IDs de canciones");

        return result;
    }



}
