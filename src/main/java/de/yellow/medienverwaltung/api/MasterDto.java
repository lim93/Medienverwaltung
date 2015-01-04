package de.yellow.medienverwaltung.api;

import java.util.List;

public class MasterDto {

	// TODO: Krispin: Artist-Id verwenden. Dazu in der GUI nach Eingabe des
	// Künstlers eine Abfrage starten, um die Id zu ermitteln.
	private String artist;
	private String title;
	private int genreId;
	private List<Integer> subgenreIds;
	private String url;
	private String releaseDate;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public List<Integer> getSubgenreIds() {
		return subgenreIds;
	}

	public void setSubgenreIds(List<Integer> subgenreIds) {
		this.subgenreIds = subgenreIds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "MasterDto [artist=" + artist + ", title=" + title
				+ ", genreId=" + genreId + ", subgenreIds=" + subgenreIds
				+ ", url=" + url + ", releaseDate=" + releaseDate + "]";
	}

}
