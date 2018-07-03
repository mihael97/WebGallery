package hr.fer.zemris.hw17.util;

import java.util.List;

public class Picture {
	private String[] tags;
	private String description;
	private String photoName;

	public Picture(String[] tags, String description, String photoName) {
		super();
		this.tags = tags;
		this.description = description;
		this.photoName = photoName;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

}
