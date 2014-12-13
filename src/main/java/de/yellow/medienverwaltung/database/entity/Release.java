package de.yellow.medienverwaltung.database.entity;

import java.sql.Date;

public class Release {

	private int releaseId;
	private int masterId;
	private int labelId;
	private int formatId;
	private Date released;
	private String catalogNo;
	private String labelCode;
	private String barcode;
	private String comment;
	
	public int getReleaseId() {
		return releaseId;
	}
	
	public void setReleaseId(int releaseId) {
		this.releaseId = releaseId;
	}
	
	public int getMasterId() {
		return masterId;
	}
	
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	
	public int getLabelId() {
		return labelId;
	}
	
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	
	public int getFormatId() {
		return formatId;
	}
	
	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}
	
	public Date getReleased() {
		return released;
	}
	
	public void setReleased(Date released) {
		this.released = released;
	}
	
	public String getCatalogNo() {
		return catalogNo;
	}
	
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
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
