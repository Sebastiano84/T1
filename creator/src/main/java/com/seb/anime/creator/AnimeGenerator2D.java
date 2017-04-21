package com.seb.anime.creator;

import com.seb.anime.jpa.db.model.GenericObject;
import com.seb.anime.jpa.db.model.Page;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by efreseb on 19/04/2017.
 */
@Component
public class AnimeGenerator2D implements AnimeGenerator {
    @Override
    public BufferedImage createPage(Page page) throws InvalidShapeException {
        if (page == null) {
            throw new NullPointerException("Page is null");
        }
        BufferedImage img = new BufferedImage(page.getWidth(), page.getHeight(), TYPE_INT_RGB);
        Set<GenericObject> genericObjects = page.getGenericObjects();
        if (genericObjects != null) {
            for (GenericObject genericObject : genericObjects) {
                BufferedImage image = createGenericObject(genericObject);
                img = createTileMask(img, image);

            }
        }
        return img;
    }

    @Override
    public BufferedImage createGenericObject(GenericObject genericObject) throws InvalidShapeException {
        Page page = genericObject.getPage();
        BufferedImage img = new BufferedImage(page.getWidth(), page.getHeight(), TYPE_INT_RGB);
        Class<?> clazz = null;
        try {
            clazz = getClass().getClassLoader().loadClass(genericObject.getShape().getClassName());
            clazz.getMethod("draw", Graphics.class,GenericObject.class).invoke(clazz.newInstance(), img.getGraphics(),genericObject);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InvalidShapeException(e);
        }

        img.getGraphics().drawLine(0, 0, page.getWidth(), page.getHeight());
        return img;
    }

    public static BufferedImage createTileMask(BufferedImage tile, BufferedImage mask) {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(tile.getWidth(null), tile.getHeight(null), Transparency.BITMASK);
        BufferedImage temp = gc.createCompatibleImage(tile.getWidth(null), tile.getHeight(null), Transparency.BITMASK);
        WritableRaster raster = result.getRaster();
        Raster maskData = mask.getRaster();
        Raster tileData = tile.getRaster();
        Graphics g;
        int[] pixel = new int[4];
        int width = tile.getWidth(null);
        int height = tile.getHeight(null);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = maskData.getPixel(x, y, pixel);
                if (pixel[0] == 0) {
                    tileData.getPixel(x, y, pixel);
                    pixel[3] = 255;
                    raster.setPixel(x, y, pixel);
                    pixel = tileData.getPixel(x, y, pixel);
                }
            }
        }

        result.setData(raster);

        g = temp.createGraphics();
        g.drawImage(result, 0, 0, null);
        g.dispose();

        return temp;
    }
}
