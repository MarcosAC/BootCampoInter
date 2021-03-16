package com.pokedex.apipokedex.controller;

import com.pokedex.apipokedex.service.PokedexService;
import com.pokedex.apipokedex.repository.PokedexRepository;
import com.pokedex.apipokedex.document.Pokedex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.pokedex.apipokedex.constants.PokedexConstants.POKEDEX_ENDPOINT_LOCAL;

@RestController
@Slf4j

public class PokedexController {
    PokedexService pokedexService;
    PokedexRepository pokedexRepository;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(PokedexController.class);

    public PokedexController(PokedexService pokedexService, PokedexRepository pokedexRepository){
        this.pokedexService = pokedexService;
        this.pokedexRepository = pokedexRepository;
    }

    @GetMapping(POKEDEX_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Pokedex> getAllItems() {
        log.info("requesting the list off all pokemons");
        return pokedexService.findAll();
    }

    @GetMapping(POKEDEX_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Pokedex>> findByIdPokemom(@PathVariable String id) {
        log.info("Requesting the pokemon with id {}", id);
        return pokedexService.findByIdPokemom(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(POKEDEX_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokedex> createPokemom(@RequestBody Pokedex pokedex) {
        log.info("A new pokemon was Created");
        return pokedexService.save(pokedex);
    }

    @DeleteMapping(POKEDEX_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDPokemom(@PathVariable String id) {
        pokedexService.deletebyIDPokemom(id);
        log.info("Deleting the pokemon with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
