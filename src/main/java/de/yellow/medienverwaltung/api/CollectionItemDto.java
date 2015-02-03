package de.yellow.medienverwaltung.api;

public class CollectionItemDto {

	private int collItemId;
	private int userId;
	private int versionId;

	public int getCollItemId() {
		return collItemId;
	}

	public void setCollItemId(int collItemId) {
		this.collItemId = collItemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

}
