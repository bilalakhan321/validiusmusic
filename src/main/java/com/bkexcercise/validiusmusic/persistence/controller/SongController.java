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

import com.bkexcercise.validiusmusic.persistence.model.Song;
import com.bkexcercise.validiusmusic.persistence.service.SongService;

@RestController
public class SongController {
	
	
	@Autowired 
	SongService songService;
	
	@GetMapping("/artist/{artistId}/albums/{albumId}/songs")
    public Page<Song> getAllSongsByAlbumId(@PathVariable (value = "artistId") Long artistId,
    		@PathVariable (value = "albumId") Long albumId, 
    		Pageable pageable) {
		
		//Return get all songs from service layer using specified album id
		return songService.getAllSongsByAlbumId(artistId, albumId, pageable);
    }
	
	@PostMapping("/artist/{artistId}/albums/{albumId}/songs")
	public Song createSong(@PathVariable (value = "artistId") Long artistId,
			@PathVariable (value = "albumId") Long albumId,
            @Valid @RequestBody Song song) {
		
		//Return the create function from service layer
		return songService.createSong(artistId, albumId, song);
	
	}
	
	@PutMapping("/artist/{artistId}/albums/{albumId}/songs/{songId}")
    public Song updateSong(@PathVariable (value = "artistId") Long artistId,
                                 @PathVariable (value = "albumId") Long albumId,
                                 @PathVariable (value = "songId") Long songId,
                                 @Valid @RequestBody Song songRequest) {
        
		//Return update function from service layer
		return songService.updateSong(artistId, albumId, songId, songRequest);
    }
	
	@DeleteMapping("/artist/{artistId}/albums/{albumId}/songs/{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable (value = "artistId") Long artistId,
                              @PathVariable (value = "albumId") Long albumId,
                              @PathVariable (value = "songId") Long songId) {
        
		//Return the delete function from service layer
		return songService.deleteSong(artistId, albumId, songId);
    }
	
}
