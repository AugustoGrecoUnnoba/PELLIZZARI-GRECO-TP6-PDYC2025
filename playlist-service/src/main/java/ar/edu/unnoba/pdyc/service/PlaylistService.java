package ar.edu.unnoba.pdyc.service;

import ar.edu.unnoba.pdyc.dto.PlaylistDTO;
import ar.edu.unnoba.pdyc.dto.PlaylistWithSongsDTO;

import java.util.List;

public interface PlaylistService {
    List<PlaylistDTO> getAllPlaylists();
    PlaylistDTO getPlaylistById(Long id) throws Exception;
    PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) throws Exception;
    PlaylistDTO addSongToPlaylist(Long playlistId, Long songId) throws Exception;
    PlaylistWithSongsDTO getPlaylistWithSongs(Long playlistId) throws Exception;
}
