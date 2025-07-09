package ar.edu.unnoba.pdyc.artist_service.service;

import ar.edu.unnoba.pdyc.artist_service.model.Artist;
import ar.edu.unnoba.pdyc.artist_service.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImp implements ArtistService {
    private ArtistRepository repository;

    @Autowired
    public ArtistServiceImp(ArtistRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Artist> getArtists() {
        return repository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
