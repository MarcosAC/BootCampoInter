package com.pokedex.apipokedex.service;

import com.pokedex.apipokedex.document.Pokedex;
import com.pokedex.apipokedex.repository.PokedexRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokedexService {
    private final PokedexRepository pokedexRepository;

    public PokedexService(PokedexRepository pokedexRepository){
        this.pokedexRepository = pokedexRepository;
    }

    public Flux<Pokedex> findAll(){

        return Flux.fromIterable(this.pokedexRepository.findAll());
    }

    public  Mono<Pokedex> findByIdPokemom(String id){

        return  Mono.justOrEmpty(this.pokedexRepository.findById(id));
    }

    public Mono<Pokedex> save(Pokedex pokedex){
        return  Mono.justOrEmpty(this.pokedexRepository.save(pokedex));
    }

    public Mono<Boolean> deletebyIDPokemom(String id) {
        pokedexRepository.deleteById(id);
        return Mono.just(true);
    }
}
