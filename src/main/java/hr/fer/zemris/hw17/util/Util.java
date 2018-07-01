package hr.fer.zemris.hw17.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class contains method for manipulating with data on disc<br>
 * Class provides method for:<br>
 * <ul>
 * <li>loading tag names form disc</li>
 * </ul>
 * 
 * @author Mihael
 *
 */
public abstract class Util {

	/**
	 * String constant shows where file with pictures description is stored
	 */
	private static final String DESCRIPTION_FILE = "src/main/WEB-INF/opisnik.txt";

	/**
	 * Method loads file form disc and returns set of <code>unique</code> tags
	 * 
	 * @return set of tags
	 * @throws IOException
	 *             -exception during reading
	 */
	public static Set<String> getTags() throws IOException {
		Set<String> forReturn = new HashSet<>();
		List<String> file = Files.readAllLines(Paths.get(DESCRIPTION_FILE));
		int i = 2;

		while (i < file.size()) {
			String tag = file.get(i);
			String[] array = tag.split(",");

			forReturn.addAll(Arrays.asList(array));
			i += 3; // tags are every third row
		}

		return forReturn;
	}
}
