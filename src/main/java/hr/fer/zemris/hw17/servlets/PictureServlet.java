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

import com.google.gson.Gson;

/**
 * Class provides implementation for returning pictures in full size
 * 
 * @author Mihael
 *
 */
@WebServlet("servlets/picture")
public class PictureServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Path to folder with pictures
	 */
	private final static String PICTURES = "WEB-INF/slike/";

	/**
	 * Method returns picture which name is given with parameter in full size
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("U picture!");
		BufferedImage image = ImageIO
				.read(Paths.get(req.getServletContext().getRealPath(PICTURES)).resolve(name).toUri().toURL());
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", stream);
		resp.getWriter().write(new Gson().toJson(stream).toString());
	}
}
