package ar.edu.unnoba.pdyc.artist_service.service;

import ar.edu.unnoba.pdyc.artist_service.model.Artist;

import java.util.List;

public interface ArtistService {
    public List<Artist> getArtists();
    public Artist findById(Long id);
}
