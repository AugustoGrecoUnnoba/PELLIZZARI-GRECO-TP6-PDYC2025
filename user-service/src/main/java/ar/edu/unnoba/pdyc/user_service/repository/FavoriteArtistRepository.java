package ar.edu.unnoba.pdyc.user_service.repository;

import ar.edu.unnoba.pdyc.user_service.model.Artist;
import ar.edu.unnoba.pdyc.user_service.model.FavoriteArtist;
import ar.edu.unnoba.pdyc.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteArtistRepository extends JpaRepository<FavoriteArtist,Long> {
    public List<FavoriteArtist> getFavoriteArtistByUserId(Long userId);
}
