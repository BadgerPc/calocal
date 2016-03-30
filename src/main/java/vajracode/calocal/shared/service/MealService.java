package vajracode.calocal.shared.service;

import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.Options;

import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;

@Path(ResourcePaths.MEAL)
@Consumes(MediaType.APPLICATION_JSON)
public interface MealService extends DirectRestService {
	
    @POST
    MealData create(MealData meal);
    
    @DELETE
    @Options(expect = {204})
    @Path("/{id}")
    void delete(@PathParam("id") long id);

    @GET
    MealDataList list(@QueryParam("fromDate") Date fromDate, 
    		@QueryParam("toDate") Date toDate, 
    		@QueryParam("fromTime") Date fromTime, 
    		@QueryParam("toTime") Date toTime,
    		@QueryParam("offset") @DefaultValue("0") long offset,
    		@QueryParam("limit") @DefaultValue("100") int limit);
        
	@GET
	@Path("/{id}")
	MealData get(@PathParam("id") long id);
    
    @PUT
    @Path("/{id}")
    MealData update(@PathParam("id") long id, MealData meal);

}