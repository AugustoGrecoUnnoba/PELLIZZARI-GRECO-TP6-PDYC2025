package ar.edu.unnoba.pdyc.user_service.service;

import ar.edu.unnoba.pdyc.user_service.model.Artist;

import java.util.List;

public interface FavoriteArtistService {
    public List<Artist> getFavoriteArtist(Long userId);
}
