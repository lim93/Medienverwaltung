package de.yellow.medienverwaltung.database.entity;

public class Subgenre {

	private int subgenreId;
	private int genreId;
	private String name;
	
	public int getSubgenreId() {
		return subgenreId;
	}
	
	public void setSubgenreId(int subgenreId) {
		this.subgenreId = subgenreId;
	}
	
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
}
