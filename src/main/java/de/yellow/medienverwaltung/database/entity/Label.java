package de.yellow.medienverwaltung.database.entity;

public class Label {

	private int labelID;
	private String name;
	private String website;
	
	public int getLabelID() {
		return labelID;
	}
	
	public void setLabelID(int labelID) {
		this.labelID = labelID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
}
