package com.bkexcercise.validiusmusic;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bkexcercise.validiusmusic.persistence.controller.ArtistController;
import com.bkexcercise.validiusmusic.persistence.model.Album;
import com.bkexcercise.validiusmusic.persistence.model.Artist;
import com.bkexcercise.validiusmusic.persistence.repositories.ArtistRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(ArtistController.class)
@ContextConfiguration(classes = {Artist.class, ArtistRepository.class, Album.class})
public class ArtistControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	public void testGetArtist() {
		
		
		
	}
}
