package com.bkexcercise.validiusmusic.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkexcercise.validiusmusic.persistence.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
}
