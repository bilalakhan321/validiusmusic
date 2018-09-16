package com.bkexcercise.validiusmusic.persistence.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;
import com.bkexcercise.validiusmusic.persistence.service.ArtistService;


public class ArtistServiceTest extends AbstractTest {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Autowired
	private ArtistService artistService;
	
	@MockBean
	private ArtistRepository artistRepository;
	
	@Test
	public void testGetArtist() throws Exception {
				
		Artist artist = new Artist("Dummy Value");
		List<Artist> artistList = Arrays.asList(artist);
		
		//Create Stub to run mock search against
		given(artistRepository.findAll()).willReturn(artistList);
		
		//Assert if object is found
		artistList = artistRepository.findAll();
		assertNotNull(artistList);
		assertThat(artistList.size()).isEqualTo(1);
		
	}
	
	@Test
	public void testCreateArtist() {
		Artist artist = new Artist("Test Created Artist");
		
		//Mock the addition of new object and assert if it is true
		Mockito.when(artistRepository.save(artist)).thenReturn(artist);
		assertThat(artistService.createArtist(artist)).isEqualTo(artist);
	}
	
	@Test
	public void testGetArtistById() {
		
		Artist artist = new Artist("Test Created Artist");
		Long artistId = 1L;
		Optional<Artist> artistList = Optional.of(artist);
		
		//Create stub to use for mock search
		given(artistRepository.findById(artistId)).willReturn(artistList);
		
		//Mock the find function by specific ID
		Pageable pageable = PageRequest.of(0, 1);
		Artist returnedArtist = artistService.getArtistById(artistId, pageable).get();
		Mockito.verify(artistRepository).findById(artistId);
		assertNotNull(returnedArtist);
		assertThat(returnedArtist).isEqualTo(artist);
	}
	
	@Test
	public void testUpdateArtist() {
		
		Artist artistMock = new Artist("Test Created Artist");
		Long artistId = 1L;
		Optional<Artist> artistList = Optional.of(artistMock);
		
		//Mock the find function of object to be updated
		given(artistRepository.findById(artistId)).willReturn(artistList);
		Pageable pageable = PageRequest.of(0, 1);
		artistMock = artistService.getArtistById(artistId, pageable).get();
		artistMock.setName("Test Updated Artist");
		
		//Mock the save function of object after its content have been updated
		Mockito.when(artistRepository.save(artistMock)).thenReturn(artistMock);
		assertThat(artistService.updateArtist(artistId, artistMock)).isEqualTo(artistMock);
		
	}
	
	@Test 
	public void testDeleteArtist() {
		
		Artist artistToBeDeleted = new Artist("Test Artist to be deleted");
		Long artistId = 1L;
		Optional<Artist> artistList = Optional.of(artistToBeDeleted);
		
		//Mock the find function to find object to delete
		given(artistRepository.findById(artistId)).willReturn(artistList);
		Pageable pageable = PageRequest.of(0, 1);
		artistToBeDeleted = artistService.getArtistById(artistId, pageable).get();
		
		//Mock verification if object exists after delete function
		Mockito.when(artistRepository.existsById(artistId)).thenReturn(false);
		assertFalse(artistRepository.existsById(artistId));
		
	}
	
}

