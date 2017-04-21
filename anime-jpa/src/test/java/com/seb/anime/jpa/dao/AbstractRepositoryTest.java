package com.seb.anime.jpa.dao;

import com.seb.anime.jpa.db.model.Anime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceContext;

/**
 * Created by efreseb on 13/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = {Anime.class, AnimeRepository.class,DataSourceConfig.class})
@ContextConfiguration(classes = {PersistenceContext.class})
public abstract class AbstractRepositoryTest {
    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    SceneRepository sceneRepository;

    @Autowired
    GenericObjectRepository genericObjectRepository;

    @Autowired
    PageRepository pageRepository;

    void cleanUpDatabase(){
        animeRepository.delete(animeRepository.findAll());
    }
}
