package com.seb.anime.jpa.dao;


import com.seb.anime.jpa.db.model.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AnimeRepository extends CrudRepository<Anime, Integer> {
    List<Anime> findByName(String name);

}