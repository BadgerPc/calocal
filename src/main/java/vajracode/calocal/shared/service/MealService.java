package vajracode.calocal.shared.service;

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
    @Produces(MediaType.APPLICATION_JSON)
    MealDataList list(@QueryParam("fromDate") @DefaultValue("0") long fromDate, 
    		@QueryParam("toDate") @DefaultValue("0") long toDate, 
    		@QueryParam("fromTime") @DefaultValue("0") long fromTime, 
    		@QueryParam("toTime") @DefaultValue("0") long toTime,
    		@QueryParam("uid") @DefaultValue("0") long uid,
    		@QueryParam("offset") @DefaultValue("0") int offset,
    		@QueryParam("limit") @DefaultValue("100") int limit);
        
	@GET
	@Path("/{id}")
	MealData get(@PathParam("id") long id);
    
    @PUT
    @Path("/{id}")
    MealData update(@PathParam("id") long id, MealData meal);

}