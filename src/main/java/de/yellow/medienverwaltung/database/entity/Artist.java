package de.yellow.medienverwaltung.database.entity;

import java.sql.Date;

/**
 * Die Entity-Klassen bilden DB-Tabellen ab
 * 
 * @author marius
 *
 */
public class Artist {

	private int artistId;
	private String name;
	private Date formed;
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
	
	public Date getFormed() {
		return formed;
	}
	
	public void setFormed(Date formed) {
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
