package de.yellow.medienverwaltung.api;

import java.util.List;

import de.yellow.medienverwaltung.database.entity.Genre;
import de.yellow.medienverwaltung.database.entity.Subgenre;

public class ArtistDto {

	private int artistId;
	private String name;
	private int formed;
	private String from;
	private String website;
	private List<Genre> genres;
	private List<Subgenre> subgenres;

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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Subgenre> getSubgenres() {
		return subgenres;
	}

	public void setSubgenres(List<Subgenre> subgenres) {
		this.subgenres = subgenres;
	}

	@Override
	public String toString() {
		return "ArtistDto [artistId=" + artistId + ", name=" + name
				+ ", formed=" + formed + ", from=" + from + ", website="
				+ website + ", genres=" + genres + ", subgenres=" + subgenres
				+ "]";
	}

}
