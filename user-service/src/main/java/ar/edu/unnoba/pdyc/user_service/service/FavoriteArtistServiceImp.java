package ar.edu.unnoba.pdyc.user_service.service;

import ar.edu.unnoba.pdyc.user_service.model.Artist;
import ar.edu.unnoba.pdyc.user_service.model.FavoriteArtist;
import ar.edu.unnoba.pdyc.user_service.repository.FavoriteArtistRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteArtistServiceImp implements FavoriteArtistService{
    private FavoriteArtistRepository repository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public FavoriteArtistServiceImp(FavoriteArtistRepository favoriteArtistRepository,
                                    DiscoveryClient discoveryClient,
                                    RestClient.Builder restClientBuilder){
        this.repository = favoriteArtistRepository;
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }
    @Override
    public List<Artist> getFavoriteArtist(Long userId) {
        List<FavoriteArtist> favoriteArtists = repository.getFavoriteArtistByUserId(userId);
        ServiceInstance serviceInstance = discoveryClient.getInstances("ARTIST-SERVICE").get(0);
        List<Artist> artists = new ArrayList<>();
        favoriteArtists.stream().forEach(fa -> {
            Artist artist = restClient.get()
                    .uri(serviceInstance.getUri() + "/artists/" + fa.getArtistId())
                    .retrieve()
                    .body(Artist.class);
            artists.add(artist);
        });
        return artists;
    }
}
