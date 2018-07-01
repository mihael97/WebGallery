package hr.fer.zemris.hw17.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class represents {@link HttpServlet} and contains method for creating
 * thumbnails<br>
 * On disc is map whit already used <code>thumbnails</code>. If thumbnail we
 * want is not used before,method will create it for first time and saves it on
 * disc. Othrwisw,it will load generated thumbnail and redirects it to client
 * 
 * @author Mihael
 *
 */
public class ThumbnailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tagName = req.getParameter("tagName");

	}
}
