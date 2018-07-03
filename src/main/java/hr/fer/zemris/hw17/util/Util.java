package hr.fer.zemris.hw17.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
	private final static String THUMBNAILS = "WEB-INF/thumbnails/";

	/**
	 * Method loads file form disc and returns set of <code>unique</code> tags
	 * 
	 * @param req
	 * 
	 * @return set of tags
	 * @throws IOException
	 *             -exception during reading
	 */
	public static Set<String> getTags(HttpServletRequest req) throws IOException {
		Set<String> forReturn = new HashSet<>();
		List<String> file = Files
				.readAllLines(Paths.get(req.getServletContext().getRealPath("/WEB-INF/")).resolve("opisnik.txt"));
		int i = 2;

		while (i < file.size()) {
			String tag = file.get(i);
			String[] array = tag.split(",");

			forReturn.addAll(Arrays.asList(array));
			i += 3; // tags are every third row
		}

		return forReturn;
	}

	/**
	 * Method creates folder for thumb nails storing
	 * 
	 * @param req
	 *            - HTTP request
	 * @throws IOException
	 *             - problems folder creating
	 */
	public static void createFolder(HttpServletRequest req) throws IOException {
		Path pathToFolder = Paths.get(req.getServletContext().getRealPath(THUMBNAILS));
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
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @return List of pictures name
	 * @throws IOException
	 *             - exception during reading
	 */
	public static List<String> getPictureByTag(String parameter, HttpServletRequest req) throws IOException {
		List<String> forReturn = new ArrayList<>();

		List<String> file = Files
				.readAllLines(Paths.get(req.getServletContext().getRealPath("/WEB-INF/")).resolve("opisnik.txt"));
		int i = 2;

		while (i < file.size()) {
			String tag = file.get(i);
			String[] array = tag.split(",");

			for (String string : array) {
				if (parameter.equals(string)) {
					forReturn.add(file.get(i - 2));
				}
			}

			i += 3; // tags are every third row
		}

		return forReturn;
	}

	/**
	 * Method returns informations about picture
	 * 
	 * @param parameter
	 *            - picture name
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @return informations about picture
	 * @throws IOException
	 *             - if exception during reading appears
	 */
	public static Picture getPictureByName(String parameter, HttpServletRequest req) throws IOException {
		Picture forReturn = null;

		List<String> file = Files
				.readAllLines(Paths.get(req.getServletContext().getRealPath("/WEB-INF/")).resolve("opisnik.txt"));
		int i = 0;

		while (i < file.size()) {
			if (file.get(i).equals(parameter)) {
				forReturn = new Picture(file.get(i + 2).split(","), file.get(i), file.get(i + 1));
				break;
			}

			i += 3; // tags are every third row
		}

		return forReturn;
	}
}
