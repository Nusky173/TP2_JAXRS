package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.Infrastructure.User.*;
import fr.istic.taa.jaxrs.Model.LoginModel;
import fr.istic.taa.jaxrs.dao.concrete.IndividualDao;
import fr.istic.taa.jaxrs.dao.concrete.UserDao;
import fr.istic.taa.jaxrs.entities.Individual;
import fr.istic.taa.jaxrs.entities.Professional;
import fr.istic.taa.jaxrs.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import java.util.List;

@Path("/users")
@Produces({"application/json"})
public class UserResource {


    @GET
    @Operation(summary = "Find all users async",
            tags = {"users"},
            description = "Returns all the users of the applications in cascade.",
            responses = {
                    @ApiResponse(description = "All the users", content = @Content(
                            schema = @Schema(implementation = User.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
                    @ApiResponse(responseCode = "404", description = "Users not found")
            })
    public List<User> getUsers() {
        return new UserDao().findAll();
    }

    @GET
    @Operation(summary = "Find user by ID",
            tags = {"users"},
            description = "Returns the users with the id supply if exist",
            responses = {
                    @ApiResponse(description = "Return the user that match wit the id.", content = @Content(
                            schema = @Schema(implementation = User.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @Path("/{id}")
    public User getUser(@Parameter(
            description = "User id that need to be find",
            required = true)
                            @PathParam("id")long id) {
        return new UserDao().findOne(id);
    }

    @POST
    @Path("/professional")
    @Consumes({"application/json"})
    @Operation(summary = "Add a new professional to the app",
            tags = {"professional"},
            responses = {
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            })
    public Professional addProfessional(@Parameter(
            description = "professional object required to be added into the app",
            required = true)Professional professional) {
        final var prof = new AddProfessional();
        prof.addProfessional(professional);
        return professional;
    }

    @POST
    @Path("/individual")
    @Consumes({"application/json"})
    @Operation(summary = "Add a new individual to the app",
            tags = {"individual"},
            responses = {
                    @ApiResponse(responseCode = "405", description = "Invalid input")
            })
    public Individual addIndividual(@Parameter(
            description = "individual object required to be added into the app",
            required = true)Individual individual) {
        final var individualDao = new IndividualDao();
        individualDao.save(individual);
        return individual;
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete an user of the app",
            tags = {"individual"},
            responses = {
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "404", description = "user not found"),
            })
    public void deleteUser(@Parameter(
            description = "the id of the user to be deleted",
            required = true)@PathParam("id")long id){
        final var temp = new DeleteUser();
        temp.deleteUser(id);
    }

    @POST
    @Path("/log")
    @Consumes({"application/json"})
    @Operation(summary = "Log an user",
            tags = {"users"},
            description = "Returns an user if the entries match with an user log",
            responses = {
                    @ApiResponse(description = "The user that match with the login entries", content = @Content(
                            schema = @Schema(implementation = User.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public User logUser(@Parameter(
            description = "The login and the password that match the user informations",
            required = true) LoginModel login_P) {
        final var result = new UserDao().findUserLogAndPassword(login_P.login, login_P.password);
        if( result != null ) {
            System.out.println("Bienvenue : " + login_P.getLogin() );
            return result;
        } else {
            return null;
        }
    }


}
