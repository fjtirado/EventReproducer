package org.javierito;


import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.eclipse.microprofile.reactive.messaging.OnOverflow.Strategy;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/events")
public class StartEventPublisher {

	@Inject
    @Channel("pepe")
	@OnOverflow(Strategy.UNBOUNDED_BUFFER)
	Emitter<JsonObject> emitter;
	
	private static final Logger logger = Logger.getLogger(StartEventPublisher.class);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(JsonObject json) {
		int numEvents = json.getInt("numEvents");
		while (numEvents-->0) {
			emitter.send(json).exceptionally(e -> {logger.warn("Trata de arrancarlo Carlos", e); return null;});
		}
		return Response.ok().build();
	}	
}
