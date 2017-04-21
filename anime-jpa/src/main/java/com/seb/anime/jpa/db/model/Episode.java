package com.seb.anime.jpa.db.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


/**
 * The persistent class for the utenti database table.
 * 
 */
@Entity
public class Episode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private int episodeNumber;

	private String episodeName;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Season season;

	@OneToMany(mappedBy = "episode")
	private Set<Scene> scenes;

	public Episode() {
	}

    public Episode(int episodeNumber, String episodeName, Season season) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.season = season;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Set<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}
}