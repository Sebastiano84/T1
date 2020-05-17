package com.seb.anime.creator;

import static org.junit.Assert.assertNotNull;

import com.seb.anime.jpa.db.model.Anime;
import com.seb.anime.jpa.db.model.Episode;
import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import com.seb.anime.jpa.db.model.Scene;
import com.seb.anime.jpa.db.model.Season;
import com.seb.anime.jpa.db.model.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;

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
  public void testNullPage() throws InvalidShapeException {
    BufferedImage image = animeGenerator.createPage(null);
  }

  @Test
  public void testEmptyPage() throws IOException, InvalidShapeException {
    Page page = new Page("testEmptyPage");
    page.setWidth(200);
    page.setHeight(200);
    BufferedImage image = animeGenerator.createPage(page);
    generateTempImage(image, "temp");
    assertNotNull(image);
  }

  @Test
  public void testPageWithSingleObject() throws IOException, InvalidShapeException {
    Page page = new Page(1, new Scene(new Episode(11, "test", new Season("season 1", new Anime("anime1")))));
    page.setWidth(200);
    page.setHeight(200);
    GenericObject genericObject = new GenericObject("test", 10, 10, 10, 0, 30, 30, 30, page, null, null,
        new Shape("com.seb.anime.creator.shapes.Circle"));
    page.setGenericObjects(Collections.singleton(genericObject));
    BufferedImage image = animeGenerator.createPage(page);
    generateTempImage(image, "temp");
    assertNotNull(image);
  }

  @Test(expected = InvalidShapeException.class)
  public void testPageWithSingleObjectWithInvalidShape() throws IOException, InvalidShapeException {
    Page page = new Page(1, new Scene(new Episode(11, "test", new Season("season 1", new Anime("anime1")))));
    page.setWidth(200);
    page.setHeight(200);
    GenericObject genericObject = new GenericObject("test", 10, 10, 10, 0, 30, 30, 30, page, null, null,
        new Shape("com.seb.anime.creator.shapes.InvalidShape"));
    page.setGenericObjects(Collections.singleton(genericObject));
    BufferedImage image = animeGenerator.createPage(page);
    generateTempImage(image, "temp");
    assertNotNull(image);
  }

  private void generateTempImage(BufferedImage image, String imageName) throws IOException {
    File tempImage = File.createTempFile(imageName, ".png");
    ImageIO.write(image, "png", new FileOutputStream(tempImage));
  }
}
