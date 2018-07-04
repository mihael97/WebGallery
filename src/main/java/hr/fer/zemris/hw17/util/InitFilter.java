package hr.fer.zemris.hw17.util;

import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class represents {@link ServletContextListener}
 * 
 * @author Mihael
 *
 */
public class InitFilter implements ServletContextListener {

	/**
	 * Method is called when servlet is in process of initialization<br>
	 * In that situation,method calls {@link Util#init(ServletContextEvent)} for
	 * filling picture collection
	 * 
	 * @param sce
	 *            - servlet context event
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
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
