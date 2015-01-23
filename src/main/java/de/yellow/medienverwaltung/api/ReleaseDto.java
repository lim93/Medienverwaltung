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
	private int releaseDay;
	private int releaseMonth;
	private int releaseYear;
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

	public int getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(int releaseDay) {
		this.releaseDay = releaseDay;
	}

	public int getReleaseMonth() {
		return releaseMonth;
	}

	public void setReleaseMonth(int releaseMonth) {
		this.releaseMonth = releaseMonth;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public List<Track> getTracklist() {
		return tracklist;
	}

	public void setTracklist(List<Track> tracklist) {
		this.tracklist = tracklist;
	}

	@Override
	public String toString() {
		return "ReleaseDto [masterId=" + masterId + ", formatId=" + formatId
				+ ", label=" + label + ", labelcode=" + labelcode
				+ ", catalogNo=" + catalogNo + ", barcode=" + barcode
				+ ", releaseDay=" + releaseDay + ", releaseMonth="
				+ releaseMonth + ", releaseYear=" + releaseYear
				+ ", tracklist=" + tracklist + "]";
	}

}
