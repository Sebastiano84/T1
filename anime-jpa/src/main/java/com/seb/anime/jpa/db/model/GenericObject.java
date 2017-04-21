package com.seb.anime.jpa.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the utenti database table.
 * 
 */
@Entity
public class GenericObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique=true)
	private String name;

	private int posX;
	private int posY;
	private int posZ;

	private int orientation;

    @ManyToOne(cascade = CascadeType.PERSIST)
	private Page page;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private GenericObject parentGenericObject;

	@OneToMany(mappedBy = "parentGenericObject", cascade = CascadeType.ALL)
	private Set<GenericObject> genericObjects;

	public GenericObject() {
	}

	public GenericObject(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosZ() {
		return posZ;
	}

	public void setPosZ(int posZ) {
		this.posZ = posZ;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public GenericObject getParentGenericObject() {
		return parentGenericObject;
	}

	public void setParentGenericObject(GenericObject parentGenericObject) {
		this.parentGenericObject = parentGenericObject;
	}

	public Set<GenericObject> getGenericObjects() {
		return genericObjects;
	}

	public void setGenericObjects(Set<GenericObject> genericObjects) {
		this.genericObjects = genericObjects;
	}
}