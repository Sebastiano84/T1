package com.seb.anime.jpa.dao;


import com.seb.anime.jpa.db.model.Scene;
import com.seb.anime.jpa.db.model.Season;
import org.springframework.data.repository.CrudRepository;


public interface SeasonRepository extends CrudRepository<Season, Long> {
}