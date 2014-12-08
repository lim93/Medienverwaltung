package de.yellow.medienverwaltung.database.entity;

import java.sql.Date;

public class Release {

	private int releaseID;
	private int masterID;
	private int labelID;
	private int formatID;
	private Date released;
	private String catalog;
	private String labelCode;
	private String barcode;
	private String comment;
	
	public int getReleaseID() {
		return releaseID;
	}
	
	public void setReleaseID(int releaseID) {
		this.releaseID = releaseID;
	}
	
	public int getMasterID() {
		return masterID;
	}
	
	public void setMasterID(int masterID) {
		this.masterID = masterID;
	}
	
	public int getLabelID() {
		return labelID;
	}
	
	public void setLabelID(int labelID) {
		this.labelID = labelID;
	}
	
	public int getFormatID() {
		return formatID;
	}
	
	public void setFormatID(int formatID) {
		this.formatID = formatID;
	}
	
	public Date getReleased() {
		return released;
	}
	
	public void setReleased(Date released) {
		this.released = released;
	}
	
	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
	public String getLabelCode() {
		return labelCode;
	}
	
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
