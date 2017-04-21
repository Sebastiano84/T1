package com.seb.anime.jpa.dao;


import com.seb.anime.jpa.db.model.Page;
import org.springframework.data.repository.CrudRepository;


public interface PageRepository extends CrudRepository<Page, Long> {
}