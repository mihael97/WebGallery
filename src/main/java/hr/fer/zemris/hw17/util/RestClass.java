package hr.fer.zemris.hw17.util;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

/**
 * Class contains methods for REST API<br>
 * Supported methods are for:<br>
 * <ul>
 * <li>loading tag names from disc</li>
 * <li>creating folder for thumbnail storing</li>
 * <li>filtering pictures by tag name</li>
 * <li>returning pictures informations</li>
 * </ul>
 * 
 * @author Mihael
 *
 */
@Path("/methods")
public class RestClass {
	/**
	 * Method loads file form disc and returns set of <code>unique</code> tags
	 * 
	 * @return response with set of tags
	 * @throws IOException
	 *             -exception during reading
	 */
	@GET
	@Path("/index")
	@Produces("application/json")
	public static Response getTags() {
		try {
			return Response.status(Status.OK).entity(new Gson().toJson(Util.getTags())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}

	/**
	 * Method returns thumbnail for picture with given name
	 * 
	 * @param name
	 *            - picture name
	 * @return response with thumbnail
	 */
	@GET
	@Path("/thumbnails/{name}")
	@Produces("application/json")
	public static Response getThumbs(@PathParam("name") String name) {
		try {
			return Response.status(Status.OK).entity(new Gson().toJson(Util.getPictureByTag(name))).build();
		} catch (IOException e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}

	/**
	 * Method returns informations about picture
	 * 
	 * @param name
	 *            - picture name
	 * @return response with informations about picture
	 */
	@GET
	@Path("/picInfo/{name}")
	@Produces("application/json")
	public static Response getPictureInformation(@PathParam("name") String name) {
		try {
			return Response.status(Status.OK).entity(new Gson().toJson(Util.getPictureByName(name))).build();
		} catch (IOException e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}
}
