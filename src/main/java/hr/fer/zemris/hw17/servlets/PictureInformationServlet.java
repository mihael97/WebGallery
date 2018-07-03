package hr.fer.zemris.hw17.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hr.fer.zemris.hw17.util.Picture;
import hr.fer.zemris.hw17.util.Util;

/**
 * Class extends {@link HttpServlet} and returns informations about picture
 * 
 * @author Mihael
 *
 */
public class PictureInformationServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method returns informations about picture which name is given by parameter
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Picture picture = Util.getPictureByName(req.getParameter("name"), req);

		resp.getWriter().write(new Gson().toJson(picture));
		resp.getWriter().flush();
	}
}
