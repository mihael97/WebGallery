package hr.fer.zemris.hw17.servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import hr.fer.zemris.hw17.util.Util;

/**
 * Class represents {@link HttpServlet} and contains method for creating thumbnails<br>
 * On disc is map with already used <code>thumbnails</code>. If thumbnail we
 * want is not used before,method will create it for first time and save it on
 * disc. Otherwise,it will load generated thumbnail and redirects it to client
 * 
 * @author Mihael
 *
 */
@WebServlet("/servlets/thumbnails")
public class ThumbnailsServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Every thumbnail dimensions should be <code>150x150</code>
	 */
	private final Integer DIMENSIONS = 150;

	/**
	 * Path to folder with thumbnails
	 */
	private final static String THUMBNAILS = "WEB-INF/thumbnails/";
	/**
	 * Path to folder with pictures
	 */
	private final static String PICTURES = "WEB-INF/slike/";

	/**
	 * Method returns all pictures name where every picture must be described with
	 * tag from parameter
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Util.createFolder(req);

		List<String> pictures = Util.getPictureByTag(req.getParameter("tagname"), req);

		for (String picture : pictures) {
			BufferedImage image;
			Path pathThumb = Paths.get(req.getServletContext().getRealPath(THUMBNAILS)).resolve(picture);
			Path pathPic = Paths.get(req.getServletContext().getRealPath(PICTURES)).resolve(picture);

			if (!Files.exists(pathThumb)) {
				image = loadImage(pathPic, req);
				saveImage(pathThumb, image, req);
			} else {
				image = ImageIO.read(pathThumb.toUri().toURL());
			}
		}

		resp.setContentType("application/json;charset=UTF-8");

		String string = new Gson().toJson(pictures.toArray());
		resp.getWriter().write(string);
		resp.getWriter().flush();
	}

	/**
	 * Method saves photo in <code>jpg</code> format on disc
	 * 
	 * @param path
	 *            - path to place for storing
	 * @param image
	 *            - image
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @throws IOException
	 *             - exception during writing
	 */
	private void saveImage(Path path, BufferedImage image, HttpServletRequest req) throws IOException {
		ImageIO.write(image, "jpg", path.toFile());
	}

	/**
	 * Method loads picture in full size form disc and resizes it
	 * 
	 * @param path
	 *            - path to location where picture is stored
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @return resized photo
	 * @throws MalformedURLException
	 *             - exceptions with URL
	 * @throws IOException
	 *             - if exception during loading appears
	 */
	private BufferedImage loadImage(Path path, HttpServletRequest req) throws MalformedURLException, IOException {
		BufferedImage image = ImageIO.read(path.toUri().toURL());
		BufferedImage resized = new BufferedImage(DIMENSIONS, DIMENSIONS, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D graphics = resized.createGraphics();
		graphics.drawImage(image, 0, 0, DIMENSIONS, DIMENSIONS, null);
		graphics.dispose();

		return resized;
	}
}