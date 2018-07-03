package hr.fer.zemris.hw17.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.hw17.util.Picture;
import hr.fer.zemris.hw17.util.Util;

public class PictureInformationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Picture picture=Util.getPictureByName(req.getParameter("name"),req);
	}
}
