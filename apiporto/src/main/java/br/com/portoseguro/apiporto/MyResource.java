package br.com.portoseguro.apiporto;

import java.sql.SQLException;
import java.util.List;

import br.com.portoseguro.apiporto.model.Bicicleta;
import br.com.portoseguro.apiporto.service.BicicletaService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	
	  @GET
	  @Produces(MediaType.TEXT_PLAIN) public String getIt() { return "Got it!"; }
	 
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response index() throws SQLException {
    	BicicletaService service = new BicicletaService();
		//return service.getAllBikes();
    	String json = "{\"msg\": 2}";
    	return  Response.ok(json).build();
		
	}
    
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) throws SQLException {
    	BicicletaService service = new BicicletaService();
		Bicicleta bike = service.findById(id);
		//String json = String.format("{\"id\": %d, \"marca\": %s, \"modelo\": %s}", bike.getId(), bike.getMarca(), bike.getModelo());
		if (bike == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(bike).build();
		
	}

}
