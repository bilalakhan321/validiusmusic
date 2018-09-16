package com.bkexcercise.validiusmusic.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bkexcercise.validiusmusic.exception.ResourceNotFoundException;
import com.bkexcercise.validiusmusic.persistence.model.Song;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.SongRepository;

@Service
public class SongService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private SongRepository songRepository;

	public Page<Song> getAllSongsByAlbumId(Long artistId, Long albumId, Pageable pageable) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!albumRepository.existsById(albumId)) {
			throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
		}
		if (!isArtistFromAlbum(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}
		
		//Retrieve all songs from dependent object
		return songRepository.findByAlbumId(albumId, pageable);
	}

	public Song createSong(Long artistId, Long albumId, Song song) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!isArtistFromAlbum(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}
		
		//Find the dependent object. When dependent object is found, create new resource and link it
		//to dependent object
		return albumRepository.findById(albumId).map(album -> {
			song.setAlbum(album);
			return songRepository.save(song);
		}).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + " not found"));

	}

	public Song updateSong(Long artistId, Long albumId, Long songId, Song songRequest) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!albumRepository.existsById(albumId)) {
			throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
		}

		if (!isArtistFromAlbum(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}
		
		//Update the object and save it back
		return songRepository.findById(songId).map(song -> {
			song.setName(songRequest.getName());
			song.setTrack(songRequest.getTrack());
			return songRepository.save(song);
		}).orElseThrow(() -> new ResourceNotFoundException("SongId " + songId + "not found"));
	}

	public ResponseEntity<?> deleteSong(Long artistId, Long albumId, Long songId) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!albumRepository.existsById(albumId)) {
			throw new ResourceNotFoundException("AlbumId " + albumId + " not found");
		}

		if (!isArtistFromAlbum(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}
		
		//Find the object and delete
		return songRepository.findById(songId).map(song -> {
			songRepository.delete(song);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("SongId " + songId + " not found"));
	}

	/** HELPER METHODS **/
	
	//Verify if album belongs to the provided artist
	private boolean isArtistFromAlbum(long artistId, long albumId) {

		if (albumRepository.findById(albumId) != null) {
			if (albumRepository.findById(albumId).get().getArtist().getId() == artistId) {
				return true;
			}
		}
		return false;
	}

}
