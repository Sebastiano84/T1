package com.seb.anime.creator;

import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import processing.core.PImage;

import java.awt.image.BufferedImage;

/**
 * Created by efreseb on 19/04/2017.
 */
public interface AnimeGenerator {
    default BufferedImage generateImage(PImage image){
        return (BufferedImage)image.getNative();
    }
    BufferedImage createPage(Page page);
    PImage createGenericObject(GenericObject genericObject);
}
