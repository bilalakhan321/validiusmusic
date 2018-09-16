package com.bkexcercise.validiusmusic.persistence.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.service.ArtistService;

@RestController
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/artist")
    public Page<Artist> getAllArtists(Pageable pageable) {
        
		//Returns all artist processed from service layer
		return artistService.getAllArtists(pageable);
    }
	
	//
    @PostMapping("/artist")
    public Artist createArtist(@Valid @RequestBody Artist artist) {

    	//Returns create function from service layer
    	return artistService.createArtist(artist);
    }
    
    @GetMapping("/artist/{artistId}")
    public Optional<Artist> getArtistById(@PathVariable Long artistId, Pageable pageable) {
        
    	//Returns Artist by artist id from service layer
    	return artistService.getArtistById(artistId, pageable);
    }
    
    @PutMapping("/artist/{artistId}")
    public Artist updateArtist(@PathVariable Long artistId, @Valid @RequestBody Artist artistRequest) {
    	
    	//Returns update function from service layer
    	return artistService.updateArtist(artistId, artistRequest);
    }
    
    @DeleteMapping("/artist/{artistId}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long artistId) {
        
    	//Returns delete function from service layer
    	return artistService.deleteArtist(artistId);
    }
}
