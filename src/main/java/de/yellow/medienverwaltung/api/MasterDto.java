package de.yellow.medienverwaltung.api;

import java.util.List;

public class MasterDto {

	private String artist;
	private int artistId;
	private String title;
	private String genre;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
		return "MasterDto [artist=" + artist + ", artistId=" + artistId
				+ ", title=" + title + ", genre=" + genre + ", genreId="
				+ genreId + ", subgenreIds=" + subgenreIds + ", url=" + url
				+ ", releaseDate=" + releaseDate + "]";
	}

}
