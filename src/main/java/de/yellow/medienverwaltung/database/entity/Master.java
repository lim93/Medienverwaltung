package de.yellow.medienverwaltung.database.entity;

import java.sql.Date;
import java.sql.Blob;

public class Master {

	private int masterId;
	private int artistId;
	private String title;
	private Date released;
	private String imageURL;
	private Blob image;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
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
