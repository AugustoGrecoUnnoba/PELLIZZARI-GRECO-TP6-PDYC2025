package ar.edu.unnoba.pdyc.artist_service.repository;

import ar.edu.unnoba.pdyc.artist_service.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long>{}
