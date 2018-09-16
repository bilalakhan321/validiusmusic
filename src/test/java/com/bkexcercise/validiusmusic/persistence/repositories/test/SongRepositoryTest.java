package com.bkexcercise.validiusmusic.persistence.repositories.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.model.Song;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.SongRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SongRepositoryTest {
	
	
	@Autowired
	private TestEntityManager entityManager;
		
	@Autowired
	private SongRepository songRepository;
	
	@Test
	public void testSaveSong() {
		Artist artist = new Artist("Junaid Jamshed 2");
		entityManager.persist(artist);
		Album album = new Album("Aitebaar", 1988);
		album.setArtist(artist);
		entityManager.persist(album);
		Song song = new Song("Dil Dil Pakistan", 1);
		song.setAlbum(album);
		
		//Save new song resource and assert if saved successfully
		Song savedSong = entityManager.persist(song);
		Song songFromDb = songRepository.findById(savedSong.getId()).get();
		
		assertThat(songFromDb).isEqualTo(savedSong);
		
	}
	
	@Test
	public void testGetAllSongsByAlbumId() {
		Artist artist = new Artist("Junaid Jamshed 2");
		entityManager.persist(artist);
		Album album = new Album("Aitebaar", 1988);
		album.setArtist(artist);
		entityManager.persist(album);
		
		//Create songs and save them to one album
		Song song1 = new Song("Dil Dil Pakistan", 1);
		song1.setAlbum(album);
		Song song2 = new Song("Aitebaar Song", 2);
		song2.setAlbum(album);
		Song song3 = new Song("Ray of Light", 3);
		song3.setAlbum(album);
		
		//Save all the songs and assert if all songs have been retrieved successfully
		entityManager.persist(song1);
		entityManager.persist(song2);
		entityManager.persist(song3);
		
		List<Song> allSongsFromDb = songRepository.findAll();
		
		assertThat(allSongsFromDb.size()).isEqualByComparingTo(3);
		
	}
	
	@Test
	public void updateSongByIdFromAlbumIdandArtistId() {
		
		Artist artist = new Artist("Adele");
		entityManager.persist(artist);
		Album album = new Album("Hello", 2016);
		album.setArtist(artist);
		entityManager.persist(album);
		
		//Create Song and save them to one Album
		Song song1 = new Song("He Won't Go", 1);
		song1.setAlbum(album);
		
		//Save the song after update and assert true on success
		Song savedSong = entityManager.persist(song1);
		
		savedSong.setName("He Wont Go Remastered");
		Song updatedSong = entityManager.persist(savedSong);
		assertThat(updatedSong.getName()).isEqualTo("He Wont Go Remastered");
		
	}
	
	@Test
	public void deleteSongByIdFromAlbumIdandArtistId() {
		Artist artist = new Artist("Adele");
		entityManager.persist(artist);
		Album album = new Album("Hello", 2016);
		album.setArtist(artist);
		entityManager.persist(album);
		
		//Create Songs and save them to one Album
		Song song1 = new Song("He Won't Go", 1);
		song1.setAlbum(album);
		Song song2 = new Song("Helle", 2);
		song2.setAlbum(album);
		Song song3 = new Song("Hiding My Heart", 3);
		song3.setAlbum(album);
		
		//Save all songs created
		entityManager.persist(song1);
		entityManager.persist(song2);
		Song songToDelete = entityManager.persist(song3);
		
		List<Song> listOfSongsBeforeDelete = songRepository.findAll();
		entityManager.remove(songToDelete);
		List<Song> listOfSongsAfterDelete = songRepository.findAll();
		
		//Assert if list size has changed after deletion
		assertThat(listOfSongsAfterDelete.size()).isEqualTo(listOfSongsBeforeDelete.size()-1);
	}
	
}
