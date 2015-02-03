package de.yellow.medienverwaltung.database.entity;

import java.util.Collection;
import java.util.List;

public class User {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private List<Release> collection;
	private Collection<Genre> useGenres;
	private Collection<Subgenre> userSubgenres;
	private Collection<Artist> userArtists;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Release> getCollection() {
		return collection;
	}

	public void setCollection(List<Release> collection) {
		this.collection = collection;
	}

	public Collection<Genre> getUseGenres() {
		return useGenres;
	}

	public void setUseGenres(Collection<Genre> useGenres) {
		this.useGenres = useGenres;
	}

	public Collection<Subgenre> getUserSubgenres() {
		return userSubgenres;
	}

	public void setUserSubgenres(Collection<Subgenre> userSubgenres) {
		this.userSubgenres = userSubgenres;
	}

	public Collection<Artist> getUserArtists() {
		return userArtists;
	}

	public void setUserArtists(Collection<Artist> userArtists) {
		this.userArtists = userArtists;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", email=" + email
				+ ", collection=" + collection + ", useGenres=" + useGenres
				+ ", userSubgenres=" + userSubgenres + ", userArtists="
				+ userArtists + "]";
	}

}
