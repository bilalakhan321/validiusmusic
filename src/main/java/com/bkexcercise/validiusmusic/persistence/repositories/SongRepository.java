package com.bkexcercise.validiusmusic.persistence.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkexcercise.validiusmusic.persistence.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
	
	public Page<Song> findByAlbumId(Long albumId, Pageable pageable);
	public List<Song> findByAlbumId(Long albumId);
	//public Page<Song> findByArtistId(Long artistId, Pageable pageable);
	//
	//public Page<Album> findByArtistId(Long artistId, Pageable pageable);
}
