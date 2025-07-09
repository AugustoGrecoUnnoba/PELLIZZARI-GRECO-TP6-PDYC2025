package ar.edu.unnoba.pdyc.repository;

import ar.edu.unnoba.pdyc.model.Playlist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
    List<Playlist> findAll();
    java.util.Optional<Playlist> findById(Long id);

    @Query("SELECT p FROM Playlist p LEFT JOIN FETCH p.playlistSongs WHERE p.id = :id")
    Optional<Playlist> findByIdWithSongs(@Param("id") Long id);

}
