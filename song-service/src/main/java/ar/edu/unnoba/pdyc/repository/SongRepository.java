package ar.edu.unnoba.pdyc.repository;

import ar.edu.unnoba.pdyc.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findById(Long id);

    List<Song> findAll();
}

