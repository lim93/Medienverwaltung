package de.yellow.medienverwaltung.database.entity;

/**
 * Die Entity-Klassen bilden DB-Tabellen ab
 * 
 * @author krispin
 *
 */
public class Track {

	private int trackId;
	private String number;
	private String title;
	private String duration;

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", number=" + number + ", title="
				+ title + ", duration=" + duration + "]";
	}

}
