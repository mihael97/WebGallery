package hr.fer.zemris.hw17.servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import hr.fer.zemris.hw17.util.Util;

/**
 * Class represents {@link HttpServlet} and contains method for creating thumb
 * nails<br>
 * On disc is map whit already used <code>thumb nails</code>. If thumb nail we
 * want is not used before,method will create it for first time and saves it on
 * disc. Otherwise,it will load generated thumb nail and redirects it to client
 * 
 * @author Mihael
 *
 */
@WebServlet("/servlets/thumbnails")
public class ThumbnailsServlet extends HttpServlet {
	private final String THUMBNAILS = "WEB-INF/thumbnails/";
	private final String PICTURES = "WEB-INF/slike/";
	/**
	 * Every thumb nail dimensions should be <code>150x150</code>
	 */
	private final Integer DIMENSIONS = 150;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Util.createFolder(req);

		List<String> tags = Util.getPictureByTag(req.getParameter("tagname"), req);
		List<Path> images = new ArrayList<>();

		for (String tag : tags) {
			Path path = Paths.get(req.getServletContext().getRealPath(THUMBNAILS + tag + ".jpg"));
			BufferedImage image = ImageIO.read(path.toUri().toURL());

			if (image == null) {
				image = loadImage(tag, req);
				saveImage(tag, image, req);
			}

			images.add(path);
		}

		resp.getOutputStream().write(createResponse(images));
	}

	private byte[] createResponse(List<Path> images) {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();

		images.forEach(e -> array.put(e));
		object.put("images", array);

		return object.toString().getBytes();
	}

	private void saveImage(String tag, BufferedImage image, HttpServletRequest req) throws IOException {
		ImageIO.write(image, "jpg", Paths.get(req.getServletContext().getRealPath(THUMBNAILS + tag + ".jpg")).toFile());
	}

	private BufferedImage loadImage(String tag, HttpServletRequest req) throws MalformedURLException, IOException {
		BufferedImage image = ImageIO
				.read(Paths.get(req.getServletContext().getRealPath(THUMBNAILS + tag + ".jpg")).toUri().toURL());

		BufferedImage resized = new BufferedImage(DIMENSIONS, DIMENSIONS, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D graphics = resized.createGraphics();
		graphics.drawImage(image, 0, 0, DIMENSIONS, DIMENSIONS, null);
		graphics.dispose();

		return image;
	}
}
