package de.yellow.medienverwaltung.database.entity;

public class Label {

	private int labelId;
	private String name;
	private String website;
	
	public int getLabelId() {
		return labelId;
	}
	
	public void setLabelId(int labelId) {
		this.labelId = labelId;
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
