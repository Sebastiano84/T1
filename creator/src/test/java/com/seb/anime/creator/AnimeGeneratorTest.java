package com.seb.anime.creator;

import com.seb.anime.jpa.db.model.Anime;
import com.seb.anime.jpa.db.model.Episode;
import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import com.seb.anime.jpa.db.model.Scene;
import com.seb.anime.jpa.db.model.Season;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

/**
 * Created by efreseb on 19/04/2017.
 */
public class AnimeGeneratorTest extends AbstractTest {

    private AnimeGenerator animeGenerator;

    @Before
    public void setUp() {
        animeGenerator = new AnimeGenerator2D();
    }

    @Test(expected = NullPointerException.class)
    public void testNullPage() {
        BufferedImage image = animeGenerator.createPage(null);
    }

    @Test
    public void testEmptyPage() throws IOException {
        BufferedImage image = animeGenerator.createPage(new Page("testPage"));
        generateTempImage(image);
        assertNotNull(image);
    }

    @Test
    public void testPageWithSingleObject() throws IOException {
        Page page = new Page(1, new Scene(new Episode(11, "test", new Season("season 1", new Anime("anime1")))));
        GenericObject genericObject = new GenericObject();
        genericObject.setPage(page);
        page.setGenericObjects(Collections.singleton(genericObject) );
        BufferedImage image = animeGenerator.createPage(new Page("testPage"));
        generateTempImage(image);
        assertNotNull(image);
    }

    private void generateTempImage(BufferedImage image) throws IOException {
        File tempImage = File.createTempFile("temp",".png");
        ImageIO.write(image,"png",new FileOutputStream(tempImage));
    }
}
