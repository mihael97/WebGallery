package hr.fer.zemris.hw17.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hr.fer.zemris.hw17.util.Util;

/**
 * Class represents {@link HttpServlet} which loads all unique tag names form
 * disc
 * 
 * @author Mihael
 *
 */
@WebServlet("/servlets/tags")
public class TagsServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method loads all unique tags from disc
	 * 
	 * @param req
	 *            - HTTP request
	 * @param resp
	 *            - HTTP response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Set<String> set = Util.getTags(req);
		String[] array = new String[set.size()];
		set.toArray(array);
		resp.setContentType("application/json;charset=UTF-8");

		Gson gson = new Gson();
		String jsonText = gson.toJson(array);
		resp.getWriter().write(jsonText);
		resp.getWriter().flush();

	}

}
