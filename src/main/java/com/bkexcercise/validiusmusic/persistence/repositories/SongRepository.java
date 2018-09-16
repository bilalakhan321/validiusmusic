package com.bkexcercise.validiusmusic.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkexcercise.validiusmusic.persistence.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
	
	public Page<Song> findByAlbumId(Long albumId, Pageable pageable);
	
}
