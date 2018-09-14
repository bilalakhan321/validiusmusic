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
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@RestController
public class AlbumController {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@GetMapping("/artist/{artistId}/albums")
    public Page<Album> getAllAlbumsByArtistId(@PathVariable (value = "artistId") Long artistId,
                                                Pageable pageable) {
        return albumRepository.findByArtistId(artistId, pageable);
    }
	
	@PostMapping("/artist/{artistId}/albums")
	public Album createAlbum(@PathVariable (value = "artistId") Long artistId,
            @Valid @RequestBody Album album) {
		
		return artistRepository.findById(artistId).map(artist -> {
            album.setArtist(artist);
            return albumRepository.save(album);
        }).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));
	
	}
	
	@PutMapping("/artist/{artistId}/albums/{albumId}")
    public Album updateAlbum(@PathVariable (value = "artistId") Long artistId,
                                 @PathVariable (value = "albumId") Long albumId,
                                 @Valid @RequestBody Album albumRequest) {
        if(!artistRepository.existsById(artistId)) {
            throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
        }

        return albumRepository.findById(albumId).map(album -> {
            album.setName(albumRequest.getName());
            album.setYearReleased(albumRequest.getYearReleased());
            return albumRepository.save(album);
        }).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + "not found"));
    }
	
	@DeleteMapping("/artist/{artistId}/albums/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable (value = "artistId") Long artistId,
                              @PathVariable (value = "albumId") Long albumId) {
        if(!artistRepository.existsById(artistId)) {
            throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
        }

        return albumRepository.findById(albumId).map(album -> {
             albumRepository.delete(album);
             return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + " not found"));
    }
	
}
