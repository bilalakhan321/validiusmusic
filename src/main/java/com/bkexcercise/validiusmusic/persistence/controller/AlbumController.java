package com.bkexcercise.validiusmusic.persistence.controller;

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

import com.bkexcercise.validiusmusic.exception.ResourceNotFoundException;
import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.service.AlbumService;

@RestController
public class AlbumController {
		
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/artist/{artistId}/albums")
    public Page<Album> getAllAlbumsByArtistId(@PathVariable (value = "artistId") Long artistId,
                                                Pageable pageable) {
        
		//Returns all album objects identified by artist id from service layer
		return albumService.getAllAlbumsByArtistId(artistId, pageable);
    }
	
	@GetMapping("/artist/{artistId}/albums/{albumId}")
	public Album getAlbumbyIdByArtistId(@PathVariable (value = "artistId")Long artistId,
			@PathVariable (value = "albumId") Long albumId,
			Pageable Pageable) {
		
		/*
		 * Artist Id and Album id are being verified at controller level to generate appropriate response
		 */
		if(!albumRepository.existsById(albumId)) {
			throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
		}
		if(!isAlbumFromArtist(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " does not own the album");
		}
		
		//Returns specific object identified by album id and artist id from service layer
		Album found =  albumService.getAlbumbyIdFromArtistId(artistId, albumId, Pageable);
		return found;
	}
	
	@PostMapping("/artist/{artistId}/albums")
	public Album createAlbum(@PathVariable (value = "artistId") Long artistId,
            @Valid @RequestBody Album album) {
		
		//Return create function from service layer
		return albumService.createAlbum(artistId, album);
	
	}
	
	@PutMapping("/artist/{artistId}/albums/{albumId}")
    public Album updateAlbum(@PathVariable (value = "artistId") Long artistId,
                                 @PathVariable (value = "albumId") Long albumId,
                                 @Valid @RequestBody Album albumRequest) {
        
		//Return update function from service layer
		return albumService.updateAlbum(artistId, albumId, albumRequest);
    }
	
	@DeleteMapping("/artist/{artistId}/albums/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable (value = "artistId") Long artistId,
                              @PathVariable (value = "albumId") Long albumId) {
        
		//Return delete function from service layer
		return albumService.deleteAlbum(artistId, albumId);
    }
	
	private boolean isAlbumFromArtist(long artistId, Long albumId) {
		if(albumRepository.findById(albumId).get().getArtist().getId() == artistId) {
			return true;
		}
		return false;
	}
	
}
