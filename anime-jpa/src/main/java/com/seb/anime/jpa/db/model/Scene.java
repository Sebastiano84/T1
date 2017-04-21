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
public class Scene implements Serializable {
    private static final long serialVersionUID = 1L;

    public Scene() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Episode episode;

    @OneToMany(mappedBy = "scene")
    private Set<Page> pages;

    public Scene(Episode episode) {
        this.episode = episode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }
}