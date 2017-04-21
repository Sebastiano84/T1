package com.seb.anime.jpa.dao;


import com.seb.anime.jpa.db.model.GenericObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GenericObjectRepository extends CrudRepository<GenericObject, Integer> {

    List<GenericObject> findByName(String name);
}