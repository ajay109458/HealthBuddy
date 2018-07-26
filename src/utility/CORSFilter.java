package utility;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getStringHeaders().putSingle("Access-Control-Allow-Origin", "*");
		responseContext.getStringHeaders().putSingle("Access-Control-Allow-Credentials", "true");
		responseContext.getStringHeaders().putSingle("Access-Control-Allow-Methods",
				"GET, POST, DELETE, PUT, OPTIONS, HEAD");
		responseContext.getStringHeaders().putSingle("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With");

	}
}