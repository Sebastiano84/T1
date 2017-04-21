package com.seb.anime.creator;

import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by efreseb on 19/04/2017.
 */
public interface AnimeGenerator {
    BufferedImage createPage(Page page) throws InvalidShapeException;
    BufferedImage createGenericObject(GenericObject genericObject) throws InvalidShapeException;
}
