package com.bkexcercise.validiusmusic.persistence.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.service.AlbumService;

public class AlbumServiceTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Autowired
	private AlbumService albumService;

	@MockBean
	private ArtistRepository artistRepository;

	@MockBean
	private AlbumRepository albumRepository;

	@Test
	public void testGetAllAlbumsByArtistIdTest() {
		
		//Create new Songs and bind them to common artist and album
		Artist artist = new Artist("Test Artist");
		Pageable pageable = PageRequest.of(0, 1);
		Long artistId = 1L;
		artist.setId(artistId);
		Album album = new Album("Test Album", 1995);
		album.setArtist(artist);
		List<Album> albumsList = Arrays.asList(album);
		Page<Album> albumPage = new PageImpl<Album>(albumsList);

		//Mock the find function and verify all found
		Mockito.when(albumRepository.findByArtistId(artistId, pageable)).thenReturn(albumPage);
		albumPage = albumRepository.findByArtistId(artistId, pageable);
		assertNotNull(albumPage);
		assertThat(albumPage.getContent().size()).isEqualTo(albumsList.size());
	}

	@Test
	public void testGetAlbumbyIdFromArtistId() {

		Artist artist = new Artist("Test Artist");
		Long artistId = 1L;
		artist.setId(artistId);
		Album album = new Album("Test Album", 1995);
		album.setArtist(artist);
		Optional<Album> albumList = Optional.of(album);
		
		//Mock the find function by specific Id
		Mockito.when(albumRepository.findById(album.getId())).thenReturn(albumList);
		Album albumFound = albumRepository.findById(album.getId()).get();
		assertThat(albumFound).isEqualTo(album);
		

	}

	@Test
	public void testCreateAlbum() {
		Artist artist = new Artist("Test Artist");
		Long artistId = 1L;
		artist.setId(artistId);
		Album album = new Album("Test Album", 1995);
		album.setArtist(artist);
		
		//Mock to add new object
		Mockito.when(albumRepository.save(album)).thenReturn(album);
		assertThat(albumRepository.save(album)).isEqualTo(album);
	}
	
	@Test
	public void testUpdateAlbum() {
		Artist artist = new Artist("Test Artist");
		Pageable pageable = PageRequest.of(0, 1);
		Long artistId = 1L;
		artist.setId(artistId);
		Album album = new Album("Test Album", 1995);
		album.setArtist(artist);
		
		//Mock the find function
		Optional<Album> albumList = Optional.of(album);
		given(albumRepository.findById(album.getId())).willReturn(albumList);
		
		//Mock the save function after updating value
		album = albumRepository.findById(album.getId()).get();
		album.setName("Test Album Updated");
		Mockito.when(albumRepository.save(album)).thenReturn(album);
		Album updated = albumRepository.save(album);
		assertThat(updated).isEqualTo(album);
		
	}
	
	@Test
	public void testDeleteAlbum() {
		Artist artist = new Artist("Test Artist");
		Pageable pageable = PageRequest.of(0, 1);
		Long artistId = 1L;
		artist.setId(artistId);
		Album albumToBeDeleted = new Album("Test Album", 1995);
		Long albumId = 1L;
		albumToBeDeleted.setArtist(artist);
		
		//Mock the find Function
		Optional<Album> albumList = Optional.of(albumToBeDeleted);
		given(albumRepository.findById(albumToBeDeleted.getId())).willReturn(albumList);
		
		//Mock the exist function with false value after delete task
		albumToBeDeleted = albumRepository.findById(albumToBeDeleted.getId()).get();
		Mockito.when(albumRepository.existsById(albumId)).thenReturn(false);
		assertFalse(albumRepository.existsById(albumId));
		
	}
}
