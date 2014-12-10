package de.yellow.medienverwaltung.database.entity;

import java.util.List;

public class Genre {

	private int genreId;
	private String name;
	private List<Subgenre> subgenres;

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
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
