package numbers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/numbers")
public class Numbers {

  @GET
  @Consumes("text/plain")
  @Produces("text/plain")
  @Path("{num1}/{num2}/{num3}")
  public String smallest(@PathParam("num1") int num1, @PathParam("num2") int num2, @PathParam("num3") int num3){
		int smallest = num1;
		if(num2 < smallest){
			smallest = num2;
		}
		if(num3 < smallest){
			smallest = num3;
		}
		return "Smallest: " + smallest;
	}
  

} 
