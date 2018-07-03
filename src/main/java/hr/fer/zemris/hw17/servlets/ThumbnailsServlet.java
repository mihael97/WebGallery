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
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.gson.Gson;

import hr.fer.zemris.hw17.util.Picture;
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
	/**
	 * Every thumb nail dimensions should be <code>150x150</code>
	 */
	private final Integer DIMENSIONS = 150;

	private final static String THUMBNAILS = "WEB-INF/thumbnails/";
	private final static String PICTURES = "WEB-INF/slike/";

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

	private void saveImage(Path path, BufferedImage image, HttpServletRequest req) throws IOException {
		ImageIO.write(image, "jpg", path.toFile());
	}

	private BufferedImage loadImage(Path path, HttpServletRequest req) throws MalformedURLException, IOException {
		BufferedImage image = ImageIO.read(path.toUri().toURL());
		BufferedImage resized = new BufferedImage(DIMENSIONS, DIMENSIONS, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D graphics = resized.createGraphics();
		graphics.drawImage(image, 0, 0, DIMENSIONS, DIMENSIONS, null);
		graphics.dispose();

		return resized;
	}
}