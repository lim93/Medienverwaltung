package de.yellow.medienverwaltung.database.entity;

import java.util.List;

public class Genre {

	private int genreID;
	private String name;
	private List<Subgenre> subgenres;

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subgenre> getSubgenres() {
		return subgenres;
	}

	public void setSubgenres(List<Subgenre> subgenres) {
		this.subgenres = subgenres;
	}

}
