package ar.edu.unnoba.pdyc.artist_service.resource;

import ar.edu.unnoba.pdyc.artist_service.model.Artist;
import ar.edu.unnoba.pdyc.artist_service.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistResource {
    private ArtistService service;
    @Autowired
    public ArtistResource(ArtistService artistService){
        this.service = artistService;
    }
    @GetMapping
    public List<Artist> getArtist(){
        return service.getArtists();
    }
    @GetMapping("/{id}")
    public Artist getArtist(@PathVariable("id") Long artistId){
        return service.findById(artistId);
    }
}
