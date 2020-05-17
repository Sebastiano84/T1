package com.seb.anime.jpa.dao;

import com.seb.anime.jpa.db.model.Anime;
import com.seb.anime.jpa.db.model.Episode;
import com.seb.anime.jpa.db.model.Page;
import com.seb.anime.jpa.db.model.Scene;
import com.seb.anime.jpa.db.model.Season;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by efreseb on 12/04/2017.
 */

public class PageRepositoryTest extends AbstractRepositoryTest  {

    @Test
    public void testSave() {
        Page anime = new Page();
        savePage( anime);
    }

    @Test
    public void testCascadeSaveUntilAnime() {
        cleanUpDatabase();
        Page page = new Page(1, new Scene(new Episode(11,"test",new Season("season 1",new Anime("anime1")))));
        savePage(page);
        assertEquals(1,animeRepository.count());
        assertEquals(1,seasonRepository.count());
        assertEquals(1,episodeRepository.count());
        assertEquals(1,sceneRepository.count());
        assertEquals(1,pageRepository.count());
    }

    @Test
    public void testSaveUntilAnime() {
        cleanUpDatabase();
        Anime anime = animeRepository.save(new Anime("anime1"));
        Season season = seasonRepository.save(new Season());
        Episode episode = episodeRepository.save(new Episode(1,"ep1",null));
        Scene scene = sceneRepository.save(new Scene(episode));
        Page page = pageRepository.save(new Page("page1"));
        savePage(page);
        assertEquals(1,animeRepository.count());
        assertEquals(1,seasonRepository.count());
        assertEquals(1,episodeRepository.count());
        assertEquals(1,sceneRepository.count());
        assertEquals(1,pageRepository.count());
    }

    private Page savePage(Page page) {
        Page savedPage = pageRepository.save(page);
        assertNotNull(savedPage);

        Page found= pageRepository.findById(savedPage.getId()).orElseThrow();
        assertNotNull(found);
        assertEquals(savedPage,found);
        return found;
    }
}
