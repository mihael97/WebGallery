package hr.fer.zemris.hw17.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import org.json.JSONObject;

/**
 * Abstract class contains method for manipulating with data on disc<br>
 * Class provides methods for:<br>
 * <ul>
 * <li>loading tag names from disc</li>
 * <li>creating folder for thumbnail storing</li>
 * <li>filtering pictures by tag name</li>
 * <li>returning pictures informations</li>
 * </ul>
 * 
 * @author Mihael
 *
 */
public abstract class Util {

	/**
	 * Path to folder with thumb nails
	 */
	private static String THUMBNAILS;

	/**
	 * List of all pictures
	 */
	private static List<Picture> pictures = null;

	/**
	 * Method initializes collection with all pictures
	 * 
	 * @param sce
	 *            - {@link ServletContextEvent}
	 * @throws IOException
	 *             - exception during reading
	 */
	 public static void init(ServletContextEvent sce) throws IOException {
	//public static void init() throws IOException {
		List<String> file = Files
				.readAllLines(Paths.get(sce.getServletContext().getRealPath("/WEB-INF/")).resolve("opisnik.txt"));

		//List<String> file = Files.readAllLines(Paths.get("opisnik.txt"));
		int i = 0;
		pictures = new ArrayList<>();
		System.out.println("tu san!");

		while (i < file.size()) {
			String name = file.get(i++);
			String desc = file.get(i++);
			String[] tags = file.get(i++).split(",");

			pictures.add(new Picture(tags, desc, name));
		}

		 THUMBNAILS = sce.getServletContext().getRealPath("WEB-INF/thumbnails/");
		//THUMBNAILS = "WEB-INF/thumbnails/";
	}

	/**
	 * Method loads file form disc and returns set of <code>unique</code> tags
	 * 
	 * @return set of tags
	 * @throws IOException
	 *             -exception during reading
	 */
	public static Set<String> getTags() throws IOException {
		//init();
		Set<String> forReturn = new HashSet<>();
		System.out.println("\n\n\n\nTU SAM! haha " + pictures == null + " " + pictures.size());

		for (Picture pic : pictures) {
			System.out.println(pic.getPhotoName());
			forReturn.addAll(Arrays.asList(pic.getTags()));
		}

		JSONObject object = new JSONObject();
		object.put("tags", forReturn);
		return forReturn;
	}

	/**
	 * Method creates folder for thumb nails storing
	 * 
	 * 
	 * @throws IOException
	 *             - problems folder creating
	 */
	public static void createFolder() throws IOException {
		java.nio.file.Path pathToFolder = Paths.get(THUMBNAILS);
		System.out.println(pathToFolder.toAbsolutePath().toString());

		if (!Files.isDirectory(pathToFolder)) {
			Files.createDirectory(pathToFolder);
		}
	}

	/**
	 * Method filters all pictures which have specific tag
	 * 
	 * @param parameter
	 *            - tag we need
	 * @return list of pictures name
	 * @throws IOException
	 *             - exception during reading
	 */
	public static List<String> getPictureByTag(String parameter) throws IOException {
		List<String> forReturn = new ArrayList<>();
		createFolder();

		for (Picture pic : pictures) {
			String[] array = pic.getTags();

			for (String string : array) {
				if (parameter.equals(string)) {
					forReturn.add(pic.getPhotoName());
				}
			}
		}

		return forReturn;
	}

	/**
	 * Method returns informations about picture
	 * 
	 * @param parameter
	 *            - picture name
	 * @return informations about picture
	 * @throws IOException
	 *             - if exception during reading appears
	 */
	public static Picture getPictureByName(String parameter) throws IOException {

		for (Picture pic : pictures) {
			if (pic.getPhotoName().equals(parameter)) {
				return pic;
			}
		}
		return null;
	}
}
