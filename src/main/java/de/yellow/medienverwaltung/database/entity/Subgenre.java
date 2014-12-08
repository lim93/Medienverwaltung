package de.yellow.medienverwaltung.database.entity;

public class Subgenre {

	private int subgenreID;
	private int genreID;
	private String name;
	
	public int getSubgenreID() {
		return subgenreID;
	}
	
	public void setSubgenreID(int subgenreID) {
		this.subgenreID = subgenreID;
	}
	
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
}
