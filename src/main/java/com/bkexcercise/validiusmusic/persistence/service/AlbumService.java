package com.bkexcercise.validiusmusic.persistence.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bkexcercise.validiusmusic.exception.ResourceNotFoundException;
import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@Service
public class AlbumService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private AlbumRepository albumRepository;

	public Page<Album> getAllAlbumsByArtistId(Long artistId, Pageable pageable) {
		return albumRepository.findByArtistId(artistId, pageable);
	}

	public Album getAlbumbyIdFromArtistId(Long artistId, Long albumId, Pageable Pageable) {
		
		/*Validity of artistId and albumId have been verified from controller */
		
		//Find object by Id and return it
		Optional<Album> listOfAlbumsFromArtist = albumRepository.findById(albumId);
		Album albumFound = listOfAlbumsFromArtist.get();
		return albumFound;
	}

	public Album createAlbum(Long artistId, Album album) {

		return artistRepository.findById(artistId).map(artist -> {
			album.setArtist(artist);
			return albumRepository.save(album);
		}).orElseThrow(() -> new ResourceNotFoundException("ArtistId " + artistId + " not found"));

	}

	public Album updateAlbum(Long artistId, Long albumId, Album albumRequest) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!isAlbumFromArtist(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}
		
		//Find object by id, update its properties and save it back
		return albumRepository.findById(albumId).map(album -> {
			album.setName(albumRequest.getName());
			album.setYearReleased(albumRequest.getYearReleased());
			return albumRepository.save(album);
		}).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + "not found"));
	}

	public ResponseEntity<?> deleteAlbum(Long artistId, Long albumId) {
		
		//Test the validity of artistId and albumId
		if (!artistRepository.existsById(artistId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		if (!isAlbumFromArtist(artistId, albumId)) {
			throw new ResourceNotFoundException("ArtistId " + artistId + " not found");
		}

		return albumRepository.findById(albumId).map(album -> {
			albumRepository.delete(album);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("AlbumId " + albumId + " not found"));
	}

	/* Helper Method */
	
	//Verify if album belongs to the provided artist
	private boolean isAlbumFromArtist(long artistId, Long albumId) {
		if (albumRepository.findById(albumId).get().getArtist().getId() == artistId) {
			return true;
		}
		return false;
	}

}
