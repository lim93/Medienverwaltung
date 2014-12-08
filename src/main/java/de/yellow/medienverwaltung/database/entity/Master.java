package de.yellow.medienverwaltung.database.entity;

import java.sql.Date;
import java.sql.Blob;

public class Master {

	private int masterID;
	private int artistID;
	private String title;
	private Date released;
	private String imageURL;
	private Blob image;

	public int getMasterID() {
		return masterID;
	}

	public void setMasterID(int masterID) {
		this.masterID = masterID;
	}

	public int getArtistID() {
		return artistID;
	}

	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Blob getImage(){
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
}
