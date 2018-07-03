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

@WebServlet("servlets/picture")
public class PictureServlet extends HttpServlet {
	private final static String PICTURES = "WEB-INF/slike/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		BufferedImage image = ImageIO
				.read(Paths.get(req.getServletContext().getRealPath(PICTURES)).resolve(name).toUri().toURL());
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", stream);
		resp.getOutputStream().write(stream.toByteArray());
	}
}
