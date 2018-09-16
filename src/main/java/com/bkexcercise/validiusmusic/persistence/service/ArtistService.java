package com.bkexcercise.validiusmusic.persistence.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bkexcercise.validiusmusic.exception.ResourceNotFoundException;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public Page<Artist> getAllArtists(Pageable pageable) {
		
		return artistRepository.findAll(pageable);
	}
	
	public Artist createArtist(Artist artist) {
		return artistRepository.save(artist);
	}
	
	public Optional<Artist> getArtistById(Long artistId, Pageable pageable) {
		return artistRepository.findById(artistId);
	}
	
	public Artist updateArtist(Long artistId, Artist artistRequest) {
		
		//Find Object, update its properties and save it back
		return artistRepository.findById(artistId).map(artist -> {
            artist.setName(artistRequest.getName());
            return artistRepository.save(artist);
        }).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));
	}
	
	public ResponseEntity<?> deleteArtist(Long artistId) {
		
		//find the artist and delete with attached http code
        return artistRepository.findById(artistId).map(artist -> {
        	artistRepository.delete(artist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));
    }
}
