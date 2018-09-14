package com.bkexcercise.validiusmusic.persistence.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.bkexcercise.validiusmusic.exception.ResourceNotFoundException;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@RestController
public class ArtistController {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@GetMapping("/artist")
    public Page<Artist> getAllArtists(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @PostMapping("/artist")
    public Artist createPost(@Valid @RequestBody Artist artist) {
        return artistRepository.save(artist);
    }
    
    @GetMapping("/artist/{artistId}")
    public Optional<Artist> getArtistById(@PathVariable Long artistId, Pageable pageable) {
        return artistRepository.findById(artistId);
    }
    
    @PutMapping("/artist/{artistId}")
    public Artist updatePost(@PathVariable Long artistId, @Valid @RequestBody Artist artistRequest) {
        return artistRepository.findById(artistId).map(artist -> {
            artist.setName(artistRequest.getName());
            return artistRepository.save(artist);
        }).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));
    }
    
    @DeleteMapping("/artist/{artistId}")
    public ResponseEntity<?> deletePost(@PathVariable Long artistId) {
        return artistRepository.findById(artistId).map(artist -> {
        	artistRepository.delete(artist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));
    }
}
