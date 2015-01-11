package de.yellow.medienverwaltung.api;

import java.util.List;

import de.yellow.medienverwaltung.database.entity.Track;

public class ReleaseDto {

	private int masterId;
	private int formatId;
	private String label;
	private String labelcode;
	private String catalogNo;
	private String barcode;
	private String releaseDate;
	private List<Track> tracklist;

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelcode() {
		return labelcode;
	}

	public void setLabelcode(String labelcode) {
		this.labelcode = labelcode;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Track> getTracklist() {
		return tracklist;
	}

	public void setTracklist(List<Track> tracklist) {
		this.tracklist = tracklist;
	}

	@Override
	public String toString() {
		return "VersionsDto [masterId=" + masterId + ", formatId=" + formatId
				+ ", label=" + label + ", labelcode=" + labelcode
				+ ", catalogNo=" + catalogNo + ", barcode=" + barcode
				+ ", releaseDate=" + releaseDate + ", tracklist=" + tracklist
				+ "]";
	}

}
