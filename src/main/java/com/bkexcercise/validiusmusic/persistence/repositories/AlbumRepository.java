package com.bkexcercise.validiusmusic.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkexcercise.validiusmusic.persistence.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	public Page<Album> findByArtistId(Long artistId, Pageable pageable);
	//public Optional<Album> findById(Long albumId);
	
}
