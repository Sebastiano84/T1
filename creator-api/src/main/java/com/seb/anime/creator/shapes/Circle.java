package com.seb.anime.creator.shapes;

import com.seb.anime.jpa.db.model.GenericObject;

import java.awt.*;

/**
 * Created by efreseb on 24/04/2017.
 */
public class Circle {
    public void draw(Graphics graphics, GenericObject genericObject){
        graphics.drawOval(genericObject.getPosX(),genericObject.getPosY(),genericObject.getWidth(),genericObject.getHeight());
    }
}
