package vajracode.calocal.shared.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

import vajracode.calocal.shared.constants.LoginFields;
import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;

@Path(ResourcePaths.LOGIN)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthService extends DirectRestService {
    @POST    
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    void login(@FormParam(LoginFields.USERNAME) String username, @FormParam(LoginFields.PASSWORD) String password);

    @DELETE
    void logout();

    @GET
    UserData getCurrentUser();

    @PUT
	UserData register(RegistrationData data);

}
