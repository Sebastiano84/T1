package com.seb.anime.jpa.dao;

import com.seb.anime.jpa.db.model.Anime;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by efreseb on 12/04/2017.
 */


public class AnimeRepositoryTest extends AbstractRepositoryTest  {

    @Test
    public void testSave() {
        String name = "Pippo";
        Anime anime = new Anime(name);
        saveAnime(name, anime);
    }
    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveWithDuplicatedName() {
        String name = "Pippo";
        Anime anime = new Anime(name);
        saveAnime(name, anime);
        anime = new Anime(name);
        saveAnime(name, anime);
    }

    private Anime saveAnime(String name, Anime anime) {
        animeRepository.save(anime);
        List<Anime> findByName = animeRepository.findByName(name);
        assertNotNull(findByName);
        Anime findAnime = findByName.get(0);
        assertEquals(1, findByName.size());
        assertEquals(name, findAnime.getName());
        return findAnime;
    }
}
