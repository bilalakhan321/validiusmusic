package com.bkexcercise.validiusmusic.persistence.repositories.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.AlbumRepository;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlbumRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	
	@Test
	public void testSaveAlbum() {
		Artist artist = new Artist("Junaid Jamshed 2");
		entityManager.persist(artist);
		Album album = new Album("Aitebaar", 1988);
		album.setArtist(artist);
		Album savedInDb = entityManager.persist(album);
		Album getFromDb = albumRepository.findById(savedInDb.getId()).get();
		
		//Assert if new resource was successfully saved
		assertThat(getFromDb).isEqualTo(savedInDb);
		
	}
	
	@Test
	public void testGetAlbum() {
		Artist artist = new Artist("Michael Jackson 2");
		entityManager.persist(artist);
		Album album = new Album("BAD", 1985);
		album.setArtist(artist);
		Album savedInDb = entityManager.persist(album);
		Album getFromDb = albumRepository.findById(savedInDb.getId()).get();
		
		//Assert if Object to be found was successfully retrieved
		assertThat(getFromDb).isEqualTo(savedInDb);
	}
	
	@Test
	public void testFindAlbumByArtistId() {
		Artist artist = new Artist("Junaid Jamshed");
		entityManager.persist(artist);
		Album album = new Album("Dil Dil", 1984);
		album.setArtist(artist);
		Album savedInDb = entityManager.persist(album);
		Pageable pageable = PageRequest.of(0,1);
		
		//Find Object by Id and verify when found
		List<Album> getAllFromDbById = albumRepository.findByArtistId(savedInDb.getArtist().getId(), pageable)
				.getContent();
		Album getFromDb;
		for(int i=0; i<getAllFromDbById.size();i++) {
			if((savedInDb.getArtist().getId()==getAllFromDbById.get(i).getArtist().getId())) {
				getFromDb = getAllFromDbById.get(i);
				assertThat(getFromDb).isEqualTo(savedInDb);
			}
		}
		
	}
	
	@Test
	public void testUpdateAlbum() {
		Artist artist = new Artist("Junaid Jamshed");
		entityManager.persist(artist);
		Album album = new Album("Dil Dil", 1984);
		album.setArtist(artist);
		Album savedInDb = entityManager.persist(album);
		
		//Update an already existing object and save 
		savedInDb.setName("Best of Vital Signs");
		Album updatedAlbum = entityManager.persist(savedInDb);
		
		//Assert when object gets successfully saved
		assertThat(updatedAlbum.getName()).isEqualTo("Best of Vital Signs");
		
	}
	
	@Test
	public void testDeleteAlbum() {
		Artist artist = new Artist("Junaid Jamshed");
		
		Album album1 = new Album("Best of 1980s", 1984);
		album1.setArtist(artist);
		Album album2 = new Album("Humsafar", 1998);
		album2.setArtist(artist);
		
		//save Artist
		entityManager.persist(artist);
		
		//save the albums
		entityManager.persist(album1);
		entityManager.persist(album2);
		
		List<Album> albumsListBeforeDelete = albumRepository.findAll();
		entityManager.remove(album2);
		List<Album> albumsListAfterDelete = albumRepository.findAll();
		
		//Assert if total number of objects is less than 1 after deletion
		assertThat(albumsListBeforeDelete.size()-1).isEqualTo(albumsListAfterDelete.size());
		
		
	}
	
}
