package com.pokedex.apipokedex.repository;

import com.pokedex.apipokedex.document.Pokedex;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface PokedexRepository extends CrudRepository<Pokedex, String>{
}
