package org.javierito;

import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/consumer")
public class EventConsumer {
	
	
	private static final Logger logger = Logger.getLogger(EventConsumer.class);
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(JsonObject object) throws InterruptedException {
		int delay = object.getInt("delay");
		logger.info("Message is "+object.getString("message")+" Waiting: "+delay+ " seconds");
		Thread.sleep(TimeUnit.SECONDS.toMillis(delay));
	    return Response.ok().build();
	}
	
}
