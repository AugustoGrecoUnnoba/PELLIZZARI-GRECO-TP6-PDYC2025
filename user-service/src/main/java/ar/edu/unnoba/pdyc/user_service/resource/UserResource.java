package ar.edu.unnoba.pdyc.user_service.resource;

import ar.edu.unnoba.pdyc.user_service.model.Artist;
import ar.edu.unnoba.pdyc.user_service.model.User;
import ar.edu.unnoba.pdyc.user_service.service.FavoriteArtistService;
import ar.edu.unnoba.pdyc.user_service.service.UserService;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private UserService service;
    private FavoriteArtistService favoriteArtistService;
    @Autowired
    public UserResource(UserService userService, FavoriteArtistService favoriteArtistService){
        this.service = userService;
        this.favoriteArtistService = favoriteArtistService;
    }
    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}/favorite_artists")
    public List<Artist> getFavoriteArtists(@PathVariable("id") Long userId){
        return favoriteArtistService.getFavoriteArtist(userId);
    }
}
