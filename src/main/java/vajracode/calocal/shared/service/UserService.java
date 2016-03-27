package vajracode.calocal.shared.service;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.Options;

import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserListData;

@Path(ResourcePaths.USER)
public interface UserService extends DirectRestService {

	@GET
	UserListData listUsers(@QueryParam("page") int page);

	@Path("/{id}")
	@GET
	UserData getUser(@PathParam("id") long id);

	@POST
	UserData createUser(@QueryParam("name") String name, @QueryParam("role") Role role);

	@Path("/{id}")
	@PUT
	UserData updateUser(@PathParam("id") long id, @QueryParam("name") String name, @QueryParam("role") Role role);

	@Path("/{id}")
	@DELETE
	@Options(expect = {204})
	void deleteUser(@PathParam("id") long id);
}
