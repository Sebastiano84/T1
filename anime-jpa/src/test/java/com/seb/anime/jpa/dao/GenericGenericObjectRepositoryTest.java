package com.seb.anime.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.seb.anime.jpa.db.model.Anime;
import com.seb.anime.jpa.db.model.Episode;
import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import com.seb.anime.jpa.db.model.Scene;
import com.seb.anime.jpa.db.model.Season;
import java.util.HashSet;
import java.util.Optional;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;


/**
 * Created by efreseb on 12/04/2017.
 */

public class GenericGenericObjectRepositoryTest extends AbstractRepositoryTest {

  @Test
  public void testSave() {
    String name = "Pippo";
    GenericObject genericObject = new GenericObject(name);
    saveObject(genericObject);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveWithDuplicatedName() {
    String name = "Pippo";
    GenericObject genericObject = new GenericObject(name);
    saveObject(genericObject);
    genericObject = new GenericObject(name);
    saveObject(genericObject);
  }

  @Test
  public void testCascadeSaveUntilAnimeWithSingleObject() {
    cleanUpDatabase();
    Page page = new Page(1, new Scene(new Episode(11, "test", new Season("season 1", new Anime("anime1")))));
    GenericObject genericObject = new GenericObject();
    genericObject.setPage(page);
    saveObject(genericObject);
    assertEquals(1, animeRepository.count());
    assertEquals(1, seasonRepository.count());
    assertEquals(1, episodeRepository.count());
    assertEquals(1, sceneRepository.count());
    assertEquals(1, pageRepository.count());
  }

  @Test
  public void testCascadeSaveUntilAnimeWithMultipleObject() {
    cleanUpDatabase();
    Page page = new Page(1, new Scene(new Episode(11, "test", new Season("season 1", new Anime("anime1")))));
    GenericObject genericObject = new GenericObject();
    genericObject.setPage(page);
    genericObject.setGenericObjects(new HashSet<GenericObject>());
    genericObject.getGenericObjects().add(new GenericObject());
    genericObject.getGenericObjects().add(new GenericObject());
    genericObject.getGenericObjects().add(new GenericObject());
    saveObject(genericObject);
    assertEquals(1, animeRepository.count());
    assertEquals(1, seasonRepository.count());
    assertEquals(1, episodeRepository.count());
    assertEquals(1, sceneRepository.count());
    assertEquals(1, pageRepository.count());
  }

  private GenericObject saveObject(GenericObject genericObject) {
    GenericObject savedObject = genericObjectRepository.save(genericObject);
    assertNotNull(savedObject);

    GenericObject one = genericObjectRepository.findById(savedObject.getId()).orElseThrow();
    assertNotNull(one);

    assertEquals(savedObject, one);
    return one;
  }
}
