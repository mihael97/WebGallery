package hr.fer.zemris.hw17.util;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitFilter implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			System.out.println(Paths.get(sce.getServletContext().getRealPath("WEB/INF")).resolve("opisnik.txt"));
			Util.init(sce);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Not implemented
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
