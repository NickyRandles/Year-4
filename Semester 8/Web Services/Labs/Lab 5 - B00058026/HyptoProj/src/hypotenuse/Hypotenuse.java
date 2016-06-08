package hypotenuse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hypotenuse")
public class Hypotenuse {

  @GET
  @Consumes("text/plain")
  @Produces("text/plain")
  @Path("{side1}/{side2}")
  public String hypotenuse(@PathParam("side1") double side1, @PathParam("side2") double side2){
	  return "Hypotenuse: " + Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
  }
  

} 

