package com.bkexcercise.validiusmusic.persistence.repositories.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArtistRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	@Test
	public void testSaveArtist() {
		Artist artist = new Artist("Junaid Jamshed");
		Artist savedInDb = entityManager.persist(artist);
		Artist getFromDb = artistRepository.findById(savedInDb.getId()).get();
		
		//Verify if object has been saved
		assertThat(getFromDb).isEqualTo(savedInDb);
		
	}
	
	@Test
	public void testGetArtist() {
		Artist artist = new Artist("Michael Jackson");
		
		//Save Object
		Artist savedInDb = entityManager.persist(artist);
		Artist getFromDb = artistRepository.findById(savedInDb.getId()).get();
		
		//Assert if saved object can be retrieved
		assertThat(getFromDb).isEqualTo(savedInDb);
	}
	
	
	@Test
	public void testGetAllArtists() {
		
		Artist artist1 = new Artist("Maroon5");
		Artist artist2 = new Artist("Adele");
		
		//Save the objects
		entityManager.persist(artist1);
		entityManager.persist(artist2);
		
		//verify if all objects can be retrieved
		assertThat(artistRepository.findAll().size()).isEqualTo(2);
	}
	
	@Test
	public void testUpdateArtist() {
		Artist artist = new Artist("Madonna");
		entityManager.persist(artist);
		
		Artist artistFromDb = artistRepository.findById(artist.getId()).get();
		
		//Update the artist, save the updated object and assert if save was successful
		artistFromDb.setName("Madonna Ray Of Light");
		Artist updatedArtist = entityManager.persist(artistFromDb);
		assertThat(updatedArtist.getName()).isEqualTo("Madonna Ray Of Light");
		
	}
	
	
	@Test
	public void testDeleteArtist() {
		Artist artist1 = new Artist("Nusrat Fateh Ali Khan");
		Artist artist2 = new Artist("Rahat Ali Khan");
		
		entityManager.persist(artist1);
		entityManager.persist(artist2);
		List<Artist> allArtistsInDbBeforeDelete = artistRepository.findAll();
		
		//delete and verify if the total number of objects is less than by 1 after deletion
		entityManager.remove(artist1);
		List<Artist> allArtistsInDb = artistRepository.findAll();
		
		assertThat(allArtistsInDb.size()).isEqualTo(allArtistsInDbBeforeDelete.size() - 1);
		
	}

}
