package vajracode.calocal.shared.service;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.Options;

import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;

@Path(ResourcePaths.USER)
public interface UserService extends DirectRestService {

	@POST
	UserData create(UserData user);
	
	@GET
	UserDataList list(@QueryParam("offset") @DefaultValue("0") int offset, 
		@QueryParam("limit") @DefaultValue("100") int limit);

	@GET
	@Path("/{id}")
	UserData get(@PathParam("id") long id);
	
	@PUT
	@Path("/{id}")
	UserData update(@PathParam("id") long id, UserData user);
	
	@DELETE
	@Path("/{id}")
	@Options(expect = {204})
	void delete(@PathParam("id") long id);

}
