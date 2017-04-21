package com.seb.anime.creator;

import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import org.springframework.stereotype.Component;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.image.BufferedImage;
import java.util.Set;

import static processing.core.PConstants.*;

/**
 * Created by efreseb on 19/04/2017.
 */
@Component
public class AnimeGenerator2D implements AnimeGenerator {
    @Override
    public BufferedImage createPage(Page page) {
        if (page == null) {
            throw new NullPointerException("Page is null");
        }
        PApplet pApplet = new PApplet();
        PImage pImage = pApplet.createImage(100,100, RGB);

        Set<GenericObject> genericObjects = page.getGenericObjects();
        if (genericObjects != null) {
            for (GenericObject genericObject : genericObjects) {
                pImage.mask(createGenericObject(genericObject));
            }
        }
        return generateImage(pImage);
    }

    @Override
    public PImage createGenericObject(GenericObject genericObject) {
        Page page = genericObject.getPage();
        PImage pImage = new PImage(page.getWidth(),page.getHeight(), 66, RGB);
        return pImage;
    }
}
