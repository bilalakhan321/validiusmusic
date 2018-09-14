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
import com.bkexcercise.validiusmusic.persistence.model.Song;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.SongRepository;

@RestController
public class SongController {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	private SongRepository songRepository;
	
	@GetMapping("/artist/{artistId}/albums/{albumId}/songs")
    public Page<Song> getAllSongsByAlbumId(@PathVariable (value = "artistId") Long artistId,
    		@PathVariable (value = "albumId") Long albumId, 
    		Pageable pageable) {
		
        return songRepository.findByAlbumId(albumId, pageable);
    }
	
	@PostMapping("/artist/{artistId}/albums/{albumId}/songs")
	public Song createSong(@PathVariable (value = "albumId") Long albumId,
            @Valid @RequestBody Song song) {
		
		return albumRepository.findById(albumId).map(album -> {
            song.setAlbum(album);
            return songRepository.save(song);
        }).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + " not found"));
	
	}
	
	@PutMapping("/artist/{artistId}/albums/{albumId}/songs/{songId}")
    public Song updateSong(@PathVariable (value = "artistId") Long artistId,
                                 @PathVariable (value = "albumId") Long albumId,
                                 @PathVariable (value = "songId") Long songId,
                                 @Valid @RequestBody Song songRequest) {
        if(!artistRepository.existsById(artistId)) {
            throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
        }
        
        if(!albumRepository.existsById(albumId)) {
            throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
        }

        return songRepository.findById(songId).map(song -> {
            song.setName(songRequest.getName());
            song.setTrack(songRequest.getTrack());
            return songRepository.save(song);
        }).orElseThrow(() -> new ResourceNotFoundException("SongId " + songId + "not found"));
    }
	
	@DeleteMapping("/artist/{artistId}/albums/{albumId}/songs/{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable (value = "artistId") Long artistId,
                              @PathVariable (value = "albumId") Long albumId,
                              @PathVariable (value = "songId") Long songId) {
        if(!artistRepository.existsById(artistId)) {
            throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
        }
        
        if(!albumRepository.existsById(albumId)) {
            throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
        }

        return songRepository.findById(songId).map(song -> {
             songRepository.delete(song);
             return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("SongId " + songId + " not found"));
    }
}
