package hr.fer.zemris.hw17.util;

/**
 * Class represents structure for describing informations about picture. Every
 * picture has:<br>
 * <ol>
 * <li>tags</li>
 * <li>description</li>
 * <li>photo name</li>
 * </ol>
 * 
 * @author Mihael
 *
 */
public class Picture {
	/**
	 * Arrays contains all tags
	 */
	private String[] tags;
	/**
	 * Picture description
	 */
	private String description;
	/**
	 * Picture name
	 */
	private String photoName;

	/**
	 * Method creates new picture describer
	 * 
	 * @param tags
	 *            - picture tags
	 * @param description
	 *            - picture description
	 * @param photoName
	 *            - picture name
	 */
	public Picture(String[] tags, String description, String photoName) {
		super();
		this.tags = tags;
		this.description = description;
		this.photoName = photoName;
	}

	/**
	 * Method returns picture's tags
	 * 
	 * @return picture's tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * Method sets picture tags
	 * 
	 * @param tags
	 *            - new picture tags
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	/**
	 * Method returns picture's description
	 * 
	 * @return picture's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method sets picture's description
	 * 
	 * @param description
	 *            - new picture description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method returns photo's name
	 * 
	 * @return photo's name
	 */
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * Method sets photo's name
	 * 
	 * @param photoName
	 *            - new photo's name
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

}
