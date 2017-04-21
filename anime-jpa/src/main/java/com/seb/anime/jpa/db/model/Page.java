package com.seb.anime.jpa.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * The persistent class for the anime database table.
 */
@Entity
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private int height;
    private int width;

    public Page() {
    }

    public Page(String name) {
        this.name = name;
    }
    public Page(int pageNumber, Scene scene) {
        this.scene = scene;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int pageNumber;


    private String name;

    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL)
    private Set<GenericObject> genericObjects;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Scene scene;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Set<GenericObject> getGenericObjects() {
        return genericObjects;
    }

    public void setGenericObjects(Set<GenericObject> genericObjects) {
        this.genericObjects = genericObjects;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}