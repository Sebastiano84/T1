package com.seb.anime.jpa.dao;


import com.seb.anime.jpa.db.model.Page;
import com.seb.anime.jpa.db.model.Scene;
import org.springframework.data.repository.CrudRepository;


public interface SceneRepository extends CrudRepository<Scene, Long> {
}