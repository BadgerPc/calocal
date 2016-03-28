package vajracode.calocal.shared.service;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

import javax.ws.rs.*;

import org.fusesource.restygwt.client.DirectRestService;

import vajracode.calocal.shared.constants.LoginFields;
import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;

@Path(ResourcePaths.LOGIN)
public interface AuthService extends DirectRestService {
    @POST    
    @Consumes(APPLICATION_FORM_URLENCODED)
    void login(@FormParam(LoginFields.USERNAME) String username, @FormParam(LoginFields.PASSWORD) String password);

    @DELETE
    void logout();

    @GET
    UserData getCurrentUser();

    @PUT   
	void register(RegistrationData data);

}
