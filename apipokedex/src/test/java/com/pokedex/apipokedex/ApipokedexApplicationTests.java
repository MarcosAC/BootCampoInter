package com.pokedex.apipokedex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import com.pokedex.apipokedex.document.Pokedex;
import com.pokedex.apipokedex.repository.PokedexRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.pokedex.apipokedex.constants.PokedexConstants.POKEDEX_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class ApipokedexApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Autowired
	PokedexRepository pokedexRepository;

	@Test
	public void getOnePokemomById(){
		webTestClient.get().uri(POKEDEX_ENDPOINT_LOCAL.concat("/{id}"),"10")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOnePokemomnotFound(){
		webTestClient.get().uri(POKEDEX_ENDPOINT_LOCAL.concat("/{id}"),"10")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deletePokemom(){
		webTestClient.delete().uri(POKEDEX_ENDPOINT_LOCAL.concat("/{id}"),"1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}
}
