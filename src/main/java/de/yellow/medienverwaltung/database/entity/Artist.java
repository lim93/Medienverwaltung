package de.yellow.medienverwaltung.database.entity;


/**
 * Die Entity-Klassen bilden DB-Tabellen ab
 * 
 * @author marius
 *
 */
public class Artist {

	private int artistId;
	private String name;
	private int formed;
	private String from;
	private String website;

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFormed() {
		return formed;
	}

	public void setFormed(int formed) {
		this.formed = formed;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
