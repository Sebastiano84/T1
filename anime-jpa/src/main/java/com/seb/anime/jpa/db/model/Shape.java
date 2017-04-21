package com.seb.anime.jpa.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by efreseb on 24/04/2017.
 */
@Entity
public class Shape {    @Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
    private String className;

    public Shape() {
    }

    public Shape(String className) {
        this.className = className;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
