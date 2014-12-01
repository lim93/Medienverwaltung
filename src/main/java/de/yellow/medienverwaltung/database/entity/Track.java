package de.yellow.medienverwaltung.database.entity;

/**
 * Die Entity-Klassen bilden DB-Tabellen ab
 * 
 * @author krispin
 *
 */
public class Track {

	private int trackID;
	private int number;
	private String title;
	private String duration;

	public int getTrackID() {
		return trackID;
	}

	public void setTrackID(int trackID) {
		this.trackID = trackID;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
