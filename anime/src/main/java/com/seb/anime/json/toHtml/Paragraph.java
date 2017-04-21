package com.seb.anime.json.toHtml;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Created by efreseb on 21/03/2017.
 */
public class Paragraph {
    private final String text;
    private final String color;

    public Paragraph(String text, Color color) {
        this.text = text;
        this.color = getColorName(color);
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }


    private String getColorName(Color c) {
        for (Field f : Color.class.getDeclaredFields()) {
            if (f.getType().equals(Color.class))
                try {
                    if (f.get(null).equals(c))
                        return f.getName();
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Paragraph{");
        sb.append("text='").append(text).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
