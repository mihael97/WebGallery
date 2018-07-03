package hr.fer.zemris.hw17.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Method provides implementation for returning thumbnail icon(dimensions are
 * <code>150x150</code>)
 * 
 * @author Mihael
 *
 */
@WebServlet("servlets/getThumb")
public class ThumbnailsLoader extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Path to folder with thumbs
	 */
	private final static String THUMBS = "WEB-INF/thumbnails/";

	/**
	 * Method returns thumnail icon for picture which name is given by parameter
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		BufferedImage image = ImageIO
				.read(Paths.get(req.getServletContext().getRealPath(THUMBS)).resolve(name).toUri().toURL());
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", stream);
		resp.getOutputStream().write(stream.toByteArray());
	}
}
