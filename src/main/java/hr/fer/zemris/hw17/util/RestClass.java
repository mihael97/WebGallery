package hr.fer.zemris.hw17.util;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.google.gson.Gson;

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
			System.out.println("HEJHO!");
			return Response.status(Status.OK).entity(new Gson().toJson(Util.getTags())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}

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
