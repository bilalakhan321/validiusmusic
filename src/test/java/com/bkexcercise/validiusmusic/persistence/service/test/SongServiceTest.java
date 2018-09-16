package com.bkexcercise.validiusmusic.persistence.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
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
import com.bkexcercise.validiusmusic.persistence.model.Song;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.SongRepository;
import com.bkexcercise.validiusmusic.persistence.service.SongService;

public class SongServiceTest extends AbstractTest {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Autowired
	private SongService songService;

	@MockBean
	private ArtistRepository artistRepository;

	@MockBean
	private AlbumRepository albumRepository;
	
	@MockBean
	private SongRepository songRepository;
	
	@Test
	public void testGetAllSongsByAlbumId() {
		Artist artist = new Artist("Test Artist");
		Album album = new Album("Test Album", 2018);
		album.setArtist(artist);
		Song song1 = new Song("Test Song 1", 1);
		song1.setAlbum(album);
		Song song2 = new Song("Test Song 2", 2);
		song2.setAlbum(album);
		//Add newly created Songs to Page
		List<Song> songsList = Arrays.asList(song1, song2);
		Page<Song> songsPage = new PageImpl<Song>(songsList);
		Pageable pageable = PageRequest.of(0, 1);
		
		Mockito.when(songRepository.findByAlbumId(album.getId(), pageable)).thenReturn(songsPage);
		songsPage = songRepository.findByAlbumId(album.getId(), pageable);
		
		assertNotNull(songsPage);
		assertThat(songsPage.getContent().size()).isEqualTo(songsList.size());
		
	}
	
	@Test
	public void testCreateAlbum() {
		Artist artist = new Artist("Test Artist");
		Album album = new Album("Test Album", 2018);
		album.setArtist(artist);
		Song song1 = new Song("Test Song 1", 1);
		song1.setAlbum(album);
		
		//Mock the save function
		Mockito.when(songRepository.save(song1)).thenReturn(song1);
		assertThat(songRepository.save(song1)).isEqualTo(song1);
		
	}
	
	@Test
	public void testUpdateSong() {
		Artist artist = new Artist("Test Artist");
		Album album = new Album("Test Album", 2018);
		album.setArtist(artist);
		Song song1 = new Song("Test Song 1", 1);
		song1.setAlbum(album);
		
		Optional<Song> songList = Optional.of(song1);
		
		//Mock the find function
		given(songRepository.findById(song1.getId())).willReturn(songList);
		song1 = songRepository.findById(song1.getId()).get();
		song1.setName("Test Album Updated");
		
		//Mock the save updated function
		Mockito.when(songRepository.save(song1)).thenReturn(song1);
		Song updated = songRepository.save(song1);
		assertThat(updated).isEqualTo(song1);
	}
	
	@Test
	public void testDeleteSong() {
		Artist artist = new Artist("Test Artist");
		Album album = new Album("Test Album", 2018);
		album.setArtist(artist);
		Song songToBeDeleted = new Song("Test Song 1", 1);
		songToBeDeleted.setAlbum(album);
		
		//Mock the find function
		Optional<Song> songList = Optional.of(songToBeDeleted);
		given(songRepository.findById(songToBeDeleted.getId())).willReturn(songList);
		
		//Mock the find function with false value
		songToBeDeleted = songRepository.findById(songToBeDeleted.getId()).get();
		Mockito.when(songRepository.existsById(songToBeDeleted.getId())).thenReturn(false);
		assertFalse(albumRepository.existsById(songToBeDeleted.getId()));
	}
	
}
